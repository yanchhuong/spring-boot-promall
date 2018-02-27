/**
 * 
 */

// TODO: replace API_KEY with the api key or somehow get it in here.
const ourAPIKey = 'YOUR_API_KEY_GOES_HERE';

/*
 * Simple array generation
 *
 * Assumes
 *  1. start is an integer
 *  1. end is an integer
 */
const range = (start, end, step) => {
  if (!Number.isInteger(start) || !Number.isInteger(end)) {
    console.error('Invalid input. Expected integers.')
    return null;
  }

  var effectiveStep = step;
  if (typeof effectiveStep == 'undefined') {
    effectiveStep = (start <= end) ? 1 : -1
  } else if (effectiveStep === 0) {
    console.error('Invalid step Step cannot be 0');
    return null;
  }

  if (effectiveStep < 0 && start < end) {
    console.error('Invalid input. Start must be greater than End when using a negative step');
    return null;
  } else if (effectiveStep > 0 && start > end) {
    console.error('Invalid input. Start must be less than End when using a positive step');
    return null;
  }

  const r = [];
  if (effectiveStep > 0) {
    for (var i = start; i < end; i += effectiveStep) {
      r.push(i);
    }
  } else {
    for (var i = start; i > end; i += effectiveStep) {
      r.push(i);
    }
  }
  return r;
};

// ref: http://stackoverflow.com/a/111545/7347047
function encodeQueryData(data) {
  let ret = [];
  for (let d in data)
    ret.push(encodeURIComponent(d) + '=' + encodeURIComponent(data[d]));
  return ret.join('&');
};

/*
 * Edmunds request helper
 *
 * ref: http://stackoverflow.com/a/111545/7347047
 */
const edmundBaseUrl = 'https://api.edmunds.com/api/vehicle/v2';
const edmundAPIRequest = ({ endpoint, parameters={}, success }) => {
  // Set the format to JSON if not otherwise specified
  const defaultParameters = {
    fmt: 'json',
    api_key: ourAPIKey,
    view: 'basic',
  };

  const url = '${edmundBaseUrl}${endpoint}?${encodeQueryData($.extend({}, defaultParameters, parameters ))}';
  $.get(url, success);
};



$(() => {
  // ============================================
  // Define helpful jQuery element selectors

  const $form = $('#car-form');
  const $formErrorAlert = $('#car-form-error-alert');

  const $formElements = {
    year: $('#vehicle-form-year'),
    make: $('#vehicle-form-make'),
    model: $('#vehicle-form-model'),
    style: $('#vehicle-form-style'),
    engine: $('#vehicle-form-engine'),
    transmission: $('#vehicle-form-transmission'),
    mileage: $('#vehicle-form-mileage'),
    zipcode: $('#vehicle-form-zipcode'),
  };

  const $resultElements = {
    wrapper: $('#results-wrapper'),
    maintenanceSchedules: $('#results-maintenance-schedules'),
    recalls: $('#results-recalls'),
    bulletins: $('#results-bulletins'),
  };

  // ============================================
  // Helper functions (could go in another file but I've chosen to leave them in
  // here for simplicity)

  const disableField = ($el) => {
    $el.prop('disabled', true);
  };

  const enableField = ($el) => {
    $el.prop('disabled', false);
  };

  const showResults = () => {
    $resultElements.wrapper.show();
  };

  const hideResults = () => {
    $resultElements.wrapper.hide();
  };

  const showFormError = (html) => {
    $formErrorAlert.html(html);
    $formErrorAlert.show();
  };

  const hideFormError = () => {
    $formErrorAlert.hide()
  };

  const handleInvalidField = ($el) => {
    $el.parent().removeClass('has-success');
    $el.parent().addClass('has-danger');
  };

  const handleValidField = ($el) => {
    $el.parent().removeClass('has-danger');
    $el.parent().addClass('has-success');
  };

  // ============================================
  // Handle all changes in the form

  const fieldChangeHandler = (opts) => {
    if (opts.val === 'None') {
      opts.backtrack(opts.val);
    } else {
      opts.proceed(opts.val);
    }
  };

  const handleChange = {
    year: function () {
      fieldChangeHandler({
        val: $(this).val(),
        backtrack: () => {
          resetFormTo($formElements.year);
        },
        proceed: (year) => {
          edmundAPIRequest({
            endpoint: '/makes',
            parameters: {
              year,
              view: 'basic',
            },
            success: (data) => {
              console.log('SUCCESS');
              console.log('data', data);
              const makeOptions = data.makes.map((make) => (
                '<option value="${make.niceName}">${make.name}</option>'
              ))
              makeOptions.unshift('<option value="none">None</option>')

              $formElements.make.html(makeOptions);
              resetFormTo($formElements.make);
            },
          });

        },
      });
    },
    make: function () {
      fieldChangeHandler({
        val: $(this).val(),
        backtrack: () => {
          resetFormTo($formElements.make);
        },
        proceed: (make) => {
          const year = $formElements.year.val();
          edmundAPIRequest({
            endpoint: '/${make}/models',
            parameters: {
              year,
              view: 'basic',
            },
            success: (data) => {
              const modelOptions = data.models.map((model) => (
                '<option value="${model.niceName}">${model.name}</option>'
              ))
              modelOptions.unshift('<option value="none">None</option>')

              $formElements.model.html(modelOptions);
              resetFormTo($formElements.model);
            },
          });
        },
      });
    },
    model: function () {
      fieldChangeHandler({
        val: $(this).val(),
        backtrack: () => {
          resetFormTo($formElements.model);
        },
        proceed: (model) => {
          const year = $formElements.year.val();
          const make = $formElements.make.val();
          edmundAPIRequest({
            endpoint: '/${make}/${model}/${year}/styles',
            parameters: {
              view: 'basic',
            },
            success: (data) => {
              const styleOptions = data.styles.map((style) => (
                '<option value="${style.id}">${style.name}</option>'
              ))
              styleOptions.unshift('<option value="none">None</option>')

              $formElements.style.html(styleOptions);
              resetFormTo($formElements.style);
            },
          });
        },
      });
    },
    style: function () {
      fieldChangeHandler({
        val: $(this).val(),
        backtrack: () => {
          resetFormTo($formElements.style);
        },
        proceed: (style) => {
          edmundAPIRequest({
            endpoint: '/styles/${style}/engines',
            parameters: {
              view: 'basic',
            },
            success: (data) => {
              console.log('engine data', data);
              const engineOptions = data.engines.map((engine) => (
                '<option value="${engine.id}">${engine.cylinder} Cyl ${engine.size} Litre</option>'
              ))
              engineOptions.unshift('<option value="none">None</option>')

              $formElements.engine.html(engineOptions);

              // I could enable both the transmission field and the engine field at
              // this point but choose not to for simplicity.
              resetFormTo($formElements.engine);
            },
          });
        },
      });
    },
    engine: function () {
      fieldChangeHandler({
        val: $(this).val(),
        backtrack: () => {
          resetFormTo($formElements.engine);
        },
        proceed: () => {
          const styleId = $formElements.style.val();

          edmundAPIRequest({
            endpoint: '/styles/${styleId}/transmissions',
            parameters: {
              view: 'basic',
            },
            success: (data) => {
              const transmissionOptions = data.transmissions.map((transmission) => (
                '<option value="${transmission.id}">${transmission.name} - ${transmission.transmissionType}</option>'
              ))
              transmissionOptions.unshift('<option value="none">None</option>')

              $formElements.transmission.html(transmissionOptions);
              resetFormTo($formElements.transmission);
            },
          });
        },
      });
    },
    transmission: function () {
      fieldChangeHandler({
        val: $(this).val(),
        backtrack: () => {
          resetFormTo($formElements.transmission);
        },
        proceed: () => {},
      });
    },
  };

  // Attach listeners
  for (key in $formElements) {
    if (typeof handleChange[key] !== 'undefined') {
      $formElements[key].on('change', handleChange[key]);
    }
  }

  // ============================================
  // Setup

  const resetFormTo = ($el) => {
    let keysToIgnore = [];
    if ($el === $formElements.year) {
      keysToIgnore = [
        'year',
      ];
    } else if ($el === $formElements.make) {
      keysToIgnore = [
        'year',
        'make',
      ];
    } else if ($el === $formElements.model) {
      keysToIgnore = [
        'year',
        'make',
        'model',
      ];
    } else if ($el === $formElements.style) {
      keysToIgnore = [
        'year',
        'make',
        'model',
        'style',
      ];
    } else if ($el === $formElements.engine) {
      keysToIgnore = [
        'year',
        'make',
        'model',
        'style',
        'engine',
      ];
    } else if ($el === $formElements.transmission) {
      keysToIgnore = [
        'year',
        'make',
        'model',
        'style',
        'engine',
        'transmission',
      ];
    }

    // Always ignore mileage and zipcode. They never need to be disabled.
    keysToIgnore = keysToIgnore.concat(['mileage', 'zipcode']);

    // Disable fields
    for (key in $formElements) {
      if (keysToIgnore.includes(key)) {
        enableField($formElements[key]);
      } else {
        disableField($formElements[key]);
      }
    }
  };

  hideResults();
  hideFormError();
  resetFormTo($formElements.year);

  // ============================================
  // Year

  // Populate the Year field
  const years = range(2017, 1989).map((year) => (
    '<option value="${year}">${year}</option>'
  ));
  years.unshift('<option value="none">None</option>')
  $formElements.year.html(years);

  // ============================================
  // Form

  const validateForm = (onValid) => {
    const nonSelectFormElements = [
      'mileage',
      'zipcode',
    ];

    let errorHtml = '';
    let val;

    // None of the select fields can be 'none'
    for (key in $formElements) {
      if (!nonSelectFormElements.includes(key)) {
        val = $formElements[key].val();
        if (val === 'none' || val === null) {
          // Invalid field state!
          errorHtml += '<p>Please select a ${key}</p>';
          handleInvalidField($formElements[key]);
        } else {
          handleValidField($formElements[key]);
        }
      }
    }

    // mileage
    const mileage = $formElements.mileage.val();
    if (mileage === '' || (!Number.parseInt(mileage) && Number.parseInt(mileage) !== 0)) {
      // Invalid field state!
      errorHtml += '<p>Please enter your mileage</p>';
      handleInvalidField($formElements.mileage);
    } else {
      handleValidField($formElements.mileage);
    }

    // zipocde
    const zipcode = $formElements.zipcode.val();
    if (zipcode === '' || !Number.parseInt(zipcode)) {
      // Invalid field state!
      errorHtml += '<p>Please enter your zip code</p>';
      handleInvalidField($formElements.zipcode);
    } else {
      handleValidField($formElements.zipcode);
    }

    if (errorHtml !== '') {
      showFormError(errorHtml);
    } else {
      onValid();
    }
  };

  $form.on('submit', function (e) {
    e.preventDefault();
    hideResults();

    validateForm(() => {
      // Valid form
      const modelyearid = $formElements.style.val();

      const totalAPIRequests = 3;
      let apiResponsesReceived = 0;

      edmundAPIRequest({
        endpoint: '/maintenance/actionrepository/findbymodelyearid/',
        parameters: {
          modelyearid,
        },
        success: (data) => {
          apiResponsesReceived += 1;

          const maintenanceScheduleList = data.actionHolder.map((action) => (
        		  '<li>${action.item} - ${action.itemDescription}</li>'
        		  ));
          $resultElements.maintenanceSchedules.html(
        		  '<ul>${maintenanceScheduleList.join("")}</ul>'
        		  );
          if (apiResponsesReceived === totalAPIRequests) {
            showResults();
          }
        },
      });

      edmundAPIRequest({
        endpoint: '/maintenance/reacallrepository/findbymodelyearid/',
        parameters: {
          modelyearid,
        },
        success: (data) => {
          apiResponsesReceived += 1;

          const recalls = data.recallHolder.map((recall) => (
            '<h5>${recall.recallNumber} - ${recall.componentDescription}</h5>'+
            '<p><strong>${recall.defectConsequence}</strong></p>'+
            '<p>${recall.defectCorrectiveAction}</p>'
          ));
          $resultElements.recalls.html(recalls.join(''));

          if (apiResponsesReceived === totalAPIRequests) {
            showResults();
          }
        },
      });

      edmundAPIRequest({
        endpoint: '/maintenance/servicebulletinrepository/findbymodelyearid/',
        parameters: {
          modelyearid,
        },
        success: (data) => {
          apiResponsesReceived += 1;

          const bulletins = data.serviceBulletinHolder.map((bulletin) => (
            '<h5>${bulletin.bulletinNumber} - ${bulletin.componentDescription}</h5>'+
            '<p>${bulletin.summaryText}</p>'
          ));
          $resultElements.bulletins.html(bulletins.join(''));

          if (apiResponsesReceived === totalAPIRequests) {
            showResults();
          }
        },
      });
    });
  });
});
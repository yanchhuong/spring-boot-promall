
$(document).ready(function(){
	
	//using FormData() object

	
});

function uploadFormData(){
   $('#result').html('');
  var csrfHeader = $("meta[name='_csrf_header']").attr("content");
  var csrfToken = $("meta[name='_csrf']").attr("content");
  var oMyForm = new FormData();
 
  oMyForm.append("file", file.files[0]);
  $.ajax({
    url: '/upload_file/uploadimg',
    data: oMyForm,
    dataType: 'text',
    processData: false,
    contentType: false,
    type: 'POST',
     beforeSend: function(xhr) {
      xhr.setRequestHeader(csrfHeader, csrfToken);
    },
    success: function(data){
      $('#imageUploadForm').append(data);
     
      console.log(data);
    }
  });
}


function readURL(input) {
  if (input.files && input.files[0]) {
    var reader = new FileReader();

    reader.onload = function (e) {
            $uploadedImg[0].style.backgroundImage='url('+e.target.result+')';
    };

    reader.readAsDataURL(input.files[0]);
  }
}
var $form = $("#imageUploadForm"), 
    $file = $("#file"), 
    $uploadedImg = $("#uploadedImg") ;
    $helpText = $("#helpText")
;
$file.on('change', function(){
//  readURL(this);
  $form.addClass('loading');
  $form.find('.imagSet').remove();
  uploadFormData();
  
});
$uploadedImg.on('webkitAnimationEnd MSAnimationEnd oAnimationEnd animationend', function(){
  $form.addClass('loaded');
});
$helpText.on('webkitAnimationEnd MSAnimationEnd oAnimationEnd animationend', function(){
  setTimeout(function() {
    $file.val(''); 
    $form.removeClass('loading').removeClass('loaded');
    $form.find('.helpText').removeClass();
  }, 5000);
});
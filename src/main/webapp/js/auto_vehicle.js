var _this;
var data={};
var auto_vehicle ={};
$(document).ready(function(){	
	onload();
	$(document).on("click","#showPic",function(){
		onload();
	 });
	 $(document).on("click","#showPic1",function(){
		 var url = auto_vehicle.getApiUrl('2','1FT7X2B68CEA05672');
		 $.ajax({
			   url: 'https://api.edmunds.com/api/vehicle/v2/makes',
			   data: {
			      format: 'json',
			      api_key:'v6tbfrqmemwb6ybx3tvf72u8',
			      view:'basic',
			      year:'2014',	 
			      state:'used'
			   },
			   error: function() {
			      $('#info').html('<p>An error has occurred</p>');
			   },
			   dataType: 'jsonp',
			   success: function(data) {
			     console.log(data);
			   },
			   type: 'GET'
		});
		 
	
	 });
});

function onload(){
	 var body = $('.carousel-inner');
	 var html="";
	 body.html("");
	 var url = auto_vehicle.getApiUrl('1','');
	 var options = {
			  styleId: "101396367",
		      fmt :"json",
		 //     api_key:'v6tbfrqmemwb6ybx3tvf72u8',
	           };
	 $.ajax({
		   url: url,
		   data: options,
		   error: function() {
		      $('#info').html('<p>An error has occurred</p>');
		   },
		   dataType: 'jsonp',
		   success: function(data) {
			   var len = data.length;
				 var j = 0;
				 var k=1;
				 while ( j < len) {
					 $.map(data[j].photoSrcs, function(i,v){
						var str = parseInt(i.substring(i.lastIndexOf("_") + 1,i.lastIndexOf(".")));
						if(str > 450){
							 if(k==1){
								// html += "<img src='http://media.ed.edmunds-media.com"+ i +"'/>";
								 html += "<div class='item active'><a class='hidden-xs highslide' href='http://media.ed.edmunds-media.com"+i+"'onclick='return hs.expand(this)'><img src='http://media.ed.edmunds-media.com"+i+"'></div>"; 
								// html += "<div class='item'><a class='hidden-xs highslide'><img src='http://media.ed.edmunds-media.com"+i+"'></a><img class='visible-xs' src='http://media.ed.edmunds-media.com"+i+"'></div>"; 
							 }else{
								 html += "<div class='item'><a class='hidden-xs highslide' href='http://media.ed.edmunds-media.com"+i+"'onclick='return hs.expand(this)'><img src='http://media.ed.edmunds-media.com"+i+"'></div>"; 
							 }
							 k++;
						}
						console.log(i);
					 });
					 j++;
				 }
			
				 body.append(html);
		   },
		   type: 'GET'
	});
		   
}
auto_vehicle.getApiUrl=function(type,vin){
	var api_key ='v6tbfrqmemwb6ybx3tvf72u8';
	var url="https://api.edmunds.com";
	//media photos
	if(type=='1'){
		   /* var options = {
				"styleId": "3883" 
		   };*/
          url+='/v1/api/vehiclephoto/service/findphotosbystyleid?api_key='+api_key;
	}
	//GET Get Full Vehicle Details by VIN
	else if(type=='2'){
		  /*  var options = {
				"fmt":"json"
			};*/
		  url+='/api/vehicle/v2/vins/'+/*1FT7X2B68CEA05672*/vin;
	}
	
	// GET Get Vehicle Details by SquishVIN /api/vehicle/v2/squishvins/:id/
	else if(type=='3'){
	    /* var options = {
			"fmt": "json"
		};*/
	  url+='/api/vehicle/v2/squishvins/'+ /*SquishVIN VIN*/ vin+'/';
    }

	//VEHICLE ENGINE & TRANSMISSION
	else if(type=='4'){
	    /* var options = {
			"view": "full",
			"fmt": "json"
		};*/
	  url+='/api/vehicle/v2/styles/'+ /*vehicle style ID*/vin+'/engines';
    }
	//SPEC: VEHICLE COLORS AND OPTIONS
	//GET  List of Options by Style ID
	else if(type=='5'){
	    /* var options = {
			"category": "Interior,Exterior,Roof,Interior Trim,Mechanical,Package ,Additional Fees,Other",
			"fmt": "json"
		};*/
	  url+='/api/vehicle/v2/styles/'+ /*vehicle style ID*/ vin+'/options';
    }
	
	//VEHICLE COLORS AND OPTIONS
	// Get List of Colors by Style ID
	else if(type=='6'){
	    /* var options = {
			"category": "Interior,Exterior",
			"fmt": "json"
		};*/
	  url+='/api/vehicle/v2/styles/'+ /*vehicle style ID*/ vin+'/colors';
    }
	
	//SPEC: VEHICLE EQUIPMENT
	// GET Get Equipment Details by Style ID
	else if(type=='7'){
	    /* var options = {
			"availability": "optional,standard,used",
			"fmt": "json"
		};*/
	  url+='/api/vehicle/v2/styles/'+ /*vehicle style ID*/ vin+'/equipment';
    }
	
	//MAINTENANCE
	// SERVICE: VEHICLE MAINTENANCE : GET Get by Vehicle Model Year ID
	else if(type=='8'){
	    /* var options = {
	        "modelyearid":"100537293",
			"fmt": "json"
		};*/
	  url+='/v1/api/maintenance/actionrepository/findbymodelyearid';
    }
	// VEHICLE SERVICE BULLETIN
	else if(type=='9'){
	    /* var options = {
	        "modelyearid":"100537293",
			"fmt": "json"
		};*/
	  url+='/v1/api/maintenance/servicebulletinrepository/findbymodelyearid';
    }
	//SERVICE: VEHICLE NOTES
	else if(type=='10'){
	    /* var options = {
			"fmt": "json"
		};*/
	  url+='/v1/api/maintenance/stylesnotes/'+/*vehicle style ID*/vin;
    }
	
	return url;
}


var app = angular.module('Wikipedia', []);
app.factory('GetSearch', function($http) {
  var wiki = {};
  wiki.jsonp = '&callback=JSON_CALLBACK';
  wiki.getWiki = function(term) {
    var endPoint = 'https://en.wikipedia.org/w/api.php?format=json&action=query&generator=search&gsrnamespace=0&gsrlimit=15&prop=pageimages|extracts&pilimit=max&exintro&explaintext&exsentences=1&exlimit=max&gsrsearch=';
    return $http.jsonp(endPoint + term + wiki.jsonp);
  };

  wiki.getRandom = function() {
    var endPoint = 'http://randomword.setgetgo.com/get.php';
    return $http.jsonp(endPoint + "?callback=JSON_CALLBACK");
  };

  return wiki;
});

app.controller('MainCtrl', function(GetSearch, $scope) { /*Model data bank*/
  $scope.MainData = {};
  $scope.MainData.results = [];
  $scope.MainData.listen = null;
  $scope.MainData.search = function(term) {
    GetResults($('input.dirty').val());
  };

  // on random search call this
  $scope.MainData.Random = function(term) {
    RandomSearch(term);
  };

  //When Mic icon is clicked
  $scope.MainData.Speech = function(url) {
    StartSpeech();
  };

  // GET  SEARCH 'from wikipedia'
  function GetResults(term) {
    $('input.dirty').val(''); //clear input field
    GetSearch.getWiki(term).success(function(data) {
      Render(data);
    });
  }

  // GET RANDOM WORD
  function RandomSearch() {
    GetSearch.getRandom().success(function(data) {
      GetResults(data.Word); // search with random word
      $scope.MainData.val = data.Word;
    });
  }

  //Render Data
  function Render(data) {
    var url = 'http://en.wikipedia.org/?curid=';
    var Value = data.query.pages;
    $scope.MainData.results = []; // reset data
    for (var key in Value) {
      var obj = {};
      var insideValue = Value[key];
      obj.title = insideValue.title;
      obj.text = insideValue.extract;
      obj.url = url + insideValue.pageid;
      $scope.MainData.results.push(obj);
    }
  }

  /*auto completion*/
  $(document).ready(function() {
    $(".dirty").autocomplete({

      source: function(request, response) {
        $.ajax({
          url: "https://en.wikipedia.org/w/api.php",
          dataType: "jsonp",
          data: {
            'action': "opensearch",
            'format': "json",
            'search': request.term,
            'gsrlimit': 8
          },
          success: function(data) {
            response(data[1]);
            $scope.$apply();
          }
        });
      },
      select: function(event, ui) {
        $scope.MainData.search();
      }

    });

  });

  //notification sounds
  var findSound = "http://oringz.com/ringtone/just-like-magic/sounds-948-just-like-magic/?download"
  var wikiSound = 'http://oringz.com/ringtone/gets-in-the-way/sounds-874-gets-in-the-way/?download';
  var findAudio = new Audio(findSound);
  var wikiAudio = new Audio(wikiSound);

  // Voice search
  function StartSpeech() {
    $('input.dirty').val(''); //clear input field
    if ($scope.MainData.listen) {
      $scope.MainData.listen = null;
      animate();
      return annyang.abort();
    } else {
      animate();
      $scope.MainData.listen = true;
      var commands = {
        'find *term': function(term) {
          GetResults(term);
          $scope.MainData.listen = null;
          $scope.MainData.val = term;
          animate();
          findAudio.play();
          return annyang.abort();

        },
        'wikipedia': function(term) {
          wikiAudio.play();
          //change placeholder
          $("input.dirty").attr("placeholder", 'Say "Find" + any Word').val("")
        },

      };

      annyang.addCommands(commands);
      annyang.start();
    }
  }
  // change placeholder and toggle some classes
  function animate() {
    $("input.dirty").attr("placeholder", 'Say "Wikipidia"').val("")
    $('.icon').toggleClass('listen');
  }

});


 
 
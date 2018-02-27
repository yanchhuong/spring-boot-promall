var _this;
$(document).ready(function(){
	 // on load 
	 //ListAll();

	 
	  Session();
       $("#btfacebooklog").click( "click", function() {
		   
    	   window.fbAsyncInit = function() {
    		    FB.init({
    		      appId      : 'your-app-id',
    		      xfbml      : true,
    		      version    : 'v2.7'
    		    });
    		    FB.AppEvents.logPageView();
    		  };

    		  (function(d, s, id){
    		     var js, fjs = d.getElementsByTagName(s)[0];
    		     if (d.getElementById(id)) {return;}
    		     js = d.createElement(s); js.id = id;
    		     js.src = "//connect.facebook.net/en_US/sdk.js";
    		     fjs.parentNode.insertBefore(js, fjs);
    		   }(document, 'script', 'facebook-jssdk'));
		   
	   });
       
       $("#txtfile").on('change', function(){
    	   uploadFormData();
       });
	  
});
function uploadFormData(){
	  var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	  var csrfToken  = $("meta[name='_csrf']").attr("content");
	    var formData = new FormData();
	    formData.append('file', $('input[type=file]')[0].files[0]);
	    console.log("form data " + formData);
	    $.ajax({
	      url: '/storage/uploadFile',
	      data: formData,
	      processData: false,
	      contentType: false,
	      type: 'POST',
	      beforeSend: function(xhr) {
			  xhr.setRequestHeader(csrfHeader, csrfToken);
		  },
	      success: function(data) {
	        alert(data);
	      },
	      error: function() {
	        $('#errorMsg').html("An error occurred.");
	      }
	    });
};
function Session(id){
	var input={};
	$.ajax({
    	type   : 'GET',
	    url    : "/test/list_user_session",
	    cache  : true
	})
    .done(function(dat) {
    	console.log(dat);
	})
}



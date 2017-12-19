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
	  
});

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



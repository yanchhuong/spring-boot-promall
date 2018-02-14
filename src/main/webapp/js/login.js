
var login = {};
var _url  = '';
$(document).ready(function(){
	
	if(typeof $.urlParam("input") != "undefined"){
		_url = JSON.parse(decodeURIComponent($.urlParam("input"))).link;
	};

	if(JSON.parse(decodeURIComponent($.urlParam("input"))).status == 'login'){
		$('.tab-group li:eq(1)').addClass('active');
		$('.tab-group li:eq(0)').removeClass('active');
		
		$("#signup").hide();
		$("#login").show();
//		var target = $('.tab-group li:eq(1)').attr('href');
//		$('.tab-content > div').not(target).hide();
//		$(target).fadeIn(600);
	}

	$('.form').find('input, textarea').on('keyup blur focus', function (e) {
		var $this = $(this),
      	label = $this.prev('label');

	  	if (e.type === 'keyup') {
			if ($this.val() === '') {
				label.removeClass('active highlight');
			} else {
        		label.addClass('active highlight');
        	}
			} else if (e.type === 'blur') {
	  				if( $this.val() === '') {
	  				label.removeClass('active highlight'); 
	  		} else {
	  				label.removeClass('highlight');   
	  		}   
    		} else if (e.type === 'focus') {
    			if( $this.val() === '') {
    				label.removeClass('highlight'); 
				}
    			else if( $this.val() !== '') {
    				label.addClass('highlight');
				}
    		}

		});
	
		$('.tab a').on('click', function (e) {
			e.preventDefault();
  
				$(this).parent().addClass('active');
				$(this).parent().siblings().removeClass('active');
  
				target = $(this).attr('href');
				
				$('.tab-content > div').not(target).hide();
				$(target).fadeIn(600);
	    });
		
		$('#btsign_up').on('click', function (e) {
			ftsign_up();
		});
		
	   $('#loginform').submit(function (event) {
		    var csrfHeader = $("meta[name='_csrf_header']").attr("content");
			var csrfToken  = $("meta[name='_csrf']").attr("content");
	        event.preventDefault();
	        var data = 'username=' + $('#username').val() + '&password=' + $('#Lpassword').val();
	        $.ajax({
	            data: data,
	            timeout: 1000,
	            type: 'POST',
	            url: '/login',
	            async : false,
	            beforeSend  : function(xhr) {
	       			  xhr.setRequestHeader(csrfHeader, csrfToken);
	       		},
	       		/*success: function(data){
	        	
	       		}*/
	        }).done(function(data, textStatus, jqXHR) {
	        	
	        	if(getSesion()!=""){	        		
					var	callbackFn = parent.wehrm.popup.callbackFn["login"];
			  		if($.isFunction(callbackFn)) {
			  			callbackFn({IS_TRUE:true});
			  			wehrm.popup.closePopup("login");
			            window.parent.location = document.location.origin+_url;
			  		}
				}
//	            var preLoginInfo = JSON.parse($.cookie('restsecurity.pre.login.request'));
//	            alert(preLoginInfo.url);
//	            window.location = preLoginInfo.url;
	        }).fail(function(jqXHR, textStatus, errorThrown) {
	            alert('Booh! Wrong credentials, try again!');
	        });
	        
       });
	

});

function ftsign_up(){
	  var csrfHeader = $("meta[name='_csrf_header']").attr("content");
 	  var csrfToken  = $("meta[name='_csrf']").attr("content");
	  var input = {};
	  input["fname"]  = $("#firstname").val();
	  input["lname"]  = $("#lastname").val();
	  input["email"]     = $("#email").val();
	  input["password"]  = $("#password").val();
	  $.ajax({
			type   : 'POST',
		    url    : "/users/sign_up",
		    data   : JSON.stringify(input),
		    cache: false,
		    dataType: 'json',
			contentType: 'application/json',
		    async: false,
		    beforeSend: function(xhr) {
		        xhr.setRequestHeader(csrfHeader, csrfToken);
		    },
	        data:JSON.stringify(input),
	    	success :function(result){
	    		alert(result);
	    }
   })
}
function getSesion(){
	var sessionObj = "";
	$.ajax({
		type   : 'GET',
	    url    : "/get_sesssion",
	    cache  : true,
	    async : false
	})
	.done(function(dat){
		if(dat.SESSION_IS!=null){
			sessionObj= dat.SESSION_IS.usercd;
		}
	})
	return sessionObj;
};

$.urlParam = function(name){
	    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
	    return results[1] || 0;
}


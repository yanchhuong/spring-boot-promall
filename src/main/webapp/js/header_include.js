var header_page = {};
var show;
var countClick = 0;
var clickChat = 0;
var appendChat = '';

$(document).ready(function(){

	if(getSession() != ''){
		header_page.loadProfileHeaderImage();
	}else{
		$(".wrap_setting").find("ul > li:eq(0), li:eq(2)").hide();
	}
	
	$(document).delegate("#logo", "click", function(){
    	window.location.href = document.location.origin;
    });
    	
	$(document).delegate("#login_sess", "click", function(){
		var status  = 'login';
		wehrm.popup.openPopup("login", {status}, function(data){
			callbackFn(data);
		});
	});
		
    $(document).delegate("#logout_sess", "click", function(){
    	var logOutYes = confirm("Are you sure ?");
    	if(logOutYes){
    		wehrm.ui.logOut();
    		location.reload();
    	}
    });
    
    $(document).delegate(".header_pro", "click", function(){
    	$("#pro_test").slideToggle();
    });
    
    $(document).delegate(".header_pro", "mouseenter", function(){
    	$("#pro_test").slideToggle();
    });
    
    $(document).delegate("#dropdown-toggle", "click", function(){
    	countClick++;
    	$(".wrapper").hide();
    	$('#notification-menu').slideToggle();
    	$(".wrap_setting").hide();
    	$(".navbox").hide();
    	if(countClick > 1){
    		countClick = 0;
    		$(".btn_alrim").css({"background": "url(../../img/bg/musical-bell-outline.png)  no-repeat 50% 14px", "background-size": "19px 23px"});
    	}else{
    		$(".btn_alrim").css({"background": "url(img/bg/musical-bell-outline-change.png) no-repeat 50% 14px", "background-size": "19px 23px"});
    	}
    });
    
    $(document).delegate("#dropdown-toggle", "mouseover", function(){
    	countClick++;
    	$(".wrapper").hide();
    	$('#notification-menu').slideToggle();
    	$(".wrap_setting").hide();
    	$(".navbox").hide();
    	if(countClick > 1){
    		countClick = 0;
    		$(".btn_alrim").css({"background": "url(../../img/bg/musical-bell-outline.png)  no-repeat 50% 14px", "background-size": "19px 23px"});
    	}else{
    		$(".btn_alrim").css({"background": "url(img/bg/musical-bell-outline-change.png) no-repeat 50% 14px", "background-size": "19px 23px"});
    	}
    });
    
    $(document).delegate("#notification-menu", "mouseleave", function(){
    	countClick = 0;
    	$('#notification-menu').hide();
    	$(".btn_alrim").css({"background": "url(../../img/bg/musical-bell-outline.png)  no-repeat 50% 14px", "background-size": "19px 23px"});
    });

    $('.chat[data-chat=person1]').addClass('active-chat');
    $('.person[data-chat=person1]').addClass('active');

    
    $('.left .person').mousedown(function(){
        if ($(this).hasClass('.active')) {
            return false;
        } else {
            var findChat = $(this).attr('data-chat');
            var personName = $(this).find('.name').text();
            $('.right .top .name').html(personName);
            $('.chat').removeClass('active-chat');
            $('.left .person').removeClass('active');
            $(this).addClass('active');
            $('.chat[data-chat = '+findChat+']').addClass('active-chat');
        }
    });
    
    $(document).delegate(".ico_chat", "click", function(){
    	countClick++;
    	if(countClick > 1){
    		countClick = 0;
    		$(".ico_chat").find("img").attr("src","img/bg/chat.png");
    	}else{
    		$(".ico_chat").find("img").attr("src","img/bg/chat-red.png");
    	}
    	$('#notification-menu').hide();
    	$(".wrapper").slideToggle();
    	$(".wrap_setting").hide();
    	$(".navbox").hide();
    	$(".btn_alrim").css({"background": "url(../../img/bg/musical-bell-outline.png)  no-repeat 50% 14px", "background-size": "19px 23px"});
    });
    
    $(document).delegate(".ico_chat", "mouseover", function(){
    	countClick++;
    	if(countClick > 1){
    		countClick = 0;
    		$(".ico_chat").find("img").attr("src","img/bg/chat.png");
    	}else{
    		$(".ico_chat").find("img").attr("src","img/bg/chat-red.png");
    	}
    	$('#notification-menu').hide();
    	$(".wrapper").slideToggle();
    	$(".wrap_setting").hide();
    	$(".navbox").hide();
    	$(".btn_alrim").css({"background": "url(../../img/bg/musical-bell-outline.png)  no-repeat 50% 14px", "background-size": "19px 23px"});
    });
    
    $(document).delegate("ul.people > li.human", "click", function(){
    	countClick = 0;
    	$(".ico_chat").find("img").attr("src","img/bg/chat.png");
    	$(".wrapper").hide();
//    	$(".chat_list").next().append(appendChat); 
    	$(".chatbox-holder").append(appendChat);
    });
    
    $(document).delegate(".fa-minus", "click", function(){ $(this).parents('.chatbox').toggleClass('chatbox-min'); });
    $(document).delegate(".fa-close", "click", function(){ $(this).parents('.chatbox').remove(); });
    
    $(document).delegate('#navbox-trigger', "click", function(){
    	$(".wrapper").hide();
    	$('#notification-menu').hide();
    	$(".wrap_setting").hide();
//    	$('#navigation-bar').toggleClass('navbox-open');
    	$(".navbox").slideToggle();
    });
    
    $(document).delegate('#navbox-trigger', "mouseover", function(){
    	$(".wrapper").hide();
    	$('#notification-menu').hide();
    	$(".wrap_setting").hide();
//    	$('#navigation-bar').toggleClass('navbox-open');
    	$(".navbox").slideToggle();
    });
    
    $(document).delegate('#setting_trigger', "click", function(){
    	$(".wrapper").hide();
    	$(".navbox").hide();
    	$('#notification-menu').hide();
    	$(".wrap_setting").slideToggle();
    });
    
    $(document).delegate(".animate > table, tbody", "click", function(){
    	var pcd = $(this).find('td').attr('data-pcd');
    	window.location.href = document.location.origin+'/profile?ref='+pcd;
    });

    
});



header_page.loadProfileHeaderImage = function(){
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken  = $("meta[name='_csrf']").attr("content");
	var input = {};
	
	input["usercd"] = getSession();
	
    $.ajax({
    	type	: 'POST',
		url		: '/userdetails/selectprofileimage',
		async	: false,
		cache	: true,
		data	: JSON.stringify(input),
	    dataType	: 'json',
	    contentType : 'application/json',
		beforeSend  : function(xhr) {
			xhr.setRequestHeader(csrfHeader, csrfToken);
		},
		success : function(data){
			$(".animate").find("table, tbody").html("");
			
			$.each(data.OUT_REC, function(i, v){
				$("#header_profile").attr("src","https://s3-us-west-1.amazonaws.com/g9bay-image-files/"+v.randname);
				
				if(v.pnm.length > 20){
					$(".animate").find("table, tbody").append('<tr><td style="padding: 7px 0 10px 38px;" data-pcd="'+v.pcd+'">'+v.pnm.substring(0,15)+' . . .</td></tr>');
				}else{
					$(".animate").find("table, tbody").append('<tr><td style="padding: 7px 0 10px 38px;" data-pcd="'+v.pcd+'">'+v.pnm+'</td></tr>');
				}
			});
		  }
	  });
};


function getSession(){
	 var sessionObj = "";
	 $.ajax({
		 type   : 'GET',
	     url    : "/get_sesssion",
	     cache  : true,
	     async : false
	 })
	 .done(function(dat){
	  if(dat.SESSION_IS!=null){
	   sessionObj = dat.SESSION_IS.usercd;
	   console.log("in getsession: "+sessionObj);
	  }
	 })
	 return sessionObj;
};




appendChat += '<div class="chatbox">																																	';
appendChat += '    <div class="chatbox-top">																															';
appendChat += '      <div class="chatbox-avatar">																															';
appendChat += '        <a target="_blank" href="https://www.facebook.com/mfreak"><img src="https://gravatar.com/avatar/2449e413ade8b0c72d0a15d153febdeb?s=512&d=https://codepen.io/assets/avatars/user-avatar-512x512-6e240cf350d2f1cc07c2bed234c3a3bb5f1b237023c204c782622e80d6b212ba.png" /></a> 	';
appendChat += '      </div>																																		';
appendChat += '      <div class="chat-partner-name">																														';
appendChat += '        <span class="status online"></span>																														';
appendChat += '        <a target="_blank" href="https://www.facebook.com/mfreak">Mamun Khandaker</a>																								';
appendChat += '      </div>																																		';
appendChat += '      <div class="chatbox-icons">																															';
appendChat += '        <a href="javascript:void(0);"><i class="fa fa-minus"></i></a>																										';
appendChat += '        <a href="javascript:void(0);"><i class="fa fa-close"></i></a>       																										';
appendChat += '      </div>      																																	';
appendChat += '    </div>																																		';
appendChat += '    																																			';
appendChat += '    <div class="chat-messages">																															';
appendChat += '       <div class="message-box-holder">																														';
appendChat += '        <div class="message-box">																															';
appendChat += '          Hello																																	';
appendChat += '        </div>																																	';
appendChat += '      </div>																																		';
appendChat += '      																																		';
appendChat += '      <div class="message-box-holder">																														';
appendChat += '        <div class="message-sender">																															';
appendChat += '           Mamun Khandaker																																';
appendChat += '         </div>																																	';
appendChat += '        <div class="message-box message-partner">																													';
appendChat += '          Hi.																																	';
appendChat += '        </div>																																	';
appendChat += '      </div>																																		';
appendChat += '      																																		';
appendChat += '      <div class="message-box-holder">																														';
appendChat += '        <div class="message-box">																															';
appendChat += '          How are you doing?																																';
appendChat += '        </div>																																	';
appendChat += '      </div>																																		';
appendChat += '      																																		';
appendChat += '      <div class="message-box-holder">																														';
appendChat += '        <div class="message-sender">																															';
appendChat += '           Mamun Khandaker																																';
appendChat += '         </div>																																	';
appendChat += '        <div class="message-box message-partner">																													';
appendChat += '          Im doing fine. How about you?																														';
appendChat += '        </div>																																	';
appendChat += '      </div>																																		';
appendChat += '      																																		';
appendChat += '      <div class="message-box-holder">																														';
appendChat += '        <div class="message-box">																															';
appendChat += '          I am fine.																																	';
appendChat += '        </div>																																	';
appendChat += '      </div>																																		';
appendChat += '      																																		';
appendChat += '      <div class="message-box-holder">																														';
appendChat += '        <div class="message-box">																															';
appendChat += '          Do you know why I knocked you today?																													';
appendChat += '        </div>																																	';
appendChat += '      </div>																																		';
appendChat += '      																																		';
appendChat += '      <div class="message-box-holder">																														';
appendChat += '        <div class="message-box">																															';
appendChat += '          Theres something important I would like to share with you. Do you have some time?																								';
appendChat += '        </div>																																	';
appendChat += '      </div>																																		';
appendChat += '      																																		';
appendChat += '      <div class="message-box-holder">																														';
appendChat += '        <div class="message-sender">																															';
appendChat += '           Mamun Khandaker																																';
appendChat += '         </div>																																	';
appendChat += '        <div class="message-box message-partner">																													';
appendChat += '          Yeah sure. Lets meet in the Einstein cafe this evening and discuss the matter.																								';
appendChat += '        </div>																																	';
appendChat += '      </div>																																		';
appendChat += '      																																		';
appendChat += '      <div class="message-box-holder">																														';
appendChat += '        <div class="message-sender">																															';
appendChat += '           Mamun Khandaker																																';
appendChat += '         </div>																																	';
appendChat += '        <div class="message-box message-partner">																													';
appendChat += '          I thought of coming to your place and discuss about it but I had to finish my projects and I didnt have enough time to go out of the house.																';
appendChat += '        </div>																																	';
appendChat += '      </div>      																																	';
appendChat += '    </div>																																		';
appendChat += '    																																			';
appendChat += '    <div class="chat-input-holder">																															';
appendChat += '      <textarea class="chat-input"></textarea>																													';
appendChat += '      <input type="submit" value="Send" class="message-send" />																											';
appendChat += '    </div>																																		';
appendChat += '    <div class="attachment-panel">																															';
appendChat += '      <a href="#" class="fa fa-thumbs-up"></a>																													';
appendChat += '      <a href="#" class="fa fa-camera"></a>																														';
appendChat += '      <a href="#" class="fa fa-video-camera"></a>																													';
appendChat += '      <a href="#" class="fa fa-image"></a>																														';
appendChat += '      <a href="#" class="fa fa-paperclip"></a>																													';
appendChat += '      <a href="#" class="fa fa-link"></a>																														';
appendChat += '      <a href="#" class="fa fa-trash-o"></a>																														';
appendChat += '      <a href="#" class="fa fa-search"></a>																														';
appendChat += '    </div>																																		';
appendChat += '</div>																																		';

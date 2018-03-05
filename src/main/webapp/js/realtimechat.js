//Global 

var stompClient = null;
var socket = null;
var whoami = null;
var grpcd = '';
var chkgroup = '';
var chkemptychat = false;
var chatLiveTime = 0;

var testHeight = 0;
$(document).ready(function(e){
	
	//Onload
	LoadData();
//	alert(getSession().id);
	$('.chat[data-chat=person2]').addClass('active-chat');
	$('.person[data-chat=person2]').addClass('active');

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
	
	$(".send").on("click",function(){
		var receiver = $("#reciever").attr("data-id");
		sendMessageTo(receiver,$("#txtCHAT").val());
		insertChatMessage();
		$("#txtCHAT").val("");
		newMessage();
	});
	
	$("#txtCHAT").on("keypress",function(event){
		var receiver = $("#reciever").attr("data-id");
		if (event.which == 13) {
			insertChatMessage();
			sendMessageTo(receiver,$("#txtCHAT").val());
			$("#txtCHAT").val("");
			newMessage();
         }
	});
	 
	//new chat box
	$(".messages").animate({ scrollTop: $(document).height() }, "fast");

	$("#profile-img").click(function() {
		$("#status-options").toggleClass("active");
	});

	$(".expand-button").click(function() {
		$("#profile").toggleClass("expanded");
	 	$("#contacts").toggleClass("expanded");
	});

	$("#status-options ul li").click(function() {
	 	$("#profile-img").removeClass();
	 	$("#status-online").removeClass("active");
	 	$("#status-away").removeClass("active");
	 	$("#status-busy").removeClass("active");
	 	$("#status-offline").removeClass("active");
	 	$(this).addClass("active");
	 	
	 	if($("#status-online").hasClass("active")) {
	 		$("#profile-img").addClass("online");
	 	} else if ($("#status-away").hasClass("active")) {
	 		$("#profile-img").addClass("away");
	 	} else if ($("#status-busy").hasClass("active")) {
	 		$("#profile-img").addClass("busy");
	 	} else if ($("#status-offline").hasClass("active")) {
	 		$("#profile-img").addClass("offline");
	 	} else {
	 		$("#profile-img").removeClass();
	 	};
	 	
	 	$("#status-options").removeClass("active");
	});

	$(document).delegate(".contactList", "click", function(){
		$(".message-input").show();
	 	if(!$(this).hasClass('active')){
	 		$(".contact").removeClass('active');
	 		$(this).addClass('active');
	 		
	 		$(this).children().find('i').fadeOut(1000);
	 		var imgSrc = $(this).children().find("img").attr("src");
	 		var personName = $(this).children().find(".name").text();
	 		var recievecd  = $(this).find("#usercd").val();
	 		$(".contact-profile").find("img").attr("src",imgSrc);
	 		$(".contact-profile").find("p").text(personName);
	 		$(".contact-profile").attr("data-id",$(this).attr('id'));
	 		insertGroup(recievecd);
	 		var data = {};
	 			data["id"] = $(this).attr("id");
	 			data["imgSrc"]  = imgSrc;
	 			data["name"]    = personName;
	 		    data["usercd"]  = recievecd;
	 		    data["grpcd"]   = grpcd;
	 		addChat(data);
	 		loadChatMesage(grpcd);
	 		
	 		if(chkgroup == 'yes'){
	 			if(!chkemptychat){
		 			$("#contacts ul").prepend(addChat(data));					
	 			}
	 			chkgroup = 'no';
	 			chkemptychat = false;
	 		}	 		
	 		
	 	}
	});

	$(document).delegate("#addcontact", "click", function(){
		 if(!$(this).hasClass('activeChat')){
			 $("#btnchat_list").removeClass('activeChat');
			 $(this).addClass('activeChat');
		 }
		 $("#reciever").attr("data-id","");
//		 LoadData();
		 window.location.reload();
	});
	 
	$(document).delegate("#btnchat_list", "click", function(){
		 if(!$(this).hasClass('activeChat')){
			 $("#addcontact").removeClass('activeChat');
			 $(this).addClass('activeChat');
		 }
		 loadChatListData();
	});
	 
	$(document).delegate(".chatList", "click", function(){
		 $(this).prependTo("#contacts ul");
		 $("#contacts ul li").removeClass('active');
		 $(this).addClass('active');
		 grpcd = $(this).find("#grpcd").val();
		 
 		var imgSrc = $(this).children().find("img").attr("src");
 		var personName = $(this).children().find(".name").text();
 		$(".contact-profile").find("img").attr("src",imgSrc);
 		$(".contact-profile").find("p").text(personName);
 		$(".contact-profile").attr("data-id",$(this).attr('id'));
		 loadChatMesage(grpcd);
	});
	 
	function newMessage() {
	 	message = $(".message-input input").val();
	 	if($.trim(message) == '') {
	 		return false;
	 	}
	 	$('<li class="replies"><img src="http://emilcarlsson.se/assets/mikeross.png" alt="" /><p>' + message + '</p></li>').appendTo($('.messages ul'));
	 	$('.message-input input').val(null);
	 	$('.contact.active .preview').html('<span>You: </span>' + message);
	 	$(".messages").animate({ scrollTop: $(document).height() }, "fast");
	};
	
	
	//initialchat
	connect();
});


function LoadData(){
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken  = $("meta[name='_csrf']").attr("content");
	var input = {};
	    
	$.ajax({
    	type   : 'POST',
	    url    : "/message/get_usercontact_list",
	    data   : JSON.stringify(input),
	    cache: false,
        dataType: 'json',
    	contentType: 'application/json',
        async: false,
        beforeSend: function(xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
	})
    .done(function(dat) {
    	var contact = $("#contacts ul").html("");
    	$("#profile .wrap").html("");
    	var strOurProfile = '';
    	
    	var html= "";
    	$.each(dat.OUT_REC,function(i,v){
    		if(v.usercd != getSession().usercd){
	          	  html    += '<li class="contact contactList" id="'+v.chatId+'">'
	        	  html    +='<div class="wrap">'
	        	  html    +='<span class="contact-status"></span>'
			      if(v.randname!=null){
		        	  html    +='<img width="40px" height="40px" src="'+"https://s3-us-west-1.amazonaws.com/g9bay-image-files/"+ v.randname+'" alt="">'
			      }else{
			          html    +=' <img src="http://emilcarlsson.se/assets/katrinabennett.png" alt="" />'
			      }
	        	  html    +='<div class="meta">'
	        	  html    +='<p class="name" style="float:left;">'+v.fullname+'</p>'
	        	  html    +='<p style="float:right;" class="chattime">9:30 PM</p>'
	        	  html    +='<p class="preview" style="margin-top: 20px;">You just got LITT up, Mike.</p>'
	        	  html    +='</div>'
	        	  html    +='</div>'
	        	  html    +='<input type="hidden" id="usercd" value="'+v.usercd+'" />'
	        	  html    +='</li>';
    		}else{
			      if(v.randname!=null){
			    	  strOurProfile +='	<img id="profile-img" height="50px" src="'+"https://s3-us-west-1.amazonaws.com/g9bay-image-files/"+ v.randname+'" class="online" alt="" />';
			      }else{
			    	  strOurProfile +=' <img id="profile-img" src="http://emilcarlsson.se/assets/mikeross.png" class="online" alt="" />';
			      }
	    		strOurProfile +='	<p>'+v.fullname+'</p>';
	    		strOurProfile +='	<i class="fa fa-chevron-down expand-button" aria-hidden="true"></i>';
	    		strOurProfile +='	<div id="status-options">';
	    		strOurProfile +='		<ul>';
	    		strOurProfile +='			<li id="status-online" class="active"><span class="status-circle"></span> <p>Online</p></li>';
	    		strOurProfile +='			<li id="status-away"><span class="status-circle"></span> <p>Away</p></li>';
	    		strOurProfile +='			<li id="status-busy"><span class="status-circle"></span> <p>Busy</p></li>';
	    		strOurProfile +='			<li id="status-offline"><span class="status-circle"></span> <p>Offline</p></li>';
	    		strOurProfile +='		</ul>';
	    		strOurProfile +='	</div>';
	    		strOurProfile +='	<div id="expanded">';
	    		strOurProfile +='		<label for="twitter"><i class="fa fa-facebook fa-fw" aria-hidden="true"></i></label>';
	    		strOurProfile +='		<input name="twitter" type="text" value="mikeross" />';
	    		strOurProfile +='		<label for="twitter"><i class="fa fa-twitter fa-fw" aria-hidden="true"></i></label>';
	    		strOurProfile +='		<input name="twitter" type="text" value="ross81" />';
	    		strOurProfile +='		<label for="twitter"><i class="fa fa-instagram fa-fw" aria-hidden="true"></i></label>';
	    		strOurProfile +='		<input name="twitter" type="text" value="mike.ross" />';
	    		strOurProfile +='	</div>';
    		}
    	})
    	$("#profile .wrap").append(strOurProfile);
    	contact.append(html);
    })
};

function loadChatListData(){
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken  = $("meta[name='_csrf']").attr("content");
	var input = {};
	
	input["usercd"] = getSession().usercd;
	$.ajax({
    	type   : 'POST',
	    url    : "/message/get_userchat_list",
	    data   : JSON.stringify(input),
	    cache: false,
        dataType: 'json',
    	contentType: 'application/json',
        async: false,
        beforeSend: function(xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
	})
    .done(function(dat) {
    	var contact = $("#contacts ul").html("");
    	var html = "";
    	$.each(dat.OUT_REC,function(i,v){

    		  html    +='<li class="contact chatList" id="'+v.chatId+'">'
        	  html    +='<div class="wrap">'
        	  html    +='<span class="contact-status"></span>'
		      if(v.randname!=null){
	        	  html    +='<img width="40px" height="40px" src="'+"https://s3-us-west-1.amazonaws.com/g9bay-image-files/"+ v.randname+'" alt="">'
		      }else{
		          html    +=' <img src="http://emilcarlsson.se/assets/katrinabennett.png" alt="" />'
		      }
        	  html    +='<div class="meta">'
        	  html    +='<p class="name" style="float:left;">'+v.fullname+'</p>'
        	  html    +='<p style="float:right;" class="chattime">9:30 PM</p>'
        	  html    +='<p class="preview" style="margin-top: 20px;">You just got LITT up, Mike.</p>'
        	  html    +='</div>'
        	  html    +='</div>'
        	  html    +='<input type="hidden" id="usercd" value="'+v.usercd+'" />'
        	  html    +='<input type="hidden" id="grpcd" value="'+v.grpcd+'" />'
        	  html    +='</li>';
    	})
    	contact.append(html);
//    	$("#contacts ul li:eq(0)").addClass('active');
    })
};

function insertGroup($recievecd){
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken  = $("meta[name='_csrf']").attr("content");
	var input = {};
	
	input["grpname"]    = getSession().fullname+","+$(".contact-profile").find("p").text();
	input["sendercd"]   = getSession().usercd;
	input["recievecd"]  = $recievecd;
	
	$.ajax({
    	type   : 'POST',
	    url    : "/message/insert_group",
	    data   : JSON.stringify(input),
	    cache  : false,
        dataType: 'json',
    	contentType: 'application/json',
        async: false,
        beforeSend: function(xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
	})
    .done(function(dat) {
    	console.log(dat.GRPCD);
    	grpcd = dat.GRPCD;
    	chkgroup = dat.CHKGROUP;
    	loadChatListData();
    	$("#addcontact").removeClass('activeChat');
    	$("#btnchat_list").addClass('activeChat');
    });
};



function loadChatMesage(data){
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken  = $("meta[name='_csrf']").attr("content");
	
	$.ajax({
    	type   : 'POST',
	    url    : "/message/list_chat",
	    data   : {"grpcd":data},
	    cache  : false,
	    async  : true,
        dataType: 'json',
//    	contentType: 'application/json',
        async: false,
        beforeSend: function(xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
	})
    .done(function(dat) {
    	$(".messages ul").html("");
    	var strChat = '';
    	if(dat.OUT_REC.length > 0){
    		chkemptychat = true;
    	}
    	$.each(dat.OUT_REC, function(i, v){
    		if(v.usercd == getSession().usercd){
        		strChat += '<li class="sent">';
  		      if(v.randname!=null){
  		    	strChat		+='<img width="40px" height="40px" src="'+"https://s3-us-west-1.amazonaws.com/g9bay-image-files/"+ v.randname+'" alt="">'
		      }else{
		    	strChat		+= '<img src="http://emilcarlsson.se/assets/mikeross.png" alt="" />';
		      }
        		strChat += '<p>'+v.reply+'</p>';			
        		strChat += '</li>';
    		}else{
        		strChat += '<li class="replies">';
        		  if(v.randname!=null){
	  		    	strChat +='<img width="40px" height="40px" src="'+"https://s3-us-west-1.amazonaws.com/g9bay-image-files/"+ v.randname+'" alt="">'
			      }else{
			    	strChat +='<img src="http://emilcarlsson.se/assets/mikeross.png" alt="" />';
			      }
        		strChat += '	<p>'+v.reply +'</p>';			
        		strChat += '</li>';
    		}
    	});
    	$(".messages ul").html(strChat);
    })
};

function insertChatMessage($recievecd){
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken  = $("meta[name='_csrf']").attr("content");
	var input = {};

	input["reply"]     = $("#txtCHAT").val();
	input["usercd"]    = getSession().usercd;
	input["grpcd"]     = grpcd;
	input["fullname"]  = getSession().fullname;
	input["ip"]  = "";
		
	$.ajax({
    	type   : 'POST',
	    url    : "/message/insert_chat",
	    data   : JSON.stringify(input),
	    cache  : false,
        dataType: 'json',
    	contentType: 'application/json',
        async: false,
        beforeSend: function(xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
	})
    .done(function(dat) {
    	console.log(dat.OUT_REC);
    });
};

function connect() {
        socket = new SockJS('/chat');
        stompClient = Stomp.over(socket);
        stompClient.connect('', '', function(frame) {
          whoami = frame.headers['user-name'];
          console.log('whoami: ' + whoami);
          console.log('Connected: ' + frame);
          stompClient.subscribe('/user/queue/messages', function(message) {
        	  console.log('message BODY: ' + JSON.stringify(message.body));
              showMessage(JSON.parse(message.body));              
          });
          stompClient.subscribe('/topic/active', function(activeMembers) {
            showActive(activeMembers);  
            console.log('TEST:'+ activeMembers);
          });
     });
}
      
function showActive(activeMembers) {
        renderActive(activeMembers.body);
        stompClient.send('/app/activeUsers', {}, '');
}
function renderActive(activeMembers) {
	    $("#userList").html("");
    	console.log('SHow :' +activeMembers);
        var previouslySelected = $('.user-selected').text();
        var usersWithPendingMessages = new Object();
       
        $.each($('.pending-messages'), function(index, value) {
             usersWithPendingMessages[value.id.substring(5)] = true; // strip the user-
             alert(value.id.substring(5));
        });
        var members = $.parseJSON(activeMembers);
        console.log("meme");
        console.log(members);
        var userDiv = $('<div>', {id: 'users'});
        $.each(members, function(index, value) {
        	var collection = $(".contact");
            collection.each(function(index,v) {
                if($(v).attr("id") === value){
                 $(v).find(".contact-status").addClass("online");
                }
            });
            if (value === whoami) { return true; }
         /*   var myId='user-' + value;
            var html="";
            html+='<li class="person" data-chat="'+value+'" id="'+myId+'">';
            html+='   <img src="https://s13.postimg.org/ih41k9tqr/img1.jpg" alt="" />';
            html+='       <span class="name">'+value+'</span>';
            html+='       <span class="time">2:09 PM</span>';
            html+='       <span class="preview">I was wondering...</span>';
            html+='   </li>';
            $("#userList").append(html);*/
        
          });
 }
      
function disconnect() {
        stompClient.disconnect();
        console.log("Disconnected");
}
function sendMessageTo(user,message) {
        var chatInput = '#input-chat-' + user;
        var message   = $("#txtCHAT").val();
        console.log("chatInput TESETTESET"+chatInput);
        if (!message.length) {
           return;
        }
   	
        stompClient.send("/app/chat",{"content-type": "application/json;charset=UTF-16"}, JSON.stringify({
          'recipient': user,
          'message' : message
        }));
        $(chatInput).val('');
        $(chatInput).focus();        
}

function showMessage(message) {
        var chatWindowTarget = (message.recipient === whoami) ? message.sender : message.recipient;
        
        var userDisplay = message.sender === whoami ? 'sent' : 'replies';
        var container = $(".messages ul");
        var html = '<li class="'+userDisplay+'">';
			html += '<img src="http://emilcarlsson.se/assets/harveyspecter.png" alt="">';
			html += '<p>'+message.message+'</p>';
			html += '</li>';
		
		
		$(".messages").animate({ scrollTop: $(document).height() + $(".messages").scrollTop() }, "fast");
        
        if (message.sender !== whoami) {
          var sendingUser = $('#' + message.sender);
//          if (!sendingUser.hasClass('active') && !sendingUser.hasClass('chatList')) {
        	  sendingUser.children().find(".meta p").css("font-weight","bold");
        	  sendingUser.children().find(".meta p:nth-child(2)").html("");
//        	  wehrm.string.formatTimeSixDigit(message.date.substring(8, 12),":")
        	  sendingUser.children().find(".meta p:nth-child(2)").append('<i style="margin-left: 8px;" class="fa fa-envelope" aria-hidden="true"></i>');
        	  sendingUser.children().find(".meta p:nth-child(3)").text(message.message);
//          }
        }

        if(message.sender === getSession().id || $("#reciever").attr("data-id") === message.sender){
        	container.append(html);
        }
        
}
function newMessageIcon() {
	var newMessage = '<i style="margin-left: 8px;" class="fa fa-envelope" aria-hidden="true"></i>';
    return newMessage;
}

function addChat(data){

	var html = '<li class="contact chatList active" id="'+data.id+'">';
		html += '<div class="wrap"><span class="contact-status"></span> ';
		html += '<img width="40px" height="40px" src="'+data.imgSrc+'" alt="">';
		html += '<div class="meta"><div class="name" style="float:left;">'+data.name+'</div>';
		html += '<div style="float:right;">9:30 PM<i style="margin-left: 8px; display: none;" class="fa fa-envelope" aria-hidden="true"></i></div>';
		html += '<p class="preview" style="margin-top: 20px;">You just got LITT up, Mike.</p></div></div>';
		html += '<input type="hidden" id="usercd" value="'+data.usercd+'">';
		html += '<input type="hidden" id="grpcd" value="'+data.grpcd+'"></li>';
		
	return html;
}


function getSession(){
	 var sessionObj;
	 $.ajax({
		 type   : 'GET',
	     url    : "/get_sesssion",
	     cache  : true,
	     async : false
	 })
	 .done(function(dat){
		 if(dat.SESSION_IS!=null){
			 sessionObj = dat.SESSION_IS;  
		 }
	 })
	 return sessionObj;
};




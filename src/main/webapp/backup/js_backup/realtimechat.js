//Global 

var stompClient = null;
var socket = null;
var whoami = null;
$(document).ready(function(e){
	//Onload
	LoadData();

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
	    $("#reciever").attr("data-id",findChat);
	});
	$(".send").on("click",function(){
		var receiver = $("#reciever").attr("data-id");
		sendMessageTo(receiver,$("#txtCHAT").val());
		$("#txtCHAT").val("");
	});
	$("#txtCHAT").on("keypress",function(event){
		var receiver = $("#reciever").attr("data-id");
		if (event.which == 13) {
			sendMessageTo(receiver,$("#txtCHAT").val());
			$("#txtCHAT").val("");
         }
	});
	
	//initialchat
///	 connect();
});
function LoadData(){
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken  = $("meta[name='_csrf']").attr("content");
	var input = {};
	    
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
    	cosole.log(dat);
    })
};
function connect() {
        socket = new SockJS('/chat');
        stompClient = Stomp.over(socket);
        stompClient.connect('', '', function(frame) {
          whoami = frame.headers['user-name'];
          console.log('whoami: ' + whoami);
          console.log('Connected: ' + frame);
          stompClient.subscribe('/user/queue/messages', function(message) {
        	  console.log('message: ' + JSON.parse(message.body));
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
            if (value === whoami) {
              return true;
            }
            
            $("li" ).each(function(index) {
              	 console.log(value);
            });
            
          /*      var myId='user-' + value;
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
        var message = $("#txtCHAT").val();
        console.log("chatInput"+chatInput);
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
function getChatWindow(userName) {
        var existingChats = $('.chat active-chat');
        var elementId = 'chat-' + userName;
        var containerId = elementId + '-container';
        var selector = '#' + containerId;
        var inputId = 'input-' + elementId;
        //if (!$(selector).length) {
          var chatContainer = $('<div>', {id: containerId, class: 'chat-container'});
          chatContainer.attr("data-chat",userName);
          var chatWindow = $('<div>', {id: elementId, class: 'bubble'});
          var chatInput = $('<input>', {id: inputId, type: 'text',
            placeholder: 'Enter a message.  Something deep and meaningful.'});
          var chatSubmit = $('<a>', {id: 'submit-' + elementId, class: 'write-link send'})
        

          if (existingChats.length) {
              chatContainer.hide();
          }
        return $(selector);
}
      
function showMessage(message) {
        var chatWindowTarget = (message.recipient === whoami) ? message.sender : message.recipient;
        
        var userDisplay = message.sender === whoami ? 'bubble me' : 'bubble you';
        var container = $(".active-chat");
		var html= '<div class="'+userDisplay+'">'+message.message+'</div>';
		container.append(html);
        
        if (message.sender !== whoami) {
          var sendingUser = $('#user-' + message.sender);
          if (!sendingUser.hasClass('user-selected') && !sendingUser.hasClass('pending-messages')) {
             sendingUser.append(newMessageIcon());
             sendingUser.addClass('pending-messages');
          }
        }
}
function newMessageIcon() {
        var newMessage = $('<span>', {class: 'newmessage'});
        newMessage.html('&#x2709;');
        return newMessage;
}
	




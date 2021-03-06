<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8"> 

<title>Chat</title>
<script src="/js-lib/sockjs-0.3.4.js"></script>
<script src="/js-lib/stomp.js"></script>
<script src="/js-lib/jquery-2.1.0.min.js"></script>
<link href="/css/chat.css" rel="stylesheet" type="text/css">
</head>
<body>
  <div>
    <div id="userList">
    </div>
  </div>
  <script type="text/javascript">
      var stompClient = null;
      var socket = null;
      var whoami = null;
      
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
    	console.log('Body :' +activeMembers.body);
        renderActive(activeMembers.body);
        stompClient.send('/app/activeUsers', {}, '');
      }
      
      function renderActive(activeMembers) {
    	console.log('SHow :' +activeMembers);
        var previouslySelected = $('.user-selected').text();
        var usersWithPendingMessages = new Object();
        $.each($('.pending-messages'), function(index, value) {
          usersWithPendingMessages[value.id.substring(5)] = true; // strip the user-
        });
        var members = $.parseJSON(activeMembers);
        var userDiv = $('<div>', {id: 'users'});
        $.each(members, function(index, value) {
          if (value === whoami) {
            return true;
          }
          var userLine = $('<div>', {id: 'user-' + value});
          userLine.addClass('user-entry');
          if (previouslySelected === value) {
            userLine.addClass('user-selected');
          }
          else {
            userLine.addClass('user-unselected');
          }
          var userNameDisplay = $('<span>');
          userNameDisplay.html(value);
          userLine.append(userNameDisplay);
          userLine.click(function() {
            var foo = this;
            $('.chat-container').hide();
            $('.user-entry').removeClass('user-selected');
            $('.user-entry').addClass('user-unselected');
            userLine.removeClass('user-unselected');
            userLine.removeClass('pending-messages');
            userLine.addClass('user-selected');
            userLine.children('.newmessage').remove();
            var chatWindow = getChatWindow(value);
            chatWindow.show();
          });
          if (value in usersWithPendingMessages) {
            userLine.append(newMessageIcon());
            userLine.addClass('pending-messages');
          }
          userDiv.append(userLine);
        });
        $('#userList').html(userDiv);
      }
      
      function disconnect() {
        stompClient.disconnect();
        console.log("Disconnected");
      }
      function sendMessageTo(user) {
        var chatInput = '#input-chat-' + user;
        var message = $(chatInput).val();
        
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
        var existingChats = $('.chat-container');
        var elementId = 'chat-' + userName;
        var containerId = elementId + '-container';
        var selector = '#' + containerId;
        var inputId = 'input-' + elementId;
        if (!$(selector).length) {
          var chatContainer = $('<div>', {id: containerId, class: 'chat-container'});
          var chatWindow = $('<div>', {id: elementId, class: 'chat'});
          var chatInput = $('<textarea>', {id: inputId, type: 'text', class: 'chat-input', rows: '2', cols: '75', 
            placeholder: 'Enter a message.  Something deep and meaningful.  Something you can be proud of.'});
          var chatSubmit = $('<button>', {id: 'submit-' + elementId, type: 'submit', class: 'chat-submit'})
          chatSubmit.html('Send');
          
          chatInput.keypress(function(event) {
            if (event.which == 13) {
              var user = event.currentTarget.id.substring(11); // gets rid of 'input-chat-'
              event.preventDefault();
              sendMessageTo(user);
            }
          });
          
          chatSubmit.click(function(event) {
            var user = event.currentTarget.id.substring(12); // gets rid of 'submit-chat-'
            console.log("RECIEVER:"+user);
            sendMessageTo(user);
            
          });
          
          chatContainer.append(chatWindow);
          chatContainer.append(chatInput);
          chatContainer.append(chatSubmit);
          
          if (existingChats.length) {
            chatContainer.hide();
          }
          
          $('body').append(chatContainer);
        }
        return $(selector);
      }
      
      function showMessage(message) {
        var chatWindowTarget = (message.recipient === whoami) ? message.sender : message.recipient;
        var chatContainer = getChatWindow(chatWindowTarget);
        var chatWindow = chatContainer.children('.chat');
        var userDisplay = $('<span>', {class: (message.sender === whoami ? 'chat-sender' : 'chat-recipient')});
        
        userDisplay.html(message.sender + ' says: ');
        
        console.log(message);
        var messageDisplay = $('<span>');
        messageDisplay.html(message.message );
        chatWindow.append(userDisplay).append(messageDisplay).append('<br/>');
        chatWindow.animate({ scrollTop: chatWindow[0].scrollHeight}, 1);
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
      
      $(document).ready(function() {
        connect();
      });
  </script>
</body>
</html>
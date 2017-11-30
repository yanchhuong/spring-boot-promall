(function(){
  
  var chat = {
    messageToSend: '',
    messageResponses: [
      'Why did the web developer leave the restaurant? Because of the table layout.',
      'How do you comfort a JavaScript bug? You console it.',
      'An SQL query enters a bar, approaches two tables and asks: "May I join you?"',
      'What is the most used language in programming? Profanity.',
      'What is the object-oriented way to become wealthy? Inheritance.',
      'An SEO expert walks into a bar, bars, pub, tavern, public house, Irish pub, drinks, beer, alcohol'
    ],
    init: function() {
      this.cacheDOM();
      this.bindEvents();
      this.render();
    },
    cacheDOM: function() {
      this.$chatHistory = $('.chat-history');
      this.$button = $('button');
      this.$textarea = $('#message-to-send');
      this.$chatHistoryList =  this.$chatHistory.find('ul');
    },
    bindEvents: function(event) {
      this.$button.on('click', this.addMessage.bind(this));
      this.$textarea.on('keyup', this.addMessageEnter.bind(this));
    },
    render: function() {
      this.scrollToBottom();
      if (this.messageToSend.trim() !== '') {
        var template = Handlebars.compile( $("#message-template").html());
        var context = { 
          messageOutput: this.messageToSend,
          time: this.getCurrentTime()
        };

        this.$chatHistoryList.append(template(context));
        this.scrollToBottom();
        this.$textarea.val('');
        
        // responses
        var templateResponse = Handlebars.compile( $("#message-response-template").html());
        var contextResponse = { 
          response: this.getRandomItem(this.messageResponses),
          time: this.getCurrentTime()
        };
        
        setTimeout(function() {
          this.$chatHistoryList.append(templateResponse(contextResponse));
          this.scrollToBottom();
        }.bind(this), 1500);
        
      }
      
    },
    
    addMessage: function() {
      this.messageToSend = this.$textarea.val()
      this.render(); 
      this.sendMessageTo('bbb');
    },
    sendMessageTo:function(user) {
        var chatInput = '#message-to-send';
        var message = $(chatInput).val();
        
        console.log("chatInput:"+message);
        
        if (!message.length) {
          return;
        }
        stompClient.send("/app/chat",{"content-type": "application/json;charset=UTF-16"}, JSON.stringify({
          'recipient': user,
          'message' : message
        })); 
        $(chatInput).val('');
        $(chatInput).focus();
    },
    
    addMessageEnter: function(event) {
        // enter was pressed
        if (event.keyCode === 13) {
          this.addMessage();
        }
    },
    scrollToBottom: function() {
       this.$chatHistory.scrollTop(this.$chatHistory[0].scrollHeight);
    },
    getCurrentTime: function() {
      return new Date().toLocaleTimeString().
              replace(/([\d]+:[\d]{2})(:[\d]{2})(.*)/, "$1$3");
    },
    getRandomItem: function(arr) {
      return arr[Math.floor(Math.random()*arr.length)];
    }
    
  };
  
  chat.init();
  
  var searchFilter = {
    options: { valueNames: ['name'] },
    init: function() {
      var userList = new List('people-list', this.options);
      var noItems = $('<li id="no-items-found">No items found</li>');
      
      userList.on('updated', function(list) {
        if (list.matchingItems.length === 0) {
          $(list.list).append(noItems);
        } else {
          noItems.detach();
        }
      });
    }
  };
  
  searchFilter.init();
  
})();


$(document).ready(function(){
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
     //  renderActive(activeMembers.body);
       stompClient.send('/app/activeUsers', {}, '');
     }
     
     function renderActive(activeMembers) {
       var body= $('.list').empty(''); 
       var previouslySelected = $('.user-selected').text();
       var usersWithPendingMessages = new Object();
       $.each($('.pending-messages'), function(index, value) {
         usersWithPendingMessages[value.id.substring(5)] = true; // strip the user-
       });
       var members = $.parseJSON(activeMembers);
    
       $.each(members, function(index, value) {
         if (value === whoami) {
           return true;
         }
         var userDiv ='<li class="clearfix">                                                                              '+
         '  <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/195612/chat_avatar_01.jpg" alt="avatar"> '+
         '  <div class="about">										              '+
         '    <div class="name">'+value +'</div>							      '+
         '    <div class="status">									              '+
         '      <i class="fa fa-circle online"></i> online						  '+
         '    </div>											                  '+
         '  </div>											                      ';
         $('.list').append(userDiv);
       });
     
     }
     
     function disconnect() {
       stompClient.disconnect();
       console.log("Disconnected");
     }
     function sendMessageTo(user) {
       var chatInput = '#input-chat-' + user;
       var message = $(chatInput).val();
       
       
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
             console.log("RECIEVER:"+user);
           }
         });
         chatSubmit.click(function(event) {
           var user = event.currentTarget.id.substring(12); // gets rid of 'submit-chat-'
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
});



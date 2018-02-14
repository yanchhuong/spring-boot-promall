<%@page import="com.code.session.SessionManager"%>
<%@page import="com.code.model.UserSessionBean"%>
<%@page import="com.code.session.UserSession"%>
<%

UserSessionBean sess = SessionManager.getSession(request, response);
SessionManager.logout(request, response);
String usercd = sess.getUsercd();

%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
   	<meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta charset="utf-8">
    <title>Home page</title>

    <%@include file="fragments/include_admin.jsp"%>
    
<script src="/js/test_dao.js"></script>       
   <style type="text/css">
        ul, li {
            list-style: none;
        }

        #wrapper {
            width: 900px;
            margin: 20px auto;
        }

        .data-container {
            margin-top: 20px;
        }

        .data-container ul {
            padding: 0;
            margin: 0;
        }

        .data-container li {
            margin-bottom: 5px;
            padding: 5px 10px;
            background: #eee;
            color: #666;
        }
    </style>
</head>
<body>

<input type="hidden" value="<%=usercd%>"/>
<div class="example">
    <div class="container">
        <div class="jumbotron">
            <nav role="navigation">
                <div class="alert alert-info">
                    <center><h3> WELCOME TO AJAX HOME</h3></center>
                </div>
                <div class="alert alert-info btn-block">
                    Server Time ${time}
                </div>
                <div class="alert alert-info btn-block">
                    Message: ${sessionScope.username}
                </div>
                
                <form>
                     <input type="button" value="Hello Ajax" id="ajaxtest">
                     <input type="button" value="Add" id="btAdd">
                     <input type="button" value="Search" id="tbSearch">
                     <input type="text" id="keysearch"/>
                     <br>
                </form>
            </nav>
        </div>

    </div>
    
    <div id="wrapper">
	    <section>
	        <div class="data-container"></div>
	        <div id="pagination-demo1"></div>
	        <div class="data-container"></div>
	        <div id="pagination-demo2"></div>
	    </section>
	</div>
</div>




<script>
$(function() {
  (function(name) {
    var container = $('#pagination-' + name);
    var sources = function () {
      var result = [];

      for (var i = 1; i < 196; i++) {
        result.push(i);
      }

      return result;
    }();

    var options = {
      dataSource: sources,
      callback: function (response, pagination) {
        window.console && console.log(response, pagination);

        var dataHtml = '<ul>';

        $.each(response, function (index, item) {
          dataHtml += '<li>' + item + '</li>';
        });

        dataHtml += '</ul>';

        container.prev().html(dataHtml);
      }
    };

    //$.pagination(container, options);

    container.addHook('beforeInit', function () {
      window.console && console.log('beforeInit...');
    });
    container.pagination(options);

    container.addHook('beforePageOnClick', function () {
      window.console && console.log('beforePageOnClick...');
      //return false
    });
  })('demo1');

  (function(name) {
    var container = $('#pagination-' + name);
    container.pagination({
      dataSource: 'https://api.flickr.com/services/feeds/photos_public.gne?tags=cat&tagmode=any&format=json&jsoncallback=?',
      locator: 'items',
      totalNumber: 120,
      pageSize: 20,
      ajax: {
        beforeSend: function() {
          container.prev().html('Loading data from flickr.com ...');
        }
      },
      callback: function(response, pagination) {
        window.console && console.log(22, response, pagination);
        var dataHtml = '<ul>';

        $.each(response, function (index, item) {
          dataHtml += '<li>' + item.title + '</li>';
        });

        dataHtml += '</ul>';

        container.prev().html(dataHtml);
      }
    })
  })('demo2');
})
</script>

</body>
</html>

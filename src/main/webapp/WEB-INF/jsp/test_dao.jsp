<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Home page</title>

     <%@include file="fragments/include_admin.jsp"%>
    <script src="/js/test_dao.js"></script>
   <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
   <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>
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
</div>
</body>
</html>

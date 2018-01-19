<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
 <%      
  DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
  Date date = new Date();
  String _localDatetime = dateFormat.format(date);
 %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
   <!-- default header name is X-CSRF-TOKEN -->
   <meta name="_csrf" content="${_csrf.token}"/>
   <meta name="_csrf_header" content="${_csrf.headerName}"/>
   
    <!--css  files-->
   <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css"> 
     
    <!--js file  -->
    <script src="../../js-lib/jquery-1.7.2.min.js?<%=_localDatetime%>"></script>
    <script src="../../js-lib/sockjs-0.3.4.js?<%=_localDatetime%>"></script>
    <script src="../../js-lib/stomp.js?<%=_localDatetime%>"></script>
    <script src="../../js-lib/jquery.validate.min.js?<%=_localDatetime%>"></script>
    <script src="../../js-lib/jquery.cookie.js?<%=_localDatetime%>"></script>
 <!-- popup -->
    <script type="text/javascript" src="../../js_admin/wehrm.popup.js?<%=_localDatetime%>"></script>
    <script type="text/javascript" src="../../js_admin/wehrm.ui.js?<%=_localDatetime%>"></script>
    <script type="text/javascript" src="../../js_admin/jquery.bpopup.min.js?<%=_localDatetime%>"></script>
    <script type="text/javascript" src="../../js-lib/wehrm.string.js?<%=_localDatetime%>"></script>
    
</head>
</html>
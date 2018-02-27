<%-- <%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%> --%>
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
   <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
   
   <link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'>
   <link rel="stylesheet" type="text/css" href="../../css/css_page/reset.css" media="all"> 
   <link rel="stylesheet" type="text/css" href="../../css/css_page/content.css" media="all">
   <link rel="stylesheet" type="text/css" href="../../css/css_page/content_media_screen.css" media="all">
   <link rel="stylesheet" type="text/css" href="../../css/css_page/button.css" media="all">
   
   <!-- pagination -->
   <link rel="stylesheet" type="text/css" href="../../css/css_page/pagination.css" media="all">
   <link rel="stylesheet" type="text/css" href="../../css/css_page/slider.css" media="all">
   
   <!-- icon link -->
   <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css'>

    <!--js file  
    <script src="../../js-lib/jquery-1.7.2.min.js"></script> 
    <script src="../../js-lib/sockjs-0.3.4.js"></script>
    <script src="../../js-lib/stomp.js"></script>
    <script src="../../js-lib/jquery.validate.min.js"></script> -->
        
    <script src="https://s3.amazonaws.com/codecademy-content/projects/jquery.min.js"></script>
    
    <script  src="../../js-lib/jquery-1.10.2.js?<%=_localDatetime%>"></script>
    <script  src="../../js-lib/collJsPlugin.js?<%=_localDatetime%>"></script>
    <script  src="../../js-lib/common.js?<%=_localDatetime%>"></script>
    
    <!-- datepicker -->
    <link rel="stylesheet" type="text/css" href="../../css_admin/jquery-ui.css" media="all">
    <script type="text/javascript" src="../../js_admin/jquery-ui.js?<%=_localDatetime%>"></script>
    <script type="text/javascript" src="../../js_admin/wehrm.ui.js?<%=_localDatetime%>"></script>
    
    <!-- pagination -->
    <script src="http://code.jquery.com/jquery-1.8.2.min.js"></script>
    <script src="../../js-lib/pagination.js?<%=_localDatetime%>"></script>
    
    <!-- popup -->
    <script type="text/javascript" src="../../js_admin/wehrm.popup.js?<%=_localDatetime%>"></script> 
    <script type="text/javascript" src="../../js_admin/jquery.bpopup.min.js?<%=_localDatetime%>"></script>

    <script  src="../../js-lib/tbl.paginging.js?<%=_localDatetime%>"></script>
    <script type="text/javascript" src="../../js-lib/lightslider.js?<%=_localDatetime%>"></script>
    <script type="text/javascript" src="/js/main_page.js?<%=_localDatetime%>"></script>
    


 
</head>

</html>
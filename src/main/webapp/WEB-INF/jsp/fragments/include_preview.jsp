<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
  <%  
     
  DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
  Date date = new Date();
  String _localDatetime = dateFormat.format(date);
  
 %>
  
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<!--<meta http-equiv="Cache-Control" content="No-Cache">
    <meta http-equiv="Pragma" content="No-Cache">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
   
    
    <link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'>
	<link rel="stylesheet" type="text/css" href="../../css/css_page/reset.css" media="all">
   	<link rel="stylesheet" type="text/css" href="../../css/css_page/content.css" media="all">
   	<link rel="stylesheet" type="text/css" href="../../css/css_page/content_media_screen.css" media="all">
   	<link rel="stylesheet" type="text/css" href="../../css/css_page/button.css" media="all">

	<!-- pagination -->
   	<link rel="stylesheet" type="text/css" href="../../css/css_page/pagination.css" media="all">

   	<link rel="stylesheet" type="text/css" href="../../css/css_page/slider.css" media="all">
    
<!--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
    <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.5.9/slick.min.css'>
    <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.5.9/slick-theme.min.css'> -->    
    
    <!-- icon link -->
   	<link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css'>
   	<script src="https://s3.amazonaws.com/codecademy-content/projects/jquery.min.js"></script>
    
    <script  src="../../js-lib/jquery-1.10.2.js?<%=_localDatetime%>"></script>
    <script  src="../../js-lib/collJsPlugin.js?<%=_localDatetime%>"></script>
    <script  src="../../js-lib/common.js?<%=_localDatetime%>"></script>
    <script  src="../../js-lib/moment.js?<%=_localDatetime%>"></script>
    
    <script type="text/javascript" src="../../js_admin/jquery.placeholder.min.js?<%=_localDatetime%>"></script>
	<script type="text/javascript" src="../../js-lib/lightslider.js?<%=_localDatetime%>"></script>
    <script type="text/javascript" src="../../js_admin/wehrm.ui.js?<%=_localDatetime%>"></script>
    
    <!-- pagination -->
    <!-- <script src="http://code.jquery.com/jquery-1.8.2.min.js"></script> -->
    <script  src="../../js-lib/pagination.js"></script>

  	<!-- popup -->
    <script type="text/javascript" src="../../js_admin/wehrm.popup.js?<%=_localDatetime%>"></script> 
    <script type="text/javascript" src="../../js_admin/jquery.bpopup.min.js?<%=_localDatetime%>"></script>
    
    <script type="text/javascript" src="../../js-lib/wehrm.string.js?<%=_localDatetime%>"></script>
    <script type="text/javascript" src="../../js_admin/_loading.js?<%=_localDatetime%>"></script>
    
<!--     <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
  	<script src='https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.5.9/slick.min.js'></script>
 -->
    <!-- more products -->
	<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js'></script>
    


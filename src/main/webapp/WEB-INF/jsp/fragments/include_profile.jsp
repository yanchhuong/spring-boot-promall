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
   	<link rel="stylesheet" type="text/css" href="../../css/css_page/reset.css" media="all">
    <link rel="stylesheet" type="text/css" href="../../css/css_page/content.css" media="all">
    <link rel="stylesheet" type="text/css" href="../../css/css_page/content_media_screen.css" media="all">
   	<link rel="stylesheet" type="text/css" href="../../css/css_page/reset_profile.css" media="all">
   	<link rel="stylesheet" type="text/css" href="../../css/css_page/main.css" media="all">
    <link rel="stylesheet" type="text/css" href="../../css/toastr.css" media="all">
    
    <link href="https://fonts.googleapis.com/css?family=Rubik:400,500,700" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    
    <!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css"> -->
	<link rel='stylesheet prefetch' href='http://bigbangburger.com/css/fonts.css'>
    <%-- <script  src="../../js-lib/jquery-1.10.2.js?<%=_localDatetime%>"></script> --%>
    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
   <%-- 
    <script  src="../../js-lib/common.js?<%=_localDatetime%>"></script>
 	--%>
    <script type="text/javascript" src="../../js_admin/jquery.placeholder.min.js?<%=_localDatetime%>"></script>
    <script type="text/javascript" src="../../js_admin/wehrm.ui.js?<%=_localDatetime%>"></script>
    <script type="text/javascript" src="../../js-lib/wehrm.string.js?<%=_localDatetime%>"></script>
    
    <!-- datepicker -->
    <link rel="stylesheet" type="text/css" href="../../css_admin/jquery-ui.css" media="all">
    <script type="text/javascript" src="../../js_admin/jquery-ui.js?<%=_localDatetime%>"></script>
    <script type="text/javascript" src="../../js_admin/wehrm.ui.js?<%=_localDatetime%>"></script>
    
    <!-- popup -->
    <script type="text/javascript" src="../../js_admin/wehrm.popup.js?<%=_localDatetime%>"></script> 
    <script type="text/javascript" src="../../js_admin/jquery.bpopup.min.js?<%=_localDatetime%>"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
    <!-- <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&amp;sensor=false"></script> -->
    
   
    <script type="text/javascript" src="../../js_admin/_loading.js?<%=_localDatetime%>"></script>
    <script type="text/javascript" src="/js/toastr-profile.js"></script>
    
    
    <script type="text/javascript" src="/js/profile_page_001.js"></script>
    
    
   <script  async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCeTaPMPKXYYp_py-BCqzRQCpGova3ukYE&callback=createMap"></script>
    
 	
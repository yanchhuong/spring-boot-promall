<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
<head>
  <meta charset="UTF-8">
  <title>Animated image upload form</title>
 <link rel="stylesheet" type="text/css" href="../../css/uploadimg.css" media="all"> 
 <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
 <!-- default header name is X-CSRF-TOKEN -->
 <meta name="_csrf" content="${_csrf.token}"/>
 <meta name="_csrf_header" content="${_csrf.headerName}"/>
<%--  <%@include file="../fragments/include_admin.jsp"%> --%>
<style>

	/* .btn_popclose{
    position: relative;
    top: -10px;
    right: 30px;
    width: 17px;
    height: 17px;
    float: right;
    z-index: 9;
	} */
</style>

</head>

<body>
  <div class="uploadWrapper">
<!--  <a href="#none" onclick="self.close()" class="btn_popclose">
 <img src="../img/btn/close-48.png" alt="Upload image"></a> -->
  <form id="imageUploadForm" class="imageUploadForm" method="post" action="/upload_file/uploadimg" enctype="multipart/form-data">
    <span class="helpText" id="helpText">Upload an image</span>
    <div class="img">
        <input type='file' id="file" class="uploadButton" accept="image/*" />
    </div>
    <div id="uploadedImg" class="uploadedImg">
      <span class="unveil"></span>
    </div>
    <span class="pickFile">
      <a href="#" class="pickFileButton">Save</a>
    </span>
  </form>
</div>
   <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

   <script type="text/javascript" src="../../js/popup_uploadimg_002.js"></script>

</body>
</html>


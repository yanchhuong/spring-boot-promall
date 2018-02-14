<%@page import="com.code.model.UserSessionBean"%>
<%@page import="com.code.session.SessionManager"%>
<%@page import="com.code.session.UserSession"%>
<%
	UserSessionBean sess = SessionManager.getSession(request, response);
	String usercd = sess.getUsercd();
%>

<html >
<head>
  <meta charset='UTF-8'>
  <title>Multi Step Form with Progress Bar using jQuery and CSS3</title>
  <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css'>
  <meta name="_csrf" content="${_csrf.token}"/>
  <meta name="_csrf_header" content="${_csrf.headerName}"/>
  <%@include file="fragments/include_admin.jsp"%>
  <link rel='stylesheet' href='/css/post.css'>
</head>'

<body>

<input type="hidden" id="usercd" value="<%=usercd%>" />

 <!-- multistep form -->
<form id='msform'>
  <!-- progressbar -->
  <ul id='progressbar'>
    <li class='active'>Select category</li>
    <li>Information Product</li>
    <li>Upload pictures</li>
	<li>Set your Address</li>
  </ul>
  <!-- fieldsets -->
  <fieldset id='form0'>
    <h2 class='fs-title'>Select category</h2>
    <h3 class='fs-subtitle'>This is step 1</h3>
    
	<div id="key-parties">
		<!-- Main Menu Container -->
		<ul id="key-parties-menu" class="style-15">
 
<!--  	  <li class="menu-item">
		      <a href="#" class="menu-item-btn selected" id="btn-1">
		        <span>Cars</span>
		      </a>

		      <div class="menu-item-content show-content" id="content-1">
		        <h3>Cars</h3>
		          <div class="contact-card">
		            <ul>
		              <li class="next"><a href="#" class="next">Isuzu</a></li>
		              <li><a href="#">Toyota</a></li>
 		              <li><a href="#">Lexus</a></li>
		              <li><a href="#">Tesla1</a></li>
		              <li><a href="#">Tesla2</a></li>
		              <li><a href="#">Tesla3</a></li>
		              <li><a href="#">Tesla4</a></li>
		              <li><a href="#">Tesla5</a></li>
		            </ul>
		          </div>
		     </div>
		    </li>

		    <li class="menu-item">
		      <a href="#" class="menu-item-btn" id="btn-2">
		        <span>Apartment</span>
		      </a>

		      <div class="menu-item-content" id="content-2">
		        <h3>Apartment</h3>
		          <div class="contact-card">
		            <ul>
		              <li><a href="#">Isuzu</a></li>
		              <li><a href="#">Toyota</a></li>
 		              <li><a href="#">Lexus</a></li>
		              <li><a href="#">Tesla1</a></li>
		              <li><a href="#">Tesla2</a></li>
		              <li><a href="#">Tesla3</a></li>
		              <li><a href="#">Tesla4</a></li>
		              <li><a href="#">Tesla5</a></li>
		            </ul>
		          </div>
		     </div>
		    </li>

		    <li class="menu-item">
		      <a href="#" class="menu-item-btn" id="btn-3">
		        <span>Smart Phone</span>
		      </a>
		    </li>
 -->
 		<div class="force-overflow"></div>
		</ul> 
		<!-- End of menu container -->
	</div>

</fieldset>

  <fieldset id='form1'>
    <h2 class='fs-title'>INFORMATION PRODUCT</h2>
    <h3 class='fs-subtitle'>Fill product's information below!</h3>
	<table >
		<tr>
	       <td>Category <span>*</span></td>
           <td><input type='text' name='Category' placeholder='Category'  disabled id="ctgr_nm" /> </td>
		</tr>
		<tr>
	       <td>Title <span> * </span></td>
            <td><input type='text' name='title' placeholder='title' id="title" /> </td>
		</tr>
		<tr>
			<td></td>
			<td><span id="alertPrice" style="color:red;display:none;">*Input only number</span></td>
		</tr>
		<tr>
	        <td>Price <span> *</span> </label> </td>
            <td><input type='text' name='price' placeholder='price' id="price" /></td>
		</tr>
		<tr>
		    <td>Description <span> *</span> </td>	
            <td><textarea name='description' rows='5' placeholder='Descript about your product information' id="description" ></textarea></td>
		 </tr>
	</table>
	<td><input type='button' name='previous' class='previous action-button' value='Previous' /> </td>
    <input type='button' name='next' class='next action-button' value='Next' />
  </fieldset>

<fieldset id='form2'>	
	<h2 class='fs-title'>Upload Original Picture</h2>
    <h3 class='fs-subtitle'>Be layolty to !</h3>
    
	<div id="results"> <!-- image will be replaced here --> </div>
	<div class="fileform" type="form" action="*"></div>
    <div id="upload">Upload more</div>

    <input type='button' name='previous' class='previous action-button' value='Previous' />
    <input type='button' name='next' class='next action-button' value='Next' />
</fieldset>

<fieldset id='form3'>
    <h2 class='fs-title'>Fill Your Contact</h2>
    <h3 class='fs-subtitle'>It's important for buyer!</h3>
    <table>
<!-- 		<tr>
	       <td>Name <span>*</span></td>
           <td><input type='text' name='Category' placeholder='your name'  visible='true' id="username" /> </td>
		</tr>   -->
		<tr>
			<td></td>
			<td><span id="alertPhone" style="color:red;display:none;">*Input only number</span></td>
		</tr>
		<tr>
	       <td>Phone <span> * </span></td>
            <td><input type='text' name='facebook' placeholder='availible contact number' id="phone_number" /> </td>
		</tr>
		<tr>
	        <td>Country <span> *</span> </label> </td>
            <td><input type='text' name='gplus' placeholder='country' id="country" /></td>
		 </tr>
		 <tr>
	        <td>Province<span> *</span> </label> </td>
            <td>
			   <form name=myform >
				<select id="province" name='state' placeholder='Your Location'>
					<!-- <option name=one value=one> one </option>
					<option name=two value=two> two </option>
					<option name=three value=three> three </option> -->
					<!-- <option name=three value=three selected> three </option> -->
				</select>
				</form>
			</td>
		</tr>
		<tr>
		    <td>Address <span> *</span> </td>	
            <td><textarea name='address' rows='5' placeholder='Your detail address' id="addr_detail" ></textarea></td>
		</tr> 
		<tr>
			<td><input type='checkbox' checked='true' value='1' id='save_contact' /></td>
			<td>Save contact!</td>
		</tr>
	</table>
	 
    <input type='button' name='previous' class='previous action-button' value='Previous' />
    <input type='submit' name='submit' class='submit action-button' value='Finish' id="saveAll" />
</fieldset>
  
</form>

<!--<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
	<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js'></script> -->
	<script src="/js/post_control_001.js"></script>

</body>
</html>

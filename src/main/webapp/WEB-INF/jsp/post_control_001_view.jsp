<html >
<head>
  <meta charset='UTF-8'>
  <title>Multi Step Form with Progress Bar using jQuery and CSS3</title>
  <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css'>
  <link rel='stylesheet' href='/css/post.css'>
</head>
<body>
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
		<ul id="key-parties-menu">
<!-- 
		    First Product
		    <li class="menu-item">
		      Menu Button
		      <a href="#" class="menu-item-btn selected" id="btn-1">
		        <span>Cars</span>
		        <i class="fa fa-chevron-down" aria-hidden="true"></i>  
		      </a>
		        
		      Product item
		      <div class="menu-item-content show-content" id="content-1">
		        <h3>Cars</h3>
		        <div class="contact-cards">
		          <div class="shadow-overlay"></div>
		          Card 1
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
		     </div>
		     
		    </li>

		    Second Product
		    <li class="menu-item">
		      Menu Button
		      <a href="#" class="menu-item-btn" id="btn-2">
		        <span>Apartment</span>
		        <i class="fa fa-chevron-down" aria-hidden="true"></i>  
		      </a>
		      Product item
		      <div class="menu-item-content" id="content-2">
		        <h3>Apartment</h3>
		        <div class="contact-cards">
		          <div class="shadow-overlay"></div>
		          Card 1
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
		     </div>
		    </li>

		    Third Product
		    <li class="menu-item">
		      Menu Button
		      <a href="#" class="menu-item-btn" id="btn-3">
		        <span>Smart Phone</span>
		        <i class="fa fa-chevron-down" aria-hidden="true"></i>  
		      </a>
		    </li> -->

		</ul> <!-- End of menu container -->
	</div>
	
</fieldset>
  
  
  <fieldset id='form1'>
    <h2 class='fs-title'>INFORMATION PRODUCT</h2>
    <h3 class='fs-subtitle'>Fill product's information below!</h3>
	<table >
		<tr>
	       <td>Category <span>*</span></td>
           <td><input type='text' name='Category' placeholder='Category'  disabled /> </td>
		</tr>  
		<tr>
	       <td>Title <span> * </span></td>
            <td><input type='text' name='facebook' placeholder='title' /> </td>
		</tr>  
		<tr>
	        <td>Price <span> *</span> </label> </td>
            <td><input type='text' name='gplus' placeholder='price'/></td>
		 </tr>
		<tr>
		    <td>Description <span> *</span> </td>	
            <td><textarea name='address' rows='5' placeholder='Descript about your product information' ></textarea></td>
		 </tr> 
		 
	</table>
	<td><input type='button' name='previous' class='previous action-button' value='Previous' /> </td>
    <input type='button' name='next' class='next action-button' value='Next' />
  </fieldset>
  <fieldset id='form2'>
    <h2 class='fs-title'>Upload Original Picture</h2>
    <h3 class='fs-subtitle'>Be layolty to !</h3>
      <div id="results">
	  </div>
	 <div class="fileform" type="form" action="*">   
     </div>
     <div id="upload">Upload more</div>  
	
	
    <input type='button' name='previous' class='previous action-button' value='Previous' />
    <input type='button' name='next' class='next action-button' value='Next' />
  </fieldset>
 
  <fieldset id='form3'>
    <h2 class='fs-title'>Fill Your Contact</h2>
    <h3 class='fs-subtitle'>It's important for buyer!</h3>
    <table >
		<tr>
	       <td>Name <span>*</span></td>
           <td><input type='text' name='Category' placeholder='your name'  visible='true'/> </td>
		</tr>  
		<tr>
	       <td>Phone <span> * </span></td>
            <td><input type='text' name='facebook' placeholder='availible contact number' /> </td>
		</tr>  
		<tr>
	        <td>Country <span> *</span> </label> </td>
            <td><input type='text' name='gplus' placeholder='country'/></td>
		 </tr>
		 <tr>
	        <td>State<span> *</span> </label> </td>
            <td>
			   <form name=myform >
				<select name='state' placeholder='Your Location'>
					<option name=one value=one> one </option>
					<option name=two value=two> two </option>
					<option name=three value=three> three </option>
					<option name=three value=three selected> three </option>
				</select>
				</form>
			</td>
		</tr>
		<tr>
		    <td>Address <span> *</span> </td>	
            <td><textarea name='address' rows='5' placeholder='Your detail address' ></textarea></td>
		</tr> 
		<tr>
			<td><input id='save_contact' type='checkbox' checked='true' value='1'></td>
			<td>Save contact!</td>
		</tr>
	</table>
	 
    <input type='button' name='previous' class='previous action-button' value='Previous' />
    <input type='submit' name='submit' class='submit action-button' value='Finish' />
  </fieldset>
  
</form>
      <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
      <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js'></script>
      <script src="/js/post_control_001.js"></script>

        <script>
        $(function() {
            var makeInput = function() {
              return $('<input type="file" accept="image/jpeg, image/gif, image/png" name="files[]" style="opacity:0;">');
            };

            $('#upload').click(function() {
              var hookInput = makeInput();
              var id = 'i' + parseInt((new Date)/1000);
              hookInput.attr('id', id);
              $('.fileform').append(hookInput);
              $('#' + id).click();
              $(hookInput).on('change', setImage);
            });

            function setImage() {
              for (var i = 0; i < this.files.length; i++) {
                var id = $(this).attr('id');
                var file = this.files[i];
                fr = new FileReader();
                fr.onload = function(e) {
                    var img = $('<img>');
                    img.attr('src', e.target.result);
                    img.css('height', '160px');
                    $('#results').append(img);
                    $(img).on('click', {id: id}, removeImage);
                };
                fr.readAsDataURL(file);
                if ($('#results').children().length > 5) {
                  $('#upload').css('background', '#ddd');
                  $('#upload').unbind();
                }
              }
            }
            function removeImage(e) {
              $(this).remove();
              $('#' + e.data.id).remove();
            }
        });
        </script>

</body>
</html>

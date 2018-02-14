var profile_page_001 = {};

var _map;
var _profileRandname = '';
var _imageStatus = '';
var onDisable = "../img/ico/icon_alim_off.png";
var onEnable = "../img/ico/icon_alim_on.png";
$(document).ready(function(){
	
	setTimeout(createMap, 1000);
	setTimeout(createMap, 2000);
	$("#map_canvas > div").trigger('dblclick');
	$(window).resize(function() {
	    // (the 'map' here is the result of the created 'var map = ...' above)
	    google.maps.event.trigger(map, "resize");
	});
	
	
	profile_page_001.loadCoverImage();
	profile_page_001.loadProfileImage();
	profile_page_001.loadData();
	profile_page_001.loadPageProduct();
	profile_page_001.loadPageSaveProduct();
	
	
	if($("#cv_usercd").val() != getSessionUsercd()){
		$(".bnt-ch-cover").hide();
//		$(".btn-change-pic").hide();
		$(".usr_pf").find(".user-name").click(false);
		
		$(".phone-image").hide();
		$(".edit-email").hide();
		$(".edit-fb").hide();
		$(".website-image").hide();
		$(".edit-BD").hide();
		$(".edit-gender").hide();
		$(".pro-num").hide();
		$(".pro-img img").css("top","0");
		$(".tab_cnt li:eq(1)").hide();
	}else{
		$(".usr_pf").prepend('<a href="javascript:" class="bnt-ch-cover">Update Cover Photo</a>');
	}
	
    /*$("#sub-cate-wrapper").hide();
    $("#sub-pt").hide();*/
    $("#lang-wrap").hide();
    
    $("#lang-switch").click(function(){
       $("#lang-wrap").toggle();
    });

    $(document).delegate(".bnt-ch-cover", "click", function(){
    	var status   = 'login';
    	_imageStatus = 'changeCover';
    	if($("#usercd").val() != $("#cv_usercd").val()){
    		wehrm.popup.openPopup("login", {status}, function(data){
    			callbackFn(data);
    		});
    	}else{
        	$(".select_pic").find("#file").trigger("click");
    	}
    });

    $(document).delegate("#user_pic", "click", function(e){
    	var status   = 'login';
    	_imageStatus = 'changeProfile';
    	if($("#usercd").val() != $("#cv_usercd").val()){
    		$(this).data('onclick').call(this, event || window.event);
    		wehrm.popup.openPopup("login", {status}, function(data){
    			callbackFn(data);
    		});
    	}else{
        	$(".select_pic").find("#file").trigger("click");
    	}
    });
    
    $(document).delegate("#select_pic", "change", function(){
    	uploadFormData();
    	if(_imageStatus == 'changeCover'){    		
    		updateCoverImage();
    		$(".usr_pf").css("background-image","url("+document.location.origin+"/upload_file/files/"+_profileRandname+")");
    	}else if(_imageStatus == 'changeProfile'){
        	updateProfileImage();
            $("#user_pic").find("img:eq(0)").attr("src",document.location.origin+"/upload_file/files/"+_profileRandname);
    	}
    });
    
    $(document).delegate(".user-name", "mouseenter", function(){
    	$("#blur").removeClass("blur");
    });
    
    $(document).delegate(".user-name", "mouseleave", function(){
    	$("#blur").addClass("blur");
    });
    
    $(document).delegate(".user-name", "click", function(){
    	var status  = 'login';
    	if($("#usercd").val() != $("#cv_usercd").val()){
    		wehrm.popup.openPopup("login", {status}, function(data){
    			callbackFn(data);
    		});
    	}else{
    		$(".user-name").attr("contenteditable","true");
        	$(".ico_save").show();
    	}
    	
    });
    
    $(document).delegate(".ico_save", "mouseenter", function(){
    	$("#blur").removeClass("blur");
    });

    $(document).delegate(".ico_save", "click", function(){
    	$(this).hide();
    	$("#blur").addClass("blur");
    	profile_page_001.updatePageName();
//    	alert($(".user-name").text());
    });
	
	$(document).delegate(".dropbtn", "click", function(){
//		$(".pro-num").find("#myDropdown").hide();
		$(this).next().slideToggle();
	});
	    
    $(document).delegate(".onOrOff", "click", function(){
		var status;
		if($(this).find("img").attr("src") == onEnable){
			$(this).find("img").attr("src",onDisable);
			status = false;
		}else{
			$(this).find("img").attr("src",onEnable);
			status = true;
		}
		var prcd = $(this).parents(".pro-num").siblings().find("#prcd").val();
		profile_page_001.loadUpdateStatusProduct(status,prcd);
		profile_page_001.loadPageProduct();
	});
    
    $(document).delegate("#usr_pf_posts", "click", function(){
    	if(!$(this).hasClass('on')){
    		$("#usr_pf_saves").removeClass('on');
    		$("#usr_pf_about").removeClass('on');
    		$(this).addClass('on');
    		$("#aboutus-wrapper").hide();
    		$("#save-product-wrapper").hide();
    		$("#main-content-wrapper").show();

    	}
    });

    $(document).delegate("#usr_pf_saves", "click", function(){
    	if(!$(this).hasClass('on')){
    		$("#usr_pf_posts").removeClass('on');
    		$("#usr_pf_about").removeClass('on');
    		$(this).addClass('on');
    		$("#aboutus-wrapper").hide();
    		$("#main-content-wrapper").hide();
    		$("#save-product-wrapper").show();
//    		profile_page_001.loadPageSaveProduct();
    	}
    });
    
    $(document).delegate("#wrap-value", "click", function(){
    	var prcd = $(this).children().siblings().find("#prcd").val();
    	var parentid = $(this).children().siblings().find("#parentId").val();
    	window.location.href = document.location.origin+'/preview?prcd='+prcd+"&parentid="+parentid;
    });
    
    $(document).delegate("#usr_pf_about", "click", function(){
    	if(!$(this).hasClass('on')){
    		$("#usr_pf_saves").removeClass('on');
    		$("#usr_pf_posts").removeClass('on');
    		$(this).addClass('on');
    		
    		$("#main-content-wrapper").hide();
    		$("#save-product-wrapper").hide();
    		$("#aboutus-wrapper").show();
    	}
    });
    
    $(document).delegate("#seller_contact", "click", function(){
    	$("#seller_place").removeClass('active');
    	$("#seller_detail").removeClass('active');
    	$("#seller_feedback").removeClass('active');
    	$(this).addClass('active');
    	
    	$("#labels_detail").hide();
    	$("#labels_place").hide();
    	$("#labels_contact").show();
    	$("#labels_feedback").hide();
    });

    $(document).delegate("#seller_place", "click", function(){
    	$("#seller_contact").removeClass('active');
    	$("#seller_detail").removeClass('active');
    	$("#seller_feedback").removeClass('active');
    	$(this).addClass('active');
    	
    	$("#labels_contact").hide();
    	$("#labels_place").show();
    	$("#labels_feedback").hide();
    	$("#labels_detail").hide();
    });
    
    $(document).delegate("#seller_detail", "click", function(){
    	$("#seller_contact").removeClass('active');
    	$("#seller_place").removeClass('active');
    	$("#seller_feedback").removeClass('active');
    	$(this).addClass('active');
    	
    	$("#labels_contact").hide();
    	$("#labels_place").hide();
    	$("#labels_feedback").hide();
    	$("#labels_detail").show();
    });

    $(document).delegate("#seller_feedback", "click", function(){
    	$("#seller_contact").removeClass('active');
    	$("#seller_place").removeClass('active');
    	$("#seller_detail").removeClass('active');
    	$(this).addClass('active');
    	
    	$("#labels_contact").hide();
    	$("#labels_place").hide();
    	$("#labels_detail").hide();
    	$("#labels_feedback").show();
    });
    

    $('#contact-car').hide();
    $('#contact-bus').hide();
    $('#contact-bike').hide();
    $('#contact-phone').hide();
    $('#contact-mail').hide();

    $('#contact-carClick').removeClass("active");
    $('#contact-busClick').removeClass("active");
    $('#contact-bikeClick').removeClass("active");
    $('#contact-phoneClick').removeClass("active");
    $('#contact-mailClick').removeClass("active");

    $('#contact-map').show();
    $("#contact-mapClick").addClass("active");

	// contact page toogles
	$("#contact-mapClick").click(function () {
	    $('#contact-car').hide();
	    $('#contact-bus').hide();
	    $('#contact-bike').hide();
	    $('#contact-phone').hide();
	    $('#contact-mail').hide();
	
	    $('#contact-carClick').removeClass("active");
	    $('#contact-busClick').removeClass("active");
	    $('#contact-bikeClick').removeClass("active");
	    $('#contact-phoneClick').removeClass("active");
	    $('#contact-mailClick').removeClass("active");
	
	    $('#contact-map').show();
	    $("#contact-mapClick").addClass("active");
	});
	
	$("#contact-carClick").click(function () {
	    $('#contact-map').hide();
	    $('#contact-bus').hide();
	    $('#contact-bike').hide();
	    $('#contact-phone').hide();
	    $('#contact-mail').hide();
	
	    $('#contact-mapClick').removeClass("active");
	    $('#contact-busClick').removeClass("active");
	    $('#contact-bikeClick').removeClass("active");
	    $('#contact-phoneClick').removeClass("active");
	    $('#contact-mailClick').removeClass("active");
	
	    $('#contact-car').show();
	    $("#contact-carClick").addClass("active");
	});
	
	$("#contact-busClick").click(function () {
	    $('#contact-map').hide();
	    $('#contact-car').hide();
	    $('#contact-bike').hide();
	    $('#contact-phone').hide();
	    $('#contact-mail').hide();
	
	    $('#contact-mapClick').removeClass("active");
	    $('#contact-carClick').removeClass("active");
	    $('#contact-bikeClick').removeClass("active");
	    $('#contact-phoneClick').removeClass("active");
	    $('#contact-mailClick').removeClass("active");
	
	    $('#contact-bus').show();
	    $("#contact-busClick").addClass("active");
	});
	
	$("#contact-bikeClick").click(function () {
	    $('#contact-map').hide();
	    $('#contact-car').hide();
	    $('#contact-bus').hide();
	    $('#contact-phone').hide();
	    $('#contact-mail').hide();
	
	    $('#contact-mapClick').removeClass("active");
	    $('#contact-carClick').removeClass("active");
	    $('#contact-busClick').removeClass("active");
	    $('#contact-phoneClick').removeClass("active");
	    $('#contact-mailClick').removeClass("active");
	
	    $('#contact-bike').show();
	    $("#contact-bikeClick").addClass("active");
	});
	
	$("#contact-phoneClick").click(function () {
	    $('#contact-map').hide();
	    $('#contact-car').hide();
	    $('#contact-bus').hide();
	    $('#contact-bike').hide();
	    $('#contact-mail').hide();
	
	    $('#contact-mapClick').removeClass("active");
	    $('#contact-carClick').removeClass("active");
	    $('#contact-busClick').removeClass("active");
	    $('#contact-bikeClick').removeClass("active");
	    $('#contact-mailClick').removeClass("active");
	
	    $('#contact-phone').show();
	    $("#contact-phoneClick").addClass("active");
	});
	
	
	$("#contact-mailClick").click(function () {
	    $('#contact-map').hide();
	    $('#contact-car').hide();
	    $('#contact-bus').hide();
	    $('#contact-bike').hide();
	    $('#contact-phone').hide();
	
	    $('#contact-mapClick').removeClass("active");
	    $('#contact-carClick').removeClass("active");
	    $('#contact-busClick').removeClass("active");
	    $('#contact-bikeClick').removeClass("active");
	    $('#contact-phoneClick').removeClass("active");
	
	    $('#contact-mail').show();
	    $("#contact-mailClick").addClass("active");
	});
	
//	$(document).delegate("#phone > li", "click", function(){
//		$(this).hide();
//		if(!$("#clphno").hasClass('on')){
//			$(this).next("#clphno").show();
//		}
//		$("input").focus();
//		$(".btnPhone").show();
//		$(this).next("#clphno").addClass('on');
//	});
	var cntClick = 0;
	$(document).delegate(".phone-image", "click", function(){
		if(cntClick < 1 || !$("#phone span").hasClass('phoneno')){
			cntClick++;
			$("#phone span").addClass("phoneno");
		}else{
			cntClick = 0;
			$("#phone span").removeClass("phoneno");
		}
		$(".phono").slideToggle();
	});

	$(document).delegate("#btnaddph", "click", function(){
		var morphno = $("#mophno").val();
		if($("#phone span").length < 4 && $("#mophno").val().substring(0,1) == '0'){
			if(morphno != '' && !isNaN(morphno) && morphno.length >= 9 && $("#phone span").length < 4){
				var str = $('<span class="phoneno">'+wehrm.string.formatPhoneNumber(morphno, "")+'<a href="javascript:" id="deleteph"></a></span>');
				$("#phone").append(str);
				$("#mophno").val("");
				profile_page_001.updatePhonno();
				toastr["success"]("Your number " + morphno + " updated successfully.");
			}else{
				toastr["error"]("Number incorrectly.");
			}
		}else{
			toastr["error"]("You are input more than 4 numbers, you can delete some of them", "Error");
		}
	});

	$(document).delegate("#btndoneph", "click", function(){
		$(".phono").slideToggle();
		$("#phone span").removeClass("phoneno");
	});
	
	$(".phono input").on("keypress",function (event) {
		$(this).val($(this).val().replace(/[^0-9\.]/g,''));
	    	if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
	    		event.preventDefault();
	        }
	});
	
	$(document).delegate("#deleteph", "click", function(){
		$(this).parent().remove();
		profile_page_001.updatePhonno();
//		$("#wait").css("display", "none");
	});
	
	$(document).delegate(".edit-email", "click", function(){
		$(this).hide();
		$(this).prev().find('li').hide();
		$(this).prev().find('span').show();
		
		var width = ($(this).prev().find('li').text().length * 8 ) - 8;
		$(this).prev().find('span').children().css('width',width);
//		alert(testwidth);
		$("input").focus();
	});
	
	$(document).delegate("#btnsaveemail", "click", function(){
		$(this).parent().hide();
		$(this).parent().prev().show();
		$('.Dd').find('.edit-email').show();
		
		$(this).parent().prev().text($(this).prev().val());
		
		if($(this).prev().val() != ''){
			profile_page_001.updateEmail(this);
			toastr["success"]("Your Email Update success.");
		}else{
			toastr["error"]("input email incorrectly.");
		}
		
	});
	
	$(document).delegate(".edit-BD", "click", function(){
		$(this).hide();
		$("#bDate, #bMonth, #bYear, #btnsavebd").show();
	});
	
	$(document).delegate("#btnsavebd", "click", function(){
		$(".edit-BD").show();
		$("#bDate, #bMonth, #bYear, #btnsavebd").hide();
		var newBD = '';
		
		var BDate  = $("#bDate").find("option:selected").text();
		var BMonth = $("#bMonth").find("option:selected").text();
		var BYear  = $("#bYear").find("option:selected").text();
		newBD = BDate+BMonth+BYear;
		$("#userBD").text(wehrm.string.formatDateNormal(newBD,"-"));
		
		profile_page_001.updateBirthdate(newBD);
	});
	
	$(document).delegate(".edit-gender", "click", function(){
		$(this).hide();
		$(".gender").find('li:eq(1)').show();
	});
	
	$(document).delegate("#btnsaveGD", "click", function(){
		$(".gender").find('li:eq(1)').hide();
		$('.edit-gender').show();
		var newGD = $("#selectGD").find("option:selected").text();
		var updateGD = $("#selectGD").find("option:selected").attr('data-gender');
		$("#gender").text(newGD);
		
		profile_page_001.updateGender(updateGD);
	});

});


profile_page_001.loadData = function(){
	_loadingStart();
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken  = $("meta[name='_csrf']").attr("content");
	var input = $("#cv_usercd").val();

	
    $.ajax({
		  type	: 'POST',
		  url	: '/userdetails/getuserdetails',
		  async	: false,
		  cache	: false,
		  data	: JSON.stringify(input),
		  dataType	  : 'json',
		  contentType : 'application/json',
		  beforeSend  : function(xhr) {
			  xhr.setRequestHeader(csrfHeader, csrfToken);
		  },
		  success : function(data){
			  var strPhone = '';
			  var strEmail = '';
			  var strDay   = '';
			  var strMonth = '';
			  var strYear  = '';
			  var strShowBD = '';
			  var gender   = '';
			  
			  $("#phone").html('');
			  $(".email").html('');
			  $(".birthday").html('');
			  
			  $.each(data.OUT_REC, function(i,v){
				  var phone = v.cphone;
				  var myregexp2 = new RegExp(","); 
				  dateArray = phone.split(myregexp2);
				  for(var i=0;i<dateArray.length;i++){
					  strPhone += '<span>'+wehrm.string.formatPhoneNumber(dateArray[i])+'<a href="javascript:" id="deleteph"></a></span>';
				  }
				  
				  strEmail += 	'<li id="email">'+v.email+'</li>';
				  strEmail +=  	'<span style="display:none;">';
				  strEmail +=		'<input type="text" value="'+v.email+'" onkeypress="chkKey(event, this)" onfocus="this.setSelectionRange(1000,1001)" />';
				  strEmail +=		'<span class="btn_style1_b" id="btnsaveemail"><a href="javascript:">save</a></span>';
				  strEmail +=	'</span>';
				  
				  strShowBD += '<li><label id="userBD">'+wehrm.string.formatDateNormal(v.birthdate)+'</label></li>';
				  
				  if(v.sex == 'M'){
					  $("#gender").text("Male");
				  }else if(v.sex == 'F'){
					  $("#gender").text("Female");
				  }else if(v.sex == 'A'){
					  $("#gender").text("All");
				  }
			  });
			  
			  strDay += '<li id="bDate"><span>D:  </span><select>';
				  for(var j=1;j<=31;j++){
					  if(j<=9){
						  strDay += '<option>0'+j+'</option>';
					  }else{
						  strDay += '<option>'+j+'</option>';
					  }
				  }
			  strDay += '</select></li>';
			  
			  strMonth += '<li id="bMonth"><span>M:  </span><select>';
				  for(var j=1;j<=12;j++){
					  if(j<=9){
						  strMonth += '<option>0'+j+'</option>';
					  }else{
						  strMonth += '<option>'+j+'</option>';
					  }
				  }
			  strMonth += '</select></li>';
				  
			  strYear += '<li id="bYear"><span>Y:  </span><select>';
				  for(var j=1990;j<=2018;j++){
					  strYear += '<option>'+j+'</option>';
				  }
			  strYear += '</select></li>';
			  

			  $("#phone").append(strPhone);
			  $(".email").append(strEmail);
			  $(".birthday").append(strShowBD);
			  $(".birthday").append(strDay);
			  $(".birthday").append(strMonth);
			  $(".birthday").append(strYear);
			  $(".birthday").append('<span class="btn_style1_b" id="btnsavebd"><a href="javascript:">save</a></span>');
			  _loadingStop();  
		  }
	  });
};


profile_page_001.updatePageName = function(){
	_loadingStart();
	var input = {};
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken  = $("meta[name='_csrf']").attr("content");
		
	input["usercd"] = $("#usercd").val();
	input["pcd"]    = $.urlParam("ref");
	input["pnm"]    = $(".user-name").text();
	
	console.log("pageName: "+JSON.stringify(input));
	$.ajax({
		type	: 'POST',
		url	    :'/userpage/updatepagename',
		async	: false,
		cache	: false,
		data	: JSON.stringify(input),
	    dataType	: 'json',
	    contentType : 'application/json',
		beforeSend  : function(xhr) {
			xhr.setRequestHeader(csrfHeader, csrfToken);
		},
		success : function(data){
			console.log(data.SMS);
			_loadingStop(); 
		}
	});
};

profile_page_001.updatePhonno = function(){
	_loadingStart();
	var input = {};
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken  = $("meta[name='_csrf']").attr("content");
	var getPhone = '';
	var strPhone = '';
	
	$("#phone span").each(function(i, v){
		getPhone  = ","+$(v).text().replace("-","").replace("-","");
		strPhone += getPhone;
	});
	strPhone = strPhone.substring(1,(strPhone.length));
	
	input["usercd"] = $("#cv_usercd").val();
	input["cphone"] = strPhone;
	
	$.ajax({
		type	: 'POST',
		url	    :'/userdetails/updateuserphonno',
		async	: false,
		cache	: false,
		data	: JSON.stringify(input),
	    dataType	  : 'json',
	    contentType : 'application/json',
		beforeSend  : function(xhr) {
			xhr.setRequestHeader(csrfHeader, csrfToken);
		},
		success : function(data){
			console.log(data.SMS);
			_loadingStop();
		}
	});
};



profile_page_001.updateEmail = function($this){
	_loadingStart();
	var input = {};
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken  = $("meta[name='_csrf']").attr("content");
	
	
	input["usercd"] = $("#cv_usercd").val();
	input["email"]  = $($this).prev().val();
	
	$.ajax({
		type	: 'POST',
		url	    :'/userdetails/updateuseremail',
		async	: true,
		cache	: false,
		data	: JSON.stringify(input),
	    dataType	  : 'json',
	    contentType : 'application/json',
		beforeSend  : function(xhr) {
			xhr.setRequestHeader(csrfHeader, csrfToken);
		},
		success : function(data){
			console.log(data.MESSAGE);
			_loadingStop();
		}
	});
};


profile_page_001.updateBirthdate = function(newdate){
	_loadingStart();
	var input = {};
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken  = $("meta[name='_csrf']").attr("content");
	
	
	input["usercd"]     = $("#cv_usercd").val();
	input["birthdate"]  = newdate;
	
	$.ajax({
		type	: 'POST',
		url	    :'/userdetails/updateuserbirthdate',
		async	: true,
		cache	: false,
		data	: JSON.stringify(input),
	    dataType	  : 'json',
	    contentType : 'application/json',
		beforeSend  : function(xhr) {
			xhr.setRequestHeader(csrfHeader, csrfToken);
		},
		success : function(data){
			console.log(data.MESSAGE);
			_loadingStop();
		}
	});
};


profile_page_001.updateGender = function(newgen){
	_loadingStart();
	var input = {};
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken  = $("meta[name='_csrf']").attr("content");
	
	
	input["usercd"] = $("#cv_usercd").val();
	input["sex"]    = newgen;
	
	$.ajax({
		type	: 'POST',
		url		:'/userdetails/updateusergender',
		async	: true,
		cache	: false,
		data	: JSON.stringify(input),
	    dataType	  : 'json',
	    contentType : 'application/json',
		beforeSend  : function(xhr) {
			xhr.setRequestHeader(csrfHeader, csrfToken);
		},
		success : function(data){
			console.log(data.MESSAGE);
			_loadingStop();
		}
	});
};

function _loadingStart(){
	$("#wait").css("display", "block");
}

function _loadingStop(){
	$("#wait").fadeOut(15);
}

$.urlParam = function(name){
    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
    return results[1] || 0;
}

profile_page_001.loadCoverImage = function(){
	_loadingStart();
	var input = {};
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken  = $("meta[name='_csrf']").attr("content");
	
//	input["usercd"] = $.urlParam("usercd");
	input["pcd"]    = $.urlParam("ref");

	$.ajax({
		type	: 'POST',
		url		: '/userpage/userpagedata',
		async	: false,
		cache	: true,
		data	: JSON.stringify(input),
	    dataType	: 'json',
	    contentType : 'application/json',
		beforeSend  : function(xhr) {
			xhr.setRequestHeader(csrfHeader, csrfToken);
		},
		success : function(data){

			if(data.OUT_REC.length > 0){
				$.each(data.OUT_REC, function(i,v){
					$("#cv_pcd").val(v.pcd);
					$("#cv_pnm").val(v.pnm);
					$("#cv_title").val(v.title);
					$("#cv_usercd").val(v.usercd);
					$("#cv_url").val(v.url);
					$("#cv_enabled").val(v.enabled);
					$("#cv_regdate").val(v.regdate);
					$(".usr_pf").css("background-image","url("+document.location.origin+"/upload_file/files/"+v.randname+")");
					$(".user-name").text(v.pnm);
				});
			}		
			_loadingStop();
		}
	});
};

profile_page_001.loadProfileImage = function(){
	_loadingStart();
	var input = {};
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken  = $("meta[name='_csrf']").attr("content");
	
	input["usercd"] = $("#cv_usercd").val();
	$.ajax({
		type	: 'POST',
		url		: '/userdetails/selectprofileimage',
		async	: false,
		cache	: true,
		data	: JSON.stringify(input),
	    dataType	: 'json',
	    contentType : 'application/json',
		beforeSend  : function(xhr) {
			xhr.setRequestHeader(csrfHeader, csrfToken);
		},
		success : function(data){
			var strProfile = '';
			$(".usr_pic").html('');
			
			$.each(data.OUT_REC, function(i,v){
				strProfile += '<img src="'+document.location.origin+"/upload_file/files/"+v.randname+'" class="img_Prf"/>';
				strProfile += '<span class="btn-change-pic"><img src="../../img/users/camera-ico.png"></span>';
				if($("#cv_usercd").val() == $("#usercd").val()){
					$("#header_profile").attr("src",document.location.origin+"/upload_file/files/"+v.randname);
				}
			});
			
			$(".usr_pic").append(strProfile);
			_loadingStop();
		}
	});
};

function uploadFormData(){
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken  = $("meta[name='_csrf']").attr("content");
  	var oMyForm = new FormData();
  	
   
  	oMyForm.append("file", file.files[0]);
  	$.ajax({
  		url			: '/upload_file/uploadimg',
	    data		: oMyForm,
	    async		: false,
	    cache   	: true,
	    type		: 'POST',
	    dataType	: 'text',
	    processData	: false,
	    contentType	: false,
	    beforeSend: function(xhr) {
	      xhr.setRequestHeader(csrfHeader, csrfToken);
	    },
	    success: function(data){
	            data = JSON.parse(data);
	            
//	            $("#user_pic").find("img:eq(0)").attr("src",document.location.origin+"/upload_file/files/"+ data.RANDNAME);
	            _profileRandname = data.RANDNAME;
	            $(".select_pic").append("<input type='hidden' id='type' value='"+ data.OUT_REC.type+"'>" );
	            $(".select_pic").append("<input type='hidden' id='size' value='"+ data.OUT_REC.size+"'>" );
	            $(".select_pic").append("<input type='hidden' id='orname' value='"+ data.OUT_REC.orname+"'>" ); 
		    	$(".select_pic").append("<input type='hidden' id='regdate' value='"+ data.OUT_REC.regdate+"'>" );
		    	$(".select_pic").append("<input type='hidden' id='randname' value='"+ data.OUT_REC.randname+"'>" );
	    }
  	});
};

function updateCoverImage(){
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken  = $("meta[name='_csrf']").attr("content");
	var input 	   = {};

	input["orname"]   = $("#orname").val();
    input["randname"] = $("#randname").val();
    input["regdate"]  = $("#regdate").val();
    input["size"]     = $("#size").val();
    input["type"]     = $("#type").val();
    input["path"] 	  = $(".usr_pf").css("background-image");
    input["kind"] 	  = '3';
	input["pcd"]   = $("#cv_pcd").val();
	input["old_randname"] = getSession();
	
	console.log(JSON.stringify(input));
  	$.ajax({
  		url			: '/upload_file/save_file_name',
  		dataType	: 'text',
    	type   		: 'POST',
    	cache  		: true,
        processData	: false,
        contentType	: false,
    	contentType : 'application/json',
        beforeSend	: function(xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        data		: JSON.stringify(input),
	    success: function(data){
//	    	alert(data.SMS);
	    }
  });
};



function updateProfileImage(){
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken  = $("meta[name='_csrf']").attr("content");
	var input 	   = {};

	input["orname"]   = $("#orname").val();
    input["randname"] = $("#randname").val();
    input["regdate"]  = $("#regdate").val();
    input["size"]     = $("#size").val();
    input["type"]     = $("#type").val();
    input["path"] 	  = $("#user_pic").find("img:eq(0)").attr("src");
    input["kind"] 	  = '2';
	input["usercd"]   = $("#usercd").val();
	input["old_randname"] = getSession();
	
  	$.ajax({
  		url			: '/upload_file/save_file_name',
  		dataType	: 'text',
    	type   		: 'POST',
    	cache  		: true,
        processData	: false,
        contentType	: false,
    	contentType : 'application/json',
        beforeSend	: function(xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        data		: JSON.stringify(input),
	    success: function(data){
//	    	alert(data.SMS);
	    }
  });
};

profile_page_001.loadPageProduct = function(){
	_loadingStart();
	var input = {};
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken  = $("meta[name='_csrf']").attr("content");
	
	input["pcd"] = $.urlParam("ref");
	console.log(JSON.stringify(input));
	$.ajax({
		type	: 'POST',
//		url		: '/userpage/getpageproducts',
		url    : "/products/list_product",
		async	: false,
		cache	: true,
		data	: JSON.stringify(input),
	    dataType	: 'json',
	    contentType : 'application/json',
		beforeSend  : function(xhr) {
			xhr.setRequestHeader(csrfHeader, csrfToken);
		},
		success : function(data){
			var strProducts = '';
			var cntRow = 0;
			$("#product-main-wrap").html("");
			
			$.each(data.OUT_REC, function(i,v){		
				cntRow++;
				if(cntRow == 1){
				strProducts +="<div id='product-row'>";
				}
				strProducts  +='<div class="pro-dis">'
							 +'        <div class="pro-num">'
							 +'				<div class="dropbtn"><img src="img/ico/three-dots.png" width="20" height="20" alt="More premium icon" title="More premium icon"></div>'
							 +'			  	<div id="myDropdown" class="dropdown-content">'
				if(v.enabled){strProducts +='<a href="javascript:" id="status" class="onOrOff"><img src="../img/ico/icon_alim_on.png" alt=""></a>'}
						 else{strProducts +='<a href="javascript:" id="status" class="onOrOff"><img src="../img/ico/icon_alim_off.png" alt=""></a>'}
				strProducts +='			  		<a href="javascript:" id="btn_edit">Discount</a>'
							+'			    	<a href="javascript:" id="btn_delete">Remove</a>'
							+'				</div>'
							+'		  </div>'
							+'        <div id="wrap-value">'
							+'       	<div id="pro-thumb-wrap">'
							+'           	<a href="javascript:" class="pro-img"><img id="wrap" width="100%" height="225px"  src="'+document.location.origin+'/upload_file/files/'+ v.randname+'" /></a>'
							+'       	</div><!--end of product thumbnail-->'
							+'       	<div id="pro-desc-wrap">'
							+'           	<a href="javascript:" class="pro-title">'+v.title+'</a>'
							+'           	<h1 id="pro-price">$ '+v.price+'</h1>'
							+'           	<input type=hidden id="prcd" value='+v.prcd+' />'
							+'           	<input type=hidden id="parentId" value='+v.parentid+' />'
							+'       	</div><!--end of product description-->'
							+'       </div>'
							+'       <div style="clear:both"></div>'
							+'       <div id="pro-action-wrap">'
							+'           <div id="pro-act-left">'
							+'               <ul>'
							+'                   <li><a href="javascript:" class="pro-location"></a></li>'
							+'                   <li><a href="javascript:" class="pro-date"></a></li>'
							+'                   <li><a href="javascript:" class="pro-share"></a></li>'
							+'               </ul>'
							+'           </div><!--end of product action left-->'
							+'           <div id="pro-act-right">'
							+'               <ul>'
							+'                   <li><span class="pro-view-c">'+v.viewcnt+'</span><i class="pro-view-c-ico"></i></li>'
							+'                   <li><span class="pro-like-c">'+v.likecnt+'</span><i class="pro-like-c-ico"></i></li>'
							+'               </ul>'
							+'           </div><!--end of product action right-->'
							+'       </div><!--end of product action wrap-->'
							+'   </div>';
		  		console.log(v.enabled);

				if(cntRow == 4 || (data.OUT_REC.length-1) == i){
					cntRow = 0;				
					strProducts +="</div>";
					$("#product-main-wrap").append(strProducts);
					strProducts="";
				}
			});
			$("#usr_pf_posts").text(data.CNT+" POSTS");
			
			_loadingStop();
		}
	});
};

profile_page_001.loadPageSaveProduct = function(){
	_loadingStart();
	var input = {};
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken  = $("meta[name='_csrf']").attr("content");
	
	input["usercd"] = $("#cv_usercd").val();
	console.log(JSON.stringify(input));
	$.ajax({
		type	: 'POST',
//		url		: '/userpage/getpageproducts',
		url    : "/products/list_product",
		async	: false,
		cache	: true,
		data	: JSON.stringify(input),
	    dataType	: 'json',
	    contentType : 'application/json',
		beforeSend  : function(xhr) {
			xhr.setRequestHeader(csrfHeader, csrfToken);
		},
		success : function(data){
			var strProducts = '';
			var cntRow = 0;
			$("#product-save-wrap").html("");
			
			$.each(data.OUT_REC, function(i,v){
				cntRow++;
				if(cntRow == 1){
					strProducts +="<div id='product-row'>";
				}
				
				strProducts +='  <div class="pro-dis">'
							+'        <div class="pro-num">'
							+'				<div class="dropbtn"><img src="img/ico/three-dots.png" width="20" height="20" alt="More premium icon" title="More premium icon"></div>'
							+'			  	<div id="myDropdown" class="dropdown-content">'
							+'			  		<a href="javascript:" id="btn_edit">Edit</a>'
							+'			    	<a href="javascript:" id="btn_delete">Delete</a>'
							+'				</div>'
							+'		  </div>'
							+'        <div id="wrap-value">'
							+'       	<div id="pro-thumb-wrap">'
							+'           	<a href="javascript:" class="pro-img"><img id="wrap" width="100%" height="225px"  src="'+document.location.origin+'/upload_file/files/'+ v.randname+'" /></a>'
							+'       	</div><!--end of product thumbnail-->'
							+'       	<div id="pro-desc-wrap">'
							+'           	<a href="javascript:" class="pro-title">'+v.title+'</a>'
							+'           	<h1 id="pro-price">$ '+v.price+'</h1>'
							+'           	<input type=hidden id="prcd" value='+v.prcd+' />'
							+'           	<input type=hidden id="parentId" value='+v.parentid+' />'
							+'       	</div><!--end of product description-->'
							+'       </div>'
							+'       <div style="clear:both"></div>'
							+'       <div id="pro-action-wrap">'
							+'           <div id="pro-act-left">'
							+'               <ul>'
							+'                   <li><a href="javascript:" class="pro-location"></a></li>'
							+'                   <li><a href="javascript:" class="pro-date"></a></li>'
							+'                   <li><a href="javascript:" class="pro-share"></a></li>'
							+'               </ul>'
							+'           </div><!--end of product action left-->'
							+'           <div id="pro-act-right">'
							+'               <ul>'
							+'                   <li><span class="pro-view-c">'+v.viewcnt+'</span><i class="pro-view-c-ico"></i></li>'
							+'                   <li><span class="pro-like-c">'+v.likecnt+'</span><i class="pro-like-c-ico"></i></li>'
							+'               </ul>'
							+'           </div><!--end of product action right-->'
							+'       </div><!--end of product action wrap-->'
							+'   </div>';
				
				if(cntRow == 4 || (data.OUT_REC.length-1) == i){
					cntRow = 0;
					strProducts +="</div>";
					$("#product-save-wrap").append(strProducts); 
					strProducts="";
				}
			});
			$("#usr_pf_saves").text(data.CNT+" SAVES");
			_loadingStop();
		}
	});
};


profile_page_001.loadUpdateStatusProduct = function($status, $prcd){
	_loadingStart();
	var input = {};
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken  = $("meta[name='_csrf']").attr("content");
	
	input["enabled"] = $status;
	input["prcd"]    = $prcd;
	
	$.ajax({
		type	: 'POST',
		url    : "/products/update_status",
		async	: false,
		cache	: true,
		data	: JSON.stringify(input),
	    dataType	: 'json',
	    contentType : 'application/json',
		beforeSend  : function(xhr) {
			xhr.setRequestHeader(csrfHeader, csrfToken);
		},
		success : function(data){
//			alert(data.SMS);
			_loadingStop();
		}
	});
};



function getSession(){
	 var sessionObj = "";
	 $.ajax({
		 type   : 'GET',
	     url    : "/get_sesssion",
	     cache  : true,
	     async : false
	 })
	 .done(function(dat){
	  if(dat.SESSION_IS!=null){
	   sessionObj = dat.SESSION_IS.randname;
	   console.log("in getsession: "+sessionObj);
	  }
	 })
	 return sessionObj;
};


function getSessionUsercd(){
	 var sessionObj = "";
	 $.ajax({
		 type   : 'GET',
	     url    : "/get_sesssion",
	     cache  : true,
	     async : false
	 })
	 .done(function(dat){
	  if(dat.SESSION_IS!=null){
	   sessionObj = dat.SESSION_IS.usercd;
	   console.log("in getsession: "+sessionObj);
	  }
	 })
	 return sessionObj;
};

	
function createMap(){
	/*
	//google map
	var mapOptions = {zoom: 9,
					  center: new google.maps.LatLng(11.9098, 104.3010),
					  mapTypeId: google.maps.MapTypeId.ROADMAP
    				};
	
    var map = new google.maps.Map(document.getElementById('map_canvas'), mapOptions);
    */
    var mapProp = {
    	    center:new google.maps.LatLng(11.527012,104.900513),
    	    zoom:9,
    	};
    var map = new google.maps.Map(document.getElementById("map_canvas"),mapProp);
    
    google.maps.event.addListener(map, "idle", function(){
    	  google.maps.event.trigger(document.getElementById("map_canvas"), 'resize');
    	  google.maps.event.trigger(document.getElementById("map_canvas"), 'dblclick');
    });
    
}

//call back
function callbackFn(data){
	if(data.IS_TRUE){
		location.reload();
	}	
}
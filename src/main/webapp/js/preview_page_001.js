


var preview_page_001 = {};
var edit_btn;
var like = 1;
var Session_usercd = "";
var ListPro_usercd = "";
$(document).ready(function() {
	preview_page_001.getSesion();
	preview_page_001.insertViewProduct();
	preview_page_001.loadCategory();
	preview_page_001.loadProductAddress();
	preview_page_001.loadProductPictures();
	preview_page_001.loadProductDetail();
	preview_page_001.loadProductComments();
	preview_page_001.loadRelatedProduct();
	preview_page_001.getSesion();
	preview_page_001.checkLikeProduct();
	preview_page_001.loadProfileImage();
	
	$('#image_gallery').lightSlider({
		gallery:true,
		item:1,
		thumbItem:7,
		slideMargin: 0,
		speed:500,
		auto:false,
		loop:true,
		onSliderLoad: function() {
			$('#image_gallery').removeClass('cS-hidden');
		}  
	});

	$(document).delegate(".sidebar_signup", "click", function(){
		wehrm.popup.openPopup("login", {}, function(data){
			callbackFn(data);
		});
	});

	$(document).delegate(".sidebar_login", "click", function(){
		var status  = 'login';
		wehrm.popup.openPopup("login", {status}, function(data){
			callbackFn(data);
		});
	});
	
	$(document).delegate("#btn_upload", "click", function(){
		alert();
//		var link = '/post';
//		var status  = 'login';
//		if(getSesion() == ''){
//			wehrm.popup.openPopup("login", {link, status}, function(data){
//				callbackFn(data);
//			});
//		}else{
//
//			window.location.href = document.location.origin+'/post?usercd='+getSesion();
//		}
	});
	
	$('.comment_opt').generPlugIn({
		btnCombo	:	'.btn_comment_opt',
		layerCom	:	'.layer_comm_opt'
	});

	$('.related_prod').readMoreDetPage();
	
	$(document).delegate(".ico_wishlist > span", "click", function(){
		var status = 'login';
		if($("#usercd").val() == ''){
			wehrm.popup.openPopup("login",{url:"/preview?prcd=e09752f9-4849-4513-b4cf-73d4dc60000f20171223122818&parentid=9",status}, function(data){
				callbackFn(data);
			});
		}else{
			if($(".ico_wishlist").hasClass('heart')){
				$(".ico_wishlist").removeClass('heart');
				$(this).css("background-image","url(../../img/comm/like.png)");
				var cntLike = parseInt($(this).text());
				$(this).text((cntLike - 1) == -1 ? 0 : cntLike - 1);
				preview_page_001.deleteLikeProduct();
			}else{
				var cntLike = parseInt($(this).text());
				$(this).text(cntLike + 1);
				$(".ico_wishlist").addClass('heart');
				$(this).css("background-image","url(../../img/comm/red_hearts.png)");
				preview_page_001.likeProduct();
			}
		}
		
	});
	
	$(document).delegate("#sellerName, #sellerphoto", "click", function(){
//		var usercd = $("#usercd").val();
//		if($("#usercd").val() == ''){
//			wehrm.popup.openPopup("login",{}, function(data){
//				callbackFn(data);
//			});
//		}else{
//			window.location.href = document.location.origin+'/profile_page_001?usercd='+usercd;
//		}
		window.location.href = document.location.origin+'/profile?ref='+$("#pcd").val();
	});
	
	$(document).delegate("#post", "click", function(){
		var status  = 'login';
		if($("#usercd").val() == ''){
			wehrm.popup.openPopup("login",{status}, function(data){
				callbackFn(data);
			});
			location.reload();
		}else{
			if($(".field_comment").find("#comment_contents").val() != ''){
				preview_page_001.insertProductCommentAdd();
				$(".field_comment").find("#comment_contents").val("");				
			}
		}
	});
	

	$(document).delegate("#btn_edit", "click", function(){
		var status  = 'login';
		if($("#usercd").val() == ''){
			wehrm.popup.openPopup("login", {status}, function(data){
				callbackFn(data);
			});
			document.location.reload(true);
		}else{
			$(".hideComment").hide();
			$(".showComment").show();
			$(this).parents(".showComment").hide();
			$(this).parents("li").next().show();
			$(".hideComment").children().find("#commentUpdate").focus();
		}
	});

	$(document).delegate("#btn_delete", "click", function(){
		var cnfmDel = confirm('are you sure ?');
		if(cnfmDel == true){
			preview_page_001.deleteProductComments(this);
		}
	});

	$(document).delegate("#btn_save", "click", function(){
//		var textUpdate = $(this).parents(".hideComment").children().find("#commentUpdate").val();
//		var commID = $(this).parents(".hideComment").children().find("input").val();
		preview_page_001.updateProductComments(this);
	});
	
	$(document).delegate("#btn_cancel", "click", function(){
		preview_page_001.loadProductComments();
	});

	
	$(document).delegate(".dropbtn", "click", function(){
	    $(this).next("#myDropdown").slideToggle();
    });
	
	$(document).delegate(".dropdown-content", "mouseleave", function(){
	    $(this).slideUp(800);
    });
	
	$(document).delegate("#relPro", "click", function(){
		var prcd 	 = $(this).find("#prcd").val();
		var parentid = $(this).find("#parentid").val();
		window.open(document.location.origin+'/preview?ref1='+prcd+"&ref2="+parentid);
    });

});


preview_page_001.loadCategory = function(){
	$.ajax({
		type   : 'GET',
	    url    : "/category/list_category",
	    cache  : true
	})
	.done(function(dat){
    	var html = "";
    	
    	$.each(dat.OUT_REC, function(i,v){
    		var j = i + 1;
    		if(v.lvl == "1"){
    			html += '<a href="#none" class="ctg_0'+j+'" catg-id="'+v.catgid+'" style="background-image:url('+document.location.origin+"/upload_file/files/"+v.randname+');background-size:20px;background-position:10px;">'+v.nm_eng+'</a>';
    		}
    	});
    	$("#sidebar_catagory_list").html(html);
	})
};

preview_page_001.loadProfileImage = function(){
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken  = $("meta[name='_csrf']").attr("content");
	var input = {};
	
	input["usercd"] = ListPro_usercd;

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
			var strPrf = '';
			$("#sellerphoto").html("");
			console.log("image: "+JSON.stringify(data.OUT_REC));
			$.each(data.OUT_REC, function(i, v){
				strPrf += '<img src="'+document.location.origin+"/upload_file/files/"+v.randname+'" alt="" class="loaded">';
			});

			$("#sellerphoto").append(strPrf);
		  }
	  });
};


preview_page_001.insertViewProduct = function(){
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken  = $("meta[name='_csrf']").attr("content");
	var input = {};
	
	input["prcd"]    = $.urlParam("ref1");
	input["usercd"]  = $("#usercd").val();

    $.ajax({
		  type	: 'POST',
		  url	:'/products/insert_view',
		  async	: true,
		  cache	: false,
		  data	: JSON.stringify(input),
          dataType	  : 'json',
	      contentType : 'application/json',
		  beforeSend  : function(xhr) {
			  xhr.setRequestHeader(csrfHeader, csrfToken);
		  },
		  success : function(data){

		  }
	  });
};


preview_page_001.likeProduct = function(){
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken  = $("meta[name='_csrf']").attr("content");
	var input = {};
	
	input["prcd"]    = $.urlParam("ref1");
	input["usercd"]  = $("#usercd").val();

    $.ajax({
		  type	: 'POST',
		  url	:'/likes/insert_like',
		  async	: true,
		  cache	: false,
		  data	: JSON.stringify(input),
          dataType	  : 'json',
	      contentType : 'application/json',
		  beforeSend  : function(xhr) {
			  xhr.setRequestHeader(csrfHeader, csrfToken);
		  },
		  success : function(data){
			  console.log(data.SMS);
		  }
	  });
};

preview_page_001.deleteLikeProduct = function(){
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken  = $("meta[name='_csrf']").attr("content");
	var input = {};
	
	input["prcd"]    = $.urlParam("ref1");
	input["usercd"]  = $("#usercd").val();

    $.ajax({
		  type	: 'POST',
		  url	:'/likes/delete_like',
		  async	: true,
		  cache	: false,
		  data	: JSON.stringify(input),
          dataType	  : 'json',
	      contentType : 'application/json',
		  beforeSend  : function(xhr) {
			  xhr.setRequestHeader(csrfHeader, csrfToken);
		  },
		  success : function(data){
			  console.log(data.SMS);
		  }
	  });
};


preview_page_001.checkLikeProduct = function(){
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken  = $("meta[name='_csrf']").attr("content");
	var input = {};
	input["prcd"]    = $.urlParam("ref1");
    $.ajax({
		  type	: 'POST',
		  url	:'/likes/check_like',
		  async	: true,
		  cache	: false,
		  data	: JSON.stringify(input),
          dataType	  : 'json',
	      contentType : 'application/json',
		  beforeSend  : function(xhr) {
			  xhr.setRequestHeader(csrfHeader, csrfToken);
		  },
		  success : function(data){
			  $.each(data.OUT_REC, function(i, v){
				  if(Session_usercd  == v.usercd){
					  $(".ico_wishlist").find("span").css("background-image","url(../../img/comm/red_hearts.png)");
					  $(".ico_wishlist").addClass("heart");
				  }

			  });
		  }
	  });
};


preview_page_001.loadProductAddress = function(){
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken  = $("meta[name='_csrf']").attr("content");
	var input  =  $.urlParam("ref1");
	
    $.ajax({
		  type	: 'POST',
		  url	:'/products/product_picture_address',
		  async	: true,
		  cache	: false,
		  data	: input,
          dataType	  : 'json',
	      contentType : 'application/json',
		  beforeSend  : function(xhr) {
			  xhr.setRequestHeader(csrfHeader, csrfToken);
		  },
		  success : function(data){
		  var html = '';
		  $(".seller_info").empty();

			  $.each(data.OUT_REC, function(i,v){
				  html += '<p class="sell_user">: '+v.fullname+'</p>';
				  html += '<p class="sell_tel">: '+wehrm.string.formatPhoneNumber(v.cphone, "")+'</p>';
				  html += '<p class="sell_localtion">: '+v.province+'</p>';
				  html += '<p class="sell_map">: '+v.detail+'</p>';
				  html += '<input type="hidden" id="pcd" value="'+v.pcd+'" />';
				  $("#sellerName").find("p").text(v.pnm);
			  });
			  $(".seller_info").append(html);
		  }
	  });
};


preview_page_001.loadProductPictures = function(){
//	_loadingWholeStart();
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken  = $("meta[name='_csrf']").attr("content");
	var input  =  $.urlParam("ref1");

	
    $.ajax({
		  type	: 'POST',
		  url	: '/products/product_picture_address',
		  async	: false,
		  cache	: false,
		  data	: input,
          dataType	  : 'json',
	      contentType : 'application/json',
		  beforeSend  : function(xhr) {
			  xhr.setRequestHeader(csrfHeader, csrfToken);
		  },
		  success: function(data){
			  var html = '';
			  $("#image_gallery").html("");

			  $.each(data.PICTURE_REC, function(i,v){
				  html += '<li data-thumb="'+document.location.origin+"/upload_file/files/"+v.randname+'"><img src="'+document.location.origin+"/upload_file/files/"+v.randname+'" alt="productPicture"></li>';
			  });

			  $("#image_gallery").append(html);
//			  _loadingWholeStop();
		  }
	  });
//	  preview_page_001.loadSliderLibrary();
};


preview_page_001.loadProductDetail = function(){
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken  = $("meta[name='_csrf']").attr("content");
	var input = {};
		
	input["prcd"]  =  $.urlParam("ref1");
	
    $.ajax({
		  type	: 'POST',
		  url	: '/products/list_product',
		  async	: false,
		  cache	: false,
		  data	: JSON.stringify(input),
          dataType	  : 'json',
	      contentType : 'application/json',
		  beforeSend  : function(xhr) {
			  xhr.setRequestHeader(csrfHeader, csrfToken);
		  },
		  success: function(data){
			  var html = '';
			  var desc = '';
			  $(".prod_price").empty();
			  
			  $.each(data.OUT_REC, function(i, v){

				  html += '<ul>';
				  html += 	'<li><p class="prod_name">'+v.title+'</p></li>';
				  html +=	'<li><p class="prod_price">$ '+v.price+'</p></li>';
				  html += '</ul>';
				  html += '<div class="other_info">';
				  html +=	'<ul class="items_foot_opts">';
				  html += 		'<li class="ico_wishlist"><span>'+v.likecnt+'</span></li>';
				  html +=		'<li class="ico_view"><span>'+v.viewcnt+'</span></li>';
				  html +=		'<li class="ico_report"><a href="javascrip:">Report</a></li>';
				  html +=		'<li class="ico_fbshare"><a href="javascrip:"></a></li>';
				  html +=		'<li class="ico_time"><span>'+wehrm.string.formatDateTime(v["regdate"],"-")+'min</span></li>';
				  html +=	'</ul>';
				  html += '</div>';
				   
				  ListPro_usercd = v.usercd;
				  desc += '<p>'+v.description+'</p>';
				  console.log("test usercd: "+ListPro_usercd);
			  });
			$(".prod_price").append(html);
			$(".tab01").append(desc);
			preview_page_001.checkLikeProduct();
		  }
	  });
};



preview_page_001.insertProductCommentAdd = function(){
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken  = $("meta[name='_csrf']").attr("content");
	var input = {};
	
	input["content"] = $(".field_comment").find("#comment_contents").val();
	input["prcd"]    = $.urlParam("ref1");
	input["usercd"]  = $("#usercd").val();

    $.ajax({
		  type	: 'POST',
		  url	:'/comments/comments_add',
		  async	: true,
		  cache	: false,
		  data	: JSON.stringify(input),
          dataType	  : 'json',
	      contentType : 'application/json',
		  beforeSend  : function(xhr) {
			  xhr.setRequestHeader(csrfHeader, csrfToken);
		  },
		  success : function(data){
			  preview_page_001.loadProductComments();
		  }
	  });
};


preview_page_001.loadProductComments = function(){
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken  = $("meta[name='_csrf']").attr("content");
	var input = {};

	input["prcd"]    = $.urlParam("ref1");

    $.ajax({
		  type	: 'POST',
		  url	: '/comments/comments_list',
		  async	: true,
		  cache	: false,
		  data	: JSON.stringify(input),
          dataType	  : 'json',
	      contentType : 'application/json',
		  beforeSend  : function(xhr) {
			  xhr.setRequestHeader(csrfHeader, csrfToken);
		  },
		  success : function(dat){
			  
		    	$(".pagination").pagination({
		    	    dataSource: dat.OUT_REC,
//		    	    totalNumber: 5,
		    	    pageSize: 5,
		    	    ajax: {
		    	        beforeSend: function() {
		    	        	$(".pagination").prev().prev().find("ul").html('Loading data from DB ...');
		    	        }
		    	    },
		    	    
		    	    callback: function(data, pagination) {
		  			  var html = '';
					  $(".pagination").prev().prev().find("ul").empty();
					  
					  $.each(data, function(i, v){
						  
						  html +='<li class="showComment">';
						  html += 	'<div class="comment">';
						 if(v.randname == null){
						  html += 		'<div class="customer"><img src="img/users/customer001.png" alt=""></div>';  
						 }else{
						  html +=		'<div class="customer"><img src="'+document.location.origin+"/upload_file/files/"+v.randname+'" alt=""></div>';
						 }
						  html +=		'<div>';
						  html +=			'<h5>'+v.fullname+'</h5>';
						  html +=			'<p>'+v.content+'</p>';
						  html +=		'</div>';
						  if(v.usercd == $("#usercd").val()){
						  html +=   	'<div class="dropdown">';
						  html +=			'<div class="dropbtn"><img src="img/ico/three-dots.png" width="20" height="20" alt="More premium icon" title="More premium icon"></div>';
						  html +=			'<div id="myDropdown" class="dropdown-content">';
						  html +=				'<a href="#home" id="btn_edit">Edit</a>';
						  html +=				'<a href="#about" id="btn_delete">Delete</a>';
						  html +=			'</div>';
						  html +=		'</div>';
						  }
						  html +=	'</div>';
						  
						  html +=	'<div class="comment_opt">';
						  html +=		'<ul class="layer_comm_opt">';
//						  html +=			'<li class="comm_delete"><a href="javascrip:" id="btn_delete">Delete</a></li>';
//						  html +=			'<li class="comm_edit"><a href="javascrip:" id="edit">Edit</a></li>';
						  html +=			'<li class="comm_date"><span>'+wehrm.string.periodTime(v["regdate"])+'</span></li>';
						  html +=		'</ul>';
						  html +=	'</div>';
						  html +='</li>';
					  
						  html +='<li class="hideComment" style="display:none;">'
								+'	<div class="comment">'
								+'		<div class="customer"><img src="img/users/customer001.png" alt=""></div>'
								+'		<div>'
								+'			<input type="hidden" id="comm_id" value="'+v.id+'" />'
								+'			<textarea name="" placeholder="Your comment" id="commentUpdate" onfocus="this.setSelectionRange(1000,1001);">'+v.content+'</textarea>'
								+'			<div class="btn_wrap mgt5 tar" style="">'
								+'				<a href="javascrip:" class="btn_b1" id="btn_save">Save</a>'
								+'				<a href="javascrip:" class="btn_wh1" id="btn_cancel">Cancel</a>'
								+'			</div>'
								+'		</div>'
								+'	</div>'
								+'</li>';
					  });
					  $(".pagination").prev().prev().find("ul").append(html);		    	    	
		    	    }
		    	})		    	
		  }
	  });
};


preview_page_001.updateProductComments = function(data){
	
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken  = $("meta[name='_csrf']").attr("content");
	var input = {};
	
	input["content"] = $(data).parents(".hideComment").children().find("#commentUpdate").val();
	input["id"]		 = $(data).parents(".hideComment").children().find("input").val();
	input["usercd"]	 = $("#usercd").val();
	input["prcd"]    = $.urlParam("ref1");
	
    $.ajax({
		  type	: 'POST',
		  url	: '/comments/comments_update',
		  async	: true,
		  cache	: false,
		  data	: JSON.stringify(input),
          dataType	  : 'json',
	      contentType : 'application/json',
		  beforeSend  : function(xhr) {
			  xhr.setRequestHeader(csrfHeader, csrfToken);
		  },
		  
		  success : function(data){
			  preview_page_001.loadProductComments();
		  }
	  });

};

preview_page_001.deleteProductComments = function(data){
	
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken  = $("meta[name='_csrf']").attr("content");
	var input = {};
	
	input["id"] = $(data).parents(".showComment").next().find("input").val();

    $.ajax({
		  type	: 'POST',
		  url	: '/comments/comments_delete',
		  async	: true,
		  cache	: false,
		  data	: JSON.stringify(input),
          dataType	  : 'json',
	      contentType : 'application/json',
		  beforeSend  : function(xhr) {
			  xhr.setRequestHeader(csrfHeader, csrfToken);
		  },
		  
		  success : function(data){
			  preview_page_001.loadProductComments();
		  }
	  });

};


preview_page_001.loadRelatedProduct = function(){
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken  = $("meta[name='_csrf']").attr("content");
	var input = {};
	
	input["prcd"]  	   =  $.urlParam("ref1");
	input["parentid"]  =  $.urlParam("ref2");


    $.ajax({
		  type	: 'POST',
		  url	: '/products/related_product',
		  async	: false,
		  cache	: false,
		  data	: JSON.stringify(input),
          dataType	  : 'json',
	      contentType : 'application/json',
		  beforeSend  : function(xhr) {
			  xhr.setRequestHeader(csrfHeader, csrfToken);
		  },
		  
		  success: function(data){
			  $(".carousel-inner").html("");
  			  var item3  = '';
  			  var checkI = 0;
  			 if(data.OUT_REC.length > 0 || data.OUT_REC == null){
  	  			 $.each(data.OUT_REC, function(i, v){
  	  				  checkI++;

  					  if(checkI == 1){
  						  item3 += '<div class="item">';
  						  item3 += '<div class="row">';
  					  }

  					  item3 += '<div class="col-xs-6 col-sm-4" id="relPro"> '
  				            + '    <div class="tcb-product-item"> '
  				            + '        <input type="hidden" id="prcd" value="'+v.prcd+'" /> '
  				            + '        <input type="hidden" id="parentid" value="'+v.parentid+'" /> '
  				            + '        <div class="tcb-product-photo"> '
  				            + '            <a href="#"><img src="'+document.location.origin+"/upload_file/files/"+v.randname+'" class="img-responsive" alt="a" /></a> '
  				            + '        </div> '
  				            + '        <div class="tcb-product-info"> '
  				            + '            <div class="tcb-product-title"> '
  				            + '                <h4><a href="#">'+v.title+'</a></h4></div> '
  				            + '            <div class="tcb-product-rating">	'
  				            + '                <i class="active glyphicon glyphicon-star"></i><i class="active glyphicon glyphicon-star"></i>	'
  				            + '                <i class="active glyphicon glyphicon-star"></i><i class="active glyphicon glyphicon-star"></i>	'
  				            + '                <i class="glyphicon glyphicon-star"></i>	'
  				            + '                <a href="#">(4,585 ratings)</a> '
  				            + '            </div> '
  				            + '            <div class="tcb-hline"></div> '
  				            + '            <div class="tcb-product-price"> '
  				            + '                '+v.price+' (17% off) '
  				            + '            </div> '
  				            + '        </div> '
  				            + '    </div> '
  				            + ' </div> ';
  					  
  					  if(checkI == 3 || (data.OUT_REC.length-1) == i){
  						  checkI = 0;
  						  
  						  item3 += '</div>';
  						  item3 += '</div>';
  						  $(".carousel-inner").append(item3);
  						  
  						  item3 = "";
  					  }
  	  			 });
  	  			 $(".carousel-inner .item:eq(0)").addClass("active");
  			 }

	      }
    });
};
$.urlParam = function(name){
    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
    return results[1] || 0;
}
//call back
function callbackFn(data){
	if(data.IS_TRUE){
		location.reload();
	}	
}
function updateComment(data){
	
	var html ='  <li>'
		+'	<div class="comment">'
		+'		<div class="customer"><img src="img/users/customer001.png" alt=""></div>'
		+'		<div>'
		+'			<textarea name="" placeholder="Your comment" id="commentUpdate" onfocus="this.setSelectionRange(1000,1001);">'+data+'</textarea>'
		+'			<div class="btn_wrap mgt5 tar" style="">'
		+'				<a href="javascrip:" class="btn_b1">Save</a>'
		+'				<a href="javascrip:" class="btn_wh1" id="btn_cancel">Cancel</a>'
		+'			</div>'
		+'		</div>'
		+'	</div>'
		+'</li>';
 return html;
}
preview_page_001.getSesion = function(){
	$.ajax({
		type   : 'GET',
	    url    : "/get_sesssion",
	    cache  : true,
	    async : false
	})
	.done(function(dat){
		if(dat.SESSION_IS!=null){
			Session_usercd = dat.SESSION_IS.usercd;
		}
	})
};



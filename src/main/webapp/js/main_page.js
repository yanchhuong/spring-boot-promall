
var main_page = {};
var parentId = '';
var show;
var countClick = 0;
var clickChat = 0;
var appendChat = '';

$(document).ready(function(){	
	$(".sub_cat").hide();
	main_page.listProduct();
	main_page.loadCategory();
	if(getSesion() != ''){
		main_page.loadProfileImage();
	}	
	
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
		var link = '/post';
		var status  = 'login';
		if(getSesion() == ''){
			wehrm.popup.openPopup("login", {link, status}, function(data){
				callbackFn(data);
			});
		}else{
			window.location.href = document.location.origin+'/post?usercd='+getSesion();
		}
	});
    
	$(document).delegate("#sidebar_catagory_list a", "click", function(){
		parentId   = $(this).attr("catg-id");
		$(".hdbox h3").text($(this).text());
		
		main_page.listProduct(parentId);
		main_page.categoryChild(parentId);
	});
	
//	$(document).delegate(".goodslist li", "click", function(){
	$(".goodslist li").live("click", function(){
		var prcd = $(this).find("#prcd").val();
		var parentid = $(this).find("#parentid").val();
		window.location.href = document.location.origin+'/preview?prcd='+prcd+"&parentid="+parentid;
//		window.location.href = document.location.origin+'/preview?prcd='+prcd+"&parentid="+parentid;
	});
	
});

main_page.loadCategory = function(){
	_loadingWholeStart();
	$.ajax({
		type   : 'GET',
	    url    : "/category/list",
	    cache  : true,
	    async  : false,
	    success: (function(dat){
		    _loadingWholeStop();
	    	var html = "";
	    	
	    	$.each(dat.OUT_REC, function(i,v){
	    		var j = i + 1;
	    		if(v.lvl == "1"){
	    			html += '<a href="javascript:" class="ctg_0'+j+'" catg-id="'+v.catgid+'" style="background-image:url('+document.location.origin+"/upload_file/files/"+v.randname+');background-size:20px;background-position:10px;">'+v.nm_eng+'</a>';
	    		}
	    	});
	    	$("#sidebar_catagory_list").html(html);	    	
	    })
	});
};


main_page.categoryChild = function(parentId){
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken = $("meta[name='_csrf']").attr("content");

	var input = {};
	input["catgid"] = parentId;

	$.ajax({
    	type   : 'POST',
	    url    : "/products/list_product",
	    data   : JSON.stringify(input),
	    cache: true,
        dataType: 'json',
    	contentType: 'application/json',
        async: false,
        beforeSend: function(xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
	})
	.done(function(dat){
    	var html = '';
    	var ul   = $('<ul></ul>');
    	$(".ctglist").empty();
    	if(dat.SUB_OUTREC.length> 0){
    		$.each(dat.SUB_OUTREC, function(i,v){
    			if(dat.SUB_OUTREC.length < 2 ){
    				$(".sub_cat").hide();
    			}else{
    				$(".sub_cat").show()
    				html += '<li><a href="javascript:"><em>'+v.nm_eng+'</em><span>'+v.cnt+' Items</span></a></li>';
    			}
    		});
    	}else{
    		$(".sub_cat").hide();
    	}
    	
    	$(".ctglist").empty();
    	$(ul).append(html);
    	$(".ctglist").append(ul);
    	$(".ctglist").append('<button class="btn_more"><span class="btn_text">Show More</span><span class="btn_collape"></span></button>');
    	main_page.showMoreCatg();
	})
};


main_page.showMoreCatg = function(){
	var minHeight =	$(".ctglist").children('ul').height();
	var maxHeight;
	
	if(minHeight < 164){
		$(".ctglist").find("button").hide();
	}else{
		$(".ctglist").find("button").show();
		minHeight = 164;//static
		//maxHeight = 250;//dynamic
		maxHeight = $(".ctglist").children('ul').height();
	}
	$(".ctglist").children('ul').css({'height':minHeight,'overflow':'hidden'});
	$('body').delegate('.btn_more','click',function(){
		$(this).prev('ul').animate({height:maxHeight+"px"},300);
		$(this).addClass('read_few');
		$(this).find('.btn_text').text("Show Less");
	});
	$('body').delegate('.read_few','click',function(){
		$(this).prev('ul').animate({height:minHeight},300).stop();
		$(this).removeClass('read_few');
		$(this).find('.btn_text').text("Show More");
	});
};


main_page.listProduct = function(parentId){
	_loadingWholeStart();
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken  = $("meta[name='_csrf']").attr("content");

	var input = {};
	input["catgid"] = parentId;
	console.log("first parentId: "+parentId);
	$.ajax({
    	type   : 'POST',
	    url    : "/products/list_product",
	    data   : JSON.stringify(input),
	    cache: true,
        dataType: 'json',
    	contentType: 'application/json',
        async: false,
        beforeSend: function(xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
	})
    .done(function(dat) {
    	$(".pagination").pagination({
    	    dataSource: dat.OUT_REC,
    	    totalNumber: 8,
    	    pageSize: 8,

    	    ajax: {
    	        beforeSend: function() {
    	        	$(".pagination").prev().find(".goodslist").html('Loading data from database...');
    	        }
    	    },
    	    callback: function(data, pagination) {

    	    	var html = '';
    	    	$(".pagination").prev().find(".goodslist").empty();
    	    	$.each(data, function(i,v){
    	    		html += '<li>';
    		        html += 	'<a href="javascript:" class="thumb">';
    			    html += 		'<img src="'+document.location.origin+"/upload_file/files/"+v.randname+'" alt="first_product" class="thumb loaded">';
    			    html += 		'<p class="txtinfo">';
    				html += 		'<em>'+v.title+'</em>';
    				html += 		'<strong class="flt"><span>$'+v.price+'</span></strong>';
    				html += 		'<strong class="frl"><span>10% OFF</span></strong>';
    			    html += 		'</p>';
    		    	html += 	'</a>';
    		    	html +=		'<input type="hidden" id="prcd" value="'+v.prcd+'" />';
    		    	html +=		'<input type="hidden" id="parentid" value="'+v.parentid+'" />';
    		    	html += 	'<div class="items_foot">';
    			    html += 	'<div class="flt">';
    				html += 		'<ul class="items_foot_opt">';
    				html += 		'<li class="ico_local"><a href="javascript:"></a></li>';
    				html += 		'<li class="ico_save"><a href="javascript:"></a></li>';
    				html += 		'<li class="ico_fb"><a href="javascript:"></a></li>';
    				html += 		'</ul>';
    			    html += 	'</div>';
    			    html += 	'<div class="frl">';
    				html += 		'<ul class="items_foot_view">';
    				html += 		'<li class="ico_atlist"><a href="javascript:">'+v.likecnt+'</a></li>';
    				html += 		'<li class="ico_local"><a href="javascript:">'+v.viewcnt+'</a></li>';
    				html += 		'</ul>';
    				html += 	'</div>';
    				html += 	'</div>';		
    				html += '</li>';
    				
    	    	});

    	    	$(".pagination").prev().find(".goodslist").append(html);
    	    }
    	})
        _loadingWholeStop();
    })
};


main_page.loadProfileImage = function(){
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken  = $("meta[name='_csrf']").attr("content");
	var input = {};
	
	input["usercd"] = getSesion();
	
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

			$.each(data.OUT_REC, function(i, v){
				$("#sidebar_photo").attr("src",document.location.origin+"/upload_file/files/"+v.randname);
//				$("#header_profile").attr("src",document.location.origin+"/upload_file/files/"+v.randname);
			});

		  }
	  });
};


function getSesion(){
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


//call back
function callbackFn(data){
	if(data.IS_TRUE){
		location.reload();
	}	
}


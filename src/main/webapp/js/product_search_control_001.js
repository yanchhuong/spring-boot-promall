
var main_page = {};
var parentId = '';
var show;
var countClick = 0;
var clickChat = 0;
var appendChat = '';

$(document).ready(function(){
	$(".sub_cat").hide();
//	main_page.loadCategory();
	main_page.listProduct();
	
	
	$(document).delegate("#sidebar_catagory_list a", "click", function(){
		parentId   = $(this).attr("catg-id");
		$(".hdbox h3").text($(this).text());
		
		main_page.listProduct(parentId);
//		main_page.categoryChild(parentId);
	});

	$(".goodslist li").live("click", function(){
		var prcd = $(this).find("#prcd").val();
		var parentid = $(this).find("#parentid").val();
		window.location.href = document.location.origin+'/preview?prcd='+prcd+"&parentid="+parentid;
	});
	
});

main_page.loadCategory = function(){
	$.ajax({
		type   : 'GET',
	    url    : "/category/list",
	    cache  : true
	})
	.done(function(dat){
    	var html = "";
    	
    	$.each(dat.OUT_REC, function(i,v){
    		var j = i + 1;
    		if(v.lvl == "1"){
    			html += '<a href="#none" class="ctg_0'+j+'" catg-id="'+v.catgid+'" style="background-image:url('+"https://s3-us-west-1.amazonaws.com/g9bay-image-files/"+v.randname+');background-size:20px;background-position:10px;">'+v.nm_eng+'</a>';
    		}
    	});
    	$("#sidebar_catagory_list").html(html);
	})
};


main_page.listProduct = function(parentId){
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
    		        html += 	'<a href="#none" class="thumb">';
    			    html += 		'<img src="'+"https://s3-us-west-1.amazonaws.com/g9bay-image-files/"+v.randname+'" alt="first_product" class="thumb loaded">';
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
    				html += 		'<li class="ico_local"><a href="#none"></a></li>';
    				html += 		'<li class="ico_save"><a href="#none"></a></li>';
    				html += 		'<li class="ico_fb"><a href="#none"></a></li>';
    				html += 		'</ul>';
    			    html += 	'</div>';
    			    html += 	'<div class="frl">';
    				html += 		'<ul class="items_foot_view">';
    				html += 		'<li class="ico_local"><a href="#none">'+v.viewcnt+'</a></li>';
    				html += 		'<li class="ico_atlist"><a href="#none">'+v.likecnt+'</a></li>';
    				html += 		'</ul>';
    				html += 	'</div>';
    				html += 	'</div>';		
    				html += '</li>';
    				
    	    	});

    	    	$(".pagination").prev().find(".goodslist").append(html);
    	    }
    	})
    	
    	
    })
};

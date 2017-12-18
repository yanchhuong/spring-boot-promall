
var main_page = {};

$(document).ready(function(){
	$(".sub_cat").hide();
	main_page.loadCategory();
	
	$(document).delegate("#sidebar_catagory_list a", "click", function(){
		$(".sub_cat").slideToggle();
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
    			html += '<a href="#none" class="ctg_0'+j+'" style="background-image:url('+document.location.origin+"/upload_file/files/"+v.randname+')">'+v.nm_eng+'</a>';    			
    		}
    	});
    	$("#sidebar_catagory_list").html(html);
	})
};
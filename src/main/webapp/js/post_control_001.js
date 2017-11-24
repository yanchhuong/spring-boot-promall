//jQuery time
 var current_fs, next_fs, previous_fs; //fieldsets
 var left, opacity, scale; //fieldset properties which we will animate
 var animating; //flag to prevent quick multiclick glitches
 var post_control_001={};
 $(document).ready(function(){
	 post_control_001.listMenu();
	 $(".next").click(function(){
 		if(animating) return false;
 			animating = true;
 			if($(this).parent().is('li')){
 					current_fs = $('#form0');
 					next_fs  = $('#form1');
 				}
 			else{
 				current_fs = $(this).parent();
         		next_fs =  $(this).parent().next();
 			} 
 			//activate next step on progressbar using the index of next_fs
 			$("#progressbar li").eq($("fieldset").index(next_fs)).addClass("active");
 	
 			//show the next fieldset
 				next_fs.show(); 
 				//hide the current fieldset with style
 				current_fs.animate({opacity: 0}, {
 				step: function(now, mx) {
 				//as the opacity of current_fs reduces to 0  stored in "now"
 				//1. scale current_fs down to 80%
 				scale = 1 - (1-  now) * 0.2;
 				//2. bring next_fs from the right(50%)
 				left = (now * 50)+"%";
 				//3. increase opacity of next_fs to 1 as it moves in
 				opacity = 1 - now;
 				current_fs.css({
 				'transform': 'scale('+scale+')',
         	'position': 'absolute'
 			});
 				next_fs.css({'left': left, 'opacity': opacity});
 				}, 
 				duration: 800, 
 				complete: function(){
 				current_fs.hide();
 				animating = false;
 			}, 
 			//this comes from the custom easing plugin
 				easing: 'easeInOutBack'
 			});
 		});

 		$(".previous").click(function(){
 			if(animating) return false;
 			animating = true;
 	
 			current_fs = $(this).parent();
 			previous_fs = $(this).parent().prev();
 	
 			//deactivate current step on progressbar
 			$("#progressbar li").eq($("fieldset").index(current_fs)).removeClass("active");
 	
 			//show the previous fieldset
 			previous_fs.show(); 
 			//hide the current fieldset with style
 			current_fs.animate({opacity: 0}, {
 				step: function(now, mx) {
 			//as the opacity of current_fs reduces to 0  stored in "now"
 			//1. scale previous_fs from 80% to 100%
 				scale = 0.8 + (1 - now) * 0.2;
 			//2. take current_fs to the right(50%)  from 0%
 				left = ((1 - now) * 50)+"%";
 			//3. increase opacity of previous_fs to 1 as it moves in
 				opacity = 1 - now;
 				current_fs.css({'left': left});
 				previous_fs.css({'transform': 'scale('+scale+')', 'opacity': opacity});
 			}, 
 			duration: 800, 
 			complete: function(){
 				current_fs.hide();
 				animating = false;
 			}, 
 				//this comes from the custom easing plugin
 				easing: 'easeInOutBack'
 			});
 		});
 
 		$(".submit").click(function(){
 			return false;
 		})

 });

post_control_001.listMenu=function(){
	$.ajax({
    	type   : 'GET',
	    url    : "/category/list",
	    cache  : true
	})
    .done(function(dat) {
    	var tbody = $("#primary_nav_wrap");
    	var html = "";
//    	tbody.html('');

    	html= "";
    	$.each(dat.OUT_REC, function(i,v){
    		if(v.lvl == "1"){
    		   html += "<li data-id="+v.catgid+" class='current-menu-item'><a href='' class='next'>"+v.nm_eng+"</a></li>";
    		}
    	});
    	var ul="<ul></ul>";
    	$("#MENU").append(html);
    	$.each(dat.OUT_REC, function(i,v){
    		if(v.lvl == "2"){
    			$("#MENU li").each(function(){
    	    		if($(this).data("id") == v.parentid){
    	    		    $(this).append(ul).append("<li data-id="+v.catgid+" class='next'><a href='#' class='next'>"+v.nm_eng+"</a></li>");
    	    		}
    	    	});
    		}
        });
    	
    	
	})
};
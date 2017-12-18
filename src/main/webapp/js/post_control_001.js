//jQuery time
 var current_fs, next_fs, previous_fs; //fieldsets
 var left, opacity, scale; //fieldset properties which we will animate
 var animating; //flag to prevent quick multiclick glitches
 var post_control_001={};
 var ctgr_name = "";
 var catgcd;
 
 
 $(document).ready(function(){
	 post_control_001.OnLoadImage();
	 post_control_001.listMenu();
	 post_control_001.listProvince();
	 
	 $(document).on("click", ".menu-item-btn", function(){
		 $('.selected').removeClass('selected');
		 $('.show-content').removeClass('show-content');
		 $(this).addClass('selected');
		 $(this).next().addClass('show-content');
	 });

	 $(document).on("click", ".next", function(){
 		if(animating) return false;
 			animating = true;
 				ctgr_name = $(this).parent().parent().parent().prev().text();
 			    ctgr_name += " » "+$(this).text().trim();
 			
 			if($(this).parent().is('li')){
 				$("#ctgr_nm").val(ctgr_name);
 				catgcd = $(this).attr("data-id");
				current_fs = $('#form0');
 				next_fs    = $('#form1');
 			}else{
	 			current_fs = $(this).parent();
	        	next_fs    = $(this).parent().next();
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
 
	 $(document).on("click", ".wrap_img", function(){
		 $(this).remove();
	 });

	 $(".submit").click(function(){
		 return false;
	 });
	 
	 $("#saveAll").click(function(){
		 post_control_001.SaveProductPost();
	 });

});

post_control_001.listMenu=function(){
	$.ajax({
    	type   : 'GET',
	    url    : "/category/list",
	    cache  : true
	})
    .done(function(dat) {

    	var tbody = $("#key-parties-menu");
    	var html = "";
    	var parentId = '';
    	var k = 1;

    	$.each(dat.OUT_REC, function(i,v){
    		if(v.lvl == "1"){
    			parentId = v.catgid;
    			html += '<li data-id="'+v.catgid+'" class="menu-item">';
    			html += '<a href="#" class="menu-item-btn" id="btn-1"><span>'+v.nm_eng+'</span></a>';
    			html += '<div class="menu-item-content">';
	    			html += '<h3 class="title">'+v.nm_eng+'</h3>';
	    			html += '<div class="contact-card">';
	    			html += '<ul>';
		    			$.each(dat.OUT_REC, function(i,v){
		    				if((parentId == v.parentid) && (v.lvl == "2")){
		        				html += '<li><a href="#" class="next" data-id='+v.catgcd+'>'+v.nm_eng+'</a></li>';
		        			}
		    			});
	    			html += '</ul>';
	    			html += '</div>';
    			html += '</div';
    			html += '</li>';
    		}
    	});
    	tbody.html(html);
	})
};

post_control_001.listProvince=function(){
	$.ajax({
    	type   : 'GET',
	    url    : "/location_map/province_list",
	    cache  : true
	})
    .done(function(dat){
    	$.each(dat.OUT_REC, function(i,v){
    		$("#form3 table #province").append($('<option name="'+v.nm_eng+'" id="'+v.id+'">'+v.nm_eng+'</option>'));
    	});
    });
};


post_control_001.SaveProductPost=function(){
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken  = $("meta[name='_csrf']").attr("content");
	var recImg 	= [];
	var input   = {};
	var isChk   = false;

	if($("#save_contact").is(":checked")){
		isChk = true;
		//update
	}else{
		isChk = false;
		//insert
	}
	
	input["is_checked"] = isChk;
	input["catgcd"] = catgcd;
	input["title"]  = $("#title").val();
	input["price"]  = $("#price").val();
	input["desc"] = $("#description").val();

//	here input image
	$("#results div").each(function(){
		recImg.push({
			"type"     :$(this).find("#type").val(),
			"size"     :$(this).find("#size").val(),
			"path"     :$(this).find("img").attr("value"),
			"orname"   :$(this).find("#orname").val(),
			"regdate"  :$(this).find("#regdate").val(),
			"randname" :$(this).find("#randname").val()
		});
	});
	input["inRec"] = recImg;

//	user_detail table
	input["usercd"] = "12fw3rwfdf23";
	input["cphone"] = $("#phone_number").val();

//	address table
	input["country"]  = $("#country").val();
	input["province"] = $("#province option:selected").text();
	input["detail"]   = $("#addr_detail").val();
	
	console.log("result after input "+JSON.stringify(input));
	
	$.ajax({
		url: '/products/insert_product',
		cache: true,
		processData: false,
		contentType: false,
		dataType: 'text',
		contentType: 'application/json',
		type: 'POST',
		beforeSend: function(xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        data: JSON.stringify(input),
        success: function(result){
        	alert(result);
        }
	})
};


post_control_001.OnLoadImage=function(){
	
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
				post_control_001.uploadFormData(file);
//				var img = $('<img>');
//	            img.attr('src', e.target.result);
//	            img.css('height', '160px');
//	            $('#results').append(img);
//	            $(img).on('click', {id: id}, removeImage);
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
};

post_control_001.uploadFormData = function(file){
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken  = $("meta[name='_csrf']").attr("content");
	var oMyForm    = new FormData();
	var wrap_img   = $('<div class="wrap_img" style="display: inline;"></div>');
	
	oMyForm.append("file", file);
	$.ajax({
		url: '/upload_file/uploadimg',
	    data: oMyForm,
	    dataType: 'text',
	    cache   : true,
	    processData: false,
	    contentType: false,
	    type: 'POST',
	    beforeSend: function(xhr) {
	    	xhr.setRequestHeader(csrfHeader, csrfToken);
	    },
	    success: function(data){
	    	data=JSON.parse(data);
	    	wrap_img.append($('<img width="200px" height="140px" value="'+document.location.origin+"/upload_file/files/"+'" style="margin:6px;">').attr("src", document.location.origin+"/upload_file/files/"+ data.RANDNAME));
	    	wrap_img.append("<input type='hidden' id='orname' value='"+ data.OUT_REC.orname+"'>" );
	    	wrap_img.append("<input type='hidden' id='regdate' value='"+ data.OUT_REC.regdate+"'>" );
	    	wrap_img.append("<input type='hidden' id='size' value='"+ data.OUT_REC.size+"'>" );
	    	wrap_img.append("<input type='hidden' id='randname' value='"+ data.OUT_REC.randname+"'>" );
	    	wrap_img.append("<input type='hidden' id='type' value='"+ data.OUT_REC.type+"'>" );
	    	$("#results").append(wrap_img);

	    }
	});
};





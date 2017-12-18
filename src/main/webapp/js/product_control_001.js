var onDisable = "../img/ico/icon_alim_off.png";
var onEnable = "../img/ico/icon_alim_on.png";
var product_control_001={};
var input={};

$(document).ready(function(e){
	
   product_control_001.listData();
	
   $(document).on("click","#Result_List tr .thumb",function(){
	//	wehrm.popup.openPopup("popup_user_settingrole_001");
		var input={};
		    input["name"]=$(this).parents('tr').find('#name').text() ;
		    input["email"]=$(this).parents('tr').find('#email').text() ;
		    input["usercd"]=$(this).parents('tr').find('#usercd').val() ;
	     wehrm.popup.openPopup("popup_user_settingrole_001",input, function(data){
		      callbackFn(data);
    	 });
	});
	$("#btnaAdd").click(function(){
		//wehrm.popup.openPopup("popup_add_role_list_001");
		/*wehrm.popup.openPopup("popup_uploadimg_002",input, function(data){
			callbackFn(data);
    	});*/
	});
	//enable detail 
	$("span#detail_up").click(function(){
		$(this).hide();
		$(this).removeClass("on");
		
		$("#input_upper").fadeIn();
		$("span#detail_down").show();
		$("span#detail_down").addClass("on");
		$("div.tbl_srch").slideUp();
	});
	$("span#detail_down").click(function(){
		$(this).hide();
		$(this).removeClass("on");
		
		$("#input_upper").fadeOut();
		$("span#detail_up").show();
		$("span#detail_up").addClass("on");
		$("div.tbl_srch").slideDown();
		top.ifrMainResize("N",70);
		
	});
	
	$(document).on("focus", "#regdate",function(){
		$(this).datepicker({dateFormat: 'yy-mm-dd'});
	});
	$(document).on("click", ".btn_folder_plus",function(e){
		$('.tree_layerpop').hide();
		$(this).parent().find('.tree_layerpop').show();	
	});
	$('.combo_style').click(function(){
		$("#pageNum").fadeToggle();
	}).mouseleave(function(){
		$("#pageNum").fadeOut();
	});
	$(".combo_style ul#pageNum li").click(function(){	
		comboSetting(this,"개");
		product_control_001.listData();	
	});
	
	//Key search
	$(document).on("click","#btnBsSearch",function(e){
		product_control_001.listData();	
	})
	//key enter search
	$("#SRCH_STR").keydown(function (e) {
		  if (e.keyCode == 13) {
			  product_control_001.listData();
		  }
	});
	
	//update status after change
	$(document).on("click",".onOrOff",function(){
		var status="";
		if($(this).find("img").attr("src") == onEnable){
			$(this).find("img").attr("src",onDisable);
			status ="false";
		}else{
			status ="true";
			$(this).find("img").attr("src",onEnable);
		}
		 input["enabled"]= status;
		 input["prcd"] = $(this).parents("tr").find("#prcd").val();
		 product_control_001.updateProductStatus(input);
	});
	
	//show status change search
	$(document).on("click","#spStatSRC",function(e){
		$(this).find("#divstatSRC").fadeToggle();
	}).mouseleave(function(){
		$(this).find("#divstatSRC").fadeOut();
	});
	$(document).on("click","#divstatSRC ul#ustatSRC li",function(e){
		$(this).parents('ul').find('li').removeClass('on');
		$(this).addClass("on");
		title = $.trim($(this).find("a").text());
		$(this).parents('#spStatSRC').find('.txt').html(title);
	});
	
	//search advance (detail)
	$(document).on("click", ".btn_search_tb",function(e){
		product_control_001.listData();
	});
	$("#keySRC").keydown(function (e) {
		  if (e.keyCode == 13) {
			  product_control_001.listData();
		  }
	});
	
});	

product_control_001.listData = function(){
	
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken  = $("meta[name='_csrf']").attr("content");
	var enabled    = $("#spStatSRC").find(".txt").text();
	
    if(enabled == "block"){
    	enabled = false;
    }else if(enabled == "Unblock"){
    	enabled = true;
    }else{
    	enabled = "";
    }
    
    var input = {};
    
    if($("#detail_up").attr("class") == "btn_style1 up"){
    	input["keyword"] = $("#SRCH_STR").val();
    }else{
    	input["keyword"] = $("#keySRC").val();
    	if( $("#sPrice").val() != "" ||  $("#ePrice").val() != ""){
    		 input["sprice"]  = $("#sPrice").val()== "" ? 0 :$("#sPrice").val();
   	         input["eprice"]  = $("#ePrice").val()== "" ? 999999 :$("#ePrice").val();
    	}else{
    		 input["sprice"]  =  "";
  	         input["eprice"]  =  "";
    	}
    }
    input["enabled"] = enabled;
    input["regdate"] = $("#regdate").val().replace(/[-]/gi,"");

	   
	$.ajax({
    	type   : 'POST',
	    url    : "/products/list",
	    data   : JSON.stringify(input),
	    cache  : false,
        dataType: 'json',
    	contentType: 'application/json',
        async: false,
        beforeSend: function(xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
	})
    .done(function(dat) {
    	var htmRole = $(".editbtn_top_box .tab");
    	var html    = "";
    	htmRole.html('');
    	/*$.each(dat.ROLE_REC, function(i,v){
    		if(dat.ROLE_REC.length-1 == i){
    			html +='<li class="on"><a href="#none">'+v.role+'<span class="no">('+ v.cnt +')</span></a></li>'; 
    		}else{
    			html +='<li><a href="#none">'+v.role+'<span class="no">('+ v.cnt +')</span></a></li>'; 
    		}
    		
    	});*/
    	htmRole.html(html);
    	if(dat.OUT_REC.length > 0 ){
			$.map(dat.OUT_REC,function(val){
				if(val["enabled"] == 't'){
					val["enabled"] = onEnable;	
				}else{
					val["enabled"] = onDisable;	
				} 
				val["regdate"] = wehrm.string.formatDateTime(val["regdate"],"-");	

			//	total +=1;
				
				return val;
			});			
			$("#Result_List").html($("#tbl_result_template").tmpl(dat.OUT_REC));
		}
		else{
			$("#Result_List").html('<tr><td class="t_center noline" colspan="9"><div>내용이 없습니다.</div></td></tr>');
		}
    	
    	var pagination = dat.PAGINATION;
		drawTablePaing("table_paging",product_control_001.listData, 1, 1);
	//	drawTablePaing(id selector,listrec,numshow,numpage);
	//	drawTablePaing("table_paging", wadmhr_srch_0001.listData, pagination.PAGE_NO, pagination.TOTAL_PAGES);
	//	
	//	input["PAGINATION"] = {
	//			"PAGE_NO": PAGE_NO,
	//			"PAGE_SIZE": PAGE_SIZE
	//          "TOTAL_PAGES": "";
	//	        "TOTAL_ROWS":  "";
	//	};	
   })
};

product_control_001.updateProductStatus=function(data){
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken = $("meta[name='_csrf']").attr("content");
	/*var enbled= $(data).parents('#cbStatus').find('.txt').text();
	var input={};
	    input["enabled"]= (enbled=='Unblock' ? true : false);
	    input["prcd"] = $(data).parents("tr").find("#prcd").val();
	    input["username"] = "";*/
	console.log(input);
	$.ajax({
    	type   : 'POST',
	    url    : '/products/update_status',
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
    //	user_control_001.ListData();
    })
};
//call back
function callbackFn(data){
	if(data.IS_TRUE){
		product_control_001.listData();
	}	
}
function comboSetting(_this,unit) {
	$(_this).parents('ul').find('li').removeClass('on');
	$(_this).addClass("on");
	title = $.trim($(_this).find("a").text());
	$(_this).parents('.combo_style').find('span').html(title);
	if(unit=="개"){
		 PAGE_SIZE = title.replace(unit,"");
	}  
}
function comboSettingStatus(_this) {
	$(_this).parents('ul').find('li').removeClass('on');
	$(_this).addClass("on");
	title = $.trim($(_this).find("a").text());
	$(_this).parents('#cbStatus').find('.txt').html(title);
}
	

	
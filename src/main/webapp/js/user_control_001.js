var _this;
var user_control_001={};
var edithtml='';

$(document).ready(function(){
	user_control_001.ListData();
	
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
    
	//show status change
	$(document).on("click","#cbStatus",function(e){
		$(this).find(".ly_txtcombo").fadeToggle();
	}).mouseleave(function(){
		$(this).find(".ly_txtcombo").fadeOut();
	});
	//update status after change
	$(document).on("click","#txtstatus ul#cbStat li",function(e){
		comboSettingStatus(this,"개");
		user_control_001.updateUseStatus(this);
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
	
	
	$(document).on("click", ".btn_search_tb",function(e){
		user_control_001.ListData();
	});
	$("#keySRC").keydown(function (e) {
		  if (e.keyCode == 13) {
			  user_control_001.ListData();
		  }
	});
	$(document).on("click", ".tab li",function(e){
		$(this).addClass("on");
		$(this).siblings().removeClass();
		var data= $(this).text().replace(/[(0-9)]/gi,"");
		user_control_001.ListData(data);
	});
	
	$(document).on("focus", "#regdate",function(){
		$(this).datepicker();
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
		user_control_001.ListData();	
	});
});

user_control_001.updateUseStatus=function(data){
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken = $("meta[name='_csrf']").attr("content");
	var enbled= $(data).parents('#cbStatus').find('.txt').text();
	var input={};
	    input["enabled"]= (enbled=='Unblock' ? true : false);
	    input["usercd"] = $(data).parents("tr").find("#usercd").val();
	console.log(input);
	$.ajax({
    	type   : 'POST',
	    url    : '/users/updatestatus',
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

user_control_001.ListData=function(data){
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken = $("meta[name='_csrf']").attr("content");
	var input={};
	    input["keyword"]= $("#keySRC").val();
	    input["role"] = data;
	    input["regdate"] = $("#regdate").val().replace(/[/]/gi,"");
	var total= 0;
	$.ajax({
    	type   : 'POST',
	    url    : "/users/list",
	    data   : JSON.stringify(input),
	    cache: false,
        dataType: 'json',
    	contentType: 'application/json',
        async: false,
        beforeSend: function(xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
	})
    .done(function(dat) {
    	var htmRole = $(".editbtn_top_box .tab");
    	var html = "";
    	htmRole.html('');
    	$.each(dat.ROLE_REC, function(i,v){
    		if(i==0){
    			html +='<li class="on"><a href="#none">'+v.role+'<span class="no">('+ v.cnt +')</span></a></li>'; 
    		}else{
    			html +='<li><a href="#none">'+v.role+'<span class="no">('+ v.cnt +')</span></a></li>'; 
    		}
    		
    	});
    	htmRole.html(html);
    	if(dat.OUT_REC.length > 0 ){
			$.map(dat.OUT_REC,function(val){
				if(val["enabled"]!='t'){
					val["enabled"] = "block";	
					
				}else{
					$("#cbStat li").addClass("on");
					val["enabled"] = "Unblock";	
				} 

				total +=1;
				
				return val;
			});
			$("#Result_List").html($("#tbl_result_template").tmpl(dat.OUT_REC));
		}
		else{
			$("#Result_List").html('<tr><td class="t_center noline" colspan="9"><div>내용이 없습니다.</div></td></tr>');
		}
    	
    	var pagination = dat.PAGINATION;
		drawTablePaing("table_paging", user_control_001.ListData, 1, 1);
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


//call back
function callbackFn(data){
	if(data.IS_TRUE){
		menu_control_001.listMenu();
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
	
	
	
	

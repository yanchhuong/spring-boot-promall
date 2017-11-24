var _this;
$(document).ready(function(){
	
	if($("div#searchDefault span.on").attr("id") == "detail_down"){
		input["SRCH_GBN"] = ""; 
	}else if($("div#searchDefault span.on").attr("id") == "detail_up"){
		input["SRCH_STR"] ="";
	}
	
	// end agreement popUp
	$("#com_group li").click(function(){
		$("#com_group li").removeClass("on");
		$(this).addClass("on");
	});
	
	$("#cmenu").click(function(){
		$("#ifr_content").attr("src","menu_control_001_view");
	});
	$("#cuser").click(function(){
		$("#ifr_content").attr("src","user_control_001_view");
	});
	$("#cproduct").click(function(){
		$("#ifr_content").attr("src","product_control_001_view");
	});
	$("#cfeedback").click(function(){
		$("#ifr_content").attr("src","feedback_control_001_view");
	});
	$("#cmessage").click(function(){
		$("#ifr_content").attr("src","message_control_001_view");
	});
	$("#cLocation").click(function(){
		$("#ifr_content").attr("src","location_control_001_view");
	});

	

})
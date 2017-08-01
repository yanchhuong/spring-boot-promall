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
		$("#ifr_content").attr("src","cmenu");
	});
	
	$("#cuser").click(function(){
		$("#ifr_content").attr("src","cuser");
	});
	$("#cmessage").click(function(){
		$("#ifr_content").attr("src","cmessage");
	});
	$("#cfeedback").click(function(){
		$("#ifr_content").attr("src","cfeedback");
	});
})
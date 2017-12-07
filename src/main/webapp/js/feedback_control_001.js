$(document).ready(function(){
	
	$("span#detail_up").on("click", function(){
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
	});

	$(document).delegate(".txt_combo", "click", function(e){ $(e.target).nextAll(".ly_txtcombo").toggle(); });
	$(document).delegate(".ly_txtcombo li a", "click", function(){
		$(".txt").text($(this).text());
	});
	
	$(document).delegate('#pagingPopBtn a:eq(0)', 'click', function(e) {
		$(this).parent().find(".combo_layer").show();
		});
	
	$(document).delegate('#pagingPopBtn', 'mouseleave', function(e) {
		$(this).find(".combo_layer").fadeOut();
		});
	
	$(document).delegate("#pagingPopBtn ul li", "click", function() {
		$("#pagingPopBtn a:eq(0)").html("<span> " + $(this).find("input").val() + "개</span>");
		$("#pagingPopBtn").find(".combo_layer").fadeOut();
		loadData();
		});

});

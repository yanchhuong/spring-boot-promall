var wehrm;
if(!wehrm) wehrm={};
if(!wehrm.file) wehrm.file={};
$(function(){
});

wehrm.file.multiFileDownloadLayer = function(fileJson , repository) {
	var top  = window.event.clientY;
	var left = window.event.clientX-265; 

	var layerHtml = "<div class='file_layer_pop' style='position: absolute;'>";
	layerHtml += "<ul>";
	$.each(fileJson , function(i,v){
		layerHtml += "<li class='"+(i%2==0?"tg_01":"")+"'><a style='cursor:pointer' href='/comm_0001_r001.act?ATCH_SRNO="+v.ATCH_SRNO+"&RAND_KEY="+v.RAND_KEY+"' >"+v.ORCP_FILE_NM+"</a></li>";
	});
	layerHtml += "<li class='tg_02'>&nbsp;";
	layerHtml += "<span class='btnclose'><img src='../../img/wehrm/btn/btn_lpclose.gif' alt='close' onclick='wehrm.file.layerClose()' style='cursor:pointer' /></span>";
	layerHtml += "</li>";
	layerHtml += "</ul>";
	layerHtml += "</div>";
	$("body").append(layerHtml);

	$(".file_layer_pop").css("top" ,top );
	$(".file_layer_pop").css("left",left);
};

/**
 * 리스트 조회에서 사용되는 첨부파일 전체다운로드 레이어 함수
 * 컨텐츠 새로운 디자인 파일 레이어-2014-06-30
 */
wehrm.file.multiFileDownloadListLayer = function(fileJson) {
	var ifrm = $("#ifrmFileDown");
	

	var url = "/comm_atch_list_0001.act?PARAM="+ encodeURIComponent(JSON.stringify(fileJson));

	ifrm.attr("src", url);

	if (document.layers) {
    	ifrm.css("top", event.y+document.documentElement.scrollTop);
    	ifrm.css("left", event.x-270);
    } else {
    	ifrm.css("top", event.clientY+document.documentElement.scrollTop);
    	ifrm.css("left", event.clientX-270);
    }
	
    ifrm.css("visibility", "visible");

    ifrm.load(function(){
    	ifrm[0].contentWindow.focus();
    });
    ifrm.blur(function(){
    	ifrm.css("visibility", "hidden");
    });
};

/**
 * 리스트 조회에서 사용되는 첨부파일 전체다운로드 레이어 함수
 * 컨텐츠 새로운 디자인 파일레이어2 - gray 톤 2014-07-30
 */
wehrm.file.multiFileDownloadListLayer2 = function($target) {
	var ifrm = $("#ifrmFileDown");
	ifrm.attr("src", "");
//	var article_cd = $target.data("cd");
//	aticle_cd = decodeURIComponent(article_cd);
	
	var url = "/hrmy_bdpop_0001.act?ARTICLE_CD="+ encodeURIComponent($target.data("cd"));

	ifrm.attr("src", url);
	ifrm.css("visibility", "visible");
	if (document.layers) {
		ifrm.css("top", event.y+document.documentElement.scrollTop);
		ifrm.css("left", event.x-255);
	} else {
		
		ifrm.css("top", (($target).offset().top + 7));
		ifrm.css("left", event.clientX-255);
	}
	
};

/**
 * 리스트 조회에서 사용되는 첨부파일 전체다운로드 레이어 함수
 * 컨텐츠 새로운 디자인 파일레이어3(colabo) - green 톤 2014-08-13
 */
wehrm.file.multiFileDownloadListLayer3 = function(fileJson) {
	var ifrm = $("#ifrmFileDown");

	var url = "/comm_atch_0003_01.act?PARAM="+ encodeURIComponent(JSON.stringify(fileJson));
	
	ifrm.attr("src", url);

	if (document.layers) {
		ifrm.css("top", event.y+document.documentElement.scrollTop);
		ifrm.css("left", event.x-270);
	} else {
		ifrm.css("top", event.clientY+document.documentElement.scrollTop+10);
		ifrm.css("left", event.clientX-200);
	}
	
	ifrm.css("visibility", "visible");
		
	ifrm.load(function(){
    	ifrm[0].contentWindow.focus();
    });

    ifrm.blur(function(){
    	ifrm.css("visibility", "hidden");
    });
//	$("body", document).live("click", function(e){
//		if($(e.target).parents("#ifrmFileDown").size()==0){
//			if($(e.target).attr("id") != "downIcon" && $(e.target).parents("#downIcon").size() == 0){
//				ifrm.css("visibility", "hidden");
//			}
//		}
//	});
};


wehrm.file.fileDownload = function(fileNm , saveFileNm , saveFilePath) {	
	$("#_fileFrm").find("#_fileNm"      ).val(fileNm      );
	$("#_fileFrm").find("#_saveFileNm"  ).val(saveFileNm  );
	$("#_fileFrm").find("#_saveFilePath").val(saveFilePath);	
	$("#_fileFrm").submit();
};

wehrm.file.layerClose = function(){
	$(".layerstyle1").remove();
};

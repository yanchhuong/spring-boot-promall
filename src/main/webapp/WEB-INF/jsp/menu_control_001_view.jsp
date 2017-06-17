<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<%@include file="fragments/include_admin.jsp"%>
<script src="/js/menu_control_001.js"></script>

<script>
$(function(){
	// Invoke the plugin
	$('input, textarea').placeholder({customClass:'my-placeholder'});
});

top.ifrMainResize("N", 700);
</script>

<style>

.container{
      padding:0px;
 }
</style>


</head>
<body>
<!-- content wrap -->
		<div class="content">

			<div class="content_wrap">

				<!-- 타이틀/버튼영역 -->
				<div class="title_wrap mgb15">
					<div class="left"><h1>부서현황 > 부서설정</h1></div>
					<div class="right"></div>
				</div>
				<!-- //타이틀/버튼영역 -->

				<!-- 안내 -->
				<div class="pop_info_txt">
					<p style="padding:15px 0;">
					* 이용기관의 부서를 등록하세요. 부서는 최대 8depth까지 구성이 가능합니다.<br>
					* 부서등록은 관리자가 설정 가능하며, 관리자 지정 및 변경은 비즈플레이 환경설정에서 하실 수 있습니다.
					</p>
				</div>
				<!-- //안내 -->

				<!-- 상단 기능버튼 -->
				<div class="editbtn_top_box">
					<div class="left">
						최종수정일시 : 2016-01-02 12:00:00
					</div>
				</div>
				<!-- //상단 기능버튼 -->

				<!-- 스크롤테이블 -->
				<div style="position:relative;z-index:200;" class="treemenu_wrap">
					<div class="list_scroll_top">
						<table class="list_table">
							<colgroup>
							<col width="66px">
							<col>
							<col width="95px">
							<col width="120px">
							<col width="120px">
							<col width="40px">
							</colgroup>
							<thead>
							<tr>
								<th scope="col"></th>
								<th scope="col" class="t_left">Name</th>
								<th scope="col" class="t_right">Parent</th>
								<th scope="col" class="t_left">Level</th>
								<th scope="col" class="t_left">User</th>
								<th scope="col" class="t_left"></th>
							</tr>
							</thead>
						</table>
					</div>
					<div class="list_scroll" style="">
						<div class="list_scroll_fix">
							<table class="list_table" id="TBL_DATA">
								<colgroup>
								<col width="66px">
								<col>
								<col width="95px">
								<col width="120px">
								<col width="120px">
								<col width="40px">
								</colgroup>
								<thead class="bg">
									<tr>
										<td class="t_center"></td>
										<td class="t_left"><strong>Total</strong></td>
										<td class="t_right"><strong>947</strong></td>
										<td class="t_left"></td>
										<td class="t_left"></td>
										<td class="t_left"></td>
									</tr>
								</thead>
								<tbody>
<!-- 								<tr> -->
<!-- 									<td class="t_center brd_r"> -->
<!-- 										<div class="ly_po"> -->
<!-- 											<a class="btn_folder_plus">추가</a> -->
<!-- 											layer popup -->
<!-- 											<div class="tree_layerpop" style="display:none;"> -->
<!-- 												<ul> -->
<!-- 													<li><a>동일 수준의 부서로 추가</a></li> -->
<!-- 													<li><a>하위 부서로 추가</a></li> -->
<!-- 												</ul> -->
<!-- 											</div> -->
<!-- 											//layer popup -->
<!-- 										</div> -->
<!-- 									</td> -->
<!-- 									<td class="t_left"><div class="dp1"><span class="ico_treefolder">1</span> <span class="txt">웹케시(주)</span></div></td> -->
<!-- 									<td class="t_right"><a class="txt_d">134</a></td> -->
<!-- 									<td class="t_left"><a class="txt_d off">미지정</a></td> -->
<!-- 									<td class="t_left"><a class="txt_d off">미지정</a></td> -->
<!-- 									<td class="t_center"> -->
<!-- 										<div style="position:relative;"> -->
<!-- 											<a class="btn_folderset" title="설정"></a> -->
<!-- 											layer -->
<!-- 											<div class="depset_layer" style="display:none;"> -->
<!-- 												<ul> -->
<!-- 													<li class="first"><a>부서명 수정</a></li> -->
<!-- 													<li><a>부서 이동</a></li> -->
<!-- 													<li><a>부서 삭제</a></li> -->
<!-- 												</ul> -->
<!-- 											</div> -->
<!-- 											//layer -->
<!-- 										</div> -->
<!-- 									</td> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td class="t_center brd_r"> -->
<!-- 										<div class="ly_po"> -->
<!-- 											<a class="btn_folder_plus">추가</a> -->
<!-- 											layer popup -->
<!-- 											<div class="tree_layerpop" style="display:none;"> -->
<!-- 												<ul> -->
<!-- 													<li><a>동일 수준의 부서로 추가</a></li> -->
<!-- 													<li><a>하위 부서로 추가</a></li> -->
<!-- 												</ul> -->
<!-- 											</div> -->
<!-- 											//layer popup -->
<!-- 										</div> -->
<!-- 									</td> -->
<!-- 									<td class="t_left"><div class="dp2"><span class="ico_treefolder">2</span> <span class="txt">웹케시(주)</span></div></td> -->
<!-- 									<td class="t_right"><a class="txt_d">134</a></td> -->
<!-- 									<td class="t_left"><a class="txt_d off">미지정</a></td> -->
<!-- 									<td class="t_left"><a class="txt_d off">미지정</a></td> -->
<!-- 									<td class="t_center"> -->
<!-- 										<div style="position:relative;"> -->
<!-- 											<a class="btn_folderset" title="설정"></a> -->
<!-- 											layer -->
<!-- 											<div class="depset_layer" style="display:none;"> -->
<!-- 												<ul> -->
<!-- 													<li class="first"><a>부서명 수정</a></li> -->
<!-- 													<li><a>부서 이동</a></li> -->
<!-- 													<li><a>부서 삭제</a></li> -->
<!-- 												</ul> -->
<!-- 											</div> -->
<!-- 											//layer -->
<!-- 										</div> -->
<!-- 									</td> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td class="t_center brd_r"> -->
<!-- 										<div class="ly_po"> -->
<!-- 											<a class="btn_folder_plus">추가</a> -->
<!-- 											layer popup -->
<!-- 											<div class="tree_layerpop" style="display:none;"> -->
<!-- 												<ul> -->
<!-- 													<li><a>동일 수준의 부서로 추가</a></li> -->
<!-- 													<li><a>하위 부서로 추가</a></li> -->
<!-- 												</ul> -->
<!-- 											</div> -->
<!-- 											//layer popup -->
<!-- 										</div> -->
<!-- 									</td> -->
<!-- 									<td class="t_left"><div class="dp2"><span class="ico_treefolder">2</span> <input type="text" style="width:150px;">&nbsp;<a><img src="../img/btn/btn_s_ok.png" alt="저장"></a><a style="margin-left:2px;"><img src="../img/btn/btn_s_cnl.png" alt="취소"></a></div></td> -->
<!-- 									<td class="t_right"><a class="txt_d">134</a></td> -->
<!-- 									<td class="t_left"><a class="txt_d off">미지정</a></td> -->
<!-- 									<td class="t_left"><a class="txt_d off">미지정</a></td> -->
<!-- 									<td class="t_center"> -->
<!-- 										<div style="position:relative;"> -->
<!-- 											<a class="btn_folderset" title="설정"></a> -->
<!-- 											layer -->
<!-- 											<div class="depset_layer" style="display:none;"> -->
<!-- 												<ul> -->
<!-- 													<li class="first"><a>부서명 수정</a></li> -->
<!-- 													<li><a>부서 이동</a></li> -->
<!-- 													<li><a>부서 삭제</a></li> -->
<!-- 												</ul> -->
<!-- 											</div> -->
<!-- 											//layer -->
<!-- 										</div> -->
<!-- 									</td> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td class="t_center brd_r"> -->
<!-- 										<div class="ly_po"> -->
<!-- 											<a class="btn_folder_plus">추가</a> -->
<!-- 											layer popup -->
<!-- 											<div class="tree_layerpop" style="display:none;"> -->
<!-- 												<ul> -->
<!-- 													<li><a>동일 수준의 부서로 추가</a></li> -->
<!-- 													<li><a>하위 부서로 추가</a></li> -->
<!-- 												</ul> -->
<!-- 											</div> -->
<!-- 											//layer popup -->
<!-- 										</div> -->
<!-- 									</td> -->
<!-- 									<td class="t_left"><div class="dp3"><span class="ico_treefolder">3</span> <span class="txt">웹케시(주)</span></div></td> -->
<!-- 									<td class="t_right"><a class="txt_d">134</a></td> -->
<!-- 									<td class="t_left"><a class="txt_d on">박승현</a></td> -->
<!-- 									<td class="t_left"><a class="txt_d on">BZ234567</a></td> -->
<!-- 									<td class="t_center"> -->
<!-- 										<div style="position:relative;"> -->
<!-- 											<a class="btn_folderset" title="설정"></a> -->
<!-- 											layer -->
<!-- 											<div class="depset_layer" style="display:none;"> -->
<!-- 												<ul> -->
<!-- 													<li class="first"><a>부서명 수정</a></li> -->
<!-- 													<li><a>부서 이동</a></li> -->
<!-- 													<li><a>부서 삭제</a></li> -->
<!-- 												</ul> -->
<!-- 											</div> -->
<!-- 											//layer -->
<!-- 										</div> -->
<!-- 									</td> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td class="t_center brd_r"> -->
<!-- 										<div class="ly_po"> -->
<!-- 											<a class="btn_folder_plus">추가</a> -->
<!-- 											layer popup -->
<!-- 											<div class="tree_layerpop" style="display:none;"> -->
<!-- 												<ul> -->
<!-- 													<li><a>동일 수준의 부서로 추가</a></li> -->
<!-- 													<li><a>하위 부서로 추가</a></li> -->
<!-- 												</ul> -->
<!-- 											</div> -->
<!-- 											//layer popup -->
<!-- 										</div> -->
<!-- 									</td> -->
<!-- 									<td class="t_left"><div class="dp4"><span class="ico_treefolder">4</span> <span class="txt">웹케시(주)</span></div></td> -->
<!-- 									<td class="t_right"><a class="txt_d">134</a></td> -->
<!-- 									<td class="t_left"><a class="txt_d off">미지정</a></td> -->
<!-- 									<td class="t_left"><a class="txt_d off">미지정</a></td> -->
<!-- 									<td class="t_center"> -->
<!-- 										<div style="position:relative;"> -->
<!-- 											<a class="btn_folderset" title="설정"></a> -->
<!-- 											layer -->
<!-- 											<div class="depset_layer" style="display:none;"> -->
<!-- 												<ul> -->
<!-- 													<li class="first"><a>부서명 수정</a></li> -->
<!-- 													<li><a>부서 이동</a></li> -->
<!-- 													<li><a>부서 삭제</a></li> -->
<!-- 												</ul> -->
<!-- 											</div> -->
<!-- 											//layer -->
<!-- 										</div> -->
<!-- 									</td> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td class="t_center brd_r"> -->
<!-- 										<div class="ly_po"> -->
<!-- 											<a class="btn_folder_plus">추가</a> -->
<!-- 											layer popup -->
<!-- 											<div class="tree_layerpop" style="display:none;"> -->
<!-- 												<ul> -->
<!-- 													<li><a>동일 수준의 부서로 추가</a></li> -->
<!-- 													<li><a>하위 부서로 추가</a></li> -->
<!-- 												</ul> -->
<!-- 											</div> -->
<!-- 											//layer popup -->
<!-- 										</div> -->
<!-- 									</td> -->
<!-- 									<td class="t_left"><div class="dp5"><span class="ico_treefolder">5</span> <span class="txt">웹케시(주)</span></div></td> -->
<!-- 									<td class="t_right"><a class="txt_d">134</a></td> -->
<!-- 									<td class="t_left"><a class="txt_d off">미지정</a></td> -->
<!-- 									<td class="t_left"><a class="txt_d off">미지정</a></td> -->
<!-- 									<td class="t_center"> -->
<!-- 										<div style="position:relative;"> -->
<!-- 											<a class="btn_folderset on" title="설정"></a> -->
<!-- 											layer -->
<!-- 											<div class="depset_layer" style="display:block;"> -->
<!-- 												<ul> -->
<!-- 													<li class="first"><a>부서명 수정</a></li> -->
<!-- 													<li><a>부서 이동</a></li> -->
<!-- 													<li><a>부서 삭제</a></li> -->
<!-- 												</ul> -->
<!-- 											</div> -->
<!-- 											//layer -->
<!-- 										</div> -->
<!-- 									</td> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td class="t_center brd_r"> -->
<!-- 										<div class="ly_po"> -->
<!-- 											<a class="btn_folder_plus">추가</a> -->
<!-- 											layer popup -->
<!-- 											<div class="tree_layerpop" style="display:none;"> -->
<!-- 												<ul> -->
<!-- 													<li><a>동일 수준의 부서로 추가</a></li> -->
<!-- 													<li><a>하위 부서로 추가</a></li> -->
<!-- 												</ul> -->
<!-- 											</div> -->
<!-- 											//layer popup -->
<!-- 										</div> -->
<!-- 									</td> -->
<!-- 									<td class="t_left"><div class="dp6"><span class="ico_treefolder">6</span> <span class="txt">웹케시(주)</span></div></td> -->
<!-- 									<td class="t_right"><a class="txt_d">134</a></td> -->
<!-- 									<td class="t_left"><a class="txt_d off">미지정</a></td> -->
<!-- 									<td class="t_left"><a class="txt_d off">미지정</a></td> -->
<!-- 									<td class="t_center"> -->
<!-- 										<div style="position:relative;"> -->
<!-- 											<a class="btn_folderset" title="설정"></a> -->
<!-- 											layer -->
<!-- 											<div class="depset_layer" style="display:none;"> -->
<!-- 												<ul> -->
<!-- 													<li class="first"><a>부서명 수정</a></li> -->
<!-- 													<li><a>부서 이동</a></li> -->
<!-- 													<li><a>부서 삭제</a></li> -->
<!-- 												</ul> -->
<!-- 											</div> -->
<!-- 											//layer -->
<!-- 										</div> -->
<!-- 									</td> -->
<!-- 									//20150326 -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td class="t_center brd_r"> -->
<!-- 										<div class="ly_po"> -->
<!-- 											<a class="btn_folder_plus">추가</a> -->
<!-- 											layer popup -->
<!-- 											<div class="tree_layerpop" style="display:none;"> -->
<!-- 												<ul> -->
<!-- 													<li><a>동일 수준의 부서로 추가</a></li> -->
<!-- 													<li><a>하위 부서로 추가</a></li> -->
<!-- 												</ul> -->
<!-- 											</div> -->
<!-- 											//layer popup -->
<!-- 										</div> -->
<!-- 									</td> -->
<!-- 									<td class="t_left"><div class="dp7"><span class="ico_treefolder">7</span> <span class="txt">웹케시(주)</span></div></td> -->
<!-- 									<td class="t_right"><a class="txt_d">134</a></td> -->
<!-- 									<td class="t_left"><a class="txt_d off">미지정</a></td> -->
<!-- 									<td class="t_left"><a class="txt_d off">미지정</a></td> -->
<!-- 									<td class="t_center"> -->
<!-- 										<div style="position:relative;"> -->
<!-- 											<a class="btn_folderset" title="설정"></a> -->
<!-- 											layer -->
<!-- 											<div class="depset_layer" style="display:none;"> -->
<!-- 												<ul> -->
<!-- 													<li class="first"><a>부서명 수정</a></li> -->
<!-- 													<li><a>부서 이동</a></li> -->
<!-- 													<li><a>부서 삭제</a></li> -->
<!-- 												</ul> -->
<!-- 											</div> -->
<!-- 											//layer -->
<!-- 										</div> -->
<!-- 									</td> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td class="t_center brd_r"> -->
<!-- 										<div class="ly_po"> -->
<!-- 											<a class="btn_folder_plus">추가</a> -->
<!-- 											layer popup -->
<!-- 											<div class="tree_layerpop" style="display:none;"> -->
<!-- 												<ul> -->
<!-- 													<li><a>동일 수준의 부서로 추가</a></li> -->
<!-- 													<li><a>하위 부서로 추가</a></li> -->
<!-- 												</ul> -->
<!-- 											</div> -->
<!-- 											//layer popup -->
<!-- 										</div> -->
<!-- 									</td> -->
<!-- 									<td class="t_left"><div class="dp8"><span class="ico_treefolder">8</span> <span class="txt">웹케시(주)</span></div></td> -->
<!-- 									<td class="t_right"><a class="txt_d">134</a></td> -->
<!-- 									<td class="t_left"><a class="txt_d off">미지정</a></td> -->
<!-- 									<td class="t_left"><a class="txt_d off">미지정</a></td> -->
<!-- 									<td class="t_center"> -->
<!-- 										<div style="position:relative;"> -->
<!-- 											<a class="btn_folderset" title="설정"></a> -->
<!-- 											layer -->
<!-- 											<div class="depset_layer" style="display:none;"> -->
<!-- 												<ul> -->
<!-- 													<li class="first"><a>부서명 수정</a></li> -->
<!-- 													<li><a>부서 이동</a></li> -->
<!-- 													<li><a>부서 삭제</a></li> -->
<!-- 												</ul> -->
<!-- 											</div> -->
<!-- 											//layer -->
<!-- 										</div> -->
<!-- 									</td> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td class="t_center brd_r"> -->
<!-- 										<div class="ly_po"> -->
<!-- 											<a class="btn_folder_plus">추가</a> -->
<!-- 											layer popup -->
<!-- 											<div class="tree_layerpop" style="display:none;"> -->
<!-- 												<ul> -->
<!-- 													<li><a>동일 수준의 부서로 추가</a></li> -->
<!-- 													<li><a>하위 부서로 추가</a></li> -->
<!-- 												</ul> -->
<!-- 											</div> -->
<!-- 											//layer popup -->
<!-- 										</div> -->
<!-- 									</td> -->
<!-- 									<td class="t_left"><div class="dp8"><span class="ico_treefolder">8</span> <span class="txt">웹케시(주)</span></div></td> -->
<!-- 									<td class="t_right"><a class="txt_d">134</a></td> -->
<!-- 									<td class="t_left"><a class="txt_d off">미지정</a></td> -->
<!-- 									<td class="t_left"><a class="txt_d off">미지정</a></td> -->
<!-- 									<td class="t_center"> -->
<!-- 										<div style="position:relative;"> -->
<!-- 											<a class="btn_folderset" title="설정"></a> -->
<!-- 											layer -->
<!-- 											<div class="depset_layer" style="display:none;"> -->
<!-- 												<ul> -->
<!-- 													<li class="first"><a>부서명 수정</a></li> -->
<!-- 													<li><a>부서 이동</a></li> -->
<!-- 													<li><a>부서 삭제</a></li> -->
<!-- 												</ul> -->
<!-- 											</div> -->
<!-- 											//layer -->
<!-- 										</div> -->
<!-- 									</td> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td class="t_center brd_r"> -->
<!-- 										<div class="ly_po"> -->
<!-- 											<a class="btn_folder_plus">추가</a> -->
<!-- 											layer popup -->
<!-- 											<div class="tree_layerpop" style="display:none;"> -->
<!-- 												<ul> -->
<!-- 													<li><a>동일 수준의 부서로 추가</a></li> -->
<!-- 													<li><a>하위 부서로 추가</a></li> -->
<!-- 												</ul> -->
<!-- 											</div> -->
<!-- 											//layer popup -->
<!-- 										</div> -->
<!-- 									</td> -->
<!-- 									<td class="t_left"><div class="dp8"><span class="ico_treefolder">8</span> <span class="txt">웹케시(주)</span></div></td> -->
<!-- 									<td class="t_right"><a class="txt_d">134</a></td> -->
<!-- 									<td class="t_left"><a class="txt_d off">미지정</a></td> -->
<!-- 									<td class="t_left"><a class="txt_d off">미지정</a></td> -->
<!-- 									<td class="t_center"> -->
<!-- 										<div style="position:relative;"> -->
<!-- 											<a class="btn_folderset" title="설정"></a> -->
<!-- 											layer -->
<!-- 											<div class="depset_layer" style="display:none;"> -->
<!-- 												<ul> -->
<!-- 													<li class="first"><a>부서명 수정</a></li> -->
<!-- 													<li><a>부서 이동</a></li> -->
<!-- 													<li><a>부서 삭제</a></li> -->
<!-- 												</ul> -->
<!-- 											</div> -->
<!-- 											//layer -->
<!-- 										</div> -->
<!-- 									</td> -->
<!-- 								</tr> -->
<!-- 								<tr style="display:none;"> -->
<!-- 									<td class="t_center brd_r"> -->
<!-- 										<div class="ly_po"> -->
<!-- 											<a class="btn_folder_plus">추가</a> -->
<!-- 											layer popup -->
<!-- 											<div class="tree_layerpop" style="display:none;"> -->
<!-- 												<ul> -->
<!-- 													<li><a>동일 수준의 부서로 추가</a></li> -->
<!-- 													<li><a>하위 부서로 추가</a></li> -->
<!-- 												</ul> -->
<!-- 											</div> -->
<!-- 											//layer popup -->
<!-- 										</div> -->
<!-- 									</td> -->
<!-- 									<td class="t_left"><div class="dp0"><input type="text" style="width:68%;">&nbsp;<a><img src="/img/btn/btn_s_ok.png" alt="저장"></a><a style="margin-left:2px;"><img src="/img/btn/btn_s_cnl.png" alt="취소"></a></div></td> -->
<!-- 									<td class="t_right"><a class="txt_d">134</a></td> -->
<!-- 									<td class="t_left"><a class="txt_d off">미지정</a></td> -->
<!-- 									<td class="t_left"><a class="txt_d off">미지정</a></td> -->
<!-- 									<td class="t_center"> -->
<!-- 										<div style="position:relative;"> -->
<!-- 											<a class="btn_folderset" title="설정"></a> -->
<!-- 											layer -->
<!-- 											<div class="depset_layer" style="display:none;"> -->
<!-- 												<ul> -->
<!-- 													<li class="first"><a>부서명 수정</a></li> -->
<!-- 													<li><a>부서 이동</a></li> -->
<!-- 													<li><a>부서 삭제</a></li> -->
<!-- 												</ul> -->
<!-- 											</div> -->
<!-- 											//layer -->
<!-- 										</div> -->
<!-- 									</td> -->
<!-- 								</tr> -->

<!-- 								부서미지정 -->
<!-- 								<tr> -->
<!-- 									<td class="t_center brd_r"></td> -->
<!-- 									<td class="t_left"><div class="dp0"><span class="ico_treefolder"></span> <span class="txt">부서 미지정</span></div></td> -->
<!-- 									<td class="t_right"><a class="txt_d">234</a></td> -->
<!-- 									<td class="t_left"></td> -->
<!-- 									<td class="t_left"></td> -->
<!-- 									<td class="t_left"></td> -->
<!-- 								</tr> -->
								<!-- //부서미지정 -->

								</tbody>
							</table>
						</div>
					</div>
				</div>
				<!-- //스크롤테이블 -->

			</div>

		</div>
		<!-- //content wrap -->

</body>
</html>
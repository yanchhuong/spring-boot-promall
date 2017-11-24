<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
 <!-- default header name is X-CSRF-TOKEN -->
 <meta name="_csrf" content="${_csrf.token}"/>
 <meta name="_csrf_header" content="${_csrf.headerName}"/>
   
<%@include file="fragments/include_admin.jsp"%>
<script src="/js/location_control_001.js"></script>

<script>
$(function(){
	// Invoke the plugin
	$('input, textarea').placeholder({customClass:'my-placeholder'});
});
top.ifrMainResize("N", 878);
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
					<div class="left"><h1>Location Control</h1></div>
					
				</div>
				<!-- //타이틀/버튼영역 -->

				<!-- 안내 -->
			
				<!-- table search-->
				<div class="tbl_srch">
					<table class="">
						<caption></caption>
						<colgroup>
						<col style="width:62px;">
						<col style="width:254px;">
						<col style="width:60px;">
						<col>
						</colgroup>
						<tbody>
							<tr>
								<th scope="row"><div>ខេត្ត/ក្រុង</div></th>
								   <td>
								   <div>
									<span class="txt_combo " id="spCity" style="width:190px;">
										<a href="javascript:" class="txt elipsis">Choose</a>
										<!-- 레이어 -->
										<div class="ly_txtcombo" id="divCity" style="display: none;">
											<ul id="ulCity">
												
											</ul>
										</div>
										<!-- //레이어 -->
									</span>
									
								  </div>
								</td>
								<th scope="row"><div>ស្រុក/ខ័ណ្ឌ</div></th>
								<td>
								   <div>
									<span class="txt_combo " id="spProvince" style="width:190px;">
										<a href="#none" class="txt">Choose</a>
										<!-- 레이어 -->
										<div class="ly_txtcombo" id="divProvince" style="display: none;">
											<ul id="ulProvince">
											</ul>
										</div>
										<!-- //레이어 -->
									</span>
									<input type="text" style="width:240px;" id="SCRKEY">
									<span class="btn_search_tb"><a href="#none"> 검색 </a></span>
								  </div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- //table search -->
				
				<div class="editbtn_top_box">
					<div class="left">
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
								<th scope="col" class="t_left">Description</th>
								<th scope="col" class="t_left"></th>
							</tr>
							</thead>
						</table>
					</div>
					<div class="list_scroll" style="height: 655px;max-height: 545px;">
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
										<td class="t_right" ><strong id="CNT"></strong></td>
										<td class="t_left"></td>
										<td class="t_left"></td>
										<td class="t_left"></td>
									</tr>
								</thead>
								<tbody>

								</tbody>
							</table>
							<div>&nbsp;</div>
						</div>
					</div>
				</div>
				<!-- //스크롤테이블 -->
			</div>
		</div>
		<!-- //content wrap -->

<%@include file="fragments/html_iframes.jsp"%>
</body>
</html>
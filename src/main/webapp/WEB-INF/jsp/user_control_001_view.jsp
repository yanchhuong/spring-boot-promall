<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="fragments/include_admin.jsp"%>
<script src="/js/user_control_001.js"></script>

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
				<div class="title_wrap">
					<div class="left"><h1>출퇴근체크</h1></div>
					<div class="right">
						<div class="input_box"><input type="text" style="width:180px;" placeholder="검색어를 입력하세요"><a href="#none"><img src="../img/btn/btn_topsearch.gif" alt="조회"></a></div>
					</div>
				</div>
				<!-- //타이틀/버튼영역 -->

				<!-- table search-->
				<div class="tbl_srch">
					<table class="">
						<caption></caption>
						<colgroup>
						<col style="width:62px;">
						<col>
						</colgroup>
						<tbody>
							<tr>
								<th scope="row"><div>근무일자</div></th>
								<td><div>
									<input type="text" style="width:70px;" value="2016-05-01">&nbsp;<a href="#none"><img src="../img/ico/ico_calendar.png" alt="달력"></a>
								</div></td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- //table search -->

				<!-- 상단 기능버튼 -->
				<div class="editbtn_top_box">
					<div class="left">
						<h3>근태등록 <span class="txt_b2" style="font-weight:normal;font-size:12px;margin-left:20px;">※ 근태가 정상이 아닌 직원만 근태항목을 변경해 주세요.</span></h3>
					</div>
				</div>
				<!-- //상단 기능버튼 -->

				<!-- table result -->
				<div class="table_layout mgb15">
					<table class="tbl_result">
						<caption></caption>
						<colgroup>
						<col style="width:10%;">
						<col style="width:10%;">
						<col style="width:25%;">
						<col style="width:10%;">
						<col style="width:10%;">
						<col style="width:10%;">
						<col >
						</colgroup>
						<thead>
							<tr>
								<th scope="col"><div>사원번호</div></th>
								<th scope="col"><div>성명</div></th>
								<th scope="col"><div>부서</div></th>
								<th scope="col"><div>직위(직급)</div></th>
								<th scope="col"><div>직책</div></th>
								<th scope="col"><div>근태항목</div></th>
								<th scope="col"><div>적요</div></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><div>W20010101001</div></td>
								<td><div>홍일점</div></td>
								<td><div class="elipsis">지원부문 관리부</div></td>
								<td><div>과장</div></td>
								<td><div>팀장</div></td>
								<td class="ipt"><div>
									<span class="txt_combo">
										<a href="#none" class="txt">정상</a>
										<!-- 레이어 -->
										<div class="ly_txtcombo" style="display:none;">
											<ul>
												<li><a href="#none">정상</a></li>
												<li><a href="#none">지각</a></li>
												<li><a href="#none">조퇴</a></li>
												<li><a href="#none">결근</a></li>
											</ul>
										</div>
										<!-- 레이어 -->
									</span>
								</div></td>
								<td class="ipt"><div></div></td>
							</tr>
							<tr>
								<td><div>W20010101002</div></td>
								<td><div>홍길형</div></td>
								<td><div class="elipsis">지원부문 관리부</div></td>
								<td><div>사원</div></td>
								<td><div>팀원</div></td>
								<td class="ipt"><div>
									<span class="txt_combo">
										<a href="#none" class="txt">지각</a>
										<!-- 레이어 -->
										<div class="ly_txtcombo" style="display:none;">
											<ul>
												<li><a href="#none" class="on">정상</a></li><!-- 활성화클래스 on -->
												<li><a href="#none">지각</a></li>
												<li><a href="#none">조퇴</a></li>
												<li><a href="#none">결근</a></li>
											</ul>
										</div>
										<!-- 레이어 -->
									</span>
								</div></td>
								<td class="ipt"><div><span style="vertical-align:-1px;">출근시간</span> <input type="text" style="width:50px;" value="00:00"></div></td>
							</tr>
							<tr>
								<td><div>W20010101003</div></td>
								<td><div>고길동</div></td>
								<td><div class="elipsis">사업부문 영업부</div></td>
								<td><div>부장</div></td>
								<td><div>팀장</div></td>
								<td class="ipt"><div>
									<span class="txt_combo">
										<a href="#none" class="txt">조퇴</a>
										<!-- 레이어 -->
										<div class="ly_txtcombo" style="display:none;">
											<ul>
												<li><a href="#none" class="on">정상</a></li><!-- 활성화클래스 on -->
												<li><a href="#none">지각</a></li>
												<li><a href="#none">조퇴</a></li>
												<li><a href="#none">결근</a></li>
											</ul>
										</div>
										<!-- 레이어 -->
									</span>
								</div></td>
								<td class="ipt"><div><span style="vertical-align:-1px;">퇴근시간</span> <input type="text" style="width:50px;" value="00:00"></div></td>
							</tr>
							<tr>
								<td><div>W20010101004</div></td>
								<td><div>이소라</div></td>
								<td><div class="elipsis">생산부문 기술부</div></td>
								<td><div>대리</div></td>
								<td><div>팀원</div></td>
								<td class="ipt"><div>
									<span class="txt_combo">
										<a href="#none" class="txt">정상</a>
										<!-- 레이어 -->
										<div class="ly_txtcombo" style="display:none;">
											<ul>
												<li><a href="#none" class="on">정상</a></li><!-- 활성화클래스 on -->
												<li><a href="#none">지각</a></li>
												<li><a href="#none">조퇴</a></li>
												<li><a href="#none">결근</a></li>
											</ul>
										</div>
										<!-- 레이어 -->
									</span>
								</div></td>
								<td class="ipt"><div></div></td>
							</tr>
							<tr>
								<td><div>W20010101004</div></td>
								<td><div>이소라</div></td>
								<td><div class="elipsis">생산부문 기술부</div></td>
								<td><div>대리</div></td>
								<td><div>팀원</div></td>
								<td class="ipt"><div><strong class="txt_b">휴가</strong></div></td>
								<td class="ipt"><div>연차휴가</div></td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- //table result -->

				<!-- 하단버튼 -->
				<div class="t_center">
					<span class="btn_style1_b"><a href="#none">저장</a></span>
					<span class="btn_style1"><a href="#none">취소</a></span>
				</div>
				<!-- //하단버튼 -->

			</div>

		</div>
		<!-- //content wrap -->

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko" xml:lang="ko">
<head>
<title>HR Portal | 인사관리</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="stylesheet" type="text/css" href="../../css/uploadimg.css" media="all"> 
 <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
 <!-- default header name is X-CSRF-TOKEN -->
 <meta name="_csrf" content="${_csrf.token}"/>
 <meta name="_csrf_header" content="${_csrf.headerName}"/>
<%@include file="../fragments/include_admin.jsp"%>
<script src="../js/popup_user_settingrole_list_001.js"></script>
<script>
$(function(){
	// Invoke the plugin
	$('input, textarea').placeholder({customClass:'my-placeholder'});
});
</script>
</head>

<body style="width:800px;background-color:#ddd;">

	<div class="pop_wrap">

		<!-- 팝업 헤더 -->
		<div class="pop_header">
			<h1>USER START BUSINESS PAYMENT</h1>
			<a href="#none" onClick="self.close()" class="btn_popclose"><img src="../img/btn/btn_popclose.gif" alt="팝업닫기"></a>
		</div>
		<!-- //팝업 헤더 -->

		<!-- 팝업 컨텐츠 -->
		<div class="pop_container">
			<div class="txt_b2" style="margin-bottom:15px;">
				* Mr.Lee role list
			</div>

				<!-- 테이블 영역 -->
				<div class="mgb15">
					<table class="tbl_result">
						<caption></caption>
						<colgroup>
						<col style="width:15%;">
						<col>
						<col style="width:15%;">
						<col style="width:15%;">
						<col style="width:10%;">
						<col style="width:50px;">
						</colgroup>
						<thead>
							<tr>
								<th scope="col"><div>Title</div></th>
								<th scope="col"><div>Start~End Date</div></th>
								<th scope="col"><div>Register Date</div></th>
								<th scope="col"><div>Remain time</div></th>
								<th scope="col"><div>Role Type</div></th>
								<th scope="col"><div></div></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><div>2014년 연차</div></td>
								<td><div>2014-01-01 ~ 2014-12-31</div></td>
								<td><div>2014-12-31</div></td>
								<td><div>2014-01-02 10:00:00</div></td>
								<td><div><a href="#none">[상세조회]</a></div></td>
								<td class="ipt"><div class="t_center"><a href="#none"><img src="../img/btn/btn_row_del.png" alt="삭제"></a></div></td>
							</tr>
							<tr>
								<td><div>2015년 연차</div></td>
								<td><div>2015-01-01 ~ 2015-12-31</div></td>
								<td><div>2015-12-31</div></td>
								<td><div>2015-01-02 10:00:00</div></td>
								<td><div><a href="#none">[상세조회]</a></div></td>
								<td class="ipt"><div class="t_center"><a href="#none"><img src="../img/btn/btn_row_del.png" alt="삭제"></a></div></td>
							</tr>
							<tr class="ipt_row">
								<td><div><input type="text" style="width:92%;" value="2016년 연차"></div></td>
								<td>
									<div>
										<input type="text" style="width:70px;" value="2016-01-01"> <a href="#none"><img src="../img/ico/ico_calendar.png" alt="달력"></a> ~
										<input type="text" style="width:70px;" value="2016-12-31"> <a href="#none"><img src="../img/ico/ico_calendar.png" alt="달력"></a>
									</div>
								</td>
								<td>
									<div>
										<input type="text" style="width:70px;" value="2016-12-31"> <a href="#none"><img src="../img/ico/ico_calendar.png" alt="달력"></a>
									</div>
								</td>
								<td><div><span class="btn_style3"><a href="#none">연차 생성하기</a></span></div></td>
								<td><div></div></td>
								<td><div></div></td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- //테이블 영역 -->
				<div class="add_newbox">
					<a href="#none" class="add_newtxt"><span class="ico">Add Role</span></a>
				</div>
		   </div>
		<!-- //팝업 컨텐츠 -->

	</div>

</body>
</html>
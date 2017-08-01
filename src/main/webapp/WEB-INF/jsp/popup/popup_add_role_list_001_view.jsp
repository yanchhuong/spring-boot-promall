<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko" xml:lang="ko">
<head>
<title>HR 인사관리 | 인사관리</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="stylesheet" type="text/css" href="../../css/uploadimg.css" media="all"> 
 <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
 <!-- default header name is X-CSRF-TOKEN -->
 <meta name="_csrf" content="${_csrf.token}"/>
 <meta name="_csrf_header" content="${_csrf.headerName}"/>
<%@include file="../fragments/include_admin.jsp"%>
<script src="../js/popup_add_role_list_001.js"></script>
 
<script>
$(function(){
	// Invoke the plugin
	$('input, textarea').placeholder({customClass:'my-placeholder'});
});
</script>
</head>

<body style="width:345px;background-color:#ddd;">

	<div class="pop_wrap">

		<!-- 팝업 헤더 -->
		<div class="pop_header">
			<h1>Add Role List</h1>
			<a href="#none" onClick="self.close()" class="btn_popclose"><img src="../img/btn/btn_popclose.gif" alt="팝업닫기"></a>
		</div>
		<!-- //팝업 헤더 -->

		<!-- 팝업 컨텐츠 -->
		<div class="pop_container">

			<!-- 업무이력 수정 -->
			<div class="table_layout" style="border-top:1px solid #ddd;">
				<table class="tbl_result">
					<caption></caption>
					<colgroup>
					<col>
	
					<col style="width:50px;">
					</colgroup>
					<tbody>
						<tr>
							<td class="ipt"><div><input type="text" style="width:95%;" value="휴양시설"></div></td>
							<td class="ipt">
							      <div class="t_center">
							            <a href="#none"><img src="../img/btn/btn_row_del.png" alt="삭제"></a>
							      </div>
							</td>		
						</tr>
						<tr>
							<td class="ipt"><div><input type="text" style="width:95%;" value="기타"></div></td>
							<td class="ipt"><div class="t_center"><a href="#none"><img src="../img/btn/btn_row_del.png" alt="삭제"></a></div></td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- //업무이력 수정 -->

			<div class="add_newbox mgt10">
				<a href="#none" class="add_newtxt"><span class="ico">사용유형 추가</span></a>
			</div>

		</div>
		<!-- //팝업 컨텐츠 -->

		<!-- 팝업 하단 -->
		<div class="pop_btm">
			<span class="btn_style2_b"><a href="#none">저장</a></span>
			<span class="btn_style2"><a href="#none">취소</a></span>
		</div>
		<!-- //팝업 하단 -->
	</div>

</body>
</html>
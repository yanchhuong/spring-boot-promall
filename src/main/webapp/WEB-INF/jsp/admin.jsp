<!DOCTYPE html>
<head>
<title>Admin Control</title>
<%@include file="fragments/include_admin.jsp"%>
<script>
$(function(){
	// Invoke the plugin
	$('input, textarea').placeholder({customClass:'my-placeholder'});
});
</script>
</head>

<body class="lnb_bg">

<div class="wrap">

	<!-- (공통)header -->
	<div class="cmd_header type2" id="cmd_header"><!-- [D] type1(메인), type2(업무) -->
		<div class="cmd_header_inner">
			<div class="cmd_lft">
				<h1>
					<a href="#none">
						<img src="../img/cmd_header/app_logo_type2.png" alt="" class="logo_resize">
						<span id="" class="">Admin Control</span>
					</a>
				</h1>
				<h3>
					<a href="#none"><span id="" class=""></span></a>
				</h3>
			</div>
		</div>
	</div>
	<!-- //(공통)header -->

	<!-- Container -->
	<div class="container">

		<!-- lnb box -->
		<div class="lnb_box">
			<!-- lnb top -->
			<div class="lnb_top">
				<!-- user view -->
				<div class="user_view">
					<img src="../img/user_img.png" class="img_user">
					<div class="user_r_side">
						<!--  select -->
						<div class="select_com">
							<div class="combo"><span>Admin</span><a href="#none"><img src="../img/btn/combo_link.png" alt="Setting"></a></div>
							<ul style="display:none;">
								<li><a href="#none">국민은행국민은행국민은행</a></li>
								<li><a href="#none">나라상사</a></li>
								<li><a href="#none">삼성중공업</a></li>
							</ul>
						</div>
						<!-- //select -->
						<!-- <div class="select_com">
							<div class="combo_no"><span>웹케시(주)웹케시(주)웹케시(주)</span></div>
						</div> -->
						
					</div>
				</div>
				<!-- //user view -->
			</div>
			<!-- //lnb top -->

			<!-- group wrap -->
			<div class="com_group_wrap">
				<!--company list -->
				<div class="com_list">
					<div class="com_item">
						<div class="group_name ic_lnb_cmd ic_m3_1 on"><a href="#none">Page Control</a></div>
						<ul class="group_name2">
							<li><a href="#none">Business</a></li>
							<li ><a href="#none">Feed back</a></li><!-- 활성화클래스 on -->
							<li ><a href="#none">Message</a></li><!-- 활성화클래스 on -->
							<!--(delete)20170309<li><a href="#none">직원등록</a></li>-->
						</ul>
					</div>
				</div>
				<div class="com_list">
					<div class="com_item">
						<div class="group_name ic_lnb_cmd ic_m3_2"><a href="#none">Page</a></div>
						<ul class="group_name2">
							<li><a href="#none">Menu</a></li>
							<li class="on"><a href="#none">User</a></li><!-- 활성화클래스 on -->
							
						</ul>
					</div>
				</div>
				<!--//company list -->
			</div>
			<!-- //group wrap -->
		</div>
		<!-- //lnb box -->

		<!-- content wrap -->
		<div class="content">

			<div class="content_wrap">

			</div>

		</div>
		<!-- //content wrap -->
	</div>
	<!-- //Container -->
	
	<!-- (공통)footer -->
	<div class="cmd_footer type2" id="cmd_footer"><!-- [D] type1(메인), type2(업무) -->
		<div class="cmd_footer_inner">
			<div class="cmd_lft">
				<p>Copyright(c) <strong>bizplay</strong> All rights reserved.</p>
			</div>
			<div class="cmd_rft">
				<ul>
					<li><a href="#none" id="">개인정보취급방침</a></li>
					<li><a href="#none" id="">이용약관</a></li>
					<li><span>고객센터 : 1566-0000</span></li>
				</ul>
			</div>
		</div>
	</div>
	<!-- //(공통)footer -->
	
</div>
</body>
</html>

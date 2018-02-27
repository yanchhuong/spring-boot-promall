<!DOCTYPE html>
<html lang="ko" class="leaf">
<head>
	<meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>

	<%@include file="fragments/include_page.jsp"%>
	<script type="text/javascript" src="../../js_admin/_loading.js?<%=_localDatetime%>"></script>

<style>

</style> 
 
</head>


<body>

<!-- wrap -->
<div class="wrap ">

	<!-- header -->
   	<div class="header_wrap"><!--<div class="header_wrap search">-->
		<jsp:include page="header_include.jsp"/>
    </div>
	<!-- // header -->

	<!-- container -->
	<div id="container">
		<!-- left_navigation_bar -->
		<div id="gnb" class="on">
			<div class="gnbwrap">
				<div class="gnb_inner">
					<button type="button" class="btn_gnbclose">Close</button>
					<dl class="memview">
						<dt><a href="javascript:void(0);"><img id="sidebar_photo" width="35px" height="35px" src="../../img/bg/bg_nophoto.png" alt="User"></a></dt>
						<dd id="sidebar_name">Please Login</dd>
					</dl>
					<div class="meminfo">
						<a href="javascript:void(0);" class="sidebar_item"><em>Goods</em></a>
						<a href="javascript:void(0);" class="sidebar_signup"><em>Sign Up</em></a>
						<a href="javascript:void(0);" class="sidebar_login"><em>Log In</em></a>
					</div>
					<dl class="category">
						<dt>
							<strong>Category</strong>
							<button type="button" class="btn_upload" id="btn_upload"><span>Post Free here!</span></button>
						</dt>
						<dd id="sidebar_catagory_list">
							<!-- <a href="#none" class="ctg_01">Women's clothing</a>
							<a href="#none" class="ctg_02">Men's clothing</a>
							<a href="#none" class="ctg_03">Fashion goods</a>
							<a href="#none" class="ctg_04">Beauty / Beauty</a>
							<a href="#none" class="ctg_05">Baby / birth</a>
							<a href="#none" class="ctg_06">Sports / Leisure</a>
							<a href="#none" class="ctg_07">Digital / Appliances</a>
							<a href="#none" class="ctg_08">Books / tickets / hobbies / pets</a>
							<a href="#none" class="etc_01">Life / Stationery / Furniture / Food</a>
							<a href="#none" class="etc_02">Vehicles / Motorcycles</a>
							<a href="#none" class="etc_03">More</a>
							<a href="#none" class="etc_04">Stargaz</a> -->
						</dd>
					</dl>
					<div class="btncenter">
						<button id="sidebar_loginout_btn" type="button" class="btn_wt">LogIn</button>
					</div>
				</div>
			</div>
		</div>
		<!-- //left_navigation_bar -->

		<!-- container -->
		<div class="container_inner">

			<!-- sub_category -->
			<div class="sub_cat">
				<div class="hdbox">
					<h3><!-- Women's Clothing --></h3> 
					<a href="javascript:void(0);" class="btn_subCatClose">
						<img src="img/btn/btn_subCatClose.png" alt="Sub Category Close">
					</a>
				</div>
				<div class="ctglist">
					<ul></ul>
					<button class="btn_more"><span class="btn_text">Show More</span><span class="btn_collape"></span></button>
				</div>
			</div>
			<!-- //sub_category -->

			<div class="ctgwrap" style="display:;">
				<!-- list_Sub_Categiry -->
				<div class="listalign">
					<div class="tit_rst"><span>Products </span><strong>1561811</strong><span>Items</span></div>
					<div class="rbox">
						<div class="cstselect">
							<a href="javascript:void(0);" class="selected"><span>Popularity</span></a>
							<ul class="layselected">
								<li><a href="javascript:void(0);">Latest</a></li>
								<li><a href="javascript:void(0);">Bestselling</a></li>
								<li><a href="javascript:void(0);">Low-cost</a></li>
								<li><a href="javascript:void(0);">Highest Priority</a></li>
							</ul>
						</div>
						<!--<button type="button" class="btn_txt">Advanced Search</button>-->
					</div>
				</div>
				<!-- //list_Sub_Categiry -->

				<!-- goods_list -->
				<ul class="goodslist">
					<!-- <li>
						<a href="#none" class="thumb">
							<img src="img/products/prod_01.jpg" alt="" class="thumb loaded">
							<p class="txtinfo">
								<em>Adidas Three Line One Man Line One Man</em>
								<strong class="flt"><span>$ 28</span></strong>
								<strong class="frl"><span>10% OFF</span></strong>
							</p>
						</a>
						<div class="items_foot">
							<div class="flt">
								<ul class="items_foot_opt">
									<li class="ico_local"><a href="#none"></a></li>
									<li class="ico_save"><a href="#none"></a></li>
									<li class="ico_fb"><a href="#none"></a></li>
								</ul>
							</div>
							
							<div class="frl">
								<ul class="items_foot_view">
									<li class="ico_local"><a href="#none">999</a></li>
									<li class="ico_atlist"><a href="#none">999</a></li>
								</ul>
							</div>
						</div>
					</li> -->
<!--			
					<li>
						<a href="#none" class="thumb">
							<span class="badge icon_plus">Plus</span>
							<img src="img/products/prod_02.jpg" alt="" class="thumb loaded">
							<p class="txtinfo">
								<em>Rouge Pit Long Angora</em>
								<strong class="flt"><span>$ 31.00</span></strong>
								<strong class="frl"><span>10% OFF</span></strong>
							</p>
						</a>
						<div class="items_foot">
							<div class="flt">
								<ul class="items_foot_opt">
									<li class="ico_local"><a href="#none"></a></li>
									<li class="ico_save"><a href="#none"></a></li>
									<li class="ico_fb"><a href="#none"></a></li>
								</ul>
							</div>
							
							<div class="frl">
								<ul class="items_foot_view">
									<li class="ico_local"><a href="#none">999</a></li>
									<li class="ico_atlist"><a href="#none">999</a></li>
								</ul>
							</div>
						</div>
					</li>
					<li>
						<a href="#none" class="thumb">
							<img src="img/products/prod_03.jpg" alt="" class="thumb loaded">
							<p class="txtinfo">
								<em>Mmolded horn nt</em>
								<strong class="flt"><span>$ 28</span></strong>
							</p>
						</a>
						<div class="items_foot">
							<div class="flt">
								<ul class="items_foot_opt">
									<li class="ico_local"><a href="#none"></a></li>
									<li class="ico_save"><a href="#none"></a></li>
									<li class="ico_fb"><a href="#none"></a></li>
								</ul>
							</div>
							
							<div class="frl">
								<ul class="items_foot_view">
									<li class="ico_local"><a href="#none">999</a></li>
									<li class="ico_atlist"><a href="#none">999</a></li>
								</ul>
							</div>
						</div>
					</li>
					<li>
						<a href="#none" class="thumb">
							<span class="badge icon_plus">Plus</span>
							<img src="img/products/prod_04.jpg" alt="" class="thumb loaded">
							<p class="txtinfo">
								<em>Pajama set top and bottom</em>
								<strong class="flt"><span>$ 28</span></strong>
							</p>
						</a>
						<div class="items_foot">
							<div class="flt">
								<ul class="items_foot_opt">
									<li class="ico_local"><a href="#none"></a></li>
									<li class="ico_save"><a href="#none"></a></li>
									<li class="ico_fb"><a href="#none"></a></li>
								</ul>
							</div>
							
							<div class="frl">
								<ul class="items_foot_view">
									<li class="ico_local"><a href="#none">999</a></li>
									<li class="ico_atlist"><a href="#none">999</a></li>
								</ul>
							</div>
						</div>
					</li>
					<li>
						<a href="#none" class="thumb">
							<span class="badge icon_plus">Plus</span>
							<img src="img/products/prod_05.jpg" alt="" class="thumb loaded">
							<p class="txtinfo">
								<em>Key brushed training + vest</em>
								<strong class="flt"><span>$ 52,200</span></strong>
							</p>
						</a>
						<div class="items_foot">
							<div class="flt">
								<ul class="items_foot_opt">
									<li class="ico_local"><a href="#none"></a></li>
									<li class="ico_save"><a href="#none"></a></li>
									<li class="ico_fb"><a href="#none"></a></li>
								</ul>
							</div>
							
							<div class="frl">
								<ul class="items_foot_view">
									<li class="ico_local"><a href="#none">999</a></li>
									<li class="ico_atlist"><a href="#none">999</a></li>
								</ul>
							</div>
						</div>
					</li>
					<li>
						<a href="#none" class="thumb">
							<span class="badge icon_plus">Plus</span>
							<img src="img/products/prod_06.jpg" alt="" class="thumb loaded">
							<p class="txtinfo">
								<em>Redferm Mustang</em>
								<strong class="flt"><span>$ 98.00</span></strong>
							</p>
						</a>
						<div class="items_foot">
							<div class="flt">
								<ul class="items_foot_opt">
									<li class="ico_local"><a href="#none"></a></li>
									<li class="ico_save"><a href="#none"></a></li>
									<li class="ico_fb"><a href="#none"></a></li>
								</ul>
							</div>
							
							<div class="frl">
								<ul class="items_foot_view">
									<li class="ico_local"><a href="#none">999</a></li>
									<li class="ico_atlist"><a href="#none">999</a></li>
								</ul>
							</div>
						</div>
					</li>
					<li>
						<a href="#none" class="thumb">
							<span class="badge icon_plus">Plus</span>
							<img src="img/products/prod_07.jpg" alt="" class="thumb loaded">
							<p class="txtinfo">
								<em>Band Stocking Set Items</em>
								<strong class="flt"><span>$ 3.00</span></strong>
							</p>
						</a>
						<div class="items_foot">
							<div class="flt">
								<ul class="items_foot_opt">
									<li class="ico_local"><a href="#none"></a></li>
									<li class="ico_save"><a href="#none"></a></li>
									<li class="ico_fb"><a href="#none"></a></li>
								</ul>
							</div>
							
							<div class="frl">
								<ul class="items_foot_view">
									<li class="ico_local"><a href="#none">999</a></li>
									<li class="ico_atlist"><a href="#none">999</a></li>
								</ul>
							</div>
						</div>
					</li>
					<li>
						<a href="#none" class="thumb">
							<span class="badge icon_plus">Plus</span>
						<img src="img/products/prod_08.jpg" alt="" class="thumb loaded">
							<p class="txtinfo">
								<em>Itmisha gold chain tweed jacket</em>
								<strong class="flt"><span>$ 22</span></strong>
							</p>
						</a>
						<div class="items_foot">
							<div class="flt">
								<ul class="items_foot_opt">
									<li class="ico_local"><a href="#none"></a></li>
									<li class="ico_save"><a href="#none"></a></li>
									<li class="ico_fb"><a href="#none"></a></li>
								</ul>
							</div>
							
							<div class="frl">
								<ul class="items_foot_view">
									<li class="ico_local"><a href="#none">999</a></li>
									<li class="ico_atlist"><a href="#none">999</a></li>
								</ul>
							</div>
						</div>
					</li>
					<li>
						<a href="#none" class="thumb">
							<span class="badge icon_plus">Plus</span>
						<img src="img/products/prod_09.jpg" alt="" class="thumb loaded">
							<p class="txtinfo">
								<em>Dress for Christmas</em>
								<strong class="flt"><span>$ 20</span></strong>
							</p>
						</a>
						<div class="items_foot">
							<div class="flt">
								<ul class="items_foot_opt">
									<li class="ico_local"><a href="#none"></a></li>
									<li class="ico_save"><a href="#none"></a></li>
									<li class="ico_fb"><a href="#none"></a></li>
								</ul>
							</div>
							
							<div class="frl">
								<ul class="items_foot_view">
									<li class="ico_local"><a href="#none">999</a></li>
									<li class="ico_atlist"><a href="#none">999</a></li>
								</ul>
							</div>
						</div>
					</li>
					<li>
						<a href="#none" class="thumb">
							<span class="badge icon_plus">Plus</span>
							<img src="img/products/prod_10.jpg" alt="" class="thumb loaded">
							<p class="txtinfo">
								<em>Default Goliath Panola</em>
								<strong class="flt"><span>$ 12.00</span></strong>
							</p>
						</a>
						<div class="items_foot">
							<div class="flt">
								<ul class="items_foot_opt">
									<li class="ico_local"><a href="#none"></a></li>
									<li class="ico_save"><a href="#none"></a></li>
									<li class="ico_fb"><a href="#none"></a></li>
								</ul>
							</div>
							
							<div class="frl">
								<ul class="items_foot_view">
									<li class="ico_local"><a href="#none">999</a></li>
									<li class="ico_atlist"><a href="#none">999</a></li>
								</ul>
							</div>
						</div>
					</li>
					<li>
						<a href="#none" class="thumb">
							<span class="badge icon_plus">Plus</span>
							<img src="img/products/prod_11.jpg" alt="" class="thumb loaded">
							<p class="txtinfo">
								<em>Breathing 3 Senjyu</em>
								<strong class="flt"><span>$ 9.50</span></strong>
							</p>
						</a>
						<div class="items_foot">
							<div class="flt">
								<ul class="items_foot_opt">
									<li class="ico_local"><a href="#none"></a></li>
									<li class="ico_save"><a href="#none"></a></li>
									<li class="ico_fb"><a href="#none"></a></li>
								</ul>
							</div>

							<div class="frl">
								<ul class="items_foot_view">
									<li class="ico_local"><a href="#none">999</a></li>
									<li class="ico_atlist"><a href="#none">999</a></li>
								</ul>
							</div>
						</div>
					</li>
					<li>
						<a href="#none" class="thumb">
							<span class="badge icon_plus">Plus</span>
							<img src="img/products/prod_12.jpg" alt="" class="thumb loaded">
							<p class="txtinfo">
								<em>Mulpon Corset Bra </em>
								<strong class="flt"><span>$ 12.00</span></strong>
							</p>
						</a>
						<div class="items_foot">
							<div class="flt">
								<ul class="items_foot_opt">
									<li class="ico_local"><a href="#none"></a></li>
									<li class="ico_save"><a href="#none"></a></li>
									<li class="ico_fb"><a href="#none"></a></li>
								</ul>
							</div>
							
							<div class="frl">
								<ul class="items_foot_view">
									<li class="ico_local"><a href="#none">999</a></li>
									<li class="ico_atlist"><a href="#none">999</a></li>
								</ul>
							</div>
						</div>
					</li>

-->
				</ul>
				<!-- //goods_list -->				
			</div>

			<div class="pagination paginationjs-theme-blue" style="cursor: default;padding-left: 43%;">
				<!-- <a href="#none" class="pag_prev">next</a>
				<span>
					<a href="#none" class="on">1</a>
					<a href="#none">2</a>
					<a href="#none">3</a>
					<a href="#none">4</a>
					<a href="#none">5</a>
				</span>
				<a href="#none" class="pag_next">next</a> -->
			</div>

		</div>
		<!-- //container -->
    </div>
	<!-- // container -->
	
	

	<!-- footer -->
	<footer>
		<div class="footerwrap">
			<div class="botmenu">
				<a href="javascript:void(0);">Information</a>
				<a href="javascript:void(0);">Terms and Conditions</a>
				<a href="javascript:void(0);">Privacy Policy</a>
				<a href="javascript:void(0);">Terms of Service</a>
			</div>
			<address>
				<p>
					<strong>Address </strong> No.197, Khan Russey Keo, Phnom Penh Cambodia,<br>
					<span><strong>TEL / FAX</strong> 02-598-8240 / 02-598-8241 </span>
				</p>
			</address>
		</div>
		<div class="txtmsg">
			Please Do Not Hesitate to Contact US.<br>
			Copyright â Online Shopping. All rights reserved
		</div>
	</footer>
	<!-- //footer -->

</div>
<!-- //wrap -->

<script>
/* (function () {
    $(document).ready(function () {
        $('#navbox-trigger').click(function () {
            return $('#navigation-bar').toggleClass('navbox-open');
        });
         return $(document).on('click', function (e) {
            var $target;
            $target = $(e.target);
            if (!$target.closest('.navbox').length && !$target.closest('#navbox-trigger').length) {
                return $('#navigation-bar').removeClass('navbox-open');
            }
        });
    });
}.call(this)); */
</script>
</body>

<%@include file="fragments/html_iframes.jsp"%>
</html>
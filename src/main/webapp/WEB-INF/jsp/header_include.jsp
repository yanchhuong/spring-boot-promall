<!DOCTYPE html>
<html lang="ko" class="leaf">
<head> 
    <script type="text/javascript" src="/js/header_include.js"></script>
<style>
@media(max-width:1280px){
.menu_header{float:left;position: fixed;top: 97px;right:17px;width: 50px;height: 180px;background-color: #ffffff;background-size: 32px 32px;font-size: 0;border-radius: 20px;border: 1px solid #34b188;}
.header_pro{position: relative;left: 20%;top: 35%;margin-top: 10px;}
.header_inner .btn_alrim {position: relative;left: -7px;top: 29%;padding-bottom: 12px;}
.chat_list{position: relative;left: -23%;top:26px;}
.more_option_list{position: relative;left: -21%;top: 90px;}
.setting_list{position: relative;left: -26%;top: 60px;}
.wrapper_notification {display: none;position: absolute;left: -312.5%;width: 622%;margin-top: -34px;-webkit-transform: translate(-50%, 0);transform: translate(-50%, 0);}
.container-notf .left-notf {float: left;width: 100%;height: 100%;border: 1px solid #e6e6e6;background-color: #fff;}
.container-notf {position: relative;/* top: 5%; */left: 50%;width: 100%;min-width: 100px;/* height: 75%; */background-color: #fff;-webkit-transform: translate(-50%, -50%);transform: translate(-50%, -50%);}
.wrapper {display: none;position: absolute;left: -274.5%;width: 580%;margin-top: 19px;-webkit-transform: translate(-50%, 0);transform: translate(-50%, 0);}
.wrapper > .container-chat .left {float: left;width: 100%;height: 100%;border: 1px solid #e6e6e6;background-color: #fff;}
.wrapper > .container-chat {position: relative;/* top: 5%; */left: 50%;width: 100%;min-width: 100px;/* height: 75%; */background-color: #fff;-webkit-transform: translate(-50%, -50%);transform: translate(-50%, -50%);}
.navigation-bar {position: absolute;left: -133%;top: 12px;}
.wrap_setting {left: -223px;position: absolute;top: 75%;}

.sellerwrap > .seller_inner{width:100%;}
.seller_other_opt{width:100%;}
.sellerwrap .seller_info{padding: 5px 15px 10px 300px;}
.header_inner h1.logo{top:15px;}
#pro_test{position:absolute;top:6%;margin-left:-450%;}
#pro_test ul li{background-color:lightgray;}
}

@media(max-width:955px){
.menu_header{float:left;position: fixed;top: 97px;right:17px;width: 50px;height: 180px;background-color: #ffffff;background-size: 32px 32px;font-size: 0;border-radius: 20px;border: 1px solid #34b188;}
.header_pro{position: relative;left: 20%;top: 35%;margin-top: 10px;}
.header_inner .btn_alrim {position: relative;left: 1%;top: 29%;padding-bottom: 12px;}
.chat_list{position: relative;left: -23%;top:-18px;}
.more_option_list{position: relative;left: -21%;top: 47px;}
.setting_list{position: relative;left: -26%;top: 15px;}
.wrapper_notification {display: none;position: absolute;left: -312.5%;width: 622%;margin-top: -34px;-webkit-transform: translate(-50%, 0);transform: translate(-50%, 0);}
.container-notf .left-notf {float: left;width: 100%;height: 100%;border: 1px solid #e6e6e6;background-color: #fff;}
.container-notf {position: relative;/* top: 5%; */left: 50%;width: 100%;min-width: 100px;/* height: 75%; */background-color: #fff;-webkit-transform: translate(-50%, -50%);transform: translate(-50%, -50%);}
.wrapper {display: none;position: absolute;left: -274.5%;width: 580%;margin-top: 19px;-webkit-transform: translate(-50%, 0);transform: translate(-50%, 0);}
.wrapper > .container-chat .left {float: left;width: 100%;height: 100%;border: 1px solid #e6e6e6;background-color: #fff;}
.wrapper > .container-chat {position: relative;/* top: 5%; */left: 50%;width: 100%;min-width: 100px;/* height: 75%; */background-color: #fff;-webkit-transform: translate(-50%, -50%);transform: translate(-50%, -50%);}
.navigation-bar {position: absolute;left: -133%;top: 12px;}
.wrap_setting {left: -223px;position: absolute;top: 75%;}
}
	
	
#pro_test{ left:28.5%; }
</style>
</head>

<body>

		<!-- header_inner top-bar-wrap -->
        <div class="header_inner">
			<!-- logo -->
            <h1 class="logo" id="logo"><a href="javascript:"><img src="img/bg/bg_logo.png" alt="logo"></a></h1>
			<!-- //logo -->

			<!-- button_menu_mobile responsive -->
            <button type="button" class="btn_gnb">GNB</button>
			<!-- //button_menu_mobile -->

			<!-- search_box_header -->
            <div id="searchview" onclick="schView()">
				<input type="text" class="txt" value="Product name, area name, @ shop name, initial search" disabled="">
            </div>            
			<!-- //search_box_header -->
			
			<div class="row menu_header" style="float:right;">
				<ul>
					<li>
						<div class="header_pro">
							<a href="javascript:void(0);"><img id="header_profile" style="border-radius:50%;" width="30px" height="30px" src="../../img/bg/bg_nophoto.png" alt="User"></a>
						</div>
						<dropdown class="wrap_setting" id="pro_test">
						  <ul class="animate">
						    <li class="animate">Profile<i class="fa fa-suitcase float-right"></i>
						    	<table style="width: 200px;margin-left: -20px;margin-top:11px">
						    	    <tbody>
								    	<tr><td style="padding: 7px 0 10px 38px;">Panda's Onli...(default)</td></tr>
									</tbody>
								</table>
						    </li>
						    <li class="animate" id="login_sess">Login<i class="fa fa-sign-in float-right"></i></li>
						    <li class="animate" id="logout_sess" style="color:#d95353;">Logout<i class="fa fa-sign-out float-right"></i></li>
						  </ul>
						</dropdown>
					</li>
					<li>
						<!-- aleim -->
				        <button type="button" id="dropdown-toggle" class="btn_alrim new"><span>Notice</span></button>
						<!--<button type="button" class="btn_alrim new" onclick="gotalklist()"><span>Notice</span></button>-->
						<!-- //aleim -->
						
						<div class="wrapper_notification" id="notification-menu">
						    <div class="container-notf">
						        <div class="left-notf">
						            <ul class="info-notf">
						                <li data-chat="person1" style="padding: 6px;background-color: #34b188;color: #fff;font-weight: bold;">
						                    <span class="name">Notification (3)</span>
						                    <span class="time" style="margin-left: 28%;">Make all read</span>
						                </li>
						                <li class="person" data-chat="person5">
						                    <img src="https://s16.postimg.org/ete1l89z5/img5.jpg" alt="" />
						                    <span class="name">Michael Jordan</span>
						                    <span class="time">2:09 PM</span>
						                    <span class="preview">Wasup for the third time like is you bling bitch</span>
						                </li>
						                <li class="person" data-chat="person2">
						                    <img src="https://s3.postimg.org/yf86x7z1r/img2.jpg" alt="" />
						                    <span class="name">Dog Woofson</span>
						                    <span class="time">1:44 PM</span>
						                    <span class="preview">I've forgotten how it felt before</span>
						                </li>
						                <li class="person" data-chat="person3">
						                    <img src="https://s3.postimg.org/h9q4sm433/img3.jpg" alt="" />
						                    <span class="name">Louis CK</span>
						                    <span class="time">2:09 PM</span>
						                    <span class="preview">But we’re probably gonna need a new carpet.</span>
						                </li>
						                <li class="person" data-chat="person4">
						                    <img src="https://s3.postimg.org/quect8isv/img4.jpg" alt="" />
						                    <span class="name">Bo Jackson</span>
						                    <span class="time">2:09 PM</span>
						                    <span class="preview">It’s not that bad...</span>
						                </li>
						                <li class="person" data-chat="person5">
						                    <img src="https://s16.postimg.org/ete1l89z5/img5.jpg" alt="" />
						                    <span class="name">Michael Jordan</span>
						                    <span class="time">2:09 PM</span>
						                    <span class="preview">Wasup for the third time like is you bling bitch</span>
						                </li>
						                <li class="person" data-chat="person3">
						                    <img src="https://s3.postimg.org/h9q4sm433/img3.jpg" alt="" />
						                    <span class="name">Louis CK</span>
						                    <span class="time">2:09 PM</span>
						                    <span class="preview">But we’re probably gonna need a new carpet.</span>
						                </li>
						                <li class="" data-chat="person6" style="padding:7px;background:#34b188;">
						                    <a href="javascript:">
						                    	<span class="name" style="margin-left: 45%;color: #fff;">View All</span>
						                    </a>
										</li>
						            </ul>
						        </div>
						    </div>
						</div>
					</li>
					<li>
						<div class="chat_list">
							<!-- <div class="col-sm-3"> -->
								<div class="ico_chat">
									<a href="javascript:void(0);"><img src="img/bg/chat.png" width="22px" height="22px" /></a>
								</div>
							<!-- </div> -->
							
							<div class="wrapper">
							    <div class="container-chat">
							        <div class="left">
							            <ul class="people">
							                <li class="person human" data-chat="person1">
							                    <img src="https://s13.postimg.org/ih41k9tqr/img1.jpg" alt="" />
							                    <span class="name">Thomas Bangalter</span>
							                    <span class="time">2:09 PM</span>
							                    <span class="preview">I was wondering...</span>
							                </li>
							                <li class="person human" data-chat="person2">
							                    <img src="https://s3.postimg.org/yf86x7z1r/img2.jpg" alt="" />
							                    <span class="name">Dog Woofson</span>
							                    <span class="time">1:44 PM</span>
							                    <span class="preview">I've forgotten how it felt before</span>
							                </li>
							                <li class="person human" data-chat="person3">
							                    <img src="https://s3.postimg.org/h9q4sm433/img3.jpg" alt="" />
							                    <span class="name">Louis CK</span>
							                    <span class="time">2:09 PM</span>
							                    <span class="preview">But we’re probably gonna need a new carpet.</span>
							                </li>
							                <li class="person human" data-chat="person4">
							                    <img src="https://s3.postimg.org/quect8isv/img4.jpg" alt="" />
							                    <span class="name">Bo Jackson</span>
							                    <span class="time">2:09 PM</span>
							                    <span class="preview">It’s not that bad...</span>
							                </li>
							                <li class="person human" data-chat="person5">
							                    <img src="https://s16.postimg.org/ete1l89z5/img5.jpg" alt="" />
							                    <span class="name">Michael Jordan</span>
							                    <span class="time">2:09 PM</span>
							                    <span class="preview">Wasup for the third time like is you bling bitch</span>
							                </li>
							                <li class="person human" data-chat="person6">
							                    <img src="https://s30.postimg.org/kwi7e42rh/img6.jpg" alt="" />
							                    <span class="name">Drake</span>
							                    <span class="time">2:09 PM</span>
							                    <span class="preview">howdoyoudoaspace</span>
							                </li>
							            </ul>
							        </div>
							    </div>
							</div>
						</div>
					</li>
					
					<li>
						<div class="more_option_list">
							<div id="navbox-trigger" style="position:absolute;right:3%;top:15.505050505050px;">
								<a href="javascript:void(0);"><img src="img/bg/big-and-small-dots.png" width="25px" height="25px" /></a>
							</div>							
							<div id="navigation-bar" class="navigation-bar">
							  <div class="navbox" style="display:none;">
							    <div class="navbox-tiles">
							    	<a href="#" class="tile"><div class="icon"><i class="fa fa-home"></i></div><span class="title">Home</span></a>
							    	<a href="#" class="tile"><div class="icon"><i class="fa fa-calendar"></i></div><span class="title">Calendar</span></a>
							    	<a href="#" class="tile"><div class="icon"><i class="fa fa-envelope-o"></i></div><span class="title">Email</span></a>
							    	<a href="#" class="tile"><div class="icon"><i class="fa fa-file-image-o"></i></div><span class="title">Photos</span></a>
							      	<a href="#" class="tile"><div class="icon"><i class="fa fa-cloud"></i></div><span class="title">Weather</span></a>
							      	<a href="#" class="tile"><div class="icon"><i class="fa fa-file-movie-o"></i></div><span class="title">Movies</span></a>
								</div>
							  </div>
							</div>
						</div>
					</li>
					<li style="display:none;">
						<div class="setting_list">
							<!-- <div class="col-sm-3"> -->
								<div id="setting_trigger" style="position:absolute;right:0;top:15.505050505050px;">
									<a href="javascript:void(0);"><img src="img/bg/settings-cogwheel.png" width="24px" height="23px" /></a>
								</div>
							<!-- </div> -->
						</div>
						
						<dropdown class="wrap_setting">
						  <ul class="animate">
						    <li class="animate">Profile<i class="fa fa-suitcase float-right"></i>
						    	<table style="width: 200px;margin-left: -20px;margin-top:11px">
						    	    <tbody>
								    	<tr><td style="padding: 7px 0 10px 38px;">Panda's Onli...(default)</td></tr>
									</tbody>
								</table>
						    </li>
						    <li class="animate" id="login_sess">Login<i class="fa fa-sign-in float-right"></i></li>
						    <li class="animate" id="logout_sess" style="color:#d95353;">Logout<i class="fa fa-sign-out float-right"></i></li>
						  </ul>
						</dropdown>
					</li>
				</ul>
				<div class="chatbox-holder"></div>
			</div>
        </div>
		<!-- //header_inner -->
</body>
</html>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%
	DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	Date date = new Date();
	String _localDatetime = dateFormat.format(date);
%> 

<!DOCTYPE html>
<html lang="en" >

<head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 	<!-- default header name is X-CSRF-TOKEN -->
 	<meta name="_csrf" content="${_csrf.token}"/>
 	<meta name="_csrf_header" content="${_csrf.headerName}"/>
  	<title>Direct Messaging</title>
  
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
    <link href='https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600,700,300' rel='stylesheet' type='text/css'>

	<script src="https://use.typekit.net/hoy3lrg.js"></script>
	<script>try{Typekit.load({ async: true });}catch(e){}</script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
  	<link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.2/css/font-awesome.min.css'>
  	
    <link rel="stylesheet" type="text/css" href="../css/realtimechat.css" media="all"> 
    <script src="/js-lib/jquery-2.1.0.min.js"></script>
    <script src="/js-lib/sockjs-0.3.4.js"></script>
    <script src="/js-lib/stomp.js"></script>
    <script type="text/javascript" src="../../js_admin/wehrm.string.js?<%=_localDatetime%>"></script>
    
    <!-- popup -->
    <script type="text/javascript" src="../../js_admin/wehrm.popup.js?<%=_localDatetime%>"></script> 
    <script type="text/javascript" src="../../js_admin/jquery.bpopup.min.js?<%=_localDatetime%>"></script>

<style>
	.group {
	  border-radius: 9999px;
	  box-shadow: 0 2px 8px rgba(0, 0, 0, .3);
	  height: 150px;
	  margin: 0 20px;
	  overflow: hidden;
	  width: 150px;
	}
	
	.group > div {
	  background-color: #333;
	  background-position: 50% 50%;
	  background-size: cover;
	  float: left;
	  height: 100%;
	  width: 50%;
	}
	
	.group > div:first-child:last-child {
	  width: 100%;
	}
	
	.group > div:last-child:nth-child(3),
	.group > div:nth-child(2):nth-last-child(2)
	{
	  height: 50%;
	}

</style>
</head>

<body>

	<div id="frame">
		<div id="sidepanel">
			<div id="profile">
				<div class="wrap">
					<img id="profile-img" src="http://emilcarlsson.se/assets/mikeross.png" class="online" alt="" />
					<p>Mike Ross</p>
					<i class="fa fa-chevron-down expand-button" aria-hidden="true"></i>
					<div id="status-options">
						<ul>
							<li id="status-online" class="active"><span class="status-circle"></span> <p>Online</p></li>
							<li id="status-away"><span class="status-circle"></span> <p>Away</p></li>
							<li id="status-busy"><span class="status-circle"></span> <p>Busy</p></li>
							<li id="status-offline"><span class="status-circle"></span> <p>Offline</p></li>
						</ul>
					</div>
					<div id="expanded">
						<label for="twitter"><i class="fa fa-facebook fa-fw" aria-hidden="true"></i></label>
						<input name="twitter" type="text" value="mikeross" />
						<label for="twitter"><i class="fa fa-twitter fa-fw" aria-hidden="true"></i></label>
						<input name="twitter" type="text" value="ross81" />
						<label for="twitter"><i class="fa fa-instagram fa-fw" aria-hidden="true"></i></label>
						<input name="twitter" type="text" value="mike.ross" />
					</div>
				</div>
			</div>
			<div id="search">
				<label for=""><i class="fa fa-search" aria-hidden="true"></i></label>
				<input type="text" placeholder="Search contacts..." />
			</div>
			<div id="contacts">
				<ul>
					<li class="contact">
						<div class="wrap">
							<span class="contact-status online"></span>
							<img src="http://emilcarlsson.se/assets/louislitt.png" alt="" />
							<div class="meta">
								<div class="name" style="float:left;">Louis Litt</div>
								<div style="float:right;">9:30 PM<i style="margin-left: 8px;" class="fa fa-envelope" aria-hidden="true"></i></div>
								<p class="preview" style="margin-top: 20px;">You just got LITT up, Mike.</p>
							</div>
						</div>
					</li>
					<li class="contact active">
						<div class="wrap">
							<span class="contact-status busy"></span>
							<img src="http://emilcarlsson.se/assets/harveyspecter.png" alt="" />
							<div class="meta">
								<div class="name" style="float:left;">Harvey Specter</div>
								<div style="float:right;">7:30 PM</div>
								<p class="preview" style="margin-top: 20px;">You just got LITT up, Mike.</p>
							</div>
						</div>
					</li>
					<li class="contact">
						<div class="wrap">
							<span class="contact-status away"></span>
							<img src="http://emilcarlsson.se/assets/rachelzane.png" alt="" />
							<div class="meta">
								<div class="name" style="float:left;">Rachel Zane</div>
								<div style="float:right;">2:45 PM<i style="margin-left: 8px;" class="fa fa-envelope" aria-hidden="true"></i></div>
								<p class="preview" style="margin-top: 20px;">You just got LITT up, Mike.</p>
							</div>
						</div>
					</li>
					<li class="contact">
						<div class="wrap">
							<span class="contact-status online"></span>
							<img src="http://emilcarlsson.se/assets/donnapaulsen.png" alt="" />
							<div class="meta">
								<div class="name" style="float:left;">Donna Paulsen</div>
								<div style="float:right;">12:30 PM</div>
								<p class="preview" style="margin-top: 20px;">You just got LITT up, Mike.</p>
							</div>
						</div>
					</li>
					<li class="contact">
						<div class="wrap">
							<span class="contact-status busy"></span>
							<img src="http://emilcarlsson.se/assets/jessicapearson.png" alt="" />
							<div class="meta">
								<div class="name" style="float:left;">Jessica Pearson</div>
								<div style="float:right;">2:30 PM</div>
								<p class="preview" style="margin-top: 20px;">You just got LITT up, Mike.</p>
							</div>
						</div>
					</li>
					<li class="contact">
						<div class="wrap">
							<span class="contact-status"></span>
							<img src="http://emilcarlsson.se/assets/haroldgunderson.png" alt="" />
							<div class="meta">
								<div class="name" style="float:left;">Harold Gunderson</div>
								<div style="float:right;">12:30 PM</div>
								<p class="preview" style="margin-top: 20px;">You just got LITT up, Mike.</p>
							</div>
						</div>
					</li>
					<li class="contact">
						<div class="wrap">
							<span class="contact-status"></span>
							<img src="http://emilcarlsson.se/assets/danielhardman.png" alt="" />
							<div class="meta">
								<div class="name" style="float:left;">Daniel Hardman</div>
								<div style="float:right;">12:30 PM</div>
								<p class="preview" style="margin-top: 20px;">You just got LITT up, Mike.</p>
							</div>
						</div>
					</li>
					<li class="contact">
						<div class="wrap">
							<span class="contact-status busy"></span>
							<img src="http://emilcarlsson.se/assets/katrinabennett.png" alt="" />
							<div class="meta">
								<div class="name" style="float:left;">Katrina Bennett</div>
								<div style="float:right;">12:30 PM</div>
								<p class="preview" style="margin-top: 20px;">You just got LITT up, Mike.</p>
							</div>
						</div>
					</li>
					<li class="contact">
						<div class="wrap">
							<span class="contact-status"></span>
							<img src="http://emilcarlsson.se/assets/charlesforstman.png" alt="" />
							<div class="meta">
								<div class="name" style="float:left;">Charles Forstman</div>
								<div style="float:right;">12:30 PM</div>
								<p class="preview" style="margin-top: 20px;">You just got LITT up, Mike.</p>
							</div>
						</div>
					</li>
					<li class="contact">
						<div class="wrap">
							<span class="contact-status"></span>
							<img src="http://emilcarlsson.se/assets/jonathansidwell.png" alt="" />
							<div class="meta">
								<div class="name" style="float:left;">Jonathan Sidwell</div>
								<div style="float:right;">12:30 PM</div>
								<p class="preview" style="margin-top: 20px;">You just got LITT up, Mike.</p>
							</div>
						</div>
					</li>
				</ul>
			</div>
			<div id="bottom-bar">
				<button id="addcontact" class="activeChat"><i class="fa fa-user-plus fa-fw" aria-hidden="true"></i> <span>Add contact</span></button>
				<button id="btnchat_list"><i class="fa fa-paper-plane fa-fw" aria-hidden="true"></i> <span>Chat</span></button>
			</div>
		</div>
		<div class="content">
			<div class="contact-profile" id="reciever" data-id="">
				<img width="40px" height="40px" src="img/bg/bg_logo.png" alt="" />
				<p>Discuss to get your beautiful products...!</p>
				<div class="social-media">
					<i class="fa fa-facebook" aria-hidden="true"></i>
					<i class="fa fa-twitter" aria-hidden="true"></i>
					 <i class="fa fa-instagram" aria-hidden="true"></i>
				</div>
			</div>
			<div class="messages">
				<ul>
					<li style="margin-top: 13%;">
						<table><tbody>
					        	<tr>
						            <td>
						            	<div class="group">
											<div style="background-image:url(http://i.pravatar.cc/300?1)"></div>
											<div style="background-image:url(http://i.pravatar.cc/300?4)"></div>
											<div style="background-image:url(http://i.pravatar.cc/300?2)"></div>
										</div>
						            </td>
						        	<td>
						            	<div class="group">
											<div style="background-image:url(http://i.pravatar.cc/300?9)"></div>
											<div style="background-image:url(http://i.pravatar.cc/300?7)"></div>
											<div style="background-image:url(http://i.pravatar.cc/300?5)"></div>
										</div>
						            </td>
						            <td>
						            	<div class="group">
											<div style="background-image:url(http://i.pravatar.cc/300?3)"></div>
											<div style="background-image:url(http://i.pravatar.cc/300?8)"></div>
											<div style="background-image:url(http://i.pravatar.cc/300?6)"></div>
										</div>
						            </td>
					            </tr>
					    </tbody></table>
					</li>
					<li style="margin-top: 13%;">
						<table><tbody>
					        	<tr>
						            <td>
						            	<div class="group">
											<div style="background-image:url(http://i.pravatar.cc/300?4)"></div>
											<div style="background-image:url(http://i.pravatar.cc/300?5)"></div>
											<div style="background-image:url(http://i.pravatar.cc/300?6)"></div>
										</div>
						            </td>
						        	<td>
						            	<div class="group">
											<div style="background-image:url(http://i.pravatar.cc/300?1)"></div>
											<div style="background-image:url(http://i.pravatar.cc/300?5)"></div>
											<div style="background-image:url(http://i.pravatar.cc/300?4)"></div>
										</div>
						            </td>
						            <td>
						            	<div class="group">
											<div style="background-image:url(http://i.pravatar.cc/300?7)"></div>
											<div style="background-image:url(http://i.pravatar.cc/300?5)"></div>
											<div style="background-image:url(http://i.pravatar.cc/300?9)"></div>
										</div>
						            </td>
					            </tr>
					    </tbody></table>
					</li>
				</ul>
				
			</div>
			<div class="message-input" style="display:none;">
				<div class="wrap">
				<input type="text" placeholder="Write your message..." id="txtCHAT" />
				<i class="fa fa-paperclip attachment" aria-hidden="true"></i>
				<button class="submit send"><i class="fa fa-paper-plane" aria-hidden="true"></i></button>
				</div>
			</div>
		</div>
	</div>
 <!-- <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
 -->
<script  src="/js/realtimechat.js"></script>




</body>

</html>

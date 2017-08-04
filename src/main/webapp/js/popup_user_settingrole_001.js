var popup_user_settingrole_001={};
$(document).ready(function(){
	popup_user_settingrole_001.listBusinesRole(input);
	
	$(document).on("click",".add_newtxt",function(){
		 var html=' <tr class="ipt_row">'
          +'	<td><div><input type="text" style="width:92%;font-size:11" placeholder="title" id="title"></div></td>  '
          +'	<td>																		  '
          +'		<div>																	  '
          +'			<input type="text"  value="" id="sdate"> <a href="#none" height: 20px;><img src="../img/ico/ico_calendar.png" alt="달력"></a> ~'
          +'			<input type="text"  value="" id="edate"> <a href="#none" height: 20px;><img src="../img/ico/ico_calendar.png" alt="달력"></a>	  '
          +'		</div>																	  '
          +'	</td>																		  '
          +'	<td>																		  '
          +'		<div>																	  '
          +'			<input type="text"  value="" id="regdate"> <a href="#none"><img src="../img/ico/ico_calendar.png" alt="달력"></a>	  '
          +'		</div>																	  '
          +'	</td>																		  '
          +'	<td><div><span class="btn_style3"><a href="#none">연차 생성하기</a></span></div></td>	'							
          +'	<td><div>'
		  +'   			<span class="txt_combo btn_combo_down" id="cbRole" style="width:100px;">'
		  +'   			<a href="#none" class="txt"> Choose</a>    '
		  +'   			<div class="ly_txtcombo" id="txtRole" style="display: none;">'    
		  +'  			<ul id="cbStat">'		 
		  +'   			</ul>'  
          +'      	</div>'
          +'  </td>'
          +'  <td class="ipt"><div class="t_right"><a href="javascript:" id="btAdd"><img src="../img/btn/caution_icon2.png" alt="삭제"></a></div></td>'
          +' </tr>';
		$('tbody').append(html);

		var d = new Date();
		var month = d.getMonth()+1;
		var day = d.getDate();

		var output = d.getFullYear() + '-' +
		    (month<10 ? '0' : '') + month + '-' +
		    (day<10 ? '0' : '') + day;
		
		
		$("#sdate").val(output);
		$("#edate").val(output);
		$("#regdate").val(output);
		
		$(".add_newbox").hide();
		ifrMainResize(1);
		popup_user_settingrole_001.listRole();

	});
	//sdate
	$(document).on("focus", "#sdate",function(){
		$(this).datepicker({dateFormat: 'yy-mm-dd'});
	});
	//edate
	$(document).on("focus", "#edate",function(){
		$(this).datepicker({dateFormat: 'yy-mm-dd'});
	});
	//sdate
	$(document).on("focus", "#regdate",function(){
		$(this).datepicker({dateFormat: 'yy-mm-dd'});
	});

	//show status change
	$(document).on("click","#cbRole",function(e){
		$(this).find(".ly_txtcombo").fadeToggle();
	}).mouseleave(function(){
		$(this).find(".ly_txtcombo").fadeOut();
	});
	//update status after change
	$(document).on("click","#txtRole ul#cbStat li",function(e){
		$(this).parents('ul').find('li').removeClass('on');
		$(this).addClass("on");
		title = $.trim($(this).find("a").text());
		$(this).parents('#cbRole').find('.txt').html(title);
	});
	//add role 
	$(document).on("click","#btAdd",function(e){
		popup_user_settingrole_001.addRoleUser();
		popup_user_settingrole_001.listBusinesRole(input);
	});
	
	//remove role 
	$(document).on("click","#tbRemove",function(e){
		popup_user_settingrole_001.removeRoleUser(this);
		popup_user_settingrole_001.listBusinesRole(input);
	});
	//close popup
	$(document).on("click",".btn_popclose",function(){
		wehrm.popup.closePopup("popup_user_settingrole_001");
	});
	
});

popup_user_settingrole_001.listBusinesRole = function(data){
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken = $("meta[name='_csrf']").attr("content");
	var input={};
	    input["usercd"] = data.usercd;
	$.ajax({
    	type   : 'POST',
	    url    : "/roles/list_roles_for_user",
	    data   : JSON.stringify(input),
	    cache: false,
        dataType: 'json',
    	contentType: 'application/json',
        async: false,
        beforeSend: function(xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
	})
    .done(function(dat) {
    	if(dat.OUT_REC.length > 0 ){
			$.map(dat.OUT_REC,function(val){
				if(val["role"]=="ROLE_USER"){
					val["title"]="Free Unlimitted User";
				}
				val["sdate"]= wehrm.ui.formateDate(null2void(val.sdate));
				val["edate"]= wehrm.ui.formateDate(null2void(val.edate));
				val["regdate"]= wehrm.ui.formateDate(null2void(val.regdate));
				return val;
			});
			$("#Result_List").html($("#tbl_result_template").tmpl(dat.OUT_REC));
		}
		else{
			$("#Result_List").html('<tr><td class="t_center noline" colspan="5"><div>내용이 없습니다.</div></td></tr>');
		}
    	ifrMainResize();
    	$(".add_newbox").show();
    });
};

popup_user_settingrole_001.listRole=function(){
	$.ajax({
    	type   : 'GET',
	    url    : "/users/list_roles",
	    cache  : true
	})
    .done(function(dat) {
    	var tbody = $("#cbStat");
    	var html = '';
    	$.each(dat.OUT_REC, function(i,v){
    		 html+='<li><a href="javascript:">'+v.role +'</a></li>';
    	});
    	if(dat.OUT_REC.length > 3){
    		$(".ly_txtcombo").css({top: -80,width: 157});
    	}
    	tbody.html(html);
	})
};

popup_user_settingrole_001.addRoleUser=function(){
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken = $("meta[name='_csrf']").attr("content");
	var input={};
	    input["title"]   = $(".ipt_row").find("#title").val();
	    input["sdate"]   = $(".ipt_row").find("#sdate").val().replace(/[-]/gi,"");
	    input["edate"]   = $(".ipt_row").find("#edate").val().replace(/[-]/gi,"");
/*	    input["regdate"] = $(".ipt_row").find("#regdate").val();*/
	    input["role"]    = $(".ipt_row").find(".txt").text();
	    input["usercd"]  = $("#usercd").val();
	    input["username"]= $("#email").val();
	    console.log(input);
	 $.ajax({
    	type   : 'POST',
	    url    : '/roles/add_business_role_for_user',
	    data   : JSON.stringify(input),
	    cache: true,
        dataType: 'json',
    	contentType: 'application/json',
        async: false,
        beforeSend: function(xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
	})
    .done(function(dat) {
    	
    })
  
};
popup_user_settingrole_001.removeRoleUser=function(dat){
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken = $("meta[name='_csrf']").attr("content");
	var input={};
	    input["usercd"]  = $("#usercd").val();
	    input["username"]= $("#email").val();
	    input["role"]    = $(dat).parents("tr").find("#role").text();
	 $.ajax({
    	type   : 'POST',
	    url    : '/roles/remove_business_role',
	    data   : JSON.stringify(input),
	    cache: true,
        dataType: 'json',
    	contentType: 'application/json',
        async: false,
        beforeSend: function(xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
	})
    .done(function(dat) {
	    console.log(dat);
    })
  
};
function ifrMainResize(dat) {
	if(1==dat){
		var h = parent.$("#popup_user_settingrole_001_iframe").contents().find(".pop_wrap").height();
		parent.$("#popup_user_settingrole_001_iframe").height(h + 22 + "px");
	}else{
		var h = parent.$("#popup_user_settingrole_001_iframe").contents().find(".pop_wrap").height();
		parent.$("#popup_user_settingrole_001_iframe").height(h + 33 + "px");
	}
	
} 


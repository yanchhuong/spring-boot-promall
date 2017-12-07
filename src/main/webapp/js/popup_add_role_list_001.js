var popup_add_role_list_001={};
$(document).ready(function(){
	popup_add_role_list_001.listRole();
	$(document).on("click",".add_newbox",function(){
	 	 var html = '<tr>';
		 html+='<td class="ipt"><div><input type="text" style="width:95%;" id="role" value=""></div></td>';
		 html+='<td class="ipt"><div class="t_center"><a href="#none" id="tbAdd"><img src="../img/btn/btn_s_cnl.png" alt="삭제"></a></div></td>';
	     html+='</tr>';
		$('tbody').append(html);
		$(".add_newbox").hide();
		ifrMainResize(1);
	});

	//Cancel
	$(document).on("click","#tbAdd",function(){
		 popup_add_role_list_001.listRole();
		
	});
	
	//remove
	$(document).on("click","#tbRemove",function(){
		popup_add_role_list_001.removeRoleList(this);
	});
	// Enter Save
	$(document).on("keydown","#role",function(e){
		  if (e.keyCode == 13) {
			  popup_add_role_list_001.addRoleList();
		  }
	});
	
	//Close Iframe
	$(document).on("click",".btn_popclose",function(){
		wehrm.popup.closePopup("popup_add_role_list_001");
	});
});
popup_add_role_list_001.listRole=function(){
	$.ajax({
    	type   : 'GET',
	    url    : "/users/list_roles",
	    cache  : true
	})
    .done(function(dat) {
    	if(dat.OUT_REC.length > 0 ){
			$.map(dat.OUT_REC,function(val){
				return val;
			});
			$("tbody").html($("#tbl_result_template").tmpl(dat.OUT_REC));
		}else{
			$("tbody").html('<tr><td class="t_center noline" colspan="9"><div>내용이 없습니다.</div></td></tr>');
		}
    	$(".add_newbox").show();
    	ifrMainResize();
	})
};

popup_add_role_list_001.addRoleList=function(){
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken = $("meta[name='_csrf']").attr("content");
	var input={};
	    input["role"]   = "ROLE_"+ $("#role").val().toUpperCase();
	    console.log(input);
	 $.ajax({
    	type   : 'POST',
	    url    : '/users/add_roles_list',
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
    popup_add_role_list_001.listRole();
};

popup_add_role_list_001.removeRoleList=function(dat){
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken = $("meta[name='_csrf']").attr("content");
	var input={};
	    input["role"]   = $(dat).parents("tr").find("input").val().toUpperCase();
	    console.log(input);
	 $.ajax({
    	type   : 'POST',
	    url    : '/users/remove_roles_list',
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
    popup_add_role_list_001.listRole();
};
function ifrMainResize(dat) {
	 var h = 0;
	 h = parent.$("#popup_add_role_list_001_iframe").contents().find(".pop_wrap").height();
	 if(1==dat){
		 h = h + 10;
		parent.$("#popup_add_role_list_001_iframe").height(h+"px");
	}else{
		h = h + 21;
		console.log(h);
		parent.$("#popup_add_role_list_001_iframe").height(h+"px");
	}
	
} 


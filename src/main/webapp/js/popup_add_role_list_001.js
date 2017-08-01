var popup_add_role_list_001={};
$(document).ready(function(){
	popup_add_role_list_001.listRole();
	$(document).on("click",".add_newbox",function(){
	 	 var html = '<tr>';
		 html+='<td class="ipt"><div><input type="text" style="width:95%;" value=""></div></td>';
		 html+='<td class="ipt"><div class="t_center"><a href="#none"><img src="../img/btn/btn_row_del.png" alt="삭제"></a></div></td>';
	     html+='</tr>';
		$('tbody').append(html);
	});
});
popup_add_role_list_001.listRole=function(){
	$.ajax({
    	type   : 'GET',
	    url    : "/users/listroles",
	    cache  : true
	})
    .done(function(dat) {
    	var tbody = $(".tbl_result tbody");
    	var html = '<tr>';
    	$.each(dat.OUT_REC, function(i,v){
    		 html+='<td class="ipt"><div><input type="text" style="width:95%;" value="'+v.role+'"></div></td>';
    		 html+='<td class="ipt"><div class="t_center"><a href="#none"><img src="../img/btn/btn_row_del.png" alt="삭제"></a></div></td>';
    	     html+='</tr>';
    	});
    	
    	tbody.html(html);
    	//console.log(dat);
    	
	})
};


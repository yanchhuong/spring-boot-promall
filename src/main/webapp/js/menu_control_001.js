var _this;
var menu_control_001={};
$(document).ready(function(){
	
	
	menu_control_001.listMenu();
	
	$("#cmenu").click(function(){
		$("#ifr_content").attr("src","cmenu");
	});
	
})

menu_control_001.listMenu=function(){
	
	$.ajax({
    	type   : 'GET',
	    url    : "/category/list",
	    cache  : true,
	    
	})
    .done(function(dat) {
    	var tbody = $(".list_table tbody");
    	var html = "";
    	tbody.html('');
    	$.each(dat, function(i,v){
    		console.log(v);
    			html += '<tr>';
    			html += ' <td class="t_center brd_r">									    ';
    			html += '		<div class="ly_po">											';
    			html += '			<a class="btn_folder_plus">추가</a>						';
				html += '			<!-- layer popup -->									';
				html += '			<div class="tree_layerpop" style="display:none;">		';
    			html += '				<ul>												';
    			html += '					<li><a>동일 수준의 부서로 추가</a></li>					';
    			html += '					<li><a>하위 부서로 추가</a></li>			     		';
    			html += '				</ul>											    ';
    			html += '			</div>												    ';
    			html += '			<!-- //layer popup -->								 	';
    			html += '		</div>												     	';
    			html += '	</td>														     ';
    			
    			html += '	<td class="t_left"><div class="dp1"><span class="ico_treefolder">1</span> <span class="txt">웹케시(주)</span></div></td>';
    			html += '	<td class="t_right"><a class="txt_d">134</a></td>						';
    			html += '	<td class="t_left"><a class="txt_d off">미지정</a></td>					 ';
    			html += '	<td class="t_left"><a class="txt_d off">미지정</a></td>					 ';
    			
    			html += '	<td class="t_center">												     ';
    			html += '		<div style="position:relative;">									 ';
    			html += '			<a class="btn_folderset" title="설정"></a>					     ';
    			html += '			<!-- layer -->											         ';
    			html += '			<div class="depset_layer" style="display:none;">				 ';
    			html += '				<ul>											             ';
    			html += '					<li class="first"><a>부서명 수정</a></li>					 ';
    			html += '					<li><a>부서 이동</a></li>								     ';
    			html += '					<li><a>부서 삭제</a></li>								     ';
    			html += '				</ul>											             ';
    			html += '			</div>												             ';
    			html += '			<!-- //layer -->										         ';
    			html += '		</div>													             ';
    			html += '	</td>                                                                    ';
    			html += '	</tr>                                                                    ';
    			tbody.html(html);
      })
   })
	
}
	
	
	
	

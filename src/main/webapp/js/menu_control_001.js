var _this;
var menu_control_001={};
var edithtml='';
$(document).ready(function(){
	menu_control_001.listMenu();
	console.log(menu_control_001.editTrHtml());
	
	$('.list_table tbody').delegate('td','click',function(){	
		$(this).append(menu_control_001.editTrHtml());
	});
	
});

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
    			html += '			<a class="btn_folder_plus" id="btnAddMenu">Add</a>						';
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
    			
    			html += '	<td class="t_left"><div class="dp'+dat.lvl+'" id="editMenu"><span class="ico_treefolder">1</span> <span class="txt">'+ v.nm_eng+'</span></div></td>';
    			html += '	<td class="t_right"><a class="txt_d">'+v.parent_cd+'</a></td>						';
    			html += '	<td class="t_left"><a class="txt_d off">'+v.lvl+'</a></td>					 ';
    			html += '	<td class="t_left"><a class="txt_d off">'+v.usercd+'</a></td>					 ';
    			
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
	
};

menu_control_001.editTrHtml = function(input){
	
     edithtml+=' <tr>                                                                 ';                                                                                
     edithtml+=' <td class="t_center brd_r">								          ';
     edithtml+='	<div class="ly_po">									              ';
     edithtml+='		<a class="btn_folder_plus">추가</a>						      ';
     edithtml+='		<!-- layer popup -->								          ';
     edithtml+='		<div class="tree_layerpop" style="display:none;">		      ';
     edithtml+='			<ul>									                  ';
     edithtml+='				<li><a>동일 수준의 부서로 추가</a></li>					      ';
     edithtml+='				<li><a>하위 부서로 추가</a></li>					          ';
     edithtml+='			</ul>									                  ';
     edithtml+='		</div>										                  ';
     edithtml+='		<!-- //layer popup -->								          ';
     edithtml+='	</div>											                  ';
     edithtml+=' </td>											                      ';
     edithtml+=' <td class="t_left">									              ';
     edithtml+='     <div class="dp2">									              ';
     edithtml+='            <span class="ico_treefolder">2</span> 				      ';
     edithtml+='            <input type="text" style="width:150px;">&nbsp;		      ';
     edithtml+='		   <a><img src="../img/btn/btn_s_ok.png" alt="저장"></a>		   ';
     edithtml+='		   <a style="margin-left:2px;"><img src="../img/btn/btn_s_cnl.png" alt="취소"></a> ';
     edithtml+='	     </div>										                    ';
     edithtml+='	 </td>											                    ';
     edithtml+=' <td class="t_right"><a class="txt_d">134</a></td>					    ';
     edithtml+=' <td class="t_left"><a class="txt_d off">미지정</a></td>			        ';
     edithtml+=' <td class="t_left"><a class="txt_d off">미지정</a></td>			        ';
     edithtml+=' <td class="t_center">									                ';
     edithtml+='	<div style="position:relative;">							        ';
     edithtml+='		<a class="btn_folderset" title="설정"></a>					    ';
     edithtml+='		<!-- layer -->									                ';
     edithtml+='		<div class="depset_layer" style="display:none;">				';
     edithtml+='			<ul>									                    ';
     edithtml+='				<li class="first"><a>부서명 수정</a></li>				    ';
     edithtml+='				<li><a>부서 이동</a></li>						            ';
     edithtml+='				<li><a>부서 삭제</a></li>						            ';
     edithtml+='			</ul>									                    ';
     edithtml+='		</div>										                    ';
     edithtml+='		<!-- //layer -->								                ';
     edithtml+='	 </div>											                    ';
     edithtml+=' </td>											                        ';
     edithtml+=' </tr>                                                                   ';  
		
	return edithtml;
};


	
	
	
	

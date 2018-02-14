
$(document).ready(function(){
	$('body').prepend('<div class="modal"></div>');
	$("<style> .modal {display:none;position:fixed;z-index:1000;top:0;left:0;height:100%;width:100%;background: rgba( 255, 255, 255, .8 ) url('http://i.stack.imgur.com/FhHRx.gif') 50% 50% no-repeat;}</style>").appendTo("head");
	$("<style type='text/css'> body.loading {overflow: hidden;}</style>").appendTo("head");
	$("<style type='text/css'> body.loading .modal {display: block;}</style>").appendTo("head");
});

function _loadingStart(){
	$("body").addClass("loading");
}

function _loadingStop(){
	$("body").removeClass("loading");
}

var popup_user_settingrole_001={};

$(document).ready(function(){
	popup_user_settingrole_001.listBusinesRole();
	
})

popup_user_settingrole_001.listBusinesRole=function(){
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken = $("meta[name='_csrf']").attr("content");
	var input={};
	    input["username"]= "mengly@123.com";
	    input["usercd"] = "";
	$.ajax({
    	type   : 'POST',
	    url    : "/roles/rolelist",
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
    	
    });
};
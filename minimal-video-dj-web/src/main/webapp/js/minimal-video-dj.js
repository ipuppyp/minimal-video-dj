function startVideo(fileName, id, url) {
	$("button").removeClass("btn-danger");
	$("button").removeClass("btn-warning");
	$("#" + id).addClass("btn-warning");
	$.ajax({
		url : url, success: function(result){
			$("#" + id).addClass("btn-danger");
			$("#" + id + "_message").html(result.message);
			setTimeout(function(){
				$("#" + id + "_message").html("");
			}, 2000);
			
	    }});
}

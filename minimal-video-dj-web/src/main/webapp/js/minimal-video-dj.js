$(document).ready(function() {
    $.ajax({
        url: "rest/get-video-file-list"
    }).then(function(data) {
    	var videoFileList = [];
    	for (var i in data.videoFileList) {
    		videoFileList.push({"videoFile" : data.videoFileList[i]});
    	}
    	$("#tbody").jPut({
    	    jsonData:videoFileList,
    	    name:"tbody_template",
    	});
    	
    });
});


function startVideo(fileName, id, url) {
	$("button").removeClass("btn-danger");
	$("button").removeClass("btn-warning");
	$("#" + id).addClass("btn-warning");
	$.ajax({
		url : url, success: function(result){
			$("#" + id).removeClass("btn-warning");
			$("#" + id).removeClass("btn-primary");
			$("#" + id).addClass("btn-success");
			$("#" + id + "_message").html(result.message);
			setTimeout(function(){
				$("#" + id + "_message").html("");
				$("#" + id).removeClass("btn-success");
				$("#" + id).addClass("btn-primary");
			}, 4000);
			
	    }});
}

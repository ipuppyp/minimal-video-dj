$(document).ready(function() {
	showVideoFileList();
	showFooter();
});


function showVideoFileList(){$.ajax({
	url : gOptions.getVideoFileListUrl
}).then(function(data) {
	var videoFileList = [];
	for ( var i in data.videoFileList) {
		videoFileList.push({
			"videoFile" : data.videoFileList[i]
		});
	}
	jPut.videoFileList.data = videoFileList;
});}

function startVideo(fileName, id) {
	$("button").removeClass("btn-danger");
	$("button").removeClass("btn-warning");
	$("#" + id).addClass("btn-warning");
	$.ajax({
		url : gOptions.startVideoUrl + "?file=" + fileName,
		success : function(result) {
			$("#" + id).removeClass("btn-warning");
			$("#" + id).removeClass("btn-primary");
			$("#" + id).addClass("btn-success");
			$("#" + id + "_message").html(result.message);
			setTimeout(function() {
				$("#" + id + "_message").html("");
				$("#" + id).removeClass("btn-success");
				$("#" + id).addClass("btn-primary");
			}, 4000);
		}
	});
}

function showFooter(){
	$.ajax({
		url : gOptions.getInfoUrl,
		success : function(result) {
			$("#footer").html("IP ADDRESS: " + result);			
		}
	});
}

// HELPER METHODS //
function normalizeFileName(fileName) {
	return fileName.replace(".", "_");
}

function filenameWithoutExtension(fileName) {
	var result;
	var index = fileName.lastIndexOf(".");
	if (index === -1) {
		result = fileName;
	} else {
		result = fileName.substr(0, index);
	}
	return result.toUpperCase();
}

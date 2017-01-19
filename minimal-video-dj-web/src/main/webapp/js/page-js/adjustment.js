function adjust(adjustment) {
	$.ajax({
		url : gOptions.adjustUrl + "?adjustment=" + adjustment,
		success : function(result) {
			$("#result").html(result.message);
			setTimeout(function() {
				$("#result").html("");
			}, 4000);
		}
	});
}

function stopVideo() {
	$.ajax({
		url : gOptions.stopVideoUrl,
		success : function(result) {
			$("#result").html(result.message);
			setTimeout(function() {
				$("#result").html("");
			}, 4000);
		}
	});
}

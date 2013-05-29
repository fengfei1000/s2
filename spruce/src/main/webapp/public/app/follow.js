$(function() {
	$("#follow").click(follow);
});
function follow() {
	var toid = $(this).attr("toid");
	var isfollow = $(this).attr("isfollow");
	
	if (!isfollow) { 
		// alert(toid);
		$.post("/follow/" + toid, function(data) {
			alert(data.toSource());
		}, "json");
	}
}
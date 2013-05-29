$(function() {
	$("#camera").click(function() {
		$("#forms").append(tmpl("camera_tml", {
			index : 0
		}));
	});
	$("#lens").click(function() {
		$("#forms").append(tmpl("lens_tml", {
			index : 0
		}));
	});
	$("#tripod").click(function() {
		$("#forms").append(tmpl("tripod_tml", {
			index : 0
		}));
	});
	$("#filter").click(function() {
		$("#forms").append(tmpl("filter_tml", {
			index : 0
		}));
	});
});
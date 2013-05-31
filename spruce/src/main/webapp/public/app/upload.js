$.blueimp.fileupload.prototype.processActions.duplicate = function(data,
		options) {

	data.files.push(data.files[data.index]);

	return data;
};
$(function() {
	'use strict';

	// Change this to the location of your server-side upload handler:
	var uploadButton = $(
			'<button class="btn btn-primary" id="start" type="button">	<i class="icon-upload icon-white"></i>Save</button>')
			.prop('disabled', true).text('Processing...').on(
					'click',
					function() {
						var $this = $(this), data = $this.data();
						$this.off('click').text('Abort').on('click',
								function() {
									$this.remove();
									data.abort();
								});
						data.submit().always(function() {
							$this.remove();
						});
					});
	$('#fileupload').fileupload({
		url : url,
		dataType : 'json',
		autoUpload : false,
		acceptFileTypes : /(\.|\/)(jpe?g)$/i,
		maxFileSize : 512000, // 5 MB
		loadImageMaxFileSize : 5120000, // 15MB
		disableImageResize : false,
		previewMaxWidth : 400,
		previewMaxHeight : 400,
		previewCrop : false,
		previewAsCanvas : true,
		imageCrop : false,
		processQueue : [ {
			action : 'loadImage',
			fileTypes : /^image\/(gif|jpeg|png)$/,
			maxFileSize : 5120000
		}, {
			action : 'resizeImage',
			maxWidth : 900,
			maxHeight : 600,
			minWidth : 600,
			minHeight : 400,
			crop : false
		}, {
			action : 'saveImage'
		}, {
			action : 'setImage'
		} ]
	}).bind('fileuploadadd', function(e, data) {
		$('#progress .bar').css('width', '0%');
		$("#preview > img").remove();
		var file = data.files[0];
		toForm(file);
		data.context = $('#files');
		$("#content").css("display", "block");
		$("#name").text(file.name);
		$("#size").text(formatFileSize(file.size));
		// $("#start").data(data);
		$("#start").find('button').remove();
		$("#start").append(uploadButton.clone(true).data(data));

		// $.each(data.files, function(index, file) {
		// var node = $('<p/>').append(
		// $('<span />').text(file.name));
		// if (!index) {
		// node.append('<br>').append(
		// uploadButton.clone(true).data(data));
		// }
		//
		// node.appendTo(data.context);
		// });
		window.loadImage(data.files[0], function(img) {
			$("#preview").append(img);
		}, {
			maxWidth : 400
		});

	}).bind('fileuploadprogress', function(e, data) {
		// alert(data.files.length);
	}).bind('fileuploadprogressall', function(e, data) {
		// alert(data.files.length);
		var progress = parseInt(data.loaded / data.total * 100, 10);
		$('#progress .bar').css('width', progress + '%');
	}).bind(
			'fileuploadprocessalways',
			function(e, data) {

				// var index = data.index, file = data.files[index], node =
				// $(data.context
				// .children()[index]);
				// if (file.preview) {
				// node.prepend('<br>').prepend(file.preview);
				// }
				// if (file.error) {
				// node.append('<br>').append(file.error);
				// }
				// // if (index + 1 === data.files.length) {
				// data.context.find('button').text('Upload').prop(
				// 'disabled', !!data.files.error);
				// }
				$("#start").find('button').text('Upload').prop('disabled',
						!!data.files.error);
			}).bind('fileuploadprogressall', function(e, data) {
		var progress = parseInt(data.loaded / data.total * 100, 10);
		$('#progress .bar').css('width', progress + '%');
	}).bind('fileuploaddone', function(e, data) {
		// alert(data.result);
		// $.each(data.result.files, function(index, file) {
		// var link = $('<a>').attr('target', '_blank').prop(
		// 'href', file.url);
		// $(data.context.children()[index]).wrap(link);
		// });
	}).bind('fileuploadfail', function(e, data) {
		$.each(data.result.files, function(index, file) {
			var error = $('<span/>').text(file.error);
			$(data.context.children()[index]).append('<br>').append(error);
		});
	}).bind('fileuploadprogress', function(e, data) {
		// Log the current bitrate for this upload:
		console.log(data.bitrate);
	}).bind('fileuploadsubmit', function(e, data) {
		// The example input, doesn't have to be part of the upload form:
		var formData = $('#fileuploadForm').serializeArray();
		data.formData = formData;
		// if (!data.formData.example) {
		// input.focus();
		// return false;
		// }
	});
});
var formatFileSize = function(bytes) {
	if (typeof bytes !== 'number') {
		return '';
	}
	if (bytes >= 1000000000) {
		return (bytes / 1000000000).toFixed(2) + ' GB';
	}
	if (bytes >= 1000000) {
		return (bytes / 1000000).toFixed(2) + ' MB';
	}
	return (bytes / 1000).toFixed(2) + ' KB';
};

function toForm(file) {
	ExifUtils.readExif(file, function(data) {
		if (data) {
			// alert(data.toSource());
			var tempIndex = 1;
			var index = file.name.lastIndexOf(".");
			$("#title" + tempIndex).val(file.name.substring(0, index));

			//
			$("#taken_at" + tempIndex).val(data.DateTimeOriginal2);
			$("#taken_at_display" + tempIndex).val(data.DateTimeOriginal2);
			$("#make" + tempIndex).val(data.Make);
			$("#camera" + tempIndex).val(data.Model);
			$("#focus" + tempIndex).val(data.FocalLength);
			$("#iso" + tempIndex).val(data.ISOSpeedRatings);
			$("#lens" + tempIndex).val($.trim(data.LensModel));
			$("#shutter" + tempIndex).val(data.ExposureTime2);
			$("#aperture" + tempIndex).val(data.FNumber);
			$("#ev" + tempIndex).val(data.ev);

			//
			$("#desc" + tempIndex).val("");
			$("#tags" + tempIndex).val("");
			$("#category1" + tempIndex).val(0);
		}
	});

}

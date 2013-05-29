var ExifUtils = {};
ExifUtils.takenDate = function(a) {
	var b = a.split(" ");
	return b[0] = b[0].replace(/:/gi, "-"), b[0] + " " + b[1]
};
ExifUtils.takenDateToHuman = function(a) {
	date = new Date(a.replace(/-/gi, " "));
	switch (date.getMonth()) {
	case 0:
		m = "January";
		break;
	case 1:
		m = "February";
		break;
	case 2:
		m = "March";
		break;
	case 3:
		m = "April";
		break;
	case 4:
		m = "May";
		break;
	case 5:
		m = "June";
		break;
	case 6:
		m = "July";
		break;
	case 7:
		m = "August";
		break;
	case 8:
		m = "September";
		break;
	case 9:
		m = "October";
		break;
	case 10:
		m = "November";
		break;
	case 11:
		m = "December"
	}
	return m + " " + date.getDate() + ", " + date.getFullYear()
};
ExifUtils.readExif = function(file,callback) {
	var data = {};
	data.formData={};
	if (window.FileReader && FileReader.prototype.readAsBinaryString) {

		var fileReader = new FileReader();
		fileReader.onload = function(e) {
			var contents = e.target.result;
			// alert(contents);
			var oFile = new BinaryFile(contents);
			var a = EXIF.readFromBinaryFile(oFile);
			if (a) {
				var g = "";
				typeof a.ExposureTime != "undefined"
					&& (a.ExposureTime < 1 ? g = "1/" + 1
							/ a.ExposureTime : g = Math
							.floor(a.ExposureTime));
				var h = "", j = "";
				typeof a.DateTimeOriginal != "undefined"
					? h = a.DateTimeOriginal
					: typeof a.DateTimeDigitized != "undefined"
							&& (h = a.DateTimeDigitized), h != ""
					&& (h = ExifUtils.takenDate(h), j = ExifUtils
							.takenDateToHuman(h));
				var ev="";
				var eb=a.ExposureBias ;
				if(typeof eb != "undefined"){	
					ev= eb < 0 ?   "-":"+";
					if(eb==0 ){
						ev="0";
					}else if( eb >= 1 || eb <= -1){
						ev += Math.floor(eb);	
					}else  {
						ev+= "1/" +Math.abs(1/ eb);
					}
				}

			
				a.MakerNote=[];
				a.UserComment=[];
				data = a;
				data.ExposureTime2 = g;
				data.DateTimeOriginal2 = h;
				data.DateTimeOriginal3 = j;
				data.ev=ev;
			
				

				data.formData = {
					DateTimeOriginal : a.DateTimeOriginal2,
					Model : a.Model,
					Make : a.Make,
					FocalLength : a.FocalLength,
					iso : a.ISOSpeedRatings,
					FNumber : a.FNumber,
					ExposureTime : a.ExposureTime2,
					LensModel : a.LensModel,
					ExposureBias:a.ExposureBias,
					ev:a.ev
				};
				callback(data);
			}
		}
		fileReader.readAsBinaryString(file);
		// fileReader.readAsArrayBuffer (file);
	 
	} 
	
 
}

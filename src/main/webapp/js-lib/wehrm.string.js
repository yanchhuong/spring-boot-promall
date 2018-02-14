var wehrm;
if(!wehrm) wehrm={};	

if(!wehrm.string) {
	wehrm.string={};
	
	//@@ Add Camma per 3digits
	wehrm.string.numberWithCommas = function(str) {
		if(str == null || str == "") return "";
		return str.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
		//return str.toFixed(2).replace(/(\d)(?=(\d{3})+(?!\d))/g, ",");
	};
	
	wehrm.string.removeAllCommas = function(str){
		return str.replace(/,/g, "");
	};

	wehrm.string.removeAllString = function(str) {
		return str.replace(/[^0-9\.]/g, '');
	};
		
	//@@ zero pad
	wehrm.string.zeroPad = function(num, places) {
		var zero = places - num.toString().length + 1;
	  	return Array(+(zero > 0 && zero)).join("0.") + num;
	};
	
	//@@ lpad
	wehrm.string.lPad = function(str,z){
		z = z || '0';
		str = str + '';
		return str.length >= 4 ? str : new Array(4 - str.length + 1).join(z) + str;
		
	};
	
	//@@ format Date
	wehrm.string.formatDate = function(str, format){
		if(str == "" || str == undefined) str = "";
		if(format == undefined) format = "-";
		if(str.length == 10)
			str = str.replace(/-/g,"");
		return str.substr(0,8).replace(/(\d{4})(\d{2})(\d{2})/, "$1"+format+"$2"+format+"$3");
	};
	
	//@@ normal format Date
	wehrm.string.formatDateNormal = function(str, format){
		if(str == "" || str == undefined) str = "";
		if(format == undefined) format = "-";
		if(str.length == 10)
			str = str.replace(/-/g,"");
		return str.substr(0,8).replace(/(\d{2})(\d{2})(\d{4})/, "$1"+format+"$2"+format+"$3");
	};
	
	//@@ format DateTime
	wehrm.string.formatDateTime = function(str, format){
		if(str == "" || str == undefined) str = "";
		if(format == undefined) format = "-";
		
		return str.replace(/(\d{4})(\d{2})(\d{2})(\d{2})(\d{2})(\d{2})/, "$1"+format+"$2"+format+"$3 $4:$5:$6");
	};
	
	//@@ format Time
	wehrm.string.formatTime = function(str, format){
		if(str == "" || str == undefined) str = "";
		if(format == undefined) format = ":";
		return str.substr(8,12).replace(/(\d{2})(\d{2})(\d{2})/, "$1"+format+"$2"+format+"$3");
	};
	
	//@@ format Time 6 digit
	wehrm.string.formatTimeSixDigit = function(str, format){
		if(str == "" || str == undefined) str = "";
		if(format == undefined) format = ":";
		return str.substr(0,4).replace(/(\d{2})(\d{2})/, "$1"+format+"$2");
	};
	wehrm.string.cnts_Null2Void = function(value, str){
		if(value==null ||  value=="" || typeof(value)== undefined || value=="null" || value==undefined || value=="undefined") {
			return (typeof(str)=="string")?str:"";
		} else {
			return value;
		}
	};
	
	//@@ format number to korean name
	wehrm.string.viewKoreanNum = function(num) { 
	     var hanA = new Array("","일","이","삼","사","오","육","칠","팔","구","십");
	     var danA = new Array("","십","백","천","","십","백","천","","십","백","천","","십","백","천");
	     var result = "";
		  for(var i=0; i<num.length; i++) {  
		   str = "";
		   han = hanA[num.charAt(num.length-(i+1))];
		   if(han != "")
		    str += han+danA[i];
		   if(i == 4) str += "만";
		   if(i == 8) str += "억";
		   if(i == 12) str += "조";
		   result = str + result;
		  };
		  if(num != 0){
		   result = result + "원";
		   return result;
		  }
	};
	
	//@@ return period of time
	wehrm.string.periodTime = function(regdate) {
		var postedate = wehrm.string.formatDateTime(regdate);
		var oneYear = wehrm.string.formatDate(regdate);
		var date1 = new Date(postedate);
		var date2 = new Date();
		var result = '';
			
//		console.log('difference in ms', date1 - date2);

		// Use Math.abs() so the order of the dates can be ignored and you won't
		// end up with negative numbers when date1 is before date2.
//		console.log('difference in ms abs', Math.abs(date1 - date2));
//		console.log('difference in seconds', Math.abs(date1 - date2) / 1000);

		var diffInSeconds = Math.abs(date1 - date2) / 1000;
		var days = Math.floor(diffInSeconds / 60 / 60 / 24);
		var hours = Math.floor(diffInSeconds / 60 / 60 % 24);
		var minutes = Math.floor(diffInSeconds / 60 % 60);
		var seconds = Math.floor(diffInSeconds % 60);
		var weeks = Math.floor(diffInSeconds / 60 / 60 / 24 / 7);
		var months = Math.floor(diffInSeconds / 60 / 60 / 24 / 30);
		var milliseconds = Math.round((diffInSeconds - Math.floor(diffInSeconds)) * 1000);

//		console.log(months);
//		console.log(weeks);
//		console.log('days', days);
//		console.log('hours', ('0' + hours).slice(-2));
//		console.log('minutes', ('0' + minutes).slice(-2));
//		console.log('seconds', ('0' + seconds).slice(-2));
//		console.log('milliseconds', ('00' + milliseconds).slice(-3));

		if(months > 11){
			return oneYear;
		}else if(months > 0){
			result = months+" months";
		}
		else if(weeks > 0){
			result = weeks+" weeks";
		}else if(days > 0){
			result = days+" days";
		}else if(hours > 0){
			result = hours+" hours";
		}else if(minutes > 0){
			result = minutes+" minutes";
		}else if(seconds > 0){
			result = seconds+" seconds";
		}
		result += " ago";
		return result;
}

		
	
	//Format RR_NO
	wehrm.string.formatRrNo = function(num,star) {
		var result = num;
		result = result.replace(/-/g,"");
		result = result.replace(/\s/g,"");
		if(!star) star = false;
		if(result.length == 13){
			if(star){
				result = result.substring(0,result.length-6).replace(/(\d{6})(\d{1})/, "<span class='no1'>$1</span><span class='bar'>-</span><span class='no2'>$2******</span>");
			}else{
				result = result.replace(/(\d{6})(\d{7})/, "<span class='no1'>$1</span><span class='bar'>-</span><span class='no2'>$2</span>");
			}
		}
		return result;
	}
	
	wehrm.string.formatPhoneNo = function(str,format) {
		if(str == "" || str == undefined) str = "";
		if(format == undefined) format = "-";
		str = str.replace(/[^0-9\.]/g, '');
		if(str.length == 11){
			return str.replace(/(\d{3})(\d{4})(\d{4})/, "$1"+format+"$2"+format+"$3");
		}else if(str.length == 10){
			return str.replace(/(\d{2})(\d{4})(\d{4})/, "$1"+format+"$2"+format+"$3");
		}else{
			return str;
		} 
	};

	wehrm.string.formatEmpSsn = function(str, format) {
		if(str == "" || str == undefined) str = "";
		if(format == undefined) format = "-";
		str = str.replace(/[^0-9\.]/g, '');
		if(str.length == 13){
			return str.replace(/(\d{6})(\d{7})/, "$1"+format+"$2");
		}else{
			return str;
		} 
	};
	
	wehrm.string.formatBsnnNo = function(str) {
		if(str == "" || str == undefined) str = "";
		var format = "-";
		str = str.replace(/[^0-9\.]/g, '');
		if(str.length == 10){
			return str.replace(/(\d{3})(\d{2})(\d{5})/, "$1"+format+"$2"+format+"$3");
		}else{
			return str;
		} 
	};
	
	wehrm.string.formatPhoneNumber = function(phone_val, world_cd){
		var rtn_no = "";
		
		if(phone_val == "") return rtn_no;
		
		world_cd = null2void(world_cd, "");
		if(world_cd == $("#KOR_NTNL_CD").val() || world_cd == ""){
			//$("#user_phone").html(getTel(null2void(dat.EXNM_NO, "")));
//			rtn_no = "(+855) " + getTel(null2void(phone_val, "").replace(/-/gi, "")).replace(/^0+/, " ");
			rtn_no = getTel(null2void(phone_val, "").replace(/-/gi, ""));
		} else {
			//$("#user_phone").html(null2void(dat.EXNM_NO, "-"));
			if(world_cd.split("_").length > 1){
				rtn_no = "+" + world_cd.split("_")[1] + " " + null2void(phone_val, "-").replace(/-/gi, "");
				$("#exnm_no1").val(rtn_no);
			}
		}
		
		return rtn_no;
	}
	
	function getTel(telNo){
		var temp = "";
		var tel1 = "";
		var tel2 = "";
		var tel3 = "";
		
		if(null2void(telNo,"") == "")
			return "";
		
		temp = telNo.split("-").join("");

		if( temp.indexOf("02") > -1 && temp.substr(0,2) == "02" ){
			tel1 = temp.substr(0,2);
			temp = temp.substr(2,temp.length);
		} else if( temp.length > 3 && " 0502, 0503, 0504, 0505, 0506, 0507, 0508".indexOf(temp.substr(0,4)) > -1 ){
			tel1 = temp.substr(0,4);
			temp = temp.substr(4,temp.length);
		} else if( temp.length > 3 ){
			tel1 = temp.substr(0,3);
			temp = temp.substr(3,temp.length);
		} else {
			return temp;
		}
		
		if( temp.length <= 7 ){
			if( temp.length <= 3){
				tel2 = temp.substr(0,temp.length);
				return tel1 + "-" + tel2;
			} else {
				tel2 = temp.substr(0,3);
				tel3 = temp.substr(3,temp.length);
				return tel1 + "-" + tel2 + "-" + tel3;
			}
		} else {
			tel2 = temp.substr(0,4);
			tel3 = temp.substr(4,temp.length);
			return tel1 + "-" + tel2 + "-" + tel3;
		}

	}
	
};

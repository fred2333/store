$(function() {	
	/*
	 * 1. 给注册按钮添加submit()事件，完成表单校验
	 */
	$("#submit").submit(function(){
		$("#msg").text("");
		var bool = true;
		$(".input").each(function() {
			var inputName = $(this).attr("name");
			while(!invokeValidateFunction(inputName)) {
				bool = false;
			}
		});
		return bool;
	});
	
	/*
	 * 3. 输入框推动焦点时进行校验
	 */
	$(".input").blur(function() {
		var inputName = $(this).attr("name");
		invokeValidateFunction(inputName);
	});
});

/*
 * 输入input名称，调用对应的validate方法。
 * 例如input名称为：loginname，那么调用validateLoginname()方法。
 */
function invokeValidateFunction(inputName) {
	inputName = inputName.substring(0, 1).toUpperCase() + inputName.substring(1);
	var functionName = "validate" + inputName;
	return eval(functionName + "()");	
}

/*
 * 校验邮箱
 */
function validateEmail() {
	var bool = true;
	$("#emailError").css("display", "none");
	var value = $("#email").val();
	if(!value) {// 非空校验
		$("#emailError").css("display", "");
		$("#emailError").text("邮箱不能为空！");
		bool = false;
	} else if(!/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(value)) {//长度校验
		$("#emailError").css("display", "");
		$("#emailError").text("错误的邮箱格式！");
		bool = false;
	}
	return bool;
}

/*
 * 校验手机号码
 */
function validatePhone() {
	var bool = true;
	$("#phoneError").css("display", "none");
	var value = $("#phone").val();
	if(!value) {// 非空校验
		$("#phoneError").css("display", "");
		$("#phoneError").text("手机号码不能为空！");
		bool = false;
	} else if(value.length < 11 || value.length > 11) {//长度校验
		$("#phoneError").css("display", "");
		$("#phoneError").text("手机号码长度必须为11位！");
		bool = false;
	}
	return bool;
}

/*
 * 校验手机号码
 */
function validateAddress() {
	var bool = true;
	$("#addressError").css("display", "none");
	var value = $("#address").val();
	if(!value) {// 非空校验
		$("#addressError").css("display", "");
		$("#addressError").text("地址不能为空！");
		bool = false;
	} else if(value.length > 64) {//长度校验
		$("#addressError").css("display", "");
		$("#addressError").text("地址最长为64位！");
		bool = false;
	}
	return bool;
}




$(function() {	
	/*
	 * 1. 给注册按钮添加submit()事件，完成表单校验
	 */
	$("#submit").submit(function(){
		$("#msg").text("");
		var bool = true;
		$(".input").each(function() {
			var inputName = $(this).attr("name");
			if(!invokeValidateFunction(inputName)) {
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
 * 校验密码
 */
function validateLoginpass() {
	var bool = true;
	$("#loginpassError").css("display", "none");
	var value = $("#loginpass").val();
	if(!value) {// 非空校验
		$("#loginpassError").css("display", "");
		$("#loginpassError").text("密码不能为空！");
		bool = false;
	} 
	return bool;
}

// 校验新密码
function validateNewpass() {
	var bool = true;
	$("#newpassError").css("display", "none");
	var value = $("#newpass").val();
	if(!value) {// 非空校验
		$("#newpassError").css("display", "");
		$("#newpassError").text("新密码不能为空！");
		bool = false;
	}
	return bool;
}

/*
 * 校验确认密码
 */
function validateReloginpass() {
	var bool = true;
	$("#reloginpassError").css("display", "none");
	var value = $("#reloginpass").val();
	if(!value) {// 非空校验
		$("#reloginpassError").css("display", "");
		$("#reloginpassError").text("确认密码不能为空！");
		bool = false;
	} else if(value != $("#newpass").val()) {//两次输入是否一致
		$("#reloginpassError").css("display", "");
		$("#reloginpassError").text("两次密码输入不一致！");
		bool = false;
	}
	return bool;	
}

/*
 * 重置密码
 */
function resetPwd(uid) {
	if(confirm("确实要重置该用户密码吗？")) {
		$.ajax({
			type: "get",
			url: "/Horizon/admin/ma_customer/CustomerManagementCtrl?method=resetUserPwd&uid="+uid,
			success: function() {
				location.reload(true);
			},
			error: function() {
				alert("操作异常，无法重置密码。");
			}
		});
	}
}

/**
 * 删除用户
 * @param uid
 */
function delUser(uid) {
	if(confirm("确实要删除该用户吗？")) {
		$.ajax({
			type: "get",
			url: "/Horizon/admin/ma_customer/CustomerManagementCtrl?method=delUser&uid="+uid,
			success: function(data) {
				if(data=="0") {
					alert("该用户有下单或开店记录，无法删除。");
				} else {
					location.reload(true);
				}
			},
			error: function() {
				alert("操作异常，无法删除该用户。");
			}
		});
	}
}
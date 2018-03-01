/**
 * 显示顾客问题内容
 * @param loginname
 */
function showIssue(issueid, loginname) {
	$(".login_name").text(loginname);
	$(".issue_title").text($("#tit_"+issueid).text());
	$(".iss_con").text($("#con_"+issueid).val());
	$('.pop_up').css({'display':'block'});
}

/**
 * 删除顾客问题条目
 * @param issueid
 */
function delIssue(issueid) {
	if(confirm("是否删除该问题反馈记录？")) {
		$.ajax({
			type: "POST",
			url: "/Horizon/admin/ma_customer/CustomerManagementCtrl?method=delIssueList",
			data:{
				issueid:issueid,
			},
			success: function(data) {
				location.reload(true);
			},
			error: function() {
				alert("操作异常，无法删除。");
			}
		});
	}
}
function _change() {
	$("#vCode").attr("src", "/Horizon/VerifyCodeServlet?" + new Date().getTime());
}
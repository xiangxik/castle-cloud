var parseQueryString = function(arr_url) {
	var reg_para = /([^&=]+)=([\w\W]*?)(&|$|#)/g, ret = {};
	if (arr_url) {
		var str_para = arr_url, result;
		while ((result = reg_para.exec(str_para)) != null) {
			if (result[2]) {
				ret[result[1]] = decodeURIComponent(result[2]);
			}
		}
	}
	return ret;
};

var multiAction = function(grid, action) {
	var selectedRows = grid.bootgrid("getSelectedRows");
	if (selectedRows && selectedRows.length > 0) {
		action(grid, selectedRows);
	} else {
		alert("请至少选择一条记录");
	}
}
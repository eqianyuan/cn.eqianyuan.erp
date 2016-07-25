$(function(){
	/**
	 * 全选、非全选点击事件
	 */
	$(".checkAll").click(function(){
		$("table tbody input[type='checkbox']").prop("checked", this.checked)
	});

	/**
	 * 单条数据选择点击事件
	 */
	$("table tbody").on("click", "input[type='checkbox']", function(){
		var flag = true;
		$("table tbody input[type='checkbox']").each(function(){
			if(!this.checked){
				flag = false;
			}
		});
		$(".checkAll").prop("checked", flag)
	});
});
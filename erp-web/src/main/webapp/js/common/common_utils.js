$(function(){
	/**
	 * 全选、非全选点击事件
	 */
	$("input[name='allChecked']").click(function(){
		if(this.checked){
			$("table tbody input[type='checkbox']").attr("checked", $(this).attr("checked"));
		}else{
			$("table tbody input[type='checkbox']").removeAttr("checked");
		}
		
	});

	/**
	 * 单条数据选择点击事件
	 */
	$("table tbody input[type='checkbox']").live("click", function(){
		var flag = true;
		$("table tbody input[type='checkbox']").each(function(){
			if(!this.checked){
				flag = false;
			}
		});
		$("input[name='allChecked']").attr("checked", flag);
	});
});
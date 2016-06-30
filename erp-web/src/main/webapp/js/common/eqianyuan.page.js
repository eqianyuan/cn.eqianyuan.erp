(function ($) {
    var ms = {
        init: function (obj, args) {
            return (function () {
                ms.fillHtml(obj, args);
                if(!args.initStatus) {
                    ms.bindEvent(obj, args);
                    args.initStatus = true;
                }
            })();
        },
        //填充html
        fillHtml: function (obj, args) {
            return (function () {
                //清空分页内容
                obj.empty();

                if (args.pageCount <= 0) {
                    return;
                }

                //判断上一页按钮是否可点击
                if (args.current <= 1) {
                    //禁用上一页按钮
                    obj.remove('.prev');
                    obj.append('<li class="disabled"><a href="javascript:;">&laquo;</a></li>');

                    //设置数字按钮第一页为选中模式
                    obj.append('<li class="active"><a href="javascript:;" class="current">1</a></li>');
                } else {
                    //启用上一页按钮
                    obj.append('<li class="prev"><a href="javascript:;">&laquo;</a></li>');

                    //设置数字按钮第一页为可操作模式
                    obj.append('<li><a href="javascript:;" class="tcdNumber">1</a></li>');
                }


                if (args.current - 2 > 2 && args.current <= args.pageCount && args.pageCount > 5) {
                    obj.append('<li><a href="javascript:;">...</a></li>');
                }

                if (args.pageCount > 1) {
                    //从当前页码前2位开始创建数字按钮
                    /**
                     * 《1 ... current -2 , current -1 , current , current + 1, current + 2 ... pageCount》
                     * 《1 2 3 4 5》
                     * 《1 2 3 4 ... 23》
                     * 《1 ... 8 9 10 11》
                     */

                    var start = args.current - 2, end = args.current + 2;

                    //如果start位小于1，则start赋值为2
                    if (start <= 1) {
                        start = 2;
                    }

                    //如果end为大于pageCount - 1，则end赋值为pageCount - 1
                    if (end >= args.pageCount - 1) {
                        end = args.pageCount - 1;
                    }

                    for (; start <= end; start++) {
                        if (start != args.current) {
                            obj.append('<li><a href="javascript:;" class="tcdNumber">' + start + '</a></li>');
                        } else {
                            obj.append('<li class="active"><a href="javascript:;" class="current">' + start + '</a></li>');
                        }
                    }
                }

                if (args.current + 2 < args.pageCount - 1 && args.current >= 1 && args.pageCount > 5) {
                    obj.append('<li><a href="javascript:;">...</a></li>');
                }

                if (args.current >= args.pageCount) {
                    if (args.pageCount > 1) {
                        //设置数字按钮最后一页为选中模式
                        obj.append('<li class="active"><a href="javascript:;" class="current">' + args.pageCount + '</a></li>');
                    }

                    //禁用上一页按钮
                    obj.remove('.next');
                    obj.append('<li class="disabled"><a href="javascript:;">&raquo;</a></li>');
                } else {
                    if (args.pageCount > 1) {
                        obj.append('<li><a href="javascript:;" class="tcdNumber">' + args.pageCount + '</a></li>');
                    }
                    obj.append('<li class="next"><a href="javascript:;">&raquo;</a></li>');
                }
            })();
        },
        //绑定事件
        bindEvent: function (obj, args) {
            return (function () {
                obj.on("click", "a.tcdNumber", function () {
                    var current = parseInt($(this).text());
                    ms.fillHtml(obj, {"current": current, "pageCount": args.pageCount});
                    if (typeof(args.backFn) == "function") {
                        args.backFn(current);
                    }
                });
                //上一页
                obj.on("click", ".prev", function () {
                    var current = parseInt(obj.find(".current").text());
                    ms.fillHtml(obj, {"current": current - 1, "pageCount": args.pageCount});
                    if (typeof(args.backFn) == "function") {
                        args.backFn(current - 1);
                    }
                });
                //下一页
                obj.on("click", ".next", function () {
                    var current = parseInt(obj.find(".current").text());
                    ms.fillHtml(obj, {"current": current + 1, "pageCount": args.pageCount});
                    if (typeof(args.backFn) == "function") {
                        args.backFn(current + 1);
                    }
                });
            })();
        }
    }
    $.fn.createPage = function (options) {
        var args = $.extend({
            pageCount: 10,
            current: 1,
            initStatus: false,
            backFn: function () {
            }
        }, options);
        ms.init(this, args);
    }
})(jQuery);
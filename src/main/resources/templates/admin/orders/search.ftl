<div class="row">
    <div class="col-xs-12">
        <h4 class="header smaller lighter blue">
            <span><i class="fa fa-filter"></i> 筛选条件</span>
        </h4>
        <div class="col-xs-12 col-sm-6 padding-6">
            <div class="form-group row col-xs-12 col-sm-12">
                <label class="col-sm-2 no-padding-right text-right">订单号</label>
                <div class="col-sm-10">
                    <input id="no" class="form-control col-xs-10 col-sm-9"/>
                </div>
            </div>
            <div class="form-group row col-xs-12 col-sm-12">
                <label class="col-sm-2 no-padding-right text-right">订单日期</label>
                <div class="col-sm-10">
                    <input type="text" id="dateRange" class="form-control col-xs-10 col-sm-9" readonly>
                    <input type="hidden" id="from">
                    <input type="hidden" id="to">
                    <script type="text/javascript">
                        $(function () {
                            $("#dateRange").daterangepicker({
                                autoUpdateInput: false,
                                locale: {
                                    format: "YYYY-MM-DD",
                                    separator: " - ",
                                    applyLabel: "确认",
                                    cancelLabel: "清空",
                                    fromLabel: "开始时间",
                                    toLabel: "结束时间",
                                    customRangeLabel: "自定义",
                                    daysOfWeek: ["日", "一", "二", "三", "四", "五", "六"],
                                    monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"]
                                }
                            }).on('cancel.daterangepicker', function (ev, picker) {
                                $("#dateRange").val("请选择日期");
                                $("#from").val("");
                                $("#to").val("");
                            }).on('apply.daterangepicker', function (ev, picker) {
                                $("#from").val(picker.startDate.format('YYYY-MM-DD'));
                                $("#to").val(picker.endDate.format('YYYY-MM-DD'));
                                $("#dateRange").val(picker.startDate.format('YYYY-MM-DD') + " 至 " + picker.endDate.format('YYYY-MM-DD'));
                            });
                        })
                    </script>
                </div>
            </div>
            <div class="form-group row col-xs-12 col-sm-12">
                <label class="col-sm-2 no-padding-right text-right">手机号</label>
                <div class="col-sm-10">
                    <input class="form-control col-xs-10 col-sm-9" id="mobile"/>
                </div>
            </div>
            <div class="row" style="padding-left:　30px">
                <div class="form-group row col-xs-12 col-sm-12">
                    <label class="col-sm-2 no-padding-right text-right">&nbsp;</label>
                    <div class="col-sm-10">
                        <button class="btn btn-info" type="submit" id="query">
                            <i class="icon-ok bigger-110"></i>
                            查询
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
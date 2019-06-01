<div class="row">
    <div class="col-xs-12">
        <h4 class="header smaller lighter blue">
            <span><i class="fa fa-filter"></i> 筛选条件</span>
        </h4>
        <div class="col-xs-12">
            <div class="row">
                <div class="form-group row col-xs-12 col-sm-6">
                    <label class="col-sm-2 no-padding-right text-right">名称</label>
                    <div class="col-sm-10">
                        <input name="name" class="form-control col-xs-10 col-sm-9"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group row  col-xs-12 col-sm-6">
                    <label class="col-sm-2 no-padding-right text-right">有效期</label>
                    <div class="col-sm-10">
                        <input type="text" id="dateRange" class="form-control col-xs-10 col-sm-9">
                        <input type="hidden" id="startDate">
                        <input type="hidden" id="endDate">
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
                                    $("#startDate").val("");
                                    $("#endDate").val("");
                                }).on('apply.daterangepicker', function (ev, picker) {
                                    $("#startDate").val(picker.startDate.format('YYYY-MM-DD'));
                                    $("#endDate").val(picker.endDate.format('YYYY-MM-DD'));
                                    $("#dateRange").val(picker.startDate.format('YYYY-MM-DD') + " 至 " + picker.endDate.format('YYYY-MM-DD'));
                                });
                            })
                        </script>
                    </div>
                </div>
            </div>
            <div class="row" style="padding-left:　30px">
                <div class="form-group row col-xs-12 col-sm-12">
                    <label class="col-sm-2 no-padding-right text-right">&nbsp;</label>
                    <div class="col-sm-10">
                        <button class="btn btn-info" type="submit">
                            <i class="icon-ok bigger-110"></i>
                            查询
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
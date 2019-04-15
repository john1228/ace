<#import "../layout/application.ftl" as layout>
<#import "/spring.ftl" as spring />
<#import "../formBuilder.ftl" as formBuilder />
<@layout.myLayout>
<script src="/assets/js/bootstrap/multiselect.min.js"></script>
<div class="breadcrumbs" id="breadcrumbs">
    <script type="text/javascript">
        try {
            ace.settings.check('breadcrumbs', 'fixed')
        } catch (e) {
        }
    </script>
    <ul class="breadcrumb">
        <li>
            <i class="icon-home home-icon"></i>
            <a href="/admin/">Home</a>
        </li>
        <li>
            <a href="/admin/prices/?parent=${parent}">价格管理</a>
        </li>
        <li class="active">价格新增</li>
    </ul>
</div>

<div class="page-content">
    <div class="row">
        <div class="col-xs-12">
            <div class="page-header">
                <h1>新增价格</h1>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <form action="/admin/prices?parent=${parent}" role="form" class="form-horizontal" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="hidden" name="staffId" value="${current_operator.getId()}">
                        <@spring.bind path="price"/>
                        <div class="form-group row">
                            <label class="col-sm-2 control-label no-padding-right"><span style="color: red">*</span>会议室</label>
                            <div class="col-sm-10">
                                <@spring.formMultiSelect "price.roomId",rooms,"class='multiselect col-xs-10 col-sm-9'"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-2 control-label no-padding-right"><span style="color: red">*</span>出租方式</label>
                            <div class="col-sm-10">
                                <@formBuilder.formRadioButtons "price.rental",rentals,"class='col-xs-10 col-sm-9' required data-placeholder='请选择出租方式'"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-2 control-label no-padding-right"><span style="color: red">*</span>适用日期</label>
                            <div class="col-sm-10">
                                <input type="text" id="dateRange" class="col-xs-10 col-sm-9" required>
                                <@spring.formHiddenInput "price.startDate"/>
                                <@spring.formHiddenInput "price.endDate"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-2 control-label no-padding-right"><span
                                    style="color: red">*</span>周</label>
                            <div class="col-sm-10">
                                <@spring.formMultiSelect "price.wday",weeks,"class='multiselect col-xs-10 col-sm-9'"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-2 control-label no-padding-right"><span style="color: red">*</span>适用时间</label>
                            <div class="col-sm-10">
                                <div id="slider" class="col-xs-10 col-sm-9" style="margin-top: 10px"></div>
                                <@spring.formHiddenInput "price.startTime"/>
                                <@spring.formHiddenInput "price.endTime"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-2 control-label no-padding-right"><span
                                    style="color: red">*</span>价格</label>
                            <div class="col-sm-10">
                                <@spring.formInput "price.price" "class='col-xs-10 col-sm-9' placeholder='请填写价格'"/>
                            </div>
                        </div>
                        <div class="clearfix form-actions">
                            <div class="col-md-offset-3 col-md-9">
                                <button class="btn btn-info" type="submit">
                                    <i class="icon-ok bigger-110"></i>
                                    提交
                                </button>
                                &nbsp; &nbsp; &nbsp;
                                <button class="btn" type="reset" onclick="history.go(-1)">
                                    <i class="icon-undo bigger-110"></i>
                                    返回
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        $("#slider").slider({
            range: true,
            min: 0,
            max: 48,
            step: 1,
            values: [18, 42],
            showLabels: true,
            slide: function (event, ui) {
                $('#startTime').val(ui.values[0]);
                $('#endTime').val(ui.values[1]);
            }

        });
        $('.multiselect').multiselect({
            nonSelectedText: "请选择",
            nSelectedText: "已选择",
            allSelectedText: "全选",
            enableFiltering: true,
            enableHTML: true,
            buttonClass: 'btn btn-white btn-primary',
            templates: {
                button: '<button type="button" class="multiselect dropdown-toggle" data-toggle="dropdown"><span class="multiselect-selected-text"></span> &nbsp;<b class="fa fa-caret-down"></b></button>',
                ul: '<ul class="multiselect-container dropdown-menu"></ul>',
                filter: '<li class="multiselect-item filter"><div class="input-group"><span class="input-group-addon"><i class="fa fa-search"></i></span><input class="form-control multiselect-search" type="text"></div></li>',
                filterClearBtn: '<span class="input-group-btn"><button class="btn btn-default btn-white btn-grey multiselect-clear-filter" type="button"><i class="fa fa-times-circle red2"></i></button></span>',
                li: '<li><a tabindex="0"><label></label></a></li>',
                divider: '<li class="multiselect-item divider"></li>',
                liGroup: '<li class="multiselect-item multiselect-group"><label></label></li>'
            }
        });
        $("#dateRange").daterangepicker({
            autoUpdateInput: false,
            locale: {
                format: "YYYY/MM/DD",
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
            $("#dateRange").val("请选择时间");
            $("#startDate").val("");
            $("#endDate").val("");
        }).on('apply.daterangepicker', function (ev, picker) {
            $("#startDate").val(picker.startDate.format('YYYY-MM-DD'));
            $("#endDate").val(picker.endDate.format('YYYY-MM-DD'));
            $("#dateRange").val(picker.startDate.format('YYYY-MM-DD') + " 至 " + picker.endDate.format('YYYY-MM-DD'));
        });
    })

</script>
</@layout.myLayout>

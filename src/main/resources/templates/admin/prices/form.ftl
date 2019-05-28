<#import "/spring.ftl" as spring />
<script src="/assets/js/bootstrap/multiselect.min.js"></script>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<input type="hidden" name="staffId" value="${current_operator.getId()}">
<input type="hidden" name="roomName">
<@spring.bind "price"/>
<div class="form-group row">
    <label class="col-sm-2 control-label no-padding-right"><span style="color: red">*</span>会议室</label>
    <div class="col-sm-10">
        <@spring.formMultiSelect "price.roomId",rooms,"class='multiselect col-xs-10 col-sm-9' required"/>
    </div>
</div>
<div class="form-group row">
    <label class="col-sm-2 control-label no-padding-right"><span style="color: red">*</span>出租方式</label>
    <div class="col-sm-10">
        <@spring.formRadioButtons "price.rental",rentals,''/>
    </div>
</div>
<div class="form-group row">
    <label class="col-sm-2 control-label no-padding-right"><span style="color: red">*</span>适用日期</label>
    <div class="col-sm-10">
        <input type="text" id="dateRange" class="col-xs-10 col-sm-9"
               value="${price.startDate?string('yyyy-MM-dd')} 至 ${price.endDate?string('yyyy-MM-dd')}" required
               readonly>
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
    <div class="col-sm-10" style="margin-top: 10px">
        <span id="display">09:00 ~ 21:00</span>
    </div>
</div>
<div class="form-group row">
    <label class="col-sm-2 control-label no-padding-right"><span
            style="color: red">*</span>价格</label>
    <div class="col-sm-10">
        <@spring.formInput "price.price" "class='col-xs-10 col-sm-9' placeholder='请填写价格' required"/>
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
<script type="text/javascript">
    $(function () {
        $("#slider").slider({
            range: true,
            min: 16,
            max: 40,
            step: 1,
            values: [16, 40],
            showLabels: true,
            slide: function (event, ui) {
                var startH = parseInt(ui.values[0] / 2);
                var endH = parseInt(ui.values[1] / 2);
                var startM = ui.values[0] % 2;
                var endM = ui.values[1] % 2;
                if (startH < 10) startH = '0' + startH;
                if (startM == 0) {
                    startH = startH + ":00"
                } else {
                    startH = startH + ":30"
                }

                if (endH == 10) startH = '0' + startH;
                if (endM == 0) {
                    endH = endH + ":00"
                } else {
                    endH = endH + ":30"
                }
                $("#display").html(startH + " ~ " + endH);
                $('#startTime').val(startH);
                $('#endTime').val(endH);
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
        $("form").on('submit', function (e) {
            var ary = new Array();
            $("#roomId option:selected").each(function (i, option) {
                ary.push($(this).text());
            });
            $("[name=roomName]").val(ary);
        })
    })
</script>

<#import "/spring.ftl" as spring />
<script src="/assets/js/bootstrap/multiselect.min.js"></script>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
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
        <div class="col-xs-12 col-sm-9 input-group input-daterange">
            <@spring.formInput "price.startDate",'class="form-control"'/>
            <div class="input-group-addon">至</div>
            <@spring.formInput "price.endDate",'class="form-control"'/>
        </div>
    </div>
    <script type="text/javascript">
        $('.input-daterange input').each(function () {
            $(this).datepicker({language: 'zh'});
        });
    </script>
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
        <div class="input-group col-xs-10 col-sm-9">
            <@spring.formInput "price.price" "class='form-control' placeholder='请填写价格' required"/>
            <span class="input-group-btn">
               <button class="btn btn-sm btn-default" type="button">
                    半小时
                </button>
            </span>
        </div>
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
        $("form").on('submit', function (e) {
            var ary = new Array();
            $("#roomId option:selected").each(function (i, option) {
                ary.push($(this).text());
            });
            $("[name=roomName]").val(ary);
        })
    })
</script>

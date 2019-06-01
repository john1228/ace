<#import "/spring.ftl" as spring />
<#import "../formBuilder.ftl" as formBuilder />
<script src="/assets/js/jquery/dataTables.min.js"></script>
<script src="/assets/js/jquery/dataTables.bootstrap.min.js"></script>
<link href="/assets/css/bootstrap/fileinput.css" media="all" rel="stylesheet" type="text/css">
<script src="/assets/js/bootstrap/fileinput.js"></script>
<link href="/assets/css/bootstrap/tags-input.css" media="all" rel="stylesheet" type="text/css">
<script src="/assets/js/bootstrap/tags-input.js"></script>
<script src="/assets/js/bootstrap/locales/zh.js"></script>
<@spring.bind path="room"/>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<input type="hidden" name="staffId" value="${current_operator.getId()}">
<div class="row">
    <div class="col-xs-12 col-sm-12 widget-container-col">
        <div class="widget-box">
            <div class="widget-header widget-header-custom">
                <h5 class="widget-title">场地图片</h5>
            </div>
            <div class="widget-body">
                <div class="widget-main padding-8">
                    <div class="form-group row">
                        <div class="col-sm-2 control-label no-padding-right">列表图片</div>
                        <div class="col-sm-10">
                            <div class="avatar">
                                <div class="file-loading">
                                    <input id="cover" name="coverFile" type="file" required>
                                </div>
                            </div>
                        </div>
                        <script type="text/javascript">
                            $("#cover").fileinput({
                                language: 'zh',
                                overwriteInitial: true,
                                maxFileSize: 1500,
                                showClose: false,
                                showCaption: false,
                                showBrowse: false,
                                browseOnZoneClick: true,
                                removeLabel: '',
                                removeIcon: '<i class="glyphicon glyphicon-remove"></i>',
                                removeTitle: 'Cancel or reset changes',
                                elErrorContainer: '#kv-avatar-errors-1',
                                msgErrorClass: 'alert alert-block alert-danger',
                                layoutTemplates: {main2: '{preview} {browse}'},
                                allowedFileExtensions: ["jpg", "png"]
                            });
                        </script>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-2 control-label no-padding-right">场地图片</div>
                        <div class="col-sm-10">
                            <div class="file-loading">
                                <input id="image" name="imageFiles" type="file" multiple>
                            </div>
                        </div>
                        <script type="text/javascript">
                            $(function () {
                                $("#image").fileinput({
                                    language: 'zh',
                                    overwriteInitial: true,
                                    maxFileSize: 1500,
                                    showClose: false,
                                    showCaption: false,
                                    showBrowse: false,
                                    browseOnZoneClick: true,
                                    removeLabel: '',
                                    removeIcon: '<i class="glyphicon glyphicon-remove"></i>',
                                    removeTitle: 'Cancel or reset changes',
                                    elErrorContainer: '#kv-avatar-errors-1',
                                    msgErrorClass: 'alert alert-block alert-danger',
                                    layoutTemplates: {main2: '{preview} {browse}'},
                                    allowedFileExtensions: ["jpg", "png"]
                                });
                            })
                        </script>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 widget-container-col">
        <div class="widget-box">
            <div class="widget-header widget-header-custom widget-color-blue">
                <h5 class="widget-title">基本信息</h5>
            </div>
            <div class="widget-body">
                <div class="widget-main padding-8">
                    <div class="form-group row">
                        <label class="col-sm-2 control-label no-padding-right">
                            <span style="color: red">*</span>编号
                        </label>
                        <div class="col-sm-10">
                            <@spring.formInput "room.serialNo","class='col-xs-12 col-sm-9' required"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 control-label no-padding-right">
                            <span style="color: red">*</span>名称
                        </label>
                        <div class="col-sm-10">
                            <@spring.formInput "room.name","class='col-xs-10 col-sm-9' required"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 control-label no-padding-right">
                            <span style="color: red">*</span>幢号
                        </label>
                        <div class="col-sm-10">
                            <@spring.formInput "room.buildingNo","class='col-xs-12 col-sm-9' required"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 control-label no-padding-right">
                            <span style="color: red">*</span>楼层
                        </label>
                        <div class="col-sm-10">
                            <@spring.formInput "room.floorNo","class='col-xs-12 col-sm-9' required"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 control-label no-padding-right">
                            <span style="color: red">*</span>房号
                        </label>
                        <div class="col-sm-10">
                            <@spring.formInput "room.roomNo","class='col-xs-12 col-sm-9' required"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 widget-container-col">
        <div class="widget-box">
            <div class="widget-header widget-header-custom">
                <h5 class="widget-title">数据信息</h5>
            </div>
            <div class="widget-body">
                <div class="widget-main padding-8">
                    <div class="form-group row">
                        <label class="col-sm-2 control-label no-padding-right"><span
                                style="color: red">*</span>层高</label>
                        <div class="col-sm-10">
                            <@spring.formInput "room.layerHeight" "class='col-xs-10 col-sm-9'"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 control-label no-padding-right"><span
                                style="color: red">*</span>场地类型</label>
                        <div class="col-sm-10 form-radio-group">
                            <@spring.formRadioButtons "room.type",types,""/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 control-label no-padding-right">
                            <span style="color: red">*</span>发布类型</label>
                        <div class="col-sm-10 form-radio-group">
                            <@spring.formRadioButtons "room.publish",publish,""/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 control-label no-padding-right"
                               for="form-field-1"><span style="color: red">*</span>面积</label>
                        <div class="col-sm-10">
                            <@spring.formInput "room.layerArea" "class='col-xs-10 col-sm-9' required"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 control-label no-padding-right"
                               for="form-field-1"><span style="color: red">*</span>容纳人数</label>
                        <div class="col-sm-10">
                            <@spring.formInput "room.quota","class='col-xs-10 col-sm-9' required"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-xs-12 col-sm-6 widget-container-col">
        <div class="widget-box">
            <div class="widget-header widget-header-custom">
                <h5 class="widget-title">出租信息</h5>
            </div>
            <div class="widget-body padding-6">
                <div class="widget-main padding-6">
                    <div class="form-group row">
                        <label class="col-sm-2 control-label no-padding-right"
                               for="form-field-1"><span style="color: red">*</span>参考价</label>
                        <div class="col-sm-10 form-radio-group">
                            <@spring.formInput "room.price" "class='col-xs-10 col-sm-9' required" "number"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 control-label no-padding-right"
                               for="form-field-1"><span style="color: red">*</span>出租收费</label>
                        <div class="col-sm-10 form-radio-group">
                            <@spring.formRadioButtons "room.free",free,""/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 control-label no-padding-right"
                               for="form-field-1"><span style="color: red">*</span>出租方式</label>
                        <div class="col-sm-10 form-radio-group">
                            <@spring.formRadioButtons "room.rental",rentals,""/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 control-label no-padding-right"
                               for="form-field-1"><span style="color: red">*</span>开放日期</label>
                        <div class="col-sm-10">
                            <div class="input-icon input-icon-right col-xs-10 col-sm-9"
                                 style="padding-left: 0 !important;padding-right: 0 !important; ">
                                <input type="text" id="dateRange" style="width: 100%" autocomplete="off">
                                <i class="ace-icon fa fa-calendar blue"></i>
                            </div>
                            <@spring.formHiddenInput "room.openDate"/>
                            <@spring.formHiddenInput "room.closeDate"/>
                        </div>
                        <script type="text/javascript">
                            $(function () {
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
                                    $("#dateRange").val("请选择日期");
                                    $("#openDate").val("");
                                    $("#closeDate").val("");
                                }).on('apply.daterangepicker', function (ev, picker) {
                                    $("#openDate").val(picker.startDate.format('YYYY-MM-DD'));
                                    $("#closeDate").val(picker.endDate.format('YYYY-MM-DD'));
                                    $("#dateRange").val(picker.startDate.format('YYYY-MM-DD') + " 至 " + picker.endDate.format('YYYY-MM-DD'));
                                });
                            })
                        </script>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 control-label no-padding-right"
                               for="form-field-1"><span style="color: red">*</span>起租时间</label>
                        <div class="col-sm-10">
                            <@spring.formInput "room.unit" "class='col-xs-10 col-sm-9'"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 control-label no-padding-right"
                               for="form-field-1"><span style="color: red">*</span>续租时间</label>
                        <div class="col-sm-10">
                            <@spring.formInput "room.renew" "class='col-xs-10 col-sm-9'"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 widget-container-col">
        <div class="widget-box">
            <div class="widget-header widget-header-custom">
                <h5 class="widget-title">联系方式</h5>
            </div>
            <div class="widget-body">
                <div class="widget-main padding-6">
                    <div class="form-group row">
                        <label class="col-sm-2 control-label no-padding-right"
                               for="form-field-1"><span style="color: red">*</span>负责人</label>
                        <div class="col-sm-10">
                            <@spring.formInput "room.supervisor","class='col-xs-10 col-sm-9' required"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 control-label no-padding-right"
                               for="form-field-1"><span style="color: red">*</span>负责人电话</label>
                        <div class="col-sm-10">
                            <@spring.formInput "room.supervisorMobile","class='col-xs-10 col-sm-9' required"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 control-label no-padding-right"
                               for="form-field-1">负责人邮箱</label>
                        <div class="col-sm-10">
                            <@spring.formInput "room.supervisorEmail","class='col-xs-10 col-sm-9'","email"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-xs-12 col-sm-12 widget-container-col">
        <div class="widget-box">
            <div class="widget-header widget-header-custom">
                <h5 class="widget-title">订单相关</h5>
            </div>
            <div class="widget-body padding-6">
                <div class="widget-main padding-6">
                    <div class="row">
                        <div class="form-group row col-xs-12 col-sm-6">
                            <label class="col-sm-2 control-label no-padding-right"
                                   for="form-field-1"><span style="color: red">*</span>是否支付</label>
                            <div class="col-sm-10 form-radio-group">
                                <@spring.formRadioButtons "room.payable",payable,"&nbsp"/>
                            </div>
                        </div>
                        <div class="form-group row col-xs-12 col-sm-6">
                            <label class="col-sm-2 control-label no-padding-right"
                                   for="form-field-1"><span style="color: red">*</span>确认方式</label>
                            <div class="col-sm-10 form-radio-group">
                                <@spring.formRadioButtons "room.cfm",confirmations,""/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group row col-xs-12 col-sm-6">
                            <label class="col-sm-2 control-label no-padding-right"
                                   for="form-field-1"><span style="color: red">*</span>退款时限</label>
                            <div class="col-sm-10">
                                <@spring.formInput "room.rlt","class='col-xs-10 col-sm-9' required"/>
                            </div>
                            <div class="invalid-feedback">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-xs-12 col-sm-12 widget-container-col">
        <div class="widget-box">
            <div class="widget-header widget-header-custom">
                <h5 class="widget-title">会场介绍</h5>
            </div>
            <div class="widget-body">
                <div class="widget-main padding-6">
                    <div class="form-group row">
                        <label class="col-sm-2 control-label no-padding-right"
                               for="form-field-1"><span style="color: red">*</span>免费服务</label>
                        <div class="col-sm-10">
                            <@spring.formInput "room.freeService","class='col-xs-12 col-sm-12' data-role='tagsinput' "/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 control-label no-padding-right"
                               for="form-field-1"><span style="color: red">*</span>会场简介</label>
                        <div class="col-sm-10">
                            <@spring.formTextarea "room.resume","class='col-xs-10 col-sm-9'"/>
                            <script src="/assets/js/ckeditor.js"></script>
                            <script type="text/javascript">
                                ClassicEditor.create(document.querySelector("#resume"))
                            </script>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 control-label no-padding-right"
                               for="form-field-1"><span style="color: red">*</span>收费服务</label>
                        <div class="col-sm-10">
                            <table id="supportList" class="table table-striped table-bordered" cellspacing="0"
                                   width="100%">
                                <thead>
                                <tr>
                                    <td class="center">
                                        <input type="checkbox" class="checkAll"/>
                                        <script>
                                            $(function () {
                                                $(".checkAll").change(function () {
                                                    $(".spItem").prop('checked', this.checked);
                                                })
                                            })
                                        </script>
                                    </td>
                                    <th class="center">名称</th>
                                    <th class="center">图片</th>
                                    <th class="center">备注</th>
                                    <th class="center">价格</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list supports as support>
                                <tr>
                                    <td class="center">
                                        <input type="checkbox" name="supportList[${support_index}].supportId"
                                               value="${support.id}"
                                               class="spItem"/>
                                    </td>
                                    <td class="center">${support.name}</td>
                                    <td class="center">${support.cover}</td>
                                    <td class="center">
                                        <input name="supportList[${support_index}].remark"
                                               class="col-xs-12 col-sm-12">
                                    </td>
                                    <td class="center">
                                        <input name="supportList[${support_index}].price"
                                               class="col-xs-12 col-sm-12">
                                    </td>
                                </tr>
                                </#list>
                                </tbody>
                            </table>
                            <script>
                                $(function () {
                                    $('#supportList').DataTable({
                                        searching: false,
                                        lengthChange: false
                                    });
                                })
                            </script>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row clearfix form-actions">
    <div class="col-md-offset-3 col-md-9">
        <button class="btn btn-info" type="submit">
            <i class="ace-icon fa fa-check bigger-110"></i>
            提交
        </button>
        &nbsp; &nbsp; &nbsp;
        <button class="btn" type="reset" onclick="history.go(-1)">
            <i class="ace-icon fa fa-undo bigger-110"></i>
            返回
        </button>
    </div>
</div>
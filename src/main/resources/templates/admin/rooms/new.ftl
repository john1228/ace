<#import "../layout/application.ftl" as layout>
<#import "/spring.ftl" as spring />
<#import "../formBuilder.ftl" as formBuilder />
<@layout.myLayout>
<link href="/assets/css/bootstrap/fileinput.css" media="all" rel="stylesheet" type="text/css">
<script src="/assets/js/bootstrap/fileinput.js"></script>

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
            <a href="/admin/rooms/?parent=${parent}">场地管理</a>
        </li>
        <li class="active">新增场地</li>
    </ul>
</div>

<div class="page-content">
    <h4 class="header smaller lighter blue">
        <span>新增场地</span>
    </h4>
    <form action="/admin/rooms" role="form" class="form-horizontal" enctype="multipart/form-data"
          data-toggle="validator" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" name="staffId" value="${current_operator.getId()}">
        <input type="hidden" name="parent" value="${parent}">
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
                                            <input id="cover" name="cover" type="file" required>
                                        </div>
                                    </div>

                                </div>
                                <script type="text/javascript">
                                    $("#cover").fileinput({
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
                                        defaultPreviewContent: '<img src="/assets/images/avatars/profile-pic.jpg" alt="Your Avatar">',
                                        layoutTemplates: {main2: '{preview} {browse}'},
                                        allowedFileExtensions: ["jpg", "png"]
                                    });
                                </script>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-2 control-label no-padding-right">场地图片</div>
                                <div class="col-sm-10">
                                    <div class="file-loading">
                                        <input id="image" name="image[]" type="file" multiple>
                                    </div>
                                </div>
                                <script type="text/javascript">
                                    $(function () {
                                        $("#image").fileinput({
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
                                            defaultPreviewContent: '<img src="/assets/images/avatars/profile-pic.jpg" alt="Your Avatar">',
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
                                <label class="col-sm-2 control-label no-padding-right"><span style="color: red">*</span>层高</label>
                                <div class="col-sm-10">
                                    <@spring.formInput "room.layerHeight" "class='col-xs-10 col-sm-9'"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 control-label no-padding-right"><span style="color: red">*</span>场地类型</label>
                                <div class="col-sm-10">
                                    <@formBuilder.formRadioButtons "room.type",types,"required"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 control-label no-padding-right"
                                       for="form-field-1"><span style="color: red">*</span>发布类型</label>
                                <div class="col-sm-10">
                                    <@formBuilder.formRadioButtons "room.publish",publish,"required"/>
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
                                    <@spring.formInput "room.quota" "class='col-xs-10 col-sm-9' required"/>
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
                                       for="form-field-1"><span style="color: red">*</span>出租收费</label>
                                <div class="col-sm-10">
                                    <@formBuilder.formRadioButtons "room.free",free,"required"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 control-label no-padding-right"
                                       for="form-field-1"><span style="color: red">*</span>出租方式</label>
                                <div class="col-sm-10">
                                    <@formBuilder.formRadioButtons "room.rental",rentals,"required"/>
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
                                    <div class="col-sm-10">
                                    <@formBuilder.formRadioButtons "room.payable",payable,"required"/>
                                    </div>
                                </div>
                                <div class="form-group row col-xs-12 col-sm-6">
                                    <label class="col-sm-2 control-label no-padding-right"
                                           for="form-field-1"><span style="color: red">*</span>确认方式</label>
                                    <div class="col-sm-10">
                                        <@formBuilder.formRadioButtons "room.confirmation",confirmations,"required"/>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group row col-xs-12 col-sm-6">
                                    <label class="col-sm-2 control-label no-padding-right"
                                           for="form-field-1"><span style="color: red">*</span>退款时限</label>
                                    <div class="col-sm-10">
                                        <@spring.formInput "room.confirmation","class='col-xs-10 col-sm-9' required"/>
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
                                       for="form-field-1"><span style="color: red">*</span>会场简介</label>
                                <div class="col-sm-10">
                                    <@spring.formTextarea "room.resume","class='col-xs-10 col-sm-9'"/>
                                    <script src="/assets/js/ckeditor.js"></script>
                                    <script type="text/javascript">
                                        ClassicEditor.create(document.querySelector("#resume"))
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
    </form>
</div>
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
</@layout.myLayout>

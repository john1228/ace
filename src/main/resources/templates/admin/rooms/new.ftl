<#import "../layout/application.ftl" as layout>
<#import "/spring.ftl" as spring />
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
    <h3 class="header smaller lighter blue">
        <span>新增场地</span>
    </h3>
    <form action="/admin/rooms" role="form" class="form-horizontal" method="post">
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
                                            <input id="avatar-1" name="avatar-1" type="file" required>
                                        </div>
                                    </div>

                                </div>
                                <script type="text/javascript">
                                    $("#avatar-1").fileinput({
                                        overwriteInitial: true,
                                        maxFileSize: 1500,
                                        showClose: false,
                                        showCaption: false,
                                        browseLabel: '',
                                        removeLabel: '',
                                        browseIcon: none,
                                        removeIcon: '<i class="glyphicon glyphicon-remove"></i>',
                                        removeTitle: 'Cancel or reset changes',
                                        elErrorContainer: '#kv-avatar-errors-1',
                                        msgErrorClass: 'alert alert-block alert-danger',
                                        defaultPreviewContent: '<img src="/samples/default-avatar-male.png" alt="Your Avatar">',
                                        layoutTemplates: {main2: '{preview} {remove} {browse}'},
                                        allowedFileExtensions: ["jpg", "png", "gif"]
                                    });
                                </script>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-2 control-label no-padding-right">场地图片</div>
                                <div class="col-sm-10">
                                    <div class="file-loading" id="image">
                                        <input id="input-file-1" name="input-file-1[]" multiple type="file"
                                               accept="image/*">
                                    </div>
                                </div>
                                <script type="text/javascript">
                                    $("#input-file-1").fileinput({
                                        uploadUrl: "/file-upload-batch/2",
                                        autoOrientImage: true
                                    });
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
                                <label class="col-sm-3 control-label no-padding-right"
                                       for="form-field-1">编号</label>
                                <div class="col-sm-9">
                                    <@spring.formInput "room.serialNo" "class='col-xs-10 col-sm-5'"/>
                                    <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                        <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                    </span>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3 control-label no-padding-right"
                                       for="form-field-1">名字</label>
                                <div class="col-sm-9">
                                    <@spring.formInput "room.name","class='col-xs-10 col-sm-5'"/>
                                    <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                        <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                    </span>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3 control-label no-padding-right"
                                       for="form-field-1">幢号</label>
                                <div class="col-sm-9">
                                    <@spring.formInput "room.buildingNo" "class='col-xs-10 col-sm-5'"/>
                                    <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                        <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                    </span>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3 control-label no-padding-right"
                                       for="form-field-1">楼层</label>
                                <div class="col-sm-9">
                                    <@spring.formInput "room.floorNo" "class='col-xs-10 col-sm-5'"/>
                                    <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                        <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                    </span>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3 control-label no-padding-right"
                                       for="form-field-1">房号</label>
                                <div class="col-sm-9">
                                    <@spring.formInput "room.floorNo" "class='col-xs-10 col-sm-5'"/>
                                    <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                        <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                    </span>
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
                                <label class="col-sm-3 control-label no-padding-right"
                                       for="form-field-1">层高</label>
                                <div class="col-sm-9">
                                    <@spring.formInput "room.layerHeight" "class='col-xs-10 col-sm-5'"/>
                                    <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                        <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                    </span>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3 control-label no-padding-right">类型</label>
                                <div class="col-sm-9">
                                    <@spring.formInput "room.name","class='col-xs-10 col-sm-5'"/>
                                    <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                        <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                    </span>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3 control-label no-padding-right"
                                       for="form-field-1">幢号</label>
                                <div class="col-sm-9">
                                    <@spring.formInput "room.buildingNo" "class='col-xs-10 col-sm-5'"/>
                                    <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                        <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                    </span>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3 control-label no-padding-right"
                                       for="form-field-1">楼层</label>
                                <div class="col-sm-9">
                                    <@spring.formInput "room.floorNo" "class='col-xs-10 col-sm-5'"/>
                                    <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                        <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                    </span>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3 control-label no-padding-right"
                                       for="form-field-1">房号</label>
                                <div class="col-sm-9">
                                    <@spring.formInput "room.floorNo" "class='col-xs-10 col-sm-5'"/>
                                    <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                        <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                    </span>
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
                                <label class="col-sm-3 control-label no-padding-right"
                                       for="form-field-1">出租收费</label>
                                <div class="col-sm-9">
                                    <@spring.formRadioButtons "room.free",free,"&nbsp;",""/>
                                    <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                        <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                    </span>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3 control-label no-padding-right"
                                       for="form-field-1">出租方式</label>
                                <div class="col-sm-9">
                                    <@spring.formRadioButtons "room.rental",rentals,"&nbsp;",""/>
                                    <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                        <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                    </span>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3 control-label no-padding-right"
                                       for="form-field-1">开放日期</label>
                                <div class="col-sm-9">
                                    <span class="input-icon input-icon-right">
                                        <input type="text" id="dateRange">
                                        <i class="ace-icon fa fa-calendar blue"></i>
                                    </span>
                                    <@spring.formHiddenInput "room.openDate"/>
                                    <@spring.formHiddenInput "room.closeDate"/>
                                    <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                        <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                    </span>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3 control-label no-padding-right"
                                       for="form-field-1">起租时间</label>
                                <div class="col-sm-9">
                                    <@spring.formInput "room.unit" "class='col-xs-10 col-sm-5'"/>
                                    <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                        <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                    </span>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3 control-label no-padding-right"
                                       for="form-field-1">续租时间</label>
                                <div class="col-sm-9">
                                    <@spring.formInput "room.renew" "class='col-xs-10 col-sm-5'"/>
                                    <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                        <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                    </span>
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
                                <label class="col-sm-3 control-label no-padding-right"
                                       for="form-field-1">负责人</label>
                                <div class="col-sm-9">
                                    <@spring.formInput "room.supervisor","class='col-xs-10 col-sm-5'"/>
                                    <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                        <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                    </span>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label class="col-sm-3 control-label no-padding-right"
                                       for="form-field-1">负责人电话</label>
                                <div class="col-sm-9">
                                    <@spring.formInput "room.supervisorMobile" "class='col-xs-10 col-sm-5'"/>
                                    <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                        <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                    </span>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-3 control-label no-padding-right"
                                       for="form-field-1">负责人邮箱</label>
                                <div class="col-sm-9">
                                    <@spring.formInput "room.supervisorEmail" "class='col-xs-10 col-sm-5'"/>
                                    <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                        <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                    </span>
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

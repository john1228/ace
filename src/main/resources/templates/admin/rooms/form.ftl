<#import "/spring.ftl" as spring />
<#import "../formBuilder.ftl" as formBuilder />
<script src="/assets/js/jquery/dataTables.min.js"></script>
<script src="/assets/js/jquery/dataTables.bootstrap.min.js"></script>
<link href="/assets/css/bootstrap/fileinput.css" media="all" rel="stylesheet" type="text/css">
<script src="/assets/js/bootstrap/fileinput.js"></script>
<script src="/assets/js/bootstrap/locales/zh.js"></script>
<link href="/assets/css/bootstrap/tags-input.css" media="all" rel="stylesheet" type="text/css">
<script src="/assets/js/bootstrap/tags-input.js"></script>
<script src="/assets/js/bootstrap/locales/zh.js"></script>
<script src="/assets/js/bootstrap/multiselect.min.js"></script>
<@spring.bind path="room"/>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
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
                                    <input id="coverFile" name="coverFile" type="file">
                                </div>
                            </div>
                        </div>
                        <script type="text/javascript">
                            $("#coverFile").fileinput({
                                language: 'zh',
                                overwriteInitial: true,
                                maxFileSize: 1500,
                                showUpload: false,
                                showRemove: false,
                                showClose: false,
                                showCaption: false,
                                showCancel: false,
                                showBrowse: false,
                                <#if room.cover??>
                                    browseOnZoneClick: true,
                                    allowedFileExtensions: ["jpg", "png", "jpeg"],
                                    initialPreview: ["${image + room.cover}"],
                                    initialPreviewAsData: true,
                                    initialPreviewFileType: 'image',
                                    initialPreviewShowDelete: false,
                                    layoutTemplates: {
                                        actions: ""
                                    }

                                <#else>
                                    browseOnZoneClick: true
                                </#if>
                            });
                        </script>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-2 control-label no-padding-right">场地图片</div>
                        <div class="col-sm-10">
                            <input name="remove" id="remove" type="hidden">
                            <input name="change" id="change" type="hidden">
                            <div class="avatar">
                                <div class="file-loading">
                                    <input id="img1" name="img[]" type="file" data-id="0">
                                </div>
                            </div>
                            <div class="avatar">
                                <div class="file-loading">
                                    <input id="img2" name="img[]" type="file" data-id="1">
                                </div>
                            </div>
                            <div class="avatar">
                                <div class="file-loading">
                                    <input id="img3" name="img[]" type="file" data-id="2">
                                </div>
                            </div>
                            <script type="text/javascript">
                                $("#img1").fileinput({
                                    language: 'zh',
                                    overwriteInitial: true,
                                    maxFileSize: 1500,
                                    showUpload: false,
                                    showRemove: false,
                                    showClose: false,
                                    showCaption: false,
                                    showCancel: false,
                                    showBrowse: false,
                                    <#if room.image[0]??>
                                    browseOnZoneClick: true,
                                    allowedFileExtensions: ["jpg", "png", "jpeg"],
                                    initialPreview: ["${image + room.image[0]}"],
                                    initialPreviewAsData: true,
                                    initialPreviewFileType: 'image',
                                    layoutTemplates: {
                                        actions: "<div class=\"file-actions\">" +
                                                "<div class=\"file-footer-buttons\">" +
                                                " <button type=\"button\" class=\"kv-file-remove btn btn-sm btn-kv btn-default btn-outline-secondary\" title=\"删除文件\" data-url=\"\" data-key=\"\"><i class=\"glyphicon glyphicon-trash\"></i></button>\n" +
                                                "</div>" +
                                                "</div>"
                                    }
                                    <#else>
                                    browseOnZoneClick: true
                                    </#if>
                                });
                                $('#img1').on('filedeleted', function (event, key, jqXHR, data) {
                                    console.log("图片1删除");
                                });
                                $("#img2").fileinput({
                                    language: 'zh',
                                    overwriteInitial: true,
                                    maxFileSize: 1500,
                                    showUpload: false,
                                    showRemove: false,
                                    showClose: false,
                                    showCaption: false,
                                    showCancel: false,
                                    showBrowse: false,
                                    <#if room.image[1]??>
                                    browseOnZoneClick: true,
                                    allowedFileExtensions: ["jpg", "png", "jpeg"],
                                    initialPreview: ["${image + room.image[1]}"],
                                    initialPreviewAsData: true,
                                    initialPreviewFileType: 'image',
                                    layoutTemplates: {
                                        actions: "<div class=\"file-actions\">" +
                                                "<div class=\"file-footer-buttons\">" +
                                                " <button type=\"button\" class=\"kv-file-remove btn btn-sm btn-kv btn-default btn-outline-secondary\" title=\"删除文件\" data-url=\"\" data-key=\"\"><i class=\"glyphicon glyphicon-trash\"></i></button>\n" +
                                                "</div>" +
                                                "</div>"
                                    }
                                    <#else>
                                    browseOnZoneClick: true
                                    </#if>
                                });
                                $("#img3").fileinput({
                                    language: 'zh',
                                    overwriteInitial: true,
                                    maxFileSize: 1500,
                                    showUpload: false,
                                    showRemove: false,
                                    showClose: false,
                                    showCaption: false,
                                    showCancel: false,
                                    showBrowse: false,
                                    <#if room.image[2]??>
                                    browseOnZoneClick: true,
                                    allowedFileExtensions: ["jpg", "png", "jpeg"],
                                    initialPreview: ["${image + room.image[2]}"],
                                    initialPreviewAsData: true,
                                    initialPreviewFileType: 'image',
                                    layoutTemplates: {
                                        actions: "<div class=\"file-actions\">" +
                                                "<div class=\"file-footer-buttons\">" +
                                                " <button type=\"button\" class=\"kv-file-remove btn btn-sm btn-kv btn-default btn-outline-secondary\" title=\"删除文件\" data-url=\"\" data-key=\"\"><i class=\"glyphicon glyphicon-trash\"></i></button>\n" +
                                                "</div>" +
                                                "</div>"
                                    }
                                    <#else>
                                    browseOnZoneClick: true
                                    </#if>
                                });
                                $(function () {
                                    $(".kv-file-remove").on("click", function (e) {
                                        var preview = $(this).parent().parent().parent().parent().parent();
                                        var container = preview.parent().parent().parent().find("input");
                                        var rmv = $("#remove");
                                        if (rmv.val() == 0) {
                                            $("#remove").val(container.data("id"))
                                        } else {
                                            $("#remove").val($("#remove").val() + "," + container.data("id"))
                                        }
                                        preview.empty();
                                        preview.attr("tabindex", "-1");
                                        preview.append("<div class='file-drop-zone-title'>拖拽文件到这里 …<br>(或点击文件按钮选择文件)</div>");
                                        e.stopPropagation();
                                    })
                                })
                            </script>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 widget-container-col">
        <div class="widget-box">
            <div class="widget-header widget-header-custom">
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
                            <div class="input-group col-xs-10 col-sm-9">
                                <@spring.formInput "room.layerHeight" "class='form-control' step='0.01' required" "number"/>
                                <span class="input-group-btn">
                                   <button class="btn btn-sm btn-default" type="button">
                                        米
                                    </button>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 control-label no-padding-right"><span
                                style="color: red">*</span>场地类型</label>
                        <div class="col-sm-10 form-radio-group">
                            <input type="radio" id="type0" name="type"
                                   value="室内" ${(room.type == "室内")?string("checked='checked'","")}>
                            <label for="type0">室内</label>
                            <input type="radio" id="type1" name="type"
                                   value="室外" ${(room.type == "室外")?string("checked='checked'","")}>
                            <label for="type1">室外</label>
                        </div>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 control-label no-padding-right">
                        <span style="color: red">*</span>发布类型</label>
                    <div class="col-sm-10 form-radio-group">
                        <input type="radio" id="publish0" name="publish"
                               value="PRIVATE" ${(room.publish.name() == "PRIVATE")?string("checked='checked'","")}>
                        <label for="publish0">自有</label>
                        <input type="radio" id="publish1" name="publish"
                               value="PUBLIC" ${(room.publish.name() == "PUBLIC")?string("checked='checked'","")}>
                        <label for="publish1">公开</label>
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 control-label no-padding-right"
                       for="form-field-1"><span style="color: red">*</span>面积</label>
                <div class="col-sm-10">
                    <div class="input-group col-xs-10 col-sm-9">
                        <@spring.formInput "room.layerArea" "class='form-control' step='0.01' required" "number"/>
                        <span class="input-group-btn">
                           <button class="btn btn-sm btn-default" type="button">
                                m²
                            </button>
                        </span>
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 control-label no-padding-right"
                       for="form-field-1"><span style="color: red">*</span>容纳人数</label>
                <div class="col-sm-10">
                    <div class="input-group col-xs-10 col-sm-9">
                    <@spring.formInput "room.quota" "class='form-control' required" "number"/>
                        <span class="input-group-btn">
                           <button class="btn btn-sm btn-default" type="button">
                                人
                            </button>
                        </span>
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
                        <div class="col-sm-10">
                            <div class="input-group col-xs-10 col-sm-9">
                            <@spring.formInput "room.price" "class='form-control' required" "number"/>
                                <span class="input-group-btn">
                                   <button class="btn btn-sm btn-default" type="button">
                                        元
                                    </button>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 control-label no-padding-right"
                               for="form-field-1"><span style="color: red">*</span>出租收费</label>
                        <div class="col-sm-10 form-radio-group">
                            <input type="radio" id="free0" name="free"
                                   value="true" ${(room.free)?string("checked='checked'","")}>
                            <label for="free0">免费</label>
                            <input type="radio" id="free1" name="free"
                                   value="false" ${(room.free)?string("","checked='checked'")}>
                            <label for="free1">收费</label>
                        </div>
                    </div>
                    <div class="form-group row" id="freeOrgContainer">
                        <label class="col-sm-2 control-label no-padding-right"
                               for="form-field-1"><span style="color: red">*</span>免费组织</label>
                        <div class="col-sm-10 form-radio-group">
                            <select multiple="multiple" id="freeOrg" name="freeOrg"
                                    class="multiselect col-xs-10 col-sm-9">
                                <#list current_orgs as org>
                                    <#if org.roomId??>
                                        <option value="${org.orgId}" selected>${org.orgName}</option>
                                    <#else>
                                        <option value="${org.orgId}">${org.orgName}</option>
                                    </#if>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 control-label no-padding-right"
                               for="form-field-1"><span style="color: red">*</span>出租方式</label>
                        <div class="col-sm-10 form-radio-group">
                            <input type="radio" id="rental0" name="rental"
                                   value="HOUR" ${(room.rental.name() == "HOUR")?string("checked='checked'","")}>
                            <label for="rental0">小时</label>
                            <input type="radio" id="rental1" name="rental"
                                   value="PERIOD" ${(room.rental.name() == "PERIOD")?string("checked='checked'","")}>
                            <label for="rental1">整段</label>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 control-label no-padding-right"
                               for="form-field-1"><span style="color: red">*</span>开放日期</label>
                        <div class="col-sm-10">
                            <div class="col-xs-12 col-sm-9 input-group input-daterange">
                                    <@spring.formInput "room.openDate",'class="form-control"'/>
                                <div class="input-group-addon">至</div>
                                    <@spring.formInput "room.closeDate",'class="form-control"'/>
                            </div>
                        </div>
                        <script type="text/javascript">
                            $('.input-daterange input').each(function () {
                                $(this).datepicker({language: 'zh'});
                            });
                        </script>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 control-label no-padding-right"
                               for="form-field-1"><span style="color: red">*</span>最短起租时间</label>
                        <div class="col-sm-10 ">
                            <div class="input-group col-xs-10 col-sm-9">
                                <@spring.formInput "room.unit" "class='form-control'" 'number'/>
                                <span class="input-group-btn">
                                   <button class="btn btn-sm btn-default" type="button">
                                        半小时
                                    </button>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 control-label no-padding-right"
                               for="form-field-1"><span style="color: red">*</span>最短续租时间</label>
                        <div class="col-sm-10">
                            <div class="input-group col-xs-10 col-sm-9">
                                <@spring.formInput "room.renew" "class='form-control'" 'number'/>
                                <span class="input-group-btn">
                                   <button class="btn btn-sm btn-default" type="button">
                                        半小时
                                    </button>
                                </span>
                            </div>
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
                            <@spring.formInput "room.supervisorMobile","class='col-xs-10 col-sm-9' required" "tel"/>
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
                                <input type="radio" id="payable0" name="payable"
                                       value="true" ${(room.payable)?string("checked='checked'","")}>
                                <label for="payable0">是</label>
                                <input type="radio" id="payable1" name="payable"
                                       value="false" ${(room.payable)?string("","checked='checked'")}>
                                <label for="payable1">否</label>
                            </div>
                        </div>
                        <div class="form-group row col-xs-12 col-sm-6">
                            <label class="col-sm-2 control-label no-padding-right"
                                   for="form-field-1"><span style="color: red">*</span>确认方式</label>
                            <div class="col-sm-10 form-radio-group">
                                <input type="radio" id="cfm0" name="cfm"
                                       value="AUTO" ${(room.cfm.name() == "AUTO")?string("checked='checked'","")}>
                                <label for="cfm0">无需确认</label>
                                <input type="radio" id="cfm1" name="cfm"
                                       value="BEFORE" ${(room.cfm.name() == "BEFORE")?string("checked='checked'","")}>
                                <label for="cfm1">确认后付款</label>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group row col-xs-12 col-sm-6">
                            <label class="col-sm-2 control-label no-padding-right"
                                   for="form-field-1"><span style="color: red">*</span>退款时限</label>
                            <div class="col-xs-12 col-sm-9">
                                <div class="input-group">
                                    <@spring.formInput "room.rlt" "class='form-control' required"/>
                                    <span class="input-group-btn">
                                       <button class="btn btn-sm btn-default" type="button">
                                            小时
                                       </button>
                                    </span>
                                </div>
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
                               for="form-field-1">免费服务</label>
                        <div class="col-sm-10">
                            <div class="col-xs-12 col-sm-9">
                                <@spring.formInput "room.freeService","class='form-control' data-role='tagsinput' "/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 control-label no-padding-right"
                               for="form-field-1">会场简介</label>
                        <div class="col-sm-10">
                            <@spring.formTextarea "room.resume","class='col-xs-10 col-sm-9'"/>
                            <link rel="stylesheet" href="/assets/css/froala_editor/froala_editor.css"/>
                            <link rel="stylesheet" href="/assets/css/froala_editor/froala_style.css">
                            <link rel="stylesheet" href="/assets/css/froala_editor/plugins/code_view.css">
                            <link rel="stylesheet" href="/assets/css/froala_editor/plugins/colors.css">
                            <link rel="stylesheet" href="/assets/css/froala_editor/plugins/emoticons.css">
                            <link rel="stylesheet" href="/assets/css/froala_editor/plugins/image_manager.css">
                            <link rel="stylesheet" href="/assets/css/froala_editor/plugins/image.css">
                            <link rel="stylesheet" href="/assets/css/froala_editor/plugins/line_breaker.css">
                            <link rel="stylesheet" href="/assets/css/froala_editor/plugins/table.css">
                            <link rel="stylesheet" href="/assets/css/froala_editor/plugins/char_counter.css">
                            <link rel="stylesheet" href="/assets/css/froala_editor/plugins/video.css">
                            <link rel="stylesheet" href="/assets/css/froala_editor/plugins/fullscreen.css">
                            <link rel="stylesheet" href="/assets/css/froala_editor/plugins/quick_insert.css">
                            <link rel="stylesheet" href="/assets/css/froala_editor/plugins/file.css">
                            <link rel="stylesheet" href="/assets/css/froala_editor/themes/gray.css">

                            <script type="text/javascript" src="/assets/js/froala_editor/froala_editor.min.js"></script>
                            <script type="text/javascript" src="/assets/js/froala_editor/plugins/align.min.js"></script>
                            <script type="text/javascript"
                                    src="/assets/js/froala_editor/plugins/code_beautifier.min.js"></script>
                            <script type="text/javascript"
                                    src="/assets/js/froala_editor/plugins/code_view.min.js"></script>
                            <script type="text/javascript"
                                    src="/assets/js/froala_editor/plugins/colors.min.js"></script>
                            <script type="text/javascript"
                                    src="/assets/js/froala_editor/plugins/draggable.min.js"></script>
                            <script type="text/javascript"
                                    src="/assets/js/froala_editor/plugins/emoticons.min.js"></script>
                            <script type="text/javascript"
                                    src="/assets/js/froala_editor/plugins/font_size.min.js"></script>
                            <script type="text/javascript"
                                    src="/assets/js/froala_editor/plugins/font_family.min.js"></script>
                            <script type="text/javascript" src="/assets/js/froala_editor/plugins/image.min.js"></script>
                            <script type="text/javascript" src="/assets/js/froala_editor/plugins/file.min.js"></script>
                            <script type="text/javascript"
                                    src="/assets/js/froala_editor/plugins/image_manager.min.js"></script>
                            <script type="text/javascript"
                                    src="/assets/js/froala_editor/plugins/line_breaker.min.js"></script>
                            <script type="text/javascript" src="/assets/js/froala_editor/plugins/link.min.js"></script>
                            <script type="text/javascript" src="/assets/js/froala_editor/plugins/lists.min.js"></script>
                            <script type="text/javascript"
                                    src="/assets/js/froala_editor/plugins/paragraph_format.min.js"></script>
                            <script type="text/javascript"
                                    src="/assets/js/froala_editor/plugins/paragraph_style.min.js"></script>
                            <script type="text/javascript" src="/assets/js/froala_editor/plugins/video.min.js"></script>
                            <script type="text/javascript" src="/assets/js/froala_editor/plugins/table.min.js"></script>
                            <script type="text/javascript" src="/assets/js/froala_editor/plugins/url.min.js"></script>
                            <script type="text/javascript"
                                    src="/assets/js/froala_editor/plugins/entities.min.js"></script>
                            <script type="text/javascript"
                                    src="/assets/js/froala_editor/plugins/char_counter.min.js"></script>
                            <script type="text/javascript"
                                    src="/assets/js/froala_editor/plugins/inline_style.min.js"></script>
                            <script type="text/javascript"
                                    src="/assets/js/froala_editor/plugins/quick_insert.min.js"></script>
                            <script type="text/javascript" src="/assets/js/froala_editor/plugins/save.min.js"></script>
                            <script type="text/javascript"
                                    src="/assets/js/froala_editor/plugins/fullscreen.min.js"></script>
                            <script type="text/javascript" src="/assets/js/froala_editor/plugins/quote.min.js"></script>
                            <script type="text/javascript" src="/assets/js/froala_editor/languages/zh_cn.js"></script>
                            <script type="text/javascript">
                                new FroalaEditor("#resume", {
                                    language: 'zh_cn',
                                    theme: 'gray',
                                    toolbarButtons: ['fullscreen', 'bold', 'italic', 'underline', 'strikeThrough', 'subscript', 'superscript', 'fontFamily', 'fontSize', '|', 'color', 'emoticons', 'inlineStyle', 'paragraphStyle', '|', 'paragraphFormat', 'align', 'formatOL', 'formatUL', 'outdent', 'indent', '-', 'insertLink', 'insertImage', 'insertTable', '|', 'quote', 'insertHR', 'undo', 'redo', 'clearFormatting', 'selectAll', 'html'],
                                    quickInsertButtons: ['image', 'table', 'ol', 'ul'],
                                    imageUploadURL: "/admin/images",
                                    imageUploadParam: "file",
                                    imageUploadMethod: "POST",
                                    imageAllowedTypes: ["jpeg", "jpg", "png"]
                                })
                            </script>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 control-label no-padding-right"
                               for="form-field-1">收费服务</label>
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
                                    <#if support.id??>
                                      <td class="center">
                                          <input type="checkbox" name="supportList[${support_index}].supportId"
                                                 value="${support.supportId}"
                                                 class="spItem" checked="checked"/>
                                      </td>
                                    <#else>
                                      <td class="center">
                                          <input type="checkbox" name="supportList[${support_index}].supportId"
                                                 value="${support.supportId}"
                                                 class="spItem"/>
                                      </td>
                                    </#if>
                                    <td class="center">${support.name}</td>
                                    <td class="center">
                                        <img src="${support.cover}" class="img-50"/>
                                    </td>
                                    <td class="center">
                                        <input name="supportList[${support_index}].remark"
                                               class="col-xs-12 col-sm-12" value="${support.remark!}">
                                    </td>
                                    <td class="center">
                                        <div class="input-group">
                                            <input name="supportList[${support_index}].price"
                                                   class="form-control" value="${support.price!}">
                                            <span class="input-group-btn">
                                               <button class="btn btn-sm btn-default" type="button">
                                                    元
                                               </button>
                                            </span>
                                        </div>

                                    </td>
                                </tr>
                                </#list>
                                </tbody>
                            </table>
                            <script>
                                $(function () {
                                    $('#supportList').DataTable({
                                        searching: false,
                                        lengthChange: false,
                                        paging: false,
                                        language: {
                                            sZeroRecords: "数据为空",
                                            sInfo: "",
                                            sInfoEmpty: ""
                                        },
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
<script type="text/javascript">
    var isFree = $("input[name='free']:checked").val();
    $(function () {
        var isFree = $("input[name='free']:checked").val();
        if (isFree) {
            $("#freeOrgContainer").show();
        } else {
            $("#freeOrgContainer").hide();
        }
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
        $("input[name='free']").on("change", function () {
            if ($(this).val() === 'true') {
                $("#freeOrgContainer").hide();
            } else {
                $("#freeOrgContainer").show();
            }
        });
    })
</script>

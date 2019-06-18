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
                                showCancel: true,
                                showBrowse: false,
                                browseOnZoneClick: true,
                                allowedFileTypes: ['image'],
                                removeIcon: '<i class="glyphicon glyphicon-trash"></i>',
                                removeClass: 'btn btn-default',
                                elErrorContainer: '#kv-avatar-errors-1',
                                msgErrorClass: 'alert alert-block alert-danger',
                                layoutTemplates: {main2: '{preview} {browse}'}
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
                    <div class="form-group row" id="freeOrgContainer">
                        <label class="col-sm-2 control-label no-padding-right"
                               for="form-field-1"><span style="color: red">*</span>免费组织</label>
                        <div class="col-sm-10 form-radio-group">
                            <select multiple="multiple" id="freeOrg" name="freeOrg"
                                    class="multiselect col-xs-10 col-sm-9">
                                <#list current_orgs as org>
                                    <option value="${org.id}">${org.text}</option>
                                </#list>
                            </select>
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
                               for="form-field-1"><span style="color: red">*</span>起租时间</label>
                        <div class="col-sm-10 ">
                            <div class="input-group col-xs-10 col-sm-9">
                                <@spring.formInput "room.unit" "class='form-control'"/>
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
                               for="form-field-1"><span style="color: red">*</span>续租时间</label>
                        <div class="col-sm-10">
                            <div class="input-group col-xs-10 col-sm-9">
                                <@spring.formInput "room.renew" "class='form-control'"/>
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
                            <div class="input-group col-xs-10 col-sm-9">
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
                                    <td class="center">
                                        <img src="${image + '/'+ support.cover}" class="img-50"/>
                                    </td>
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
                                        lengthChange: false,
                                        paging: false,
                                        language: {
                                            sInfo: ""
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
    console.log("##" + isFree);
    $(function () {
        var isFree = $("input[name='free']:checked").val();
        if (isFree) {
            $("#freeOrgContainer").hide();
        } else {
            $("#freeOrgContainer").show();
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
            console.log("选择改变::" + $(this).val());
            if ($(this).val() === 'true') {
                $("#freeOrgContainer").show();
            } else {
                $("#freeOrgContainer").hide();
            }
        });
    })
</script>

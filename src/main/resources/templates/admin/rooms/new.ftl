<#import "../layout/application.ftl" as layout>
<#import "/spring.ftl" as spring />
<@layout.myLayout>

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
            <a href="/admin/rooms/">场地管理</a>
        </li>
        <li class="active">新增场地</li>
    </ul>


    <div class="nav-search" id="nav-search">
        <form class="form-search">
            <span class="input-icon">
                <input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input"
                       autocomplete="off"/>
                <i class="icon-search nav-search-icon"></i>
            </span>
        </form>
    </div>
</div>

<div class="page-content">

    <div class="widget-box">
        <div class="widget-header widget-header-flat">
            <h4 class="widget-title">场地新增</h4>
        </div>
        <div class="widget-body">
            <div class="widget-main">
                <div class="row">
                    <div class="col-xs-12">
                        <form action="/admin/rooms" role="form" class="form-horizontal" method="post">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <input type="hidden" name="staffId" value="${current_operator.getId()}">
                            <@spring.bind path="room"/>
                            <div class="row">
                                <div class="col-lg-6">
                                    <fieldset class="fixed-border">
                                        <legend class="fixed-border">基础信息</legend>
                                        <div class="form-group row">
                                            <label class="col-sm-3 control-label no-padding-right"
                                                   for="form-field-1">名字</label>
                                            <div class="col-sm-9">
                                                <@spring.formInput "room.name","class='col-xs-10 col-sm-5' placeholder='会议室名称'"/>
                                                <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                                <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                            </span>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-sm-3 control-label no-padding-right"
                                                   for="form-field-1">编号</label>
                                            <div class="col-sm-9">
                                            <@spring.formInput "room.serialNo" "class='col-xs-10 col-sm-5' placeholder='会议室名称'"/>
                                                <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                                <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                            </span>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-sm-3 control-label no-padding-right"
                                                   for="form-field-1">幢号</label>
                                            <div class="col-sm-9">
                                            <@spring.formInput "room.buildingNo" "class='col-xs-10 col-sm-5' placeholder='会议室名称'"/>
                                                <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                                <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                            </span>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-sm-3 control-label no-padding-right"
                                                   for="form-field-1">楼层</label>
                                            <div class="col-sm-9">
                                            <@spring.formInput "room.floorNo" "class='col-xs-10 col-sm-5' placeholder='会议室名称'"/>
                                                <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                                <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                            </span>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-sm-3 control-label no-padding-right"
                                                   for="form-field-1">房号</label>
                                            <div class="col-sm-9">
                                            <@spring.formInput "room.floorNo" "class='col-xs-10 col-sm-5' placeholder='会议室名称'"/>
                                                <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                                <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                            </span>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-sm-3 control-label no-padding-right"
                                                   for="form-field-1">层高</label>
                                            <div class="col-sm-9">
                                            <@spring.formInput "room.layerHeight" "class='col-xs-10 col-sm-5' placeholder='会议室名称'"/>
                                                <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                                <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                            </span>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-sm-3 control-label no-padding-right"
                                                   for="form-field-1">面积</label>
                                            <div class="col-sm-9">
                                            <@spring.formInput "room.layerArea" "class='col-xs-10 col-sm-5' placeholder='会议室名称'"/>
                                                <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                                <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                            </span>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-sm-3 control-label no-padding-right"
                                                   for="form-field-1">容纳人数</label>
                                            <div class="col-sm-9">
                                            <@spring.formInput "room.quota" "class='col-xs-10 col-sm-5' placeholder='会议室名称'"/>
                                                <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                                <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                            </span>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-sm-3 control-label no-padding-right"
                                                   for="form-field-1">介绍</label>
                                            <div class="col-sm-9">
                                            <@spring.formTextarea "room.resume" "class='col-xs-10 col-sm-5' placeholder='会议室名称'"/>
                                                <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                                <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                            </span>
                                            </div>
                                        </div>
                                    </fieldset>
                                </div>
                                <div class="col-lg-6">
                                    <fieldset class="fixed-border">
                                        <legend class="fixed-border">其他</legend>
                                        <div class="form-group row">
                                            <label class="col-sm-3 control-label no-padding-right"
                                                   for="form-field-1">负责人</label>
                                            <div class="col-sm-9">
                                                <@spring.formSingleSelect "room.rental",rentals,"class='col-xs-10 col-sm-5'"/>
                                                <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                                    <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                                </span>
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label class="col-sm-3 control-label no-padding-right"
                                                   for="form-field-1">负责人</label>
                                            <div class="col-sm-9">
                                                <@spring.formInput "room.supervisor" "class='col-xs-10 col-sm-5' placeholder='会议室名称'"/>
                                                <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                                    <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                                </span>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-sm-3 control-label no-padding-right"
                                                   for="form-field-1">负责人电话</label>
                                            <div class="col-sm-9">
                                                <@spring.formInput "room.supervisorMobile" "class='col-xs-10 col-sm-5' placeholder='会议室名称'"/>
                                                <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                                    <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                                </span>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-sm-3 control-label no-padding-right"
                                                   for="form-field-1">负责人邮箱</label>
                                            <div class="col-sm-9">
                                                <@spring.formInput "room.supervisorEmail" "class='col-xs-10 col-sm-5' placeholder='会议室名称'"/>
                                                <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                                    <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                                </span>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-sm-3 control-label no-padding-right"
                                                   for="form-field-1">确认方式</label>
                                            <div class="col-sm-9">
                                                <@spring.formSingleSelect "room.confirmation",confirmations,"class='col-xs-10 col-sm-5' placeholder='会议室名称'"/>
                                                <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                                    <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                                </span>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-sm-3 control-label no-padding-right"
                                                   for="form-field-1">支付方式</label>
                                            <div class="col-sm-9">
                                                <@spring.formSingleSelect "room.supervisor",payments,"class='col-xs-10 col-sm-5' placeholder='会议室名称'"/>
                                                <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                                    <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                                </span>
                                            </div>
                                        </div>
                                    </fieldset>
                                </div>
                            </div>

                            <div class="row">
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
</div>
</div>
<script type="text/javascript">
    $(function () {
        $("#expiredType").on("change", function () {
            var selectedVal = $(this).val();
            if (selectedVal == "convention") {
                $("#dateDiv").show();
                $("#dayDiv").hide();
            } else {
                $("#dateDiv").hide();
                $("#dayDiv").show();
            }
        })

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

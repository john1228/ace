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
            <a href="/admin/rooms/">优惠券管理</a>
        </li>
        <li class="active">新增会议室</li>
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
    <div class="row">
        <div class="col-xs-12">
            <div class="page-header">
                <h1>会议室新增</h1>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <form action="/admin/rooms/" role="form" class="form-horizontal" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <@spring.bind path="room"/>
                        <fieldset class="fixed-border">
                            <legend class="fixed-border">基础信息</legend>
                            <div class="form-group">
                                <div class="col-sm-6">
                                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1">名字</label>
                                    <div class="col-sm-9">
                                        <@spring.formInput "room.name" "class='col-xs-10 col-sm-5' placeholder='会议室名称'"/>
                                        <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                            <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                        </span>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1">类型</label>
                                    <div class="col-sm-9">
                                        <@spring.formInput "room.address","class='col-xs-10 col-sm-5' placeholder='会议室地址'"/>
                                        <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                            <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class="space-4"></div>
                            <div class="form-group">
                            </div>
                        </fieldset>
                        <fieldset class="fixed-border">
                            <legend class="fixed-border">限定</legend>
                            <div class="form-group">
                                <div class="col-sm-6">
                                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1">
                                        指定组织 </label>
                                    <div class="col-sm-9">
                                        <input type="text" id="form-field-1" placeholder="属性名"
                                               class="col-xs-10 col-sm-5"
                                               name="limit_org"/>
                                        <span style="color:red; height:25px;line-height:25px;overflow:hidden;"><b>&nbsp;*</b></span>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1">
                                        指定会场 </label>
                                    <div class="col-sm-9">
                                        <input type="text" id="form-field-1" placeholder="属性名"
                                               class="col-xs-10 col-sm-5"
                                               name="limit_room"/>
                                        <span style="color:red; height:25px;line-height:25px;overflow:hidden;"><b>&nbsp;*</b></span>
                                    </div>
                                </div>
                            </div>

                        </fieldset>

                        <div class="space-4"></div>

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
            <!-- PAGE CONTENT ENDS -->
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

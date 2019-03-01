<#import "../layout/application.ftl" as layout>
<#import "/spring.ftl" as spring />
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
            <a href="/admin/coupons/">优惠券管理</a>
        </li>
        <li class="active">优惠券新增</li>
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
                <h1>优惠券新增</h1>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <form action="/admin/coupons/" role="form" class="form-horizontal" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <@spring.bind path="coupon"/>
                        <@spring.formHiddenInput "coupon.staffId"/>
                        <div class="row">
                            <div class="col-lg-6">
                                <fieldset class="fixed-border">
                                    <legend class="fixed-border">基础信息</legend>
                                    <div class="form-group row">
                                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">
                                            请输入名字 </label>
                                        <div class="col-sm-9">
                                            <@spring.formInput "coupon.name" "class='col-xs-10 col-sm-5' placeholder='请填写优惠券名字'"/>
                                            <span style="color:red; height:25px;line-height:25px;overflow:hidden;"><b>&nbsp;*<@spring.showErrors "<br>"/></b></span>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">
                                            请选择类型</label>
                                        <div class="col-sm-9">
                                            <@spring.formSingleSelect "coupon.type",couponType,"class='col-xs-10 col-sm-5'"/>
                                            <span style="color:red; height:25px;line-height:25px;overflow:hidden;"><b>&nbsp;*<@spring.showErrors "<br>"/></b></span>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">
                                            请输入数量</label>
                                        <div class="col-sm-9">
                                            <@spring.formInput "coupon.amount","class='col-xs-10 col-sm-5' placeholder='请输入优惠券数量'"/>
                                            <span style="color:red; height:25px;line-height:25px;overflow:hidden;"><b>&nbsp;*<@spring.showErrors "<br>"/></b></span>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">
                                            优惠金额 </label>
                                        <div class="col-sm-9">
                                            <@spring.formInput "coupon.discount" "class='col-xs-10 col-sm-5' placeholder='请填写优惠券优惠金额'"/>
                                            <span style="color:red; height:25px;line-height:25px;overflow:hidden;"><b>&nbsp;*<@spring.showErrors "<br>"/></b></span>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">
                                            订单金额 </label>
                                        <div class="col-sm-9">
                                            <@spring.formInput "coupon.min" "class='col-xs-10 col-sm-5' placeholder='请填写可使用优惠券的最小订单金额'"/>
                                            <span style="color:red; height:25px;line-height:25px;overflow:hidden;"><b>&nbsp;*<@spring.showErrors "<br>"/></b></span>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">
                                            有效期类型 </label>
                                        <div class="col-sm-9">
                                            <@spring.formSingleSelect "coupon.expiredType",expiredType,"class='col-xs-10 col-sm-5''"/>
                                            <span style="color:red; height:25px;line-height:25px;overflow:hidden;"><b>&nbsp;*<@spring.showErrors "<br>"/></b></span>
                                        </div>
                                    </div>
                                    <div class="form-group row" id="dayDiv" hidden>
                                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">
                                            有效天数 </label>
                                        <div class="col-sm-9">
                                            <@spring.formInput "coupon.duration","class='col-xs-10 col-sm-5' placeholder='请填写优惠券领取后的有效天数'"/>
                                            <span style="color:red; height:25px;line-height:25px;overflow:hidden;"><b>&nbsp;*</b></span>
                                        </div>
                                    </div>
                                    <div class="form-group row" id="dateDiv" hidden>
                                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">
                                            有效期 </label>
                                        <div class="col-sm-9">
                                            <input type="text" id="dateRange" class="col-xs-10 col-sm-8">
                                            <@spring.formHiddenInput "coupon.startDate"/>
                                            <@spring.formHiddenInput "coupon.endDate"/>
                                            <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                                <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                            </span>
                                        </div>
                                    </div>
                                </fieldset>
                            </div>
                            <div class="col-lg-6">
                                <fieldset class="fixed-border">
                                    <legend class="fixed-border">限定</legend>
                                    <div class="form-group row">
                                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">
                                            指定组织 </label>
                                        <div class="col-sm-9">
                                            <@spring.formMultiSelect "coupon.limitOrg",expiredType,"class='multiselect col-xs-10 col-sm-8''"/>
                                            <span style="color:red; height:25px;line-height:25px;overflow:hidden;"><b>&nbsp;*</b></span>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">
                                            指定会场 </label>
                                        <div class="col-sm-9">
                                            <@spring.formMultiSelect "coupon.limitRoom",expiredType,"class='multiselect col-xs-10 col-sm-8''"/>
                                            <span style="color:red; height:25px;line-height:25px;overflow:hidden;"><b>&nbsp;*</b></span>
                                        </div>
                                    </div>
                                </fieldset>
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
            <!-- PAGE CONTENT ENDS -->
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        $('.multiselect').multiselect({
            nonSelectedText: "请选择",
            nSelectedText: "已选择",
            allSelectedText: "全选",
            enableFiltering: true,
            enableHTML: true,
            buttonContainer: '<div class="col-xs-10 col-sm-8" />',
            buttonClass: 'btn btn-white btn-primary col-xs-12 col-sm-12',
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

        $("#expiredType").on("change", function () {
            var selectedVal = $(this).val();
            if (selectedVal == "CONVENTION") {
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

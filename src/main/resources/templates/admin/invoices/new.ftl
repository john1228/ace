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
            <a href="/admin/invoices/">发票管理</a>
        </li>
        <li class="active">新增发票</li>
    </ul>
</div>

<div class="page-content">
    <h4 class="header smaller lighter blue">
        <span>优惠券新增</span>
    </h4>
    <form action="/admin/invoices" role="form" class="form-horizontal" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" name="orderNo" value="${orderNo}"/>
        <@spring.bind path="invoice"/>
        <div class="row">
            <div class="col-xs-12 col-sm-12 widget-container-col">
                <div class="widget-box">
                    <div class="widget-header widget-header-custom">
                        <h5 class="widget-title">发票信息</h5>
                    </div>
                    <div class="widget-body">
                        <div class="widget-main padding-8">
                            <div class="form-group row">
                                <label class="col-sm-2 control-label no-padding-right">发票类型</label>
                                <div class="col-sm-10">
                                    <div class="btn-group" data-toggle="buttons">
                                        <label class="btn btn-primary">
                                            <input type="radio" name="type" value="GVATI">增值税普通发票
                                        </label>
                                        <label class="btn btn-primary active">
                                            <input type="radio" name="type" value="VATI" checked>普通增值税专用发票
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <script type="text/javascript">
                                $(function () {
                                    $("input[name=type]").on("change", function () {
                                        if ($(this).val() == "GVATI") {
                                            $(".gvati").hide();
                                            $(".gvati").find(":input").attr("disabled", false);
                                            $(".vati").show();
                                            $(".vati").find(":input").attr("disabled", false);
                                        } else {
                                            $(".vati").hide();
                                            $(".vati").find(":input").attr("disabled", false);
                                            $(".gvati").show();
                                            $(".gvati").find(":input").attr("disabled", false);
                                        }
                                    })
                                })
                            </script>
                            <div class="form-group row vati" style="display: none">
                                <label class="col-sm-2 control-label no-padding-right">类型</label>
                                <div class="col-sm-10">
                                    <div class="btn-group" data-toggle="buttons">
                                        <label class="btn btn-primary active">
                                            <input type="radio" name="content[type]" value="公司" checked>公司
                                        </label>
                                        <label class="btn btn-primary">
                                            <input type="radio" name="content[type]" value="个人">个人
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group row vati gvati">
                                <label class="col-sm-2 control-label no-padding-right">抬头</label>
                                <div class="col-sm-10">
                                    <input type="text" name="content[title]" class="col-xs-10 col-sm-5"/>
                                </div>
                            </div>
                            <div class="form-group row vati gvati">
                                <label class="col-sm-2 control-label no-padding-right">纳税人识别号</label>
                                <div class="col-sm-10">
                                    <input type="text" name="content[taxpayer]" class="col-xs-10 col-sm-5"/>
                                </div>
                            </div>
                            <div class="form-group row gvati">
                                <label class="col-sm-2 control-label no-padding-right">开户行</label>
                                <div class="col-sm-10">
                                    <input type="text" name="content[bank]" class="col-xs-10 col-sm-5"/>
                                </div>
                            </div>
                            <div class="form-group row gvati">
                                <label class="col-sm-2 control-label no-padding-right">银行卡号</label>
                                <div class="col-sm-10">
                                    <input type="text" name="content[bankNo]" class="col-xs-10 col-sm-5"/>
                                </div>
                            </div>
                            <div class="form-group row gvati">
                                <label class="col-sm-2 control-label no-padding-right">地址</label>
                                <div class="col-sm-10">
                                    <input type="text" name="content[address]" class="col-xs-10 col-sm-5"/>
                                </div>
                            </div>
                            <div class="form-group row gvati">
                                <label class="col-sm-2 control-label no-padding-right">电话</label>
                                <div class="col-sm-10">
                                    <input type="text" name="content[mobile]" class="col-xs-10 col-sm-5"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="widget-box">
                    <div class="widget-header widget-header-custom">
                        <h5 class="widget-title">寄送信息</h5>
                    </div>
                    <div class="widget-body">
                        <div class="widget-main padding-8">
                            <div class="form-group row">
                                <label class="col-sm-2 control-label no-padding-right">寄送方式</label>
                                <div class="col-sm-10">
                                    <div class="btn-group" data-toggle="buttons">
                                        <label class="btn btn-primary active">
                                            <input type="radio" name="method" value="EMAIL" checked>邮件
                                        </label>
                                        <label class="btn btn-primary">
                                            <input type="radio" name="method" value="EXPRESS">快递
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <script type="text/javascript">
                                $(function () {
                                    $("input[name=method]").on("change", function () {
                                        console.log($(this).val());
                                        if ($(this).val() == "EMAIL") {
                                            $(".express").hide();
                                            $(".express").find(":input").attr("disabled", false);
                                            $(".email").show();
                                            $(".email").find(":input").attr("disabled", false);
                                        } else {
                                            $(".email").hide();
                                            $(".email").find(":input").attr("disabled", false);
                                            $(".express").show();
                                            $(".express").find(":input").attr("disabled", false);
                                        }
                                    })
                                })
                            </script>
                            <div class="form-group row express">
                                <label class="col-sm-2 control-label no-padding-right">联系人</label>
                                <div class="col-sm-10">
                                    <input type="text" name="address[name]" class="col-xs-10 col-sm-5"/>
                                </div>
                            </div>
                            <div class="form-group row express">
                                <label class="col-sm-2 control-label no-padding-right">联系电话</label>
                                <div class="col-sm-10">
                                    <input type="text" name="address[mobile]" class="col-xs-10 col-sm-5"/>
                                </div>
                            </div>
                            <div class="form-group row express email">
                                <label class="col-sm-2 control-label no-padding-right">联系地址</label>
                                <div class="col-sm-10">
                                    <input type="text" name="address[address]" class="col-xs-10 col-sm-5"/>
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
</@layout.myLayout>

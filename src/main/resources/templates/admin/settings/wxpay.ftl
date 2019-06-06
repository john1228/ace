<#import "../layout/application.ftl" as layout>
<#import "/spring.ftl" as spring />
<@layout.myLayout>
    <script src="/assets/js/jquery/dropzone.min.js"></script>
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
                <a href="/admin/supports">配置管理</a>
            </li>
            <li class="active">微信配置</li>
        </ul>
    </div>

    <div class="page-content">
        <div class="row">
            <div class="col-xs-12">
                <div class="page-title">
                    <h1>微信设置</h1>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <form action="/admin/settings/wxpay" role="form" class="form-horizontal" method="post">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <input type="hidden" name="_method" value="put">
                            <@spring.bind path="wxpay"/>
                            <div class="form-group row">
                                <label class="col-sm-2 control-label no-padding-right"><span style="color: red">*</span>应用appId</label>
                                <div class="col-sm-10">
                                    <@spring.formInput "wxpay.appId" "class='form-control' placeholder='请填写微信应用appid' required"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 control-label no-padding-right"><span
                                        style="color: red">*</span>商户号</label>
                                <div class="col-sm-10">
                                    <@spring.formInput "wxpay.mchId","class='form-control' placeholder='请填写微信商户号' rows=5 required"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 control-label no-padding-right"><span
                                        style="color: red">*</span>支付密钥</label>
                                <div class="col-sm-10">
                                    <@spring.formInput "wxpay.secretKey","class='form-control' placeholder='请填写微信支付密钥' required"/>
                                </div>
                            </div>
                            <div class="clearfix form-actions row">
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
</@layout.myLayout>



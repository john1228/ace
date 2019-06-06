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
        <li class="active">支付宝配置</li>
    </ul>
</div>

<div class="page-content">
    <div class="row">
        <div class="col-xs-12">
            <div class="page-title">
                <h1>支付宝设置</h1>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <form action="/admin/settings/alipay" role="form" class="form-horizontal" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="hidden" name="_method" value="put">
                        <@spring.bind path="alipay"/>
                        <div class="form-group row">
                            <label class="col-sm-2 control-label no-padding-right"><span style="color: red">*</span>商户号</label>
                            <div class="col-sm-10">
                                <@spring.formInput "alipay.seller" "class='col-xs-10 col-sm-9' placeholder='请填写支付宝商户号' required"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-2 control-label no-padding-right"><span
                                    style="color: red">*</span>密钥</label>
                            <div class="col-sm-10">
                                <@spring.formTextarea "alipay.privateKey","class='form-control' placeholder='请填写支付宝密钥' rows=5 required"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-2 control-label no-padding-right"><span
                                    style="color: red">*</span>公钥</label>
                            <div class="col-sm-10">
                                 <@spring.formInput "alipay.publicKey","class='form-control' placeholder='请填写支付宝公钥' required"/>
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



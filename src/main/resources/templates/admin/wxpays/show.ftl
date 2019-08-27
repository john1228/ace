<#import "../layout/application.ftl" as layout>
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
            <a href="/admin/alipays/">支付宝管理</a>
        </li>
        <li class="active">配置查看</li>
    </ul>
</div>

<div class="page-content">
    <div class="row">
        <div class="page-title">
            <h3>${wxpay.projectName!}</h3>
        </div>
        <div class="col-xs-12 col-sm-12">
            <div class="attribute-table">
                <div class="attribute-table-row">
                    <div class="attribute-table-label">项目编号</div>
                    <div class="attribute-table-value">
                        ${wxpay.projectId!}
                    </div>
                </div>
                <div class="attribute-table-row">
                    <div class="attribute-table-label">项目名称</div>
                    <div class="attribute-table-value">
                        ${wxpay.projectName!}
                    </div>
                </div>
                <div class="attribute-table-row">
                    <div class="attribute-table-label">微信appId</div>
                    <div class="attribute-table-value">
                        ${wxpay.appId!}
                    </div>
                </div>
                <div class="attribute-table-row">
                    <div class="attribute-table-label">商户号</div>
                    <div class="attribute-table-value">
                        ${wxpay.mchId!}
                    </div>
                </div>
                <div class="attribute-table-row">
                    <div class="attribute-table-label">密钥</div>
                    <div class="attribute-table-value">
                        ${wxpay.secretKey!}
                    </div>
                </div>
                <div class="attribute-table-row">
                    <div class="attribute-table-label">创建时间</div>
                    <div class="attribute-table-value">
                        ${wxpay.createdAt?string("yyyy-MM-dd HH:mm:ss")}
                    </div>
                </div>
                <div class="attribute-table-row">
                    <div class="attribute-table-label">更新时间</div>
                    <div class="attribute-table-value">
                        ${wxpay.updatedAt?string("yyyy-MM-dd HH:mm:ss")}
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="clearfix form-actions">
            <div class="col-md-offset-3 col-md-9">
                <button class="btn" type="reset" onclick="history.go(-1)">
                    <i class="icon-undo bigger-110"></i>
                    返回
                </button>
            </div>
        </div>
    </div>
</div>
</@layout.myLayout>
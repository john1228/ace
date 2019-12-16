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
            <li class="active">下单协议</li>
        </ul>
    </div>

    <div class="page-content">
        <div class="row">
            <div class="col-xs-12">
                <div class="page-title">
                    <h1>下单协议</h1>
                </div>
                <div class="page-content">
                    ${protocol}
                </div>
                <div class="clearfix form-actions row">
                    <div class="col-md-offset-3 col-md-9">
                        <a class="btn btn-info" href="/admin/protocol/edit">
                            <i class="icon-ok bigger-110"></i>
                            编辑
                        </a>
                    </div>
                </div>
            </div>
        </div>
</@layout.myLayout>



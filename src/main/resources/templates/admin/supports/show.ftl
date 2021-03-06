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
                <a href="/admin/supports">服务管理</a>
            </li>
            <li class="active">服务详情</li>
        </ul>
    </div>

    <div class="page-content">
        <div class="page-title">
            ${support.name}
        </div>
        <div class="row">
            <div class="attribute-table">
                <div class="attribute-table-row">
                    <div class="attribute-table-label">名字</div>
                    <div class="attribute-table-value">
                        <span>${support.name}</span>
                    </div>
                </div>
                <div class="attribute-table-row">
                    <div class="attribute-table-label">单位</div>
                    <div class="attribute-table-value">
                        <span>${support.unit}</span>
                    </div>
                </div>
                <div class="attribute-table-row">
                    <div class="attribute-table-label">图片</div>
                    <div class="attribute-table-value">
                        <img src="${image + support.cover}" class="img-150"/>
                    </div>
                </div>
            </div>
            <div class="clearfix form-actions">
                <div class="col-md-offset-3 col-md-9">
                    <a class="btn btn-info" type="button"
                       href="/admin/supports/${support.id}/edit">
                        <i class="icon-ok bigger-110"></i>
                        修改
                    </a>
                    &nbsp;&nbsp;&nbsp;
                    <button class="btn" type="reset" onclick="history.go(-1)">
                        <i class="icon-undo bigger-110"></i>
                        返回
                    </button>
                </div>
            </div>
        </div>
    </div>
</@layout.myLayout>
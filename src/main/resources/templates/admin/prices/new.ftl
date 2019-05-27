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
            <a href="/admin/prices">价格管理</a>
        </li>
        <li class="active">价格新增</li>
    </ul>
</div>

<div class="page-content">
    <div class="row">
        <div class="col-xs-12">
            <div class="page-title">
                <h1>新增价格</h1>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <form action="/admin/prices" role="form" class="form-horizontal" method="post">
                       <#include "form.ftl"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</@layout.myLayout>

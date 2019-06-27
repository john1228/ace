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
            <a href="/admin/coupons">优惠券管理</a>
        </li>
        <li class="active">优惠券新增</li>
    </ul>
</div>

<div class="page-content">
    <h4 class="header smaller lighter blue">
        <span>优惠券新增</span>
    </h4>
    <form action="/admin/system_coupons/" role="form" class="form-horizontal" method="post">
        <#include "form.ftl"/>
    </form>
</div>
</@layout.myLayout>

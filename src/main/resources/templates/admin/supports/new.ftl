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
            <a href="/admin/supports">服务管理</a>
        </li>
        <li class="active">服务新增</li>
    </ul>
</div>

<div class="page-content">
    <h4 class="header smaller lighter blue">
        <span>新增服务</span>
    </h4>
    <form action="/admin/supports" role="form" class="form-horizontal" method="post"
          enctype="multipart/form-data">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" name="staffId" value="${current_operator.getId()}">
        <@spring.bind path="support"/>
        <#include "form.ftl"/>
    </form>
</div>
</@layout.myLayout>

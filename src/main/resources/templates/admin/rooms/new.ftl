<#import "../layout/application.ftl" as layout>

<@layout.myLayout>
<link href="/assets/css/bootstrap/fileinput.css" media="all" rel="stylesheet" type="text/css">
<script src="/assets/js/bootstrap/fileinput.js"></script>
<script src="/assets/js/bootstrap/locales/zh.js"></script>

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
            <a href="/admin/rooms/?parent=${parent}">场地管理</a>
        </li>
        <li class="active">新增场地</li>
    </ul>
</div>

<div class="page-content">
    <h4 class="header smaller lighter blue">
        <span>新增场地</span>
    </h4>
    <form action="/admin/rooms" role="form" class="form-horizontal" enctype="multipart/form-data"
          data-toggle="validator" method="post">
        <#include "form.ftl">
    </form>
</div>
</div>

</@layout.myLayout>

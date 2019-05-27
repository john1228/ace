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
            <a href="/admin/sites/attributes/">场地管理</a>
        </li>
        <li class="active">新增场地</li>
    </ul><!-- .breadcrumb -->

    <div class="nav-search" id="nav-search">
        <form class="form-search">
            <span class="input-icon">
                <input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input"
                       autocomplete="off"/>
                <i class="icon-search nav-search-icon"></i>
            </span>
        </form>
    </div>
</div>

<div class="page-content">
    <div class="widget-box">
        <div class="widget-header widget-header-custom">
            <h4 class="widget-title">场地新增</h4>
        </div>
        <div class="widget-body">
            <div class="widget-main">
                <div class="row">
                    <div class="col-xs-12">
                        <form action="/admin/rooms/${room.id}" role="form" class="form-horizontal" method="post">
                            <input type="hidden" name="_method" value="put">
                            <#include "form.ftl">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div><!-- /.page-content -->
</@layout.myLayout>
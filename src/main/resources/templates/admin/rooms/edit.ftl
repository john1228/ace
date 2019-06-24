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
            <a href="/admin/rooms/">场地管理</a>
        </li>
        <li class="active">更新场地</li>
    </ul><!-- .breadcrumb -->
</div>

<div class="page-content">
    <div class="row">
        <div class="page-title">
            <h4>
                <i class="ace-icon glyphicon glyphicon-edit"></i>
                场地更新
            </h4>
        </div>
        <div class="widget-body">
            <div class="widget-main">
                <div class="row">
                    <div class="col-xs-12">
                        <form action="/admin/rooms/${room.id}/edit" role="form" class="form-horizontal"
                              enctype="multipart/form-data"
                              data-toggle="validator" method="post">
                            <input type="hidden" name="_method" value="put">
                            <#if room.cover??>
                                <input type="hidden" name="cover" value="${room.cover}"/>
                            </#if>
                            <#if room.image??>
                                <input type="hidden" name="image" value="${room.image?join(",")}">
                            </#if>
                            <#include "form.ftl">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div><!-- /.page-content -->
</@layout.myLayout>
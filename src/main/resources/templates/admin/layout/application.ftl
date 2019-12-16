<#macro myLayout>
    <#setting number_format="#">
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <#include "head.ftl">
</head>
<body class="no-skin">
    <#include "top.ftl">
<div class="main-container" id="main-container">
    <script type="text/javascript">
        $.ajaxSetup({headers: {'X-CSRF-Token': $('meta[name="_csrf"]').attr('content')}});
        try {
            ace.settings.check('main-container', 'fixed');
        } catch (e) {
        }
    </script>
    <div class="sidebar responsive ace-save-state" id="sidebar">
        <script type="text/javascript">
            try {
                ace.settings.loadState('sidebar')
            } catch (e) {
            }
        </script>
        <#include "menu_shortcuts.ftl">
        <#include "menu.ftl">
    </div>

    <div class="main-content" id="main-content">
       <#nested/>
    </div>
</div>
<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
    <i class="icon-double-angle-up icon-only bigger-110 "></i>
</a>
<#if errors??>
    <script>
        ;!function () {
            layer.ready(function () {
                layer.open({
                    type: 1,
                    title: '错误提示',
                    area: ['800px', '500px'],
                    content: "<div class='widget-box'><div class='widget-body'><div class='widget-main'><ul class='list-unstyled spaced2'>" +
                            <#list errors as objErr>
                                  "<li><i class='ace-icon fa fa-warning orange'></i>&nbsp;${objErr.defaultMessage?html}</li>" +
                            </#list>
                            "</ul></div></div></div>"
                });
            });
        }();
    </script>
</#if>
<#include "foot.ftl">
</body>
</html>
</#macro>
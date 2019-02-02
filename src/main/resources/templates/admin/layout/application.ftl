<#macro myLayout>
    <#setting number_format="#">

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
    <#include "head.ftl">
<body class="no-skin">
    <#include "top.ftl">
<div class="main-container" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
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
    <div class="footer">
        <div class="footer-inner">
            <div class="footer-content">
                <span class="bigger-120">
                    <span class="blue bolder">Ace</span>
                    Application © 2013-2014
                </span>
            </div>
        </div>
    </div>
</div>
<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
    <i class="icon-double-angle-up icon-only bigger-110"></i>
</a>
<#include "foot.ftl">
</body>
</html>
</#macro>
<#import "../layout/application.ftl" as layout>
<@layout.myLayout>
<link rel="stylesheet" href="/assets/css/fullcalendar/main.css"/>
<link rel="stylesheet" href="/assets/css/fullcalendar/list/main.css"/>
<link rel="stylesheet" href="/assets/css/fullcalendar/daygrid/main.css"/>
<link rel="stylesheet" href="/assets/css/fullcalendar/timegrid/main.css"/>
<script src="/assets/js/fullcalendar/main.js"></script>
<script src="/assets/js/fullcalendar/locales-all.js"></script>
<script src="/assets/js/fullcalendar/interaction/main.js"></script>
<script src="/assets/js/fullcalendar/list/main.js"></script>
<script src="/assets/js/fullcalendar/daygrid/main.js"></script>
<script src="/assets/js/fullcalendar/timegrid/main.js"></script>
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
        <li class="active">排期查询</li>
    </ul>
</div>
<div class="page-content">
    <div class="row">
        <div class="col-xs-12">
            <div class="row">
                <#include "search.ftl"/>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <div class="table-header">排期</div>
                    <div class="row">
                        <#include "calendar.ftl"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</@layout.myLayout>
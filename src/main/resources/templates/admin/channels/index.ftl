<#import "../layout/application.ftl" as layout>
<@layout.myLayout>
<script src="/assets/js/jquery/dataTables.min.js"></script>
<script src="/assets/js/jquery/dataTables.bootstrap.min.js"></script>
<script src="/assets/js/jquery/dataTables.buttons.min.js"></script>
<link rel="stylesheet" href="/assets/css/jquery/buttons.dataTables.min.css"/>
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
        <li class="active">会议室管理</li>
    </ul>
</div>
<div class="page-content">
    <div class="row">
        <div class="col-xs-12">
            <#include "search.ftl"/>
        </div>
        <div class="col-xs-12">
            <div class="row">
                <div class="col-xs-12">
                    <div class="row">
                        <div>
                            <div class="table-header">渠道报表</div>
                            <table id="roomList" class="table table-striped table-bordered" cellspacing="0"
                                   width="100%">
                                <thead>
                                <tr>
                                    <#if current_account.isAdmin()>
                                        <th class="center">项目</th>
                                    </#if>
                                    <th class="center">会议室</th>
                                    <th class="center">线上订单数</th>
                                    <th class="center">线下订单数</th>
                                    <th class="center">总订单</th>
                                    <th class="center">线上会议室收入</th>
                                    <th class="center">线下会议室收入</th>
                                    <th class="center">线上服务收入</th>
                                    <th class="center">线下服务收入</th>
                                    <th class="center">线上收入</th>
                                    <th class="center">线下收入</th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                    <script type="text/javascript">
                        $(function () {
                            var $table = $('#roomList');
                            $table.DataTable({
                                language: {
                                    sProcessing: "处理中...",
                                    sLengthMenu: "显示 _MENU_ 项结果",
                                    sZeroRecords: "没有匹配结果",
                                    sInfo: "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                                    sInfoEmpty: "显示第 0 至 0 项结果，共 0 项",
                                    sInfoFiltered: "",
                                    sInfoPostFix: "",
                                    sUrl: "",
                                    sEmptyTable: "暂无数据",
                                    sLoadingRecords: "载入中...",
                                    sInfoThousands: ",",
                                    oPaginate: {
                                        sFirst: "首页",
                                        sPrevious: "上一页",
                                        sNext: "下一页",
                                        sLast: "末页"
                                    }
                                },
                                bLengthChange: false,
                                searching: false,
                                processing: true,
                                serverSide: true,
                                autoWidth: false,
                                ordering: false,
                                ajax: {
                                    url: "/admin/channels/dataList",
                                    type: "POST",
                                    data: function (data) {
                                        data.projectId = $("#projectId").val();
                                        data.from = $("#from").val();
                                        data.to = $("#to").val();
                                    }
                                },
                                columns: [
                                    <#if current_account.isAdmin()>
                                        {data: "pName", className: 'center'},
                                    </#if>
                                    {data: "rName", className: 'center'},
                                    {data: "olAmt", className: 'center'},
                                    {data: "oflAmt", className: 'center'},
                                    {data: "aoAmt", className: 'center'},
                                    {data: "olmIncome", className: 'center'},
                                    {data: "oflmIncome", className: 'center'},
                                    {data: "olsIncome", className: 'center'},
                                    {data: "oflsIncome", className: 'center'},
                                    {data: "olIncome", className: 'center'},
                                    {data: "oflIncome", className: 'center'}
                                ]
                            });
                            $('#query').on("click", function () {
                                $table.DataTable().draw(true);
                            })
                        });
                    </script>
                </div>
            </div>
        </div>
    </div>
</div>
</@layout.myLayout>
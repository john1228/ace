<#import "../layout/application.ftl" as layout>
<@layout.myLayout>
<script src="/assets/js/jquery/dataTables.min.js"></script>
<script src="/assets/js/jquery/dataTables.bootstrap.min.js"></script>
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
        <li class="active">订单管理</li>
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
                            <div class="table-header">操作列表</div>
                        </div>
                        <table id="logs" class="table table-striped table-bordered" cellspacing="0" width="100%">
                            <thead>
                            <tr>
                                <th class="center">员工号</th>
                                <th class="center">员工名</th>
                                <th class="center">资源</th>
                                <th class="center">操作</th>
                                <th class="center">操作ip</th>
                                <th class="center">时间</th>
                            </tr>
                            </thead>
                        </table>
                        <script type="text/javascript">

                            $(function () {
                                var $table = $('#logs');
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
                                    searching: false,
                                    processing: true,
                                    serverSide: true,
                                    autoWidth: false,
                                    ordering: false,
                                    ajax: {
                                        url: "/admin/oper_logs/dataList",
                                        type: "POST",
                                        data: function (data) {
                                            data.empName = $("#empName").val();
                                            data.from = $("#from").val();
                                            data.to = $("#to").val()
                                        }
                                    },
                                    dom: 'Bfltip',
                                    columns:
                                            [
                                                {data: "empId", className: 'center'},
                                                {data: "empName", className: 'center'},
                                                {data: "controller", className: 'center'},
                                                {data: "operation", className: 'center'},
                                                {data: "ip", className: 'center'},
                                                {
                                                    data: "createdAt",
                                                    className: 'center',
                                                    render: function (data) {
                                                        return moment(data).format("YYYY-MM-DD HH:mm:ss");
                                                    }
                                                }
                                            ]
                                })
                                ;
                                $('#query').on("click", function () {
                                    $table.DataTable().draw(true);
                                })
                            })
                        </script>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</@layout.myLayout>
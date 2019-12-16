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
        <li class="active">支付宝配置</li>
    </ul>
</div>
<div class="page-content">
    <div class="row">
        <div class="col-xs-12">
            <div class="row">
                <div class="col-xs-12">
                    <#include "search.ftl"/>
                </div>
                <div class="col-xs-12">
                    <div>
                        <div class="table-header">支付宝配置</div>
                        <table id="alipayList" class="table table-striped table-bordered" cellspacing="0" width="100%">
                            <thead>
                            <tr>
                                <th class="center">项目编号</th>
                                <th class="center">项目名</th>
                                <th class="center">商户号</th>
                                <th class="center">创建</th>
                                <th class="center">更新</th>
                                <th class="center">操作</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                    <script type="text/javascript">
                        $(function () {
                            var $table = $('#alipayList');
                            $table.DataTable({
                                language: {
                                    sProcessing: "处理中...",
                                    sLengthMenu: "显示 _MENU_ 项结果",
                                    sZeroRecords: "没有匹配结果",
                                    sInfo: "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                                    sInfoEmpty: "显示第 0 至 0 项结果，共 0 项",
                                    sInfoFiltered: "",
                                    sInfoPostFix: "",
                                    sSearch: "搜索:",
                                    sUrl: "",
                                    sEmptyTable: "表中数据为空",
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
                                    url: "/admin/alipays/dataList",
                                    type: "POST",
                                    data: function (data) {
                                        data.projectId = $("#proId").val();
                                    }

                                },
                                dom: 'Bfltip',
                                buttons: [
                                    {
                                        text: '新建配置',
                                        className: 'btn btn-primary pull-right',
                                        action: function () {
                                            window.location.href = '/admin/alipays/new';
                                        }
                                    }
                                ],
                                columns: [
                                    {data: "projectId", className: 'center'},
                                    {data: "projectName", className: 'center'},
                                    {data: "seller", className: 'center'},
                                    {data: "createdAt", className: 'center'},
                                    {data: "updatedAt", className: 'center'},
                                    {
                                        data: "projectId",
                                        className: 'center',
                                        render: function (data) {
                                            return '<a class="btn btn-xs btn-info" href="/admin/alipays/' + data + '">查看</a>' +
                                                    '<a class="btn btn-xs btn-danger" href="/admin/alipays/' + data + '/edit">编辑</a>' +
                                                    '<a class="btn btn-xs btn-warning" href="/admin/alipays/' + data + '" data-method="DELETE" data-message="删除成功" data-confirm="您确定要删除这条记录吗?">删除</a>';
                                        }
                                    }
                                ]
                            });
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
</@layout.myLayout>
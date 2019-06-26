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
        <li class="active">价格管理</li>
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
                        <div class="table-header">价格体系</div>
                        <table id="priceList" class="table table-striped table-bordered" cellspacing="0" width="100%">
                            <thead>
                            <tr>
                                <th class="center">场地</th>
                                <th class="center">出租方式</th>
                                <th class="center">适用日期</th>
                                <th class="center">周</th>
                                <th class="center">时间段</th>
                                <th class="center">价格</th>
                                <th class="center">操作</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                    <script type="text/javascript">
                        $(function () {
                            var $table = $('#priceList');
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
                                searching: false,
                                processing: true,
                                serverSide: true,
                                autoWidth: false,
                                ordering: false,
                                ajax: {
                                    url: "/admin/prices/dataList",
                                    type: "POST",
                                    data: function (data) {
                                        data.name = $("#name").val();
                                        data.date = $("#date").val();
                                    }

                                },
                                dom: 'Bfltip',
                                buttons: [
                                    {
                                        text: '新建价格',
                                        className: 'btn btn-primary pull-right',
                                        action: function () {
                                            window.location.href = '/admin/prices/new';
                                        }
                                    }
                                ],
                                columns: [
                                    {data: "roomName", className: 'center'},
                                    {
                                        data: "rental",
                                        className: 'center',
                                        render: function (data) {
                                            switch (data) {
                                                case "HOUR":
                                                    return "小时";
                                                case "PERIOD":
                                                    return "整段";
                                                default:
                                                    return "";
                                            }
                                        }
                                    },
                                    {
                                        data: 'startDate',
                                        className: 'center',
                                        render: function (data, type, row) {
                                            return row.startDate + "--" + row.endDate;
                                        }
                                    },
                                    {
                                        data: 'wday',
                                        className: 'center',
                                        render: function (data) {
                                            var weeks = String(data).split(',');
                                            var weekStr = '';
                                            for (var i = 0; i < weeks.length; i++) {
                                                switch (weeks[i]) {
                                                    case "MONDAY":
                                                        weekStr += "周一 "
                                                        break;
                                                    case "TUESDAY":
                                                        weekStr += "周二 "
                                                        break;
                                                    case "WEDNESDAY":
                                                        weekStr += "周三 "
                                                        break;
                                                    case "THURSDAY":
                                                        weekStr += "周四 "
                                                        break;
                                                    case "FRIDAY":
                                                        weekStr += "周五 "
                                                        break;
                                                    case "SATURDAY":
                                                        weekStr += "周六 "
                                                        break;
                                                    case "SUNDAY":
                                                        weekStr += "周日 "
                                                        break;
                                                }
                                            }
                                            return weekStr;
                                        }
                                    },
                                    {
                                        data: 'startTime',
                                        className: 'center',
                                        render: function (data, type, row) {
                                            return row.startTime + "--" + row.endTime;
                                        }

                                    },
                                    {data: "price", className: 'center'},
                                    {
                                        data: "id",
                                        render: function (data) {
                                            return '<a class="btn btn-xs btn-info" href="/admin/prices/' + data + '">查看</a>' +
                                                    '<a class="btn btn-xs btn-danger" href="/admin/prices/' + data + '/edit">编辑</a>' +
                                                    '<a class="btn btn-xs btn-warning" href="/admin/prices/' + data + '" data-method="DELETE" data-message="删除成功">删除</a>';
                                        },
                                        className: 'center'
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
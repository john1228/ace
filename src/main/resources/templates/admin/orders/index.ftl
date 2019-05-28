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
                            <div class="table-header">订单列表</div>
                        </div>
                        <table id="orderList" class="table table-striped table-bordered" cellspacing="0" width="100%">
                            <thead>
                            <tr>
                                <th class="center">
                                    <label>
                                        <input type="checkbox" class="ace" onclick="checkAll(this)"/>
                                        <span class="lbl"></span>
                                    </label>
                                </th>
                                <th class="center">订单号</th>
                                <th class="center">下单账户</th>
                                <th class="center">下单账户名</th>
                                <th class="center">订单金额</th>
                                <th class="center">优惠券抵用金额</th>
                                <th class="center">实付金额</th>
                                <th class="center">订单状态</th>
                                <th class="center">下单时间</th>
                                <th class="center">更新时间</th>
                                <th class="center">操作</th>
                            </tr>
                            </thead>
                        </table>
                        <script type="text/javascript">

                            $(function () {
                                var $table = $('#orderList');
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
                                        url: "/admin/orders/dataList",
                                        type: "POST",
                                        data: function (data) {
                                            data.orderNo = $("#orderNo").val();
                                            data.startDate = $("#startDate").val();
                                            data.endDate = $("#endDate").val()
                                        }
                                    },
                                    dom: 'Bfltip',
                                    buttons:
                                            [
                                                {
                                                    text: '新建订单',
                                                    className: 'btn btn-primary pull-right',
                                                    action: function () {
                                                        window.location.href = '/admin/orders/new';
                                                    }
                                                }
                                            ],
                                    columns:
                                            [
                                                {
                                                    data: "id",
                                                    render: function (data) {
                                                        return '<label>' +
                                                                '<input type="checkbox" class="ace" name="checks[]" value="' + data + '"  />' +
                                                                '<span class="lbl"></span>' +
                                                                '</label>';
                                                    },
                                                    className: 'center'
                                                },
                                                {data: "orderNo", className: 'center'},
                                                {data: "accountId", className: 'center'},
                                                {data: "accountName", className: 'center'},
                                                {data: "total", className: 'center'},
                                                {data: "coupon", className: 'center'},
                                                {data: "payAmount", className: 'center'},
                                                {
                                                    data: "status",
                                                    className: 'center',
                                                    render: function (data) {
                                                        switch (data) {
                                                            case "CANCELED":
                                                                return "已取消";
                                                            case "PENDING":
                                                                return "待付款";
                                                            case "PAID":
                                                                return "已付款";
                                                            case "REFUNDED":
                                                                return "已退款";

                                                        }
                                                    }
                                                },
                                                {
                                                    data: "createdAt",
                                                    className: 'center',
                                                    render: function (data) {
                                                        return moment(data).format("YYYY-MM-DD HH:mm:ss");
                                                    }
                                                },
                                                {
                                                    data: "updatedAt",
                                                    className: 'center',
                                                    render: function (data) {
                                                        return moment(data).format("YYYY-MM-DD HH:mm:ss");
                                                    }
                                                },
                                                {
                                                    data: "orderNo",
                                                    className: 'center',
                                                    render: function (data) {
                                                        return '<div class="hidden-sm hidden-xs btn-group">' +
                                                                '<a class="btn btn-xs btn-info" href="/admin/orders/' + data + '">查看</a>' +
                                                                '<a class="btn btn-xs btn-danger" href="/admin/orders/' + data + '/edit">编辑</a>' +
                                                                '<a class="btn btn-xs btn-warning" href="javascript:void(0)">删除</a>' +
                                                                '</div>';
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
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
            <div>
                <div class="table-header">订单列表</div>
            </div>
            <table id="orderList" class="table table-striped table-bordered" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th class="center">订单号</th>
                    <th class="center">联系人</th>
                    <th class="center">联系电话</th>
                    <th class="center">订单金额</th>
                    <th class="center">优惠金额</th>
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
                        bLengthChange: false,
                        searching: false,
                        processing: true,
                        serverSide: true,
                        autoWidth: false,
                        ordering: false,
                        ajax: {
                            url: "/admin/orders/dataList",
                            type: "POST",
                            data: function (data) {
                                data.keyword = $("#mobile").val();
                                data.proId = $("#proId").val();
                                data.orgId = $("#orgId").val();
                                data.total = $("#total").val();
                                data.payAmount = $("#payAmount").val();
                                data.status = $("#status").val();
                                data.from = $("#from").val();
                                data.to = $("#to").val();
                                data.projectName = $("#project").val();
                            }
                        },
                        <#if !current_account.isAdmin()>
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
                        </#if>
                        columns:
                                [
                                    {data: "orderNo", className: 'center'},
                                    {data: "accountName", className: 'center'},
                                    {data: "accountId", className: 'center'},
                                    {data: "total", className: 'center'},
                                    {data: "coupon", className: 'center'},
                                    {data: "payAmount", className: 'center'},
                                    {
                                        data: "status", className: 'center', render: function (data) {
                                            switch (data) {
                                                case "CANCELED":
                                                    return "已取消";
                                                case "UNPAID2CONFIRM":
                                                    return "未付款未确认";
                                                case "CONFIRM2PAID":
                                                    return "已确认未付款";
                                                case "PAID2CONFIRM":
                                                    return "已付款未确认";
                                                case "PAIDANDCONFIRM":
                                                    return "已付款已确认";
                                                case "COMPLETE":
                                                    return "已完成";
                                                case "REFUNDING":
                                                    return "退款中";
                                                case "REFUNDED":
                                                    return "已退款";
                                                default:
                                                    return "异常状态";
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
                                        render: function (data, type, row) {
                                            var _btn = '<a class="btn btn-xs btn-info" href="/admin/orders/' + data + '">查看</a>';
                                            <#if !current_account.isAdmin()>
                                                if (row.status == "UNPAID2CONFIRM" || row.status == "PAID2CONFIRM") {
                                                    _btn = _btn + '<a class="btn btn-xs btn-warning" href="/admin/orders/' + data + '/confirm" data-method="POST" data-message="确认成功">确认</a>';
                                                }
                                            </#if>
                                            return _btn;
                                        }
                                    }
                                ]
                    })
                    $('#query').on("click", function () {
                        $table.DataTable().draw(true);
                    })
                })
            </script>
        </div>
    </div>
</div>
</@layout.myLayout>
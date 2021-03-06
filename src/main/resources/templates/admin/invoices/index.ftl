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
            <div class="table-header padding-2">
                发票订单
            </div>
            <div>
                <table id="orderList" class="table table-striped table-bordered" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th class="center">订单日期</th>
                        <th class="center">订单号</th>
                        <th class="center">会议室</th>
                        <th class="center">预订人</th>
                        <th class="center">预订人手机号</th>
                        <th class="center">使用时间</th>
                        <th class="center">订单金额</th>
                        <th class="center">支付金额</th>
                        <th class="center">状态</th>
                        <th class="center">操作</th>
                    </tr>
                    </thead>
                </table>
            </div>
            <script type="text/javascript">
                $(function () {
                    $.fn.dataTable.ext.buttons.reload = {
                        text: 'Reload',
                        action: function (e, dt) {
                            dt.ajax.reload();
                        }
                    };
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
                            url: "/admin/invoices/dataList",
                            type: "POST",
                            data: function (data) {
                                data.keyword = $("#keyword").val();
                                data.status = $("#status").val();
                                data.createdFrom = $("#createdFrom").val();
                                data.createdTo = $("#createdTo").val();
                                data.totalFrom = $("#totalFrom").val();
                                data.totalTo = $("#totalTo").val();
                                data.payAmtFrom = $("#payAmtFrom").val();
                                data.payAmtTo = $("#payAmtTo").val();
                            }
                        },
                        columns: [
                            {data: "createdAt", className: 'center'},
                            {data: "orderNo", className: 'center'},
                            {data: "roomName", className: 'center'},
                            {data: "contactName", className: 'center'},
                            {data: "contactMobile", className: 'center'},
                            {data: "appointTime", className: 'center'},
                            {data: "total", className: 'center'},
                            {data: "payAmount", className: 'center'},
                            {data: "status", className: 'center'},
                            {
                                data: "status",
                                className: 'center',
                                render: function (data, type, row) {
                                    var _html = '<div class="hidden-sm hidden-xs btn-group">' +
                                            '<a class="btn btn-xs btn-info" href="/admin/invoices/' + row.orderNo + '">查看</a>';
                                    <#if current_account.isAdmin()>
                                    switch (data) {
                                        case "APPLYING":
                                            _html += '<a class="btn btn-xs btn-info" href="/admin/invoices/' + row.orderNo + '">开票</a>';
                                            break;
                                        case "INVOICED":
                                            _html += '<a class="btn btn-xs btn-info" href="/admin/invoices/' + row.orderNo + '">邮寄</a>';
                                            break;
                                    }
                                    </#if>
                                    _html += '</div>';
                                    return _html;
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
</@layout.myLayout>
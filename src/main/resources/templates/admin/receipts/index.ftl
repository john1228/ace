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
        <li class="active">流水管理</li>
    </ul>
</div>
<div class="page-content">
    <div class="row">
        <div class="col-xs-12">
            <#include "search.ftl"/>
        </div>
        <div class="col-xs-12">
            <div class="table-header  padding-2">流水列表</div>
            <div>
                <table id="receiptList" class="table table-striped table-bordered" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th class="center" width="10%">订单号</th>
                        <th class="center" width="15%">流水号</th>
                        <th class="center" width="10%">项目</th>
                        <th class="center" width="7%">会议室</th>
                        <th class="center" width="7%">订单金额</th>
                        <th class="center" width="7%">应付金额</th>
                        <th class="center" width="7%">流水金额</th>
                        <th class="center" width="10%">下单时间</th>
                        <th class="center" width="10%">支付时间</th>
                        <th class="center" width="7%">支付方式</th>
                    </tr>
                    </thead>
                </table>
            </div>
            <script type="text/javascript">
                $(function () {
                    var $table = $('#receiptList');
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
                            url: "/admin/receipts/dataList",
                            type: "POST",
                            data: function (data) {
                                data.keyword = $("#keyword").val();
                                data.status = $("#status").val();
                                data.from = $("#from").val();
                                data.to = $("#to").val();
                                data.payAmountFrm = $("#payAmountFrm").val();
                                data.payAmountTo = $("#payAmountTo").val();
                                data.totalFrm = $("#totalFrm").val();
                                data.totalTo = $("#totalTo").val();
                            }

                        },
                        columns: [
                            {data: "orderNo", className: 'center'},
                            {data: "no", className: 'center'},
                            {data: "projectName", className: 'center'},
                            {data: "roomName", className: 'center'},
                            {data: "total", className: 'center'},
                            {data: "payAmount", className: 'center'},
                            {data: "price", className: 'center'},
                            {data: "createdAt", className: 'center'},
                            {data: "paidAt", className: 'center'},
                            {data: "payType", className: 'center'}
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
</@layout.myLayout>
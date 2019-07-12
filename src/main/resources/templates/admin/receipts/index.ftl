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
    <div class="nav-search" id="nav-search">
        <form class="form-search">
				<span class="input-icon">
					<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input"
                           autocomplete="off"/>
					<i class="icon-search nav-search-icon"></i>
				</span>
        </form>
    </div>
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
                        <th class="center">订单号</th>
                        <th class="center">流水号</th>
                        <th class="center">项目</th>
                        <th class="center">组织</th>
                        <th class="center">会议室</th>
                        <th class="center">订单金额</th>
                        <th class="center">应付金额</th>
                        <th class="center">流水金额</th>
                        <th class="center">下单时间</th>
                        <th class="center">支付时间</th>
                        <th class="center">支付方式</th>
                    </tr>
                    </thead>
                </table>
            </div>
            <script type="text/javascript">
                $(function () {
                    $('#receiptList').DataTable({
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
                            url: "/admin/receipts/dataList",
                            type: "POST",
                            data: function (data) {
                                data.keyword = $("#mobile").val();
                                data.status = $("#project").val();
                                data.total = $("#total").val();
                                data.payAmount = $("#payAmount").val();
                                data.from = $("#from").val();
                                data.to = $("#to").val();
                            }

                        },
                        columns: [
                            {data: "orderNo", className: 'center'},
                            {data: "no", className: 'center'},
                            {data: "projectName", className: 'center'},
                            {data: "orgName", className: 'center'},
                            {data: "roomName", className: 'center'},
                            {data: "total", className: 'center'},
                            {data: "payAmount", className: 'center'},
                            {data: "price", className: 'center'},
                            {data: "createdAt", className: 'center'},
                            {data: "paidAt", className: 'center'},
                            {data: "payType", className: 'center'}
                        ]
                    });
                })
            </script>
        </div>
    </div>
</div>
</@layout.myLayout>
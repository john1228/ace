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
            <div class="row">
                <div class="col-xs-12">
                    <h4 class="header smaller lighter blue">
                        <span>流水列表</span>
                    </h4>
                    <div>
                        <table id="receiptList" class="table table-striped table-bordered" cellspacing="0" width="100%">
                            <thead>
                            <tr>
                                <th class="center">
                                    <label>
                                        <input type="checkbox" class="ace" onclick="checkAll(this)"/>
                                        <span class="lbl"></span>
                                    </label>
                                </th>
                                <th class="center">订单号</th>
                                <th class="center">流水号</th>
                                <th class="center">付款账号</th>
                                <th class="center">金额</th>
                                <th class="center">下单时间</th>
                                <th class="center">操作</th>
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
                                    sInfoFiltered: "(由 _MAX_ 项结果过滤)",
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
                                processing: true,
                                serverSide: true,
                                autoWidth: false,
                                ordering: false,
                                ajax: {
                                    url: "/admin/receipts/dataList",
                                    type: "GET"
                                },
                                columns: [
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
                                    {data: "no", className: 'center'},
                                    {data: "orderNo", className: 'center'},
                                    {data: "buyer", className: 'center'},
                                    {data: "price", className: 'center'},
                                    {data: "createdAt", className: 'center'},
                                    {
                                        data: "id",
                                        render: function (data) {
                                            return '<div class="hidden-sm hidden-xs btn-group">' +
                                                    '<a class="btn btn-xs btn-info" href="/admin/receipts/' + data + '?parent=${parent}"><i class="ace-icon fa fa-pencil bigger-120"></i></a>' +
                                                    '</div>';
                                        },
                                        className: 'center'
                                    }
                                ]
                            });
                        })
                    </script>
                </div>
            </div>
        </div>
    </div>
</div>
</@layout.myLayout>
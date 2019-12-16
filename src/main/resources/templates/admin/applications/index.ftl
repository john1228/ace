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
        <li class="active">退款管理</li>
    </ul>
</div>
<div class="page-content">
    <div class="row">
        <div class="col-xs-12">
            <#include "search.ftl"/>
        </div>
        <div class="col-xs-12">
            <div class="table-header padding-2">
                退款申请
            </div>
            <div>
                <table id="refundList" class="table table-striped table-bordered" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th class="center">订单号</th>
                        <th class="center">项目</th>
                        <th class="center">会议室</th>
                        <th class="center">预订人</th>
                        <th class="center">预订人手机号</th>
                        <th class="center">订单金额</th>
                        <th class="center">退款金额</th>
                        <th class="center">状态</th>
                        <th class="center">申请时间</th>
                        <th class="center">审批时间</th>
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
                    var $table = $('#refundList');
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
                            url: "/admin/applications/dataList",
                            type: "POST",
                            data: function (data) {
                                data.orderNo = $("#orderNo").val();
                                data.roomName = $("#roomName").val();
                                data.contactName = $("#contactName").val();
                                data.contactMobile = $("#contactMobile").val();
                            }
                        },
                        columns: [
                            {data: "orderNo", className: 'center'},
                            {data: "projectName", className: 'center'},
                            {data: "roomName", className: 'center'},
                            {data: "contactName", className: 'center'},
                            {data: "contactMobile", className: 'center'},
                            {data: "amount", className: 'center'},
                            {data: "confirmAmount", className: 'center'},
                            {data: "status", className: 'center'},
                            {data: "createdAt", className: 'center'},
                            {data: "updatedAt", className: 'center'},
                            {
                                data: "id",
                                className: 'center',
                                render: function (data, type, row) {
                                    if (row.status == "申请中") {
                                        return '<a class="btn btn-xs btn-info" data-toggle="modal" data-target="#exampleModal' + data + '">同意</a>' +
                                                '<div class="modal fade" id="exampleModal' + data + '" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">' +
                                                '<form action="/admin/applications/' + data + '/agree" method="POST">' +
                                                '<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>' +
                                                '<input type="hidden" name="amount" value="' + row.amount + '"/>' +
                                                '<div class="modal-dialog" role="document"><div class="modal-content">' +
                                                '<div class="modal-header"><h5 class="smaller lighter blue no-margin" id="test2">退款审批' + row.orderNo + '</h5>' +
                                                '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button></div>' +
                                                '<div class="modal-body form-horizontal">' +
                                                '<div class="row">' +
                                                '<label class="col-sm-2 control-label no-padding-right">订单金额</label>' +
                                                '<div class="col-sm-10" style="text-align: left;padding-top:7px">' +
                                                '<span style="color:red">' + row.amount + '</span>' +
                                                '</div></div>' +
                                                '<div class="row">' +
                                                '<label class="col-sm-2 control-label no-padding-right">退款金额</label>' +
                                                '<div class="col-sm-10" style="text-align: left">' +
                                                '<input type="number" style="width: 100%;" step="0.01" min="0" max="' + row.amount + '" placeholder="最大可退款金额:' + row.amount + '￥" name="confirmAmount" value=" ' + row.amount + '"/>' +
                                                '</div></div></div>' +
                                                '<div class="modal-footer">' +
                                                '<button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>' +
                                                '<button type="submit" class="btn btn-primary">确定</button></div></div></div></form></div>' +
                                                '<a class="btn btn-xs btn-warning" href="/admin/applications/' + data + '/reject" data-method="DELETE" data-confirm="确定要拒绝该退款申请吗？" data-message="拒绝成功">拒绝</a>';
                                    } else {
                                        return "";
                                    }
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
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
        <li class="active">优惠券管理</li>
    </ul>
</div>
<div class="page-content">
    <div class="row">
        <div class="col-xs-12">
            <#include 'search.ftl'/>
        </div>
        <div class="col-xs-12">
            <div class="table-header padding-2">
                优惠券列表
            </div>
            <table id="couponList" class="table table-bordered table-hover" cellspacing="0"
                   width="100%">
                <thead>
                <tr>
                    <th class="center">优惠券名</th>
                    <th class="center">类型</th>
                    <th class="center">总数量</th>
                    <th class="center">剩余数量</th>
                    <th class="center">使用开始时间</th>
                    <th class="center">使用结束时间</th>
                    <th class="center">使用周数</th>
                    <th class="center">操作</th>
                </tr>
                </thead>
            </table>
            <script type="text/javascript">
                $(function () {
                    $.fn.dataTable.ext.buttons.reload = {
                        text: 'Reload',
                        action: function (e, dt, node, config) {
                            dt.ajax.reload();
                        }
                    };
                    $('#couponList').DataTable({
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
                        searching: false,
                        processing: true,
                        serverSide: true,
                        autoWidth: false,
                        ordering: false,
                        ajax: {
                            url: "/admin/coupons/dataList",
                            type: "GET"
                        },
                        dom: 'Bfltip',
                        buttons: [
                            {
                                text: '新增优惠券',
                                className: 'btn btn-primary pull-right',
                                action: function (e, dt, node, config) {
                                    window.location.href = '/admin/coupons/new?parent=${parent}';
                                }
                            }
                        ],
                        columns: [
                            {data: "name", className: 'center'},
                            {
                                data: "type",
                                className: 'center',
                                render: function (data) {
                                    return "现金券";
                                }
                            },
                            {data: "amount", className: 'center'},
                            {data: "amount", className: 'center'},
                            {data: "startDate", className: 'center'},
                            {data: "endDate", className: 'center'},
                            {data: "weekList", className: 'center'},
                            {
                                data: "id",
                                className: 'center',
                                render: function (data) {
                                    return '<div class="hidden-sm hidden-xs btn-group">' +
                                            '<a class="btn btn-xs btn-info" href="/admin/coupons/' + data + '?parent=${parent}"><i class="ace-icon fa fa-eye bigger-120"></i></a>' +
                                            '<a class="btn btn-xs btn-danger" href="/admin/coupons/' + data + '/edit?parent=${parent}"><i class="ace-icon fa fa-edit bigger-120"></i></a>' +
                                            '<a class="btn btn-xs btn-warning" href="javascript:void(0)"><i class="ace-icon fa fa-trash bigger-120"></i></a>' +
                                            '</div>';
                                }
                            }
                        ]
                    });
                })
            </script>
        </div>
    </div>
</@layout.myLayout>
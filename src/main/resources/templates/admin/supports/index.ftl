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
        <li class="active">设备管理</li>
    </ul>
</div>
<div class="page-content">
    <div class="row">
        <div class="col-xs-12">
            <div class="row">
                <div class="col-xs-12">
                    <h4 class="header smaller lighter blue">
                        <span>服务列表</span>
                    </h4>
                    <div class="row">
                        <div class="clearfix">
                            <div class="tableTools-container col-sm-6">
                                <div class="input-group pull-left">
                                    <input class="form-control" placeholder="Search" aria-label="Search" id="search">
                                    <div class="input-group-addon" id="searchBtn">
                                        <i class="ace-icon fa fa-search"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div>
                            <div class="table-header">服务列表</div>
                            <table id="supportList" class="table table-striped table-bordered" cellspacing="0"
                                   width="100%">
                                <thead>
                                <tr>
                                    <th class="center">编号</th>
                                    <th class="center">名字</th>
                                    <th class="center">图片</th>
                                    <th class="center">单位</th>
                                    <th class="center">操作</th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                    <script type="text/javascript">
                        $(function () {
                            $.ajaxSetup({headers: {'X-CSRF-Token': $('meta[name="_csrf"]').attr('content')}});
                            $('#supportList').DataTable({
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
                                    url: "/admin/supports/dataList",
                                    type: "POST"
                                },
                                dom: 'Bfltip',
                                buttons: [
                                    {
                                        text: '新建服务',
                                        className: 'btn btn-primary pull-right',
                                        action: function () {
                                            window.location.href = '/admin/supports/new';
                                        }
                                    }
                                ],
                                columns: [
                                    {data: "id", className: 'center'},
                                    {data: "name", className: 'center'},
                                    {
                                        data: "cover",
                                        className: 'center',
                                        render: function (data) {
                                            return '<img class="img-50" src="${image}' + data + '"/>';
                                        }
                                    },
                                    {data: "unit", className: 'center'},
                                    {
                                        data: "id",
                                        render: function (data) {
                                            return '<div class="hidden-sm hidden-xs btn-group">' +
                                                    '<a class="btn btn-xs btn-info" href="/admin/supports/' + data + '">查看</a>' +
                                                    '<a class="btn btn-xs btn-danger" href="/admin/supports/' + data + '/edit">编辑</a>' +
                                                    '<a class="btn btn-xs btn-warning" href="javascript:void(0)">删除</a>' +
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
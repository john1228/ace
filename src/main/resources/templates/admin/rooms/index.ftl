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
        <li class="active">会议室管理</li>
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
                            <div class="table-header">会议室列表</div>
                            <table id="roomList" class="table table-striped table-bordered" cellspacing="0"
                                   width="100%">
                                <thead>
                                <tr>
                                    <th class="center">系统编号</th>
                                    <th class="center">编号</th>
                                    <th class="center">名称</th>
                                    <th class="center">发布类型</th>
                                    <th class="center">出租方式</th>
                                    <th class="center">房间信息</th>
                                    <th class="center">面积</th>
                                    <th class="center">容纳人数</th>
                                    <th class="center">负责人</th>
                                    <th class="center">联系电话</th>
                                    <th class="center">邮箱</th>
                                    <th class="center">状态</th>
                                    <th class="center" width="15%">操作</th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                    <script type="text/javascript">
                        $(function () {
                            var $table = $('#roomList');
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
                                    url: "/admin/rooms/dataList",
                                    type: "POST",
                                    data: function (data) {
                                        data.name = $("#name").val();
                                        data.proId = $("#proId").val();
                                        data.orgId = $("#orgId").val();
                                        data.layerAreaFrom = $("#layerAreaFrom").val();
                                        data.layerAreaTo = $("#layerAreaTo").val();
                                        data.quotaFrom = $("#quotaFrom").val();
                                        data.quotaTo = $("#quotaTo").val();
                                    }
                                },
                                <#if !current_account.isAdmin()>
                                    dom: 'Bfltip',
                                    buttons: [
                                        {
                                            text: '新建场地',
                                            className: 'btn btn-primary pull-right',
                                            action: function () {
                                                window.location.href = '/admin/rooms/new';
                                            }
                                        }
                                    ],
                                </#if>
                                columns: [
                                    {data: "id", className: 'center'},
                                    {data: "serialNo", className: 'center'},
                                    {data: "name", className: 'center'},
                                    {data: "publish", className: 'center'},
                                    {
                                        data: "rental", className: 'center', render: function (data) {
                                            return data == 'HOUR' ? '小时' : '整段';
                                        }
                                    },
                                    {
                                        data: "buildingNo",
                                        className: 'center',
                                        render: function (data, type, row) {
                                            return data + "幢" + row.floorNo + "层" + row.roomNo + "室";
                                        }
                                    },
                                    {data: "layerArea", className: 'center'},
                                    {data: "quota", className: 'center'},
                                    {data: "supervisor", className: 'center'},
                                    {data: "supervisorMobile", className: 'center'},
                                    {data: "supervisorEmail", className: 'center'},
                                    {
                                        data: "online", className: 'center', render: function (data) {
                                            return data ? '在线' : '下线';
                                        }
                                    },
                                    {
                                        data: "id",
                                        render: function (data, type, row) {
                                            var _btn = '<a class="btn btn-xs btn-info" href="/admin/rooms/' + data + '">查看</a>';
                                            <#if !current_account.isAdmin()>
                                                if (row.online) {
                                                    _btn = _btn +
                                                            '<a class="btn btn-xs btn-danger" href="/admin/rooms/' + data + '/edit">编辑</a>' +
                                                            '<a class="btn btn-xs btn-warning" href="/admin/rooms/' + data + '/disable" data-method="POST" data-message="停用成功">停用</a>';
                                                } else {
                                                    _btn = _btn +
                                                            '<a class="btn btn-xs btn-danger" href="/admin/rooms/' + data + '/edit">编辑</a>' +
                                                            '<a class="btn btn-xs btn-warning" href="/admin/rooms/' + data + '/enable" data-method="POST" data-message="启用成功">启用</a>';
                                                }
                                            </#if>
                                            return _btn;
                                        },
                                        className: 'center'
                                    }
                                ]
                            });
                            $('#query').on("click", function () {
                                $table.DataTable().draw(true);
                            })
                        });
                    </script>
                </div>
            </div>
        </div>
    </div>
</div>
</@layout.myLayout>
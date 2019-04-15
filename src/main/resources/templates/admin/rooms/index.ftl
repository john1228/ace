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
        <li class="active">优惠券管理</li>
    </ul>
</div>
<div class="page-content">
    <div class="row">
        <div class="col-xs-12">
            <div class="row">
                <div class="col-xs-12">
                    <h4 class="header smaller lighter blue">
                        <span>场地列表</span>
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
                            <div class="tableTools-container col-sm-6">
                                <a class="btn btn-primary pull-right " href="/admin/rooms/new?parent=${parent}">新增场地</a>
                            </div>
                        </div>
                        <div>
                            <div class="table-header">会议室列表</div>
                            <table id="roomList" class="table table-striped table-bordered" cellspacing="0"
                                   width="100%">
                                <thead>
                                <tr>
                                    <th class="center">
                                        <label>
                                            <input type="checkbox" class="ace" onclick="checkAll(this)"/>
                                            <span class="lbl"></span>
                                        </label>
                                    </th>
                                    <th class="center">编号</th>
                                    <th class="center">名字</th>
                                    <th class="center">类型</th>
                                    <th class="center">高度</th>
                                    <th class="center">面积</th>
                                    <th class="center">人数</th>
                                    <th class="center">独自性</th>
                                    <th class="center">操作</th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                    <script type="text/javascript">
                        $(function () {
                            var table = $('#roomList').DataTable({
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
                                    url: "/admin/rooms/dataList",
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
                                    {data: "id", className: 'center'},
                                    {data: "name", className: 'center'},
                                    {
                                        data: "type",
                                        className: 'center',
                                        render: function (data) {
                                            switch (data) {
                                                case "Clazz1":
                                                    return "类型1";
                                                case "Clazz2":
                                                    return "类型2";

                                            }
                                        }
                                    },
                                    {data: "height", className: 'center'},
                                    {data: "area", className: 'center'},
                                    {data: "capacity", className: 'center'},
                                    {data: "oneself", className: 'center'},
                                    {
                                        data: "id",
                                        render: function (data) {
                                            return '<a class="btn btn-xs btn-info" href="/admin/rooms/' + data + '?parent=${parent}"><i class="ace-icon fa fa-pencil bigger-120"></i></a>' +
                                                    '<a class="btn btn-xs btn-danger" href="/admin/rooms/' + data + '/edit?parent=${parent}"><i class="ace-icon fa fa-edit bigger-120"></i></a>' +
                                                    '<a class="btn btn-xs btn-warning" href="javascript:void(0)"><i class="ace-icon fa fa-trash bigger-120"></i></a>';
                                        },
                                        className: 'center'
                                    }
                                ]
                            });
                            $("#searchBtn").on("click", function () {
                                var searchVal = $("#search").val();
                                console.log("输入内容:" + !searchVal || /^\s*$/.test(searchVal));
                                if (!(!searchVal || /^\s*$/.test(searchVal)))
                                    table.search(searchVal).draw();
                            })
                        })
                    </script>
                </div>
            </div>
        </div>
    </div>
</div>
</@layout.myLayout>
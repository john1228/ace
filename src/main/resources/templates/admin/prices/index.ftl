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
        <li class="active">价格管理</li>
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
                    <h3 class="header smaller lighter blue">
                        <span>价格列表</span>
                        <a class="btn btn-primary" style="float:right;margin-top: -12px;"
                           href="/admin/prices/new?parent=${parent}"><i
                                class="icon-pencil align-top bigger-125"></i>新增</a>
                    </h3>
                    <div>
                        <table id="roomList" class="table table-striped table-bordered" cellspacing="0" width="100%">
                            <thead>
                            <tr>
                                <th class="center">
                                    <label>
                                        <input type="checkbox" class="ace" onclick="checkAll(this)"/>
                                        <span class="lbl"></span>
                                    </label>
                                </th>
                                <th class="center">场地</th>
                                <th class="center">出租方式</th>
                                <th class="center">适用日期</th>
                                <th class="center">周</th>
                                <th class="center">时间段</th>
                                <th class="center">价格</th>
                                <th class="center">操作</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                    <script type="text/javascript">
                        $(function () {
                            $('#roomList').DataTable({
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
                                    url: "/admin/prices/dataList",
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
                                    {data: "roomName", className: 'center'},
                                    {
                                        data: "rental",
                                        className: 'center',
                                        render: function (data) {
                                            switch (data) {
                                                case "HOUR":
                                                    return "每小时";
                                                case "PERIOD":
                                                    return "已停用"

                                            }
                                        }
                                    },
                                    {
                                        data: 'startDate',
                                        className: 'center',
                                        render: function (data) {
                                            return ''
                                        }
                                    },
                                    {
                                        data: 'wday',
                                        className: 'center',
                                        render: function (data) {
                                            var weekStr = "";
                                            for (var i = 0; i < data.length; i++) {
                                                switch (data[i]) {
                                                    case "MONDAY":
                                                        weekStr += "周一 "
                                                        break;
                                                    case "TUESDAY":
                                                        weekStr += "周二 "
                                                        break;
                                                    case "WEDNESDAY":
                                                        weekStr += "周三 "
                                                        break;
                                                    case "THURSDAY":
                                                        weekStr += "周四 "
                                                        break;
                                                    case "FRIDAY":
                                                        weekStr += "周五 "
                                                        break;
                                                    case "SATURDAY":
                                                        weekStr += "周六 "
                                                        break;
                                                    case "SUNDAY":
                                                        weekStr += "周日 "
                                                        break;
                                                }
                                            }
                                            return weekStr;
                                        }
                                    },
                                    {
                                        data: 'startTime',
                                        className: 'center'
                                    },
                                    {data: "price", className: 'center'},
                                    {
                                        data: "id",
                                        render: function (data) {
                                            return '<div class="hidden-sm hidden-xs btn-group">' +
                                                    '<a class="btn btn-xs btn-info" href="/admin/prices/' + data + '?parent=${parent}"><i class="ace-icon fa fa-eye bigger-120"></i></a>' +
                                                    '<a class="btn btn-xs btn-danger" href="/admin/prices/' + data + '/edit?parent=${parent}"><i class="ace-icon fa fa-edit bigger-120"></i></a>' +
                                                    '<a class="btn btn-xs btn-warning" href="javascript:void(0)"><i class="ace-icon fa fa-trash bigger-120"></i></a>' +
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
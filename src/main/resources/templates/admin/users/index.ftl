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
        <li class="active">用户管理</li>
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
                        <span>用户列表</span>
                        <a class="btn" style="float:right;margin-top: -12px;" href="/admin/user/add"><i
                                class="icon-pencil align-top bigger-125"></i>用户新增</a>
                    </h3>
                    <div>
                        <table id="userList" class="table table-striped table-bordered" cellspacing="0" width="100%">
                            <thead>
                            <tr>
                                <th class="center">
                                    <label>
                                        <input type="checkbox" class="ace" onclick="checkAll(this)"/>
                                        <span class="lbl"></span>
                                    </label>
                                </th>
                                <th class="center">组</th>
                                <th class="center">登录账号</th>
                                <th class="center">用户姓名</th>
                                <th class="hidden-480">用户头像</th>
                                <th>
                                    <i class="icon-time bigger-110 hidden-480"></i>
                                    创建时间
                                </th>
                                <th>操作</th>
                            </tr>
                            </thead>
                        </table>
                        <script type="text/javascript">
                            $(function () {
                                $('#userList').DataTable({
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
                                        url: "/admin/users/dataList",
                                        type: "GET"
                                    },
                                    columns: [
                                        {data: "id"},
                                        {data: "groupName"},
                                        {data: "userName"},
                                        {data: "portrait"},
                                        {data: "createTime"},
                                        {data: "portrait"},
                                        {
                                            data: "id",
                                            render: function (data) {
                                                return '<a class="btn btn-xs btn-info" href="/admin/users/' + data + '"><i class="ace-icon fa fa-pencil bigger-120"></i></a>' +
                                                        '<a class="btn btn-xs btn-danger" href="/admin/users/' + data + '/edit"><i class="ace-icon fa fa-trash-o bigger-120"></i></a>' +
                                                        '<a class="btn btn-xs btn-warning" href="javascript:void(0)"><i class="ace-icon fa fa-flag bigger-120"></i></a>';
                                            }
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
</div>
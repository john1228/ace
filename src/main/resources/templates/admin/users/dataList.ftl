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
                    <div class="table-header">
                        共有${pu.total}条数据
                    </div>

                    <div class="table-responsive">
                        <table id="sample-table-2" class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th class="center">
                                    <label>
                                        <input type="checkbox" class="ace" onclick="checkAll(this)"/>
                                        <span class="lbl"></span>
                                    </label>
                                </th>
                                <th>组</th>
                                <th>登录账号</th>
                                <th>用户姓名</th>
                                <th class="hidden-480">用户头像</th>

                                <th>
                                    <i class="icon-time bigger-110 hidden-480"></i>
                                    创建时间
                                </th>
                                <th class="hidden-480">状态</th>

                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                                    <#list pu.list as user >
                                    <tr>
                                        <td class="center">
                                            <label>
                                                <input type="checkbox" class="ace" name="checks"/>
                                                <span class="lbl"></span>
                                            </label>
                                        </td>
                                        <td>${user.groupName}</td>
                                        <td>
                                            <a href="/admin/user/select/${user.id}">${user.loginName}</a>
                                        </td>
                                        <td>${user.userName}</td>
                                        <td class="hidden-480">
                                            <img src="/${user.portrait!}" width="45" height="45"/>
                                        </td>
                                        <td>
                                            ${user.createTime?number_to_datetime?string("yyyy-MM-dd HH:mm")}
                                        </td>

                                        <td class="hidden-480">
                                            <span class="label label-sm label-success">已创建</span>
                                        </td>

                                        <td>
                                            <div class="hidden-sm hidden-xs btn-groups">
                                                <a class="btn btn-xs btn-info" href="/admin/users/${user.id}">
                                                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                                                </a>

                                                <a class="btn btn-xs btn-danger" href="/admin/users/${user.id}/edit">
                                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                                </a>

                                                <a class="btn btn-xs btn-warning" href="javascript:void(0)">
                                                    <i class="ace-icon fa fa-flag bigger-120"></i>
                                                </a>
                                            </div>
                                        </td>
                                    </tr>
                                    </#list>
                            </tbody>
                        </table>
                    </div>

                    <div class="modal-footer no-margin-top">
                        <ul class="pagination pull-right no-margin" id="page">
                            <li class="${(pu.pageNum==1)?string("prev disabled","prev")}">
                                <a href="${(pu.pageNum==1)?string("javascript:void(0)","/admin/user/list/1")}">
                                    <i class=" icon-double-angle-left"></i>
                                </a>
                            </li>
                                <#list pc.pageList as page >
                                    <li class="${(page==pu.pageNum)?string("active","")}">
                                        <a href="${(page==pu.pageNum)?string("javascript:void(0)","/admin/user/list/" + page)}">
                                            ${page}
                                        </a>
                                    </li>
                                </#list>
                            <li class="${(pu.pageNum==pu.lastPage)?string("next disabled","next")}">
                                <a href="${(pu.pageNum==pu.lastPage)?string("javascript:void(0)","/admin/user/list/"+ pu.lastPage)}">
                                    <i class="icon-double-angle-right"></i>
                                </a>
                            </li>
                        </ul>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
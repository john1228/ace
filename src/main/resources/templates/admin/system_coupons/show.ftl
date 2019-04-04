<#import "../layout/application.ftl" as layout>
<#import "/spring.ftl" as spring />
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
        <li>
            <a href="/admin/users/">优惠券管理</a>
        </li>
        <li class="active">优惠券查看</li>
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
            <div class="page-header">
                <h1>
                    <i class="fa fa-tag"></i>
                    ${coupon.name}
                </h1>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="widget-box">
                        <div class="widget-header widget-header-custom">
                            <h4 class="widget-title">优惠券详情</h4>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main">
                                <div class="attribute-table">
                                    <div class="attribute-table-row">
                                        <div class="attribute-table-label">名字</div>
                                        <div class="attribute-table-value">
                                            <span>${coupon.name}</span>
                                        </div>
                                    </div>
                                    <div class="attribute-table-row">
                                        <div class="attribute-table-label">类型</div>
                                        <div class="attribute-table-value">
                                                <span>
                                                    ${coupon.type.getName()}
                                                </span>
                                        </div>
                                    </div>
                                    <div class="attribute-table-row">
                                        <div class="attribute-table-label">优惠金额</div>
                                        <div class="attribute-table-value">
                                                <span>
                                                    ${coupon.discount}
                                                </span>
                                        </div>
                                    </div>
                                    <div class="attribute-table-row">
                                        <div class="attribute-table-label">订单金额</div>
                                        <div class="attribute-table-value">
                                                <span>
                                                    ${coupon.min}
                                                </span>
                                        </div>
                                    </div>
                                    <div class="attribute-table-row">
                                        <div class="attribute-table-label">时间</div>
                                        <div class="attribute-table-value">
                                            <span>
                                                ${coupon.startDate?string("yyyy-MM-dd")} - ${coupon.endDate?string("yyyy-MM-dd")}
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="widget-box">
                        <div class="widget-header widget-header-custom">
                            <h4 class="widget-title">使用范围</h4>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main">
                                <div class="attribute-table">
                                    <div class="attribute-table-row">
                                        <div class="attribute-table-label">组织</div>
                                        <div class="attribute-table-value">
                                                <span>

                                                </span>
                                        </div>
                                    </div>
                                    <div class="attribute-table-row">
                                        <div class="attribute-table-label">会议室</div>
                                        <div class="attribute-table-value">
                                                <span>

                                                </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-12">
                    <div class="widget-box">
                        <div class="widget-header widget-header-custom">
                            <h4 class="widget-title">领取列表</h4>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main">
                                <div class="row">
                                    <div class="col-xs-12">
                                        <table id="mcList" class="table table-striped table-bordered" cellspacing="0"
                                               width="100%">
                                            <thead>
                                            <tr>
                                                <th class="center">编号</th>
                                                <th class="center">有效期</th>
                                                <th class="center">状态</th>
                                                <th class="center">操作</th>
                                            </tr>
                                            </thead>
                                        </table>
                                        <script type="text/javascript">
                                            $(function () {
                                                $('#mcList').DataTable({
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
                                                        url: "/admin/coupons/${coupon.id}/member_coupons/dataList",
                                                        type: "GET"
                                                    },
                                                    columns: [
                                                        {data: "id", className: 'center'},
                                                        {
                                                            data: "startDate",
                                                            className: 'center',
                                                            render: function (data) {
                                                                '111'
                                                            }
                                                        },
                                                        {
                                                            data: "status",
                                                            className: 'center',
                                                            render: function (data) {
                                                                switch (data) {
                                                                    case "PENDING":
                                                                        return "未使用";
                                                                    case "USED":
                                                                        return "已使用";
                                                                }
                                                            }
                                                        },
                                                        {
                                                            data: "id",
                                                            render: function (data) {
                                                                return '<a class="btn btn-xs btn-info" href="/admin/coupons/' + data + '"><i class="ace-icon fa fa-eye bigger-120"></i></a>' +
                                                                        '<a class="btn btn-xs btn-danger" href="/admin/coupons/' + data + '/edit"><i class="ace-icon fa fa-edit bigger-120"></i></a>' +
                                                                        '<a class="btn btn-xs btn-warning" href="javascript:void(0)"><i class="ace-icon fa fa-trash bigger-120"></i></a>';
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
                </div>
                <div class="col-sm-12 padding-6">
                    <div class="col-md-offset-3  col-md-9">
                        <a class="btn btn-info" type="button"
                           href="/admin/coupons/${coupon.id}/edit?parent=${parent}">
                            <i class="icon-ok bigger-110"></i>
                            修改
                        </a>
                        &nbsp;&nbsp;
                        <button class="btn" type="reset" onclick="history.go(-1)">
                            <i class="icon-undo bigger-110"></i>
                            返回
                        </button>
                        &nbsp;&nbsp;
                        <a href="#my-modal" class="btn" data-toggle="modal">发放</a>
                        <div id="my-modal" class="modal fade" tabindex="-1">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal"
                                                aria-hidden="true">&times;
                                        </button>
                                        <h4 class="smaller lighter blue no-margin">[${coupon.name}
                                            ]发放</h4>
                                    </div>
                                    <div class="modal-body">
                                        <form action="/admin/coupons/${coupon.id}/grant" role="form"
                                              class="form-horizontal" method="post">
                                                            <@spring.bind path="grant"/>
                                            <div class="form-group row">
                                                <label class="col-sm-3 control-label no-padding-right">
                                                    发放账号 </label>
                                                <div class="col-sm-9">
                                                                    <@spring.formInput "grant.accounts","class='col-xs-10 col-sm-5'"/>
                                                    <span style="color:red; height:25px;line-height:25px;overflow:hidden;"><b>&nbsp;*</b></span>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="col-sm-3 control-label no-padding-right">
                                                    发放数量 </label>
                                                <div class="col-sm-9">
                                                                    <@spring.formInput "grant.amount","class='col-xs-10 col-sm-5' placeholder='每个用户发放的数量'"/>
                                                    <span style="color:red; height:25px;line-height:25px;overflow:hidden;"><b>&nbsp;*</b></span>
                                                </div>
                                            </div>
                                        </form>
                                    </div>

                                    <div class="modal-footer">
                                        <button class="btn btn-sm btn-danger pull-right"
                                                data-dismiss="modal">
                                            <i class="ace-icon fa fa-times"></i>
                                            Close
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@layout.myLayout>
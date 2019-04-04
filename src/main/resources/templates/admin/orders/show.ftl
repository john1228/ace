<#import "../layout/application.ftl" as layout>
<@layout.myLayout>
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
            <a href="/admin/users/">订单管理</a>
        </li>
        <li class="active">订单查看</li>
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
                <div class="col-sm-6">
                    <div class="widget-box">
                        <div class="widget-header widget-header-custom">
                            <h4 class="widget-title">订单详情</h4>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main">
                                <div class="attribute-table">
                                    <div class="attribute-table-row">
                                        <div class="attribute-table-label">账号编号</div>
                                        <div class="attribute-table-value">
                                                <span>
                                                    ${order.accountId!}
                                                </span>
                                        </div>
                                    </div>
                                    <div class="attribute-table-row">
                                        <div class="attribute-table-label">账号名字</div>
                                        <div class="attribute-table-value">
                                                <span>
                                                    ${order.accountName!}
                                                </span>
                                        </div>
                                    </div>
                                    <div class="attribute-table-row">
                                        <div class="attribute-table-label">订单号</div>
                                        <div class="attribute-table-value">
                                                <span>
                                                    ${order.orderNo!}
                                                </span>
                                        </div>
                                    </div>
                                    <div class="attribute-table-row">
                                        <div class="attribute-table-label">订单金额</div>
                                        <div class="attribute-table-value">
                                                <span>
                                                    ${order.total!}
                                                </span>
                                        </div>
                                    </div>
                                    <div class="attribute-table-row">
                                        <div class="attribute-table-label">优惠金额</div>
                                        <div class="attribute-table-value">
                                                <span>
                                                    ${order.coupon!}
                                                </span>
                                        </div>
                                    </div>
                                    <div class="attribute-table-row">
                                        <div class="attribute-table-label">实付金额</div>
                                        <div class="attribute-table-value">
                                                <span>
                                                    ${order.payAmount!}
                                                </span>
                                        </div>
                                    </div>
                                    <div class="attribute-table-row">
                                        <div class="attribute-table-label">状态</div>
                                        <div class="attribute-table-value">
                                                <span>
                                                    ${order.status!}
                                                </span>
                                        </div>
                                    </div>
                                    <div class="attribute-table-row">
                                        <div class="attribute-table-label">下单时间</div>
                                        <div class="attribute-table-value">
                                                <span>
                                                    ${order.accountName!}
                                                </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="clearfix form-actions">
                                    <div class="col-md-offset-3 col-md-9">
                                        <a class="btn btn-info" type="button" href="/admin/orders/${order.id}/edit">
                                            <i class="icon-ok bigger-110"></i>
                                            修改
                                        </a>
                                        &nbsp;&nbsp;&nbsp;
                                        <button class="btn" type="reset" onclick="history.go(-1)">
                                            <i class="icon-undo bigger-110"></i>
                                            返回
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="widget-box">
                        <div class="widget-header widget-header-custom">
                            <h4 class="widget-title">预约信息</h4>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main">
                                <div class="row">
                                    <div class="col-xs-12">
                                        <ul class="list-unstyled spaced">
                                            <li>
                                                <i class="ace-icon fa fa-bell-o bigger-110 purple"></i>
                                                List with custom icons and more space
                                            </li>

                                            <li>
                                                <i class="ace-icon fa fa-check bigger-110 green"></i>
                                                Unordered List Item # 2
                                            </li>

                                            <li>
                                                <i class="ace-icon fa fa-times bigger-110 red"></i>
                                                Unordered List Item # 3
                                            </li>
                                        </ul>

                                        <ul class="list-unstyled spaced2">
                                            <li>
                                                <i class="ace-icon fa fa-circle green"></i>
                                                Even more space
                                            </li>

                                            <li class="text-warning bigger-110 orange">
                                                <i class="ace-icon fa fa-exclamation-triangle"></i>
                                                Unordered List Item # 5
                                            </li>

                                            <li class="muted">
                                                <i class="ace-icon fa fa-angle-right bigger-110"></i>
                                                Unordered List Item # 6
                                            </li>

                                            <li>
                                                <ul class="list-inline">
                                                    <li>
                                                        <i class="ace-icon fa fa-share green bigger-110"></i>
                                                        Inline List Item # 1
                                                    </li>
                                                    <li>List Item # 2</li>
                                                    <li>List Item # 3</li>
                                                </ul>
                                            </li>
                                        </ul>
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
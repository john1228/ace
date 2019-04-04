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
            <a href="/admin/users/">用户管理</a>
        </li>
        <li class="active">用户查看</li>
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
                <h1>${coupon.name}
                    <small>
                        <i class="icon-double-angle-right">
                            详细资料
                        </i>
                    </small>
                </h1>
            </div>
            <div class="row">
                <div class="col-sm-6">
                    <div class="widget-box">
                        <div class="widget-header widget-header-custom">
                            <h4 class="widget-title">优惠券详情</h4>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main">
                                <fieldset class="fixed-border">
                                    <legend class="fixed-border">基础信息</legend>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">
                                            名字 </label>
                                        <div class="col-sm-9" style="padding:5px;">${coupon.name}</div>
                                    </div>&nbsp;
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">
                                            类型 </label>
                                        <div class="col-sm-9" style="padding:5px;">${coupon.type.getName()}</div>
                                    </div>&nbsp;
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">
                                            优惠金额 </label>
                                        <div class="col-sm-9" style="padding:5px;">${coupon.discount}</div>
                                    </div>&nbsp;
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">
                                            订单金额 </label>
                                        <div class="col-sm-9" style="padding:5px;">${coupon.min}</div>
                                    </div>&nbsp;
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">
                                            有效期方式 </label>
                                        <div class="col-sm-9" style="padding:5px;">${coupon.expiredType.getName()}</div>
                                    </div>&nbsp;
                                </fieldset>
                                <fieldset class="fixed-border">
                                    <legend class="fixed-border">范围设置</legend>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">
                                            项目 </label>
                                        <div class="col-sm-9" style="padding:5px;">${coupon.limitPro!}</div>
                                    </div>&nbsp;
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">
                                            组织 </label>
                                        <div class="col-sm-9" style="padding:5px;">${coupon.limitOrg!}</div>
                                    </div>&nbsp;
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label no-padding-right" for="form-field-1">
                                            会议室 </label>
                                        <div class="col-sm-9" style="padding:5px;">${coupon.limitRoom!}</div>
                                    </div>&nbsp;
                                </fieldset>
                                <div class="clearfix form-actions">
                                    <div class="col-md-offset-3 col-md-9">
                                        <a class="btn btn-info" type="button" href="/admin/coupons/${coupon.id}/edit">
                                            <i class="icon-ok bigger-110"></i>
                                            修改
                                        </a>
                                        &nbsp;&nbsp;&nbsp;
                                        <button class="btn" type="reset" onclick="history.go(-1)">
                                            <i class="icon-undo bigger-110"></i>
                                            返回
                                        </button>
                                        &nbsp;&nbsp;&nbsp;
                                        <button class="btn" type="reset" onclick="history.go(-1)">
                                            <i class="icon-ok bigger-110"></i>
                                            发放
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
                            <h4 class="widget-title">领取列表</h4>
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
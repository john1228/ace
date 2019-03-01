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
            <a href="/admin/users/">设备管理</a>
        </li>
        <li class="active">设备详情</li>
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
                <h1>${device.name}
                    <small>
                        <i class="icon-double-angle-right">
                            详情
                        </i>
                    </small>
                </h1>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="widget-box">
                        <div class="widget-header widget-header-flat">
                            <h4 class="widget-title">设备详情</h4>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main">
                                <fieldset class="fixed-border">
                                    <legend class="fixed-border">基础信息</legend>
                                    <div class="form-group">
                                        <p>
                                            <strong>名字:</strong> ${device.name}
                                        </p>
                                    </div>&nbsp;
                                    <div class="form-group">
                                        <p>
                                            <strong>单价:</strong> ${device.price}
                                        </p>
                                    </div>&nbsp;
                                    <div class="form-group">
                                        <p>
                                            <strong>单位:</strong> ${device.unit}
                                        </p>
                                    </div>&nbsp;
                                    <div class="form-group">
                                        <p>
                                            <strong>状态:</strong> ${device.status.getName()}
                                        </p>
                                    </div>&nbsp;
                                </fieldset>
                                <div class="clearfix form-actions">
                                    <div class="col-md-offset-3 col-md-9">
                                        <a class="btn btn-info" type="button"
                                           href="/admin/devices/${device.id}/edit?parent=${parent}">
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
            </div>
        </div>
    </div>
</@layout.myLayout>
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
            <a href="/admin/prices">设备管理</a>
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
                <h3>价格</h3>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="widget-box">
                        <div class="widget-header widget-header-custom">
                            <h4 class="widget-title">价格详情</h4>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main">
                                <div class="attribute-table">
                                    <div class="attribute-table-row">
                                        <div class="attribute-table-label">会议室</div>
                                        <div class="attribute-table-value ">
                                            <#list price.roomName as room>
                                                <span class="label label-info arrowed-in arrowed-in-right">
                                                    ${room}
                                                </span>
                                            </#list>
                                        </div>
                                    </div>
                                    <div class="attribute-table-row">
                                        <div class="attribute-table-label">出租方式</div>
                                        <div class="attribute-table-value">
                                        <span>
                                            ${price.rental.getName()}
                                        </span>
                                        </div>
                                    </div>
                                    <div class="attribute-table-row">
                                        <div class="attribute-table-label">适用日期</div>
                                        <div class="attribute-table-value">
                                        <span>
                                            ${(price.startDate?string('yyyy-MM-dd'))!"n/a"} 至 ${(price.endDate?string('yyyy-MM-dd'))!"n/a"}
                                        </span>
                                        </div>
                                    </div>
                                    <div class="attribute-table-row">
                                        <div class="attribute-table-label">周</div>
                                        <div class="attribute-table-value">
                                            <#list price.wday as week>
                                                <span class="label label-info arrowed-in arrowed-in-right">${week}</span>
                                            </#list>
                                        </div>
                                    </div>
                                    <div class="attribute-table-row">
                                        <div class="attribute-table-label">价格</div>
                                        <div class="attribute-table-value">
                                        <span>
                                            ${price.price}
                                        </span>
                                        </div>
                                    </div>
                                    <div class="attribute-table-row">
                                        <div class="attribute-table-label">创建时间</div>
                                        <div class="attribute-table-value">
                                        <span>
                                            ${price.createdAt?string('yyyy-MM-dd HH:mm:ss')}
                                        </span>
                                        </div>
                                    </div>
                                    <div class="attribute-table-row">
                                        <div class="attribute-table-label">更新时间</div>
                                        <div class="attribute-table-value">
                                        <span>
                                            ${price.updatedAt?string('yyyy-MM-dd HH:mm:ss')}
                                        </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="clearfix form-actions">
                                    <div class="col-md-offset-3 col-md-9">
                                        <a class="btn btn-info" type="button"
                                           href="/admin/prices/${price.id}/edit">
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
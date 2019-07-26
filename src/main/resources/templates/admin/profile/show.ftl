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
        <li class="active">操作员资料</li>
    </ul>
</div>

<div class="page-content">
    <div class="row">
        <div class="col-xs-12">
            <div class="page-header">
                <h3>资料详情</h3>
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
                                        <div class="attribute-table-label">账户名</div>
                                        <div class="attribute-table-value">
                                        <span>
                                            ${account.accountName}
                                        </span>
                                        </div>
                                    </div>
                                    <#if account.isAdmin()>
                                        <div class="attribute-table-row">
                                            <div class="attribute-table-label">包办用户</div>
                                            <div class="attribute-table-value">
                                            <span>
                                                是
                                            </span>
                                            </div>
                                        </div>
                                    </#if>
                                    <div class="attribute-table-row">
                                        <div class="attribute-table-label">
                                            组织信息
                                        </div>
                                        <div class="attribute-table-value" 　 style="display: table-cell;col: 3">
                                            <#list account.staffList as staff>
                                                <div style="padding: 5px;border: 1px dotted #b1b1b1;">
                                                    <div>项目: ${staff.projectName}</div>
                                                    <div>组织: ${staff.orgName}</div>
                                                    <div>员工: ${staff.empName}</div>
                                                </div>
                                            </#list>
                                        </div>
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
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
</div>

<div class="page-content">
    <div class="row">
        <div class="page-title">
            <h3>${order.orderNo}</h3>
        </div>
        <div class="col-sm-6">
            <div class="widget-box">
                <div class="widget-header widget-header-custom">
                    订单信息
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
                                                    ${order.status.getName()}
                                                </span>
                                </div>
                            </div>
                            <div class="attribute-table-row">
                                <div class="attribute-table-label">下单时间</div>
                                <div class="attribute-table-value">
                                                <span>
                                                    ${order.createdAt?string("yyyy-MM-dd HH:mm:ss")}
                                                </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-6">
            <div class="widget-box">
                <div class="widget-header widget-header-custom">
                    预约信息
                </div>
                <div class="widget-body">
                    <div class="widget-main">
                        <div class="attribute-table">
                            <div class="attribute-table-row">
                                <div class="attribute-table-label">会议室</div>
                                <div class="attribute-table-value">
                                        <span>
                                            ${order.appointment.roomName!}
                                        </span>
                                </div>
                            </div>
                            <div class="attribute-table-row">
                                <div class="attribute-table-label">预约时间</div>
                                <div class="attribute-table-value">
                                    ${order.appointment.startTime?string("yyyy-MM-dd HH:mm")}
                                    - ${order.appointment.endTime?string("HH:mm")}
                                </div>
                            </div>
                            <div class="attribute-table-row">
                                <div class="attribute-table-label">选择服务</div>
                                <div class="attribute-table-value">
                                    <div class="media search-media">
                                        <#list order.appointment.service as service>
                                            <div class="media-body">
                                                <div>${service.name}</div>
                                                <div class="search-actions text-center">
                                                    ￥${service.price}x${service.amount}${service.unit}
                                                </div>
                                            </div>
                                        </#list>
                                    </div>
                                </div>
                            </div>
                            <div class="attribute-table-row">
                                <div class="attribute-table-label">联系人</div>
                                <div class="attribute-table-value">
                                    ${order.appointment.contactName!}
                                </div>
                            </div>
                            <div class="attribute-table-row">
                                <div class="attribute-table-label">联系电话</div>
                                <div class="attribute-table-value">
                                    ${order.appointment.contactMobile!}
                                </div>
                            </div>
                            <div class="attribute-table-row">
                                <div class="attribute-table-label">备注</div>
                                <div class="attribute-table-value">
                                    ${order.appointment.remark!}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <#--<div class="col-xs-12 col-sm-12">-->
            <#--<div class="widget-box">-->
                <#--<div class="widget-header widget-header-custom">-->
                    <#--备注-->
                <#--</div>-->
                <#--<div class="widget-body">-->
                    <#--<div class="widget-main">-->
                    <#--</div>-->
                <#--</div>-->
            <#--</div>-->
        <#--</div>-->
    </div>
    <div class="row">
        <div class="clearfix form-actions">
            <div class="col-md-offset-3 col-md-9">
                <button class="btn" type="reset" onclick="history.go(-1)">
                    <i class="icon-undo bigger-110"></i>
                    返回
                </button>
            </div>
        </div>
    </div>
</div>
</@layout.myLayout>
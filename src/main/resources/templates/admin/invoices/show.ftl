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
            <a href="/admin/users/">发票管理</a>
        </li>
        <li class="active">发票查看</li>
    </ul>
</div>

<div class="page-content">
    <div class="row">
        <div class="col-xs-12">
            <div class="page-header">
                <h1>
                    <small>
                        <i class="icon-double-angle-right">
                            发票资料
                        </i>
                    </small>
                </h1>
            </div>
            <div class="row">
                <div class="col-sm-6">
                    <div class="widget-box">
                        <div class="widget-header widget-header-custom">
                            订单信息
                        </div>
                        <div class="widget-body">
                            <div class="widget-main">
                                <div class="attribute-table-row">
                                    <div class="attribute-table-label">订单号</div>
                                    <div class="attribute-table-value">
                                        ${order.orderNo!}
                                    </div>
                                </div>
                                <div class="attribute-table-row">
                                    <div class="attribute-table-label">会议室</div>
                                    <div class="attribute-table-value">
                                        ${order.roomName!}
                                    </div>
                                </div>
                                <div class="attribute-table-row">
                                    <div class="attribute-table-label">预订人</div>
                                    <div class="attribute-table-value">
                                        ${order.contactName!}
                                    </div>
                                </div>
                                <div class="attribute-table-row">
                                    <div class="attribute-table-label">预订人电话</div>
                                    <div class="attribute-table-value">
                                        ${order.contactMobile!}
                                    </div>
                                </div>
                                <div class="attribute-table-row">
                                    <div class="attribute-table-label">使用时间</div>
                                    <div class="attribute-table-value">
                                        ${order.appointTime!}
                                    </div>
                                </div>
                                <div class="attribute-table-row">
                                    <div class="attribute-table-label">订单金额</div>
                                    <div class="attribute-table-value">
                                        ${order.total!}
                                    </div>
                                </div>
                                <div class="attribute-table-row">
                                    <div class="attribute-table-label">实付金额</div>
                                    <div class="attribute-table-value">
                                        ${order.payAmount!}
                                    </div>
                                </div>
                                <div class="attribute-table-row">
                                    <div class="attribute-table-label">下单时间</div>
                                    <div class="attribute-table-value">
                                        ${order.createdAt?string("yyyy-MM-dd HH:mm:ss")}
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <#if invoice??>
                <div class="col-sm-6">
                    <div class="widget-box">
                        <div class="widget-header widget-header-custom">
                            发票信息
                        </div>
                        <div class="widget-body">
                            <div class="widget-main">
                                <div class="attribute-table-row">
                                    <div class="attribute-table-label">发票类型</div>
                                    <div class="attribute-table-value">
                                        ${invoice.type.getName()}
                                    </div>
                                </div>
                                <#if invoice.type.name() == "GVATI">
                                    <div class="attribute-table-row">
                                        <div class="attribute-table-label">类型</div>
                                        <div class="attribute-table-value">
                                            ${invoice.content["type"]!}
                                        </div>
                                    </div>
                                    <div class="attribute-table-row">
                                        <div class="attribute-table-label">抬头</div>
                                        <div class="attribute-table-value">
                                            ${invoice.content["title"]!}
                                        </div>
                                    </div>
                                    <#if invoice.content["type"] == "公司">
                                        <div class="attribute-table-row">
                                            <div class="attribute-table-label">纳税人识别号</div>
                                            <div class="attribute-table-value">
                                                ${invoice.content["taxpayer"]!}
                                            </div>
                                        </div>
                                    </#if>
                                <#else>
                                    <div class="attribute-table-row">
                                        <div class="attribute-table-label">抬头</div>
                                        <div class="attribute-table-value">
                                            ${invoice.content["title"]!}
                                        </div>
                                    </div>
                                    <div class="attribute-table-row">
                                        <div class="attribute-table-label">纳税人识别号</div>
                                        <div class="attribute-table-value">
                                            ${invoice.content["taxpayer"]!}
                                        </div>
                                    </div>
                                    <div class="attribute-table-row">
                                        <div class="attribute-table-label">开户行</div>
                                        <div class="attribute-table-value">
                                            ${invoice.content["bank"]!}
                                        </div>
                                    </div>
                                    <div class="attribute-table-row">
                                        <div class="attribute-table-label">账户</div>
                                        <div class="attribute-table-value">
                                            ${invoice.content["bankNo"]!}
                                        </div>
                                    </div>
                                    <div class="attribute-table-row">
                                        <div class="attribute-table-label">地址电话</div>
                                        <div class="attribute-table-value">
                                            ${invoice.content["contact"]!}
                                        </div>
                                    </div>
                                </#if>
                                <div class="attribute-table-row">
                                    <div class="attribute-table-label">备注</div>
                                    <div class="attribute-table-value">
                                        ${invoice.remark!}
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="widget-box">
                        <div class="widget-header widget-header-custom">
                            邮寄信息
                        </div>
                        <div class="widget-body">
                            <div class="widget-main">
                                <div class="attribute-table-row">
                                    <div class="attribute-table-label">邮寄方式</div>
                                    <div class="attribute-table-value">
                                        ${invoice.method.getName()}
                                    </div>
                                </div>
                                <#if invoice.method.name() ="EMAIL">
                                 <div class="attribute-table-row">
                                     <div class="attribute-table-label">邮寄地址</div>
                                     <div class="attribute-table-value">
                                         ${invoice.address["address"]}
                                     </div>
                                 </div>
                                <#else>
                                <div class="attribute-table-row">
                                    <div class="attribute-table-label">收件人</div>
                                    <div class="attribute-table-value">
                                        ${invoice.address["name"]}
                                    </div>
                                </div>
                                <div class="attribute-table-row">
                                    <div class="attribute-table-label">联系电话</div>
                                    <div class="attribute-table-value">
                                        ${invoice.address["mobile"]}
                                    </div>
                                </div>
                                <div class="attribute-table-row">
                                    <div class="attribute-table-label">收货地址</div>
                                    <div class="attribute-table-value">
                                        ${invoice.address["address"]}
                                    </div>
                                </div>
                                </#if>
                            </div>
                        </div>
                    </div>
                    <#if invoice.express??>
                     <div class="widget-box">
                         <div class="widget-header widget-header-custom">
                             快递信息
                         </div>
                         <div class="widget-body">
                             <div class="widget-main">
                                 <div class="attribute-table-row">
                                     <div class="attribute-table-label">快递公司</div>
                                     <div class="attribute-table-value">
                                         ${invoice.express["company"]!}
                                     </div>
                                 </div>
                                 <div class="attribute-table-row">
                                     <div class="attribute-table-label">快递单号</div>
                                     <div class="attribute-table-value">
                                         ${invoice.express["no"]!}
                                     </div>
                                 </div>
                             </div>
                         </div>
                     </div>
                    </#if>
                </div>
                </#if>

            </div>
        </div>
    </div>
    <div class="row">
        <div class="clearfix form-actions">
            <div class="col-md-offset-3 col-md-9">
                <button class="btn" type="reset" onclick="history.go(-1)">
                    返回
                </button>
                <#if invoice??>
                &nbsp;&nbsp;&nbsp;
                <button class="btn" type="reset" id="mail">
                    邮寄
                </button>
                <div id="closedModal" class="modal fade" tabindex="-1">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <form action="/admin/invoices/${order.orderNo}/mail" role="form" class="form-horizontal"
                                  enctype="multipart/form-data"
                                  data-toggle="validator" method="post" id="closedFrm">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        &times;
                                    </button>
                                    <h5 class="smaller lighter blue no-margin">寄送</h5>
                                </div>
                                <div class="modal-body">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    <div class="form-group row">
                                        <div class="col-sm-2 control-label no-padding-right">快递公司</div>
                                        <div class="col-sm-10">
                                            <input type="text" name="express[company]" class="form-control"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-sm-2 control-label no-padding-right">快递单号</div>
                                        <div class="col-sm-10">
                                            <input type="text" name="express[no]" class="form-control"/>
                                        </div>
                                    </div>
                                </div>

                                <div class="modal-footer">
                                    <button class="btn btn-sm btn-primary pull-right" type="submit">
                                        提交
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <script type="text/javascript">
                    $(function () {
                        $("#mail").on("click", function () {
                            $('#closedModal').modal('show');
                        })
                    })
                </script>
                <#else>
                    &nbsp;&nbsp;&nbsp;
                    <a href="/admin/invoices/new?orderNo=${order.orderNo!}" class="btn">
                        登记开票
                    </a>
                </#if>
            </div>
        </div>
    </div>
</div>
</@layout.myLayout>
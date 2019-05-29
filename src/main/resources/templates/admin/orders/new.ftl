<#import "../layout/application.ftl" as layout>
<#import "/spring.ftl" as spring />
<@layout.myLayout>
<script src="/assets/js/jquery/dataTables.min.js"></script>
<script src="/assets/js/jquery/dataTables.bootstrap.min.js"></script>
<script src="/assets/js/jquery/dataTables.buttons.min.js"></script>
<link rel="stylesheet" href="/assets/css/jquery/buttons.dataTables.min.css"/>
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
            <a href="/admin/orders/">订单管理</a>
        </li>
        <li class="active">订单新增</li>
    </ul>
</div>
<div class="page-content">
    <div class="row">
        <div class="col-xs-12">
            <div class="page-title">
                <h1>订单新增</h1>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <form action="/admin/orders/" role="form" class="form-horizontal" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="hidden" name="staffId" value="${current_operator.getId()}">
                        <@spring.bind path="order"/>
                        <div class="widget-box">
                            <div class="widget-header widget-header-custom">
                                <h5 class="widget-title">订单信息</h5>
                            </div>
                            <div class="widget-body">
                                <div class="widget-main padding-8">
                                    <div class="form-group row">
                                        <label class="col-sm-2 control-label no-padding-right"><span
                                                style="color: red">*</span>会议室</label>
                                        <div class="col-sm-6 col-xs-12">
                                            <select id="roomId" name="appointment.roomId"
                                                    class="chosen-select col-xs-12 col-sm-9"
                                                    data-placeholder="请选择会议室">
                                            <#list rooms as room>
                                                <option value="${room.id}">${room.name}</option>
                                            </#list>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-sm-2 control-label no-padding-right"><span
                                                style="color: red">*</span>预约时间</label>
                                        <div class="col-sm-6 col-xs-12">
                                            <div class="input-group">
                                                <input type="text" class="input-sm form-control datetimepicker"
                                                       name="appointment.startTime">
                                                <span class="input-group-addon"><i class="fa fa-exchange"></i></span>
                                                <input type="text" class="input-sm form-control datetimepicker"
                                                       name="appointment.endTime">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-sm-2 control-label no-padding-right"><span
                                                style="color: red">*</span>原价格</label>
                                        <div class="col-sm-6 col-xs-12">
                                            <@spring.formInput "order.total","class='form-control' required"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-sm-2 control-label no-padding-right"><span
                                                style="color: red">*</span>优惠金额</label>
                                        <div class="col-sm-6 col-xs-12">
                                            <@spring.formInput "order.coupon","class='form-control' required"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-sm-2 control-label no-padding-right"><span
                                                style="color: red">*</span>支付金额</label>
                                        <div class="col-sm-6 col-xs-12">
                                            <@spring.formInput "order.payAmount","class='form-control' required"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-sm-2 control-label no-padding-right"><span
                                                style="color: red">*</span>支付方式</label>
                                        <div class="col-sm-6 col-xs-12">
                                            <@spring.formInput "order.payAmount","class='form-control' required"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-sm-2 control-label no-padding-right"><span
                                                style="color: red">*</span>第三方流水号</label>
                                        <div class="col-sm-6 col-xs-12">
                                            <@spring.formInput "order.payAmount","class='form-control' required"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="widget-box">
                            <div class="widget-header widget-header-custom">
                                <h5 class="widget-title">服务</h5>
                            </div>
                            <div class="widget-body">
                                <div class="widget-main padding-8">
                                    <table id="supportList" class="table table-striped table-bordered" cellspacing="0"
                                           width="100%">
                                        <thead>
                                        <tr>
                                            <td class="center">
                                                <input type="checkbox" class="checkAll"/>
                                                <script>
                                                    $(function () {
                                                        $(".checkAll").change(function () {
                                                            $(".spItem").prop('checked', this.checked);
                                                        })
                                                    })
                                                </script>
                                            </td>
                                            <th class="center">名称</th>
                                            <th class="center">图片</th>
                                            <th class="center">价格</th>
                                            <th class="center">数量</th>
                                        </tr>
                                        </thead>
                                    </table>
                                    <script type="text/javascript">
                                        $(function () {
                                            var $support = $('#supportList');
                                            $support.DataTable({
                                                paging: false,
                                                searching: false,
                                                processing: true,
                                                serverSide: true,
                                                autoWidth: false,
                                                ordering: false,
                                                bInfo: false,
                                                ajax: {
                                                    url: "/admin/rooms/supportList",
                                                    type: "POST",
                                                    data: function (data) {
                                                        data.roomId = $("#roomId").val();
                                                    }
                                                },
                                                columns: [
                                                    {data: "name", className: 'center'},
                                                    {data: "cover", className: 'center'},
                                                    {data: "price", className: 'center'},
                                                    {data: "unit", className: 'center'},
                                                    {data: "id", className: 'center'}
                                                ]
                                            });
                                            $("#roomId").on("change", function () {
                                                $support.DataTable().draw(true);
                                            })
                                        })
                                    </script>
                                </div>
                            </div>
                        </div>
                        <div class="widget-box">
                            <div class="widget-header widget-header-custom">
                                <h5 class="widget-title">预订信息</h5>
                            </div>
                            <div class="widget-body">
                                <div class="widget-main padding-8">
                                    <div class="form-group row">
                                        <label class="col-sm-2 control-label no-padding-right"><span
                                                style="color: red">*</span>联系人</label>
                                        <div class="col-sm-6 col-xs-12">
                                            <@spring.formInput "order.appointment.contactName","class='form-control' required"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-sm-2 control-label no-padding-right"><span
                                                style="color: red">*</span>联系电话</label>
                                        <div class="col-sm-6 col-xs-12">
                                            <@spring.formInput "order.appointment.contactMobile","class='form-control' required"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-sm-2 control-label no-padding-right"><span
                                                style="color: red">*</span>会议</label>
                                        <div class="col-sm-6 col-xs-12">
                                            <@spring.formInput "order.appointment.meetingName","class='form-control' required"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row clearfix form-actions">
                            <div class="col-md-offset-3 col-md-9">
                                <button class="btn btn-info" type="submit">
                                    <i class="ace-icon fa fa-check bigger-110"></i>
                                    提交
                                </button>
                                &nbsp; &nbsp; &nbsp;
                                <button class="btn" type="reset" onclick="history.go(-1)">
                                    <i class="ace-icon fa fa-undo bigger-110"></i>
                                    返回
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</@layout.myLayout>

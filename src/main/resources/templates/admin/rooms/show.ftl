<#import "../layout/application.ftl" as layout>
<@layout.myLayout>
<script src="/assets/js/jquery/dataTables.min.js"></script>
<script src="/assets/js/jquery/dataTables.bootstrap.min.js"></script>
<script src="/assets/js/jquery/dataTables.buttons.min.js"></script>
<script src="/assets/js/bootstrap/timepicker.min.js"></script>
<link rel="stylesheet" href="/assets/css/jquery/buttons.dataTables.min.css"/>
<link rel="stylesheet" href="/assets/css/bootstrap/timepicker.min.css"/>
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
            <a href="/admin/rooms">场地</a>
        </li>
        <li class="active">查看场地</li>
    </ul>
</div>
    <#setting number_format="0.##">
<div class="page-content">
    <div class="page-title">
        <h3>
            <i class="fa fa-tag"></i>
            ${room.name}
        </h3>
    </div>
    <div class="col-xs-12">
        <div class="row">
            <div class="widget-box">
                <div class="widget-header widget-header-custom">
                    <h5 class="widget-title">会场列表图</h5>
                </div>
                <div class="widget-body">
                    <div class="widget-main">
                        <img src="${image + '/'+ room.cover}" style="object-fit: cover;width: 100px;height: 100px"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="widget-box">
                <div class="widget-header widget-header-custom">
                    <h5 class="widget-title">会场图片</h5>
                </div>
                <div class="widget-body">
                    <div class="widget-main">
                            <#list room.image as img>
                                <img src="${image + '/'+ img}" style="object-fit: cover;width: 100px;height: 100px"/>
                            </#list>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="widget-box">
                <div class="widget-header widget-header-custom">
                    <h5 class="widget-title">会场基本信息</h5>
                </div>
                <div class="widget-body">
                    <div class="widget-main">
                        <div class="attribute-table">
                            <div class="attribute-table-row col-xs-12 col-sm-6">
                                <div class="attribute-table-label">场地编号</div>
                                <div class="attribute-table-value">
                                        <span>
                                            ${room.serialNo!}
                                        </span>
                                </div>
                            </div>
                            <div class="attribute-table-row col-xs-12 col-sm-6">
                                <div class="attribute-table-label">栋号</div>
                                <div class="attribute-table-value">
                                        <span>
                                            ${room.buildingNo}
                                        </span>
                                </div>
                            </div>
                            <div class="attribute-table-row col-xs-12 col-sm-6">
                                <div class="attribute-table-label">楼层</div>
                                <div class="attribute-table-value">
                                        <span>
                                            ${room.floorNo}
                                        </span>
                                </div>
                            </div>
                            <div class="attribute-table-row col-xs-12 col-sm-6">
                                <div class="attribute-table-label">房号</div>
                                <div class="attribute-table-value">
                                        <span>
                                            ${room.roomNo}
                                        </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="widget-box">
                <div class="widget-header widget-header-custom">
                    <h5 class="widget-title">会场数据信息</h5>
                </div>
                <div class="widget-body">
                    <div class="widget-main">
                        <div class="attribute-table">
                            <div class="attribute-table-row col-xs-12 col-sm-6">
                                <div class="attribute-table-label">场地层高</div>
                                <div class="attribute-table-value">
                                    ${room.layerHeight!}　米
                                </div>
                            </div>
                            <div class="attribute-table-row col-xs-12 col-sm-6">
                                <div class="attribute-table-label">场地类型</div>
                                <div class="attribute-table-value">
                                    ${room.type}
                                </div>
                            </div>
                            <div class="attribute-table-row col-xs-12 col-sm-6">
                                <div class="attribute-table-label">发布类型</div>
                                <div class="attribute-table-value">
                                    ${room.publish.getName()}
                                </div>
                            </div>
                            <div class="attribute-table-row col-xs-12 col-sm-6">
                                <div class="attribute-table-label">面积</div>
                                <div class="attribute-table-value">
                                    ${room.layerArea}　m²
                                </div>
                            </div>
                            <div class="attribute-table-row col-xs-12 col-sm-6">
                                <div class="attribute-table-label">最大容纳人数</div>
                                <div class="attribute-table-value">
                                    ${room.quota}　人
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="widget-box">
                <div class="widget-header widget-header-custom">
                    <h5 class="widget-title">会场出租信息</h5>
                </div>
                <div class="widget-body">
                    <div class="widget-main">
                        <div class="attribute-table">
                            <div class="attribute-table-row col-xs-12 col-sm-6">
                                <div class="attribute-table-label">出租收费</div>
                                <div class="attribute-table-value">
                                    ${room.free?string("免费","收费")}
                                </div>
                            </div>
                            <div class="attribute-table-row col-xs-12 col-sm-6">
                                <div class="attribute-table-label">出租方式</div>
                                <div class="attribute-table-value">
                                    ${room.rental.getName()}
                                </div>
                            </div>
                            <div class="attribute-table-row col-xs-12 col-sm-6">
                                <div class="attribute-table-label">开放时间</div>
                                <div class="attribute-table-value">
                                    ${room.openDate?string("yyyy-MM-dd")} 至　${room.closeDate?string("yyyy-MM-dd")}
                                </div>
                            </div>
                            <div class="attribute-table-row col-xs-12 col-sm-6">
                                <div class="attribute-table-label">最短起租时间</div>
                                <div class="attribute-table-value">
                                    ${room.unit} 个/半小时
                                </div>
                            </div>
                            <div class="attribute-table-row col-xs-12 col-sm-6">
                                <div class="attribute-table-label">最短续租时间</div>
                                <div class="attribute-table-value">
                                    ${room.renew} 小时
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="widget-box">
                <div class="widget-header widget-header-custom">
                    <h5 class="widget-title">联系方式</h5>
                </div>
                <div class="widget-body">
                    <div class="widget-main">
                        <div class="attribute-table">
                            <div class="attribute-table-row col-xs-12 col-sm-6">
                                <div class="attribute-table-label">负责人</div>
                                <div class="attribute-table-value">
                                    ${room.supervisor}
                                </div>
                            </div>
                            <div class="attribute-table-row col-xs-12 col-sm-6">
                                <div class="attribute-table-label">联系电话</div>
                                <div class="attribute-table-value">
                                    ${room.supervisorMobile}
                                </div>
                            </div>
                            <div class="attribute-table-row col-xs-12 col-sm-6">
                                <div class="attribute-table-label">邮箱</div>
                                <div class="attribute-table-value">
                                    ${room.supervisorEmail}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="widget-box">
                <div class="widget-header widget-header-custom">
                    <h5 class="widget-title">订单相关</h5>
                </div>
                <div class="widget-body">
                    <div class="widget-main">
                        <div class="attribute-table">
                            <div class="attribute-table-row col-xs-12 col-sm-6">
                                <div class="attribute-table-label">确认方式</div>
                                <div class="attribute-table-value">
                                    ${room.cfm.getName()}
                                </div>
                            </div>
                            <div class="attribute-table-row col-xs-12 col-sm-6">
                                <div class="attribute-table-label">是否支付</div>
                                <div class="attribute-table-value">
                                    ${room.payable?string("是","否")}
                                </div>
                            </div>
                            <div class="attribute-table-row col-xs-12 col-sm-6">
                                <div class="attribute-table-label">退款限时</div>
                                <div class="attribute-table-value">
                                    ${room.rlt!} 小时
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="widget-box">
                <div class="widget-header widget-header-custom">
                    <h5 class="widget-title">简介</h5>
                </div>
                <div class="widget-body">
                    <div class="widget-main">
                        ${room.resume}
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="widget-box">
                <div class="widget-header widget-header-custom">
                    <h5 class="widget-title">服务</h5>
                </div>
                <div class="widget-body">
                    <div class="widget-main">
                        <div class="attribute-table">
                            <div class="attribute-table-row col-xs-12 col-sm-12">
                                <div class="attribute-table-label">免费</div>
                                <div class="attribute-table-value">
                                    ${room.freeService}
                                </div>
                            </div>
                            <div class="attribute-table-row col-xs-12 col-sm-12">
                                <div class="attribute-table-label">收费</div>
                                <div class="attribute-table-value" style="display: table-cell">
                                    <#list room.supportList as support>
                                        <div style="padding: 5px;border: 1px dotted #b1b1b1">
                                            <div>${support.name}</div>
                                            <div><img src="${support.cover}" class="img-50"/></div>
                                            <div>${support.price}/${support.unit}</div>
                                        </div>
                                    </#list>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row" style="border: 1px solid #CCC">
            <div class="table-header" style="background-color:#F3FBFB;color: #37B0AE ">临时不开放时间</div>
            <table id="closedList" class="table table-striped table-bordered" cellspacing="0"
                   width="100%">
                <thead>
                <tr>
                    <th class="center">日期</th>
                    <th class="center">时间</th>
                    <th class="center">备注</th>
                    <th class="center">操作</th>
                </tr>
                </thead>
            </table>
            <script type="text/javascript">
                $(function () {
                    var table = $('#closedList').DataTable({
                        language: {
                            sProcessing: "处理中...",
                            sLengthMenu: "显示 _MENU_ 项结果",
                            sZeroRecords: "没有匹配结果",
                            sInfo: "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                            sInfoEmpty: "显示第 0 至 0 项结果，共 0 项",
                            sInfoFiltered: "",
                            sInfoPostFix: "",
                            sUrl: "",
                            sEmptyTable: "暂无数据",
                            sLoadingRecords: "载入中...",
                            sInfoThousands: ",",
                            oPaginate: {
                                sFirst: "首页",
                                sPrevious: "上一页",
                                sNext: "下一页",
                                sLast: "末页"
                            }
                        },
                        searching: false,
                        paging: false,
                        processing: true,
                        serverSide: true,
                        autoWidth: false,
                        ordering: false,
                        ajax: {
                            url: "/admin/rooms/${room.id}/closed/dataList",
                            type: "GET"
                        },
                        dom: 'Bfltip',
                        buttons: [
                            {
                                text: '新建',
                                className: 'btn btn-primary pull-right',
                                action: function (e, dt, node, config) {
                                    $('#closedModal').modal('show')
                                }
                            }
                        ],
                        columns: [
                            {
                                data: "startDate",
                                className: 'center',
                                render: function (data, type, row) {
                                    return row.startDate + " - " + row.endDate;
                                }
                            },
                            {
                                data: "startTime",
                                className: 'center',
                                render: function (data, type, row) {
                                    return row.startTime + " - " + row.endTime;
                                }
                            },
                            {data: "remark", className: 'center'},
                            {
                                data: "id",
                                className: 'center',
                                render: function (data) {
                                    return '<a class="btn btn-xs btn-warning rest"　href="/admin/rooms/${room.id}/closed/' + data + '" data-method="DELETE">删除</a>';
                                }
                            }
                        ]
                    });
                })
            </script>
            <div id="closedModal" class="modal fade" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="/admin/rooms/${room.id}/closed" role="form" class="form-horizontal"
                              enctype="multipart/form-data"
                              data-toggle="validator" method="post" id="closedFrm">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;
                                </button>
                                <h5 class="smaller lighter blue no-margin">新建不课开放时间</h5>
                            </div>
                            <div class="modal-body">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <input type="hidden" name="roomId" value="${room.id}">
                                <div class="form-group row">
                                    <div class="col-sm-2 control-label no-padding-right">日期</div>
                                    <div class="col-sm-10">
                                        <div class="col-xs-12 col-sm-12 input-group input-daterange">
                                            <input id="startDate" name="startDate" class="form-control">
                                            <div class="input-group-addon">至</div>
                                            <input id="endDate" name="endDate" class="form-control">
                                        </div>
                                        <script type="text/javascript">
                                            $('.input-daterange input').each(function () {
                                                $(this).datepicker({language: 'zh'});
                                            });
                                        </script>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-2 control-label no-padding-right">时间</div>
                                    <div class="col-sm-10">
                                        <div class="col-xs-12 col-sm-12 input-group">
                                            <input class="form-control" name="startTime" d="startTime">
                                            <div class="input-group-addon">至</div>
                                            <input class="form-control" name="endTime" id="endTime">
                                        </div>
                                        <script>
                                            $(function () {
                                                $('#startTime').timepicker({
                                                    minuteStep: 5,
                                                    showSeconds: false,
                                                    showMeridian: false,
                                                    disableFocus: true,
                                                    icons: {
                                                        up: 'fa fa-chevron-up',
                                                        down: 'fa fa-chevron-down'
                                                    }
                                                }).on('focus', function () {
                                                    $('#startTime').timepicker('showWidget');
                                                }).next().on(ace.click_event, function () {
                                                    $(this).prev().focus();
                                                });
                                                $('#endTime').timepicker({
                                                    minuteStep: 5,
                                                    showSeconds: false,
                                                    showMeridian: false,
                                                    disableFocus: true,
                                                    icons: {
                                                        up: 'fa fa-chevron-up',
                                                        down: 'fa fa-chevron-down'
                                                    }
                                                }).on('focus', function () {
                                                    $('#endTime').timepicker('showWidget');
                                                }).next().on(ace.click_event, function () {
                                                    $(this).prev().focus();
                                                });

                                            })
                                        </script>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-2 control-label no-padding-right">备注</div>
                                    <div class="col-sm-10">
                                        <div class="file-loading">
                                            <textarea name="remark" class="form-control"></textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="modal-footer">
                                <button class="btn btn-sm btn-primary pull-right" type="submit">
                                    提交
                                </button>
                            </div>
                    </div><!-- /.modal-content -->
                    </form>
                </div><!-- /.modal-dialog -->
            </div>
        </div>
    </div>
</div>
</@layout.myLayout>
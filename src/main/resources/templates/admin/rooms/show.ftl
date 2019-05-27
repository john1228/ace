<#import "../layout/application.ftl" as layout>
<@layout.myLayout>
<script src="/assets/js/jquery/dataTables.min.js"></script>
<script src="/assets/js/jquery/dataTables.bootstrap.min.js"></script>
<script src="/assets/js/jquery/dataTables.buttons.min.js"></script>
<script src="/assets/js/bootstrap/timepicker.min.js"></script>
<link rel="stylesheet" href="/assets/css/jquery/buttons.dataTables.min.css"/>
<link rel="stylesheet" href="/assets/csss/bootstrap/timepicker.min.css"/>
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
<div class="page-content">
    <div class="row">
        <div class="col-xs-12">
            <div class="page-header">
                <h3>${room.name}</h3>
            </div>
            <div class="row">
                <div class="widget-box">
                    <div class="widget-header widget-header-custom">
                        <h5 class="widget-title">会场列表图</h5>
                    </div>
                    <div class="widget-body">
                        <div class="widget-main">
                            <img src="${image + '/'+ room.cover}"/>
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
                                <img src="${image + '/' + img}"/>
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
                                        <span>
                                            ${room.layerHeight!}
                                        </span>
                                    </div>
                                </div>
                                <div class="attribute-table-row col-xs-12 col-sm-6">
                                    <div class="attribute-table-label">场地类型</div>
                                    <div class="attribute-table-value">
                                        <span>
                                            ${room.type}
                                        </span>
                                    </div>
                                </div>
                                <div class="attribute-table-row col-xs-12 col-sm-6">
                                    <div class="attribute-table-label">发布类型</div>
                                    <div class="attribute-table-value">
                                        <span>
                                            ${room.publish}
                                        </span>
                                    </div>
                                </div>
                                <div class="attribute-table-row col-xs-12 col-sm-6">
                                    <div class="attribute-table-label">面积</div>
                                    <div class="attribute-table-value">
                                        <span>
                                            ${room.layerArea}
                                        </span>
                                    </div>
                                </div>
                                <div class="attribute-table-row col-xs-12 col-sm-6">
                                    <div class="attribute-table-label">最大容纳人数</div>
                                    <div class="attribute-table-value">
                                        <span>
                                            ${room.quota}
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
                        <h5 class="widget-title">会场出租信息</h5>
                    </div>
                    <div class="widget-body">
                        <div class="widget-main">
                            <div class="attribute-table">
                                <div class="attribute-table-row col-xs-12 col-sm-6">
                                    <div class="attribute-table-label">出租收费</div>
                                    <div class="attribute-table-value">
                                        <span>
                                            ${room.free?string("免费","收费")}
                                        </span>
                                    </div>
                                </div>
                                <div class="attribute-table-row col-xs-12 col-sm-6">
                                    <div class="attribute-table-label">出租方式</div>
                                    <div class="attribute-table-value">
                                        <span>
                                            ${room.rental}
                                        </span>
                                    </div>
                                </div>
                                <div class="attribute-table-row col-xs-12 col-sm-6">
                                    <div class="attribute-table-label">开放时间</div>
                                    <div class="attribute-table-value">
                                        <span>
                                            ${room.publish}
                                        </span>
                                    </div>
                                </div>
                                <div class="attribute-table-row col-xs-12 col-sm-6">
                                    <div class="attribute-table-label">起租时间</div>
                                    <div class="attribute-table-value">
                                        <span>
                                            ${room.unit}
                                        </span>
                                    </div>
                                </div>
                                <div class="attribute-table-row col-xs-12 col-sm-6">
                                    <div class="attribute-table-label">续租时间</div>
                                    <div class="attribute-table-value">
                                        <span>
                                            ${room.renew}
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
                        <h5 class="widget-title">联系方式</h5>
                    </div>
                    <div class="widget-body">
                        <div class="widget-main">
                            <div class="attribute-table">
                                <div class="attribute-table-row col-xs-12 col-sm-6">
                                    <div class="attribute-table-label">负责人</div>
                                    <div class="attribute-table-value">
                                        <span>
                                            ${room.supervisor}
                                        </span>
                                    </div>
                                </div>
                                <div class="attribute-table-row col-xs-12 col-sm-6">
                                    <div class="attribute-table-label">联系电话</div>
                                    <div class="attribute-table-value">
                                        <span>
                                            ${room.supervisorMobile}
                                        </span>
                                    </div>
                                </div>
                                <div class="attribute-table-row col-xs-12 col-sm-6">
                                    <div class="attribute-table-label">邮箱</div>
                                    <div class="attribute-table-value">
                                        <span>
                                            ${room.supervisorEmail}
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
                        <h5 class="widget-title">订单相关</h5>
                    </div>
                    <div class="widget-body">
                        <div class="widget-main">
                            <div class="attribute-table">
                                <div class="attribute-table-row col-xs-12 col-sm-6">
                                    <div class="attribute-table-label">确认方式</div>
                                    <div class="attribute-table-value">
                                        <span>
                                            ${room.cfm}
                                        </span>
                                    </div>
                                </div>
                                <div class="attribute-table-row col-xs-12 col-sm-6">
                                    <div class="attribute-table-label">是否支付</div>
                                    <div class="attribute-table-value">
                                        <span>
                                            ${room.payable?string("是","否")}
                                        </span>
                                    </div>
                                </div>
                                <div class="attribute-table-row col-xs-12 col-sm-6">
                                    <div class="attribute-table-label">退款限时</div>
                                    <div class="attribute-table-value">
                                        <span>
                                            ${room.rlt!}
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
                                        <span>
                                            ${room.freeService}
                                        </span>
                                    </div>
                                </div>
                                <div class="attribute-table-row col-xs-12 col-sm-12">
                                    <div class="attribute-table-label">收费</div>
                                    <div class="attribute-table-value">
                                        <#list room.supportList as support>
                                            <div>
                                                <div>${support.name}</div>
                                                <div><img src="${image + "/" + support.cover}"/></div>
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
                                        <span>
                                            ${room.cfm}
                                        </span>
                                    </div>
                                </div>
                                <div class="attribute-table-row col-xs-12 col-sm-6">
                                    <div class="attribute-table-label">是否支付</div>
                                    <div class="attribute-table-value">
                                        <span>
                                            ${room.payable?string("是","否")}
                                        </span>
                                    </div>
                                </div>
                                <div class="attribute-table-row col-xs-12 col-sm-6">
                                    <div class="attribute-table-label">退款限时</div>
                                    <div class="attribute-table-value">
                                        <span>
                                            ${room.rlt!}
                                        </span>
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
                                        return '<a class="btn btn-xs btn-warning rest"　href="/admin/rooms/${room.id}/closed/' + data + '" data-method="DELETE"><i class="ace-icon fa fa-trash bigger-120"></i></a>';
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
                                    <input type="hidden" name="staffId" value="${current_operator.getId()}">
                                    <input type="hidden" name="roomId" value="${room.id}">
                                    <div class="form-group row">
                                        <div class="col-sm-2 control-label no-padding-right">日期</div>
                                        <div class="col-sm-10">
                                            <div class="input-icon input-icon-right col-xs-10 col-sm-9"
                                                 style="padding-left: 0 !important;padding-right: 0 !important; ">
                                                <input type="text" id="dateRange" style="width: 100%"
                                                       autocomplete="off">
                                                <i class="ace-icon fa fa-calendar blue"></i>
                                            </div>
                                            <input type="hidden" id="startDate" name="startDate">
                                            <input type="hidden" id="endDate" name="endDate">
                                            <script type="text/javascript">
                                                $(function () {
                                                    $("#dateRange").daterangepicker({
                                                        autoUpdateInput: false,
                                                        locale: {
                                                            format: "YYYY/MM/DD",
                                                            separator: " - ",
                                                            applyLabel: "确认",
                                                            cancelLabel: "清空",
                                                            fromLabel: "开始时间",
                                                            toLabel: "结束时间",
                                                            customRangeLabel: "自定义",
                                                            daysOfWeek: ["日", "一", "二", "三", "四", "五", "六"],
                                                            monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"]
                                                        }
                                                    }).on('cancel.daterangepicker', function (ev, picker) {
                                                        $("#dateRange").val("请选择日期");
                                                        $("#startDate").val("");
                                                        $("#endDate").val("");
                                                    }).on('apply.daterangepicker', function (ev, picker) {
                                                        $("#startDate").val(picker.startDate.format('YYYY-MM-DD'));
                                                        $("#endDate").val(picker.endDate.format('YYYY-MM-DD'));
                                                        $("#dateRange").val(picker.startDate.format('YYYY-MM-DD') + " 至 " + picker.endDate.format('YYYY-MM-DD'));
                                                    });
                                                })
                                            </script>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-sm-2 control-label no-padding-right">时间</div>
                                        <div class="col-sm-10">
                                            <div class="input-group">
                                                <input type="text" class="input-sm form-control" name="startTime"
                                                       id="startTime">
                                                <span class="input-group-addon">
                                                        <i class="fa fa-exchange"></i>
                                                    </span>
                                                <input type="text" class="input-sm form-control" name="endTime"
                                                       id="endTime">
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
</div>
</@layout.myLayout>
<h4 class="page-title">
    <span><i class="fa fa-filter"></i> 筛选条件</span>
</h4>
<div class="col-xs-12 col-sm-12 padding-6 form-horizontal">
    <div class="col-xs-12 col-sm-12 form-group">
        <div class="col-xs-12 col-sm-6">
            <input id="keyword" class="form-control" placeholder="订单号"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-10 form-group">
        <div class="col-xs-12 col-sm-6">
            <label class="col-sm-3 control-label no-padding-right text-right">订单状态</label>
            <div class="col-sm-9">
                <select class="form-control" id="status">
                    <option disabled selected>---请选择---</option>
                    <option value="PAIDANDCONFIRM">已付款</option>
                    <option value="COMPLETE">已完成</option>
                    <option value="REFUNDING">退款中</option>
                    <option value="REFUNDED">已退款</option>
                </select>
            </div>
        </div>
        <div class="col-xs-12 col-sm-6">
            <label class="col-sm-3 control-label no-padding-right text-right">订单日期</label>
            <div class="col-sm-9">
                <div class="col-xs-12 input-group input-daterange">
                    <input id="from" class="form-control" readonly="readonly">
                    <div class="input-group-addon">至</div>
                    <input id="to" class="form-control" readonly="readonly">
                </div>
                <script type="text/javascript">
                    $('.input-daterange input').each(function () {
                        $(this).datepicker({language: 'zh'});
                    });
                </script>
            </div>
        </div>
    </div>
    <div class="col-xs-12 col-sm-10 form-group">
        <div class="col-xs-12 col-sm-6">
            <label class="col-sm-3 control-label no-padding-right text-right">支付金额</label>
            <div class="col-sm-9">
                <div class="col-xs-12 input-group input-daterange">
                    <input id="payAmountFrm" class="form-control">
                    <div class="input-group-addon">至</div>
                    <input id="payAmountTo" class="form-control">
                </div>
            </div>
        </div>
        <div class="col-xs-12 col-sm-6">
            <label class="col-sm-3 control-label no-padding-right text-right">订单金额</label>
            <div class="col-sm-9">
                <div class="col-xs-12 input-group input-daterange">
                    <input id="totalFrm" class="form-control">
                    <div class="input-group-addon">至</div>
                    <input id="totalTo" class="form-control">
                </div>
            </div>
        </div>
    </div>
    <div class="row" style="padding-left:　30px">
        <div class="form-group row col-xs-12 col-sm-12">
            <label class="col-sm-2 no-padding-right text-right">&nbsp;</label>
            <div class="col-sm-10">
                <button class="btn btn-info" type="submit" id="query">
                    <i class="icon-ok bigger-110"></i>
                    查询
                </button>
            </div>
        </div>
    </div>
</div>
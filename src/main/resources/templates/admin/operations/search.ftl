<h4 class="page-title">
    <span><i class="fa fa-filter"></i> 筛选条件</span>
</h4>
<div class="col-xs-12 col-sm-12 padding-6 form-horizontal">
    <div class="col-xs-12 col-sm-9">
        <div class="form-group col-xs-12 col-sm-6">
            <label class="col-sm-2 control-label no-padding-right text-right">订单日期</label>
            <div class="col-sm-10">
                <div class="col-xs-12 input-group input-daterange">
                    <input id="from" class="form-control" readonly="readonly">
                    <div class="input-group-addon">至</div>
                    <input id="to" class="form-control" readonly="readonly">
                </div>
            </div>
            <script type="text/javascript">
                $('#from, #to').datepicker({
                    dateFormat: 'yy-mm-dd',
                    maxDate: '-1d'
                })
            </script>
        </div>
        <#if current_account.isAdmin() >
            <div class="form-group col-xs-12 col-sm-6">
                <label class="col-sm-2 control-label no-padding-right text-right">项目</label>
                <div class="col-sm-10">
                    <select class="chosen-select form-control" id="projectId">
                        <option disabled selected>---请选择---</option>
                            <#list current_project as pro>
                                <option value="${pro.id}">${pro.text}</option>
                            </#list>
                    </select>
                </div>
            </div>
        </#if>
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

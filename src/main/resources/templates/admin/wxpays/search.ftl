<div class="row">
    <div class="col-xs-12">
        <h4 class="header smaller lighter blue">
            <span><i class="fa fa-filter"></i>筛选条件</span>
        </h4>
        <div class="col-xs-12 col-sm-12">
            <div class="row">
                <div class="form-group row col-xs-12 col-sm-6">
                    <label class="col-sm-2 no-padding-right text-right">项目</label>
                    <div class="col-sm-10">
                        <select class="chosen-select form-control" id="proId" required>
                            <option disabled selected>---请选择---</option>
                            <#list current_project as pro>
                                <option value="${pro.id}">${pro.text}</option>
                            </#list>
                        </select>
                    </div>
                </div>
            </div>
            <div class="row" style="padding-left:　30px">
                <div class="form-group row col-xs-12 col-sm-6">
                    <label class="col-sm-2 no-padding-right text-right">&nbsp;</label>
                    <div class="col-sm-10">
                        <button class="btn btn-info" id="query">
                            <i class="icon-ok bigger-110"></i>
                            查询
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
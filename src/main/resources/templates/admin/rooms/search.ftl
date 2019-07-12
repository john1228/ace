<h4 class="page-title">
    <span><i class="fa fa-filter"></i> 筛选条件</span>
</h4>
<div class="col-xs-12 col-sm-12 padding-6 form-horizontal">
    <div class="col-xs-12 col-sm-9">
        <div class="form-group row col-xs-12 col-sm-6">
            <input id="name" class="form-control" placeholder="请输入场地名字"/>
        </div>
    </div>
    <#if current_account.isAdmin() >
        <div class="col-xs-12 col-sm-9">
            <div class="form-group col-xs-12 col-sm-6">
                <label class="col-sm-2 control-label no-padding-right text-right">项目</label>
                <div class="col-sm-10">
                    <select class="chosen-select form-control">
                        <option>---请选择---</option>
                        <#list current_project as pro>
                            <option value="${pro.id}">${pro.text}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="form-group col-xs-12 col-sm-6">
                <label class="col-sm-2 control-label no-padding-right text-right">组织</label>
                <div class="col-sm-10">
                    <select class="chosen-select form-control">
                        <option>---请选择---</option>
                    <#--<#list current_account.getStaffList() as staff>-->
                    <#--<option value="${staff.orgId}">${staff.orgName}</option>-->
                    <#--</#list>-->
                    </select>
                </div>
            </div>
        </div>
    </#if>
    <div class="col-xs-12 col-sm-9">
        <div class="form-group col-xs-12 col-sm-6">
            <label class="col-sm-2 control-label no-padding-right text-right">面积</label>
            <div class="col-sm-10">
                <div class="input-group">
                    <input type="text" class="input-sm form-control" id="layerAreaFrom">
                    <span class="input-group-addon">
                            <i class="fa fa-exchange"></i>
                        </span>
                    <input type="text" class="input-sm form-control" id="layerAreaTo">
                </div>
            </div>
        </div>
        <div class="form-group col-xs-12 col-sm-6">
            <label class="col-sm-2 control-label no-padding-right text-right">名额</label>
            <div class="col-sm-10">
                <div class="input-group">
                    <input type="text" class="input-sm form-control" id="quotaFrom">
                    <span class="input-group-addon">
                            <i class="fa fa-exchange"></i>
                        </span>
                    <input type="text" class="input-sm form-control" id="quotaTo">
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

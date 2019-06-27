<#import "/spring.ftl" as spring />
<script src="/assets/js/bootstrap/multiselect.min.js"></script>
<@spring.bind path="coupon"/>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<div class="row">
    <div class="col-xs-12 col-sm-12 widget-container-col">
        <div class="widget-box">
            <div class="widget-header widget-header-custom">
                <h5 class="widget-title">优惠券信息</h5>
            </div>
            <div class="widget-body">
                <div class="widget-main padding-8">
                    <div class="form-group row">
                        <label class="col-sm-2 control-label no-padding-right"><span
                                style="color: red">*</span>优惠券名</label>
                        <div class="col-sm-10">
                            <@spring.formInput "coupon.name","class='col-xs-10 col-sm-9' placeholder='请填写优惠券名字'"/>
                        </div>
                    </div>
                    <div class="form-group row" id="dateDiv">
                        <label class="col-sm-2 control-label no-padding-right"><span
                                style="color: red">*</span>有效期</label>
                        <div class="col-sm-10">
                            <div class="col-xs-12 col-sm-9 input-group input-daterange">
                                <@spring.formInput "coupon.startDate",'class="form-control"'/>
                                <div class="input-group-addon">至</div>
                                <@spring.formInput "coupon.endDate",'class="form-control"'/>
                            </div>
                        </div>
                        <script type="text/javascript">
                            $('.input-daterange input').each(function () {
                                $(this).datepicker({language: 'zh'});
                            });
                        </script>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 control-label no-padding-right"><span style="color: red">*</span>抵扣金额
                        </label>
                        <div class="col-sm-10">
                            <@spring.formInput "coupon.discount" "class='col-xs-10 col-sm-9' placeholder='请填写优惠券优惠金额'"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 control-label no-padding-right"><span style="color: red">*</span>抵扣条件
                        </label>
                        <div class="col-sm-10">
                            <@spring.formInput "coupon.min" "class='col-xs-10 col-sm-9' placeholder='请填写可使用优惠券的最小订单金额'"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 control-label no-padding-right"><span
                                style="color: red">*</span>数量</label>
                        <div class="col-sm-10">
                            <@spring.formInput "coupon.amount","class='col-xs-10 col-sm-9' placeholder='请输入优惠券数量'"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-xs-12 col-sm-12 widget-container-col">
        <div class="widget-box">
            <div class="widget-header widget-header-custom">
                <h5 class="widget-title">适用范围</h5>
            </div>
            <div class="widget-body">
                <div class="widget-main padding-8">
                    <div class="form-group row">
                        <label class="col-sm-2 control-label no-padding-right">使用周数</label>
                        <div class="col-sm-10">
                            <select id="limitWday" name="limitWday" class="multiselect" multiple>
                                <#list weeks as id,name>
                                    <option value="${id}" ${coupon.limitWday?seq_contains(name)?string('selected="selected"', '')}>${name}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 control-label no-padding-right">可使用场地</label>
                        <div class="col-sm-10">
                            <select id="limitRoom" name="limitRoom" class="multiselect" multiple>
                                <#list rooms as id,name>
                                    <option value="${id}" ${coupon.limitRoom?seq_contains(id)?string('selected="selected"', '')}>${name}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row clearfix form-actions">
    <div class="col-md-offset-3 col-md-9">
        <button class="btn btn-info" type="submit">
            <i class="icon-ok bigger-110"></i>
            提交
        </button>
        &nbsp; &nbsp; &nbsp;
        <button class="btn" type="reset" onclick="history.go(-1)">
            <i class="icon-undo bigger-110"></i>
            返回
        </button>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        $('.multiselect').multiselect({
            nonSelectedText: "请选择",
            nSelectedText: "已选择",
            allSelectedText: "全选",
            enableFiltering: true,
            enableHTML: true,
            buttonClass: 'btn btn-white btn-primary',
            templates: {
                button: '<button type="button" class="multiselect dropdown-toggle" data-toggle="dropdown"><span class="multiselect-selected-text"></span> &nbsp;<b class="fa fa-caret-down"></b></button>',
                ul: '<ul class="multiselect-container dropdown-menu"></ul>',
                filter: '<li class="multiselect-item filter"><div class="input-group"><span class="input-group-addon"><i class="fa fa-search"></i></span><input class="form-control multiselect-search" type="text"></div></li>',
                filterClearBtn: '<span class="input-group-btn"><button class="btn btn-default btn-white btn-grey multiselect-clear-filter" type="button"><i class="fa fa-times-circle red2"></i></button></span>',
                li: '<li><a tabindex="0"><label></label></a></li>',
                divider: '<li class="multiselect-item divider"></li>',
                liGroup: '<li class="multiselect-item multiselect-group"><label></label></li>'
            }
        });
    })
</script>

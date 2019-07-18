<h4 class="header smaller lighter blue">
    <span><i class="fa fa-filter"></i> 筛选条件</span>
</h4>
<div class="col-xs-12 col-sm-10 form-horizontal">
    <div class="col-xs-12 col-sm-9 form-group">
        <div class="col-xs-12 col-sm-8">
            <div class="col-sm-10">
                <input class="form-control" id="mobile" placeholder="订单号/手机号/姓名"/>
            </div>
        </div>
    </div>
    <#if current_account.isAdmin() >
        <div class="col-xs-12 col-sm-12 form-group">
            <div class="col-xs-12 col-sm-6">
                <label class="col-sm-2 control-label no-padding-right text-right">项目</label>
                <div class="col-sm-10">
                    <select class="chosen-select form-control" id="proId">
                        <option disabled selected>---请选择---</option>
                        <#list current_project as pro>
                            <option value="${pro.id}">${pro.text}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="col-xs-12 col-sm-6">
                <label class="col-sm-2 control-label no-padding-right text-right">组织</label>
                <div class="col-sm-10">
                    <select class="chosen-select form-control" id="orgId">
                        <option disabled selected>---请选择---</option>
                    </select>
                </div>
            </div>
            <script type="text/javascript">
                $(function () {
                    $("#proId").on("change", function () {
                        var orgs;
                        $.ajax({
                            type: "GET",
                            url: "/admin/options/orgs",
                            data: {link: $("#proId").val()}
                        }).done(function (data) {
                            orgs = eval(data);
                            $('#orgId').html("<option disabled selected>---请选择---</option>");
                            $(orgs).each(function () {
                                $('#orgId').append('<option value="' + this.id + '">' + this.text + '</option>');
                            });
                            $("#orgId").trigger("liszt:updated");
                        });
                    })
                })
            </script>
        </div>
    </#if>
    <div class="col-xs-12 col-sm-12 form-group">
        <div class="col-xs-12 col-sm-6">
            <label class="col-sm-2 control-label no-padding-right text-right">原价</label>
            <div class="col-sm-10">
                <input class="form-control col-xs-10 col-sm-9" id="total"/>
            </div>
        </div>
        <div class="col-xs-12 col-sm-6">
            <label class="col-sm-2 control-label no-padding-right text-right">支付金额</label>
            <div class="col-sm-10">
                <input class="form-control col-xs-10 col-sm-9" id="payAmount"/>
            </div>
        </div>
    </div>
    <div class="col-xs-12 col-sm-12 form-group">
        <div class="col-xs-12 col-sm-6">
            <label class="col-sm-2 control-label no-padding-right text-right">订单日期</label>
            <div class="col-sm-10">
                <div class="col-xs-12 input-group input-daterange">
                    <input id="from" class="form-control">
                    <div class="input-group-addon">至</div>
                    <input id="to" class="form-control">
                </div>
            </div>
            <script type="text/javascript">
                $('.input-daterange input').each(function () {
                    $(this).datepicker({language: 'zh'});
                });
            </script>
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
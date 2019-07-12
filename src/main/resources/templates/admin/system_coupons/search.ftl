<div class="row">
    <div class="col-xs-12">
        <h4 class="header smaller lighter blue">
            <span><i class="fa fa-filter"></i> 筛选条件</span>
        </h4>
        <div class="col-xs-12">
            <div class="row">
                <div class="form-group row col-xs-12 col-sm-6">
                    <label class="col-sm-2 no-padding-right text-right">名称</label>
                    <div class="col-sm-10">
                        <input id="name" class="form-control col-xs-10 col-sm-9"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group row  col-xs-12 col-sm-6">
                    <label class="col-sm-2 no-padding-right text-right">有效期</label>
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
                            查询
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
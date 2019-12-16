<div class="row">
    <div class="col-xs-12">
        <h4 class="header smaller lighter blue">
            <span><i class="fa fa-filter"></i> 筛选条件</span>
        </h4>
        <div class="col-xs-12 col-sm-6">
            <div class="row">
                <div class="form-group row col-xs-12 col-sm-12">
                    <label class="col-sm-2 no-padding-right text-right">名称</label>
                    <div class="col-sm-10">
                        <input id="name" class="form-control"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group row  col-xs-12 col-sm-12">
                    <label class="col-sm-2 no-padding-right text-right">开始时间</label>
                    <div class="col-sm-10">
                        <input class="form-control datepicker" id="date" readonly="readonly"/>
                    </div>
                    <script type="text/javascript">
                        $(".datepicker").datepicker();
                    </script>
                </div>
            </div>
            <div class="row" style="padding-left:　30px">
                <div class="form-group row col-xs-12 col-sm-12">
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
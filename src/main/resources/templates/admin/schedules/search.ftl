<link rel="stylesheet" href="/assets/css/bootstrap/bootstrap-select.css"/>
<script src="/assets/js/bootstrap/bootstrap-select.js"></script>
<script src="/assets/js/bootstrap/datepicker.min.js"></script>
<div class="row">
    <div class="col-xs-12">
        <h4 class="header smaller lighter blue">
            <span><i class="fa fa-filter"></i> 筛选条件</span>
        </h4>
        <div class="col-xs-12 col-sm-6 padding-6">
            <div class="row">
                <div class="form-group row col-xs-12 col-sm-12">
                    <label class="col-sm-2 no-padding-right text-right">会议室</label>
                    <div class="col-sm-10">
                        <select id="room" class="chosen-select form-control"
                                data-placeholder="请选择会议室">
                            <#list rooms as room>
                                <option value="${room.id}">${room.name}</option>
                            </#list>
                        </select>
                    </div>
                </div>
                <div class="form-group row col-xs-12 col-sm-12">
                    <label class="col-sm-2 no-padding-right text-right">查询日期</label>
                    <div class="col-sm-10">
                        <input name="name" id="date" class="form-control col-xs-10 col-sm-9" autocomplete="off"/>
                        <script>
                            $("#date").datepicker({format: 'yyyy-mm-dd'});
                        </script>
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
    </div>
</div>
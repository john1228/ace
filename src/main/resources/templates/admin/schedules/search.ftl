<h4 class="header smaller lighter blue">
    <span><i class="fa fa-filter"></i> 筛选条件</span>
</h4>
<div class="col-xs-12 col-sm-12 padding-6 form-horizontal">
    <div class="form-group row col-xs-12 col-sm-9">
        <label class="col-sm-2 no-padding-right text-right">会议室</label>
        <div class="col-sm-10">
            <select id="room" class="chosen-select form-control"
                    data-placeholder="请选择会议室">
                <option disabled selected>---请选择---</option>
                <#list rooms as room>
                    <option value="${room.id}">${room.name}</option>
                </#list>
            </select>
        </div>
    </div>
    <div class="form-group row col-xs-12 col-sm-9">
        <label class="col-sm-2 no-padding-right text-right">查询日期</label>
        <div class="col-sm-10">
            <input name="name" id="date" class="form-control" autocomplete="off" placeholder="查看日期一周的排期情况"/>
            <script>
                $("#date").datepicker({language: 'zh'});
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
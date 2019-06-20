<link href="/assets/css/bootstrap/fileinput.css" media="all" rel="stylesheet" type="text/css">
<script src="/assets/js/bootstrap/fileinput.js"></script>
<div class="row">
    <div class="col-xs-12 col-sm-12 widget-container-col">
        <div class="widget-box">
            <div class="widget-header widget-header-custom">
                <h5 class="widget-title">服务信息</h5>
            </div>
            <div class="widget-body">
                <div class="widget-main padding-8">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right"><span
                                style="color: red">*</span>名称</label>
                        <div class="col-sm-10">
                            <@spring.formInput "support.name" "class='col-xs-10 col-sm-5' placeholder='请填写服务名字' required"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">图片</label>
                        <div class="col-sm-10">
                            <div class="avatar">
                                <div class="file-loading">
                                    <input id="coverFile" name="coverFile" type="file">
                                    <script type="text/javascript">
                                        $("#coverFile").fileinput({
                                            language: 'zh',
                                            overwriteInitial: true,
                                            maxFileSize: 1500,
                                            showClose: false,
                                            showCaption: false,
                                            showBrowse: false,
                                            showRemove: false,
                                            showUpload: false,
                                            <#if support.cover??>
                                            browseOnZoneClick: true,
                                            allowedFileExtensions: ["jpg", "png"],
                                            initialPreview: ["${image + support.cover}"],
                                            initialPreviewAsData: true,
                                            initialPreviewFileType: 'image'
                                            <#else>
                                            browseOnZoneClick: true
                                            </#if>
                                        });
                                    </script>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right"><span
                                style="color: red">*</span>单位</label>
                        <div class="col-sm-10">
                            <@spring.formInput "support.unit","class='col-xs-10 col-sm-5' placeholder='请填写服务单位' required"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="clearfix form-actions row">
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
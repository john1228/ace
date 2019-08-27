<#import "../layout/application.ftl" as layout>
<#import "/spring.ftl" as spring />
<@layout.myLayout>
    <script src="/assets/js/jquery/dropzone.min.js"></script>
    <div class="breadcrumbs" id="breadcrumbs">
        <script type="text/javascript">
            try {
                ace.settings.check('breadcrumbs', 'fixed')
            } catch (e) {
            }
        </script>

        <ul class="breadcrumb">
            <li>
                <i class="icon-home home-icon"></i>
                <a href="/admin/">Home</a>
            </li>
            <li>
                <a href="/admin/supports">配置管理</a>
            </li>
            <li class="active">下单协议</li>
        </ul>
    </div>

    <div class="page-content">
        <div class="row">
            <div class="col-xs-12">
                <div class="page-title">
                    <h1>下单协议</h1>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <form action="/admin/protocol" role="form" class="form-horizontal" method="post">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <input type="hidden" name="_method" value="put">
                            <div class="form-group row">
                                <label class="col-sm-2 control-label no-padding-right"><span style="color: red">*</span>下单协议</label>
                                <div class="col-sm-10">
                                    <textarea class="form-control" name="protocol" rows="10" placeholder="下单协议"
                                              id="protocol">
                                        ${protocol!}
                                    </textarea>
                                    <link rel="stylesheet" href="/assets/css/froala_editor/froala_editor.css"/>
                                    <link rel="stylesheet" href="/assets/css/froala_editor/froala_style.css">
                                    <link rel="stylesheet" href="/assets/css/froala_editor/plugins/code_view.css">
                                    <link rel="stylesheet" href="/assets/css/froala_editor/plugins/colors.css">
                                    <link rel="stylesheet" href="/assets/css/froala_editor/plugins/emoticons.css">
                                    <link rel="stylesheet" href="/assets/css/froala_editor/plugins/image_manager.css">
                                    <link rel="stylesheet" href="/assets/css/froala_editor/plugins/image.css">
                                    <link rel="stylesheet" href="/assets/css/froala_editor/plugins/line_breaker.css">
                                    <link rel="stylesheet" href="/assets/css/froala_editor/plugins/table.css">
                                    <link rel="stylesheet" href="/assets/css/froala_editor/plugins/char_counter.css">
                                    <link rel="stylesheet" href="/assets/css/froala_editor/plugins/video.css">
                                    <link rel="stylesheet" href="/assets/css/froala_editor/plugins/fullscreen.css">
                                    <link rel="stylesheet" href="/assets/css/froala_editor/plugins/quick_insert.css">
                                    <link rel="stylesheet" href="/assets/css/froala_editor/plugins/file.css">
                                    <link rel="stylesheet" href="/assets/css/froala_editor/themes/gray.css">

                                    <script type="text/javascript"
                                            src="/assets/js/froala_editor/froala_editor.min.js"></script>
                                    <script type="text/javascript"
                                            src="/assets/js/froala_editor/plugins/align.min.js"></script>
                                    <script type="text/javascript"
                                            src="/assets/js/froala_editor/plugins/code_beautifier.min.js"></script>
                                    <script type="text/javascript"
                                            src="/assets/js/froala_editor/plugins/code_view.min.js"></script>
                                    <script type="text/javascript"
                                            src="/assets/js/froala_editor/plugins/colors.min.js"></script>
                                    <script type="text/javascript"
                                            src="/assets/js/froala_editor/plugins/draggable.min.js"></script>
                                    <script type="text/javascript"
                                            src="/assets/js/froala_editor/plugins/emoticons.min.js"></script>
                                    <script type="text/javascript"
                                            src="/assets/js/froala_editor/plugins/font_size.min.js"></script>
                                    <script type="text/javascript"
                                            src="/assets/js/froala_editor/plugins/font_family.min.js"></script>
                                    <script type="text/javascript"
                                            src="/assets/js/froala_editor/plugins/image.min.js"></script>
                                    <script type="text/javascript"
                                            src="/assets/js/froala_editor/plugins/file.min.js"></script>
                                    <script type="text/javascript"
                                            src="/assets/js/froala_editor/plugins/image_manager.min.js"></script>
                                    <script type="text/javascript"
                                            src="/assets/js/froala_editor/plugins/line_breaker.min.js"></script>
                                    <script type="text/javascript"
                                            src="/assets/js/froala_editor/plugins/link.min.js"></script>
                                    <script type="text/javascript"
                                            src="/assets/js/froala_editor/plugins/lists.min.js"></script>
                                    <script type="text/javascript"
                                            src="/assets/js/froala_editor/plugins/paragraph_format.min.js"></script>
                                    <script type="text/javascript"
                                            src="/assets/js/froala_editor/plugins/paragraph_style.min.js"></script>
                                    <script type="text/javascript"
                                            src="/assets/js/froala_editor/plugins/video.min.js"></script>
                                    <script type="text/javascript"
                                            src="/assets/js/froala_editor/plugins/table.min.js"></script>
                                    <script type="text/javascript"
                                            src="/assets/js/froala_editor/plugins/url.min.js"></script>
                                    <script type="text/javascript"
                                            src="/assets/js/froala_editor/plugins/entities.min.js"></script>
                                    <script type="text/javascript"
                                            src="/assets/js/froala_editor/plugins/char_counter.min.js"></script>
                                    <script type="text/javascript"
                                            src="/assets/js/froala_editor/plugins/inline_style.min.js"></script>
                                    <script type="text/javascript"
                                            src="/assets/js/froala_editor/plugins/quick_insert.min.js"></script>
                                    <script type="text/javascript"
                                            src="/assets/js/froala_editor/plugins/save.min.js"></script>
                                    <script type="text/javascript"
                                            src="/assets/js/froala_editor/plugins/fullscreen.min.js"></script>
                                    <script type="text/javascript"
                                            src="/assets/js/froala_editor/plugins/quote.min.js"></script>
                                    <script type="text/javascript">
                                        new FroalaEditor("#protocol", {
                                            language: 'zh_cn',
                                            theme: 'gray',
                                            toolbarButtons: ['fullscreen', 'bold', 'italic', 'underline', 'strikeThrough', 'subscript', 'superscript', 'fontFamily', 'fontSize', '|', 'color', 'emoticons', 'inlineStyle', 'paragraphStyle', '|', 'paragraphFormat', 'align', 'formatOL', 'formatUL', 'outdent', 'indent', '-', 'insertLink', 'insertImage', 'insertTable', '|', 'quote', 'insertHR', 'undo', 'redo', 'clearFormatting', 'selectAll', 'html'],
                                            quickInsertButtons: ['image', 'table', 'ol', 'ul'],
                                            imageUploadURL: "/admin/images",
                                            imageUploadParam: "file",
                                            imageUploadMethod: "POST",
                                            imageAllowedTypes: ["jpeg", "jpg", "png"]
                                        })
                                    </script>
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
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@layout.myLayout>



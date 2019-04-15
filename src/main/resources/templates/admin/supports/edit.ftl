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
            <a href="/admin/supports/?parent=${parent}">服务管理</a>
        </li>
        <li class="active">服务修改</li>
    </ul>
</div>

<div class="page-content">
    <h4 class="header smaller lighter blue">
        <span>修改服务</span>
    </h4>
    <form action="/admin/supports/${support.id}?parent=${parent}" role="form" class="form-horizontal" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" name="_method" value="put">
        <input type="hidden" name="staffId" value="${current_operator.getId()}">
        <@spring.bind path="support"/>
        <div class="row">
            <div class="col-xs-12 col-sm-12 widget-container-col">
                <div class="widget-box">
                    <div class="widget-header widget-header-custom">
                        <h5 class="widget-title">服务信息</h5>
                    </div>
                    <div class="widget-body">
                        <div class="widget-main padding-8">
                            <div class="form-group">
                                <label class="col-sm-2 control-label no-padding-right">名称</label>
                                <div class="col-sm-10">
                                    <@spring.formInput "support.name" "class='col-xs-10 col-sm-5' placeholder='请填写服务名字'"/>
                                    <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                        <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                    </span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label no-padding-right">单位</label>
                                <div class="col-sm-10">
                                    <@spring.formInput "support.unit","class='col-xs-10 col-sm-5' placeholder='请填写服务单位'"/>
                                    <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                        <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                    </span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label no-padding-right">图片</label>
                                <div class="col-sm-10">
                                    <@spring.formHiddenInput "support.cover","value=''"/>
                                    <div class="dropzone single" id="cover"></div>
                                    <script type="text/javascript">
                                        $("div#cover").dropzone({
                                            url: '/file/upload',
                                            addRemoveLinks: true,
                                            dictRemoveLinks: "x",
                                            dictCancelUpload: "x",
                                            dictDefaultMessage: "<i class=\"glyphicon glyphicon-plus-sign theme-color\"/></i><span>点击添加图片</span>"
                                        });
                                        $("div#cover").find('div.dz-message').css('font-size', '14px');
                                        $("div#cover").find('div.dz-message').css('padding-top', '30px');
                                    </script>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="clearfix form-actions">
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
</@layout.myLayout>
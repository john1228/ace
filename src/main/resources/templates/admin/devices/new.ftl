<#import "../layout/application.ftl" as layout>
<#import "/spring.ftl" as spring />
<@layout.myLayout>

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
            <a href="/admin/rooms/0/devices/">设备管理</a>
        </li>
        <li class="active">设备新增</li>
    </ul>


    <div class="nav-search" id="nav-search">
        <form class="form-search">
            <span class="input-icon">
                <input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input"
                       autocomplete="off"/>
                <i class="icon-search nav-search-icon"></i>
            </span>
        </form>
    </div>
</div>

<div class="page-content">
    <div class="row">
        <div class="col-xs-12">
            <div class="page-header">
                <h1>设备新增</h1>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <form action="/admin/rooms/0/devices/" role="form" class="form-horizontal" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="hidden" name="accountId" value="${currentAdminUser.id}">
                        <@spring.bind path="device"/>
                        <fieldset class="fixed-border">
                            <legend class="fixed-border">设备信息</legend>
                            <div class="form-group">
                                <div class="col-sm-6 form-group">
                                    <label class="col-sm-3 control-label no-padding-right">设备名字</label>
                                    <div class="col-sm-9">
                                        <@spring.formInput "device.name" "class='col-xs-10 col-sm-5' placeholder='请填写设备名字'"/>
                                        <span style="color:red; height:25px;line-height:25px;overflow:hidden;"><b>&nbsp;*<@spring.showErrors "<br>"/></b></span>
                                    </div>
                                </div>
                                <div class="col-sm-6 form-group">
                                    <label class="col-sm-3 control-label no-padding-right">设备单价</label>
                                    <div class="col-sm-9">
                                        <@spring.formInput "device.price","class='col-xs-10 col-sm-5' placeholder='请填写设备单价'"/>
                                        <span style="color:red; height:25px;line-height:25px;overflow:hidden;"><b>&nbsp;*<@spring.showErrors "<br>"/></b></span>
                                    </div>
                                </div>
                            </div>
                            <div class="space-4"></div>
                            <div class="form-group">
                                <div class="col-sm-6 form-group">
                                    <label class="col-sm-3 control-label no-padding-right">设备单位</label>
                                    <div class="col-sm-9">
                                        <@spring.formInput "device.unit","class='col-xs-10 col-sm-5' placeholder='请填写设备单位'"/>
                                        <span style="color:red; height:25px;line-height:25px;overflow:hidden;"><b>&nbsp;*<@spring.showErrors "<br>"/></b></span>
                                    </div>
                                </div>
                                <div class="col-sm-6 form-group">
                                    <label class="col-sm-3 control-label no-padding-right">设备状态</label>
                                    <div class="col-sm-9">
                                        <@spring.formSingleSelect "device.status",statuses,"class='col-xs-10 col-sm-5' placeholder='请填写设备单价'"/>
                                        <span style="color:red; height:25px;line-height:25px;overflow:hidden;"><b>&nbsp;*<@spring.showErrors "<br>"/></b></span>
                                    </div>
                                </div>
                            </div>
                        </fieldset>
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
            </div>
        </div>
    </div>
</div>
</@layout.myLayout>

<#import "../../layout/application.ftl" as layout>
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
            <a href="/admin/user/list/1">用户管理</a>
        </li>
        <li class="active">用户新增</li>
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
                <h1>属性新增</h1>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <form action="/admin/sites/attributes" role="form" class="form-horizontal" method="post"
                          id="form_post">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 请输入属性名 </label>
                            <div class="col-sm-9">
                                <input type="text" id="form-field-1" placeholder="属性名" class="col-xs-10 col-sm-5"
                                       name="name"/>
                                <span style="color:red; height:25px;line-height:25px;overflow:hidden;"><b>&nbsp;*</b></span>
                            </div>
                        </div>
                        <div class="space-4"></div>


                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 请选择属性类型 </label>
                            <div class="col-sm-9">
                                <select name="type">
                                    <option selected>请选择类型</option>
                                    <option>选择</option>
                                    <option>输入</option>
                                </select>
                                <span style="color:red; height:25px;line-height:25px;overflow:hidden;"><b>&nbsp;*</b></span>
                            </div>
                        </div>
                        <div class="space-4"></div>


                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 请输入属性值 </label>
                            <div class="col-sm-9">
                                <input type="text" id="form-field-1" placeholder="属性值" class="col-xs-10 col-sm-5"
                                       name="value"/>
                                <span style="color:red; height:25px;line-height:25px;overflow:hidden;"><b>&nbsp;*</b></span>
                            </div>
                        </div>
                        <div class="space-4"></div>

                        <div class="clearfix form-actions">
                            <div class="col-md-offset-3 col-md-9">
                                <button class="btn btn-info" type="button" onclick="form_submit('form_post')">
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
            <!-- PAGE CONTENT ENDS -->
        </div>
    </div>
</div>
<script type="text/javascript" th:inline="javascript">
    /*<![CDATA[*/
    function form_submit(id) {
        bootbox.confirm("确认新增?", function (result) {
            var form = $("#" + id);
            $.ajax({
                url: form.attr('action'),
                type: "POST",
                data: form.serialize(),
                dataType: 'json',
                success: function (data) {
                    if (data.code == 0) {
                        ace_msg.success(data.msg);
                    } else {
                        ace_msg.danger(data.msg);
                    }
                }
            });
        });
    }

    /*]]>*/
</script>
</@layout.myLayout>

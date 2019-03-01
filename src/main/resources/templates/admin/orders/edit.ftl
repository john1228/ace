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
            <a href="/admin/sites/attributes/">属性管理</a>
        </li>
        <li class="active">属性修改</li>
    </ul><!-- .breadcrumb -->

    <div class="nav-search" id="nav-search">
        <form class="form-search">
				<span class="input-icon">
					<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input"
                           autocomplete="off"/>
					<i class="icon-search nav-search-icon"></i>
				</span>
        </form>
    </div><!-- #nav-search -->
</div>

<div class="page-content">
    <div class="row">
        <div class="col-xs-12">
            <!-- PAGE CONTENT BEGINS -->
            <div class="page-header">
                <h1>属性修改
                    <small>
                        <i class="icon-double-angle-right">
                            请编辑属性最新资料
                        </i>
                    </small>
                </h1>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <form action="/admin/sites/attributes/${attribute.id}/update" role="form" class="form-horizontal"
                          method="post" name="attribute">
                        <#--<input type="hidden" name="_method" value="put">-->
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <@spring.bind path="attribute"/>
                        <@spring.formHiddenInput "attribute.id"/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 请输入属性名 </label>
                            <div class="col-sm-9">
                                <@spring.bind "attribute.name" />
                                <@spring.formInput "attribute.name"/>
                                <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                    <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                </span>
                            </div>
                        </div>
                        <div class="space-4"></div>


                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 请选择属性类型 </label>
                            <div class="col-sm-9">
                                <@spring.formSingleSelect "attribute.type",['请选择类型','选择',"输入"]/>
                                <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                    <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                </span>
                            </div>
                        </div>
                        <div class="space-4"></div>


                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 请输入属性值 </label>
                            <div class="col-sm-9">
                                <@spring.formInput "attribute.value"/>
                                <span style="color:red; height:25px;line-height:25px;overflow:hidden;">
                                    <b>&nbsp;*<@spring.showErrors "<br>"/></b>
                                </span>
                            </div>
                        </div>
                        <div class="space-4"></div>


                        <div class="clearfix form-actions">
                            <div class="col-md-offset-3 col-md-9">
                                <button class="btn btn-info" type="submit">
                                    <i class="icon-ok bigger-110"></i>
                                    提交
                                </button>
                                &nbsp; &nbsp; &nbsp;
                                <button class="btn" type="reset" onclick="go_back()">
                                    <i class="icon-undo bigger-110"></i>
                                    返回
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <!-- PAGE CONTENT ENDS -->
        </div><!-- /.col -->
    </div><!-- /.row -->
</div><!-- /.page-content -->
</@layout.myLayout>
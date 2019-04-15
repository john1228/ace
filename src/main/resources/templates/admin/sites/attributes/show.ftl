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
            <a href="/admin/users/">用户管理</a>
        </li>
        <li class="active">用户查看</li>
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
                <h1>${attribute.name}
                    <small>
                        <i class="icon-double-angle-right">
                            详细资料
                        </i>
                    </small>
                </h1>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 属性名 </label>
                        <div class="col-sm-9" style="padding:5px;">${attribute.name}</div>
                    </div>&nbsp;
                    <div class="space-4"></div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 类型 </label>
                        <div class="col-sm-9" style="padding:5px;">${attribute.type}</div>
                    </div>&nbsp;
                    <div class="space-4"></div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 值 </label>
                        <div class="col-sm-9" style="padding:5px;">${attribute.value}</div>
                    </div>&nbsp;
                    <div class="space-4"></div>

                    <div class="clearfix form-actions">
                        <div class="col-md-offset-3 col-md-9">
                            <a class="btn btn-info" type="button" href="/admin/sites/attributes/${attribute.id}/edit">
                                <i class="icon-ok bigger-110"></i>
                                修改
                            </a>
                            &nbsp;&nbsp;&nbsp;
                            <button class="btn" type="reset" onclick="history.go(-1)">
                                <i class="icon-undo bigger-110"></i>
                                返回
                            </button>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
</@layout.myLayout>
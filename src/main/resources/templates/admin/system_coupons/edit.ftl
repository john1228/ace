<#import "../layout/application.ftl" as layout>
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
            <a href="/admin/coupons/?parent=${parent}">优惠券管理</a>
        </li>
        <li class="active">优惠券修改</li>
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
                <h4>${coupon.name}修改
                    <small>
                        <i class="icon-double-angle-right">
                            请编辑属性最新资料
                        </i>
                    </small>
                </h4>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <form action="/admin/coupons/${coupon.id}" role="form" class="form-horizontal"
                          method="post" name="coupon">
                        <input type="hidden" name="_method" value="put">
                        <#include "form.ftl"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</@layout.myLayout>
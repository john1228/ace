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
            <a href="/admin/coupons">优惠券管理</a>
        </li>
        <li class="active">优惠券修改</li>
    </ul>
</div>

<div class="page-content">
    <div class="row">
        <div class="col-xs-12">
            <div class="page-title">
                <h4>
                    优惠券修改
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
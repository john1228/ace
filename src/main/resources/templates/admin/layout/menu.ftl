<#assign requestUri = requestContext.requestUri>
<ul class="nav nav-list" id="nav_nav-list">
    <li>
        <form action="/admin/" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="hidden" name="redirectUri" value="${requestUri}">
            <select class="form-control" name="operator" onchange="submit();">
                <#list current_account.getStaffList() as staff>
                    <#if staff==current_operator>
                        <option value="${staff.id}" selected>${staff}</option>
                    <#else>
                        <option value="${staff.id}">${staff}</option>
                    </#if>
                </#list>
            </select>
        </form>
    </li>
    <li class="${(parent == "site")?string("active open","")}">
        <a href="#" class="dropdown-toggle">
            <i class="menu-icon fa fa-building"></i>
            <span class="menu-text"> 场地管理 </span>
            <b class="arrow fa fa-angle-down"></b>
        </a>
        <b class="arrow"></b>
        <ul class="submenu">
            <li class="${(requestUri?starts_with("/admin/rooms"))?string("active","")}">
                <a href="/admin/rooms?parent=site">
                    <i class="menu-icon fa fa-caret-right"></i>
                    <text>场地</text>
                </a>
                <b class="arrow"></b>
            </li>
            <li class="${(requestUri?starts_with("/admin/devices"))?string("active","")}">
                <a href="/admin/devices?parent=site">
                    <i class="menu-icon fa fa-caret-right"></i>
                    <text>设备</text>
                </a>
                <b class="arrow"></b>
            </li>
            <li class="${(requestUri?starts_with("/admin/services"))?string("active","")}">
                <a href="/admin/services?parent=site">
                    <i class="menu-icon fa fa-caret-right"></i>
                    <text>服务</text>
                </a>
                <b class="arrow"></b>
            </li>
            <li class="${(requestUri?starts_with("/admin/prices"))?string("active","")}">
                <a href="/admin/prices?parent=site">
                    <i class="menu-icon fa fa-caret-right"></i>
                    <text>价格体系</text>
                </a>
                <b class="arrow"></b>
            </li>
            <li>
                <a href="/admin/schedules?parent=site">
                    <i class="menu-icon fa fa-caret-right"></i>
                    <text>日程</text>
                </a>
                <b class="arrow"></b>
            </li>
        </ul>
    </li>
    <li class="${(parent == "order")?string("active open","")}">
        <a href="#" class="dropdown-toggle">
            <i class="menu-icon fa fa-list"></i>
            <span class="menu-text"> 订单管理 </span>
            <b class="arrow fa fa-angle-down"></b>
        </a>
        <b class="arrow"></b>
        <ul class="submenu">
            <li class="${(requestUri?starts_with("/admin/orders"))?string("active","")}">
                <a href="/admin/orders?parent=order">
                    <i class="menu-icon fa fa-caret-right"></i>
                    <text>订单</text>
                </a>
                <b class="arrow"></b>
            </li>
            <li class="${(requestUri?starts_with("/admin/invoices"))?string("active","")}">
                <a href="/admin/invoices?parent=order">
                    <i class="menu-icon fa fa-caret-right"></i>
                    <text>发票</text>
                </a>
                <b class="arrow"></b>
            </li>
            <li class="${(requestUri?starts_with("/admin/receipts"))?string("active","")}">
                <a href="/admin/receipts?parent=order">
                    <i class="menu-icon fa fa-caret-right"></i>
                    <text>支付流水</text>
                </a>
                <b class="arrow"></b>
            </li>
        </ul>
    </li>
    <li class="${(parent == "coupon")?string("active open","")}">
        <a href="#" class="dropdown-toggle">
            <i class="menu-icon fa fa-gift"></i>
            <span class="menu-text"> 优惠券 </span>
            <b class="arrow fa fa-angle-down"></b>
        </a>
        <b class="arrow"></b>
        <ul class="submenu">
            <li class="${(requestUri?starts_with("/admin/coupons"))?string("active","")}">
                <a href="/admin/coupons?parent=coupon">
                    <i class="menu-icon fa fa-caret-right"></i>
                    <text>列表</text>
                </a>
                <b class="arrow"></b>
            </li>
            <li class="${(requestUri?starts_with("/admin/member_coupons"))?string("active","")}">
                <a href="/admin/member_coupons?parent=coupon">
                    <i class="menu-icon fa fa-caret-right"></i>
                    <text>领取</text>
                </a>
                <b class="arrow"></b>
            </li>
        </ul>
    </li>
    <li class="${(requestUri?starts_with("/admin/settings"))?string("active open","")}">
        <a href="#" class="dropdown-toggle">
            <i class="menu-icon fa fa-cogs"></i>
            <span class="menu-text"> 配置管理 </span>
            <b class="arrow fa fa-angle-down"></b>
        </a>
        <b class="arrow"></b>
        <ul class="submenu">
            <li class="${(requestUri?starts_with("/admin/sites/attributes"))?string("active","")}">
                <a href="/admin/sites/attributes">
                    <i class="menu-icon fa fa-caret-right"></i>
                    <text>短信配置</text>
                </a>
                <b class="arrow"></b>
            </li>
            <li>
                <a href="#">
                    <i class="menu-icon fa fa-caret-right"></i>
                    <text>支付配置</text>
                </a>
                <b class="arrow"></b>
            </li>
        </ul>
    </li>
</ul>
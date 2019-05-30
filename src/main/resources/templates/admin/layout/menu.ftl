<#assign requestUri = requestContext.requestUri>
<ul class="nav nav-list" id="nav_nav-list">
    <li>
        <form action="/admin/" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="hidden" name="redirectUri" value="${requestUri}">
            <select class="form-control" name="operator" onchange="submit();">
                <#list current_account.getEmployee() as staff>
                    <#if staff == (current_operator)>
                        <option value="${staff.id}" selected>${staff}</option>
                    <#else>
                        <option value="${staff.id}">${staff}</option>
                    </#if>
                </#list>
            </select>
        </form>
    </li>
    <li class="${(menu == "room")?string("active open","")}">
        <a href="#" class="dropdown-toggle">
            <i class="menu-icon fa fa-building"></i>
            <span class="menu-text"> 场地管理 </span>
            <b class="arrow fa fa-angle-down"></b>
        </a>
        <b class="arrow"></b>
        <ul class="submenu">
            <li class="${(requestUri?starts_with("/admin/supports"))?string("active","")}">
                <a href="/admin/supports">
                    <i class="menu-icon fa fa-caret-right"></i>
                    <text>服务管理</text>
                </a>
                <b class="arrow"></b>
            </li>
            <li class="${(requestUri?starts_with("/admin/rooms"))?string("active","")}">
                <a href="/admin/rooms">
                    <i class="menu-icon fa fa-caret-right"></i>
                    <text>场地</text>
                </a>
                <b class="arrow"></b>
            </li>
            <li class="${(requestUri?starts_with("/admin/schedules"))?string("active","")}">
                <a href="/admin/schedules">
                    <i class="menu-icon fa fa-caret-right"></i>
                    <text>排期查询</text>
                </a>
                <b class="arrow"></b>
            </li>
            <li class="${(requestUri?starts_with("/admin/prices"))?string("active","")}">
                <a href="/admin/prices">
                    <i class="menu-icon fa fa-caret-right"></i>
                    <text>价格体系</text>
                </a>
                <b class="arrow"></b>
            </li>
            <li class="${(requestUri?starts_with("/admin/coupons"))?string("active","")}">
                <a href="/admin/coupons">
                    <i class="menu-icon fa fa-caret-right"></i>
                    <text>优惠券</text>
                </a>
                <b class="arrow"></b>
            </li>
        </ul>
    </li>
    <li class="${(menu == "order")?string("active open","")}">
        <a href="#" class="dropdown-toggle">
            <i class="menu-icon fa fa-list"></i>
            <span class="menu-text"> 订单管理 </span>
            <b class="arrow fa fa-angle-down"></b>
        </a>
        <b class="arrow"></b>
        <ul class="submenu">
            <li class="${(requestUri?starts_with("/admin/orders"))?string("active","")}">
                <a href="/admin/orders">
                    <i class="menu-icon fa fa-caret-right"></i>
                    <text>订单</text>
                </a>
                <b class="arrow"></b>
            </li>
            <li class="${(requestUri?starts_with("/admin/invoices"))?string("active","")}">
                <a href="/admin/invoices">
                    <i class="menu-icon fa fa-caret-right"></i>
                    <text>发票</text>
                </a>
                <b class="arrow"></b>
            </li>
            <li class="${(requestUri?starts_with("/admin/receipts"))?string("active","")}">
                <a href="/admin/receipts">
                    <i class="menu-icon fa fa-caret-right"></i>
                    <text>支付流水</text>
                </a>
                <b class="arrow"></b>
            </li>
        </ul>
    </li>
    <li class="${(menu == "setting")?string("active open","")}">
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
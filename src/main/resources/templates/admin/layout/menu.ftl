<ul class="nav nav-list" id="nav_nav-list">
    <li class="active">
        <a href="#">
            <i class="menu-icon fa fa-tachometer"></i>
            <span class="menu-text"> Dashboard </span>
        </a>
        <b class="arrow"></b>
    </li>
    <li class="open">
        <a href="#">
            <i class="menu-icon fa fa-tachometer"></i>
            <span class="menu-text"> 项目管理 </span>
        </a>
        <b class="arrow"></b>
    </li>
    <li class="${(rca.requestUri?starts_with("/admin/rooms"))?string("active open","")}">
        <a href="#" class="dropdown-toggle">
            <i class="menu-icon fa fa-tachometer"></i>
            <span class="menu-text"> 场地管理 </span>
            <b class="arrow fa fa-angle-down"></b>
        </a>
        <b class="arrow"></b>
        <ul class="submenu">
            <li class="${(rca.requestUri=="/admin/rooms")?string("active","")}">
                <a href="/admin/rooms">
                    <i class="menu-icon fa fa-caret-right"></i>
                    <text>场地</text>
                </a>
                <b class="arrow"></b>
            </li>
            <li class="${(rca.requestUri=="/admin/rooms/0/devices")?string("active","")}">
                <a href="/admin/rooms/0/devices">
                    <i class="menu-icon fa fa-caret-right"></i>
                    <text>设备</text>
                </a>
                <b class="arrow"></b>
            </li>
            <li class="${(rca.requestUri=="/admin/rooms/0/services")?string("active","")}">
                <a href="/admin/rooms/0/services">
                    <i class="menu-icon fa fa-caret-right"></i>
                    <text>服务</text>
                </a>
                <b class="arrow"></b>
            </li>
            <li>
                <a href="#">
                    <i class="menu-icon fa fa-caret-right"></i>
                    <text>日程</text>
                </a>
                <b class="arrow"></b>
            </li>
        </ul>
    </li>
    <li class="${(rca.requestUri?starts_with("/admin/orders"))?string("active open","")}">
        <a href="#" class="dropdown-toggle">
            <i class="menu-icon fa fa-tachometer"></i>
            <span class="menu-text"> 订单管理 </span>
            <b class="arrow fa fa-angle-down"></b>
        </a>
        <b class="arrow"></b>
        <ul class="submenu">
            <li class="${(rca.requestUri?starts_with("/admin/sites/attributes"))?string("active","")}">
                <a href="/admin/sites/attributes">
                    <i class="menu-icon fa fa-caret-right"></i>
                    <text>订单</text>
                </a>
                <b class="arrow"></b>
            </li>
            <li>
                <a href="#">
                    <i class="menu-icon fa fa-caret-right"></i>
                    <text>发票</text>
                </a>
                <b class="arrow"></b>
            </li>
            <li>
                <a href="#">
                    <i class="menu-icon fa fa-caret-right"></i>
                    <text>支付流水</text>
                </a>
                <b class="arrow"></b>
            </li>
        </ul>
    </li>
    <li class="${(rca.requestUri?starts_with("/admin/coupons"))?string("active open","")}">
        <a href="#" class="dropdown-toggle">
            <i class="menu-icon fa fa-tachometer"></i>
            <span class="menu-text"> 优惠券 </span>
            <b class="arrow fa fa-angle-down"></b>
        </a>
        <b class="arrow"></b>
        <ul class="submenu">
            <li class="${(rca.requestUri?starts_with("/admin/coupons/"))?string("active","")}">
                <a href="/admin/coupons/">
                    <i class="menu-icon fa fa-caret-right"></i>
                    <text>列表</text>
                </a>
                <b class="arrow"></b>
            </li>
            <li>
                <a href="/admin/coupons/0/member_coupons">
                    <i class="menu-icon fa fa-caret-right"></i>
                    <text>领取</text>
                </a>
                <b class="arrow"></b>
            </li>
        </ul>
    </li>
    <li class="${(rca.requestUri?starts_with("/admin/settings"))?string("active open","")}">
        <a href="#" class="dropdown-toggle">
            <i class="menu-icon fa fa-tachometer"></i>
            <span class="menu-text"> 配置管理 </span>
            <b class="arrow fa fa-angle-down"></b>
        </a>
        <b class="arrow"></b>
        <ul class="submenu">
            <li class="${(rca.requestUri?starts_with("/admin/sites/attributes"))?string("active","")}">
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
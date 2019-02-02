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
    <li>
        <a href="#" class="dropdown-toggle">
            <i class="menu-icon fa fa-tachometer"></i>
            <span class="menu-text"> 场地管理 </span>
            <b class="arrow fa fa-angle-down"></b>
        </a>
        <b class="arrow"></b>
        <ul class="submenu">
            <li>
                <a href="/admin/attributes?parentMenu=site">
                    <i class="menu-icon fa fa-caret-right"></i>
                    <text>属性管理</text>
                </a>
                <b class="arrow"></b>
            </li>
            <li>
                <a href="#">
                    <i class="menu-icon fa fa-caret-right"></i>
                    <text>数据管理</text>
                </a>
                <b class="arrow"></b>
            </li>
        </ul>
    </li>
    <#list menus as menu>
        <li class="open">
            <a href="#" class="dropdown-toggle">
                <i class="${menu.permission.className}"></i>
                <span class="menu-text">${menu.permission.name}</span>
                <b class="arrow fa fa-angle-down"></b>
            </a>
            <b class="arrow"></b>
            <ul class="submenu" style="${(menu.permission.id == Session.parentId)?string("display:block","")}">
                <#list menu.pers as per>
                    <li>
                        <a href="/${per.url}?parentId=${menu.permission.id}">
                            <i class="menu-icon fa fa-caret-right"></i>
                            <text>${per.name}</text>
                        </a>
                        <b class="arrow"></b>
                    </li>
                </#list>
            </ul>
        </li>
    </#list>
</ul>
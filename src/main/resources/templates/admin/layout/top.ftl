<div id="navbar" class="navbar navbar-default ace-save-state">
    <div class="navbar-container ace-save-state" id="navbar-container">
        <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
            <span class="sr-only">Toggle sidebar</span>

            <span class="icon-bar"></span>

            <span class="icon-bar"></span>

            <span class="icon-bar"></span>
        </button>

        <div class="navbar-header pull-left">
            <a href="#" class="navbar-brand">
                <small>
                    <i class="fa fa-leaf"></i>
                ${(current_operator.projectName)!"上海大之商"}
                </small>
            </a>
        </div>
        <div class="navbar-buttons navbar-header">
            <ul class="nav ace-nav">
                <li class="light-blue">
                    <a href="http://bpmp.baobanwang.com/p/index" class="btn btn-white">
                        <span class="glyphicon glyphicon-send"></span> 点击返回
                    </a>
                </li>
            </ul>
        </div>
        <div class="navbar-buttons navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">
                <li class="light-blue dropdown-modal">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <img class="nav-user-photo" src="/assets/images/avatars/user.jpg" alt=""/>
                        <span class="user-info">
                            <small>欢迎,</small>
                        ${current_account.accountName}
                        </span>
                        <i class="ace-icon fa fa-caret-down"></i>
                    </a>

                    <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li>
                            <a href="profile.html">
                                <i class="ace-icon fa fa-user"></i>
                                返回
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="profile.html">
                                <i class="ace-icon fa fa-user"></i>
                                资料
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="/admin/logout" data-method="DELETE">
                                <i class="ace-icon fa fa-power-off"></i>
                                退出
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Admin Application Login</title>
    <meta name="renderer" content="webkit"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <!-- basic styles -->
    <link href="/assets/css/bootstrap/min.css" rel="stylesheet"/>
    <link href="/assets/css/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet"/>

    <!--[if IE 7]>
    <link rel="stylesheet" href="/assets/css/font-awesome/4.5.0/css/font-awesome-ie7.min.css"/>
    <![endif]-->
    <!-- ace styles -->
    <link rel="stylesheet" href="/assets/css/ace/min.css"/>
    <link rel="stylesheet" href="/assets/css/ace/rtl.min.css"/>
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="/assets/css/ace/ie.min.css"/>
    <![endif]-->
    <!--[if lt IE 9]>
    <script src="/assets/js/html5shiv.js"></script>
    <script src="/assets/js/respond.min.js"></script>
    <![endif]-->
</head>
<body class="login-layout">
<div class="main-container">
    <div class="main-content">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <div class="login-container">
                    <div class="center">
                        <h1>
                            <i class="icon-leaf green"></i>
                            <span class="red">会议室</span>
                            <span class="white">管理平台</span>
                        </h1>
                        <h4 class="blue">
                            <span>${company}</span>
                            <br/></h4>
                    </div>

                    <div class="space-6"></div>

                    <div class="position-relative">
                        <div id="login-box" class="login-box visible widget-box no-border">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h4 class="header blue lighter bigger">
                                        <i class="icon-coffee green"></i>
                                    <#if  RequestParameters.error??>
                                        <span id="error"> 用户名密码错误! </span>
                                    </#if>
                                    </h4>
                                    <div class="space-6"></div>
                                    <form action="/admin/login" method="post" id="login_post">
                                        <fieldset>
                                            <label class="block clearfix">
													<span class="block input-icon input-icon-right">
														<input type="text" class="form-control" placeholder="UserName"
                                                               name="username"/>
														<i class="icon-user"></i>
													</span>
                                            </label>

                                            <label class="block clearfix">
													<span class="block input-icon input-icon-right">
														<input type="password" class="form-control"
                                                               placeholder="Password" name="password"/>
														<i class="icon-lock"></i>
													</span>
                                            </label>

                                            <div class="space"></div>

                                            <div class="clearfix">
                                                <label class="inline">
                                                    <input type="checkbox" class="ace"/>
                                                    <span class="lbl"> 记住我</span>
                                                </label>

                                                <button type="button" class="width-35 pull-right btn btn-sm btn-primary"
                                                        onclick="login()">
                                                    <i class="icon-key"></i>
                                                    Login
                                                </button>
                                            </div>

                                            <div class="space-4"></div>
                                        </fieldset>
                                    </form>
                                </div><!-- /widget-main -->

                                <div class="toolbar clearfix">
                                    <div>
                                        <a href="#" onclick="show_box('forgot-box'); return false;"
                                           class="forgot-password-link">
                                            <i class="icon-arrow-left"></i>
                                            忘记密码
                                        </a>
                                    </div>
                                    <div>
                                        <a href="#" class="user-signup-link">
                                            异常反馈
                                            <i class="icon-arrow-right"></i>
                                        </a>
                                    </div>
                                </div>
                            </div><!-- /widget-body -->
                        </div><!-- /login-box -->

                        <div id="forgot-box" class="forgot-box widget-box no-border">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h4 class="header red lighter bigger">
                                        <i class="icon-key"></i>
                                        Retrieve Password
                                    </h4>
                                    <div class="space-6"></div>
                                    <p>
                                        Enter your email and to receive instructions
                                    </p>
                                    <form>
                                        <fieldset>
                                            <label class="block clearfix">
													<span class="block input-icon input-icon-right">
														<input type="email" class="form-control" placeholder="Email"/>
														<i class="icon-envelope"></i>
													</span>
                                            </label>

                                            <div class="clearfix">
                                                <button type="button" class="width-35 pull-right btn btn-sm btn-danger">
                                                    <i class="icon-lightbulb"></i>
                                                    Send Me!
                                                </button>
                                            </div>
                                        </fieldset>
                                    </form>
                                </div><!-- /widget-main -->

                                <div class="toolbar center">
                                    <a href="#" onclick="show_box('login-box'); return false;"
                                       class="back-to-login-link">
                                        Back to login
                                        <i class="icon-arrow-right"></i>
                                    </a>
                                </div>
                            </div><!-- /widget-body -->
                        </div><!-- /forgot-box -->
                    </div><!-- /signup-box -->
                </div>
            </div><!-- /position-relative -->
        </div><!-- /.col -->
    </div><!-- /.row -->
</div><!-- /.main-container -->


<!-- basic scripts -->
<script src="/assets/js/jquery/2.1.4.min.js"></script>
<script src="/assets/js/jquery/mobile.custom.min.js"></script>
<!--[if !IE]> -->
<!--
<script type="text/javascript">
    window.jQuery
            || document
                    .write("<script th:src='{@/static/assets/js/jquery-2.0.3.min.js}'>"
                            + "<"+"/script>");
</script>
 -->
<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='https://code.jquery.com/jquery-1.10.2.js' integrity='sha256-it5nQKHTz+34HijZJQkpNBIHsjpV8b6QzMJs9tmOBSo=' crossorigin='anonymous'>" + "<" + "/script>");
</script>
<![endif]-->
<!-- inline scripts related to this page -->

<script type="text/javascript">
    /*<![CDATA[*/
    $(document).ready(function () {
        document.onkeydown = function (event) {
            var e = event || window.event
                    || arguments.callee.caller.arguments[0];
            if (e.keyCode == 13) {
                //enter键
                login();
            }
        };
        var loginName = getCookieVal("username");
        var password = getCookieVal("password");

        //document.getElementsByName("username")[0].value = loginName;
        //document.getElementsByName("password")[0].value = password;

        if (loginName != '') {
            $("input[type='checkbox']").attr("checked", "true");
        }
    });

    function show_box(id) {
        jQuery('.widget-box.visible').removeClass('visible');
        jQuery('#' + id).addClass('visible');
    }

    function login() {
        var loginName = document.getElementsByName("username")[0];
        var password = document.getElementsByName("password")[0];
        var error = document.getElementById("error");
        if (loginName.value == "") {
            error.innerHTML = "Please Input Your LoginName.";
            return;
        }
        if (password.value == "") {
            error.innerHTML = "Please Input Your password.";
            return;
        }

        var remember = $("input[type='checkbox']").is(":checked");
        /*
        $("#login_post").attr("action",
                $("#login_post").attr("action") + "?remember=" + remember);
        */
        var login = document.getElementById("login_post");
        login.submit();
    }

    function getCookieVal(cookieName) {
        var search = cookieName + "=";
        var returnvalue = "";
        if (document.cookie.length > 0) {
            offset = document.cookie.indexOf(search);
            if (offset != -1) {
                offset += search.length;
                end = document.cookie.indexOf(";", offset);
                if (end == -1)
                    end = document.cookie.length;
                returnvalue = unescape(document.cookie.substring(offset,
                        end));
            }
        }
        if (returnvalue == "/" / "") {
            returnvalue = "";
        }
        return returnvalue;
    }

    /*]]>*/
</script>

</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="shortcut icon" href="../../favicon.ico" type="image/x-icon">
    <title>Shiro权限管理系统</title>
    <link href="../../WEB-RES/plugins/bootstrap3/css/bootstrap.css" type="text/css" rel="stylesheet">
    <link href="../../WEB-RES/css/home.css" type="text/css" rel="stylesheet">
    <%--一些字体，比如左上角导航栏的折叠菜单按钮的背景--%>
    <link rel="stylesheet" href="../../WEB-RES/plugins/font-awesome/css/font-awesome.css">
    <%--<link rel="stylesheet" href="../../WEB-RES/plugins/adminlte/css/ionicons.css">--%>
    <link type="text/css" rel="stylesheet" href="/WEB-RES/plugins/adminlte/css/AdminLTE.css">
    <link type="text/css" rel="stylesheet" href="/WEB-RES/plugins/adminlte/css/skins/skin-blue.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <!-- Main Header ,这个class 必须有-->
    <header class="main-header">
        <!-- Logo -->
        <a href="#" class="logo">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini"><b>TAO</b></span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg"><b>SHIRO</b>权限管理</span>
        </a>
        <!-- Header Navbar -->
        <nav class="navbar navbar-static-top" role="navigation">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                <span class="sr-only">Toggle navigation</span>
            </a>
        </nav>
    </header>

    <!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <!-- Sidebar user panel (optional) -->
            <div class="user-panel">
                <div class="pull-left image">
                    <img src="/WEB-RES/plugins/adminlte/img/user2-160x160.jpg"
                         class="img-circle" alt="User Image">
                </div>
                <div class="pull-left info">
                    <p>Ternence Tao</p>
                    <!-- Status -->
                    <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                </div>
            </div>

            <!-- Sidebar Menu -->
            <ul class="sidebar-menu" data-widget="tree">
                <li class="header">HEADER</li>
                <li class="treeview active">
                    <a href="#">
                        <i class="fa fa-link"></i>
                        <span>系统管理</span>
                        <%--加上style="margin: 0"去除箭头被意外加上的margin -7px--%>
                        <span class="pull-right-container" style="margin: 0">
                            <i class="fa fa-angle-left pull-right"></i>
                        </span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="#">用户管理</a></li>
                        <li><a href="#">角色管理</a></li>
                        <li><a href="#">资源管理</a></li>
                        <li><a href="#">权限管理</a></li>
                    </ul>
                </li>
            </ul>
            <!-- /.sidebar-menu -->
        </section>
        <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Page Header
                <small>Optional description</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
                <li class="active">Here</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content container-fluid">

            <!--------------------------
              | Your Page Content Here |
              -------------------------->

        </section>
        <!-- /.content -->
    </div>
</div>

<script src="../../WEB-RES/js/jquery-1.9.1.js"></script>
<script src="../../WEB-RES/plugins/bootstrap3/js/bootstrap.js"></script>
<script src="../../WEB-RES/plugins/adminlte/js/adminlte.min.js"></script>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>

<!DOCTYPE html>
<html>
<head>
    <title>用户管理</title>
    <jsp:include page="../includes/header.jsp"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../includes/nav.jsp"/>
    <jsp:include page="../includes/menu.jsp"/>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                用户管理
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 用户管理</a></li>
                <li class="active">用户列表</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <!-- /.row -->
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${baseResult != null}">
                        <div class="alert alert-${baseResult.status == 200 ? "success" : "danger"} alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                ${baseResult.message}
                        </div>
                    </c:if>
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">用户列表</h3>

                            <div class="row" style="padding-left: 15px;padding-top: 10px;padding-bottom: 5px">
                                <a href="/user/form" type="button" class="btn btn-sm btn-default"><i class="fa fa-plus"></i> 新增</a>&nbsp;&nbsp;&nbsp;
                                <button type="button" class="btn btn-sm btn-default" onclick="App.deleteMulti('/user/delete')"><i class="fa fa-trash-o"></i> 删除</button>&nbsp;&nbsp;&nbsp;
                                <a href="#" type="button" class="btn btn-sm btn-default"><i class="fa fa-download"></i> 导入</a>&nbsp;&nbsp;&nbsp;
                                <a href="#" type="button" class="btn btn-sm btn-default"><i class="fa fa-upload"></i> 导出</a>&nbsp;&nbsp;&nbsp;
                            </div>

                            <div class="box-tools">
                               <form:form action="/user/search" method="post" modelAttribute="tbUsers">
                                   <div class="input-group input-group-sm" style="width: 150px;">
                                       <input type="text" name="keyword" class="form-control pull-right" placeholder="搜索">
                                       <div class="input-group-btn">
                                           <button type="submit" class="btn btn-default"><i class="fa fa-search"></i>
                                           </button>
                                       </div>
                                   </div>
                               </form:form>
                            </div>

                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive no-padding">
                            <table id="dt" class="table table-hover">
                                <thead>
                                    <tr>
                                        <th><input type="checkbox" class="minimal icheck_master" /></th>
                                        <th>ID</th>
                                        <th>用户名</th>
                                        <th>手机号</th>
                                        <th>邮箱</th>
                                        <th>更新时间</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${tbUsers}" var="tbuser">
                                    <tr>
                                        <td><input id="${tbuser.id}" type="checkbox" class="minimal " /></td>
                                        <td>${tbuser.id}</td>
                                        <td>${tbuser.username}</td>
                                        <td>${tbuser.phone}</td>
                                        <td>${tbuser.email}</td>
                                        <td><fmt:formatDate value="${tbuser.updated}" pattern="yyyy-MM-dd hh:mm:ss"/> </td>
                                        <td>
                                            <a type="button" class="btn  btn-default btn-xs">查看</a>&nbsp;&nbsp;&nbsp;
                                            <a type="button" class="btn  btn-primary btn-xs" href="/user/form?id=${tbuser.id}">编辑</a>&nbsp;&nbsp;&nbsp;
                                            <a type="button" class="btn  btn-danger btn-xs">删除</a>&nbsp;&nbsp;&nbsp;
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
            </div>
        </section>
    </div>

    <jsp:include page="../includes/copyright.jsp"/>
</div>

<jsp:include page="../includes/footer.jsp"/>
<!-- 自定义模态框 -->
<sys:modal />
<script>
    $(function () {
        $("#dt").DataTable();
    });
</script>
</body>

</html>
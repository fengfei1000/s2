<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="u" uri="/WEB-INF/ui.tld"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<title><u:insert name="title" /></title>
<u:style src="/css/s.css" />
<u:script src="/css/s.js" />
<link rel="stylesheet" media="screen"
	href="@{'/public/bootstrap/css/bootstrap.min.css'}">
<!-- <link rel="stylesheet" media="screen"
	href="@{'/public/bootstrap/css/bootstrap-responsive.min.css'}">
 -->
<script src="@{'/public/js/jquery-1.9.1.js'}" type="text/javascript"></script>
<script src="@{'/public/blueimp/md5.min.js'}" type="text/javascript"></script>
<script src="@{'/public/js/jquery.form.js'}" type="text/javascript"></script>
<script src="@{'/public/bootstrap/js/bootstrap.min.js'}"
	type="text/javascript"></script>
<script src="@{'/public/app/app.js'}" type="text/javascript"></script>
<link rel="stylesheet" media="screen" href="@{'/public/styles/app.css'}">
<!--[if lt IE 9]>
      <script src="@{'/public/js/html5shiv.js'}"></script>
<![endif]-->

</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner  navbar-height">
			<div class="container my_container">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="#">Spruce</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li class="active"><a href="/">Home</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">Photos <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="/last">Last</a></li>
								<li><a href="/popular">Popular</a></li>
								<li><a href="/top">Top</a></li>
								<li><a href="/random">Random</a></li>
								<li><a href="/catalog">Catalog</a></li>
							</ul></li>

						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">Shop<b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="/shop/best">Best</a></li>
								<li><a href="/shop/bestSellers">Best Sellers</a></li>
								<li><a href="/shop/random">Random</a></li>
								<li><a href="/shop/catalog">Catalog</a></li>
								<li><a href="/shop/new">New Show</a></li>
							</ul></li>


					</ul>
					<div class="nav">
						<form class="navbar-search span2">
							<input type="text" class="search-query input-medium"
								placeholder="Search">
						</form>
					</div>
					<%
					    if (session.getAttribute(Admin.SESSION_LOGIN_KEY) == null) {
					%>

					<!-- 				<div class="nav pull-right">
						<div class="btn-group">
							<a href="/signup" class="btn btn-success btn-small"><i class="icon-arrow-up icon-white"></i>&ensp;Signup</a>
						</div>
						&ensp;
					</div> -->
					<div class="nav pull-right">
						<div class="btn-group">
							<a class="btn btn-primary btn-small" href="/login"><i
								class="icon-user icon-white"></i>&ensp;Login</a>
						</div>
						&ensp;
					</div>

					<%
					    } else {
					%>
					<div class="nav pull-right">
						<div class="btn-group">
							<a class="btn btn-primary btn-small" href="/you"><i
								class="icon-user icon-white"></i>
								${session.getAttribute(Admin.SESSION_USER_NAME_KEY)}</a> <a
								class="btn btn-primary btn-small dropdown-toggle"
								data-toggle="dropdown" href="#"><span class="caret"></span></a>

							<ul class="dropdown-menu">
								<li><a href="/settings/profile"><i class="icon-pencil"></i>Setting</a></li>
								<li><a href="/account"><i class="icon-trash"></i>
										Account</a></li>
								<li><a href="/profile"><i class="icon-ban-circle"></i>Profile</a></li>
								<li class="divider"></li>
								<li><a href="/store"><i class="i"></i> Store</a></li>
								<li class="divider"></li>
								<li><a href="/logout"><i class="i"></i> Logout</a></li>
							</ul>
						</div>
					</div>
					<div class="nav pull-right">

						<div class="btn-group">
							<a class="btn btn-success btn-small" href="#"><i
								class=" icon-share icon-white"></i> Add</a> <a
								class="btn btn-success btn-small dropdown-toggle"
								data-toggle="dropdown" href="#"><span class="caret"></span></a>

							<ul class="dropdown-menu">
								<li><a href="/upload"><i class="icon-upload"></i>Upload</a></li>
								<li class="divider"></li>
								<li><a href="/manage"> <i class="icon-edit"></i> Manage
								</a></li>
							</ul>
						</div>
					</div>
					<%
					    }
					%>
				</div>





			</div>
			<!--/.nav-collapse -->
		</div>
	</div>


	<div class="container my_container">
		<div class="page-header">
			<h3>
				<u:insert name="header" />
			</h3>
		</div>
		<u:insert name="body" />


	</div>

	<footer class="footer">
		<div class="container">
			<p>&copy; Company 2012</p>
			<u:insert name="footer" />
		</div>
	</footer>

</body>
</html>
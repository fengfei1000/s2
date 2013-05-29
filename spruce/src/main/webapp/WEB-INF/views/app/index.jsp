<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>

<%@ taglib prefix="u" uri="/WEB-INF/ui.tld"%>
<meta charset="UTF-8">

</head>
<body>
	<u:body template="/layout/layout.jsp" action="#">
		<u:define name="title">Sprucy Home Page</u:define>
		<u:define name="header">Home</u:define>

		<u:define name="body">
			<div class="btn-group">
				<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
					Action <span class="caret"></span>
				</a>
			<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
				<li><a tabindex="-1" href="#">Action</a></li>
				<li><a tabindex="-1" href="#">Another action</a></li>
				<li><a tabindex="-1" href="#">Something else here</a></li>
				<li class="divider"></li>
				<li><a tabindex="-1" href="#">Separated link</a></li>
			</ul>
			</div>
		
			<div class="alert">
				<button type="button" class="close" data-dismiss="alert">×</button>
				<strong>Warning!</strong> Best check yo self, you're not looking too
				good.
			</div>
			<div class="row-fluid">
				<ul class="thumbnails">
					<li class="span3"><a href="#" class="thumbnail"> <img
							src="http://placehold.it/260x180" class="img-rounded" alt="">
					</a></li>
					<li class="span3"><a href="#" class="thumbnail"> <img
							src="http://placehold.it/260x180" class="img-rounded" alt="">
					</a></li>
					<li class="span3"><a href="#" class="thumbnail"> <img
							src="http://placehold.it/260x180" class="img-rounded" alt="">
					</a></li>
					<li class="span3"><a href="#" class="thumbnail"> <img
							src="http://placehold.it/260x180" class="img-rounded" alt="">
					</a></li>
					<li class="span3"><a href="#" class="thumbnail"> <img
							src="http://placehold.it/260x180" class="img-rounded" alt="">
					</a></li>
					<li class="span3"><a href="#" class="thumbnail"> <img
							src="http://placehold.it/260x180" class="img-rounded" alt="">
					</a></li>
					<li class="span3"><a href="#" class="thumbnail"> <img
							src="http://placehold.it/260x180" class="img-rounded" alt="">
					</a></li>
					<li class="span3"><a href="#" class="thumbnail"> <img
							src="http://placehold.it/260x180" class="img-rounded" alt="">
					</a></li>
				</ul>
			</div>
			<div class="row-fluid">
				<ul class="thumbnails">
					<li class="span3">
						<div class="thumbnail">
							<img class="img-rounded" src="/images/1.JPG" alt="">
							<div class="caption">
								<h3>Thumbnail label</h3>
								<p>Cras justo odio, dapibus</p>

							</div>
						</div>
					</li>
					<li class="span3">
						<div class="thumbnail">
							<img class="img-rounded" src="/images/2.JPG" alt="">
							<div class="caption">
								<h3>Thumbnail label</h3>
								<p>Cras justo odio, dapibus ac facilisis in,</p>

							</div>
						</div>
					</li>
					<li class="span3">
						<div class="thumbnail">
							<img class="img-rounded" src="/images/3.JPG" alt="">
							<div class="caption">
								<h3>Thumbnail label</h3>
								<p>Cras justo odio, dapibus ac facilisis in</p>

							</div>
						</div>
					</li>
					<li class="span3">
						<div class="thumbnail">
							<img class="img-rounded" src="/images/3.JPG" alt="">
							<div class="caption">
								<h3>Thumbnail label</h3>
								<p>Cras justo odio, dapibus ac facilisis in</p>

							</div>
						</div>
					</li>
				</ul>
			</div>

			<ul class="thumbnails">
				<li class="span4"><a href="#" class="thumbnail"> <img
						src="http://placehold.it/360x270" alt="">
				</a></li>
				<li class="span2"><a href="#" class="thumbnail"> <img
						src="http://placehold.it/160x120" alt="">
				</a></li>
				<li class="span2"><a href="#" class="thumbnail"> <img
						src="http://placehold.it/160x120" alt="">
				</a></li>
				<li class="span2"><a href="#" class="thumbnail"> <img
						src="http://placehold.it/160x120" alt="">
				</a></li>
				<li class="span2"><a href="#" class="thumbnail"> <img
						src="http://placehold.it/160x120" alt="">
				</a></li>
				<li class="span2"><a href="#" class="thumbnail"> <img
						src="http://placehold.it/160x120" alt="">
				</a></li>
				<li class="span2"><a href="#" class="thumbnail"> <img
						src="http://placehold.it/160x120" alt="">
				</a></li>
				<li class="span2"><a href="#" class="thumbnail"> <img
						src="http://placehold.it/160x120" alt="">
				</a></li>
				<li class="span2"><a href="#" class="thumbnail"> <img
						src="http://placehold.it/160x120" alt="">
				</a></li>
			</ul>
			<div class="pagination  pagination-centered">
				<ul>
					<li><a href="#">Prev</a></li>
					<li class="disabled"><a href="#">1</a></li>
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
					<li><a href="#">Next</a></li>
				</ul>
			</div>


		</u:define>

	</u:body>

</body>
</html>
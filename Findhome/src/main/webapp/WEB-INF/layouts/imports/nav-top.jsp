<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras"
	prefix="tilesx"%>
<html>
<head>
<body>
<c:url value="/logout" var="logoutUrl" />
	<nav class="navbar navbar-default navbar-static-top">
		<div class="container" >
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href='<c:url value="/"/>'>FindHome</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false"><i class="glyphicon glyphicon-home"></i>
							Объявления аренды<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="#">На долгий срок</a></li>
								<li><a href="#">Посуточно</a></li>
							</ul></li>
					
					<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false"><i class="glyphicon glyphicon-home"></i>
							Объявления съема<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="#">Сниму квартиру</a></li>
								<li><a href="#">Сниму комнату в квартире</a></li>
							</ul></li>

				</ul>
				
				<c:if test="${user != null}">
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false"><i class="glyphicon glyphicon-user"></i>
							<c:out value="${user.email}" /><span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="#">Мои объявления</a></li>
								<li><a href="#">Создать объявление</a></li>
								<li><a href='<c:url value="/profile/flat"/>'>Добавить квартиру</a></li>
								<li><a href='<c:url value="/profile"/>'>Профиль</a></li>
								<li role="separator" class="divider"></li>
								<li><a href="${logoutUrl}">Выход</a></li>
							</ul></li>
					</ul>
				</c:if>
				<c:if test="${user == null}">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="/FindHome/register">Register</a></li>
						<li>ИЛИ</li>
						<li><a href="/FindHome/login">Log-in</a></li>
					</ul>
				</c:if>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>
</body>
</html>

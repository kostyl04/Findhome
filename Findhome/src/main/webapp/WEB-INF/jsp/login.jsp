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
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="/FindHome/resources/css/registration.css" rel="stylesheet">
</head>
<body>
	<div class="container">




		<div class="panel-heading">
			<div class="panel-title text-center">
				<h1 class="title">Вход</h1>
				<hr />
			</div>
		</div>
		<div class="registration">
			<form:form method="post" modelAttribute="userLogin" action="/FindHome/login">
				<spring:bind path="email">
					<div
						class="form-group ${first!=null ? status.error ? 'has-error' : 'has-success' :''}">
						<label class="control-label">Введите e-mail</label>
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-envelope"></i>
							</span>
							<form:input path="email" name="email" type="text" class="form-control"
								placeholder="Email" />
						</div>
						<form:errors htmlEscape="false" path="email" class="control-label" />

					</div>
				</spring:bind>
				<spring:bind path="password">
					<div
						class="form-group ${first!=null ? status.error ? 'has-error' : 'has-success' :''}">
						<label class="control-label">Введите пароль</label>
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="glyphicon glyphicon-lock"></i>
							</span>
							<form:input path="password" name="password" type="text" class="form-control"
								placeholder="Пароль" />
						</div>
						<form:errors htmlEscape="false" path="password"
							class="control-label" />

					</div>
				</spring:bind>
				<div class="text-center">
				<div class="row">
						<input type="checkbox" name="rememberme" id="rememberme"  /> remember-me
					</div>
					<div class="row">
						<a href="#">Забыли свой пароль?</a>
					</div>
					<div class="row">
						У вас нет аккаунта? <a href="#">Регистрация</a>
					</div>
					<div class="row" style="width:70%;margin-left:15%">
						<input class="btn btn-primary btn-block" type="submit" value="Войти">
					</div>
				</div>
			</form:form>
		</div>

	</div>

	<!-- /container -->
</body>
</html>

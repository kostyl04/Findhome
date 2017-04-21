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




		<form:form action="/FindHome/login" method="post">
			<input type="text" class="form-control" id="email" name="email"
				placeholder="Enter Username" required>
			<input type="password" class="form-control" id="password"
				name="password" placeholder="Enter Password" required>
				
				<input type="checkbox" class="form-control" id="remember-me"
				name="remember-me" placeholder="Enter Password" required>
				<input type="submit" value="go">
		</form:form>
<h1><c:out value="${error}"/></h1>
	</div>

	<!-- /container -->
</body>
</html>

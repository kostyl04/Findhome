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
</head>
<body>
	<div class="container" style="min-width: 770px">
		<div class="text-center">
			<h1 class="title">Мой профиль</h1>
			<hr />
		</div>
		<div class="row text-center">

			<table class="table table-bordered ">

				<tr id="nameinfo">
					<td>Имя</td>
					<td>${user.name!=null?user.name:'Вы не добавили себе имя'}</td>
					<td><a href="#" class="btn btn-primary" id="changenamebtn">${user.name!=null?'Изменить имя':'Добавить имя'}</a></td>
				</tr>


				<tr id="changename" style="display: none">
					<form action='<c:url value="/profile/changename"/>'>
						<td>Имя</td>

						<td><input type="text" id="name" name="name"></td>
						<td><input type="submit" class="btn btn-success"
							id="changesubmit" value="Изменить"><a href="#"
							class="btn btn-danger" id="cancelchange">Отмена</a></td>
					</form>
				</tr>



			</table>
			<h2 class="title">Контакты</h2>
			<hr />
			<table class="table table-bordered table-striped">
				<c:forEach var="contact" items="${contacts}">
					<tr>
						<td>${contact.contactType}</td>
						<td>${contact.contactString}</td>
						<c:if test="${contact.contactString!=user.email}">
							<td><a class="btn btn-danger"
								href="<c:url value="/profile/contacts/delete/${contact.id}"/>">Удалить</a></td>
						</c:if>
					</tr>
				</c:forEach>
				<tr>
					<form action="<c:url value="/profile/contacts/add/${user.id}"/>">
						<td><input type="text" name="contactType" id="contactString"></td>
						<td><input type="text" name="contactString"
							id="contactString"></td>
						<td><input type="submit" class="btn btn-success"
							value="Добавить"></td>
					</form>
				</tr>

			</table>
			<h2 class="title">Мои квартиры</h2>
			<hr />
			<table class="table table-bordered table-striped">
				<c:forEach var="flat" items="${flats}">
					<tr>
						<td>${flat.adress}</td>
						<td><a class="btn btn-primary"
							href="<c:url value="/profile/flats/delete/${flat.id}"/>">Редактировать</a></td>
						<td><a class="btn btn-danger"
							href="<c:url value="/profile/flats/delete/${flat.id}"/>">Удалить</a></td>

					</tr>
					
				</c:forEach>
				<tr>
						<td></td>
						<td></td>
						<td><a class="btn btn-success" href='<c:url value="/profile/flat"/>'>Добавить новую квартиру</a></td>
					</tr>
				


			</table>
		</div>
	</div>
	<script src="<c:url value='/resources/js/profile.js'/>"></script>


</body>
</html>

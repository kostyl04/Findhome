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

<link href='<c:url value="/resources/css/flat.css"/>' rel="stylesheet">

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body ng-app="myApp" ng-controller="userCtrl">
	<div class="container">
		<div class="text-center row">
			<h1 class="title">Создание кваритры</h1>
		</div>
		<hr />
		<div class="row text-center">
			<div class="rooms col-xs-5">
				<h2 class="title text-center">
					Управление комнатами
					</h1>
					<hr />

					<div class="text-center">
						<span class="addroom btn btn-primary" ng-click="addRoom()">Добавить
							комнату</span> <span ng-click="chooseFile()"
							ng-class="activeID!=-1?'':'none'"
							class="addFotoButton btn btn-primary">Добавить фото</span>
					</div>
					<hr />
					<div class="roomsnav">
						<span class="btn smallerDivs" ng-repeat="x in rooms"
							ng-class="$index==activeID?'btn-primary':'btn-default'"
							ng-click="setActive($index)" id="{{x.name}}"> {{x.name}}
							<p style="display: inline-block" class="remove-room"
								ng-click="removeRoom($index)">X</p>
						</span>

					</div>
					<hr />
					<input type="file" id="inputIMG" ng-class="activeID!=-1?'':'none'"
						name="img" accept="image/*"
						onchange="angular.element(this).scope().addImage(this)"
						style="display: none;">
					<div class="image-nav">
						<input type="button" value="<" class="
							leftArrow" ng-click="leftArrowClick()"
							ng-show="activeID!=-1&&rooms[activeID].imgs.length>1" />
						<div class="content">

							<div ng-show="activeID!=-1&&rooms[activeID].imgs.length!=0">
								<img
									ng-src="{{'data:image/jpeg;base64,'+rooms[activeID].imgs[currentFotoIndex]}}"
									class="foto" ng-click="zoomIMG(this)" /> <a href=""
									class="del text-danger" ng-click="deleteFoto()">Удалить</a>
							</div>
							<div ng-if="rooms[activeID].imgs.length==0">
								<span class="no-imgs-img glyphicon glyphicon-alert"></span> <span
									class="no-imgs-text">Добавте фото комнаты </span>
							</div>

							<div ng-if="rooms.length==0">
								<span class="no-imgs-img glyphicon glyphicon-alert"></span> <span
									class="no-imgs-text" style="left: 50px;">Создайте
									комнату!</span>
							</div>
						</div>
						<input type="button" class="rightArrow" value=">"
							ng-show="activeID!=-1&&rooms[activeID].imgs.length>1"
							ng-click="rightArrowClick()">
					</div>
			</div>


			<div class="text-center col-xs-1"></div>
			<div class="text-center col-xs-5">
				<div class="col-xs-6 form-group">
					<label for="region">Выберите область:</label> <select
						class="form-control" id="region" name="region"
						ng-change="changeCitys(regionID)" ng-model="regionID"
						ng-options="region.regionID as region.name for region in regions">

					</select>
				</div>
				<div class="col-xs-6 form-group">
					<label for="city">Выберите город:</label> <select id="city"
						class="form-control">
						<option ng-repeat="x in citys" value="{{x.name}}">{{x.name}}</option>
					</select>
				</div>
				<div class="text-center col-xs-12">
					<div class="form-group">
						<label for="adress">Адрес:</label> <input type="text"
							class="form-control" id="adress" placeholder="Введите адрес">
					</div>
				</div>
				<div class="text-center col-xs-12">
					<div class="form-group">
						<label for="desc">Описание:</label>
						<textarea style="resize: none" class="form-control" id="desc"
							rows="5"></textarea>
					</div>
				</div>
				<div id="alertDiv" class="text-center col-xs-12 ">
					<div class="form-group ">
						<label for="comfort">Добавьте условия(близко от метро,
							мебель и тд.):</label>
						<div class="text-center  col-xs-8">
							<input type="text" class="form-control" id="comfort"
								placeholder="условие">
						</div>
						<div class="text-center col-xs-4">
							<span class="btn btn-primary " ng-click="addComfort()">Добавить</span>
						</div>
						<div id="errorDiv" style="display: none"
							ng-model="comfortErrorMessage" class="text-center col-xs-12 ">{{comfortErrorMessage}}</div>
					</div>
				</div>
				<div class="text-center col-xs-12 " style="margin-top: 10px;">
					<div class="badge"
						style="word-break: break-all; white-space: normal;"
						id="comfort-label" ng-repeat="comfort in comforts">
						{{comfort}} <span class="glyphicon glyphicon-remove removeComfort"
							ng-click="removeComfort(comfort)"></span>
					</div>
				</div>
				<div class="btn btn-success col-xs-12 " style="margin-top: 10px;" ng-click="saveFlat()">Сохранить
					квартиру</div>
			</div>

		</div>
	</div>
	<div id="myModal" class="modal">

		<!-- The Close Button -->
		<span class="close" onclick="">&times;</span>

		<!-- Modal Content (The Image) -->
		<img class="modal-content"
			ng-src="{{'data:image/jpeg;base64,'+rooms[activeID].imgs[currentFotoIndex]}}"
			id="img01">

		<!-- Modal Caption (Image Text) -->
		<div id="delete" class="text-center">
			<p ng-click="deleteFoto()">Удалить фото</p>
		</div>
	</div>





	<script>
		var path = "${pageContext.request.contextPath}";
	</script>
	<script src="/FindHome/resources/js/flat.js"></script>

</body>
</html>

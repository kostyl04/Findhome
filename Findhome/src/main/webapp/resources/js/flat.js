angular
		.module('myApp', [])
		.controller(
				'userCtrl',
				function($scope, $location, $anchorScroll, $http) {
					function Room(name) {
						this.name = name;
						this.imgs = [];
					}
					function Comfort(name) {
						this.name = name;
						
					}
					function Flat(adress, rooms, region, city, comforts, desc) {
						this.rooms = rooms;
						this.region = region;
						this.city = city;
						this.description = desc;
						this.adress = adress;
						this.comforts = comforts;
					}
					var rbdb = {};
					$scope.regions;
					$scope.citys;
					$scope.comforts = [];
					$scope.comfortErrorMessage = "";
					$http.get(path + "/getDB").then(function(response) {

						rbdb = response.data;
						$scope.regions = rbdb.regions;
						$scope.changeCitys($scope.regions[0].regionID);
						$scope.regionID = $scope.regions[0].regionID;
						for (x in $scope.regions)
							console.log($scope.regions[x].name);
					});

					$scope.fName = 'Роман';
					$scope.rooms = [];
					$scope.id = 1;
					$scope.activeID = -1;
					$scope.src = [];
					$scope.currentFotoIndex = 0;
					$scope.addRoom = function() {
						var room = new Room("Комната " + $scope.id);
						$scope.rooms.push(room);
						$scope.activeID = $scope.rooms.indexOf(room);
						$scope.id++;
						resetInputIMG();
						scroll(room.name);
					};
					function scroll(name) {
						$location.hash(name);
						$anchorScroll();
					}
					;
					$scope.removeRoom = function(index) {
						$scope.rooms.splice(index, 1);
						$scope.id--;
						var i = 1;
						for (x in $scope.rooms) {
							$scope.rooms[x].name = "Комната " + i;
							i++;
						}
						if ($scope.rooms.length == 0) {
							$scope.activeID = -1;
						} else {
							$scope.activeID = 0;
							scroll($scope.rooms[0].name);
						}
						resetInputIMG();

					};
					$scope.setActive = function(index) {
						resetInputIMG();
						$scope.activeID = index;

					};
					$scope.addImage = function(input) {
						var reader = new FileReader();
						reader.onload = function() {
							
							var arrayBuffer = this.result, 
							array = new Uint8Array(arrayBuffer), 
							binaryString = String.fromCharCode.apply(null, array);
							console.log(array);
							if ($scope.rooms[$scope.activeID].imgs
									.indexOf(btoa(binaryString)) == -1)
								$scope.rooms[$scope.activeID].imgs
										.push(btoa(binaryString));
							$scope.currentFotoIndex = $scope.rooms[$scope.activeID].imgs
									.indexOf(btoa(binaryString));
							$scope.$apply();

						}
						if (input.files[0] != "")
							reader.readAsArrayBuffer(input.files[0]);
						resetInputIMG();
					};
					$scope.leftArrowClick = function() {
						if ($scope.activeID != -1) {
							if ($scope.currentFotoIndex > 0)
								$scope.currentFotoIndex--;
							else
								$scope.currentFotoIndex = $scope.rooms[$scope.activeID].imgs.length - 1;
						}
					};
					$scope.rightArrowClick = function() {
						if ($scope.activeID != -1) {
							if ($scope.currentFotoIndex < $scope.rooms[$scope.activeID].imgs.length - 1)
								$scope.currentFotoIndex++;
							else
								$scope.currentFotoIndex = 0;
						}
					};
					function resetInputIMG() {
						document.getElementById("inputIMG").value = "";
						$scope.currentFotoIndex = 0;

					}
					;
					$scope.deleteIMG = function(index, e) {
						e.preventDefault();
						$scope.rooms[$scope.activeID].imgs.splice(index, 1);

					};
					$scope.chooseFile = function() {
						document.getElementById("inputIMG").click();
					};

					$scope.deleteFoto = function() {
						$scope.rooms[$scope.activeID].imgs.splice(
								$scope.currentFotoIndex, 1);
						var span = document.getElementsByClassName("close")[0];
						$scope.currentFotoIndex = 0;
						span.click();
					}
					$scope.zoomIMG = function(e) {
						var modal = document.getElementById('myModal');

						// Get the image and insert it inside the modal - use
						// its "alt" text as a caption
						// var img = document.getElementById('myImg');
						var modalImg = document.getElementById("img01");
						modal.style.display = "block";
						// modalImg.src =e.src;

						// Get the <span> element that closes the modal
						var span = document.getElementsByClassName("close")[0];

						// When the user clicks on <span> (x), close the modal
						span.onclick = function() {
							modal.style.display = "none";
						}
						window.onclick = function(event) {
							if (event.target == modal) {
								modal.style.display = "none";
							}
						}
					}
					$scope.changeCitys = function(regionID) {
						$scope.citys = new Array();
						for (x in rbdb.citys) {
							if (rbdb.citys[x].regionID == regionID)
								$scope.citys.push(rbdb.citys[x]);
						}
					};
					$scope.addComfort = function() {
						var comfort = $("#comfort").val();
						if (comfort == "") {
							$scope.comfortErrorMessage = "Условие не должно быть пустым!";
							$("#errorDiv").show();
							$("#alertDiv").addClass("alert alert-danger");
							return;
						}
						console.log($scope.comforts.indexOf(comfort));
						if ($scope.comforts.indexOf(comfort) != -1) {
							$scope.comfortErrorMessage = "Вы уже добавляли такое условие!";
							$("#errorDiv").show();
							$("#alertDiv").addClass("alert alert-danger");
							return;
						}
						$scope.comforts.push(comfort);
						$("#errorDiv").hide();
						$("#alertDiv").removeClass("alert alert-danger");
					}
					$scope.removeComfort = function(comfort) {
						console.log(comfort);
						$scope.comforts.splice(
								$scope.comforts.indexOf(comfort), 1);
					}
					$scope.saveFlat = function() {
						var desc = $("#desc").val();
						var region = $("#region option:selected").text();
						var city = $("#city").val();
						var adress = $("#adress").val();
					
						var flat = new Flat(adress, $scope.rooms, region, city,
								$scope.comforts, desc);
						$http({
					        url: path + "/saveflat",
					        method: "POST",
					        data: flat
					    })
					    .then(function(response) {
					    	console.log(response.data);
					           //window.location.href=path+"/profile";
					    }, 
					    function(response) { // optional
					    	console.log(response.data);
					    });

					}
				});
/*
 * <div class="content" ng-repeat="src in rooms[activeID].imgs"> <img
 * ng-src="{{'data:image/jpeg;base64,'+src}}" class="foto" id="{{$index}}" /><a
 * href="" ng-click="deleteIMG($index,$event)">Удалить</a> </div>
 */
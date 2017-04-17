angular
    .module('app')
  	.controller('homeCtrl', homeCtrl)
  	.controller('modalController', modalController)
    .controller('RegisterCtrl', RegisterCtrl)
;

  var variableSimi = [];

  function homeCtrl($scope, $uibModal, $log,$window, $http){
  	
      var modalPopup = function () {
        return $scope.modalInstance = $uibModal.open({
          templateUrl: 'View/formModal.html',
          size:'lg',
          scope: $scope
        });
      };

      // Modal window popup trigger 
      $scope.showModalForm = function () {
        modalPopup().result
          .then(function (data) {
            $scope.handleSuccess(data);
          })
          .then(null, function (reason) {
            $scope.handleDismiss(reason);
          });
      };

      // Affichage du Tooltip
      $scope.placement = {
        options: [
          'top',
          'top-left',
          'top-right',
          'bottom',
          'bottom-left',
          'bottom-right',
          'left',
          'left-top',
          'left-bottom',
          'right',
          'right-top',
          'right-bottom'
        ],
        selected: 'top'
      };
      // Close the modal if Yes button click
      $scope.yes = function () {

        var jsonResult = {}; 
        var jsonData0  = {};
        var jsonData1  = {};
        var jsonSame   = {};
        var trust      = {};
        var headTds = document.getElementById("table").tHead.getElementsByTagName("td");


        var names = {};
        names[0] = document.getElementById("name0").textContent;
        names[1] = document.getElementById("name1").textContent;

        var adresses = {};
        adresses[0] = document.getElementById("adresse0").textContent;
        adresses[1] = document.getElementById("adresse1").textContent;

        var telephones = {};
        telephones[0] = document.getElementById("telephone0").textContent;
        telephones[1] = document.getElementById("telephone1").textContent;

        var types = {};
        types[0] = document.getElementById("type0").textContent;
        types[1] = document.getElementById("type1").textContent;

        // Récupérer les noms de colonnes
        for (var i = 0; i < headTds.length; i++)
        {
          switch (i) {
            case 0:
              jsonResult[headTds[i].id] = names;
              break;
            case 1:
              jsonResult[headTds[i].id] = adresses;
              break;
            case 2:
              jsonResult[headTds[i].id] = telephones;
              break;
            case 3: 
              jsonResult[headTds[i].id] = types;
              break;
          }
        }

        // JSON SI CLE NUMERIQUE
        jsonResult["idPair"] = 1;
        jsonResult["Val"] = document.getElementById("exampleInputName2").value;
		
		$scope.jsonResultSend = jsonResult;

		$http({
			method: 'POST',
			url: 'http://127.0.0.1:8080/ProjetPPD/postPairServlet',
			headers: {'Content-Type': 'application/json'},
			data:  $scope.jsonResultSend 
		}).then(function (success){
			
	   },function (error){
			console.log('error : ' + error.status);
	   });
        console.log("POST done");
		
        /*return $window.alert(console.log(jsonResult));*/

      };

      // Dismiss the modal if No button click
      $scope.no = function () {
        	$scope.modalInstance.dismiss('No Button Clicked')
      };

      $scope.next = function ($scope) {
      	$window.alert('pas d\'autre valeur');
      };

      // Log Success message
      $scope.handleSuccess = function (data) {
        $log.info('Modal closed: ' + data);
      };

      // Log Dismiss message
      $scope.handleDismiss = function (reason) {
        $log.info('Modal dismissed: ' + reason);
      }

      $scope.dataRestaurants = [
  		{name: "Apple Pan",adresse:"The 10801 W. Pico Blvd. West LA",telephone:"310-475-3585",type:"American"},
  		{name: "Arnie Morton's of Chicago",adresse:"435 S. La Cienega Blvd. Los Angeles",telephone:"310-246-1501",type:" Steakhouses"},
        	{name: "Art's Deli",adresse: "12224 Ventura Blvd. Studio City",telephone: "818-762-1221",type :"Delis"},
        	{name:"Asahi Ramen",adresse:"2027 Sawtelle Blvd. West LA",telephone :"310-479-2231",type:"Noodle Shops"},
        	{name:"Baja Fresh",adresse:"3345 Kimber Dr. Westlake Village",telephone:"805-498-4049",type:"Mexican"},
        	{name:"Bel-Air Hotel",adresse:"701 Stone Canyon Rd. Bel Air",telephone:"310-472-1211",type:"Californian"},
        	{name:"Belvedere",adresse:"The 9882 Little Santa Monica Blvd. Beverly Hills",telephone:" 310-788-2306",type:" Pacific New Wave"},
        	{name:"Benita's Frites",adresse:" 1433 Third St. Promenade Santa Monica",telephone:" 310-458-2889",type:" Fast Food"},
        	{name: "Apple Pan",adresse:"The 10801 W. Pico Blvd. West LA",telephone:"310-475/3585",type:"American"},
  		{name: "ArnieMorton'sofChicago",adresse:"435 S. La Cienega Blvd. Los Angeles",telephone:"310-246-1501",type:" Steakhouses"},
        	{name: "Art's Deli",adresse: "12224 Ventura Blvd. Studio City",telephone: "818-762-1221",type :"Italien"},
        	{name:"Asahi Ramen",adresse:"2027 Sawtelle Blvd. West LA",telephone :"310/479/2231",type:"Noodle Shops"},
        	{name:"Baja Fresh",adresse:"3345 Kimber Dr. Westlake Village",telephone:"805-498-4049",type:"Baja"},
        	{name:"Bel-Air Hotel",adresse:"701 Stone Canyon Rd. Bel Air",telephone:"310-472-1211",type:"Californian"},
        	{name:"Belvedere",adresse:"The 9882 Little Santa Monica Blvd. Beverly Hills",telephone:" 310-788-2306",type:"Wave"},
        	{name:"Benita's Frites",adresse:" 1433 Third St. Promenade Santa Monica",telephone:" 310-458-2889",type:"Benitian"}
      ];

      $scope.go = function ( path ) {
        $location.path( path );
      };
  };

  function modalController($scope, $window){

  	$scope.CheckPair = function (item, value) {
  		var idItem = item.target.id;
  		var idNameWithoutNumber = idItem.substring(0, idItem.length-1);
          if ($scope.IsPair) {
          	angular.element(document.getElementById(idNameWithoutNumber+0)).addClass("pairActive");
  			angular.element(document.getElementById(idNameWithoutNumber+1)).addClass("pairActive");
          	variableSimi.push(value);
  		} else {
              $window.alert("CheckBox is not checked.");
          }
      };

      $scope.isActive = function(item) {
  	       return $scope.selected === item;
  	};
  };



  function RegisterCtrl($scope) {
    $scope.messages = 'This is Show orders screen';
  };

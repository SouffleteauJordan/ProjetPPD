angular
    .module('app')
    .controller('homeCtrl', homeCtrl)
    .controller('modalController', modalController)
    .controller('RegisterCtrl', RegisterCtrl)
    .controller('loginCtrl', loginCtrl);

  var variableSimi = [];

  // Controller de la page d'accueil des personnes connectés
  function homeCtrl($scope, $uibModal, $log,$window){
    
      // Ouverture de la fenetre modal 
      var modalPopup = function () {
        return $scope.modalInstance = $uibModal.open({
          templateUrl: 'View/formModal.html',
          size:'lg',
          scope: $scope
        });
      };

      // Fonction qui controle l'affichage du modal. Affichage dans la console les erreurs si il y a lieu
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

      // fonction qui ...
      $scope.go = function ( path ) {
        $location.path( path );
      };
  };

  // Controller de la fenetre modal
  function modalController($scope, $window, $http){
	  var vmBis = this;
	  vmBis.lastSliderUpdated = '';
	  
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

    // Fonction qui prepare en JSON en fonction des réponses de l'utilisateur
    $scope.yes = function () {

      var jsonResult = {}; 
      var jsonData0  = {};
      var jsonData1  = {};
      var jsonSame   = {};
      var trust      = {};
      var headTds = document.getElementById("table").tHead.getElementsByTagName("td");

      var simili = function(item){
        for (var i = 0; i < variableSimi.length; i++) {
          if(item == variableSimi[i]){
            return "true";
          }else{
            return "false";
          }
        }
      }

      var names = {};
      names[0] = document.getElementById("name0").textContent;
      names[1] = document.getElementById("name1").textContent;
      names[2] = simili(names[0]);

      var adresses = {};
      adresses[0] = document.getElementById("adresse0").textContent;
      adresses[1] = document.getElementById("adresse1").textContent;
      adresses[2] = simili(adresses[0]);

      var telephones = {};
      telephones[0] = document.getElementById("telephone0").textContent;
      telephones[1] = document.getElementById("telephone1").textContent;
      telephones[2] = simili(telephones[0]);

      var types = {};
      types[0] = document.getElementById("type0").textContent;
      types[1] = document.getElementById("type1").textContent;
      types[2] = simili(types[0]);

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
      };

      // JSON SI CLE NUMERIQUE
      jsonResult["idPair"] = 1;
      console.log('has changed with ' + $scope.sliders.sliderValue);
      jsonResult["Val"] = $scope.sliders.sliderValue;
		
      $scope.jsonResultSend = jsonResult;
      console.log(jsonResult);
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

      return $window.alert('donnée à envoyer : ' + variableSimi);
      /*return $window.alert(console.log(jsonResult));*/

    };

      // Fonction qui ...
    $scope.no = function () {
        $scope.modalInstance.dismiss('No Button Clicked')
    };
  
    // fonction qui ...
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
    };

    // fonction qui : Vérifie que le checkbox est activé, colore les champs selectionnés 
    $scope.CheckPair = function (item, value) {
      var idItem = item.target.id;
      var idNameWithoutNumber = idItem.substring(0, idItem.length-1);
      if ($scope.IsPair) {
        angular.element(document.getElementById(idNameWithoutNumber+0)).addClass("pairActive");
        angular.element(document.getElementById(idNameWithoutNumber+1)).addClass("pairActive");
        variableSimi.push(value);
      } 
      else {
        $window.alert("CheckBox is not checked.");
      }
    };

    // fonction qui ...
    $scope.isActive = function(item) {
      return $scope.selected === item;
    };

    // Si le checkbox est désactivé, les champs selectionnés sont initialisés
    $scope.isDontPair = function() {
      if(!$scope.IsPair){
        angular.element(document.querySelectorAll('#contentTable td')).removeClass("pairActive");
      }
    };
      
    $scope.formRange = {
      min: 0,
      max: 1,
      step: 0.1,
      precision: 2,
      orientation: 'horizontal',  // vertical
      handle: 'round', //'square', 'triangle' or 'custom'
      tooltip: 'show', //'hide','always'
      tooltipseparator: ':',
      tooltipsplit: false,
      enabled: true,
      naturalarrowkeys: false,
      range: false,
      ngDisabled: false,
      reversed: false
    };
  };

  // Controller de la page d'inscription
  function RegisterCtrl(UserService, $location, $rootScope, FlashService) {
      var vm = this;

      vm.register = register;

      function register() {
          vm.dataLoading = true;
          UserService.Create(vm.user)
              .then(function (response) {
                  if (response.success) {
                      FlashService.Success('Registration successful', true);
                      $location.path('/login');
                  } else {
                      FlashService.Error(response.message);
                      vm.dataLoading = false;
                  }
              });
      };
  };

  // Controller de la page de connexion
  function loginCtrl(){
    
  };

angular
    .module('app')
	.config(function($routeProvider, $locationProvider) {
		$locationProvider.hashPrefix('');
		$routeProvider
			.when('/home', {
				templateUrl: 'View/home.view.html',
				controller : 'homeCtrl'
			})
			.when('/', {
				templateUrl: 'View/login.view.html',
			})
			.when('/register', {
				templateUrl: 'View/register.view.html',
				controller : 'RegisterCtrl'
			})
			.otherwise({
				redirectTo: '/'
			});
	});
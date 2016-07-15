var example = angular.module("example", ['ui.router']);
 
example.config(function($stateProvider, $urlRouterProvider) {
    $stateProvider
        .state('login', {
            url: '/login',
            templateUrl: 'templates/login.html',
            controller: 'LoginController'
        })
    $urlRouterProvider.otherwise('/login');
});
 
example.controller("LoginController", function($scope) {
 
	$scope.login = function() {

       	 window.location.href = "http://localhost:8080/login?username=username&password=password"

	}
});
 
$scope.login = function() {

        window.location.href = "http://localhost:8080/login?username=username&password=password"

}

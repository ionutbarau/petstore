/**
 * Created by ionutbarau on 24/04/2017.
 */
'use strict';

// Declare app level module which depends on views, and components
angular.module('petstore', [
    'ngRoute',
    'ui.bootstrap',
    'petstore.login',
    'petstore.pets'
]).config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/login', {
     templateUrl: 'app/login/login.html'
     });
    $routeProvider.when('/view-pets', {
        templateUrl: 'app/pet/view-pets.html',
    });
    $routeProvider.when('/add-pet', {
        templateUrl: 'app/pet/add-pet.html'

    });
    $routeProvider.otherwise({redirectTo: '/login'});
}]).config(['$qProvider', function ($qProvider) {
    $qProvider.errorOnUnhandledRejections(false);
}]).run(function ($rootScope, $location, LoginService) {
    console.log(LoginService);
    console.log("route change start");
    $rootScope.$on("$routeChangeStart", function(event){
        if (!LoginService.isAutheticated){
            // Redirect to login
            console.log("user is not allowed");
            $location.path('/login');
        }else{
            if($location.path() == '/login'){
                $location.path('/view-pets');
            }
            console.log("user is allowed");
        }
    });
});
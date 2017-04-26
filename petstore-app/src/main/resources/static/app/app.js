/**
 * Created by ionutbarau on 24/04/2017.
 */
'use strict';

// Declare app level module which depends on views, and components
angular.module('petstore', [
    'ngRoute',
    'petstore.pets'
]).config(['$routeProvider', function ($routeProvider) {
    /*$routeProvider.when('/login', {
     templateUrl: 'app/login/login.html'
     });*/
    //$routeProvider.otherwise({redirectTo: '/login'});
    $routeProvider.when('/view-pets', {
        templateUrl: 'app/pet/view-pets.html'

    });
    $routeProvider.otherwise({redirectTo: '/view-pets'});
}]);
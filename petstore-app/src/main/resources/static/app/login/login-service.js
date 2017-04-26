/**
 * Created by ionutbarau on 24/04/2017.
 */
angular.module('petstore.login', [])
    .service('LoginService', ['$rootScope', '$http', '$log', function ($rootScope, $http, $log) {
        var self = this;
        self.login = function (username, password) {
            //create the header info
            var headers = credentials ? {
                authorization: "Basic "
                + btoa(credentials.username + ":" + credentials.password)
            } : {};

            $http.get('user', {headers: headers}).then(function (response) {
                if (response.data.name) {
                    $rootScope.authenticated = true;
                } else {
                    $rootScope.authenticated = false;
                }
            }, function () {
                $rootScope.authenticated = false;
            });
        }

    }]);

/**
 * Created by ionutbarau on 24/04/2017.
 */
angular.module('petstore.login', ['ngRoute'])
    .controller('LoginController', ['LoginService', '$location', '$log', function (loginService, $location, $log) {
        var self = this;
        self.username = '';
        self.password = '';
        self.loginMsg = '';
        self.login = function () {
            loginService.login(self.username, self.password, function () {
                if ($rootScope.authenticated) {
                    $location.path("/pet");
                    self.error = false;
                } else {
                    $location.path("/login");
                    self.error = true;
                }
            });
        };
    }]);

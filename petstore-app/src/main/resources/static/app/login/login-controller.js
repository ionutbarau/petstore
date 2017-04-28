/**
 * Created by ionutbarau on 24/04/2017.
 */
angular.module('petstore.login', [])
    .controller('LoginController', ['LoginService', '$location', '$log', '$scope', function (loginService, $location, $log, $scope) {
        var self = this;
        self.username = '';
        self.password = '';
        self.errorMsg;
        self.isAutheticated=loginService.isAutheticated;
        self.loginName = loginService.username;
        self.isManager = loginService.isManager;
        self.login = function () {
            loginService.login(self.username, self.password, function () {
                if (loginService.isAutheticated) {
                    $location.path("/view-pets");
                    self.error = false;
                } else {
                    $location.path("/login");
                    self.error = true;
                }
            });
        };

        self.logout = function () {
            loginService.logout();
        };

        /**
         * Login Error handler
         */
        $scope.$on('login-error', function (event) {
            self.errorMsg = loginService.errorMsg;
            self.isAutheticated = loginService.isAutheticated;
        })

        /**
         * Login ok handler
         */
        $scope.$on('login-ok', function (event) {
            self.errorMsg = undefined;
            self.isAutheticated = loginService.isAutheticated;
            self.loginName = loginService.username;
            self.isManager = loginService.isManager;
            $location.path('/view-pets');
        })

        /**
         * Logout Error handler
         */
        $scope.$on('logout-error', function (event) {
            self.errorMsg = loginService.errorMsg;
        })

        /**
         * Logout ok handler
         */
        $scope.$on('logout-ok', function (event) {
            self.errorMsg = undefined;
            self.isAutheticated = loginService.isAutheticated;
            self.loginName = loginService.username;
            self.isManager = loginService.isManager;
            $location.path('/login');
        })
    }]);

/**
 * Created by ionutbarau on 24/04/2017.
 */
angular.module('petstore.login')
    .service('LoginService', ['$rootScope', '$http', '$log','PetService', function ($rootScope, $http, $log, petService) {
        var self = this;
        self.errorMsg="";
        self.isAutheticated=false;
        self.username = '';
        self.isManager = false;
        self.login = function (username, password) {
            //create the header info
            var auth = btoa(username +':'+password);
            var headers = {"Authorization": "Basic " + auth};

            $http.get('/user/', {headers: headers}).then(function (response) {
                console.log(response);
                self.isAutheticated = true;
                self.errorMsg = undefined;
                self.username = response.data.principal.username;
                self.isManager = response.data.authorities[0].authority ==='ROLE_MANAGER';
                $rootScope.$broadcast("login-ok");
                petService.searchPets('');
            }, function (error) {
                self.isAutheticated = false;
                self.errorMsg = error.data.msg;
                console.log(error);
                $rootScope.$broadcast("login-error");
            });
        }

        self.logout = function(){
            $http.get('/logout').then(function (response) {
                console.log(response);
                self.isAutheticated = false;
                self.errorMsg = undefined;
                self.username = '';
                self.isManager = false;
                $rootScope.$broadcast("logout-ok");
            }, function (error) {
                $rootScope.$broadcast("logout-error");
            });
        }
    }]);

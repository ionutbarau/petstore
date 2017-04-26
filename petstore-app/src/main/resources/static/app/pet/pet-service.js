/**
 * Created by ionutbarau on 25/04/2017.
 */
angular.module('petstore.pets')
    .service('PetService', ['$http', '$log', '$rootScope', function ($http, $log, $rootScope) {
        var self = this;
        self.pets = [];
        self.errorMsg = '';
        self.addPet = function (pet) {
            //create the header info
            $http.post('/pet/', pet).then(function (response) {
                console.log(response);
            }, function (error) {
                console.log(error);
            });
        };


        self.searchPets = function (id) {
            //create the header info
            var auth = btoa("manager:password");
            var headers = {"Authorization": "Basic " + auth};
            return $http.get('/pet/' + id, {headers: headers}).then(function (response) {
                if (id === '') {
                    self.pets = response.data;
                } else {
                    self.pets = [];
                    self.pets.push(response.data);
                }
                $rootScope.$broadcast("updatePets");
            }, function (error) {
                console.log('error' + error);
                self.errorMsg = error.data.msg;
                $rootScope.$broadcast("error");
            });
        };


    }]);


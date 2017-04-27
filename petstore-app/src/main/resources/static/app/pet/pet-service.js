/**
 * Created by ionutbarau on 25/04/2017.
 */
angular.module('petstore.pets')
    .service('PetService', ['$http', '$log', '$rootScope', function ($http, $log, $rootScope) {
        var self = this;
        self.pets = [];
        self.errorMsg = '';


        /**
         * Add new pet.
         * @param pet
         */
        self.addPet = function (pet) {
            //create the header info
           /* var auth = btoa("manager:password");
            var headers = {"Authorization": "Basic " + auth};*/
            $http.post('/pet/', pet).then(function (response) {
                self.searchPets('');
            }, function (error) {
                console.log('error' + error);
                self.errorMsg = error.data.msg;
                $rootScope.$broadcast("error");
            });
        };


        /**
         * Search for pets by id. If id is empty search for all available pets.
         * @param id
         */
        self.searchPets = function (id) {
            //create the header info
            /*var auth = btoa("manager:password");
            var headers = {"Authorization": "Basic " + auth};*/
            $http.get('/pet/' + id).then(function (response) {
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

        /**
         * Removes the the pet with the specified id.
         * @param id
         */
        self.deletePet = function (id) {
            //create the header info
            /*var auth = btoa("manager:password");
            var headers = {"Authorization": "Basic " + auth};*/
            $http.delete('/pet/' + id).then(function (response) {
                self.searchPets('');
            }, function (error) {
                console.log('error' + error);
                self.errorMsg = error.data.msg;
                $rootScope.$broadcast("error");
            });
        };


    }]);


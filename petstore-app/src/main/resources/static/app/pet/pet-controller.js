/**
 * Created by ionutbarau on 25/04/2017.
 */
angular.module('petstore.pets', [])
    .controller('PetController', ['PetService', '$log', '$scope', function (petService, $log, $scope) {
        var self = this;
        self.pets = [];
        self.newPet = '';
        self.petId = '';
        self.errorMsg = '';

        self.addPet = function (name, category, photoUrls, tags, status) {
            var pet = {'name': name, 'category': category, 'photoUrls': photoUrls, 'tags': tags, 'status': status};
            petService.addPet(pet);
        };

        self.searchPets = function () {
            petService.searchPets(self.petId);
        };

        $scope.$on('error', function (event) {
            self.errorMsg = petService.errorMsg;
            self.pets = [];
        })

        $scope.$on('updatePets', function (event) {
            self.pets = petService.pets;
            self.errorMsg = '';
        })

        self.searchPets('');

    }]);

/**
 * Created by ionutbarau on 25/04/2017.
 */
angular.module('petstore.pets', [])
    .controller('PetController', ['PetService', '$log', '$scope', '$location', function (petService, $log, $scope, $location) {
        var self = this;
        self.pets = [];
        self.newPet = {
            'id': 0,
            "category": {
                'id': 0,
                'name': ''
            },
            'name': '',
            'photoUrls': [],
            'tags': [],
            'status': ''
        };
        self.newPetTags = '';
        self.newPetPhotoUrls = '';
        self.petId = '';
        self.errorMsg = '';

        /**
         * Add new pet
         */
        self.addPet = function () {
            var tags = self.newPetTags.split(',');
            for(i = 0; i < tags.length; i++){
                var tagName = tags[i].trim();
                self.newPet.tags.push({'id':0,'name': tagName});
            }
            var photoUrls = self.newPetPhotoUrls.split(',');
            for(i = 0; i < photoUrls.length; i++){
                var photo = photoUrls[i].trim();
                self.newPet.photoUrls.push(photo);
            }
            petService.addPet(self.newPet);
            self.newPet = {
                'id': 0,
                "category": {
                    'id': 0,
                    'name': ''
                },
                'name': '',
                'photoUrls': [],
                'tags': [],
                'status': ''
            };
            self.newPetTags = '';
            self.newPetPhotoUrls = '';
        };

        /**
         * Search for pets.
         */
        self.searchPets = function () {
            petService.searchPets(self.petId);

        };

        /**
         * Removes the the pet with the specified id.
         * @param id
         */
        self.deletePet = function (id) {
            petService.deletePet(id);

        };

        /**
         * Error handler
         */
        $scope.$on('error', function (event) {
            self.errorMsg = petService.errorMsg;
            self.pets = [];
        })

        /**
         * Update pets handler
         */
        $scope.$on('updatePets', function (event) {
            self.pets = petService.pets;
            self.errorMsg = '';
            self.petId = '';
            $location.path('/view-pets');
        })

        self.searchPets('');

    }]);

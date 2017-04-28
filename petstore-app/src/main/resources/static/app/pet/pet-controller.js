/**
 * Created by ionutbarau on 25/04/2017.
 */
angular.module('petstore.pets', [])
    .controller('PetController', ['PetService', '$log', '$scope', '$location','$uibModal', function (petService, $log, $scope, $location, $uibModal) {
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
        self.errorMsg;
        self.selectedPet={};
        self.modalInstance;
        self.showBuyMsg = false;
        self.buyCategory = '';

        /**
         * Opens the confirmation modal for pet deletion.
         * @param pet
         */
        self.openConfirmationModal = function(pet){
            self.selectedPet = pet;
             self.modalInstance = $uibModal.open({
                templateUrl:'confirmationModal.html',
                scope: $scope
            });

        };

        /**
         * Closes the confirmation modal for pet deletion.
         */
        self.cancelConfirmationModal = function(){
            self.modalInstance.dismiss();
            self.selectedPet = {};
        }

        /**
         * Opens the buy modal.
         */
        self.openBuyModal = function(category){
            if(category ==='BEAR' || category ==='CROCODILE'){
                self.showBuyMsg = true;
                self.buyCategory = category;
            }
            self.modalInstance = $uibModal.open({
                templateUrl:'buyModal.html',
                scope: $scope
            });

        };

        /**
         * Closes the buy modal.
         */
        self.cancelBuyModal = function(){
            self.modalInstance.dismiss();
            self.showBuyMsg = false;
            self.buyCategory = '';
        }

        /**
         * Opens the photo modal.
         */
        self.openPhotoModal = function(pet){
            self.selectedPet = pet;
            self.modalInstance = $uibModal.open({
                templateUrl:'photoModal.html',
                scope: $scope
            });

        };

        /**
         * Closes the photo modal.
         */
        self.cancelPhotoModal = function(){
            self.modalInstance.dismiss();
            self.selectedPet = {};
        }


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
        self.deletePet = function () {
            petService.deletePet(self.selectedPet.id);
            self.modalInstance.dismiss();
            self.selectedPet = {};
        };

        /**
         * Error handler
         */
        $scope.$on('error', function (event) {
            self.errorMsg = petService.errorMsg;
            self.petId = '';
            //self.pets = [];
        })

        /**
         * Update pets handler
         */
        $scope.$on('updatePets', function (event) {
            self.pets = petService.pets;
            self.errorMsg = undefined;
            self.petId = '';
            $location.path('/view-pets');
        })
        

    }]);

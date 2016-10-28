angular.module('userModule')
		.controller('UserEditController', UserEditController);

	function UserEditController(userService, $timeout, $injector, $q) {

		var vm = this;
		vm.userData = {};
        vm.userData.tipoDocumento = {};
        vm.userData.rol = {};
		vm.searchedUsers = [];

		vm.searchUsuariosByName = function() {
		    userService.getUsuariosByName(vm.userName)
                .then(function(res) {
                    vm.message = "";
                    vm.searchedUsers = [];
                    vm.userData = {};
                    vm.userSelected = false;
                    vm.searchMessage = "";
                    console.log(res.data);
                    vm.searchedUsers = res.data;
                    if(res.data.length==0){
                        vm.searchMessage = "No se encontró ningún usuario con ese nombre!"
                    }
                });
		}

		vm.selectUserForUpdate = function(user) {
		    vm.userData = user;
		    vm.userSelected = true;
		}

        vm.updateUser = function() {
            console.log(vm.userData);
            userService.update(vm.userData)
                .then(function(res) {
                    vm.message = res.data.message;
                });
        };

		vm.goBack = function() {
			$injector.get('$state').go('root.home');
		};
	}
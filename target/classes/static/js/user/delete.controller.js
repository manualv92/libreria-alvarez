angular.module('userModule')
		.controller('UserDeleteController', UserDeleteController);

	function UserDeleteController(userService, $timeout, $injector, $q) {

		var vm = this;
		vm.userData = {};
		vm.userData.tipoPersona = {};
        vm.userData.tipoDocumento = {};
		vm.searchedUsers = [];
		vm.userSelected = false;

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
                        vm.searchMessage = "No se encontró ningún usere con ese nombre!"
                    }
                });
		}

		vm.selectUserForDelete = function(user) {
		    vm.userData = user;
		    vm.userSelected = true;
		}

        vm.deleteUser = function() {
            if(vm.userSelected){
                vm.userData.habilitado = 0;
                console.log(vm.userData);
                userService.update(vm.userData)
                    .then(function(res) {
                            vm.message = res.data.message;
                            vm.userData = {};
                            vm.searchUsuariosByName()
                            vm.userSelected = false;
                    });
            }else {
                vm.message = "Debe seleccionar un usuario a borrar!"
            }
        };

		vm.goBack = function() {
			$injector.get('$state').go('root.home');
		};
	}
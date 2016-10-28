angular.module('userModule')
		.controller('UserCreateController', UserCreateController);

    /*@ngInject*/
	function UserCreateController(userService, $timeout, $injector, $q) {

		var vm = this;
		vm.userData = {};
		vm.userData.tipoDocumento = {};
        vm.userData.habilitado = 1;
        vm.userData.tipoDocumentoList = [];
        getTipoDocumento();
        getRoles();

        vm.createUser = function() {
            userService.create(vm.userData)
                .then(function(res) {
                    console.log(res.data.success);
                    vm.message = res.data.message;
                    vm.userData = {};
                    vm.userData.habilitado = 1;
                    getTipoDocumento();
                });
        };

		vm.goBack = function() {
			$injector.get('$state').go('root.home');
		};

		function getTipoDocumento(){
            userService.getTipoDocumento()
                .then(function(res) {
                console.log(res.data);
                    vm.userData.tipoDocumentoList = res.data;
                    console.log(vm.userData.tipoDocumentoList);
                });
        }

        function getRoles(){
            userService.getRoles()
                .then(function(res) {
                    console.log(res.data);
                    vm.userData.rolList = res.data;
                    console.log(vm.userData.rolList);
                });
        }
	}
angular.module('userModule')
		.controller('UserLoginController', UserLoginController);

    /*@ngInject*/
	function UserLoginController(userService, $timeout, $injector, $q, $window) {

		var vm = this;
		vm.userData = {};

        vm.logInUser = function() {
            userService.logInUser(vm.userData)
                .then(function(res) {
                    console.log(res.data.success);
                    if(res.data.success){
                        vm.message = "Numero de documento o password inválidos";
                    }else{
                        vm.user = res.data;
                        $window.localStorage.setItem('user', JSON.stringify(vm.user));
                        $injector.get('$state').go('root.home', {}, { reload: true });
                    }
                });
        };

		vm.goBack = function() {
			$injector.get('$state').go('root.home');
		};
	}
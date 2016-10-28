angular.module('app')
		.controller('NavbarController', NavbarController);

    /*@ngInject*/
	function NavbarController(sellService, $timeout, $injector, $q, $window) {

		var vm = this;

        getLocalUser();

		function getLocalUser(){

		    vm.userString = $window.localStorage.getItem('user');
		    if(vm.userString==null){
		        vm.userLogged = false;
		    }else{
		        vm.user = JSON.parse(vm.userString);
		        vm.userLogged = true;
		    }
		}

		vm.logOut = function(){
		    $window.localStorage.removeItem('user');
            vm.userLogged = false;
		    $injector.get('$state').go('root.home', {}, { reload: true });
		}

		vm.goBack = function() {
			$injector.get('$state').go('root.home');
		};
	}
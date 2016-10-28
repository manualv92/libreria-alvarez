angular.module('app')
		.controller('HomeController', HomeController);

    /*@ngInject*/
	function HomeController(userService, $timeout, $injector, $q, $window) {

		var vm = this;

        getLocalUser();

		function getLocalUser(){

		    vm.user = $window.localStorage.getItem('user');
		    if(vm.user==null){
		        $injector.get('$state').go('root.user.login');
		    }
		}

		vm.goBack = function() {
			$injector.get('$state').go('root.home');
		};
	}
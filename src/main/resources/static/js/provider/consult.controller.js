angular.module('providerModule')
		.controller('ProviderConsultController', ProviderConsultController);

	function ProviderConsultController(providerService, productService, $timeout, $injector, $q) {

		var vm = this;
		vm.providerData = {};
		vm.searchedProviders = [];

		vm.searchProveedoresByName = function() {
		    providerService.getProveedoresByName(vm.providerName)
                .then(function(res) {
                    vm.message = "";
                    vm.searchedProviders = [];
                    vm.providerData = {};
                    vm.providerSelected = false;
                    vm.searchMessage = "";
                    console.log(res.data);
                    vm.searchedProviders = res.data;
                    if(res.data.length==0){
                        vm.searchMessage = "No se encontró ningún proveedor con ese nombre!"
                    }
                });
		}

		vm.selectProviderForUpdate = function(provider) {
		    vm.providerData = provider;
		    vm.providerSelected = true;
		}

		vm.goBack = function() {
			$injector.get('$state').go('root.home');
		};
	}
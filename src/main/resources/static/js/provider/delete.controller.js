angular.module('providerModule')
		.controller('ProviderDeleteController', ProviderDeleteController);

	function ProviderDeleteController(providerService, productService, $timeout, $injector, $q) {

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

        vm.updateProvider = function() {
            vm.providerData.habilitado = 0;
            console.log(vm.providerData);
                providerService.update(vm.providerData)
                    .then(function(res) {
                        if(res.data.success){
                            vm.message = "Se borró el proveedor con éxito!";
                        }else{
                            vm.message = "Hubo un error en el borrado del proveedor, intente nuevamente!"
                        }
                    });
        };

		vm.goBack = function() {
			$injector.get('$state').go('root.home');
		};
	}
angular.module('providerModule')
		.controller('ProviderEditController', ProviderEditController);

	function ProviderEditController(providerService, productService, $timeout, $injector, $q) {

		var vm = this;
		vm.providerData = {};
		vm.searchedProviders = [];
		vm.cuitValido = true;

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

        vm.esCUITValida = function(inputValor) {
            inputString = inputValor.toString()
            if (inputString.length == 11) {
                var Caracters_1_2 = inputString.charAt(0) + inputString.charAt(1)
                if (Caracters_1_2 == "20" || Caracters_1_2 == "23" || Caracters_1_2 == "24" || Caracters_1_2 == "27" || Caracters_1_2 == "30" || Caracters_1_2 == "33" || Caracters_1_2 == "34") {
                    var Count = inputString.charAt(0) * 5 + inputString.charAt(1) * 4 + inputString.charAt(2) * 3 + inputString.charAt(3) * 2 + inputString.charAt(4) * 7 + inputString.charAt(5) * 6 + inputString.charAt(6) * 5 + inputString.charAt(7) * 4 + inputString.charAt(8) * 3 + inputString.charAt(9) * 2 + inputString.charAt(10) * 1
                    Division = Count / 11;
                    if (Division == Math.floor(Division)) {
                        vm.cuitValido = true;
                        console.log('CUIT TRUE');
                        return true
                    }
                }
            }
            vm.cuitValido = false;
            console.log('CUIT FALSE');
            return false
        }

        vm.updateProvider = function() {
            console.log(vm.providerData);
                if(vm.cuitValido){
                    providerService.update(vm.providerData)
                        .then(function(res) {
                            vm.message = res.data.message;
                        });
                }else{
                    vm.message = "Debe ingresar un CUIT válido";
                }
        };

		vm.goBack = function() {
			$injector.get('$state').go('root.home');
		};
	}
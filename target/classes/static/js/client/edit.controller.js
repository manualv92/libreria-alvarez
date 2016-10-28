angular.module('clientModule')
		.controller('ClientEditController', ClientEditController);

	function ClientEditController(clientService, $timeout, $injector, $q) {

		var vm = this;
		vm.clientData = {};
		vm.clientData.tipoPersona = {};
        vm.clientData.tipoDocumento = {};
		vm.searchedClients = [];

		vm.searchClientesByName = function() {
		    clientService.getClientesByName(vm.clientName)
                .then(function(res) {
                    vm.message = "";
                    vm.searchedClients = [];
                    vm.clientData = {};
                    vm.clientSelected = false;
                    vm.searchMessage = "";
                    console.log(res.data);
                    vm.searchedClients = res.data;
                    if(res.data.length==0){
                        vm.searchMessage = "No se encontró ningún cliente con ese nombre!"
                    }
                });
		}

		vm.selectClientForUpdate = function(client) {
		    vm.clientData = client;
		    vm.cuitValido = true;
		    vm.clientSelected = true;
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

        vm.updateClient = function() {
            console.log(vm.clientData);
            if(vm.cuitValido){
                clientService.update(vm.clientData)
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
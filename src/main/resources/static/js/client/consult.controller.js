angular.module('clientModule')
		.controller('ClientConsultController', ClientConsultController);

	function ClientConsultController(clientService, $timeout, $injector, $q) {

		var vm = this;
		vm.clientData = {};
		vm.clientData.tipoPersona = {};
        vm.clientData.tipoDocumento = {};
		vm.searchedClients = [];
		vm.clientSelected = false;

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

		vm.selectClientForDelete = function(client) {
		    vm.clientData = client;
		    vm.clientSelected = true;
		}

		vm.goBack = function() {
			$injector.get('$state').go('root.home');
		};
	}
angular.module('clientModule')
		.controller('ClientDeleteController', ClientDeleteController);

	function ClientDeleteController(clientService, $timeout, $injector, $q) {

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

        vm.deleteClient = function() {
            if(vm.clientSelected){
                vm.clientData.habilitado = 0;
                console.log(vm.clientData);
                clientService.update(vm.clientData)
                    .then(function(res) {
                        if(res.data.success){
                            vm.message = "Se borró el cliente con éxito!";
                            vm.clientData = {};
                            vm.searchClientesByName()
                            vm.clientSelected = false;
                        }else{
                            vm.message = "Hubo un error en la edición del cliente, intente nuevamente!"
                        }
                    });
            }else {
                vm.message = "Debe seleccionar un cliente a borrar!"
            }
        };

		vm.goBack = function() {
			$injector.get('$state').go('root.home');
		};
	}
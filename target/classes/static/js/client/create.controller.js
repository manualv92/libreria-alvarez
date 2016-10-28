angular.module('clientModule')
		.controller('ClientCreateController', ClientCreateController);

    /*@ngInject*/
	function ClientCreateController(clientService, $timeout, $injector, $q) {

		var vm = this;
		vm.clientData = {};
		vm.clientData.tipoPersona = {};
		vm.clientData.tipoDocumento = {};
        vm.clientData.habilitado = 1;
        vm.clientData.tipoPersonaList = [];
        vm.clientData.tipoDocumentoList = [];
        getTipoPersonas();
        getTipoDocumento();



        vm.cleanCuitNumber = function() {
        console.log(vm.clientData.tipoPersona)
            if(vm.clientData.tipoPersona.id == 1){
                vm.clientData.nroCuit = null;
            }else{
                 vm.clientData.nroDocumento = null;
            }
        }

        vm.createClient = function() {
            if(vm.cuitValido){
                clientService.create(vm.clientData)
                    .then(function(res) {
                        console.log(res.data.success);
                        vm.message = res.data.message;
                        vm.clientData = {};
                        vm.clientData.habilitado = 1;
                        getTipoPersonas();
                        getTipoDocumento();
                    });
            }else{
                vm.message = "Debe ingresar un CUIT válido";
            }
        };

		vm.goBack = function() {
			$injector.get('$state').go('root.home');
		};

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

		function getTipoPersonas(){
		    clientService.getTipoPersonas()
                .then(function(res) {
                console.log(res.data);
                    vm.clientData.tipoPersonaList = res.data;
                    console.log(vm.clientData.tipoPersonaList);
                });
		}

		function getTipoDocumento(){
            clientService.getTipoDocumento()
                .then(function(res) {
                console.log(res.data);
                    vm.clientData.tipoDocumentoList = res.data;
                    console.log(vm.clientData.tipoDocumentoList);
                });
        }
	}
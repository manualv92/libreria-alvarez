angular.module('providerModule')
		.controller('ProviderCreateController', ProviderCreateController);

    /*@ngInject*/
	function ProviderCreateController(providerService, clientService, productService, $timeout, $injector, $q) {

		var vm = this;
		vm.providerData = {};
        vm.providerData.tipoPersona = {};
        vm.providerData.tipoDocumento = {};
		vm.providerData.habilitado = 1;
		vm.providerData.productos = [];
		vm.providerData.tipoPersonaList = [];
        vm.providerData.tipoDocumentoList = [];
        getTipoPersonas();
        getTipoDocumento();

        vm.createProvider = function() {
            if(vm.cuitValido){
                if(vm.providerData.tipoPersona.id == 2){
                    vm.providerData.tipoDocumento = null;
                }
                providerService.create(vm.providerData)
                    .then(function(res) {
                        vm.message = res.data.message;
                        vm.providerData = {};
                        vm.providerData.habilitado = 1;
                    });
            }else{
                vm.message = "Debe ingresar un CUIT válido";
            }
        };

        vm.cleanCuitNumber = function() {
            console.log(vm.providerData.tipoPersona)
            if(vm.providerData.tipoPersona.id == 1){
                //vm.providerData.nroCuit = null;
            }else{
                 vm.providerData.nroDocumento = null;
            }
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

        function getTipoPersonas(){
            clientService.getTipoPersonas()
                .then(function(res) {
                console.log(res.data);
                    vm.providerData.tipoPersonaList = res.data;
                    console.log(vm.providerData.tipoPersonaList);
                });
        }

        function getTipoDocumento(){
            clientService.getTipoDocumento()
                .then(function(res) {
                console.log(res.data);
                    vm.providerData.tipoDocumentoList = res.data;
                    console.log(vm.providerData.tipoDocumentoList);
                });
        }

		vm.goBack = function() {
			$injector.get('$state').go('root.home');
		};

	}
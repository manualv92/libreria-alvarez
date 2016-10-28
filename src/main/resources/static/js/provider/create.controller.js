angular.module('providerModule')
		.controller('ProviderCreateController', ProviderCreateController);

    /*@ngInject*/
	function ProviderCreateController(providerService, productService, $timeout, $injector, $q) {

		var vm = this;
		vm.providerData = {};
		vm.providerData.habilitado = 1;
		vm.providerData.productos = [];
        getProductos();

        vm.createProvider = function() {
            providerService.create(vm.providerData)
                .then(function(res) {
                    vm.message = res.data.message;
                    vm.providerData = {};
                    vm.providerData.habilitado = 1;
                    getProductos();
                });
        };

        function getProductos(){
            productService.getAll()
                .then(function(res) {
                console.log(res.data);
                    vm.productosAux = res.data;
                    console.log(vm.productosAux);
                });
        }

        vm.removeProduct = function(product){
            vm.providerData.productos.splice(vm.providerData.productos.indexOf(product),1);
            vm.productosAux.push(product);
        }

        vm.addProduct = function(productAux){
            vm.productosAux.splice(vm.productosAux.indexOf(productAux),1);
            vm.providerData.productos.push(productAux);
        }

		vm.goBack = function() {
			$injector.get('$state').go('root.home');
		};

	}
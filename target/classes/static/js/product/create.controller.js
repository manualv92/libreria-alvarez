angular.module('productModule')
		.controller('ProductCreateController', ProductCreateController);

    /*@ngInject*/
	function ProductCreateController(productService, $timeout, $injector, $q) {

		var vm = this;
		vm.productData = {};
		vm.productData.tipoProducto = {};
		vm.productData.habilitado = 1;
		vm.productData.tipoProductoList = [];
        getTipoProductos();

        vm.createProduct = function() {
            productService.create(vm.productData)
                .then(function(res) {
                    vm.message = res.data.message;
                    vm.productData = {};
                    vm.productData.habilitado = 1;
                    getTipoProductos();
                });
        };

        function getTipoProductos(){
            productService.getTipoProductos()
                .then(function(res) {
                console.log(res.data);
                    vm.productData.tipoProductoList = res.data;
                    console.log(vm.productData.tipoProductoList);
                });
        }

		vm.goBack = function() {
			$injector.get('$state').go('root.home');
		};

	}
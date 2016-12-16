angular.module('productModule')
		.controller('ProductConsultController', ProductConsultController);

	function ProductConsultController(productService, $timeout, $injector, $q) {

		var vm = this;
		vm.productData = {};
		vm.searchedProducts = [];
		vm.productSelected = false;

		vm.searchProductosByName = function() {
		    productService.getProductosByName(vm.productName)
                .then(function(res) {
                    vm.message = "";
                    vm.searchedProducts = [];
                    vm.productData = {};
                    vm.productSelected = false;
                    vm.searchMessage = "";
                    console.log(res.data);
                    vm.searchedProducts = res.data;
                    if(res.data.length==0){
                        vm.searchMessage = "No se encontró ningún producto con ese nombre!"
                    }
                });
		}

		vm.selectProductForDelete = function(product) {
		    vm.productData = product;
		    vm.productSelected = true;
		}

		vm.goBack = function() {
			$injector.get('$state').go('root.home');
		};
	}
angular.module('productModule')
		.controller('ProductDeleteController', ProductDeleteController);

	function ProductDeleteController(productService, $timeout, $injector, $q) {

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

        vm.deleteProduct = function() {
            if(vm.productSelected){
                vm.productData.habilitado = 0;
                console.log(vm.productData);
                productService.update(vm.productData)
                    .then(function(res) {
                        if(res.data.success){
                            vm.message = "Se borró el producto con éxito!";
                            vm.productData = {};
                            vm.searchedProducts = [];
                            vm.productSelected = false;
                        }else{
                            vm.message = "Hubo un error en el borrado del producto, intente nuevamente!"
                        }
                    });
            }
        };

		vm.goBack = function() {
			$injector.get('$state').go('root.home');
		};
	}
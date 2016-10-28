angular.module('productModule')
		.controller('ProductEditController', ProductEditController);

	function ProductEditController(productService, $timeout, $injector, $q) {

		var vm = this;
		vm.productData = {};
		vm.searchedProducts = [];

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

		vm.selectProductForUpdate = function(product) {
		    vm.productData = product;
		    vm.productSelected = true;
		}

        vm.updateProduct = function() {
            console.log(vm.productData);
                productService.update(vm.productData)
                    .then(function(res) {
                        if(res.data.success){
                            vm.message = "Se editó el producto con éxito!";
                        }else{
                            vm.message = "Hubo un error en la edición del producto, intente nuevamente!"
                        }
                    });
        };

		vm.goBack = function() {
			$injector.get('$state').go('root.home');
		};
	}
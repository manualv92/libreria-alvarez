angular.module('providerModule')
		.controller('ProviderEditController', ProviderEditController);

	function ProviderEditController(providerService, productService, $timeout, $injector, $q) {

		var vm = this;
		vm.providerData = {};
		vm.searchedProviders = [];
        getProductos();

		vm.searchProveedoresByName = function() {
		    providerService.getProveedoresByName(vm.providerName)
                .then(function(res) {
                    vm.message = "";
                    vm.searchedProviders = [];
                    vm.providerData = {};
                    vm.providerSelected = false;
                    vm.searchMessage = "";
                    console.log(res.data);
                    vm.searchedProviders = res.data;
                    if(res.data.length==0){
                        vm.searchMessage = "No se encontró ningún proveedor con ese nombre!"
                    }
                });
		}

		vm.selectProviderForUpdate = function(provider) {
		    vm.providerData = provider;
		    vm.providerSelected = true;
		    angular.forEach(vm.providerData.productos, function(producto, key) {
		        angular.forEach(vm.productosAux, function(productoAux, key){
		            console.log(producto);
		            console.log(productoAux);
		            if(producto.id==productoAux.id){
		                console.log("Son iguales");
		                vm.productosAux.splice(vm.productosAux.indexOf(productoAux),1);
		            }else{
		                console.log("No son iguales");
		            }
		        });
		    });
		}

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

        vm.updateProvider = function() {
            console.log(vm.providerData);
                providerService.update(vm.providerData)
                    .then(function(res) {
                        if(res.data.success){
                            vm.message = "Se editó el proveedor con éxito!";
                        }else{
                            vm.message = "Hubo un error en la edición del proveedor, intente nuevamente!"
                        }
                    });
        };

		vm.goBack = function() {
			$injector.get('$state').go('root.home');
		};
	}
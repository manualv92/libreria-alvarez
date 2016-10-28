angular.module('buyModule')
		.controller('BuyCreateController', BuyCreateController);

    /*@ngInject*/
	function BuyCreateController(buyService, providerService, productService, $timeout, $injector, $q) {

		var vm = this;
		vm.buyData = {};
		vm.buyData.productos = [];
		vm.buyData.proveedor = {};
		vm.textoBuscadorProveedor = "";
        getProductos();
        getProveedores();

        vm.math = window.Math;

        vm.createBuy = function() {
            console.log("Entro a createBuy");
            vm.message = "";
            if(isEmpty(vm.buyData.proveedor)){
                vm.message = "Debe ingresar al menos 1 proveedor";
            }else if(vm.buyData.productos.length==0){
                vm.message = "Debe ingresar al menos 1 producto";
            } else {
                var bandera = checkCantidades();
                if(!bandera){
                    vm.calcularTotal();
                    buyService.create(vm.buyData)
                        .then(function(res) {
                            vm.message = "Se creó la compra con éxito!";
                            vm.buyData = {};
                            vm.buyData.productos = [];
                            vm.buyData.proveedor = {};
                            getProductos();
                            getProveedores();
                        });
                }else{
                    vm.message = "Debe ingresar una cantidad valida de producto";
                }
            }
        };

        function getProductos(){
            productService.getAll()
                .then(function(res) {
                    console.log(res.data);
                    vm.productosAux = res.data;
                    console.log(vm.productosAux);
                });
        };

        function getProveedores(){
            providerService.getAll()
                .then(function(res) {
                    console.log(res.data);
                    vm.proveedoresAux = res.data;
                    console.log(vm.proveedoresAux);
                });
        };

        vm.calcularTotal = function(){
            vm.buyData.total = 0.00;
            angular.forEach(vm.buyData.productos, function(producto, key){
                if(producto.cantidad!=null){
                    vm.buyData.total = Math.round((vm.buyData.total + (producto.precio * producto.cantidad))*100)/100;
                }
            })
            console.log(vm.buyData.total);
        }

        vm.removeProduct = function(product){
            vm.buyData.productos.splice(vm.buyData.productos.indexOf(product),1);
            vm.productosAux.push(product);
        };

        vm.addProduct = function(productAux){
            vm.productosAux.splice(vm.productosAux.indexOf(productAux),1);
            productAux.cantidad = 1;
            vm.buyData.productos.push(productAux);
            vm.calcularTotal();
        };

        vm.removeProvider = function(){
            vm.message = "";
            //vm.buyData.proveedores.splice(vm.buyData.proveedores.indexOf(proveedor),1);
            vm.proveedoresAux.push(vm.buyData.proveedor);
            vm.buyData.proveedor = {};
        };

        vm.addProvider = function(providerAux){
            vm.message = "";
            if(isEmpty(vm.buyData.proveedor)){
                vm.proveedoresAux.splice(vm.proveedoresAux.indexOf(providerAux),1);
                //vm.buyData.proveedores.push(providerAux);
                vm.buyData.proveedor = providerAux;
            }else{
                vm.message = "Solo se puede ingresar 1 proveedor por compra";
            }
        };

        function checkCantidades() {
            var bandera = false;
            angular.forEach(vm.buyData.productos, function(producto, key){
                if(producto.cantidad == null){
                    bandera = true;
                }
            });
            return bandera;
        }

        function isEmpty(myObject) {
            for(var key in myObject) {
                if (myObject.hasOwnProperty(key)) {
                    return false;
                }
            }

            return true;
        }

		vm.goBack = function() {
			$injector.get('$state').go('root.home');
		};

	}
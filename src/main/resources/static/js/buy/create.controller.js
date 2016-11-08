angular.module('buyModule')
		.controller('BuyCreateController', BuyCreateController);

    /*@ngInject*/
	function BuyCreateController(buyService, providerService, productService, $timeout, $injector, $q) {

		var vm = this;
		vm.buyData = {};
		vm.buyData.productos = [];
		vm.buyData.proveedor = {};
		vm.textoBuscadorProveedor = "";

        vm.math = window.Math;

        // Get Client y Product Modal
        var proveedorModal = document.getElementById('proveedorModal');
        var productoModal = document.getElementById('productoCompraModal');

        // Get the button that opens the modals
        var proveedorBtn = document.getElementById("proveedorBtn");
        var productoBtn = document.getElementById("productoCompraBtn");

        // Get the <span> element that closes the modal
        var proveedorSpan = document.getElementsByClassName("compra-proveedor-close")[0];
        var productoSpan = document.getElementsByClassName("compra-producto-close")[0];

        // When the user clicks the button, open the modal
        proveedorBtn.onclick = function() {
            proveedorModal.style.display = "block";
        }
        productoBtn.onclick = function() {
            productoModal.style.display = "block";
        }

        // When the user clicks on <span> (x), close the modal
        proveedorSpan.onclick = function() {
            proveedorModal.style.display = "none";
        }
        productoSpan.onclick = function() {
            productoModal.style.display = "none";
        }

        // When the user clicks anywhere outside of the modal, close it
        window.onclick = function(event) {
            if (event.target == proveedorModal) {
                proveedorModal.style.display = "none";
            }
            if (event.target == productoModal) {
                productoModal.style.display = "none";
            }
        }

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
                        });
                }else{
                    vm.message = "Debe ingresar una cantidad valida de producto";
                }
            }
        };

        vm.searchProveedoresByName = function() {
            providerService.getProveedoresByName(vm.providerName)
                .then(function(res) {
                    vm.message = "";
                    vm.proveedoresAux = [];
                    vm.providerSelected = false;
                    vm.searchMessage = "";
                    console.log(res.data);
                    vm.proveedoresAux = res.data;
                    if(res.data.length==0){
                        vm.searchMessage = "No se encontró ningún proveedor con ese nombre!"
                    }
                });
        }

        vm.searchProductosByName = function() {
            productService.getProductosByName(vm.productName)
                .then(function(res) {
                    vm.message = "";
                    vm.productosAux = [];
                    vm.productSelected = false;
                    vm.searchProductMessage = "";
                    console.log(res.data);
                    vm.productosAux = res.data;
                    if(res.data.length==0){
                        vm.searchProductMessage = "No se encontró ningún producto con ese nombre!"
                    }
                });
        }

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


        //DEPRECADO
        vm.addProduct = function(productAux){
            vm.productosAux.splice(vm.productosAux.indexOf(productAux),1);
            productAux.cantidad = 1;
            vm.buyData.productos.push(productAux);
            vm.calcularTotal();
        };

        vm.selectProductForBuy = function(productAux){
            var encontro = false;
            angular.forEach(vm.buyData.productos, function(producto, key){
                if(producto.id == productAux.id){
                    producto.cantidad = producto.cantidad + 1;
                    encontro = true;
                }
            })
            if(!encontro){
                productAux.cantidad = 1;
                vm.buyData.productos.push(productAux);
            }
                vm.calcularTotal();
        };

        vm.removeProvider = function(){
            vm.message = "";
            //vm.buyData.proveedores.splice(vm.buyData.proveedores.indexOf(proveedor),1);
            vm.proveedoresAux.push(vm.buyData.proveedor);
            vm.buyData.proveedor = {};
        };

        //DEPRECADO
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

        vm.selectProviderForBuy = function(providerAux){
            vm.buyData.proveedor = providerAux;
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
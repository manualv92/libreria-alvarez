angular.module('sellModule')
		.controller('SellCreateController', SellCreateController);

    /*@ngInject*/
	function SellCreateController(sellService, clientService, productService, $timeout, $injector, $q, $window) {

		var vm = this;
		vm.sellData = {};
		vm.sellData.productos = [];
		vm.sellData.cliente = {};
		vm.sellData.usuario = {};
		vm.textoBuscadorProducto = "";
		vm.textoBuscadorCliente = "";
        getUsuario();

        vm.math = window.Math;

        // Get Client y Product Modal
        var clienteModal = document.getElementById('clienteModal');
        var productoModal = document.getElementById('productoModal');

        // Get the button that opens the modals
        var clienteBtn = document.getElementById("clienteBtn");
        var productoBtn = document.getElementById("productoBtn");

        // Get the <span> element that closes the modal
        var clienteSpan = document.getElementsByClassName("venta-cliente-close")[0];
        var productoSpan = document.getElementsByClassName("venta-producto-close")[0];

        // When the user clicks the button, open the modal
        clienteBtn.onclick = function() {
            clienteModal.style.display = "block";
        }
        productoBtn.onclick = function() {
            productoModal.style.display = "block";
        }

        // When the user clicks on <span> (x), close the modal
        clienteSpan.onclick = function() {
            clienteModal.style.display = "none";
        }
        productoSpan.onclick = function() {
            productoModal.style.display = "none";
        }

        // When the user clicks anywhere outside of the modal, close it
        window.onclick = function(event) {
            if (event.target == clienteModal) {
                clienteModal.style.display = "none";
            }
            if (event.target == productoModal) {
                productoModal.style.display = "none";
            }
        }

        vm.createSell = function() {
            console.log("Entro a crear venta");
            vm.message = "Espere mientras se procesa la venta";
            if(isEmpty(vm.sellData.cliente)){
                vm.message = "Debe ingresar al menos 1 cliente";
            }else if(vm.sellData.productos.length==0){
                vm.message = "Debe ingresar al menos 1 producto";
            } else {
                var bandera = checkCantidades();
                if(!bandera){
                    vm.calcularTotal();
                    sellService.create(vm.sellData)
                        .then(function(res) {
                            vm.message = "Se creó la venta con éxito y se guardó la factura en el disco E:";
                            vm.sellData = {};
                            vm.sellData.productos = [];
                            vm.sellData.cliente = {};
                            getUsuario();
                        });
                }else{
                    vm.message = "Debe ingresar una cantidad valida de producto";
                }
            }


        };

        function getUsuario(){
            vm.userString = $window.localStorage.getItem('user');
            vm.sellData.usuario = JSON.parse(vm.userString);
        }

        vm.calcularTotal = function(){
            vm.message = "";
            vm.sellData.total = 0.00;
            angular.forEach(vm.sellData.productos, function(producto, key){
                console.log(producto.cantidad);
                if(producto.stock < producto.cantidad){
                    vm.message = "No posee suficiente stock del producto."
                }else{
                    if(producto.cantidad!=null){
                        vm.sellData.total = Math.round((vm.sellData.total + (producto.precioVenta * producto.cantidad))*100)/100;
                    }
                }
            })
            console.log(vm.sellData.total);
        };

        vm.removeProduct = function(product){
            vm.sellData.productos.splice(vm.sellData.productos.indexOf(product),1);
            vm.productosAux.push(product);
        };

        //DEPRECADO
        vm.addProduct = function(productAux){
            vm.productosAux.splice(vm.productosAux.indexOf(productAux),1);
            productAux.cantidad = 1;
            vm.sellData.productos.push(productAux);
            vm.calcularTotal();
        };

        vm.selectProductForSell = function(productAux){
            var encontro = false;
            angular.forEach(vm.sellData.productos, function(producto, key){
                if(producto.id == productAux.id){
                    producto.cantidad = producto.cantidad + 1;
                    encontro = true;
                }
            })
            if(!encontro){
                //vm.productosAux.splice(vm.productosAux.indexOf(productAux),1);
                productAux.cantidad = 1;
                vm.sellData.productos.push(productAux);
            }
                vm.calcularTotal();
        };

        vm.removeClient = function(){
            vm.message = "";
            vm.clientesAux.push(vm.sellData.cliente);
            vm.sellData.cliente = {};
        };

        //DEPRECADO
        vm.addClient = function(clienteAux){
            vm.message = "";
            if(isEmpty(vm.sellData.cliente)){
                vm.clientesAux.splice(vm.clientesAux.indexOf(clienteAux),1);
                vm.sellData.cliente = clienteAux;
            }else{
                vm.message = "Solo se puede ingresar 1 cliente por compra";
            }
        };

        vm.selectClientForSell = function(clienteAux){
            vm.sellData.cliente = clienteAux;
        };

        vm.searchClientesByName = function() {
            clientService.getClientesByName(vm.clientName)
                .then(function(res) {
                    vm.message = "";
                    vm.clientesAux = [];
                    vm.clientSelected = false;
                    vm.searchMessage = "";
                    console.log(res.data);
                    vm.clientesAux = res.data;
                    if(res.data.length==0){
                        vm.searchMessage = "No se encontró ningún cliente con ese nombre!"
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

        function isEmpty(myObject) {
            for(var key in myObject) {
                if (myObject.hasOwnProperty(key)) {
                    return false;
                }
            }

            return true;
        }

        function checkCantidades() {
            var bandera = false;
            angular.forEach(vm.sellData.productos, function(producto, key){
                if(producto.cantidad == null){
                    bandera = true;
                }
            });
            return bandera;
        }

		vm.goBack = function() {
			$injector.get('$state').go('root.home');
		};

	}
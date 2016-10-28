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
        getProductos();
        getClientes();
        getUsuario();

        vm.math = window.Math;

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
                            getProductos();
                            getClientes();
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

        function getClientes(){
            clientService.getAll()
                .then(function(res) {
                    console.log(res.data);
                    vm.clientesAux = res.data;
                    console.log(vm.clientesAux);
                });
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

        vm.addProduct = function(productAux){
            vm.productosAux.splice(vm.productosAux.indexOf(productAux),1);
            productAux.cantidad = 1;
            vm.sellData.productos.push(productAux);
            vm.calcularTotal();
        };

        vm.removeClient = function(){
            vm.message = "";
            vm.clientesAux.push(vm.sellData.cliente);
            vm.sellData.cliente = {};
        };

        vm.addClient = function(clienteAux){
            vm.message = "";
            if(isEmpty(vm.sellData.cliente)){
                vm.clientesAux.splice(vm.clientesAux.indexOf(clienteAux),1);
                vm.sellData.cliente = clienteAux;
            }else{
                vm.message = "Solo se puede ingresar 1 cliente por compra";
            }
        };

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
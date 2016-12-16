angular.module('sellModule')
		.controller('SellConsultController', SellConsultController);

    /*@ngInject*/
	function SellConsultController(sellService, $timeout, $injector, $q) {

		var vm = this;
		vm.sellData = {};
		vm.sellData.productos = [];
		vm.sellData.proveedor = {};

		vm.searchedSells = [];
        vm.sellSelected = false;

        vm.math = window.Math;

        vm.searchSellsByDate = function() {
            vm.sellDate = vm.searchAnio + '/' + vm.searchMes + '/' + vm.searchDia
            sellService.getSellsByDate(vm.sellDate)
                .then(function(res) {
                    vm.message = "";
                    vm.searchedSells = [];
                    vm.sellData = {};
                    vm.sellSelected = false;
                    vm.searchMessage = "";
                    console.log(res.data);
                    vm.searchedSells = res.data;
                    if(res.data.length==0){
                        vm.searchMessage = "No se encontró ninguna compra en esa fecha!"
                    }
                });
        }

        vm.selectSell = function(sell) {
            vm.sellData = sell;
            vm.sellSelected = true;
        }


		vm.goBack = function() {
			$injector.get('$state').go('root.home');
		};

	}
angular.module('buyModule')
		.controller('BuyConsultController', BuyConsultController);

    /*@ngInject*/
	function BuyConsultController(buyService, $timeout, $injector, $q) {

		var vm = this;
		vm.buyData = {};
		vm.buyData.productos = [];
		vm.buyData.proveedor = {};

		vm.searchedBuys = [];
        vm.buySelected = false;

        vm.math = window.Math;

        vm.searchBuysByDate = function() {
            vm.buyDate = vm.searchAnio + '/' + vm.searchMes + '/' + vm.searchDia
            buyService.getBuysByDate(vm.buyDate)
                .then(function(res) {
                    vm.message = "";
                    vm.searchedBuys = [];
                    vm.buyData = {};
                    vm.buySelected = false;
                    vm.searchMessage = "";
                    console.log(res.data);
                    vm.searchedBuys = res.data;
                    if(res.data.length==0){
                        vm.searchMessage = "No se encontró ninguna compra en esa fecha!"
                    }
                });
        }

        vm.selectBuy = function(buy) {
            vm.buyData = buy;
            vm.buySelected = true;
        }


		vm.goBack = function() {
			$injector.get('$state').go('root.home');
		};

	}
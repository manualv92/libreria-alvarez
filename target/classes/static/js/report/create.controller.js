angular.module('reportModule')
		.controller('ReportCreateController', ReportCreateController);

    /*@ngInject*/
	function ReportCreateController(reportService, $timeout, $injector, $q) {

		var vm = this;
		vm.reportData = {};

		vm.crearReporteVenta = function() {
            reportService.createSellReport()
                .then(function(res){
                    vm.message = "Se creó un archivo excel en el disco E: con las ventas realizadas.";
                });
        }

        vm.crearReporteCompra = function() {
            reportService.createBuyReport()
                .then(function(res){
                    vm.message = "Se creó un archivo excel en el disco E: con las compras realizadas.";
                });
        }
	}
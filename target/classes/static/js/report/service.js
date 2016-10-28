angular.module('reportModule')
		.factory('reportService', reportService);

    /*@ngInject*/
	function reportService($http, $q) {

		var url = "";

		return {
			createSellReport: createSellReport,
			createBuyReport: createBuyReport
		};

		function createSellReport() {
            return $http.get(url + '/api/report/sell/');
        }
		function createBuyReport() {
            return $http.get(url + '/api/report/buy/');
        }
	}
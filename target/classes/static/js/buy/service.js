angular.module('buyModule')
		.factory('buyService', buyService);

    /*@ngInject*/
	function buyService($http, $q) {

		var url = "";

		return {
			get: get,
			create: create,
			update: update,
			del: del,
			getBuyByName: getBuyByName
		};


		function get(buyData) {
			return $http.get(url + '/api/buy/search/', buyData);
		}
		function create(buyData) {
			return $http.post(url + '/api/buy/create/', buyData);
		}
		function update(buyData) {
			return $http.put(url + '/api/buy/update/', buyData);
		}
		function del(id) {
			return $http.delete(url + '/api/buy/delete/' + id);
		}
        function getBuyByName(buyName) {
            return $http.get(url + '/api/buy/search?buyName=' + buyName + '&&buyHabilitado=1');
        }
	}
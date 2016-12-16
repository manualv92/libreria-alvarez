angular.module('sellModule')
		.factory('sellService', sellService);

    /*@ngInject*/
	function sellService($http, $q) {

		var url = "";

		return {
			get: get,
			create: create,
			update: update,
			del: del,
            getSellsByDate: getSellsByDate
		};


		function get(sellData) {
			return $http.get(url + '/api/sell/search/', sellData);
		}
		function create(sellData) {
			return $http.post(url + '/api/sell/create/', sellData);
		}
		function update(sellData) {
			return $http.put(url + '/api/sell/update/', sellData);
		}
		function del(id) {
			return $http.delete(url + '/api/sell/delete/' + id);
		}
		function getSellsByDate(sellDate) {
            return $http.get(url + '/api/sell/search?sellDate=' + sellDate);
        }
	}
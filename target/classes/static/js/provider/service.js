angular.module('providerModule')
		.factory('providerService', providerService);

    /*@ngInject*/
	function providerService($http, $q) {

		var url = "";

		return {
			get: get,
			create: create,
			update: update,
			del: del,
			getProveedoresByName: getProveedoresByName,
			getAll: getAll
		};


		function get(productData) {
			return $http.get(url + '/api/provider/search/', productData);
		}
		function create(productData) {
			return $http.post(url + '/api/provider/create/', productData);
		}
		function update(providerData) {
			return $http.put(url + '/api/provider/update/', providerData);
		}
		function del(id) {
			return $http.delete(url + '/api/provider/delete/' + id);
		}
        function getProveedoresByName(providerName) {
            return $http.get(url + '/api/provider/search?providerName=' + providerName + '&&providerHabilitado=1');
        }
        function getAll() {
            return $http.get(url + '/api/provider/getAll');
        }
	}
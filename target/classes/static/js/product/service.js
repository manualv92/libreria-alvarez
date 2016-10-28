angular.module('productModule')
		.factory('productService', productService);

    /*@ngInject*/
	function productService($http, $q) {

		var url = "";

		return {
			get: get,
			create: create,
			update: update,
			del: del,
			getTipoProductos: getTipoProductos,
			getProductosByName: getProductosByName,
			getAll: getAll
		};


		function get(productData) {
			return $http.get(url + '/api/product/search/', productData);
		}
		function create(productData) {
			return $http.post(url + '/api/product/create/', productData);
		}
		function update(productData) {
			return $http.put(url + '/api/product/update/', productData);
		}
		function del(id) {
			return $http.delete(url + '/api/product/delete/' + id);
		}
        function getProductosByName(productName) {
            return $http.get(url + '/api/product/search?productName=' + productName + '&&productHabilitado=1');
        }
        function getTipoProductos() {
            return $http.get(url + '/api/product/tipoproducto');
        }
        function getAll() {
            return $http.get(url + '/api/product/getAll');
        }
	}
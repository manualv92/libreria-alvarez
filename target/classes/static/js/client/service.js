angular.module('clientModule')
		.factory('clientService', clientService);

    /*@ngInject*/
	function clientService($http, $q) {

		var url = "";

		return {
			get: get,
			create: create,
			update: update,
			del: del,
			getTipoPersonas: getTipoPersonas,
			getTipoDocumento: getTipoDocumento,
			getClientesByName: getClientesByName,
			getClientesByNroDocumento: getClientesByNroDocumento,
			getAll: getAll
		};


		function get(clientData) {
			return $http.get(url + '/api/client/search/', clientData);
		}

		function create(clientData) {
			return $http.post(url + '/api/client/create/', clientData);
		}
		function update(clientData) {
			return $http.put(url + '/api/client/update/', clientData);
		}
		function del(id) {
			return $http.delete(url + '/api/client/delete/' + id);
		}
		function getTipoPersonas() {
            return $http.get(url + '/api/client/tipopersona');
	    }
	    function getTipoDocumento() {
            return $http.get(url + '/api/client/tipodocumento');
        }
        function getClientesByName(clientName) {
            return $http.get(url + '/api/client/search?clientName=' + clientName + '&&clientHabilitado=1');
        }
        function getClientesByNroDocumento(nroDocumento) {
            return $http.get(url + '/api/client/searchByDocument?nroDocumento=' + nroDocumento);
        }
        function getAll() {
            return $http.get(url + '/api/client/getAll');
        }

	}
angular.module('userModule')
		.factory('userService', userService);

    /*@ngInject*/
	function userService($http, $q, $window) {

		var url = "";

		return {
			get: get,
			create: create,
			update: update,
			del: del,
			getRoles: getRoles,
			getUsuariosByName: getUsuariosByName,
			getAll: getAll,
			getTipoDocumento: getTipoDocumento,
			logInUser: logInUser,
			getLocalStorageUser: getLocalStorageUser,
			setLocalStorageUser: setLocalStorageUser
		};


		function get(userData) {
			return $http.get(url + '/api/user/search/', userData);
		}
		function create(userData) {
			return $http.post(url + '/api/user/create/', userData);
		}
		function update(userData) {
			return $http.put(url + '/api/user/update/', userData);
		}
		function del(id) {
			return $http.delete(url + '/api/user/delete/' + id);
		}
        function getUsuariosByName(userName) {
            return $http.get(url + '/api/user/search?userName=' + userName + '&&userHabilitado=1');
        }
        function getRoles() {
            return $http.get(url + '/api/user/rol');
        }
        function getAll() {
            return $http.get(url + '/api/user/getAll');
        }
        function getTipoDocumento() {
            return $http.get(url + '/api/user/tipodocumento');
        }
        function logInUser(userData) {
            return $http.get(url + '/api/user/login?userNroDocumento='+userData.nroDocumento+'&&userPassword=' +userData.password+ '&&userHabilitado=1');
        }
        function getLocalStorageUser() {
            return $window.localStorage.getItem('user');
        }
        function setLocalStorageUser(user) {
            return $window.localStorage.setItem('user', user);
        }

	}
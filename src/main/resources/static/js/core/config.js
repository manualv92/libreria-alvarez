angular.module('app').config(config);

    function config($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('root', {
                abstract: true,
                views: {
                    '': {
                        template: '<div ui-view="main"></div>'
                    },
                    'navbar': {
                        templateUrl: 'views/core/navbar.html',
                        controller: 'NavbarController as nc'
                    }
                }
            })
            .state('root.home',{
                url: '/',
                views: {
                    'main': {
                        templateUrl: 'views/core/home.html',
                        controller: 'HomeController as hc'
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }
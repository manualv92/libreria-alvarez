angular.module('clientModule').config(config);

    /*@ngInject*/
    function config($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('root.client',{
                url: '/client',
                views: {
                    'main': {
                        templateUrl: 'views/client/layout.html'
                    }
                }
            })
            .state('root.client.create',{
                url: '/create',
                views: {
                    '': {
                        templateUrl: 'views/client/create.html',
                        controller: 'ClientCreateController as ccc'
                    }
                }
            })
            .state('root.client.edit',{
                url: '/edit',
                views: {
                    '': {
                        templateUrl: 'views/client/edit.html',
                        controller: 'ClientEditController as cec'
                    }
                }
            })
            .state('root.client.delete',{
                url: '/delete',
                views: {
                    '': {
                        templateUrl: 'views/client/delete.html',
                        controller: 'ClientDeleteController as cdc'
                    }
                }
            })
            .state('root.client.consult',{
                url: '/consult',
                views: {
                    '': {
                        templateUrl: 'views/client/consult.html',
                        controller: 'ClientConsultController as ccc'
                    }
                }
            });
    }
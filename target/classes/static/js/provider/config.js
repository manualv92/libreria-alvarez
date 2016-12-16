angular.module('providerModule').config(config);

    /*@ngInject*/
    function config($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('root.provider',{
                url: '/provider',
                views: {
                    'main': {
                        templateUrl: 'views/provider/layout.html'
                    }
                }
            })
            .state('root.provider.create',{
                url: '/create',
                views: {
                    '': {
                        templateUrl: 'views/provider/create.html',
                        controller: 'ProviderCreateController as pcc'
                    }
                }
            })
            .state('root.provider.edit',{
                url: '/edit',
                views: {
                    '': {
                        templateUrl: 'views/provider/edit.html',
                        controller: 'ProviderEditController as pec'
                    }
                }
            })
            .state('root.provider.delete',{
                url: '/delete',
                views: {
                    '': {
                        templateUrl: 'views/provider/delete.html',
                        controller: 'ProviderDeleteController as pdc'
                    }
                }
            })
            .state('root.provider.consult',{
                url: '/consult',
                views: {
                    '': {
                        templateUrl: 'views/provider/consult.html',
                        controller: 'ProviderConsultController as pcc'
                    }
                }
            });
    }
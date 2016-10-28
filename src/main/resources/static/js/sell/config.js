angular.module('sellModule').config(config);

    /*@ngInject*/
    function config($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('root.sell',{
                url: '/sell',
                views: {
                    'main': {
                        templateUrl: 'views/sell/layout.html'
                    }
                }
            })
            .state('root.sell.create',{
                url: '/create',
                views: {
                    '': {
                        templateUrl: 'views/sell/create.html',
                        controller: 'SellCreateController as scc'
                    }
                }
            });
    }
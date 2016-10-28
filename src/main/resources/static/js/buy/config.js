angular.module('buyModule').config(config);

    /*@ngInject*/
    function config($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('root.buy',{
                url: '/buy',
                views: {
                    'main': {
                        templateUrl: 'views/buy/layout.html'
                    }
                }
            })
            .state('root.buy.create',{
                url: '/create',
                views: {
                    '': {
                        templateUrl: 'views/buy/create.html',
                        controller: 'BuyCreateController as bcc'
                    }
                }
            });
    }
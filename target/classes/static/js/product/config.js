angular.module('productModule').config(config);

    /*@ngInject*/
    function config($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('root.product',{
                url: '/product',
                views: {
                    'main': {
                        templateUrl: 'views/product/layout.html'
                    }
                }
            })
            .state('root.product.create',{
                url: '/create',
                views: {
                    '': {
                        templateUrl: 'views/product/create.html',
                        controller: 'ProductCreateController as pcc'
                    }
                }
            })
            .state('root.product.edit',{
                url: '/edit',
                views: {
                    '': {
                        templateUrl: 'views/product/edit.html',
                        controller: 'ProductEditController as pec'
                    }
                }
            })
            .state('root.product.delete',{
                url: '/delete',
                views: {
                    '': {
                        templateUrl: 'views/product/delete.html',
                        controller: 'ProductDeleteController as pdc'
                    }
                }
            });
    }
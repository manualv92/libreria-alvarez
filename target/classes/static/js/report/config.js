angular.module('reportModule').config(config);

    /*@ngInject*/
    function config($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('root.report',{
                url: '/report',
                views: {
                    'main': {
                        templateUrl: 'views/report/layout.html'
                    }
                }
            })
            .state('root.report.create',{
                url: '/create',
                views: {
                    '': {
                        templateUrl: 'views/report/create.html',
                        controller: 'ReportCreateController as rcc'
                    }
                }
            });
    }
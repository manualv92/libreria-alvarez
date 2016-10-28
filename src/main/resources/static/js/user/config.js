angular.module('userModule').config(config);

    /*@ngInject*/
    function config($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('root.user',{
                url: '/user',
                views: {
                    'main': {
                        templateUrl: 'views/user/layout.html'
                    }
                }
            })
            .state('root.user.create',{
                url: '/create',
                views: {
                    '': {
                        templateUrl: 'views/user/create.html',
                        controller: 'UserCreateController as ucc'
                    }
                }
            })
            .state('root.user.edit',{
                url: '/edit',
                views: {
                    '': {
                        templateUrl: 'views/user/edit.html',
                        controller: 'UserEditController as uec'
                    }
                }
            })
            .state('root.user.delete',{
                url: '/delete',
                views: {
                    '': {
                        templateUrl: 'views/user/delete.html',
                        controller: 'UserDeleteController as udc'
                    }
                }
            })
            .state('root.user.login',{
                url: '/login',
                views: {
                    '': {
                        templateUrl: 'views/user/login.html',
                        controller: 'UserLoginController as ulc'
                    }
                }
            });
    }
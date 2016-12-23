(function () {
    'use strict';

    angular.module('FullStackTeste')
        .config(configFunction);

    function configFunction($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise("/login");

        $stateProvider
         .state('login', {
                url: "/login",
                views: {
                    "main": {
                        templateUrl: "app/views/login.html",
                        controller: 'LoginController as vm'
                    }
                }
            })
            .state('home', {
                url: "/home",
                views: {
                    "main": {
                        templateUrl: "app/views/home.html",
                        controller: 'HomeController as vm'
                    }
                }
            })
            .state('notas', {
                parent: 'home',
                url: "/notas",
                views: {
                    "auth": {
                        controller: 'NotaController as vm',
                        templateUrl: "app/views/nota.html",
                    }
                }
            })

    }
})();
(function () {
    'use strict';

    angular.module('FullStackTeste')
        .config(configFunction);

    function configFunction($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise("/home");

        $stateProvider
            .state('home', {
                url: "/home",
                views: {
                    "main": {
                        templateUrl: "app/views/home.html",
                        controller: 'HomeController as vm'
                    }
                }
            })
            .state('clientes', {
                parent: 'home',
                url: "/clientes",
                views: {
                    "auth": {
                        controller: 'ClienteController as vm',
                        templateUrl: "app/views/cliente.html",
                    }
                }
            })

    }
})();
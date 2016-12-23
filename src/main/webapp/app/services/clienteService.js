(function () {
    'use strict';

    angular.module('FullStackTeste')
        .service('clienteAPI', clienteAPI);

    function clienteAPI($http, apiUrl) {

        this.login = function (cliente) {
            return $http.post(apiUrl + '/cliente/login', cliente);
        };

        this.salvar = function (cliente) {
            return $http.post(apiUrl + '/cliente/salvar', cliente);
        };

       this.listar = function () {
            return $http.get(apiUrl + '/cliente/listarTodos');
        };
    }
}());
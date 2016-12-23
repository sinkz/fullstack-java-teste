(function () {
    'use strict';

    angular.module('FullStackTeste')
        .service('notaAPI', notaAPI);

    function notaAPI($http, apiUrl) {

        this.salvar = function (nota) {
            return $http.post(apiUrl + '/nota/salvar', nota);
        };

        this.listar = function (cnpj) {
            return $http.get(apiUrl + '/nota/listarPorCliente/' + cnpj);
        };
    }
}());
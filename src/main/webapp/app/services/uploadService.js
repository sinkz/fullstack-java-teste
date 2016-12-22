(function () {
    'use strict';

    angular.module('FullStackTeste')
        .service('uploadAPI', uploadAPI);

    function uploadAPI($http, apiUrl) {

        this.uploadAnexoCliente = function (file) {
            return $http.post(apiUrl + '/anexo/anexoCliente', file);
        };


    }
}());
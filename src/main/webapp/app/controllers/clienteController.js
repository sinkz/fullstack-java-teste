(function () {
    'use strict';

    angular.module('FullStackTeste')
        .controller('ClienteController', ClienteController);

    function ClienteController($scope, Upload, clienteAPI) {
        var vm = this;
        vm.cliente = {};

    
    }
})();
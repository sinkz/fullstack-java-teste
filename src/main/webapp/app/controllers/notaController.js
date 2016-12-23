(function () {
    'use strict';

    angular.module('FullStackTeste')
        .controller('NotaController', NotaController);

    function NotaController($scope, $timeout, $mdSidenav) {
        var vm = this;
        vm.myDate = new Date();
  vm.isOpen = false;
       

    }
})();
(function () {
    'use strict';

    angular.module('FullStackTeste')
        .controller('NavBarController', NavBarController);

    function NavBarController($scope, $state, $localStorage) {
        $scope.currentNavItem = 'page1';
        var vm = this;

        vm.sair = function () {
            console.log("chamou")
            delete $localStorage.usuario;
            $state.go('login', {}, {
                reload: true
            });
        }
    }
})();
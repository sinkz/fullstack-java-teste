(function () {
    'use strict';

    angular.module('FullStackTeste')
        .controller('HomeController', HomeController);

    function HomeController($scope, $timeout, $mdSidenav) {
        var vm = this;
        $scope.toggleLeft = buildToggler('left');
        $scope.toggleRight = buildToggler('right');

        function buildToggler(componentId) {
            return function () {
                $mdSidenav(componentId).toggle();
            }
        }


    }
})();
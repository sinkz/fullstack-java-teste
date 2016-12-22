(function () {
    'use strict';

    angular.module('FullStackTeste')
        .controller('NavBarController', NavBarController);

    function NavBarController($scope) {
        $scope.currentNavItem = 'page1';


    }
})();
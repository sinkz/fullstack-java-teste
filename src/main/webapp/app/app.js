(function () {
    'use strict';

    angular
        .module('FullStackTeste', ['ngMaterial', 'ui.router', 'ngAria', 'ngAnimate', 'ngMessages', 'ngFileUpload', 'ngStorage'])
        .constant('apiUrl', 'http://localhost:8080/FullstackJava/rest')
        .constant('apiBaseUrl', 'http://localhost:8080/FullstackJava')


})();
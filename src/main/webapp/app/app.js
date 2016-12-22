(function () {
    'use strict';

    angular
        .module('FullStackTeste', ['ngMaterial', 'ui.router', 'ngMessages', 'ngFileUpload'])
        .constant('apiUrl', 'http://localhost:8080/FullstackJava/rest')
        .constant('apiBaseUrl', 'http://localhost:8080/FullstackJava')


})();
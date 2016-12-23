(function () {
    'use strict';

    angular
        .module('FullStackTeste', ['ngMaterial', 'ui.router', 'ngAria', 'ngAnimate', 'ngMessages', 
        'ngFileUpload', 'ngStorage', 'md.data.table', 'ui.utils.masks', 'lfNgMdFileInput'])
        .constant('apiUrl', 'http://localhost:8080/FullstackJava/rest')
        .constant('apiBaseUrl', 'http://localhost:8080/FullstackJava')


})();
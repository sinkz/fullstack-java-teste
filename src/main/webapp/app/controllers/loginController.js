(function () {
    'use strict';

    angular.module('FullStackTeste')
        .controller('LoginController', LoginController);

    function LoginController($scope, $state, $timeout, $mdDialog, Upload, clienteAPI, $rootScope, $localStorage) {
        var vm = this;
        $rootScope.anexos = [];
        $rootScope.files = [];
        vm.cliente = {};


        vm.entrar = function (cliente) {
            clienteAPI.login(cliente).then(function (response) {
                delete $localStorage.usuario;
                $localStorage.usuario = response.data;
                console.log($localStorage.usuario);
                $state.go('home');
            }, function (err) {
                console.log("erro");
            })
        }

        $scope.hide = function () {
            $mdDialog.hide();
        };

        $scope.cancel = function () {
            $mdDialog.cancel();
        };

        $scope.answer = function (answer) {
            $mdDialog.hide(answer);
        };

        vm.showAdvanced = function (ev) {
            $mdDialog.show({
                    controller: LoginController,
                    templateUrl: 'app/views/templates/novoCliente.html',
                    parent: angular.element(document.body),
                    targetEvent: ev,
                    clickOutsideToClose: true,
                    fullscreen: $scope.customFullscreen // Only for -xs, -sm breakpoints.
                })
                .then(function (cliente) {
                    salvarCliente(cliente);

                }, function () {
                    console.log("Hue")
                });
        };

        function salvarCliente(cliente) {
            console.log("Chamou")
            if (cliente.regimeTributario === 'Simples Nacional') {
                cliente.regimeTributario = 'Simples_Nacional';
            } else if (cliente.regimeTributario === 'Lucro Presumido') {
                cliente.regimeTributario = 'Lucro_Presumido';
            }
            cliente.anexos = $rootScope.anexos;
            console.log(cliente.anexos);
            clienteAPI.salvar(cliente).then(function (response) {

                angular.forEach($rootScope.files, function (file) {
                    file.upload = Upload.upload({
                        url: 'http://localhost:8080/FullstackJava/rest/anexo/anexoCliente',
                        data: {
                            file: file
                        }
                    });
                    file.upload.then(function (response) {
                        $timeout(function () {
                            file.result = response.data;
                        });
                    }, function (response) {
                        if (response.status > 0)
                            $scope.errorMsg = response.status + ': ' + response.data;
                    }, function (evt) {
                        file.progress = Math.min(100, parseInt(100.0 *
                            evt.loaded / evt.total));
                    });
                });

            }, function (err) {
                console.log(err);
            })
            delete vm.cliente;
        }


        $scope.uploadFiles = function (files, errFiles) {
            var anexos = [];
            angular.forEach(files, function (file) {
                var aux = {
                    nomeAnexo: file.name,
                    caminhoAnexo: 'C:/fullstack/clientes',
                }
                anexos.push(aux);
                aux = {};
            });
            vm.cliente.anexos = anexos;
            $rootScope.anexos = anexos;
            $rootScope.files = files;
            vm.errFiles = errFiles;
        }


    }
})();
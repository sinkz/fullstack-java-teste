(function () {
    'use strict';

    angular.module('FullStackTeste')
        .controller('LoginController', LoginController);

    function LoginController($scope, $state, $timeout, $mdDialog, Upload, clienteAPI, $rootScope, $localStorage) {
        var vm = this;
        $rootScope.anexos = [];
        vm.cliente = {};


        vm.entrar = function (cliente) {
            clienteAPI.login(cliente).then(function (response) {
                delete  $localStorage.usuario;
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
                .then(function (cliente, files) {
                    console.log(files);
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
                console.log(response);
            }, function (err) {
                console.log(err);
            })
            console.log(cliente);
            delete vm.cliente;
        }


        $scope.uploadFiles = function (files, errFiles) {
            console.log(files);

            var anexos = [];
            angular.forEach(files, function (file) {
                var aux = {
                    nomeAnexo: file.name,
                    caminhoAnexo: 'src/main/webapp/uploads/clientes',
                }
                anexos.push(aux);
                aux = {};
            });
            vm.cliente.anexos = anexos;
            $rootScope.anexos = anexos;
            vm.files = files;
            vm.errFiles = errFiles;

            angular.forEach(files, function (file) {
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

        }


    }
})();
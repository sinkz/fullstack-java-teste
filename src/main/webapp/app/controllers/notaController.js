(function () {
    'use strict';

    angular.module('FullStackTeste')
        .controller('NotaController', NotaController);

    function NotaController($scope, $timeout, $mdSidenav, $localStorage, notaAPI, Upload, $mdDialog) {
        var vm = this;
        vm.nota = {};
        vm.clienteLogado = $localStorage.usuario;
        vm.file = {};
        var caminhoAnexo = 'c:/fullstack/notas';

        console.log(vm.clienteLogado);
        $scope.selected = [];
        $scope.myLimit = 5;
        $scope.myPage = 1;


        $scope.setFile = function (file, errorFile, v) {
            if (v === 1) {
                vm.file.file1 = file;
            } else if (v === 2) {
                vm.file.file2 = file;
            } else if (v === 3) {
                vm.file.file3 = file;
            }


        }

        vm.salvarNota = function (nota) {
            var listaAnexos = [{
                file: vm.file.file1
            }, {
                file: vm.file.file2
            }, {
                file: vm.file.file3
            }];

            vm.file.file1.name
            nota.anexos = [{
                nomeAnexo: vm.file.file1.name,
                caminhoAnexo: caminhoAnexo,
                tipoAnexo: 1,
            }, {
                nomeAnexo: vm.file.file2.name,
                caminhoAnexo: caminhoAnexo,
                tipoAnexo: 2,
            }, {
                nomeAnexo: vm.file.file3.name,
                caminhoAnexo: caminhoAnexo,
                tipoAnexo: 3,
            }]

            console.log(listaAnexos);

            


            nota.cliente = vm.clienteLogado;
            notaAPI.salvar(nota).then(function (response) {
                vm.nota = {};
                vm.notas = response.data;
                angular.forEach(listaAnexos, function (file) {
                    console.log(file.file);
                    file.upload = Upload.upload({
                        url: 'http://localhost:8080/FullstackJava/rest/anexo/anexoNota',
                        data: {
                            file: file.file
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
        }

        vm.listarNotas = function () {
            console.log("TEste");
            notaAPI.listar(vm.clienteLogado.cnpj).then(function (response) {
                vm.notas = response.data;
                console.log(response);
            }, function (err) {
                console.log(err);
            })
        }
        vm.listarNotas();


        $scope.hide = function () {
            $mdDialog.hide();
        };

        $scope.cancel = function () {
            $mdDialog.cancel();
        };

        $scope.answer = function (answer) {
            $mdDialog.hide(answer);
        };

        vm.showAdvanced = function (ev, nota) {
            console.log(nota);
            $mdDialog.show({
                    controller: NotaController,
                    templateUrl: 'app/views/templates/anexosNota.html',
                    parent: angular.element(document.body),
                    targetEvent: ev,
                    clickOutsideToClose: true,
                    fullscreen: $scope.customFullscreen // Only for -xs, -sm breakpoints.
                })
                .then(function (cliente, files) {
                    console.log("ei");


                }, function () {
                    console.log("Hue")
                });
        };

        function isEmpty(obj) {
            for (var prop in obj) {
                if (obj.hasOwnProperty(prop))
                    return false;
            }
            return true;
        }

        function uploadFiles(files) {
            console.log(files);
            var anexos = [];

        }
    }
})();
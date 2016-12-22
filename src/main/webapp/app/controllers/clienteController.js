(function () {
    'use strict';

    angular.module('FullStackTeste')
        .controller('ClienteController', ClienteController);

    function ClienteController($scope, Upload, clienteAPI) {
        var vm = this;
        vm.cliente = {};

        $scope.gridOptions = {
            data: [],
            urlSync: false
        };
        clienteAPI.listar().then(function (responseData) {
            $scope.gridOptions.data = responseData.data;
        })


        vm.salvarCliente = function (cliente) {
            if (cliente.regimeTributario === 'Simples Nacional') {
                cliente.regimeTributario = 'Simples_Nacional';
            } else if (cliente.regimeTributario === 'Lucro Presumido') {
                cliente.regimeTributario = 'Lucro_Presumido';
            }

            console.log(cliente);
            clienteAPI.salvar(cliente).then(function (response) {
                console.log(response);
            }, function (err) {
                console.log(err);
            })
            console.log(cliente);
            delete vm.cliente;
        }



        vm.uploadFiles = function uploadFiles(files, errFiles) {
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
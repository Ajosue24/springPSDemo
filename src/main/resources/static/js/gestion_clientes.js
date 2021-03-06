    $(document).ready(function () {
        var idG;
        $('#desPais').autocomplete({
            source: function (request, response) {
                var desPais = $('#desPais').val();
                $.ajax({
                    url: '/rest/autoCompPais/' + desPais,
                    dataType: 'json',
                    contentType: 'application/json',
                    success: function (data) {
                        response($.map(data, function (item) {
                            return {label: item.nombrePais.trim(), value: item.nombrePais.trim(), id: item.idPais};
                        }));
                    },
                    error: function (xhr, status, error) {
                        alert("Error");
                    }
                });
                }, select: function (event, ui) {
                updateComboIdPais(ui.item.id);
                $("#idPais").val(ui.item.id);
                    debugger;
            }
        });
    });

//Metodo para actualizar la tabla x ajax

    function updateComboIdPais(idPais) {

        $("#idTipoPais").load('/gestionClientes/idTipoPais', 'idPais.nombrePais='+idPais+'');
        $("#idDepartamentos").load('/gestionClientes/obtDepartamentos', 'idPais.nombrePais='+idPais+'');

    }

    $( "#idDepartamentos" ).change(function() {
        $("#idCiudad").load('/gestionClientes/obtCiudades', 'idDepartamento='+$( "#idDepartamentos" ).val()+'');
    });


   // old works
/*    function updateComboIdPais(idPais) {
        var url = '/restC/idTipoPais';
        url = url + '/' + idPais;
        $("#idTipoPais").load(url);
    }*/


   /* $(document).ready(function(){
        $("#numeroIdentificacionCliente").blur(function(){
            $("#clienteForm").load('/gestionClientes/editarCliente', $("#numeroIdentificacionCliente").serialize());
        });
    });*/
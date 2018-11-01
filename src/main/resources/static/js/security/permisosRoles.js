/*function updateComboIdPais(idPais) {
    $("#idDepartamento").load('/pemisosRoles/findEnableModulo', 'modulos='+$("#modulos").val()+'');
}*/


$( "#estadoModulo" ).click(function() {
    validarChecked();
});

function validarChecked() {
    if($("#modulos").val() != "" && $("#listRoles").val()!= "") {

        if ($('#estadoModulo').is(":checked") == true) {
            $("#editarPermisos").load('/pemisosRoles/enableModule', 'modulos=' + $("#listRoles").val() + '&' + 'modulos=' + $("#modulos").val() + '');

        } else {
            $("#editarPermisos").load('/pemisosRoles/disableModule', 'modulos=' + $("#listRoles").val() + '&' + 'modulos=' + $("#modulos").val() + '');
        }
    }
}

function actualizarUrlB(id,value){
    if($("#modulos").val() != "" && $("#listRoles").val()!= ""){
    if($(id).is(":checked") == true) {
        $(id).load('/pemisosRoles/enableUrl', 'url=' + value + '&' + 'url=' + $("#listRoles").val() + '');
    }else{
        $(id).load('/pemisosRoles/disableUrl', 'url=' + value + '&' + 'url=' + $("#listRoles").val() + '');
    }
}
}

$("#modulos").change(function()
{
    if($("#modulos").val() != "" && $("#listRoles").val()!= ""){
        $("#jsBck").load('/pemisosRoles/validateModuleStatus', 'rolxmodulo='+$("#listRoles").val()+'&'+'rolxmodulo='+$("#modulos").val()+'');
    }else{
        $("#editarPermisos").load('/pemisosRoles/onlyLoad2Form', 'rolxmodulo='+$("#listRoles").val()+'&'+'rolxmodulo='+$("#modulos").val()+''+'&'+'rolxmodulo='+'0'+'');
        $('#estadoModulo').prop('checked', false);
    }
});

$("#listRoles").change(function()
{
    $("#modulos").val("");
    $("#editarPermisos").load('/pemisosRoles/onlyLoad2Form', 'rolxmodulo='+$("#listRoles").val()+'&'+'rolxmodulo='+$("#modulos").val()+''+'&'+'rolxmodulo='+'0'+'');
    $('#estadoModulo').prop('checked', false);
});



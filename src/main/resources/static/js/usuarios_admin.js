function allowDrop(ev) {
    ev.preventDefault();
}

function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.id);
}

function drop(ev) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("text");
    ev.target.appendChild(document.getElementById(data));
}



function seleccionarElementosDrag() {
    var cantidadSeleccionada = $('#listaString > option').length!=0?$('#listaString > option').length!=0:$('#listaString').children('option').length;
}

function validarRolesSeleccionados() {
    if($("#pre-selected-options").val() !=null && $("#pre-selected-options").val().indexOf('' + idParticipante + '') >= 0){
        $("#codProasecal").prop('disabled', false);
        $("#codProasecal").prop('required',true);
    }else{
        $("#codProasecal").prop('disabled', true);
        $("#codProasecal").prop('required',false);
        $("#codProasecal").val("");
    }
}

$( "#codProasecal" ).keyup(function() {
    this.value = this.value.replace(/[^0-9\.]/g,'');
    if($("#pre-selected-options").val() !=null && $("#pre-selected-options").val().indexOf('' + idParticipante + '') >= 0){
        $("#nombreUsuario").val($("#codProasecal").val());
    }
});

$( "#nombreUsuario" ).keyup(function() {
    if($("#pre-selected-options").val() !=null && $("#pre-selected-options").val().indexOf('' + idParticipante + '') >= 0){
        this.value = this.value.replace(/[^0-9\.]/g,'');
        $("#codProasecal").val($("#nombreUsuario").val());
    }
});
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
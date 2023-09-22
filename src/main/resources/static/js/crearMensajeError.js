// const crearMensajeError = function crearMensajeError(mensaje){
function crearMensajeError(mensaje){
    const divMensajeError = document.getElementById('mensajeError');

    const divAlert = document.createElement('div');
    const spanMensaje = document.createElement('span');
    const botonCerrar = document.createElement('button');

    divAlert.setAttribute('class', 'alert alert-danger alert-dismissible fade show');
    divAlert.setAttribute('role', 'alert');

    botonCerrar.setAttribute('class', 'btn-close');
    botonCerrar.setAttribute('type', 'button');
    botonCerrar.setAttribute('data-bs-dismiss', 'alert');
    botonCerrar.setAttribute('aria-label', 'Close');

    spanMensaje.innerText = mensaje;

    divAlert.appendChild(spanMensaje);
    divAlert.appendChild(botonCerrar);

    divMensajeError.appendChild(divAlert);

    // DESPUES DE 10S QUIERO QUE SE ME CIERRE EL ALERT
    setTimeout(() => {
        if(divMensajeError.hasChildNodes()){
            divMensajeError.removeChild(divMensajeError.lastChild);
        } 
    }, 10000);
}

export default crearMensajeError;
// module.exports = { crearMensajeError };
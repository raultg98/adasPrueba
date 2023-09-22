import crearMensajeError from '../crearMensajeError.js';

const formulario = document.getElementById('formulario');
const comentario = document.getElementById('comentarioMensaje');
const autor = document.getElementById('autorMensaje');
const correo = document.getElementById('correoMensaje');
const btnEnviar = document.getElementById('btnEnviar');

const validaciones = {
    comentario: 'false',
    autor: 'false', 
    correo: 'false'
}

// EVENTOS PARA COMPROBAR LOS CAMBIOS DEL FORMULARIO
comentario.addEventListener('change', (e) => {
    const contenidoMensaje = e.target.value;
    
    if(contenidoMensaje.length > 5){
        validaciones.comentario = 'true';
    }else {
        crearMensajeError('No puedes escribir un comentario tan pequeño');
        validaciones.comentario = 'false';
    }

    console.log("Mensaje: "+ validaciones.comentario +", autor: "+ validaciones.autor +", correo: "+ validaciones.correo);
});

autor.addEventListener('change', (e) => {
    const contenidoAutor = e.target.value;

    if(contenidoAutor.length > 3){
        validaciones.autor = 'true';
    }else {
        crearMensajeError('nombre.length > 3');
        validaciones.autor = 'false';
    }

    console.log("Mensaje: "+ validaciones.comentario +", autor: "+ validaciones.autor +", correo: "+ validaciones.correo);
});

correo.addEventListener('change', (e) => {
    const contenidoCorreo = e.target.value;
    const expReg = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,4})+$/;;

    if(!expReg.test(contenidoCorreo)){
        crearMensajeError('El correo no es valido');
        validaciones.correo = 'false';
    }else {
        validaciones.correo = 'true';
    }

    console.log("Mensaje: "+ validaciones.comentario +", autor: "+ validaciones.autor +", correo: "+ validaciones.correo);
});

formulario.addEventListener('change', (e) => {
    // COMPRUEBO SI YA SE HAN REALIZADO CAMBIOS EN EL FORMULARIO
    if(validaciones.comentario === 'true' && validaciones.autor === 'true' && validaciones.correo === 'true'){
        btnEnviar.disabled = false;
        btnEnviar.style.cursor = 'pointer';
    }else {
        btnEnviar.disabled = true;
        btnEnviar.style.cursor = 'not-allowed';
    }
});


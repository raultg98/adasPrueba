import crearMensajeError from '../crearMensajeError.js';

const formulario = document.getElementById('formulario');
const nombre = document.getElementById('correoNombre');
const apellidos = document.getElementById('correoApellidos');
const correo = document.getElementById('correoCorreo');
const asunto = document.getElementById('correoAsunto');
const mensaje = document.getElementById('correoMensaje');
const btnEnviar = document.getElementById('btnEnviar');

const validaciones = {
    nombre: 'false', 
    apellidos: 'false', 
    correo: 'false', 
    asunto: 'false', 
    mensaje: 'false'
}

nombre.addEventListener('change', (e) => {
    const contenidoNombre = e.target.value;

    if(contenidoNombre.length > 3){
        validaciones.nombre = 'true';
    }else {
        crearMensajeError("Nombre es demasiado corto.");
        validaciones.nombre = 'false';
    }
});

apellidos.addEventListener('change', (e) => {
    const contenidoApellidos = e.target.value;

    if(contenidoApellidos.length > 4){
        validaciones.apellidos = 'true';
    }else {
        crearMensajeError('Los apellidos son muy cortos.');
        validaciones.apellidos = 'false';
    }
});

correo.addEventListener('change', (e) => {
    const contenidoCorreo = e.target.value;
    const expReg = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,4})+$/;;

    if(!expReg.test(contenidoCorreo)){
        crearMensajeError('El correo no es válido');
        validaciones.correo = 'false';
    }else {
        validaciones.correo = 'true';
    }
});

asunto.addEventListener('change', (e) => {
    const contenidoAsunto = e.target.value;

    if(contenidoAsunto.length > 4){
        validaciones.asunto = 'true';
    }else {
        crearMensajeError('Introduce un asunto más grande.');
        validaciones.asunto = 'false';
    }
});

mensaje.addEventListener('change', (e) => {
    const contenidoMensaje = e.target.value;

    if(contenidoMensaje.length > 8){
        validaciones.mensaje = 'true';
    }else {
        crearMensajeError('El Mensaje tiene que tener más contenido.');
        validaciones.mensaje = 'false';
    }
});

formulario.addEventListener('change', (e) => {
    if(validaciones.nombre === 'true' && validaciones.apellidos === 'true' && validaciones.correo === 'true' &&
       validaciones.asunto === 'true' && validaciones.mensaje === 'true'){
        btnEnviar.disabled = false;
        btnEnviar.style.cursor = 'pointer';
    }else {
        btnEnviar.disabled = true;
        btnEnviar.style.cursor = 'not-allowed';
    }
});
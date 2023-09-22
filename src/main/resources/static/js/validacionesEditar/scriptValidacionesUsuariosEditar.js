import crearMensajeError from '../crearMensajeError.js';

const formulario = document.getElementById('formulario');
const nombre = document.getElementById('usuarioNombre');
const correo = document.getElementById('usuarioCorreo');
const contrasenia = document.getElementById('usuarioContrasenia');
const cargo = document.getElementById('usuarioCargo');
const admin = document.getElementById('usuarioAdmin');
const btnEnviar = document.getElementById('btnEnviar');

const validaciones = {
    nombre: 'true', 
    correo: 'true', 
    contrasenia: 'true', 
    cargo: 'true', 
    admin: 'true'
}

nombre.addEventListener('change', (e) => {
    const contenidoNombre = e.target.value;

    if(contenidoNombre.length > 3){
        validaciones.nombre = 'true';
    }else {
        crearMensajeError('EL nombre es demasiado corto, menor de 3')
        validaciones.nombre = 'false';
    }
});

correo.addEventListener('change', (e) => {
    const contenidoCorreo = e.target.value;
    const expReg = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,4})+$/;

    if(!expReg.test(contenidoCorreo)){
        crearMensajeError('El correo no es valido');
        validaciones.correo = 'false';
    }else {
        validaciones.correo = 'true';
    }
});

contrasenia.addEventListener('change', (e) => {
    const contenidoContrasenia = e.target.value;
    
    if(contrasenia.length > 5){
        validaciones.contrasenia = 'true';
    }else {
        crearMensajeError('La contrasenia es demasiado corta');
        validaciones.contrasenia = 'false';
    }
});

cargo.addEventListener('change', (e) => {
    const contenidoCargo = e.target.value;

    if(contenidoCargo.length > 0){
        if(contenidoCargo === 'COORDINADOR' || contenidoCargo === 'ADMINISTRADOR' || contenidoCargo === 'PRESIDENTE'){
            validaciones.cargo = 'true';
        }else {
            crearMensajeError('Seleccione un cargo valido');
            validaciones.cargo = 'false';
        }
    }else {
        crearMensajeError('Seleccione un cargo');
        validaciones.cargo = 'false';
    }
});

admin.addEventListener('change', (e) => {
    const contenidoAdmin = e.target.value;

    if(contenidoAdmin !== null){
        if(contenidoAdmin === 'true' || contenidoAdmin === 'false'){
            validaciones.admin = 'true';
        }else{
            crearMensajeError('El cargo que has seleccionado no es valido');
            validaciones.admin = 'false';
        }
    }else{
        crearMensajeError('No has seleccionado si es admin o no');
        validaciones.admin = 'false';
    }
});

formulario.addEventListener('change', () => {
    if(validaciones.nombre === 'true' && validaciones.correo === 'true' && validaciones.contrasenia === 'true' &&
       validaciones.cargo === 'true' && validaciones.admin === 'true'){
        btnEnviar.disabled = false;
        btnEnviar.style.cursor = 'pointer';
    }else {
        btnEnviar.disabled = true;
        btnEnviar.style.cursor = 'not-allowed';
    }
});


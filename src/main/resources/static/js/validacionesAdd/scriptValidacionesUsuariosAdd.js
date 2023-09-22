import crearMensajeError from '../crearMensajeError.js';

const formulario = document.getElementById('formulario');
const nombre = document.getElementById('usuarioNombre');
const correo = document.getElementById('usuarioCorreo');
const contrasenia = document.getElementById('usuarioContrasenia');
const repetirContrasenia = document.getElementById('usuarioRepContrasenia');
const cargo = document.getElementById('usuarioCargo');
const admin = document.getElementById('usuarioAdmin');
const btnEnviar = document.getElementById('btnEnviar');

const validaciones = {
    nombre: 'false', 
    correo: 'false', 
    contrasenia: 'false', 
    cargo: 'false', 
    admin: 'false'
}

nombre.addEventListener('change', (e) => {
    const contenidoNombre = e.target.value;

    if(contenidoNombre.length > 3){
        validaciones.nombre = 'true';
    }else {
        crearMensajeError('El nombre.length > 3');
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
    const contenidoConstrasenia = e.target.value;
    const contenidoRepContrasenia = repetirContrasenia.value;

    // TAMBIEN SE LE PUEDE MIRAR SI ES UNA CADENA DE TEXTO, QUE TENGA LETRAS.
    if(contenidoConstrasenia.length > 5){
        if(contenidoConstrasenia === contenidoRepContrasenia){
            validaciones.contrasenia = 'true';
        }else {
            crearMensajeError('La contraseña y el repContraseña no coinciden');
            validaciones.contrasenia = 'false';
        }
    }else {
        crearMensajeError('La contrasenia.length > 5');
        validaciones.contrasenia = 'false';
    }
});

repetirContrasenia.addEventListener('change', (e) => {
    const contenidoRepContrasenia = e.target.value;
    const contenidoContrasenia = contrasenia.value;

    if(contenidoContrasenia === contenidoRepContrasenia){
        validaciones.contrasenia = 'true';
    }else {
        crearMensajeError('La contraseña y el repContraseña no coindice');
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
        }else {
            validaciones.admin = 'false';
            crearMensajeError('El cargo que has seleccionado no es valido');
        }
    }else {
        validaciones.admin = 'false';
        crearMensajeError('No has seleccionado si el usuario es admin o no');
    }
});

formulario.addEventListener('change', (e) => {
    if(validaciones.nombre === 'true' && validaciones.correo === 'true' && validaciones.contrasenia === 'true' &&
        validaciones.cargo === 'true' && validaciones.admin === 'true'){
        btnEnviar.disabled = false;
        btnEnviar.style.cursor = 'pointer';
    }else {
        btnEnviar.disabled = true;
        btnEnviar.style.cursor = 'not-allowed';
    }
});
import crearMensajeError from '../crearMensajeError.js';

const formulario = document.getElementById('formulario');
const nombre = document.getElementById('actividadNombre');
const ubicacion = document.getElementById('actividadUbicacion');
const fecha = document.getElementById('actividadFecha');
const btnEnviar = document.getElementById('btnEnviar');

const validaciones = {
    nombre: 'false',
    ubicacion: 'false',
    fecha: 'false'
}


nombre.addEventListener('change', (e) => {
    const contenidoNombre = e.target.value;

    if(contenidoNombre.length > 4){
        validaciones.nombre = 'true';
    }else {
        crearMensajeError('El nombre es demasiado corto');
        validaciones.nombre = 'false';
    }
});

ubicacion.addEventListener('change', (e) => {
    const contenidoUbicacion = e.target.value;

    if(contenidoUbicacion.length > 3){
        validaciones.ubicacion = 'true';
    }else {
        crearMensajeError('La ubicacion es demasido corta');
        validaciones.ubicacion = 'false';
    }
});

fecha.addEventListener('blur', (e) => {
    const contenidoFecha = e.target.value;
    const contenidoAnio = parseInt(contenidoFecha.split('-')[0]);
    const anioActual = new Date().getFullYear();

    if(contenidoAnio >= 2014 && contenidoAnio <= anioActual){
        validaciones.fecha = 'true';
    }else {
        crearMensajeError('El año introducido no es válido, (2014 - Actual)');
        validaciones.fecha = 'false';
    }
});

formulario.addEventListener('change', (e) => {
    if(validaciones.nombre === 'true' && validaciones.ubicacion === 'true' && validaciones.fecha === 'true'){
        btnEnviar.disabled = false;
        btnEnviar.style.cursor = 'pointer';
    }else {
        btnEnviar.disabled = true;
        btnEnviar.style.cursor = 'not-allowed';
    }
});
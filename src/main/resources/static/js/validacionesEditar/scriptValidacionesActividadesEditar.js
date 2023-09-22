import crearMensajeError from "../crearMensajeError";

const formulario = document.getElementById('formulario');
const nombre = document.getElementById('actividadNombre');
const ubicacion = document.getElementById('actividadUbicacion');
const fecha = document.getElementById('actividadFecha');
const btnEnviar = document.getElementById('btnEnviar');

const validaciones = {
    nombre: 'true', 
    ubicacion: 'true', 
    fecha: 'true'
}

nombre.addEventListener('change', (e) => {
    const contenidoNombre = e.target.value;

    if(contenidoNombre.length > 4){
        validaciones.nombre = 'true';
    }else {
        crearMensajeError('El nombre es demasiado corto');
    }
});

ubicacion.addEventListener('change', (e) => {
    const contenidoUbicacion = e.target.value;

    if(contenidoUbicacion.length > 3){
        validaciones.ubicacion = 'true';
    }else {
        crearMensajeError('La ubicación es demasiado corta');
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
        crearMensajeError('El año introducido no es valido, (2014 - ACTUAL)');
        validaciones.fecha = 'false';
    }
});

formulario.addEventListener('change', () => {
    if(validaciones.nombre === 'true' && validaciones.ubicacion === 'true' && validaciones.fecha === 'true'){
        btnEnviar.disabled = false;
        btnEnviar.style.cursor = 'pointer';
    }else {
        btnEnviar.disabled = true;
        btnEnviar.style.cursor = 'not-allowed';
    }
});
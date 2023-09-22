import crearMensajeError from "../crearMensajeError.js";

const formulario = document.getElementById('formulario');
const titulo = document.getElementById('noticiaTitulo');
const descripcion = document.getElementById('noticiaDescripcion');
const imagen = document.getElementById('noticiaImagen');
const usuario = document.getElementById('noticiaUsuarioId');
const btnEnviar = document.getElementById('btnEnviar');

const validaciones = {
    titulo: 'true', 
    descripcion: 'true', 
    imagen: 'true', 
    usuario: 'true'
}

titulo.addEventListener('change', (e) => {
    const contenidoTitulo = e.target.value;

    if(contenidoTitulo.length > 5){
        validaciones.titulo = 'true';
    }else {
        crearMensajeError('El titulo es demasiado pequeño');
        validaciones.titulo = 'false';
    }
});

descripcion.addEventListener('change', (e) => {
    const contenidoDescripcion = e.target.value;

    if(contenidoDescripcion.length > 5){
        validaciones.descripcion = 'true';
    }else {
        crearMensajeError('La descripción es demasiado corta');
        validaciones.descripcion = 'false';
    }
});

imagen.addEventListener('change', (e) => {
    const arrayArchivo = e.target.files[0].name.split('.');
    const extensionArchivo = arrayArchivo[arrayArchivo.length-1];
    const expReg = /png|jpeg|jpg/i;

    if(!expReg.test(extensionArchivo)){
        crearMensajeError('La extension del archivo no es valida (png/jpeg/jpg), '+ extensionArchivo);
        validaciones.imagen = 'false';
    }else{
        validaciones.imagen = 'true';
    }

    let reader = new FileReader();
    reader.readAsDataURL(e.target.files[0]);

    reader.onload = function() {
        const img = document.getElementById('noticiaImagenNueva');

        img.src = reader.result;
    }
});

usuario.addEventListener('change', (e) => {
    const contenidoUsuario = e.target.value;

    if(contenidoUsuario != null){
        validaciones.usuario = 'true';
    }else {
        validaciones.usuario = 'false';
    }
});

formulario.addEventListener('change', () => {
    if(validaciones.titulo === 'true' && validaciones.descripcion === 'true' &&
       validaciones.imagen === 'true' && validaciones.usuario === 'true'){
        btnEnviar.disabled = false;
        btnEnviar.style.cursor = 'pointer';
    }else {
        btnEnviar.disabled = true;
        btnEnviar.style.cursor = 'not-allowed';
    }
});
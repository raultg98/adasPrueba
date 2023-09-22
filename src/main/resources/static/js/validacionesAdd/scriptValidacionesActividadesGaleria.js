import crearMensajeError from "../crearMensajeError.js";

const imagenPreview = document.getElementById('imagenPreview');
const formulario = document.getElementById('formulario');
const imagen = document.getElementById('imagenAdd');
const btnEnviar = document.getElementById('btnEnviar');

const validaciones = {
    imagen: 'false'
}

imagen.addEventListener('change', (e) => {
    const cantidadArchivos = e.target.files.length;

    if(imagenPreview.hasChildNodes){
        let numeroHijos = imagenPreview.childNodes.length;

        for(let i=0; i<numeroHijos; i++){
            imagenPreview.removeChild(imagenPreview.lastChild);
        }
    }

    for(let i=0; i<cantidadArchivos; i++){
        const arrayArchivo =  e.target.files[i].name.split('.');
        const extensionArchivo = arrayArchivo[arrayArchivo.length-1];
        const expReg = /png|jpeg|jpg/i;

        if(!expReg.test(extensionArchivo)){
            crearMensajeError('El archivo no es válido, tipo de archivo incorrecto (png, jpeg, jpg), '+ extensionArchivo);
            validaciones.imagen = 'false';
        }else {
            validaciones.imagen = 'true';

            let reader = new FileReader();
            reader.readAsDataURL(e.target.files[i]);

            reader.onload = function(){
                const img = document.createElement('img');

                img.src = reader.result;

                imagenPreview.appendChild(img);
            }
        }
    }
});

formulario.addEventListener('change', (e) => {
    if(validaciones.imagen === 'true'){
        btnEnviar.disabled = false;
        btnEnviar.style.cursor = 'pointer';
    }else {
        btnEnviar.disabled = true;
        btnEnviar.style.cursor = 'not-allowed';
    }
});
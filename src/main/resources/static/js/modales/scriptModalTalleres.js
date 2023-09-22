const modal = document.getElementById('miModal');
const modalImg = document.getElementById('miModalImg');
const boton = document.getElementById('close');
const botonAmpliar = document.querySelector('.botonAmpliar');

botonAmpliar.addEventListener('click', () => {
    let nombreImagenSplit = botonAmpliar.parentNode.parentNode.firstElementChild.src.split('/');
    let nombreImagen = nombreImagenSplit[nombreImagenSplit.length-1];

    modal.style.display = 'block';
    modalImg.src = '/img/talleres/'+ nombreImagen;
});

boton.addEventListener('click', () => {
    modal.style.display = 'none';
});
const modal = document.getElementById("miModal");
const modalImg = document.getElementById('miModalImg');
const boton = document.getElementById('close');
const botonAmpliar = document.querySelectorAll('.botonAmpliar');

for(let i=0; i<botonAmpliar.length; i++){
    botonAmpliar[i].addEventListener('click', (e) => {
        // console.log(botonAmpliar[i].parentNode.parentNode.firstElementChild.src);
        let nombreImagenSplit = botonAmpliar[i].parentNode.parentNode.firstElementChild.src.split('/');
        let nombreImagen = nombreImagenSplit[nombreImagenSplit.length-1];
        
        modal.style.display = 'block';
        modalImg.src = '/img/galeria/'+ nombreImagen;
    });
}

boton.addEventListener('click', () => {
    modal.style.display = 'none';
});
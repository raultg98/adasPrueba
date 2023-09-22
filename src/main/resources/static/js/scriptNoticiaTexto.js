// const descripcion = document.getElementById('descripcion');
const descripcionAll = document.querySelectorAll('.descripcion');

for(let j=0; j<descripcionAll.length; j++){
    const descripcionTexto = descripcionAll[j].textContent;

    const arraySaltoLinea = descripcionTexto.split('$saltoLinea');
    const arrayNegrita = descripcionTexto.split('$negrita');
    const arrayCursiva = descripcionTexto.split('$cursiva');
    const arraySubrayado = descripcionTexto.split('$subrayado');

    let insertar = '';

    if(arrayNegrita.length !== 1){
        let arrayNegrita2 = descripcionTexto.split('$negrita');

        for(let i=0; i<arrayNegrita2.length; i++){
            if(i === 0){
                insertar = arrayNegrita2[0];
            }else if(i !== 0){
                let arraySplitFinNegrita = arrayNegrita2[i].split('$finNegrita');
                insertar += '<strong>'+ arraySplitFinNegrita[0] +'</strong>'+  arraySplitFinNegrita[1];
            }
        }
    }else {
        insertar = descripcionTexto;
    }

    if(arrayCursiva.length !== 1){
        let arrayCursiva2 = insertar.split('$cursiva');

        for(let i=0; i<arrayCursiva2.length; i++){
            if(i === 0){
                insertar = arrayCursiva2[0];
            }else {
                let arraySplitFinCursiva = arrayCursiva2[i].split('$finCursiva');
                insertar += '<i>'+ arraySplitFinCursiva[0] +'</i>'+ arraySplitFinCursiva[1];
            }
        }
    }

    if(arraySubrayado.length !== 1){
        let arraySubrayado2 = insertar.split('$subrayado');

        for(let i=0; i<arraySubrayado2.length; i++){
            if(i === 0){
                insertar = arraySubrayado2[0];
            }else {
                let arraySplitFinSubrayado = arraySubrayado2[i].split('$finSubrayado');
                insertar += '<u>'+ arraySplitFinSubrayado[0] +'</u>'+ arraySplitFinSubrayado[1];
            }
        }
    }

    if(arraySaltoLinea.length !== 1){
        let arraySaltoLinea2 = insertar.split('$saltoLinea');

        for(let i=0; i<arraySaltoLinea2.length; i++){
            if(i === 0){
                insertar = arraySaltoLinea2[0] +'<br>';
            }
            else if(i !== arraySaltoLinea2.length-1){  
                insertar += arraySaltoLinea2[i] +'<br>';
            }else {
                insertar += arraySaltoLinea2[i];
            }
        }
    }

    if(arrayNegrita.length === 1 && arrayCursiva.length === 1 && arraySaltoLinea.length === 1){
        insertar = descripcionTexto;
    }

    descripcionAll[j].innerHTML = insertar;
}

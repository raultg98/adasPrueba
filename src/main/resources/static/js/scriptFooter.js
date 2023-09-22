const footer = document.querySelector('footer');
const main = document.querySelector('main');

let alturaFooter = footer.offsetHeight;

footer.style.marginTop = alturaFooter * -1 +'px';
main.style.paddingBottom = alturaFooter +'px';
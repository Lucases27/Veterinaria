function Desactivar(){
        document.getElementById("fecha").disabled=true;
        document.getElementById("hora").disabled=true;
}
function Activar(){
    document.getElementById("fecha").disabled=false;
    document.getElementById("hora").disabled=false;
}
function MostrarIni(){
    document.getElementById("faq").style.display="block";
    document.getElementById("secreg").style.display="none";
}
function MostrarReg(){
    document.getElementById("secreg").style.display="block";
    document.getElementById("faq").style.display="none";
}


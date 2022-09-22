function writeWhatIamTyping(){
    var input =  document.getElementById("whatIamTyping");
    var text = document.getElementById("typeing");

    var inputStr = input.value;
    text.innerHTML=inputStr;

}
function changeColor(){
    var btnSearch = document.getElementById("btnSearch");
    btnSearch.style = "background-color:red";
}
function showHide(){
    var btnSearch = document.getElementById("btnSearch");
    if(btnSearch.visible){
        btnSearch.visible = false;
    btnSearch.style = "display:none";
} else {
        btnSearch.visible = true;
        btnSearch.style = "display:block";
    }
}
function setIdForDelete(id){
    var elem = document.getElementById("idforDelete");
    elem.value = id;

}
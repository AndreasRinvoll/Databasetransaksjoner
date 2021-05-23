function registrerOrdre(){
    const ordre = {
        fornavn : $("#fornavn").val().trim(),
        etternavn : $("#etternavn").val().trim(),
        adresse : $("#adresse").val().trim(),
        varenavn : $("#varenavn").val().trim(),
        pris : $("#pris").val().trim()
    };

    $.post("/lagre", ordre, function (){

    });
}
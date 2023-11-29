window.addEventListener("load", function () {
    const formulario = document.forms[0];
    const url = "http://localhost:8080/odontologos/registrar";
    formulario.addEventListener("submit", function (e) {
        e.preventDefault();
        registrarOdontologo();
    });

    function capturarDatos() {
        const matricula = document.querySelector("#matricula");
        const nombre = document.querySelector("#nombre");
        const apellido = document.querySelector("#apellido");



    const odontologo = {
        matricula: matricula.value,
        nombre: nombre.value,
        apellido: apellido.value,

    };
    return odontologo;
    }

    function registrarOdontologo() {
        const data = capturarDatos();

        const config = {
        method: "POST",
        body: JSON.stringify(data),
        headers: {
        "Content-type": "application/json; charset=UTF-8",
        },
    };

    fetch(url, config)
        .then((res) =>
        res.json())
        .then((data) => {
        console.log(data);
        });

    formulario.reset();
    }
});
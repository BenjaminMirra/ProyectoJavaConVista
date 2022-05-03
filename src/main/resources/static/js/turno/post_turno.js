window.addEventListener('load', function () {

    const formulario = document.querySelector('#add_new_turno');
    formulario.addEventListener('submit', function (event) {
        event.preventDefault();
        const formData = ({
                    fecha: document.querySelector('#fecha').value,
                    odontologo:{
                        id:document.querySelector('#odontologoID').value
                    },
                    paciente: {
                        id: document.querySelector('#pacienteID').value
                    }
         });

        const url = '/turnos';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                 let successAlert = '<div class="alert alert-success alert-dismissible">' +
                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                     '<strong></strong> Turno agregado </div>'

                 document.querySelector('#response').innerHTML = successAlert;
                 document.querySelector('#response').style.display = "block";
                 resetUploadForm();

            })
            .catch(error => {
                    let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                     '<strong> Error intente nuevamente</strong> </div>'

                      document.querySelector('#response').innerHTML = errorAlert;
                      document.querySelector('#response').style.display = "block";
                     resetUploadForm();})
    });

    function resetUploadForm(){
        document.querySelector('#fecha').value = "";
        document.querySelector('#odontologoID').value = "";
        document.querySelector('#pacienteID').value = "";

    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/turnos.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();
});
// Ejercicio 4: Selects Dependientes en Formulario

document.addEventListener('DOMContentLoaded', () => {
    const provinciaSelect = document.getElementById('provinciaSelect');
    const localidadSelect = document.getElementById('localidadSelect');
    const selectForm = document.getElementById('selectForm');
    const provinciaError = document.getElementById('provinciaError');
    const localidadError = document.getElementById('localidadError');

    const localidadesPorProvincia = {
        Huesca: ["Huesca Capital", "Jaca", "Barbastro"],
        Zaragoza: ["Zaragoza Capital", "Calatayud", "Ejea de los caballeros"],
        Teruel: ["Teruel Capital", "Alcañiz", "Calamocha"]
    };

    function actualizarLocalidades() {
        const provinciaSeleccionada = provinciaSelect.value;
        localidadSelect.innerHTML = '<option value="">Selecciona una localidad</option>'; // Resetear

        if (provinciaSeleccionada) {
            const localidades = localidadesPorProvincia[provinciaSeleccionada];
            localidades.forEach(localidad => {
                const option = document.createElement('option');
                option.value = localidad;
                option.textContent = localidad;
                localidadSelect.appendChild(option);
            });

            // Si la provincia es Teruel, seleccionar "Alcañiz" por defecto
            if (provinciaSeleccionada === "Teruel") {
                localidadSelect.value = "Alcañiz";
            }
        }
    }

    // Evento para actualizar localidades cuando cambia la provincia
    provinciaSelect.addEventListener('change', actualizarLocalidades);

    // Inicializar localidades al cargar la página (por si hay una provincia preseleccionada)
    actualizarLocalidades();

    // Validación al enviar el formulario
    selectForm.addEventListener('submit', (event) => {
        let valido = true;

        if (!provinciaSelect.value) {
            provinciaError.textContent = "Debes seleccionar una provincia.";
            valido = false;
        } else {
            provinciaError.textContent = "";
        }

        if (!localidadSelect.value) {
            localidadError.textContent = "Debes seleccionar una localidad.";
            valido = false;
        } else {
            localidadError.textContent = "";
        }

        if (!valido) {
            event.preventDefault(); // Cancelar el envío
            alert("Por favor, completa todas las selecciones.");
        } else {
            alert(`Formulario enviado: Provincia - ${provinciaSelect.value}, Localidad - ${localidadSelect.value}`);
            // Aquí se podría enviar el formulario realmente
        }
    });
});

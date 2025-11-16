// Ejercicio 8: Formulario Completo con Validación

document.addEventListener('DOMContentLoaded', () => {
    const registroForm = document.getElementById('registroForm');

    // Elementos del formulario
    const nombreInput = document.getElementById('nombre');
    const apellidosInput = document.getElementById('apellidos');
    const emailInput = document.getElementById('email');
    const poblacionInput = document.getElementById('poblacion');
    const provinciaInput = document.getElementById('provincia');
    const edadInput = document.getElementById('edad');
    const conocisteCheckboxes = document.querySelectorAll('input[name="conociste"]');
    const opinionTextarea = document.getElementById('opinion');
    const opinionContador = document.getElementById('opinionContador');

    // Elementos de error
    const nombreError = document.getElementById('nombreError');
    const apellidosError = document.getElementById('apellidosError');
    const emailError = document.getElementById('emailError');
    const poblacionError = document.getElementById('poblacionError');
    const provinciaError = document.getElementById('provinciaError');
    const edadError = document.getElementById('edadError');
    const conocisteError = document.getElementById('conocisteError');
    const opinionError = document.getElementById('opinionError');

    // Función para validar solo texto (letras y espacios)
    function validarSoloTexto(valor) {
        const regex = /^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+$/;
        return regex.test(valor.trim());
    }

    // Función para validar email
    function validarEmail(email) {
        const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return regex.test(email);
    }

    // Actualizar contador de caracteres para opinión
    opinionTextarea.addEventListener('input', () => {
        const caracteresActuales = opinionTextarea.value.length;
        opinionContador.textContent = `${caracteresActuales}/150`;
        if (caracteresActuales > 150) {
            opinionContador.style.color = 'red';
        } else {
            opinionContador.style.color = 'gray';
        }
    });

    // Validación al enviar el formulario
    registroForm.addEventListener('submit', (event) => {
        event.preventDefault(); // Prevenir el envío por defecto
        let valido = true;

        // Resetear mensajes de error
        document.querySelectorAll('.error').forEach(span => span.textContent = '');

        // 1. Nombre y apellido
        if (nombreInput.value.trim() === '') {
            nombreError.textContent = 'El nombre es obligatorio.';
            valido = false;
        } else if (!validarSoloTexto(nombreInput.value)) {
            nombreError.textContent = 'El nombre solo puede contener letras y espacios.';
            valido = false;
        }

        if (apellidosInput.value.trim() === '') {
            apellidosError.textContent = 'Los apellidos son obligatorios.';
            valido = false;
        } else if (!validarSoloTexto(apellidosInput.value)) {
            apellidosError.textContent = 'Los apellidos solo pueden contener letras y espacios.';
            valido = false;
        }

        // 2. Correo electrónico
        if (emailInput.value.trim() === '') {
            emailError.textContent = 'El correo electrónico es obligatorio.';
            valido = false;
        } else if (!validarEmail(emailInput.value)) {
            emailError.textContent = 'El formato del correo electrónico es incorrecto.';
            valido = false;
        }

        // 3. Población
        if (poblacionInput.value.trim() === '') {
            poblacionError.textContent = 'La población es obligatoria.';
            valido = false;
        } else if (!validarSoloTexto(poblacionInput.value)) {
            poblacionError.textContent = 'La población solo puede contener letras y espacios.';
            valido = false;
        }

        // 4. Provincia
        if (provinciaInput.value.trim() === '') {
            provinciaError.textContent = 'La provincia es obligatoria.';
            valido = false;
        } else if (!validarSoloTexto(provinciaInput.value)) {
            provinciaError.textContent = 'La provincia solo puede contener letras y espacios.';
            valido = false;
        }

        // 5. Edad
        const edad = parseInt(edadInput.value, 10);
        if (isNaN(edad) || edadInput.value.trim() === '') {
            edadError.textContent = 'La edad es obligatoria y debe ser un número.';
            valido = false;
        } else if (edad < 18 || edad > 100) {
            edadError.textContent = 'La edad debe estar entre 18 y 100 años.';
            valido = false;
        }

        // 6. Cómo nos conociste (Checkbox)
        let conocisteSeleccionado = false;
        conocisteCheckboxes.forEach(checkbox => {
            if (checkbox.checked) {
                conocisteSeleccionado = true;
            }
        });
        if (!conocisteSeleccionado) {
            conocisteError.textContent = 'Debes seleccionar al menos una opción.';
            valido = false;
        }

        // 7. Opinión y sugerencias (solo longitud)
        if (opinionTextarea.value.length > 150) {
            opinionError.textContent = 'La opinión no puede exceder los 150 caracteres.';
            valido = false;
        }

        // Si todo es válido, se podría enviar el formulario
        if (valido) {
            alert('Formulario enviado con éxito!');
            registroForm.reset(); // Limpiar el formulario
            opinionContador.textContent = '0/150'; // Resetear contador
            document.querySelectorAll('.error').forEach(span => span.textContent = ''); // Limpiar errores
        } else {
            alert('Por favor, corrige los errores del formulario.');
        }
    });
});

// Ejercicio 1: Validación de DNI en Formulario

document.addEventListener('DOMContentLoaded', () => {
    const dniInput = document.getElementById('dniInput');
    const dniError = document.getElementById('dniError');
    const dniForm = document.getElementById('dniForm');

    const letrasDNI = ['T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'];

    // Helper function for DNI/NIE letter calculation
    function _validarDNI_NIE_Logic(numero, letra) {
        const letraCalculada = letrasDNI[numero % 23];
        if (letra === letraCalculada) {
            return { valido: true, mensaje: "" };
        } else {
            return { valido: false, mensaje: `La letra '${letra}' no corresponde al número. La letra correcta es '${letraCalculada}'.` };
        }
    }

    function validarDocumentoIdentidad(doc) {
        doc = doc.toUpperCase(); // Ensure uppercase for validation

        // Try DNI format
        const regexDNI = /^(\d{7,8})([A-Z])$/;
        let match = doc.match(regexDNI);

        if (match) {
            const numero = parseInt(match[1], 10);
            const letra = match[2];
            return _validarDNI_NIE_Logic(numero, letra);
        }

        // Try NIE format
        const regexNIE = /^[XYZ](\d{7})([A-Z])$/;
        match = doc.match(regexNIE);

        if (match) {
            let niePrefix = doc[0];
            let numeroStr = match[1];
            const letra = match[2];

            let numero;
            if (niePrefix === 'X') {
                numero = parseInt('0' + numeroStr, 10);
            } else if (niePrefix === 'Y') {
                numero = parseInt('1' + numeroStr, 10);
            } else if (niePrefix === 'Z') {
                numero = parseInt('2' + numeroStr, 10);
            } else {
                // Should not happen due to regexNIE
                return { valido: false, mensaje: "Formato de NIE incorrecto." };
            }
            return _validarDNI_NIE_Logic(numero, letra);
        }

        return { valido: false, mensaje: "Formato de DNI/NIE incorrecto (ej: 12345678A o X1234567B)." };
    }

    // Function to update UI feedback
    function updateUIFeedback(inputElement, errorElement, isValid, message) {
        errorElement.textContent = message;
        if (isValid) {
            inputElement.classList.remove('invalid');
            inputElement.classList.add('valid');
        } else {
            inputElement.classList.remove('valid');
            inputElement.classList.add('invalid');
        }
    }

    // Real-time validation on input and blur
    dniInput.addEventListener('input', () => {
        const resultado = validarDocumentoIdentidad(dniInput.value);
        updateUIFeedback(dniInput, dniError, resultado.valido, resultado.mensaje);
    });

    dniInput.addEventListener('blur', () => {
        const resultado = validarDocumentoIdentidad(dniInput.value);
        updateUIFeedback(dniInput, dniError, resultado.valido, resultado.mensaje);
    });

    // Validation on form submission
    dniForm.addEventListener('submit', (event) => {
        const resultado = validarDocumentoIdentidad(dniInput.value);
        if (!resultado.valido) {
            event.preventDefault(); // Cancelar el envío del formulario
            updateUIFeedback(dniInput, dniError, false, resultado.mensaje);
            alert("Por favor, corrige el DNI/NIE antes de enviar.");
        } else {
            updateUIFeedback(dniInput, dniError, true, "");
            alert("DNI/NIE válido. Formulario enviado.");
            // Aquí se podría enviar el formulario realmente si no fuera un ejemplo
            // event.target.submit();
        }
    });
});
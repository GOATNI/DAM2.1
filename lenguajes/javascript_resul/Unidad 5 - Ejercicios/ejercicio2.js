// Ejercicio 2: Validación de Email en Formulario

// Ejercicio 2: Validación de Email en Formulario

document.addEventListener('DOMContentLoaded', () => {
    const emailInput = document.getElementById('emailInput');
    const emailError = document.getElementById('emailError');
    const emailForm = document.getElementById('emailForm');

    const addServerForm = document.getElementById('addServerForm');
    const servidorInput = document.getElementById('serverInput');
    const serverError = document.getElementById('serverError');
    const serverList = document.getElementById('serverList'); // Changed from 'ul' to 'serverList' for clarity

    const listaServidores = ["terra.es", "google.com", "marca.es", "yahoo.es"];

    // Function to update UI feedback for email input
    function updateEmailUIFeedback(inputElement, errorElement, isValid, message) {
        errorElement.textContent = message;
        if (isValid) {
            inputElement.classList.remove('invalid');
            inputElement.classList.add('valid');
        } else {
            inputElement.classList.remove('valid');
            inputElement.classList.add('invalid');
        }
    }

    function validarEmail(email) {
        // 2-a) Validación de formato básico: texto@servidor.loquesea
        const regexFormato = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!regexFormato.test(email)) {
            return { valido: false, mensaje: "Formato de email incorrecto (ej: usuario@dominio.com)." };
        }

        // 2-b) Comprobar si el servidor está en la lista admitida
        const partesEmail = email.split('@');
        const dominioCompleto = partesEmail[1]; // ej: "dominio.com"

        if (!listaServidores.includes(dominioCompleto)) {
            return { valido: false, mensaje: `El dominio '${dominioCompleto}' no está en la lista de servidores admitidos.` };
        }

        return { valido: true, mensaje: "" };
    }

    // Real-time validation on input and blur for email
    emailInput.addEventListener('input', () => {
        const resultado = validarEmail(emailInput.value.toLowerCase());
        updateEmailUIFeedback(emailInput, emailError, resultado.valido, resultado.mensaje);
    });

    emailInput.addEventListener('blur', () => {
        const resultado = validarEmail(emailInput.value.toLowerCase());
        updateEmailUIFeedback(emailInput, emailError, resultado.valido, resultado.mensaje);
    });

    // Validación al enviar el formulario de email
    emailForm.addEventListener('submit', (event) => {
        const resultado = validarEmail(emailInput.value.toLowerCase());
        if (!resultado.valido) {
            event.preventDefault(); // Cancelar el envío del formulario
            updateEmailUIFeedback(emailInput, emailError, false, resultado.mensaje);
            alert("Por favor, corrige el email antes de enviar.");
        } else {
            updateEmailUIFeedback(emailInput, emailError, true, "");
            alert("Email válido. Formulario enviado.");
            // Aquí se podría enviar el formulario realmente si no fuera un ejemplo
            // event.target.submit();
        }
    });

    // --- Server Management Functions ---

    function validarServidor(servidor) {
        const regexServidor = /^[^\s@]+\.[^\s@]+$/;
        if (!regexServidor.test(servidor)) {
            return { valido: false, mensaje: "Formato del servidor inválido (ej: dominio.com)" };
        }

        if (listaServidores.includes(servidor)) {
            return { valido: false, mensaje: `El servidor '${servidor}' ya está en la lista.` };
        }

        return { valido: true, mensaje: "" };
    }

    addServerForm.addEventListener('submit', (event) => {
        event.preventDefault();
        const servidor = servidorInput.value.toLowerCase().trim();
        const resultado = validarServidor(servidor);

        if (!resultado.valido) {
            serverError.textContent = resultado.mensaje;
            // alert("❌ No se pudo añadir el servidor."); // Removed alert as per project instructions (consider adding visual confirmation)
        } else {
            serverError.textContent = "";
            listaServidores.push(servidor);
            actualizarLista();
            servidorInput.value = "";
            // alert("✅ Servidor añadido correctamente."); // Removed alert as per project instructions (consider adding visual confirmation)
            // Optional: Add a temporary visual confirmation here
        }
    });

    function actualizarLista() {
        serverList.innerHTML = ""; // limpiar lista antes de volver a llenar
        listaServidores.forEach(servidor => {
            const li = document.createElement("li");
            li.textContent = servidor;
            serverList.appendChild(li);
        });
    }

    // Initial update of the server list when the page loads
    actualizarLista();
});

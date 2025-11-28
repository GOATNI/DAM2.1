// Ejercicio 2: Validación de Email en Formulario
document.addEventListener('DOMContentLoaded', () => {
    const emailInput = document.getElementById('emailInput');
    const emailError = document.getElementById('emailError');
    const emailForm = document.getElementById('emailForm');

    // Elementos para añadir servidores
    const servidorInput = document.getElementById('addserver');
    const addServerForm = document.getElementById('AñadirServidores');
    const serverError = document.getElementById('serverError');
    const ul = document.getElementById('list-inline');

    const listaServidores = ["terra.es", "google.com", "marca.es", "yahoo.es"];

    // ---- Mostrar la lista al cargar ----
    actualizarLista();

    // ---------------- EMAIL ----------------
    function validarEmail(email) {
        const regexFormato = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!regexFormato.test(email)) {
            return { valido: false, mensaje: "Formato de email incorrecto (ej: usuario@dominio.com)." };
        }

        const dominioCompleto = email.split('@')[1];
        if (!listaServidores.includes(dominioCompleto)) {
            return { valido: false, mensaje: `El dominio '${dominioCompleto}' no está en la lista de servidores admitidos.` };
        }

        return { valido: true, mensaje: "" };
    }

    // ---------------- SERVIDORES ----------------
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
            alert("❌ No se pudo añadir el servidor.");
        } else {
            serverError.textContent = "";
            listaServidores.push(servidor);
            actualizarLista();
            servidorInput.value = "";
            alert("✅ Servidor añadido correctamente.");
        }
    });

    // ---------------- LISTA ----------------
    function actualizarLista() {
        ul.innerHTML = ""; // limpiar lista antes de volver a llenar
        for (let i = 0; i < listaServidores.length; i++) {
            const li = document.createElement("li");
            li.textContent = listaServidores[i];
            ul.appendChild(li);
        }
    }

    // ---------------- FORMULARIO DE EMAIL ----------------
    emailForm.addEventListener('submit', (event) => {
        const resultado = validarEmail(emailInput.value.toLowerCase());
        if (!resultado.valido) {
            event.preventDefault();
            emailError.textContent = resultado.mensaje;
            alert("Por favor, corrige el email antes de enviar.");
        } else {
            emailError.textContent = "";
            alert("Email válido. Formulario enviado correctamente.");
        }
    });
});
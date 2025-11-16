// Ejercicio 3: Validación de Anagramas en Formulario

document.addEventListener('DOMContentLoaded', () => {
    const campo1Input = document.getElementById('campo1');
    const campo2Input = document.getElementById('campo2');
    const anagramaError = document.getElementById('anagramaError');
    const anagramaForm = document.getElementById('anagramaForm');

    function limpiarYOrdenar(cadena) {
        // Convertir a minúsculas, quitar espacios y acentos, y ordenar caracteres
        return cadena
            .toLowerCase()
            .normalize("NFD") // Descompone caracteres acentuados en su base y el acento
            .replace(/[\u0300-\u036f]/g, "") // Elimina los acentos
            .replace(/\s/g, '') // Quita espacios
            .split('')
            .sort()
            .join('');
    }

    function sonAnagramas(cadena1, cadena2) {
        if (cadena1.length === 0 || cadena2.length === 0) {
            return false; // Cadenas vacías no son anagramas válidos para este ejercicio
        }
        return limpiarYOrdenar(cadena1) === limpiarYOrdenar(cadena2);
    }

    // Validación al enviar el formulario
    anagramaForm.addEventListener('submit', (event) => {
        const valorCampo1 = campo1Input.value;
        const valorCampo2 = campo2Input.value;

        if (sonAnagramas(valorCampo1, valorCampo2)) {
            anagramaError.textContent = "";
            alert("¡Son anagramas! Formulario enviado.");
            // Aquí se podría enviar el formulario realmente
        } else {
            event.preventDefault(); // Cancelar el envío del formulario
            anagramaError.textContent = "Los campos no son anagramas entre sí.";
            alert("Por favor, asegúrate de que los campos sean anagramas.");
        }
    });
});

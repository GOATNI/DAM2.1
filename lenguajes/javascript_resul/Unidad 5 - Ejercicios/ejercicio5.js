// Ejercicio 5: Límite de caracteres en Textarea

document.addEventListener('DOMContentLoaded', () => {
    const textarea = document.getElementById('miTextarea');
    const contador = document.getElementById('contador');
    const limiteCaracteres = 200;

    function actualizarContador() {
        const caracteresActuales = textarea.value.length;
        contador.textContent = `${caracteresActuales} / ${limiteCaracteres} caracteres`;

        if (caracteresActuales >= limiteCaracteres) {
            contador.classList.add('limite-alcanzado');
        } else {
            contador.classList.remove('limite-alcanzado');
        }
    }

    // Inicializar contador
    actualizarContador();

    // Evento para actualizar el contador en cada pulsación de tecla
    textarea.addEventListener('input', actualizarContador);

    // Evento para controlar las teclas cuando se alcanza el límite
    textarea.addEventListener('keydown', (event) => {
        const caracteresActuales = textarea.value.length;

        // Permitir siempre Backspace (8), Delete (46), y las flechas (37-40)
        // También permitir Ctrl/Cmd + A, C, V, X (seleccionar todo, copiar, pegar, cortar)
        const esTeclaEspecial = [8, 46, 37, 38, 39, 40].includes(event.keyCode);
        const esCombinacionCtrl = event.ctrlKey || event.metaKey; // metaKey para Cmd en Mac

        if (caracteresActuales >= limiteCaracteres && !esTeclaEspecial && !esCombinacionCtrl) {
            // Si se ha alcanzado el límite y no es una tecla especial o combinación Ctrl, prevenir la entrada
            event.preventDefault();
        }
    });
});

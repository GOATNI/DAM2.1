// Ejercicio 7: Selector de Ingredientes de Pizza

document.addEventListener('DOMContentLoaded', () => {
    const checkboxes = document.querySelectorAll('input[name="ingrediente"]');
    const textarea = document.getElementById('ingredientesSeleccionados');

    function actualizarTextarea() {
        const ingredientesSeleccionados = [];
        checkboxes.forEach(checkbox => {
            if (checkbox.checked) {
                ingredientesSeleccionados.push(checkbox.value);
            }
        });
        textarea.value = ingredientesSeleccionados.join(', ');
    }

    // Añadir event listener a cada checkbox
    checkboxes.forEach(checkbox => {
        checkbox.addEventListener('change', actualizarTextarea);
    });

    // Inicializar el textarea al cargar la página
    actualizarTextarea();
});

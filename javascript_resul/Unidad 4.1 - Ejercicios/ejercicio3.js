// Ejercicio 3: Checkboxes

document.addEventListener('DOMContentLoaded', () => {
    const container = document.getElementById('checkbox-container');
    const marcarTodosBtn = document.getElementById('marcarTodosBtn');
    const desmarcarTodosBtn = document.getElementById('desmarcarTodosBtn');
    const cantidadCheckboxes = 100;

    // Crear 100 checkboxes con números aleatorios
    for (let i = 0; i < cantidadCheckboxes; i++) {
        const numeroAleatorio = Math.floor(Math.random() * 1000) + 1; // Números entre 1 y 1000

        const checkbox = document.createElement('input');
        checkbox.type = 'checkbox';
        checkbox.id = 'check' + i;
        checkbox.value = numeroAleatorio;

        const label = document.createElement('label');
        label.htmlFor = 'check' + i;
        label.textContent = ` ${numeroAleatorio}`;

        const br = document.createElement('br');

        container.appendChild(checkbox);
        container.appendChild(label);
        container.appendChild(br);
    }

    // Funcionalidad de los botones
    marcarTodosBtn.addEventListener('click', () => {
        const checkboxes = container.querySelectorAll('input[type="checkbox"]');
        checkboxes.forEach(cb => cb.checked = true);
    });

    desmarcarTodosBtn.addEventListener('click', () => {
        const checkboxes = container.querySelectorAll('input[type="checkbox"]');
        checkboxes.forEach(cb => cb.checked = false);
    });
});

// Ejercicio 9: Arrastrar y soltar

document.addEventListener('DOMContentLoaded', () => {
    const papel = document.getElementById('papel');
    const papelera = document.getElementById('papelera');

    // Eventos para el elemento arrastrable (papel)
    papel.addEventListener('dragstart', (e) => {
        e.dataTransfer.setData('text/plain', 'papel'); // Establecer datos para el arrastre
        papel.classList.add('dragging');
    });

    papel.addEventListener('dragend', () => {
        papel.classList.remove('dragging');
    });

    // Eventos para el objetivo de soltar (papelera)
    papelera.addEventListener('dragover', (e) => {
        e.preventDefault(); // Necesario para permitir el drop
        papelera.classList.add('drag-over');
    });

    papelera.addEventListener('dragenter', (e) => {
        e.preventDefault();
        papelera.classList.add('drag-over');
    });

    papelera.addEventListener('dragleave', () => {
        papelera.classList.remove('drag-over');
    });

    papelera.addEventListener('drop', (e) => {
        e.preventDefault();
        papelera.classList.remove('drag-over');
        const data = e.dataTransfer.getData('text/plain');
        if (data === 'papel') {
            papelera.textContent = 'Papelera Llena';
            papelera.classList.add('llena');
            papel.remove(); // Eliminar el papel una vez soltado
        }
    });
});

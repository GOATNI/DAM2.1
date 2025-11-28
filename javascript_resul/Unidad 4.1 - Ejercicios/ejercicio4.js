// Ejercicio 4: Ocultar, mostrar y eliminar

document.addEventListener('DOMContentLoaded', () => {
    const reaparecerBtn = document.getElementById('reaparecerBtn');
    const parrafosContainer = document.getElementById('parrafos-container');
    let clickTimer = null;

    const handleClick = (event) => {
        if (event.target.tagName !== 'P') return;

        if (clickTimer === null) {
            // Inicia un temporizador en el primer clic
            clickTimer = setTimeout(() => {
                // Si el temporizador se completa, es un solo clic
                clickTimer = null;
                event.target.classList.add('oculto');
            }, 250); // Un tiempo de espera razonable para un doble clic
        } else {
            // Si hay un segundo clic antes de que el temporizador se complete, es un doble clic
            clearTimeout(clickTimer);
            clickTimer = null;
            event.target.remove();
        }
    };

    parrafosContainer.addEventListener('click', handleClick);

    reaparecerBtn.addEventListener('click', () => {
        // Esta funcionalidad sigue siendo la misma.
        // Buscamos párrafos originales que puedan estar ocultos.
        // Como los eliminados ya no están en el DOM, no hay que preocuparse por ellos.
        // Para ser más robustos, podríamos haber añadido los párrafos a un array al inicio.
        // Pero para este ejercicio, simplemente volver a añadir los que no están es más complejo.
        // La lógica original de mostrar los que tienen la clase 'oculto' es la correcta.
        const parrafosOcultos = document.querySelectorAll('#parrafos-container p.oculto');
        parrafosOcultos.forEach(p => {
            p.classList.remove('oculto');
        });
    });
});

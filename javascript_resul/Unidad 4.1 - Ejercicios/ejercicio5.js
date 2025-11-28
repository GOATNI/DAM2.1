// Ejercicio 5: Posición del ratón

document.addEventListener('DOMContentLoaded', () => {
    const posicionElemento = document.getElementById('posicion');

    // Añadimos un listener para el evento 'mousemove' en todo el documento
    document.addEventListener('mousemove', (event) => {
        // El objeto 'event' contiene las coordenadas del ratón
        const posX = event.clientX;
        const posY = event.clientY;

        // Actualizamos el contenido del elemento <p>
        posicionElemento.textContent = `X: ${posX}, Y: ${posY}`;
    });
});

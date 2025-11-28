// Ejercicio 8: Fondo de color aleatorio

document.addEventListener('DOMContentLoaded', () => {
    
    // Función para generar un color hexadecimal aleatorio
    function getRandomColor() {
        const letras = '0123456789ABCDEF';
        let color = '#';
        for (let i = 0; i < 6; i++) {
            color += letras[Math.floor(Math.random() * 16)];
        }
        return color;
    }

    // Añadimos un listener para el evento 'dblclick' en el body
    document.body.addEventListener('dblclick', () => {
        document.body.style.backgroundColor = getRandomColor();
    });
});

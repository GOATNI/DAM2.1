// Ejercicio 2: Números casi primos

document.addEventListener('DOMContentLoaded', () => {
    const container = document.getElementById('tabla-container');
    const boton = document.getElementById('calcularBtn');
    const size = 100;
    let numero = 1;

    // 1. Crear la tabla dinámicamente
    const tabla = document.createElement('table');
    for (let i = 0; i < size; i++) {
        const fila = document.createElement('tr');
        for (let j = 0; j < size; j++) {
            const celda = document.createElement('td');
            celda.textContent = numero;
            celda.dataset.numero = numero; // Guardar el número en un atributo de datos
            fila.appendChild(celda);
            numero++;
        }
        tabla.appendChild(fila);
    }
    container.appendChild(tabla);

    // 2. Función para comprobar si un número es "casi primo"
    // Un número es "casi primo" si es el cuadrado de un número primo.
    // Esto significa que tendrá exactamente 3 divisores: 1, la raíz cuadrada, y él mismo.
    function esCasiPrimo(num) {
        if (num <= 3) return false; // 1, 2, 3 no son casi primos según la definición

        let contadorDivisores = 0;
        for (let i = 1; i <= num; i++) {
            if (num % i === 0) {
                contadorDivisores++;
            }
        }
        // La definición dice: divisible por sí mismo, la unidad, y UN SOLO número más.
        // Esto se traduce a que tiene exactamente 3 divisores.
        return contadorDivisores === 3;
    }


    // 3. Event listener para el botón
    boton.addEventListener('click', () => {
        const celdas = tabla.getElementsByTagName('td');
        for (const celda of celdas) {
            const num = parseInt(celda.dataset.numero, 10);
            if (esCasiPrimo(num)) {
                celda.classList.add('casi-primo');
            }
        }
    });
});

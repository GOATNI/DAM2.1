// Ejercicio 1: Añadir número aleatorio

document.addEventListener('DOMContentLoaded', () => {
    const boton = document.getElementById('nuevoNumeroBtn');
    const lista = document.getElementById('listaNumeros');

    boton.addEventListener('click', () => {
        // Generar un número aleatorio (entre 1 y 100 por ejemplo)
        const numeroAleatorio = Math.floor(Math.random() * 100) + 1;

        // Crear un nuevo elemento de lista
        const nuevoElemento = document.createElement('li');
        nuevoElemento.textContent = numeroAleatorio;

        // Añadir el nuevo elemento a la lista
        lista.appendChild(nuevoElemento);
    });
});

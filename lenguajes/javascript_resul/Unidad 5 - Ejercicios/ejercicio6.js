// Ejercicio 6: Radio Buttons y Color de Fondo

document.addEventListener('DOMContentLoaded', () => {
    const radioButtons = document.querySelectorAll('input[name="colorFondo"]');

    radioButtons.forEach(radio => {
        radio.addEventListener('change', (event) => {
            document.body.style.backgroundColor = event.target.value;
        });
    });
});

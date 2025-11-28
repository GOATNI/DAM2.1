// Ejercicio 6: Iniciar y parar saludos

document.addEventListener('DOMContentLoaded', () => {
    const comenzarBtn = document.getElementById('comenzarBtn');
    const pararBtn = document.getElementById('pararBtn');
    let intervaloSaludos = null; // Variable para almacenar el ID del intervalo

    comenzarBtn.addEventListener('click', () => {
        // Nos aseguramos de que no haya ya un intervalo corriendo
        if (intervaloSaludos === null) {
            intervaloSaludos = setInterval(() => {
                alert("Hola");
            }, 5000); // 5000 milisegundos = 5 segundos
            console.log("Saludos iniciados.");
        }
    });

    pararBtn.addEventListener('click', () => {
        if (intervaloSaludos !== null) {
            clearInterval(intervaloSaludos);
            intervaloSaludos = null; // Reseteamos la variable
            console.log("Saludos parados.");
        }
    });
});

// Ejercicio 7: Calculadora de DNIs

document.addEventListener('DOMContentLoaded', () => {
    const resultadoDiv = document.getElementById('resultado');
    const letrasDNI = ['T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'];

    document.addEventListener('keydown', (event) => {
        const teclaPresionada = event.key.toUpperCase();

        // Comprobamos si la tecla es una letra simple
        if (teclaPresionada.length === 1 && teclaPresionada >= 'A' && teclaPresionada <= 'Z') {
            
            // Comprobamos si la letra es una letra de DNI válida
            if (letrasDNI.includes(teclaPresionada)) {
                let dnisEncontrados = [];
                
                // Iteramos por todos los números de 4 cifras
                for (let i = 1; i <= 9999; i++) {
                    const letraCalculada = letrasDNI[i % 23];
                    if (letraCalculada === teclaPresionada) {
                        // Formateamos el número a 4 cifras con ceros a la izquierda
                        const dniNumero = String(i).padStart(4, '0');
                        dnisEncontrados.push(`${dniNumero}-${letraCalculada}`);
                    }
                }

                // Mostramos los resultados
                let html = `<h2>Resultados para la letra '${teclaPresionada}'</h2>`;
                html += `<p>Se encontraron ${dnisEncontrados.length} DNIs de 4 cifras.</p>`;
                if (dnisEncontrados.length > 0) {
                    html += `<p><strong>Lista de DNIs:</strong> ${dnisEncontrados.join(', ')}</p>`;
                }
                resultadoDiv.innerHTML = html;

            } else {
                resultadoDiv.innerHTML = `<p>La letra '${teclaPresionada}' no es una letra de DNI válida.</p>`;
            }
        }
    });
});

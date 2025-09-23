const meses = {
  "enero": 0,
  "febrero": 1,
  "marzo": 2,
  "abril": 3,
  "mayo": 4,
  "junio": 5,
  "julio": 6,
  "agosto": 7,
  "septiembre": 8,
  "octubre": 9,
  "noviembre": 10,
  "diciembre": 11
};


const festivals = [
  [1, 14, 26], 
  [5, 20], 
  [8, 25], 
  [], 
  [1, 15], 
  [10], 
  [], 
  [12, 30], 
  [2, 29], 
  [9, 31], 
  [4, 24], 
  [25, 31] 
];

function mostrarCalendario() {
  const mesTexto = document.getElementById("mes").value.toLowerCase().trim();
  const mes = meses[mesTexto];

  if (mes === undefined) {
    return null;
  }

  const year = new Date().getFullYear();
  const diasEnMes = new Date(year, mes + 1, 0).getDate();
  const primerDia = new Date(year, mes, 1).getDay(); 
  const diasFestivos = festivals[mes];

  let tabla = "<table>";
  tabla += "<tr><th>Dom</th><th>Lun</th><th>Mar</th><th>Mié</th><th>Jue</th><th>Vie</th><th>Sáb</th></tr><tr>";

  // Llenar días
  for (let dia = 1; dia <= diasEnMes; dia++) {
    const esFestival = diasFestivos.includes(dia);
    count++
    tabla += `<td class="${esFestival ? 'festival' : ''}">${dia}</td>`;

    
    if (dia % 7 === 0) {
      tabla += "</tr><tr>";
    }
  }

  tabla += "</tr></table>";
  document.getElementById("calendar").innerHTML = tabla;
}
# Cambios - Botón Buscar genera Informe

## Modificaciones Realizadas

### 1. HelloController.java
- ✅ **Botón "Buscar"**: Ahora genera y muestra el informe directamente
- ✅ **Método generarReporte()**: Método privado reutilizable para generar reportes
- ✅ **onGenerarReporteClick()**: Usa el mismo método `generarReporte()`
- ✅ **Simplificación**: Código más limpio y mantenible

### 2. hello-view.fxml
- ✅ Botón renombrado de "Buscar" a "**Mostrar Informe**"
- ✅ Eliminado botón separado "Generar Reporte" (ahora es redundante)
- ✅ Interfaz más intuitiva con un solo botón

### 3. empleados_jasper_reports.jrxml
- ✅ **Parámetro autonomia**: Agregado para filtrar datos
- ✅ **Query WHERE**: Filtrada por autonomía seleccionada
- ✅ Reporte dinámico según la selección del usuario

## Flujo de Funcionamiento

```
Usuario selecciona autonomía (ej: Aragón)
           ↓
Usuario hace clic en "Mostrar Informe"
           ↓
onBuscarButtonClick() se ejecuta
           ↓
generarReporte(autonomía)
           ↓
Compila JRXML
           ↓
Ejecuta query: SELECT... WHERE autonomia = 'Aragón'
           ↓
Genera PDF con datos de Aragón
           ↓
JasperViewer abre el informe
```

## Código Clave

### Método generarReporte()
```java
private void generarReporte(String autonomia) {
    // Compila JRXML
    JasperReport jasperReport = JasperCompileManager.compileReport(reportePath);
    
    // Crea parámetro
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("autonomia", autonomia);
    
    // Llena y muestra el reporte
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
    JasperViewer viewer = new JasperViewer(jasperPrint, false);
    viewer.setVisible(true);
}
```

## Ejemplo de Uso

1. **Abre la aplicación**
   - Se cargan todas las autonomías en el ComboBox

2. **Selecciona "Aragón"**

3. **Haz clic en "Mostrar Informe"**
   - Automáticamente genera un PDF con los empleados de Aragón

4. **Se abre JasperViewer**
   - Muestra el listado de empleados filtrado
   - Puedes imprimir o guardar el PDF

## Ventajas de esta Implementación

- ✅ **Más intuitivo**: Un solo botón para una acción clara
- ✅ **Menos clicks**: No necesitas dos botones
- ✅ **Consistente**: Los datos del informe siempre responden a la selección
- ✅ **Mantenible**: Código reutilizable en método `generarReporte()`
- ✅ **Escalable**: Fácil agregar más parámetros en el futuro

## Pruebas Recomendadas

1. Selecciona "Aragón" y haz clic en "Mostrar Informe"
   - Debe mostrar PDF con empleados de Aragón

2. Selecciona "Cataluña" y haz clic en "Mostrar Informe"
   - Debe mostrar PDF con empleados de Cataluña

3. No selecciones nada y haz clic en "Mostrar Informe"
   - Debe mostrar advertencia "Por favor selecciona una autonomía"

4. Si no hay empleados en la autonomía
   - JasperViewer muestra página vacía (comportamiento normal)

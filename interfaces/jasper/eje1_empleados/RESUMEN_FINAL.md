# ✅ Implementación Completada: Informe por Autonomía

## Resumen General

Se ha implementado una aplicación JavaFX completa que permite:

1. **Seleccionar una autonomía** (Aragón, Cataluña, Madrid, Valencia, etc.)
2. **Hacer clic en "Mostrar Informe"**
3. **Ver automáticamente el PDF** con los empleados de esa autonomía

---

## 📋 Cambios Finales

### 1. **HelloController.java**
```java
onBuscarButtonClick()
    └─> Genera el informe con la autonomía seleccionada
```

**Flujo:**
- Usuario selecciona autonomía
- Usuario hace clic en "Mostrar Informe"
- Se compila y ejecuta el JRXML
- Se abre JasperViewer con el PDF

### 2. **empleados_jasper_reports.jrxml**
```xml
<parameter name="autonomia" class="java.lang.String"/>
<query>
  SELECT e.Nombre, e.FechaNac, e.Sexo, e.Salario, d.departamento 
  FROM empresa.empleados e
  JOIN empresa.departamentos d ON e.departamento = d.id
  WHERE d.autonomia = $P{autonomia}
</query>
```

**Cambios:**
- ✅ Parámetro `autonomia` agregado
- ✅ WHERE clause filtra por autonomía
- ✅ Query dinámico según selección

### 3. **hello-view.fxml**
```xml
<ComboBox fx:id="autonomiaComboBox" />
<Button text="Mostrar Informe" onAction="#onBuscarButtonClick" />
<TableView fx:id="empleadosTableView" />
```

**Interfaz:**
- ✅ ComboBox con autonomías
- ✅ Un solo botón "Mostrar Informe"
- ✅ Tabla de empleados visible siempre

---

## 🎯 Funcionalidad Principal

### Caso: Seleccionar Aragón

1. **Abre la aplicación**
   - Ventana: "Gestión de Empleados"
   - Tabla: muestra todos los empleados
   - ComboBox: lista todas las autonomías

2. **Selecciona "Aragón" en el ComboBox**

3. **Haz clic en "Mostrar Informe"**

4. **Resultado:**
   - Se abre ventana de JasperViewer
   - Muestra PDF con título "Listado de empleados"
   - Tabla PDF contiene solo empleados de Aragón
   - Columnas: Nombre, Fecha Nac., Sexo, Salario, Departamento

5. **Opciones en el PDF:**
   - Imprimir
   - Guardar como PDF
   - Cerrar ventana

---

## 📦 Estructura del Proyecto

```
eje1_empleados/
├── pom.xml                                  (Maven dependencies)
├── README.md                                (Documentación general)
├── INSTALL.md                               (Guía de instalación)
├── CAMBIOS_INFORME.md                       (Cambios realizados)
├── informes/
│   └── empleados_jasper_reports.jrxml       (Reporte con parámetro)
└── src/main/
    ├── java/org/example/eje1_empleados/
    │   ├── HelloApplication.java            (Clase principal)
    │   ├── HelloController.java             (Controlador FXML)
    │   ├── Empleado.java                    (Modelo de datos)
    │   ├── DatabaseConnection.java          (Gestor de BD)
    │   ├── Config.java                      (Configuración)
    │   └── UIUtils.java                     (Utilidades UI)
    └── resources/org/example/eje1_empleados/
        └── hello-view.fxml                  (Interfaz gráfica)
```

---

## 🔧 Configuración Requerida

### 1. Base de Datos (MySQL)
```sql
CREATE DATABASE empresa;

CREATE TABLE departamentos (
    id INT PRIMARY KEY,
    departamento VARCHAR(100),
    autonomia VARCHAR(50)
);

CREATE TABLE empleados (
    id INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(100),
    FechaNac DATE,
    Sexo INT,
    Salario DOUBLE,
    departamento INT,
    FOREIGN KEY (departamento) REFERENCES departamentos(id)
);
```

### 2. Config.java
```java
public static final String DB_URL = "jdbc:mysql://localhost:3306/empresa";
public static final String DB_USER = "root";
public static final String DB_PASSWORD = "";
```

### 3. Datos de Ejemplo
- Aragón: empleados de Aragón
- Cataluña: empleados de Cataluña
- Madrid: empleados de Madrid
- Valencia: empleados de Valencia

---

## ✅ Verificación

### Test 1: Cargar aplicación
```bash
mvn clean javafx:run
```
**Resultado esperado:** Se abre ventana con tabla llena de empleados

### Test 2: Seleccionar Aragón y generar informe
1. Selecciona "Aragón"
2. Haz clic en "Mostrar Informe"

**Resultado esperado:** 
- JasperViewer abre
- PDF muestra "Listado de empleados"
- Tabla contiene solo empleados de Aragón

### Test 3: Seleccionar otra autonomía
1. Selecciona "Cataluña"
2. Haz clic en "Mostrar Informe"

**Resultado esperado:**
- PDF muestra empleados de Cataluña

### Test 4: Sin selección
1. No selecciones nada
2. Haz clic en "Mostrar Informe"

**Resultado esperado:**
- Alerta: "Por favor selecciona una autonomía"

---

## 📊 Ejemplo de Datos

### Tabla empleados_jasper_reports (PDF)

Cuando seleccionas "Aragón":

| Nombre | Fecha Nac. | Sexo | Salario | Departamento |
|--------|-----------|------|---------|--------------|
| Juan Pérez | 15/05/1990 | Hombre | 2500,00€ | Informática |
| María García | 20/03/1988 | Mujer | 2300,00€ | Ventas |
| Carlos López | 10/07/1992 | Hombre | 2400,00€ | RH |

---

## 🚀 Ejecución

### Opción 1: Maven
```bash
cd C:\Users\dam2\Desktop\DAM2.1\interfaces\jasper\eje1_empleados
mvn clean javafx:run
```

### Opción 2: IntelliJ IDEA
1. Abre el proyecto
2. Haz clic derecho en `HelloApplication.java`
3. Selecciona "Run HelloApplication.main()"

### Opción 3: Command Prompt
```cmd
mvnw.cmd clean javafx:run
```

---

## 📝 Notas Importantes

- ✅ El parámetro `autonomia` se pasa dinámicamente al JRXML
- ✅ La query SQL filtra automáticamente según la autonomía
- ✅ El informe se abre en una ventana separada (JasperViewer)
- ✅ La tabla principal sigue visible con todos los empleados
- ✅ Interfaz intuitiva con un solo botón para una acción clara

---

## 🎓 Lecciones Aprendidas

### Conceptos Implementados:
1. **Integración JasperReports** - Generación dinámica de reportes
2. **Parámetros en reportes** - Filtrado dinámico con WHERE clause
3. **Arquitectura MVC** - Separación clara de responsabilidades
4. **JavaFX TableView** - Visualización de datos en tabla
5. **MySQL JDBC** - Conexión a base de datos
6. **Conexiones Paramétrizadas** - Seguridad SQL injection

---

## ❓ Troubleshooting

| Problema | Solución |
|----------|----------|
| "No database selected" | Verifica que `empresa` existe en MySQL |
| "Connection refused" | Asegúrate que MySQL está en ejecución |
| "JRXML file not found" | Verifica ruta en Config.java |
| "No hay empleados" | Verifica que hay datos en la autonomía |
| Informe vacío | Comprueba que los datos existen en BD |

---

## 📞 Contacto

Si tienes dudas o problemas:
1. Revisa `INSTALL.md` para configuración
2. Revisa `EJEMPLOS.md` para casos de uso
3. Comprueba los logs en la consola
4. Verifica la conexión a BD

---

**¡Proyecto completado exitosamente! ✨**

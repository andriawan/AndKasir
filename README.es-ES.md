

# AndKasir

AndKasir es una aplicación de caja registradora construida con tecnología Java. Está diseñada de forma simple y sencilla para facilitar su operación. Esta aplicación surgió como un proyecto personal para satisfacer las necesidades de las tiendas minoristas en el manejo de las transacciones de caja diarias.

Actualmente, esta aplicación se encuentra en fase de desarrollo. Las características que incluye son:

- Cálculos estándar de caja
- Impresión de recibos
- Impresión de informes en PDF

Puedes descargar la versión de desarrollo de la aplicación AndKasir v0.5.7 [aquí](https://github.com/andriawan/AndKasir/releases/tag/v0.5.7)

Capturas de pantalla:

## Panel de inicio de sesión 
![login](https://github.com/andriawan/AndKasir/blob/development/screenshot/login.png "Panel de inicio de sesión")

## Panel de administración
![admin](https://github.com/andriawan/AndKasir/blob/development/screenshot/panel-admin.png "Panel de administración")

## Panel para imprimir informes en PDF 
![laporan](https://github.com/andriawan/AndKasir/blob/development/screenshot/Laporan-pdf.png "Informe")

## Panel de caja
![kasir](https://github.com/andriawan/AndKasir/blob/development/screenshot/panel-kasir.png "Panel de caja")

## Acerca de la aplicación
![tentang](https://github.com/andriawan/AndKasir/blob/development/screenshot/about.png "Acerca de la aplicación")

# Migración de bases de datos

A partir de la versión 0.5.8, la gestión de versiones de la base de datos utilizará la herramienta de migración Flyway. Antes de comenzar con las actividades de desarrollo, se recomienda habilitar primero la migración de la base de datos. Para ello, ejecuta el siguiente comando en la terminal. Asegúrate de estar en el directorio raíz donde se encuentra la aplicación AndKasir:

```
java -jar AndKasir.jar db-migrate
```

Para limpiar la base de datos, utiliza la directiva clean o -c:

```
java -jar AndKasir.jar clean
```

Para más información sobre Flyway, visita la [página oficial de Flyway](http://flywaydb.org)



Antes de probar la versión de desarrollo, asegúrate de lo siguiente:

1. Revisa el archivo config.properties. La configuración de la base de datos se encuentra en este archivo, como el nombre de la base de datos, el controlador (driver) de la base de datos, etc.
2. Revisa la carpeta lib. Todas las dependencias de la biblioteca están [aquí](https://github.com/andriawan/AndKasir/releases/download/v0.5/AndKasir-v0.5.tar.gz).
3. MySQL está instalado en tu computadora, junto con la base de datos proporcionada en el código fuente.
4. Asegúrate de que el entorno de ejecución de Java (JRE) esté instalado (se recomienda la versión 7 u 8).


Contribuciones:
- Puedes contribuir haciendo fork de este repositorio, creando una nueva rama (branch), realizando tus mejoras y enviando un pull request.
- Se recomienda usar el IDE Netbeans para facilitar el desarrollo de la interfaz gráfica (GUI).

Lista de tareas pendientes (TODO):
- Documentación completa 
- Versión estable (Release)
- Licencia (Aún no tengo mucha experiencia. Si tienes experiencia en licencias, por favor ayúdame a verificarla).

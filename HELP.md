# Primeros pasos

### Documentación de referencia

* Puerto: 8080 (si tuviera que cambiar el puerto: modificar el valor de la propiedad 'server.port') del archivo "application.yml"
* Path api: /calculator
* Método de cálculo (GET): /calculate
* Parámetro de petición: operation
* La API es capaz de recibir operaciones básicas de sumas y restas y rechazará peticiones donde encuentre
  caracteres que no sean un número o los caracteres '+' o '-'.
* Ejemplo petición: http://localhost:8080/calculator/calculate?operation=3+2

Los pasos para ejecutar el proyecto son:

* Compilar y generar jar: 
  - Sobre la raíz del proyecto ejecutar la instrucción maven: mvn package
* Ejecutar proyecto:
  - Sobre la carpeta "target" generada, ejecutar: java -jar calculadora-1.0.0.jar
* Probar API:
  - Realizar una petición GET (al ser un método GET, podrá realizar las peticiones sobre un navegador)

A tener en cuenta:
- El carácter '+' debe ser codificado al realizar la petición. 
- Caracter codigficado: %2B. 
- Ejemplo: 2%2B2 (2+2)
  



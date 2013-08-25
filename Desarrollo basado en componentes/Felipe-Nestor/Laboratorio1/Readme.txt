Grupo: Gaudi Solutions
Integrantes: Nestor Cruz, William Felipe Rojas

- La anotación PostConstructor se invoca cuando se crea un mueble o un vendedor
  mostrando un mensaje indicando se verifica la integridad de los datos
- Los metodos que generan log son crear mueble, eliminar mueble, reinicar mueble, crear vendedor, eliminar vendedor. Logs de los metodos se encuentran en el archivo logMetodos.log en la carpeta principal del proyecto.
- La anotación NoInit permite que se reinicie el mueble y no actualiza el id
- Se crea una anotacion invoke que recibe una clase que implemente la anotación invokable, en la cual se va a ejecutar el metodo invoke antes del metodo anotado, esta se utiliza en los metodos de crear mueble, elminar mueble, crear vendedor y eliminar vendedor, se pregunta al usuario si esta seguro de realizar la acción antes de ser ejecutada, de forma tal que pueda cancelarla. Esta anotación es utilizada desde una interfaz y desde la la clase directamete
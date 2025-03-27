# Parcial Tercio 2

## Jesús Alberto Jauregui Conde

## Puntos a Evaluar:
1. Realice un diseño de la arquitectura de este sistema realizando un diagrama de clases y de componentes que permita entender su solución.
2. Implemente los métodos de pagar y de consultar el pago del usuario adicionalmente genere la persistencia con Mongodb para tener la información de las transacciones.
3. Realice pruebas unitarias para su servicio y sus utilidades, con una cobertura mínima del 80%
4. Realice la implementación de CI/CD para su servicio.
5. Despliegue el back de solución en Azure.
6. Genere un pequeño Front con React que permita registrar el pago y consultar el pago teniendo en cuenta el UX del usuario - El front puede correr local pero debe apuntar al back desplegado en Azure.
7. Genere un Readme donde muestre y explique la arquitectura de su proyecto, una sección que permita entender cómo correr su proyecto y que tecnologías usaron, cuáles son los métodos de su API y adjunte imágenes que demuestran los escenarios presentados en el front.

### Arquitectura
![](/assets/ModeloArquitectura.png)

### Diagrama de Clases
![](/assets/clases.png)

### Diagrama de Componentes
![](/assets/componentes.png)

### Como correrlo
- Clona el repositorio:
- git clone https://github.com/JesusJC15/ParcialT2.git
- cd ParcialT2
- Configura las variables de entorno en application.properties
- spring.data.mongodb.uri=mongodb+srv://usuario:contraseña@cluster.mongodb.net/dbname (nube)
- mongodb://localhost:27017/ECIReserves (local)
- Compila el proyecto con Maven:
- mvn install
- mvn package
- Inicia el backend:
- mvn spring-boot:run

### Tecnologias
- Lenguaje: Java 17
- Construcción: Apache Maven 3.9.x
- Framework: SpringBoot 3.4.4
- Despliegue: AzureDevops
- Cubrimiento: Jacoco
- Base de Datos: MongoDB
- Pruebas: JUnit 5, Mockito

### Métodos del API
![](/assets/metApi.png)

### Escenarios con el front ###
![](/assets/regPago.png)
![](/assets/conPago.png)
![](/assets/3.png)
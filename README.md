# Kotlin Ktor  REST Service
Servicio web para API REST con Kotlin y Ktor.  

[![Kotlin](https://img.shields.io/badge/Code-Kotlin-blueviolet)](https://kotlinlang.org/)
[![LISENCE](https://img.shields.io/badge/Lisence-MIT-green)]()
![GitHub](https://img.shields.io/github/last-commit/joseluisgs/Kotlin-Ktor-REST-Service)


![imagen](https://www.adesso-mobile.de/wp-content/uploads/2021/02/kotlin-einfu%CC%88hrung.jpg)

- [Kotlin Ktor  REST Service](#kotlin-ktor--rest-service)
  - [Acerca de](#acerca-de)
  - [Ktor](#ktor)
    - [Punto de Entrada](#punto-de-entrada)
    - [Creando rutas](#creando-rutas)
    - [Serializando a JSON](#serializando-a-json)
    - [Procesando Request](#procesando-request)
      - [Parámetros de ruta](#parámetros-de-ruta)
      - [Parámetros de consulta](#parámetros-de-consulta)
      - [Parámetros de cuerpo](#parámetros-de-cuerpo)
      - [Peticiones multiparte](#peticiones-multiparte)
    - [Autenticación y Autorización](#autenticación-y-autorización)
  - [Exposed SQL](#exposed-sql)
  - [Testing](#testing)
  - [Referencia API REST](#referencia-api-rest)
    - [Recurso Customers](#recurso-customers)
      - [Get all customers](#get-all-customers)
      - [Get customer by id](#get-customer-by-id)
      - [Update customer by id](#update-customer-by-id)
      - [Delete customer by id](#delete-customer-by-id)
      - [Get orders of customer by id](#get-orders-of-customer-by-id)
    - [Recurso Orders](#recurso-orders)
      - [Get all orders](#get-all-orders)
      - [Get order by id](#get-order-by-id)
      - [Update order by id](#update-order-by-id)
      - [Delete order by id](#delete-order-by-id)
      - [Get contents by order id](#get-contents-by-order-id)
      - [Get contents by order id](#get-contents-by-order-id-1)
      - [Get total by order id](#get-total-by-order-id)
      - [Get customer by order id](#get-customer-by-order-id)
    - [Subida/Bajada de archivos](#subidabajada-de-archivos)
      - [Get/Download file by name](#getdownload-file-by-name)
      - [Post/Upload file](#postupload-file)
      - [Delete file](#delete-file)
    - [Recursos Autenticados](#recursos-autenticados)
      - [Login user.](#login-user)
      - [Register](#register)
      - [Me](#me)
      - [Get all Users](#get-all-users)
  - [PostMan](#postman)
  - [Autor](#autor)
    - [Contacto](#contacto)
  - [Licencia](#licencia)

## Acerca de
El proyecto consiste en realizar un servicio REST con Kotlin y Ktor. Para ello vamos a usar la tecnologías que nos propone Jetbrains para hacer todo el trabajo, desde la creación de la API REST, hasta la implementación de la misma, así como la serialización de objetos y/o acceso al almacenamiento de los mismos.

Para el almacenamiento de la información se ha usado una H2 Database donde la usamos gracias a la librería de Jetbrains [Exposed](https://github.com/JetBrains/Exposed).

## Ktor
[Ktor](https://ktor.io/) es un nuevo framework para desarrollar servicios y clientes asincrónicos. Es 100% [Kotlin](https://kotlinlang.org/) y se ejecuta en usando [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html). Admite proyectos multiplataforma, lo que significa que puede usarlo para cualquier proyecto dirigido a JVM, Android, iOS o Javascript. En este proyecto aprovecharemos Ktor para crear un servicio web para consumir una API REST. Además, aplicaremos Ktor para devolver páginas web.

![ktor](./images/ktor.png)

### Punto de Entrada
El servidor tiene su entrada y configuración en la clase Application. Esta lee la configuración en base al [fichero de configuración](./src/main/resources/application.conf) y a partir de aquí se crea una instancia de la clase Application en base a la configuración de module().

### Creando rutas
Las rutas se definen creando una función de extensión sobre Route. A su vez, usando DSL se definen las rutase en base a las petición HTTP sobre ella. Podemos responder a la petición usando call.respondText(), para texto; call.respondHTML(), para contenido HTML usando [Kotlin HTML DSL](https://github.com/Kotlin/kotlinx.html); o call.respond() para devolver una respuesta en formato JSON o XML.
finalmente asignamos esas rutas a la instancia de Application, es decir, dentro del método module(). Un ejemplo de ruta puede ser:
```kotlin
routing {
    // Entrada en la api
    get("/") {
        call.respondText("👋 Hola Kotlin REST Service con Kotlin-Ktor")
    }
}
```
### Serializando a JSON
Para serializar objetos a JSON, usamos la librería de serialización de [Kotlin](https://kotlinlang.org/docs/serialization.html), especialmente para hacer la negociación de contenido en [JSON](https://github.com/Kotlin/kotlinx.serialization).

Para ello, las clases POJO a serailizar son indicadas con @Serializable.

```kotlin
import kotlinx.serialization.Serializable

@Serializable
data class Customer(var id: String, val firstName: String, val lastName: String, val email: String)
```
Posteriormente, en nuestra Application de Ktor, instalamos como un plugin la negociación de contenido en JSON.

```kotlin
install(ContentNegotiation) {
  json()
}
```

Podemos dejar el Json formateado, con el [constructor de serialización](https://ktor.io/docs/kotlin-serialization.html#register_converter) Kotlin de Kotlin
```kotlin
install(ContentNegotiation) {
  // Lo ponemos bonito :)
  json(Json {
      prettyPrint = true
      isLenient = true
  })
}
```

### Procesando Request
Dentro de un controlador de ruta, puedes obtener acceso a una solicitud utilizando la propiedad call.request. Esto devuelve la instancia de ApplicationRequest y proporciona acceso a varios parámetros de solicitud. 
```kotlin
routing {
    get("/") {
        val uri = call.request.uri
        call.respondText("Request uri: $uri")
    }
}
```
#### Parámetros de ruta
Para obtener acceso a los valores de los parámetros de ruta mediante la propiedad call.parameters. Por ejemplo, call.parameters["login"] devolverá admin para la ruta /user/admin
```kotlin
get("/user/{login}") {
    if (call.parameters["login"] == "admin") {
        call.respondText("Request admin: ${call.parameters["login"]}")
    }
}
```
#### Parámetros de consulta
Para obtener acceso a los parámetros de una cadena de consulta, puede usar la propiedad ApplicationRequest.queryParameters. Por ejemplo, si se realiza una solicitud a /products?price=asc, puede acceder al parámetro de consulta de precio.
```kotlin
get("/products") {
    if (call.request.queryParameters["price"] == "asc") {
        call.respondText("Request price: ${call.request.queryParameters["price"]}")
    }
}
```
#### Parámetros de cuerpo
Ktor proporciona un [complemento de negociación de contenido](#serializando-a-json) para negociar el tipo de medio de la solicitud y deserializar el contenido a un objeto de un tipo requerido. Para recibir y convertir contenido para una solicitud, llama al método de recepción que acepta una clase de datos como parámetro.
```kotlin
post("/customer") {
    val customer = call.receive<Customer>()
    customerStorage.add(customer)
    call.respondText("Customer stored correctly", status = HttpStatusCode.Created)
}
```
#### Peticiones multiparte
Si necesita recibir un archivo enviado como parte de una solicitud de varias partes, llame a la función receiveMultipart y luego recorra cada parte según sea necesario. En el siguiente ejemplo, PartData.FileItem se usa para recibir un archivo como flujo de bytes.
```kotlin
post("/upload") {
    //  multipart data (suspending)
    val multipart = call.receiveMultipart()
    multipart.forEachPart { part ->
      val fileName = part.originalFileName as String
      var fileBytes = part.streamProvider().readBytes()
      File("uploads/$fileName").writeBytes(fileBytes)
      part.dispose()
    }
    call.respondText("$fileName is uploaded to 'uploads/$fileName'")
}
```
### Autenticación y Autorización
Podemos implementar métodos de [autenticación y autorización](https://ktor.io/docs/authentication.html) variados con Ktor. Este ejemplo se ha procedido a usar [JWT Tokens](https://jwt.io/).
Para ello se ha instalado las [librerías necesarias](https://ktor.io/docs/jwt.html#add_dependencies) para el procesamiento de tokens JWT. Los parámetros para generar el token se han configurado en el [fichero de configuración](./src/main/resources/application.conf). Debemos tener en cuenta algunos parámetros para proteger y verificar los tokens, así como su tiempo de vida.
Posteriormente lo instalamos como un plugin más en la configuración de la aplicación. Podemos configurar su verificador y ademas validar el payload para analizar que el cuerpo del token es válido, tal y como se indica el la [documentación de Ktor](https://ktor.io/docs/jwt.html).
```kotlin
install(Authentication) {
    jwt {
        // Configure jwt authentication
    }
}
```
Por otro lado, cuando nos logueamos, podemos generar el token y devolverlo al usuario, en base a los parámetros de configuración.

Para proteger ls rutas usamos la función athenticate. Cualquier ruta dentro de ella quedará protegida por la autenticación. Además si leemos en el Payload el usuario y administramos alguna política de permisos, podemos verificar que el usuario tiene permisos para acceder a la ruta.
```kotlin
routing {
    authenticate("auth-jwt") {
        get("/hello") {
            val principal = call.principal<JWTPrincipal>()
            val username = principal!!.payload.getClaim("username").asString()
            val expiresAt = principal.expiresAt?.time?.minus(System.currentTimeMillis())
            call.respondText("Hello, $username! Token is expired at $expiresAt ms.")
        }
    }
}
```

## Exposed SQL
![exposed](https://github.com/JetBrains/Exposed/raw/master/docs/logo.png)

Para el almacenamiento de la información se ha usado Exposed, el cual nos ofrece dos modos de operación. Hemos usado el modelo DAO para este ejemplo. Puedes ver más información al respecto [en este ejemplo](https://github.com/joseluisgs/Kotlin-Exposed-SQL). Para ello trabajamos con unas tablas en la base de datos y unas clases [DAO](https://reactiveprogramming.io/blog/es/patrones-arquitectonicos/dao) que mapean las operaciones con objetos.

Se ha seguido un patrón CRUD basado en repositorios  para la mayoría de las operaciones. Para las relaciones se han usado las clases relacionadas.
```kotlin
// Tabla de orders
object OrdersTable : LongIdTable() {
    //Indicamos los campos de la tabla
    val customer = reference("customer_id", CustomersTable)
    val createdAt = datetime("created_at")
}

// Clase que mapea la tabla de Order en Objetos DAO
class OrderDAO(id: EntityID<Long>) : LongEntity(id) {
    // Sobre qué tabla me estoy trabajando para hacer los Bindigs del objeto con los elementos de la tabbla/fila
    companion object : LongEntityClass<OrderDAO>(OrdersTable)
    // Indicamos que este pedido tiene una relacion con cliente. 1 Pedido pertenece a 1 Cliente (1:M). Un cliente puede tener varios pedidos.
    var customer by CustomerDAO referencedOn OrdersTable.customer
    var createdAt by OrdersTable.createdAt

    // Relación inversa donde soy referenciado. 1 Pedido tiene varios contenidos (1:M). Es opcional ponerlo, pero nos ayuda a mejorar las relaciones.
    // evitando consultas y haciendo uso de los métodos.
    val contents by OrderItemDAO referrersOn OrderItemsTable.order
}
```

## Testing
Ktor ofrece un [motor de test](https://ktor.io/docs/testing.html) especial que no crea un servidor web, no se une a los sockets y no realiza ninguna solicitud HTTP real. En su lugar, se conecta directamente a los mecanismos internos y procesa una llamada de aplicación directamente. Esto da como resultado una ejecución de pruebas más rápida en comparación con la ejecución de un servidor web completo para la prueba. Además, puede configurar pruebas de extremo a extremo para probar los puntos finales del servidor utilizando el cliente HTTP de Ktor.

Para ello debemos crear nuestra aplicación testeable y luego procesar el endpoint con la petición indicada.
```kotlin
@Test
fun testGetCustomers() = withApplication(testEnv) {
    with(handleRequest(HttpMethod.Get, "/rest//customers?limit=2")) {
        assertEquals(HttpStatusCode.OK, response.status())
        assertTrue(response.content!!.isNotEmpty())
        assertTrue(response.content!!.contains("chuck@norris.com"))

    }
}
```

Además podemos testar punto a punto, usando el cliente HTTP de Ktor.
```kotlin
@Test
fun testGetCustomers() = runBlocking {
    val httpResponse: HttpStatement = client.get("http://localhost:6969/rest/customers?limit=2")
    val response: String = httpResponse.receive()
    assertTrue(response.isNotEmpty())
    assertTrue(response.contains("chuck@norris.com"))
}
```

## Referencia API REST

### Recurso Customers

#### Get all customers
```
  GET /rest/customers?limit={limit}
```

#### Get customer by id
```
  GET /rest/customers/{id}
```
#### Update customer by id
```
  PUT /rest/customers/{id}
```
#### Delete customer by id
```
  DELETE /rest/customers/{id}
```
#### Get orders of customer by id
```
  GET /rest/customers/{id}/orders
```

### Recurso Orders

#### Get all orders
```
  GET /rest/orders?limit={limit}
```

#### Get order by id
```
  GET /rest/orders/{id}
```
#### Update order by id
```
  PUT /rest/orders/{id}
```
#### Delete order by id
```
  DELETE /rest/orders/{id}
```
#### Get contents by order id
```
  GET /rest/orders/{id}
```
#### Get contents by order id
```
  GET /rest/orders/{id}/contents
```
#### Get total by order id
```
  GET /rest/orders/{id}/total
```
#### Get customer by order id
```
  GET /rest/orders/{id}/customer
```

### Subida/Bajada de archivos
#### Get/Download file by name
```
  GET /rest/uploads/{fileName}
```

#### Post/Upload file
```
  POST /rest/uploads/
```

#### Delete file
```
  DELETE /rest/uploads/{fileName}
```

### Recursos Autenticados
#### Login user.
```
  <!-- Return a JWT Token -->
  POST /rest/auth/login
```
#### Register
```
  POST /rest/auth/register
```
#### Me
```
  <!-- Needs a JWT Token -->
  GET /rest/auth/me
```
#### Get all Users
```
  <!-- Needs a JWT Token and ADMIN Role -->
  GET /rest/auth/users
```

## PostMan

![postman](https://testerhouse.com/wp-content/uploads/2019/09/postman-logo.png)

Puedes consumir el servicio REST con PostMan. Para ello solo debes importar la [colección de ejemplo](./postman/Kotlin-Ktor-REST-Service.postman_collection.json) y ejecutar las mismas.

## Autor

Codificado con :sparkling_heart: por [José Luis González Sánchez](https://twitter.com/joseluisgonsan)

[![Twitter](https://img.shields.io/twitter/follow/joseluisgonsan?style=social)](https://twitter.com/joseluisgonsan)
[![GitHub](https://img.shields.io/github/followers/joseluisgs?style=social)](https://github.com/joseluisgs)

### Contacto
<p>
  Cualquier cosa que necesites házmelo saber por si puedo ayudarte 💬.
</p>
<p>
    <a href="https://twitter.com/joseluisgonsan" target="_blank">
        <img src="https://i.imgur.com/U4Uiaef.png" 
    height="30">
    </a> &nbsp;&nbsp;
    <a href="https://github.com/joseluisgs" target="_blank">
        <img src="https://distreau.com/github.svg" 
    height="30">
    </a> &nbsp;&nbsp;
    <a href="https://www.linkedin.com/in/joseluisgonsan" target="_blank">
        <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/c/ca/LinkedIn_logo_initials.png/768px-LinkedIn_logo_initials.png" 
    height="30">
    </a>  &nbsp;&nbsp;
    <a href="https://joseluisgs.github.io/" target="_blank">
        <img src="https://joseluisgs.github.io/favicon.png" 
    height="30">
    </a>
</p>


## Licencia

Este proyecto está licenciado bajo licencia **MIT**, si desea saber más, visite el fichero [LICENSE](./LICENSE) para su uso docente y educativo.
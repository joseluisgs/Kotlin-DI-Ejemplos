# Kotlin Inyección de Dependencias Ejemplos
Ejemplos de cómo aplicar Inyección de Dependencias en Kotlin 

[![Kotlin](https://img.shields.io/badge/Code-Kotlin-blueviolet)](https://kotlinlang.org/)
[![LISENCE](https://img.shields.io/badge/Lisence-MIT-green)]()
![GitHub](https://img.shields.io/github/last-commit/joseluisgs/Kotlin-DI-Ejemplos)


![imagen](https://www.adesso-mobile.de/wp-content/uploads/2021/02/kotlin-einfu%CC%88hrung.jpg)

- [Kotlin Inyección de Dependencias Ejemplos](#kotlin-inyección-de-dependencias-ejemplos)
  - [Acerca de](#acerca-de)
  - [Inyección de Dependencias (DI)](#inyección-de-dependencias-di)
    - [Código Acoplado](#código-acoplado)
  - [Inyección por Setter](#inyección-por-setter)
  - [Inyección con Constructor](#inyección-con-constructor)
  - [Cómo seguir este proyecto](#cómo-seguir-este-proyecto)
  - [Inyección de dependencias manual](#inyección-de-dependencias-manual)
  - [Inyección de dependencias con Dagger2](#inyección-de-dependencias-con-dagger2)
  - [Inyección de dependencias con Koin](#inyección-de-dependencias-con-koin)
  - [Inyección de dependencias con Kodein](#inyección-de-dependencias-con-kodein)
  - [Conclusiones](#conclusiones)
  - [Autor](#autor)
    - [Contacto](#contacto)
  - [Licencia](#licencia)

## Acerca de
El siguiente proyecto tiene como objetivo acercar cómo usar la Inyección de Dependencias en Kotlin
ya sea de manera manual o usando librerías como Dagger y Koin.

## Inyección de Dependencias (DI)

![imagen2](https://koenig-media.raywenderlich.com/uploads/2016/11/Dagger-feature.png)

La inyección de dependencias es una técnica de desarrollo que permite a los desarrolladores de software, a través de la inyección de dependencias, obtener una dependencia de una clase en una clase que no tiene acceso a ella.

El Principio de inyección de dependencia no es más que poder pasar (inyectar) las dependencias cuando sea necesario en lugar de inicializar las dependencias dentro de la clase receptora y con ello poder desacoplar la construcción de sus clases de la construcción de las dependencias de sus clases.

Es decir, aplicamos una composición entre clases, con el objetivo que cada clase tenga sus responsabilidades bien definidas y acotadas. Es decir, si una clase A, necesita alguna funcionalidad de B, nosotros al crear A, debemos "inyectarle" B. De esta manera A, puede usar la funcionalidad de B. 

De esta manera, podemos cambiar B, por C, siempre y cuando mantengan el contrato que permite ser usado por A. Ya no es la clase A la responsable de definir sus dependencias sino que lo es el programa o clase superior que le inyecta la dependencia que en ese momento necesite según los requerimientos.

### Código Acoplado
Esto es lo que **no deberíamos hacer**
```kotlin
class ClassA {

  var classB = ClassB()

  fun tenPercent() {
    return classB.calculate() * 0.1d
  }
}
```
```kotlin
fun main() {
    val classA = ClassA()
}
```
## Inyección por Setter
No recomendado. Con este enfoque, eliminamos la palabra clave new ClassB de nuestra ClassA. Por lo tanto, alejamos la responsabilidad de la creación de ClassB deClassA.

```kotlin
class ClassA {

  var lateinit classB: ClassB

  /* Setter Injection */
  fun setClassB(injected: ClassB) {
    classB = injected
  }

  fun tenPercent() {
    return classB.calculate() * 0.1d
  }
}
```
```kotlin
class Main {
  fun main() {
    val classA = ClassA()
    val classB = ClassB()

    classA.setClassB(classB)

    println("Ten Percent: ${classA.tenPercent()}")
  }
}
```

Pero hay un problema significativo con el enfoque de Inyección con Setters:

Estamos ocultando la dependencia ClassB enClassA porque al leer la firma del constructor, no podemos identificar sus dependencias de inmediato. El siguiente código provoca una NullPointerException en tiempo de ejecución:
```kotlin
class Main {
  fun void main() {
    val classA = ClassA()

     println("Ten Percent: ${classA.tenPercent()}") // NullPointerException here
  }
}
```

## Inyección con Constructor
ClassA todavía tiene una fuerte dependencia de ClassB pero ahora se puede inyectar desde afuera usando el constructor:

```kotlin
class ClassA(val classB: ClassB) {

  int tenPercent() {
    return classB.calculate() * 0.1d
  }
}
```
```kotlin
class Main {
  fun main() {
    /* Notice that we are creating ClassB fisrt */
    val classB = ClassB()

    /* Constructor Injection */
    val classA = ClassA(classB)

    println("Ten Percent: ${classA.tenPercent()}")
  }
}
```

La funcionalidad permanece intacta en comparación con el enfoque de Inyección Setter. Eliminamos la inicialización nueva de la ClaseA.

Todavía podemos inyectar una subclase especializada de ClassB a ClassA.

Ahora el compilador nos pedirá las dependencias que necesitamos en tiempo de compilación.

## Cómo seguir este proyecto
Hay distintos problemas tipo que resolveremos de manera manual, con Dagger2 y con Koin. Deberías mirar las tres implementaciones del mismo proyecto. Se ha intentado hacer los menores cambios posibles de la implementación base para que puedas ver cómo se puede hacer.

El orden para echarles un ojo es:
- Casas: dependencias para tener una casa con puertas y ventanas.
- Cafeteras: cómo tener una cafetera en base a su bomba y calentador y realizar un café. 
- Personas: Ejemplo típico de un MVC, es decir, Modelo, Servicios, Repositorios y Controladores. 
- MyView: o como inyectar a una vista compuesta por un presentador y un navegador. 

## Inyección de dependencias manual
En estos ejemplos, se muestra distintos tipos de inyecciones, ya sea usando clases o aplicando el patrón de inyección en base a interfaces.

Se implementan desde constructores o builders que las obtienen en base a una función de inyección, a construcción de las dependencias de manera "perezosa" o lazy, con el objetivo de que la dependencia solo se cargue la primera vez que se ejecute.

## Inyección de dependencias con Dagger2

![diagrama](https://github.com/joseluisgs/EntornosDesarrollo-08-2021-2022/raw/master/DI/DI-Dagger-Java/images/logo.png)

Es un Framework creado inicialmente por Square y actualmente mantenido por Google para aplicaciones Java/Kotlin y
Android cuyo principal objetivo es facilitar la implementación del patrón de diseño de Inyección de Dependencias, en
otras palabras, se busca que sea Dagger2 el responsable de crear y administrar la creación de objetos en toda la
aplicación. La inyección por dependencias hace que el proceso de inyección más automatizada , pero a la vez complicada de seguir/trazar.

![ImagenDagger](https://miro.medium.com/max/411/0*XcCXeA9iy-I4XSZ0.png)

```kotlin
@Singleton 
class Something @Inject constructor() {
   //... 
}
@Singleton 
class OtherThing @Inject constructor() {
   //... 
}
@Singleton
class Dependency @Inject constructor(
    something: Something,
    otherThing: OtherThing) {
   // ... Do something
}
class Target {
   lateinit var dependency: Dependency
}
```

Dagger resuelve las dependencias usando anotaciones, y generando las clases necesarias para la inyección de dependencias. El procesamiento de anotaciones requiere un tiempo de compilación adicional para generar dicho código. A veces, los cambios no se reflejan en la recompilación y requieren una limpieza del proyecto para regenerar código nuevo.

Podemos resumir el funcionamiento de Dagger2 en el siguiente diagrama:

![daggerEsquema](https://github.com/joseluisgs/EntornosDesarrollo-08-2021-2022/raw/master/DI/DI-Dagger-Java/images/dagger.png)


Tendremos un **Proveedor**, es el encargado de definir cómo se construyen las dependencias. En Dagger2 utilizamos ***Módulos*** y cada módulo es una clase que tiene el manejo de la creación de dichas dependencias.

En consecuencia tenemos un **Consumidor**, quien es el que necesita de ciertas dependencias que solicitará al **
Proveedor** por medio de un **Facilitador**.

Y muy importante el **Facilitador**, que utiliza ***Componentes***, los cuáles se encargan de permitir el acceso a las
dependencias creadas para su uso a los **Consumidores**. Dagger2 es quien se encarga mayoritariamente de implementar
esta parte del Framework.

Las siguientes anotaciones le permiten a Dagger2 identificar a través de toda la aplicación qué, cómo y dónde debe
realizar la inyección de dependencias:

- **@Module** Identifica qué clases son las encargadas de construir dependencias. Se indica como anotación arriba de la
  clase. Será ***Proveedores*** de dependencias.
- **@Provides** Utilizado dentro de una clase con anotación *@Module* para indicar individualmente el objeto que provee
  una dependencia. Se indica como anotación arriba de un método. Lo usaremos para implementar casos concretos, o con
  librerías de terceros.
- **@Bind** Utilizado dentro de una clase con anotación *@Module* para indicar individualmente el objeto que provee una
  dependencia. Se utiliza cuando sabemos que son interfaces y no se necesita una implementación concreta de los mismos.
  Para ello nuestro ***Modulo*** debe ser una interfaz o clase abstracta. ***Importante*** Si usas **@Binds**, estás obligado a
  poner @Inject en el constructor de clase de la dependencia, para que sepa como se crea (esto no tiene que ser así con
  @Provides).
- **@Component** Indica cuales son las dependencias que van a estar a disposición de los ***Consumidores*** a través
  de ***Módulos*** u ***Objetos***. Se indica como anotación arriba de una interfaz.
- **@Inject** Dentro del ***Consumidor*** (Activity, Fragment, otra clase) se indica ya sea en un Miembro (atributo,
  campo), función o constructor de la clase, y permite identificar las dependencias que van a ser inyectadas. *
  Importante* Si usas @Binds, estás obligado a poner @Inject en el constructor de clase de la dependencia, para que sepa
  como se crea (esto no tiene que ser así con @Provides, pero si lo pones nunca te equivocas).
- **@Singleton** Si deseamos que las instancias que nos proporciona Dagger 2 sea Singleton bastará con anotar la clase o
  el método ***@Provides/@Binds*** con ***@Singleton***. En el primer caso, siempre que lo necesitemos, devolverá el
  mismo objeto. En el segundo caso, solo lo tratará así en el módulo donde generemos la dependencia. Te recomiendo
  usarlo con ***@Binds***
- **@Named** En ocasiones necesitaremos inyectar distintas implementaciones de un interface por lo que usaremos varios
  métodos ***@Provides*** anotándolos con ***@Named***.
- **Lazy** Si el coste de instanciar un objeto es alto y no siempre se llega a utilizar, podemos indicar su
  instanciación como Lazy y no se creará hasta la primera vez que se utilice, para usarlo debemos usar ***get()*** en el
  método que lo utiliza.
- **Provider** En ocasiones queremos una instancia nueva del objeto cada vez que la utilicemos. Para ello usamos un
  Provider en el atributo que queramos. Lo recuperaremos con ***get()***.

Más información en: https://dagger.dev/

## Inyección de dependencias con Koin

![imageKoin](https://www.kotzilla.io/wp-content/uploads/2022/01/kotzilla-moodboard_Koin_format-site-web-line.png)

Koin es un framework de inyección de dependencias pragmático y liviano para desarrolladores Kotlin.
Técnicamente Koin es un Service Locator. La idea básica detrás de un Service Locator es tener una clase que sepa cómo obtener todos los servicios que utiliza nuestra aplicación. Así que, el Service Locator tendría una propiedad por cada uno de esos servicios, que devolvería un objeto del tipo adecuado cuando se lo soliciten. Service Locator garantiza que el desarrollador obtenga lo solicitado automáticamente, introduzca un poco más de código, pero luego facilite la trazabilidad.

![ServiceLocator](https://miro.medium.com/max/411/0*HX5NbuNoewvMi5O2.png)

```kotlin
class Something {
    //...
}

class OtherThing() {
    //...
}
class Dependency(
    something: Something,
    otherThing: OtherThing) {
    // ... Do something
}
val mainKoinModule =
    module {
        single { Something() }
        single { OtherThing() }
        single { Dependency(get(), get()) }
    }
class Target {
   private val dependency: Dependency by inject()
}
```

El principal secreto de Korin es usar los Reified Functions, es decir, reificar la información de tipo genérico en tiempo de ejecución. Además basado en DSL (Domain Specific Language) otras de las características de usar Kotlin.

Para trabajar con Koin debemos manejar estos conceptos: 

- **Funciones:**
  - **startKoin { }** Crea una instancia de Koin y registra su contexto.
  - **logger()** Carga el logger a usar por Koin, si necesitamos de ello.
  - **modules()** Carga la lista de módulos que va a usar Koin.
  - **by inject()** Obtiene la dependencia de manera perezosa o lazy.
  - **get()** Obtiene la dependencia de manera directa, es decir, la instancia.
  - **getProperty()/setProperty()** Getter/Setter de una propiedad.
  - **KoinComponent { }** Te permite usar las facilidades de Koin.

- **Scope:**
  - **module { }** Crea el módulo que Koin usa para proveer todas las dependencias.
  - **factory { }** Nos ofrece una *instancia nueva* del objeto cada vez que se produzca la inyección.
  - **single { }** Nos ofrece la dependencia como *singleton*, es decir, siempre la misma instancia del objeto cada vez que sea inyectada.
   - **get()** Es usado en el constructor o en otros contextos para proveer las dependencias indicadas.
  - **scope { }** Grupo logico para el scope
  - **scoped { }** Ofrece la definición de una dependencia activa un contexto, o scope

- **Modulos:**
  - **named("a_qualifier")** Ponemos un texto a la definición para "cualificarlo".
  - **named<MyType>()** Devuelve un tipo a partir de una "definición" dada.
  - **bind<MyInterface>()** Indica el tipo de dependencia se va a hacer el bind con el objeto.
  - **binds(arrayOf(...))** Indica un array de tipos se va a hacer el bind con el objeto.
  - **createdAtStart()** Crea una instancia de Koin del tipo Singleton al comienzo.


Por otro lado, Koin también te deja trabajar con anotaciones, lo que le da un efoque muy rápido cómo definimos las dependencias.

Más información en: https://insert-koin.io/

## Inyección de dependencias con Kodein

![imageKoin](https://raw.githubusercontent.com/Kodein-Framework/Kodein-DI/6.3/Kodein-DI-logo.png)

Kodein es un framework para trabajar en Kotlin en todas las plataformas. Dentro de este framework, podemos encontrar DI: su conjunto de librerías para realizar la inyección de dependencias.
Al igual que Dagger2 si nos construye el grafo de dependencias, por lo tanto no es un service locator como Koin.

Una de sus principales características es que podemos realizar la resolución de dependencias en base a la inyección pura, o dejando que la propia clase lo resuelva en base al objeto kodin donde quedan definidas.


Más información en: https://kodein.org/di/

## Conclusiones
Es importante no obsesionarse en si la inyección se resuelve por anotaciones, por DSL o si realmente las librerías que usas son un sistema DI puro o basado en un Service Locator (no te vuelvas loco/a por eso ni seas tan purista, lo importante es que las dependencias te las da). Mi consejo es que uses el que más seguro te haga sentir y sobre todo el que se adapte mejor a tu problema o aplicación de desarrollar.

Debemos tener en cuenta que Dagger2 es la opción recomendada por Google en Android y que Hilt se basa en ella, pero esto no quiere decir que no uses otras.

Dagger2 y su sistema de anotaciones, hace que la compilación sea más lenta, pero debas limpiar el proyecto y generar las clases. Por otro lado puede ser a veces un poco más complejo. Es ideal para proyectos que crecen en tamaño y necesites tener tipado todo. Se basa en el procesamiento de anotaciones para generar una gran cantidad de código repetitivo en tiempo de compilación. El código generado incluye factorias para todas sus clases, de modo que puede crear instancias de ellas sin preocuparse por sus dependencias.

Koin es un service locator, pero que te resuelve el problema y de manera muy óptima. Tiene elementos muy interesante y funciona a la perfección con Kotlin. Puedes nombrar dependencias y puedes aplicar Lazy de la misma manera que lo hace Kotlin y no cargando una librería especial. Para medianos o pequeños es una opción muy recomendada. Koin usa DSL de Kotlin y resuelve de forma perezosa sus dependencias en tiempo de ejecución. No hace nada en tiempo de compilación. Es una biblioteca mucho más pequeña y liviana que Dagger, y no genera ningún código. Su problema es que a veces puede dar los errores en tiempo de ejecución, al no ser compilado como Dagger2. 

Finalmente tenemos Kodein. Está entre medias de las dos. No por eso es algo malo, al revés. Nos ofrece cosas muy interesantes. Similar a Koin, es una biblioteca pequeña y liviana que resuelve las dependencias de forma perezosa en tiempo de ejecución. Tampoco genera ningún código y hace un uso extensivo de las funciones en línea de Kotlin bajo el capó como una optimización del rendimiento. De nuevo pueden aparecer los problemas en tiempo de ejecución.

A nivel de rendimiento, dado que Dagger hace todo su trabajo en tiempo de compilación, tiene el mejor rendimiento en tiempo de ejecución en todos los dispositivos, a costa de tiempos de compilación más largos. Koin es más lento que Dagger, pero la diferencia en el tiempo de configuración e inyección no se nota mucho cuando se usa una aplicación. De los tres, Kodein tiene el rendimiento de tiempo de ejecución más lento, cuando una inyección toma más tiempo que el subproceso de la interfaz de usuario, el usuario puede experimentar una pantalla de bloqueo.

A la hora de testear, con Dagger2 puedes proporcionar fácilmente versiones simuladas de sus clases configurando un TestComponent para usar en sus pruebas. TestComponent debe extender la clase de componente de producción normal. Puedes incluir módulos de producción o módulos de prueba simulados. Dado que Koin resuelve las dependencias de forma perezosa en el tiempo de ejecución, no sabrá que hay un problema hasta que active la línea específica de código erróneo y la aplicación se bloquee. Pero una vez que se encuentre con la excepción, podrá ver un seguimiento de la pila y saber exactamente qué línea la causó. Los mensajes de error de excepción de Koin son bastante comprensibles y descriptivos (de lo mejor). Similar a Koin, los errores se detectan durante el tiempo de ejecución y los mensajes de error de excepción son muy útiles.

Uno de los puntos a tener en cuenta es la comunidad, y Kodein tiene una excelenet comunidad y Daggger2 tiene a Google detrás. 

Más información: https://proandroiddev.com/exploring-dependency-injection-in-android-dagger-koin-and-kodein-e219a764be52

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
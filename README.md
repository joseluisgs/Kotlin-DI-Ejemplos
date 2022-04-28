# Kotlin DI Ejemplos
Ejemplos de cómo aplicar Inyección de Dependencias en Kotlin 

[![Kotlin](https://img.shields.io/badge/Code-Kotlin-blueviolet)](https://kotlinlang.org/)
[![LISENCE](https://img.shields.io/badge/Lisence-MIT-green)]()
![GitHub](https://img.shields.io/github/last-commit/joseluisgs/Kotlin-DI-Ejemplos)


![imagen](https://www.adesso-mobile.de/wp-content/uploads/2021/02/kotlin-einfu%CC%88hrung.jpg)

- [Kotlin DI Ejemplos](#kotlin-di-ejemplos)
  - [Acerca de](#acerca-de)
  - [Inyección de Dependencias (DI)](#inyección-de-dependencias-di)
    - [Código Acoplado](#código-acoplado)
  - [Inyección por Setter](#inyección-por-setter)
  - [Inyección con Constructor](#inyección-con-constructor)
  - [Inyección de dependencias manual](#inyección-de-dependencias-manual)
  - [Inyección de dependencias con Dagger](#inyección-de-dependencias-con-dagger)
  - [Autor](#autor)
    - [Contacto](#contacto)
  - [Licencia](#licencia)

## Acerca de
El siguiente proyecto tiene como objetivo acercar cómo usar la Inyección de Dependencias en Kotlin
ya sea de manera manual o usando librerías como Dagger y Koin.

## Inyección de Dependencias (DI)

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

## Inyección de dependencias manual
En estos ejemplos, se muestra distintos tipos de inyecciones, ya sea usando clases o aplicando el patrón de inyección en base a interfaces.

Se implementan desde constructores o builders que las obtienen en base a una función de inyección, a construcción de las dependencias de manera "perezosa" o lazy, con el objetivo de que la dependencia solo se cargue la primera vez que se ejecute.

## Inyección de dependencias con Dagger

Es un Framework creado inicialmente por Square y actualmente mantenido por Google para aplicaciones Java/Kotlin y
Android cuyo principal objetivo es facilitar la implementación del patrón de diseño de Inyección de Dependencias, en
otras palabras, se busca que sea Dagger2 el responsable de crear y administrar la creación de objetos en toda la
aplicación. 
![ImagenDagger](https://miro.medium.com/max/411/0*XcCXeA9iy-I4XSZ0.png)

Dagger resuelve las dependencias usando anotaciones, y generando las clases necesarias para la inyección de dependencias. El procesamiento de anotaciones requiere un tiempo de compilación adicional para generar dicho código. A veces, los cambios no se reflejan en la recompilación y requieren una limpieza del proyecto para regenerar código nuevo.

Podemos resumir el funcionamiento de Dagger2 en el siguiente diagrama:
![diagrama](https://github.com/joseluisgs/EntornosDesarrollo-08-2021-2022/raw/master/DI/DI-Dagger-Java/images/logo.png)

Tendremos un **Proveedor**, es el encargado de definir cómo se construyen las dependencias. En Dagger2 utilizamos ***
Módulos*** y cada módulo es una clase que tiene el manejo de la creación de dichas dependencias.

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
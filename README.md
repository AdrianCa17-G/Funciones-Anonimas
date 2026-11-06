# Trabajo de Consulta: Funciones Anónimas 

**Estudiante:** Adrián Guamán
**Materia:** Programación Funcional  
**Lenguajes:** Scala, Java, Python  

---

## Objetivos de aprendizaje

* Reconocer donde se usa la programacion funcional en sistemas reales.
* Explicar que es una funci´on an´onima y por qu´e es la pieza clave de las operaciones con listas.
* Identificar los tipos de problemas que se resuelven con funciones anonimas.
* Investigar nuevas operaciones sobre colecciones y aplicarlas en la solucion de problemas.
* Comparar como Scala, Java y Python implementan las funciones anonimas y argumentar, con evidencia de tu propio c´odigo, por qu´e son importantes.

## 📋 Tabla de Contenidos
1. [Parte 1: Exploración - La programación funcional en el mundo real](#parte-1-exploración---la-programación-functional-en-el-mundo-real)
2. [Parte 2: Entender - ¿Qué es una función anónima?](#parte-2-entender---qué-es-una-función-anónima)
3. [Parte 3: Aplicar - Tipos de problemas con funciones anónimas](#parte-3-aplicar---tipos-de-problemas-con-funciones-anónimas)
4. [Parte 4: Ampliar - Cuatro operaciones nuevas y combinación](#parte-4-ampliar---cuatro-operaciones-nuevas-y-combinación)
5. [Parte 5: Comparar y Concluir](#parte-5-comparar-y-concluir)

---

## Parte 1: Exploración - La programación funcional en el mundo real

Investigación de casos de uso reales de la programación funcional y funciones anónimas en la industria [cite: 1, 2].

### Caso 1: Apache Spark (Scala)
* **Problema que resuelve:** Procesamiento masivo de datos distribuidos en grandes volúmenes (Big Data) tolerante a fallos.
* **Lenguaje / Herramienta:** Scala / Apache Spark.
* **Ventaja del enfoque funcional:** La inmutabilidad de los datos y el uso de funciones de orden superior (`map`, `filter`, `reduce`) permiten paralelizar las operaciones de forma segura y eficiente en un clúster sin efectos secundarios no deseados.
* **Fragmento de código real:**
  
  ```scala
  // Ejemplo de transformación con función anónima en Spark RDD
  val lineas = sc.textFile("logs.txt")
  val errores = lineas.filter(linea => linea.contains("ERROR"))

### Caso 2: Java Streams en Sistemas Empresariales (Java)

* **Problema que resuelve:** Procesamiento declarativo y optimizado de colecciones de datos en aplicaciones corporativas de alto rendimiento.
  
* **Lenguaje / Herramienta:** Java (Streams API a partir de Java 8).
  
* **Ventaja del enfoque funcional:** Mejora la legibilidad del código al separar el qué se quiere hacer (`filtrar`, `mapear`) del cómo se ejecuta (bucle interno optimizable, soporte potencial para procesamiento paralelo con .parallelStream()).
  
* **Fragmento de código real:**

  ```Java
  // Filtrar usuarios activos y extraer sus correos
  List<String> correosActivos = usuarios.stream()
      .filter(u -> u.isActivo())
      .map(u -> u.getCorreo())
      .collect(Collectors.toList());


## Parte 2: Entender - ¿Qué es una función anónima?

### ¿Qué es una función anónima y de dónde viene el nombre lambda?

Una función anónima es una definición de función que no está vinculada a un identificador (nombre) explícito en el ámbito en el que se declara. El término lambda proviene del cálculo lambda, un sistema formal desarrollado en la década de 1930 por el matemático Alonzo Church para investigar la definición de funciones, la aplicación de funciones y la recursión.

### ¿En qué se diferencia de una función nombrada? Escribe la misma lógica de ambas formas.

Función nombrada: Posee un identificador que permite invocarla múltiples veces desde cualquier parte de su ámbito.

Función anónima: Es una expresión compacta, generalmente utilizada de forma efímera (como argumento para otra función).

Ejemplo en Python:

  ```Python
  # Función nombrada tradicional
  def cuadrado(x):
      return x * x

  # Función anónima (lambda) equivalente
  cuadrado_lambda = lambda x: x * x
  ```

### ¿Qué significa que las funciones sean ciudadanos de primera clase? ¿Por que eso permite pasar una lambda a map o filter?

Significa que las funciones son tratadas como cualquier otro valor o variable del lenguaje: pueden asignarse a variables, almacenarse en estructuras de datos, pasarse como argumentos a otras funciones y retornarse desde ellas. Esto es exactamente lo que permite inyectar una lambda directamente en métodos como map o filter.

### ¿Qué es un closure? Muestra una lambda que use una variable definida fuera de ella.

Un closure es una función que recuerda y puede acceder a las variables de su ámbito léxico exterior, incluso cuando la función es ejecutada fuera de ese ámbito original.

Ejemplo en Scala:

  ```Scala
  val umbralPrecio = 50.0 // Variable externa
  // La lambda captura 'umbralPrecio' desde fuera de su cuerpo
  val esCaro = (precio: Double) => precio > umbralPrecio
  ```

 

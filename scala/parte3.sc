case class Producto(nombre: String, categoria: String, precio: Double, stock: Int)

val productos = List(
  Producto("Laptop Gamer",           "Electronica", 850.0,   8),
  Producto("Mouse Inalambrico",      "Electronica",  25.0,  60),
  Producto("Teclado Mecanico",       "Electronica",  45.0,  40),
  Producto("Monitor Curvo",          "Electronica", 220.0,  15),
  Producto("Silla Ergonomica",       "Muebles",     130.0,  12),
  Producto("Escritorio Madera",      "Muebles",     260.0,   5),
  Producto("Lampara LED",            "Muebles",      35.0,  30),
  Producto("Cuaderno Universitario", "Papeleria",     3.5, 200),
  Producto("Esfero Azul",            "Papeleria",     0.8, 500),
  Producto("Resaltador Amarillo",    "Papeleria",     1.5, 150),
  Producto("Mochila Escolar",        "Accesorios",   40.0,  25),
  Producto("Audifonos Bluetooth",    "Accesorios",   60.0,   0)
)

// 1. SELECCION (filter): productos con precio mayor a un minimo
val seleccion = (min: Double) => productos.filter(p => p.precio > min).map(p => p.nombre)
seleccion(100)

// 2. TRANSFORMACION (map): precio con IVA del 15 % (f2 = 2 decimales con punto)
val f2 = (v: Double) => "%.2f".formatLocal(java.util.Locale.US, v)
val conIva = () => productos.map(p => p.nombre + "=" + f2(p.precio * 1.15))
conIva()

// 3. VALIDACION (exists / forall)
val hayAgotado = productos.exists(p => p.stock == 0)
val preciosValidos = productos.forall(p => p.precio > 0)

// 4. CONTEO (count): productos con stock menor a un limite
val bajoStock = (limite: Int) => productos.count(p => p.stock < limite)
bajoStock(20)

// 5. EXTREMOS (maxBy / minBy)
productos.maxBy(p => p.precio)
productos.minBy(p => p.precio)

// 6. APLANAMIENTO (flatMap): cada nombre se convierte en lista de palabras
productos.flatMap(p => p.nombre.split(" ").toList)

// 7. ACUMULACION (foldLeft / scanLeft)
val valorTotal = productos.foldLeft(0.0)((acc, p) => acc + p.precio * p.stock)

productos.scanLeft(0.0)((acc, p) => acc + p.precio * p.stock)

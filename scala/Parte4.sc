// Definición del case class y la lista de productos
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

// Ficha 1: sortBy (orden descendente por precio, toma los 5 más caros y extrae sus nombres)
val top5Precios = productos.sortBy(p => -p.precio).take(5).map(p => p.nombre)
println(s"Top 5 precios: $top5Precios")

// Ficha 2: groupBy (agrupa por categoría, cuenta cuántos hay y los ordena alfabéticamente por categoría)
val conteoPorCategoria = productos
  .groupBy(p => p.categoria)
  .map { case (cat, ps) => (cat, ps.size) }
  .toList
  .sortBy { case (cat, _) => cat }
println(s"Conteo por categoría: $conteoPorCategoria")

// Ficha 3: partition (separa en dos listas según el stock menor a 20)
val (bajos, suficientes) = productos.partition(p => p.stock < 20)
println(s"Stock bajo (${bajos.size}): ${bajos.map(_.nombre)}")
println(s"Stock suficiente (${suficientes.size}): ${suficientes.map(_.nombre)}")

// Ficha 4: zipWithIndex corregido con interpolación de cadenas (s"...")
val top3Numerados = productos
  .sortBy(p => -p.precio)
  .take(3)
  .zipWithIndex
  .map { case (p, i) => s"${i + 1}. ${p.nombre}" }
println(s"Top 3 numerados:\n${top3Numerados.mkString("\n")}")

// 4.3 Combinación: groupBy -> filter -> sortBy
val stockTotal: List[Producto] => Int = ps => ps.foldLeft(0)((acc, p) => acc + p.stock)

val categoriasConStockAlto = productos
  .groupBy(p => p.categoria)                          // 1) Agrupa por categoría
  .filter { case (_, ps) => stockTotal(ps) > 100 }    // 2) Conserva las de más de 100 unidades en total
  .toList
  .sortBy { case (_, ps) => -stockTotal(ps) }         // 3) Ordena de mayor a menor stock total
  .map { case (cat, ps) => (cat, stockTotal(ps)) }    // Devuelve una tupla (categoría, stockTotal)

println(s"Categorías con stock total > 100: $categoriasConStockAlto")
from collections import namedtuple
from functools import reduce
from itertools import accumulate, chain

Producto = namedtuple("Producto", ["nombre", "categoria", "precio", "stock"])

productos = [
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
    Producto("Audifonos Bluetooth",    "Accesorios",   60.0,   0),
]

def bool_txt(b):
    return str(b).lower()

# 1. SELECCION (filter)
caros = list(filter(lambda p: p.precio > 100, productos))
print("1. Seleccion - precio > 100: " + ", ".join(map(lambda p: p.nombre, caros)))

# 2. TRANSFORMACION (map)
con_iva = list(map(lambda p: f"{p.nombre}={p.precio * 1.15:.2f}", productos))
print("2. Transformacion - precio con IVA: " + ", ".join(con_iva))

# 3. VALIDACION (any / all)
hay_agotado = any(map(lambda p: p.stock == 0, productos))
todos_positivos = all(map(lambda p: p.precio > 0, productos))
print("3. Validacion - hay algun producto agotado: " + bool_txt(hay_agotado))
print("3. Validacion - todos los precios son positivos: " + bool_txt(todos_positivos))

# 4. CONTEO
print("4. Conteo - productos con stock < 20: "
      + str(len(list(filter(lambda p: p.stock < 20, productos)))))

# 5. EXTREMOS (max / min con key)
mas_caro = max(productos, key=lambda p: p.precio)
mas_barato = min(productos, key=lambda p: p.precio)
print(f"5. Extremos - mas caro: {mas_caro.nombre} (${mas_caro.precio:.2f})")
print(f"5. Extremos - mas barato: {mas_barato.nombre} (${mas_barato.precio:.2f})")

# 6. APLANAMIENTO (flatMap = chain.from_iterable + map)
palabras = list(chain.from_iterable(map(lambda p: p.nombre.split(" "), productos)))
print("6. Aplanamiento - palabras de los nombres: " + ", ".join(palabras))

# 7. ACUMULACION (reduce = foldLeft, accumulate = scanLeft)
valor_total = reduce(lambda acc, p: acc + p.precio * p.stock, productos, 0.0)
print(f"7. Acumulacion - valor total del inventario: {valor_total:.2f}")
parciales = list(accumulate(productos, lambda acc, p: acc + p.precio * p.stock, initial=0.0))
print("7. Acumulacion - valor acumulado paso a paso: "
      + ", ".join(map(lambda v: f"{v:.2f}", parciales)))
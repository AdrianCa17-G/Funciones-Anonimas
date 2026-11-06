import java.util.*;
import java.util.stream.*;

public class Parte3 {

    record Producto(String nombre, String categoria, double precio, int stock) {}

    static String f2(double v) {
        return String.format(Locale.US, "%.2f", v);
    }

    public static void main(String[] args) {
        List<Producto> productos = List.of(
            new Producto("Laptop Gamer",           "Electronica", 850.0,   8),
            new Producto("Mouse Inalambrico",      "Electronica",  25.0,  60),
            new Producto("Teclado Mecanico",       "Electronica",  45.0,  40),
            new Producto("Monitor Curvo",          "Electronica", 220.0,  15),
            new Producto("Silla Ergonomica",       "Muebles",     130.0,  12),
            new Producto("Escritorio Madera",      "Muebles",     260.0,   5),
            new Producto("Lampara LED",            "Muebles",      35.0,  30),
            new Producto("Cuaderno Universitario", "Papeleria",     3.5, 200),
            new Producto("Esfero Azul",            "Papeleria",     0.8, 500),
            new Producto("Resaltador Amarillo",    "Papeleria",     1.5, 150),
            new Producto("Mochila Escolar",        "Accesorios",   40.0,  25),
            new Producto("Audifonos Bluetooth",    "Accesorios",   60.0,   0)
        );

        // 1. SELECCION (filter)
        String caros = productos.stream()
            .filter(p -> p.precio() > 100)
            .map(p -> p.nombre())
            .collect(Collectors.joining(", "));
        System.out.println("1. Seleccion - precio > 100: " + caros);

        // 2. TRANSFORMACION (map)
        String conIva = productos.stream()
            .map(p -> p.nombre() + "=" + f2(p.precio() * 1.15))
            .collect(Collectors.joining(", "));
        System.out.println("2. Transformacion - precio con IVA: " + conIva);

        // 3. VALIDACION (anyMatch / allMatch)
        boolean hayAgotado = productos.stream().anyMatch(p -> p.stock() == 0);
        boolean todosPositivos = productos.stream().allMatch(p -> p.precio() > 0);
        System.out.println("3. Validacion - hay algun producto agotado: " + hayAgotado);
        System.out.println("3. Validacion - todos los precios son positivos: " + todosPositivos);

        // 4. CONTEO (count)
        long bajos = productos.stream().filter(p -> p.stock() < 20).count();
        System.out.println("4. Conteo - productos con stock < 20: " + bajos);

        // 5. EXTREMOS (max / min con Comparator)
        Producto masCaro = productos.stream()
            .max(Comparator.comparingDouble(p -> p.precio())).get();
        Producto masBarato = productos.stream()
            .min(Comparator.comparingDouble(p -> p.precio())).get();
        System.out.println("5. Extremos - mas caro: " + masCaro.nombre() + " ($" + f2(masCaro.precio()) + ")");
        System.out.println("5. Extremos - mas barato: " + masBarato.nombre() + " ($" + f2(masBarato.precio()) + ")");

        // 6. APLANAMIENTO (flatMap)
        String palabras = productos.stream()
            .flatMap(p -> Arrays.stream(p.nombre().split(" ")))
            .collect(Collectors.joining(", "));
        System.out.println("6. Aplanamiento - palabras de los nombres: " + palabras);

        // 7. ACUMULACION (reduce; scanLeft no existe en Streams, se arma con un ciclo)
        double valorTotal = productos.stream()
            .reduce(0.0, (acc, p) -> acc + p.precio() * p.stock(), Double::sum);
        System.out.println("7. Acumulacion - valor total del inventario: " + f2(valorTotal));

        List<Double> parciales = new ArrayList<>(List.of(0.0));
        double acc = 0.0;
        for (Producto p : productos) {
            acc += p.precio() * p.stock();
            parciales.add(acc);
        }
        System.out.println("7. Acumulacion - valor acumulado paso a paso: "
            + parciales.stream().map(v -> f2(v)).collect(Collectors.joining(", ")));
    }
}
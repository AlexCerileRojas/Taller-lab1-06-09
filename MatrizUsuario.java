import java.util.Scanner;

public class MatrizUsuario {

    // metodo main, solo llama al menu
    public static void main(String[] args) {
        iniciarMenu();
    }

    // Pregunta dimension al usuario
    public static int solicitarDimension(Scanner scanner, String tipoDimension) {
        int dimension;
        do {
            dimension = leerDimension(scanner, tipoDimension);
        } while (!validarDimension(dimension));
        return dimension;
    }

    // Lee y llama al metodo para validar la dimension
    public static int leerDimension(Scanner scanner, String tipoDimension) {
        int dimension;
        System.out.println("Ingrese el número de " + tipoDimension + ": ");
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada no válida. Por favor, ingrese un número entero.");
            scanner.next(); // Descartar la entrada no válida
        }
        dimension = scanner.nextInt();
        scanner.nextLine(); // Limpiar el salto de línea pendiente
        return dimension;
    }

    // Valida si las dimensiones son válidas para una matriz
    public static boolean validarDimension(int dimension) {
        if (dimension > 0) {
            return true;
        } else {
            System.out.println("La dimensión debe ser mayor que 0.");
            return false;
        }
    }

    // Crea la matriz con las dimensiones dadas por el usuario
    public static int[][] iniciarMatriz(Scanner scanner) {
        System.out.println("Creación de la matriz:");
        int filas = solicitarDimension(scanner, "filas");
        int columnas = solicitarDimension(scanner, "columnas");

        System.out.println("Dimensiones ingresadas: filas = " + filas + ", columnas = " + columnas);

        int[][] matriz = new int[filas][columnas];
        System.out.println("Matriz de " + filas + "x" + columnas + " creada exitosamente.");
        return matriz;
    }

    // Llena la matriz con valores aleatorios
    public static void llenarMatriz(int[][] matriz) {
        int filas = matriz.length;
        int columnas = matriz[0].length;

        System.out.println("Llenando la matriz con valores aleatorios...");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = (int) (Math.random() * 10);  // Llenar con valores aleatorios entre 0 y 9
            }
        }
    }

    // Muestra una fila específica de la matriz
    public static void mostrarFila(int[][] matriz, int fila) {
        if (fila >= 0 && fila < matriz.length) {
            System.out.println("Fila " + fila + ":");
            for (int valor : matriz[fila]) {
                System.out.print(valor + "\t");
            }
            System.out.println();
        } else {
            System.out.println("Índice de fila no válido.");
        }
    }
    public static boolean matrizCero(int[][] matriz) {
        int totalElementos = matriz.length * matriz[0].length;
        int contadorCeros = 0;

        for (int[] fila : matriz) {
            for (int valor : fila) {
                if (valor == 0) {
                    contadorCeros++;
                }
            }
        }
        return contadorCeros > (totalElementos / 2);
    }
    //metodo usado en el main para llamar al menu, asociado con leerOpcion, ejecutar opcion, mostrar menu
    public static void iniciarMenu() {
        Scanner scanner = new Scanner(System.in);
        int[][] matriz = null;
        boolean salir = false;
        int fila = -1;

        while (!salir) {
            mostrarMenu();
            int opcion = leerOpcion(scanner);

            if (opcion == 3 && matriz != null) {
                System.out.println("Ingrese el índice de la fila que desea mostrar:");
                fila = scanner.nextInt();
                scanner.nextLine();
            }

            matriz = ejecutarOpcion(opcion, scanner, matriz, fila);

            if (opcion == 5) {
                salir = true;
            }
        }
        scanner.close();
    }

    public static int leerOpcion(Scanner scanner) {
        System.out.print("Seleccione una opción: ");
        return scanner.nextInt();
    }

    // Ejecuta la opción elegida por el usuario
    public static int[][] ejecutarOpcion(int opcion, Scanner scanner, int[][] matriz, int fila) {
        switch (opcion) {
            case 1:
                matriz = iniciarMatriz(scanner);
                break;
            case 2:
                if (matriz != null) {
                    llenarMatriz(matriz);
                } else {
                    System.out.println("Primero debe crear la matriz (opción 1).");
                }
                break;
            case 3:
                if (matriz != null) {
                    mostrarFila(matriz, fila);
                } else {
                    System.out.println("Primero debe crear la matriz (opción 1).");
                }
                break;
            case 4:
                matrizCero(matriz);
                break;

            case 5:
                return null;
            default:
                System.out.println("Opción no válida, inténtelo de nuevo.");
        }
        return matriz;
    }

    public static void mostrarMenu() {
        System.out.println("========= Menú =========");
        System.out.println("1. Crear matriz");
        System.out.println("2. Llenar matriz");
        System.out.println("3. Mostrar fila");
        System.out.println("4. Mostrar si es matriz cero");
        System.out.println("5. Salir del menú ");
        System.out.println("========================");
    }
}

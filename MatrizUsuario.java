import java.util.Scanner;


public class MatrizUsuario {

    // metodo main, llamara al menu
    public static void main(String[] args){
        iniciarMenu();
    }

    // metodo que preguntara la dimension de la matriz
    public static int solicitarDimension(Scanner scanner, String Dimension) {
        int dimension;
        do {
            System.out.println("Ingrese el número de " + Dimension + ": ");
            // Agrega la siguiente línea para limpiar el buffer si hubo un nextInt antes
            while (!scanner.hasNextInt()) {
                System.out.println("Entrada no válida. Por favor, ingrese un número entero.");
                scanner.next();  // Descartar la entrada no válida
            }
            dimension = scanner.nextInt();
            scanner.nextLine(); // Limpiar el salto de línea pendiente
        } while (!validarDimension(dimension));
        return dimension;
    }

    //Lee la dimension y llama al metodo que valida dimensiones
    public static int leerDimension(Scanner scanner, String tipoDimension) {
        int dimension;
        do {
            System.out.println("Ingrese el número de " + tipoDimension + ": ");
            dimension = Integer.parseInt(scanner.nextLine());
        } while (!validarDimension(dimension));
        return dimension;
    }

    // Valida si las dimensiones son validas para una matriz
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

        int[][] matriz = new int[filas][columnas];
        System.out.println("Matriz de " + filas + "x" + columnas + " creada exitosamente.");
        return matriz;
    }
    //llena la matriz con valores aleatorios
    public static void llenarMatriz(int[][] matriz) {
        int filas = matriz.length;
        int columnas = matriz[0].length;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = (int) (Math.random() * 9);  // Llenar con valores aleatorios entre 0 y 9
            }
        }
    }

    public static void mostrarMatriz(int[][] matriz) {
        System.out.println("Matriz de ventas:");
        for (int[] fila : matriz) {
            for (int valor : fila) {
                System.out.print(valor + "\t");
            }
            System.out.println();
        }
    }

    public static void iniciarMenu() {
        Scanner scanner = new Scanner(System.in);
        int[][] matriz = null;


        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            int opcion = leerOpcion(scanner);

            salir = ejecutarOpcion(opcion,scanner, matriz);
        }
        scanner.close();
    }

    public static int leerOpcion(Scanner scanner) {
        return scanner.nextInt();
    }

    //ejecuta la opcion elegida por el usuario
    public static boolean ejecutarOpcion(int opcion, Scanner scanner, int[][] matriz) {
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

            case 5:
                return true; // Salir del programa
            default:
                System.out.println("Opción no válida, inténtelo de nuevo.");
        }
        return false;  // Continuar el ciclo
    }

    public static void mostrarMenu() {
        System.out.println("========= Menú =========");
        System.out.println("1. Crear matriz");
        System.out.println("2. llenar matriz ");
        System.out.println("3. Mostrar fila ");
        System.out.println("4. Mostrar si es matriz cero ");
        System.out.println("5. Salir");
        System.out.println("========================");
        System.out.print("Seleccione una opción: ");
    }

}

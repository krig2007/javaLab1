import java.util.Scanner;

public class Main  {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("___МЕНЮ___");
            System.out.println("1 - Сложение матриц");
            System.out.println("2 - Вычитание матриц");
            System.out.println("3 - Умножение матриц");
            System.out.println("4 - Деление матриц");
            System.out.println("5 - Транчпонирование");
            System.out.println("6 - Определитель");
            System.out.println("0 - Выход");
            System.out.println("Ваш выбор: ");

            int choice = sc.nextInt();

            if(choice == 0){
                System.out.println("Завершение...");
                break;
            }
            switch (choice) {
                case 1: {
                    System.out.println("___Сложение матриц___");
                    System.out.println("Матрица А: ");
                    ComplexMatrix a = readMatrix(sc);
                    System.out.println("Матрица B: ");
                    ComplexMatrix b = readMatrix(sc);

                    try {
                        ComplexMatrix result = a.add(b);
                        printResult("A + B", result);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    break;
                }
                case 2: {
                    System.out.println("___Вычитание матриц___");
                    System.out.println("Матрица А: ");
                    ComplexMatrix a = readMatrix(sc);
                    System.out.println("Матрица B: ");
                    ComplexMatrix b = readMatrix(sc);

                    try {
                        ComplexMatrix result = a.subtract(b);
                        printResult("A - B", result);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    break;
                }
                case 3: {
                    System.out.println("___Умножение матриц___");
                    System.out.println("Матрица А: ");
                    ComplexMatrix a = readMatrix(sc);
                    System.out.println("Матрица B: ");
                    ComplexMatrix b = readMatrix(sc);

                    try{
                        ComplexMatrix result = a.multiply(b);
                        printResult("A * B", result);
                    } catch (IllegalArgumentException e){
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    break;
                }
                case 4: {
                    System.out.println("___Деление матриц___");
                    System.out.println("Матрица А: ");
                    ComplexMatrix a = readMatrix(sc);
                    System.out.println("Матрица B: ");
                    ComplexMatrix b = readMatrix(sc);

                    try{
                        ComplexMatrix result = a.divide(b);
                        printResult("A/B", result);
                    } catch (IllegalArgumentException | ArithmeticException e){
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    break;
                }
                case 5: {
                    System.out.println("___Транспонирование матрицы___");
                    System.out.println("Матрица А: ");
                    ComplexMatrix a = readMatrix(sc);

                    ComplexMatrix result = a.transpose();
                    printResult("A^T", result);
                    break;
                }
                case 6: {
                    System.out.println("___Определитель матрицы___");
                    System.out.println("Матрица А: ");
                    ComplexMatrix a = readMatrix(sc);

                    try {
                        ComplexNumber det = a.determinant();
                        printResult("det(A)", det);
                    } catch (IllegalArgumentException | UnsupportedOperationException e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    break;
                }
                default:
                    System.out.println("Неверный выбор, попробуйте снова");
            }


        }
        sc.close();

    }

    static ComplexNumber readComplex(Scanner sc, String name){
        System.out.print("  " + name + " re: ");
        double re = sc.nextDouble();
        System.out.print("  " + name + " im: ");
        double im = sc.nextDouble();
        return new ComplexNumber(re, im);
    }

    static ComplexMatrix readMatrix(Scanner sc){
        System.out.print("Введите количество строк: ");
        int rows = sc.nextInt();
        System.out.print("Введите количество столбцов: ");
        int cols = sc.nextInt();

        ComplexMatrix m = new ComplexMatrix(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("  Элемент [" + i + "][" + j + "]:");
                ComplexNumber value = readComplex(sc, "");
                m.set(i, j, value);
            }
        }
        return m;
    }

    static void printResult(String label, ComplexNumber value) {
        System.out.println(label + ": " + value);
    }

    static void printResult(String label, ComplexMatrix value) {
        System.out.println(label + ":");
        System.out.println(value);
    }
}
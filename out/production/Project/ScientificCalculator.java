import java.util.Scanner;
public class ScientificCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            //Display menu
            System.out.println("Scientific calculator");
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");
            System.out.println("5. Square Root");
            System.out.println("6. Power");
            System.out.println("7. Logarithm");
            System.out.println("8. Trigonometric Functions");
            System.out.println("9. Exit");
            System.out.println("Choose an option: ");

            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    addition(scanner);
                    break;
                case 2:
                    subtraction(scanner);
                    break;
                case 3:
                    multiplication(scanner);
                    break;
                case 4:
                    division(scanner);
                    break;
                case 5:
                    squareRoot(scanner);
                    break;
                case 6:
                    power(scanner);
                    break;
                case 7:
                    logarithm(scanner);
                    break;
                case 8:
                    trigonometricFunctions(scanner);
                    break;
                case 9:
                    System.out.println("Exiting the calculator. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 9);
        scanner.close();

    }
    //Method for Addition
        public static void addition(Scanner scanner){
            System.out.println("Enter two numbers: ");
            double a = scanner.nextDouble();
            double b = scanner.nextDouble();
            System.out.println("Result: " +(a+b));
        }
    //Method for Subtraction
        public static void subtraction(Scanner scanner){
            System.out.println("Enter two numbers: ");
            double a = scanner.nextDouble();
            double b = scanner.nextDouble();
            System.out.println("Result: " +(a-b));
        }
    //Method for Multiplication
        public static void multiplication(Scanner scanner){
            System.out.println("Enter two numbers: ");
            double a = scanner.nextDouble();
            double b = scanner.nextDouble();
            System.out.println("Result: " +(a*b));
        }
    //Method for Division
        public static void division(Scanner scanner){
            System.out.println("Enter two numbers: ");
            double a = scanner.nextDouble();
            double b = scanner.nextDouble();
            if(b == 0){
                System.out.println("Error: Division by zero is not allowed");
            }
            else{
                System.out.println("Result: " +(a/b));
            }
        }
    //Method for Square Root
        public static void squareRoot(Scanner scanner){
            System.out.println("Enter a number: ");
            double a = scanner.nextDouble();
            System.out.println("Square Root: " + Math.sqrt(a));
        }
    //Method for Power
        public static void power(Scanner scanner){
            System.out.println("Enter base and exponent: ");
            double base = scanner.nextDouble();
            double exponent = scanner.nextDouble();
            System.out.println("Result: " + Math.pow(base ,exponent));
        }
    //Method for Logarithm
        public static void logarithm(Scanner scanner){
            System.out.println("Enter a number: ");
            double a = scanner.nextDouble();
            System.out.println("Square Root: " + Math.log(a));
        }
    //Method for Trigonometric Functions
        public static void trigonometricFunctions(Scanner scanner){
            System.out.println("Enter an angle in degrees: ");
            double angle = scanner.nextDouble();
            System.out.println("Sin: " + Math.sin(angle));
            System.out.println("Cos: " + Math.cos(angle));
            System.out.println("Tan: " + Math.tan(angle));
        }
}
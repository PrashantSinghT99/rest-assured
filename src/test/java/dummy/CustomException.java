package dummy;

//Custom exception class
class MyException extends Exception {
 public MyException(String message) {
     super(message);
 }
}

//Example usage of the custom exception
public class CustomException {
 public static void main(String[] args) {
     try {
         // Simulating a situation where a custom exception might be thrown
         int result = divide(10, 0);
         System.out.println("Result: " + result);
     } catch (MyException e) {
         System.err.println("CustomException caught: " + e.getMessage());
     }
 }

 // Method that may throw a custom exception
 public static int divide(int numerator, int denominator) throws MyException {
     if (denominator == 0) {
         // Throw a custom exception with a descriptive message
         throw new MyException("Cannot divide by zero");
     }
     return numerator / denominator;
 }
}


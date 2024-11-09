import java.util.Scanner;
import java.util.Random;

public class PasswordGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input for name and city
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Where do you live?: ");
        String city = scanner.nextLine();

        // Generate password
        String password = generatePassword(name, city);

        // Output the generated password
        System.out.println("Your Password is: " + password);
    }

    private static String generatePassword(String name, String city) {
        StringBuilder password = new StringBuilder();

        // Add every 3rd letter from the name
        for (int i = 2; i < name.length(); i += 3) {
            password.append(name.charAt(i));
        }

        // Add every 2nd letter from the city
        for (int i = 1; i < city.length(); i += 2) {
            password.append(city.charAt(i));
        }

        // Check if the password is at least 8 characters, if not, fill with random characters
        while (password.length() < 8) {
            password.append(getRandomChar());
        }

        // Ensure password meets all conditions
        if (!containsUpperCase(password)) {
            password.setCharAt(0, (char) (password.charAt(0) - 32)); // Force an uppercase letter
        }
        if (!containsLowerCase(password)) {
            password.setCharAt(password.length() - 1, (char) (password.charAt(password.length() - 1) + 32)); // Force a lowercase letter
        }
        if (!containsNumber(password)) {
            password.append(getRandomNumber()); // Add a random number if none is present
        }
        if (!containsSpecialCharacter(password)) {
            password.append(getRandomSpecialChar()); // Add a special character if none is present
        }

        // Truncate password to 8 characters if it's longer
        if (password.length() > 8) {
            password.setLength(8);
        }

        return password.toString();
    }

    // Helper methods to check conditions
    private static boolean containsUpperCase(StringBuilder password) {
        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsLowerCase(StringBuilder password) {
        for (int i = 0; i < password.length(); i++) {
            if (Character.isLowerCase(password.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsNumber(StringBuilder password) {
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsSpecialCharacter(StringBuilder password) {
        String specialChars = "!@#$%^&*()-_=+[]{}|;:,.<>?";
        for (int i = 0; i < password.length(); i++) {
            if (specialChars.indexOf(password.charAt(i)) != -1) {
                return true;
            }
        }
        return false;
    }

    // Helper methods to generate random characters
    private static char getRandomChar() {
        Random rand = new Random();
        return (char) ('a' + rand.nextInt(26)); // Random lowercase letter
    }

    private static char getRandomNumber() {
        Random rand = new Random();
        return (char) ('0' + rand.nextInt(10)); // Random digit
    }

    private static char getRandomSpecialChar() {
        String specialChars = "!@#$%^&*()-_=+[]{}|;:,.<>?";
        Random rand = new Random();
        return specialChars.charAt(rand.nextInt(specialChars.length()));
    }
}

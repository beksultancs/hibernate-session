package peaksoft.java5;

import peaksoft.java5.models.Client;
import peaksoft.java5.services.ClientService;

import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {
        ClientService clientService = new ClientService();
        Scanner scanner = new Scanner(System.in);
        Scanner scannerForChar = new Scanner(System.in);

        loop:
        while (true) {
            System.out.println("""
                press r to register
                press f to show all clients
                press l to log in
                press q to quit
                    """);

            char operation = scannerForChar.next().charAt(0);

            switch (operation) {
                case 'r' -> {
                    System.out.println("You're trying to register new client!");
                    System.out.print("first name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("last name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("email: ");
                    String email = scanner.nextLine();
                    System.out.print("phone number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("password: ");
                    String password = scanner.nextLine();
                    clientService.register(new Client(firstName, lastName, phoneNumber, email, password));
                }
                case 'f' -> clientService.findAll().forEach(System.out::println);
                case 'l' -> {
                    System.out.println("you're trying to log in!");
                    System.out.print("write your email: ");
                    String email = scanner.nextLine();
                    System.out.print("write your password: ");
                    String password = scanner.nextLine();
                    try {
                        clientService.login(email, password);
                        System.out.println("You're successfully logged in!");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 'q' -> {
                    break loop;
                }
                default -> throw new IllegalArgumentException(
                        "you pressed wrong letter"
                );
            }
        }

        clientService.close();
    }
}

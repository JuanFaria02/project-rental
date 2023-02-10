package application;

import model.entities.Client;
import resources.ClientResources;

import java.util.Scanner;

public class ControllerOptions {
    private static ClientResources clientResources = new ClientResources();
    public static void makeInsert(Integer option) {
        Scanner scan = new Scanner(System.in);
        switch (option) {
            case 1:
                System.out.print("What's your name: ");
                String name = scan.nextLine();
                System.out.print("What's your cpf (Only numbers): ");
                String cpf = scan.next();
                String client = clientResources.insert(new Client(name.toLowerCase(), cpf));
                System.out.println("Insert client: " + client);
                break;
        }
    }

    public static void makeDelete(Integer option) {
        Scanner scan = new Scanner(System.in);
        switch (option) {
            case 1:
                System.out.println("");

                System.out.println(clientResources.findAll());
                System.out.print("What's the id of client that you want delete: ");


                Integer idClient = scan.nextInt();
                boolean result = clientResources.deleteById(idClient);
                if (!result) {
                    System.out.println("Delete fail: " + result);
                } else {
                    System.out.println("Delete made: " + result);
                }
                break;
        }
    }
    public static void makeUpdate(Integer option) {
        Scanner scan = new Scanner(System.in);
        switch (option) {
            case 1:
                System.out.println(clientResources.findAll());
                System.out.print("What's your name: ");
                String name = scan.nextLine();
                System.out.print("What's the cpf of client that you want update (Only numbers): ");
                String cpf = scan.next();

                System.out.print("What's the id of client that you want update: ");
                Integer id = scan.nextInt();
                Client client = new Client(name.toLowerCase(), cpf);
                client.setId(id);
                String clientString = clientResources.update(client);
                System.out.println("Update client: " + clientString);

                break;
        }
    }

    public static void makeSearch(Integer option) {
        Scanner scan = new Scanner(System.in);
        switch (option) {
            case 1:
                System.out.println("Are you want: ");
                System.out.println("");
                System.out.println("(1) Find all");
                System.out.println("(2) Find By CPF");
                System.out.print("Your choose: ");
                Integer choose = scan.nextInt();
                if (choose == 1) {
                    System.out.println(clientResources.findAll());
                }
                else if (choose == 2) {
                    System.out.print("What's the cpf: ");
                    String cpf = scan.next();
                    System.out.println(clientResources.findByCpf(cpf));
                }
                break;
        }
    }
}

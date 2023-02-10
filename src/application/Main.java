package application;



import resources.*;
import services.exception.ServiceException;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        try {
            UI.printHome();

            System.out.print("Choose one: ");
            Integer res = scan.nextInt();
            UI.clearScreen();
            switch (res) {
                case 1:
                    UI.printInsertHome();
                    System.out.print("Choose one: ");
                    Integer option = scan.nextInt();
                    ControllerOptions.makeInsert(option);
                    break;
                case 2:
                    UI.printUpdateHome();
                    System.out.print("Choose one: ");
                    option = scan.nextInt();
                    ControllerOptions.makeUpdate(option);
                    break;
                case 3:
                    UI.printDeleteHome();
                    System.out.print("Choose one: ");
                    option = scan.nextInt();
                    ControllerOptions.makeDelete(option);
                    break;
                case 4:
                    UI.printSearchHome();
                    System.out.print("Choose one: ");
                    option = scan.nextInt();
                    ControllerOptions.makeSearch(option);
                    break;
            }
        }
        catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
        catch (ServiceException e1){
            System.out.println(e1.getMessage());
        }
        finally {
            scan.close();
        }
    }
}
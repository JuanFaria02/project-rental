package application;

public class UI {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void printHome() {
        System.out.println("------------------------RENTAL--------------------------");
        System.out.println("");
        System.out.println("                 What you desire make?                  ");

        System.out.println("");
        System.out.println("                      (1) Insert                        ");
        System.out.println("                      (2) Update                        ");
        System.out.println("                      (3) Delete                        ");
        System.out.println("                      (4) Search                        ");

        System.out.println("--------------------------------------------------------");
    }

    public static void printInsertHome() {
        System.out.println("                What you want insert?                   ");
        System.out.println("");
        System.out.println("                      (1) Client                        ");
        System.out.println("                      (2) Movie                         ");
        System.out.println("                      (3) Rental                        ");
        System.out.println("                      (4) Type                          ");
        System.out.println("--------------------------------------------------------");
    }
    public static void printUpdateHome() {
        System.out.println("                What you want update?                   ");
        System.out.println("");
        System.out.println("                      (1) Client                        ");
        System.out.println("                      (2) Movie                         ");
        System.out.println("                      (3) Rental                        ");
        System.out.println("                      (4) Type                          ");
        System.out.println("--------------------------------------------------------");
    }
    public static void printDeleteHome() {
        System.out.println("                What you want delete?                   ");
        System.out.println("");
        System.out.println("                      (1) Client                        ");
        System.out.println("                      (2) Movie                         ");
        System.out.println("                      (3) Rental                        ");
        System.out.println("                      (4) Type                          ");
        System.out.println("--------------------------------------------------------");
    }
    public static void printSearchHome() {
        System.out.println("                What you want search?                   ");
        System.out.println("");
        System.out.println("                      (1) Client                        ");
        System.out.println("                      (2) Movie                         ");
        System.out.println("                      (3) Rental                        ");
        System.out.println("                      (4) Type                          ");
        System.out.println("--------------------------------------------------------");
    }

}

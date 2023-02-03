package application;



import resources.TypeResources;




public class Main {
    public static void main(String[] args) {
        TypeResources typeResources = new TypeResources();
        System.out.println(typeResources.findById(2));

    }
}
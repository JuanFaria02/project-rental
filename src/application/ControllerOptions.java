package application;

import com.google.gson.Gson;
import model.entities.*;
import resources.*;
import services.MovieTypeService;


import java.util.*;
import java.util.List;


public class ControllerOptions {
    private static ClientResources clientResources = new ClientResources();
    private static MovieResources movieResources = new MovieResources();
    private static MovieTypeResource movieTypeResource = new MovieTypeResource();
    private static TypeResources typeResources = new TypeResources();
    private static MediaResource mediaResource = new MediaResource();
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
            case 2:

                System.out.print("What's the tittle: ");
                String tittle = scan.nextLine();
                System.out.print("What's the name of director: ");
                String directorName = scan.next();
                Movie movie = new Movie(tittle.toLowerCase(), directorName);

                System.out.print("What's the types of this movie: ");
                System.out.println();
                System.out.println("TYPES: ");
                System.out.println();
                System.out.println(typeResources.findAll());
                List<String> typeName = new ArrayList<>();

                System.out.println("How many types this movie have: ");
                int numberOfTypes = scan.nextInt();
                for (int i = 0; i < numberOfTypes; i++) {
                    String namesType = scan.next();
                    typeName.add(namesType);
                }


                for (String tn:
                     typeName) {
                    Type type = new Gson().fromJson(typeResources.findByName(tn), Type.class);
                    movie.getTypeSet().add(type);
                }

                System.out.println();

                String movieString = movieResources.insert(movie);


                System.out.println("Movie insert: " + movieString);
                insertMovieType(movie);
                insertMedia(movie);
                break;
            case 4:

                String type = scan.nextLine();

                String typeString = typeResources.insert(new Type(type.toLowerCase()));
                System.out.println("Insert type: " + typeString);
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
            case 2:
                System.out.println("");

                System.out.println(movieResources.findAll());
                System.out.print("What's the id of movie that you want delete: ");


                Integer idMovie = scan.nextInt();
                deleteMovieType(idMovie);
                boolean resultMovie = movieResources.deleteById(idMovie);
                if (!resultMovie) {
                    System.out.println("Delete fail");
                } else {
                    System.out.println("Delete made");
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

    private static void insertMovieType(Movie movie) {
        for (Iterator<Type> type = movie.getTypeSet().iterator();
             type.hasNext(); ) {

            movieTypeResource.insert(new MovieType(movie, type.next()));
        }
    }
    private static void deleteMovieType(Integer idMovie) {
        Scanner scan = new Scanner(System.in);
        movieTypeResource.findByName(new Gson()
                .fromJson(movieResources.findById(idMovie), Movie.class)
                .getTittle());

        movieTypeResource.deleteById(idMovie);
    }
    private static void insertMedia(Movie movie) {
        mediaResource.insert(new Media(movie));
    }
}


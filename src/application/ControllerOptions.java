package application;

import com.google.gson.Gson;

import model.entities.*;
import resources.*;



import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;


public class ControllerOptions {
    private static ClientResources clientResources = new ClientResources();
    private static MovieResources movieResources = new MovieResources();
    private static MovieTypeResource movieTypeResource = new MovieTypeResource();
    private static TypeResources typeResources = new TypeResources();
    private static MediaResource mediaResource = new MediaResource();
    private static RentalResource rentalResource = new RentalResource();

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


                for (String tn :
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
            case 3:
                //Rental
                System.out.println(mediaResource.findAll());
                System.out.println();
                System.out.print("What's the media Id: ");
                Integer idMedia = scan.nextInt();
                System.out.println();

                System.out.println(clientResources.findAll());
                System.out.print("What's the Client id: ");

                Integer idClient= scan.nextInt();
                System.out.println();
                Media media = new Gson().fromJson(mediaResource.findById(idMedia), Media.class);
                Client clientRental = new Gson().fromJson(clientResources.findById(idClient), Client.class);

                String rental = rentalResource.insert(new Rental(media,
                        clientRental, Date.valueOf(LocalDate.now())));

                System.out.println("Insert rental: " + rental);
                break;
            case 4:
                System.out.println("What's the name of type: ");
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
                deleteMovieTypeByIdMovie(idMovie);
                boolean resultMovie = movieResources.deleteById(idMovie);
                if (!resultMovie) {
                    System.out.println("Delete fail");
                } else {
                    System.out.println("Delete made");
                }
                break;
            case 3:
                //Rental
                System.out.println("");

                System.out.println(rentalResource.findAll());
                System.out.print("What's the id of rental that you want delete: ");


                Integer idRental = scan.nextInt();

                boolean resultRental = rentalResource.deleteById(idRental);
                if (!resultRental) {
                    System.out.println("Delete fail");
                } else {
                    System.out.println("Delete made");
                }
                break;
            case 4:
                System.out.println(typeResources.findAll());
                System.out.println("What's the id type that you want delete: ");
                Integer idType = scan.nextInt();

                deleteMovieTypeByIdType(idType);

                boolean resultType = typeResources.deleteById(idType);



                if (!resultType) {
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
                //CLIENT
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
            case 2:
                //MOVIE

                System.out.println(movieResources.findAll());
                System.out.print("What's the tittle: ");
                String tittle = scan.nextLine();
                System.out.print("What's the name of the director that you want update: ");
                String director = scan.nextLine();

                System.out.print("What's the id of movie that you want update: ");
                id = scan.nextInt();
                Movie movie = new Movie(tittle.toLowerCase(), director);
                movie.setId(id);
                System.out.println("Do you want update the types of your movie(y/n): ");
                String res = scan.next();
                if (res.charAt(0) == 'y') {

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
                    for (String tn :
                            typeName) {
                        Type type = new Gson().fromJson(typeResources.findByName(tn), Type.class);
                        movie.getTypeSet().add(type);
                    }
                    deleteMovieTypeByIdMovie(movie.getId());
                    insertMovieType(movie);
                }


                String movieString = movieResources.update(movie);

                System.out.println("Update client: " + movieString);
                break;
            case 3:
                //Rental
                System.out.println(mediaResource.findAll());
                System.out.println();
                System.out.print("What's the media Id: ");
                Integer idMedia = scan.nextInt();
                System.out.println();

                System.out.println(clientResources.findAll());
                System.out.print("What's the Client id: ");
                Integer idClient= scan.nextInt();
                System.out.println();

                System.out.print("What's the date of rental(dd/MM/yyyy): ");
                LocalDate moment = LocalDate.parse(scan.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                System.out.println();
                System.out.println(rentalResource.findAll());
                System.out.print("What's the Rental id: ");
                Integer idRental= scan.nextInt();
                System.out.println();

                Media media = new Gson().fromJson(mediaResource.findById(idMedia), Media.class);
                Client clientRental = new Gson().fromJson(clientResources.findById(idClient), Client.class);

                Rental rental = new Rental(media,
                        clientRental, Date.valueOf(moment));
                rental.setId(idRental);
                String rentalString = rentalResource.update(rental);

                System.out.println("Update client: " + rentalString);
                break;
            case 4:
                //TYPE
                System.out.println("What's the name of type that you want update: ");
                String typeName = scan.nextLine();
                System.out.println();

                System.out.println(typeResources.findAll());
                System.out.println("What's the id of type that you want update: ");
                Integer idType = scan.nextInt();
                System.out.println();

                Type type = new Type(typeName.toLowerCase());
                type.setId(idType);
                String typeString = typeResources.update(type);

                System.out.println("Update type: " + typeString);
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
                } else if (choose == 2) {
                    System.out.print("What's the cpf: ");
                    String cpf = scan.next();
                    System.out.println(clientResources.findByCpf(cpf));
                }
                break;
            case 2:
                System.out.println("Are you want: ");
                System.out.println("");
                System.out.println("(1) Find all");
                System.out.println("(2) Find By Id");
                System.out.println("(3) Find By Director");
                System.out.print("Your choose: ");
                choose = scan.nextInt();
                if (choose == 1) {
                    System.out.println(movieResources.findAll());
                } else if (choose == 2) {
                    System.out.print("What's the id: ");
                    Integer id = scan.nextInt();
                    System.out.println(movieResources.findById(id));
                }
                else if (choose == 3) {
                    System.out.print("What's the director name: ");
                    String directorName = scan.next();
                    System.out.println(movieResources.findByDirector(directorName));
                }
                break;
            case 3:
                System.out.println("Are you want: ");
                System.out.println("");
                System.out.println("(1) Find all");
                System.out.println("(2) Find By name of client");
                System.out.println("(3) Find By Id");
                System.out.print("Your choose: ");
                choose = scan.nextInt();
                if (choose == 1) {
                    System.out.println(rentalResource.findAll());
                } else if (choose == 2) {

                    System.out.print("What's the name of client: ");
                    scan.nextLine();
                    String name = scan.nextLine();

                    System.out.println(rentalResource.findByNameClient(name.toLowerCase()));
                } else if (choose == 3) {
                    System.out.print("What's the id: ");
                    Integer id = scan.nextInt();
                    System.out.println(rentalResource.findById(id));
                }
                break;
            case 4:
                System.out.println("Are you want: ");
                System.out.println("");
                System.out.println("(1) Find all");
                System.out.println("(2) Find By Name");
                System.out.println("(3) Find By Id");
                System.out.print("Your choose: ");
                choose = scan.nextInt();
                if (choose == 1) {
                    System.out.println(typeResources.findAll());
                } else if (choose == 2) {
                    System.out.print("What's the name of type: ");
                    String name = scan.next();
                    System.out.println(typeResources.findByName(name.toLowerCase()));
                }
                else if (choose == 3) {
                    System.out.print("What's the id: ");
                    Integer id = scan.nextInt();
                    System.out.println(typeResources.findById(id));
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

    private static void deleteMovieTypeByIdMovie(Integer idMovie) {

        movieTypeResource.findByName(new Gson()
                .fromJson(movieResources.findById(idMovie), Movie.class)
                .getTittle());

        movieTypeResource.deleteByIdMovie(idMovie);
    }

    private static void insertMedia(Movie movie) {
        mediaResource.insert(new Media(movie));
    }
    private static void deleteMovieTypeByIdType(Integer idType) {
        movieTypeResource.deleteByIdType(idType);
    }
}
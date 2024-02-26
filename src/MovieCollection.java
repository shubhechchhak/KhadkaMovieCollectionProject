import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class MovieCollection {
    private ArrayList<Movie> movies;
    public MovieCollection() {
        movies = new ArrayList<Movie>();
        importMovies();
        play();
    }

    public ArrayList<Movie> getMovies() { //getter method for movies
        return movies;
    }

    public void play() {
        printMenu();
    }

    public void importMovies() {
        try{
            File myFile = new File("src//movies_data.csv");
            Scanner scan = new Scanner(myFile);
            while(scan.hasNext()){
                String data = scan.nextLine();
                String[] values = data.split(",");
                String title = values[0];
                String cast = values[1];
                String[] actorList = cast.split("\\|");
                String director = values[2];
                String overview = values[3];
                int runtime = Integer.parseInt(values[4]);
                double userRating = Double.parseDouble(values[5]);
                Movie movie = new Movie(title, cast, director, overview, runtime, userRating);
                movies.add(movie);
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void printMenu() {
        System.out.println("Welcome to the movie collection!");
        String menuOption = "";

        while (!menuOption.equals("q")) {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (c)ast");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            Scanner scanner = new Scanner(System.in);
            menuOption = scanner.nextLine();
            if (menuOption.equals("t")) {
                searchTitles();
            } else if (menuOption.equals("c")) {
                searchCast();
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }
            scanner.close();
        }
    }


    public void searchTitles() {
        System.out.println("------------ Search Titles ----------");
        System.out.print("Enter a title search term: ");
        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();
        ArrayList<Movie> results = new ArrayList<Movie>();
        for (Movie movie : movies) {
            if (movie.getTitle().toLowerCase().contains(title.toLowerCase())) {
                results.add(movie);
            }
        }
        if (results.size() == 0) {
            System.out.println("No movie titles match that search term!");
        } else {
            for (int i = 0; i < results.size(); i++) {
                System.out.println(i + ". " + results.get(i));
            }
        }
        System.out.println("Enter number: ");
        int num = scanner.nextInt();
        Movie movie = movies.get(num);
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Runtime: " + movie.getRuntime());
        System.out.println("Directed by: " + movie.getDirector());
        System.out.println("Cast: " + movie.getCast());
        System.out.println("Overview: " + movie.getOverview());
        System.out.println("User rating: " + movie.getUserRating());
        System.out.println();
        scanner.close();
    }

    public void searchCast() {
        System.out.println("------------ Search Cast ----------");
        System.out.print("Enter cast: ");
        Scanner scanner = new Scanner(System.in);
        String cast = scanner.nextLine();
        ArrayList<Movie> results = new ArrayList<Movie>();
        for (Movie movie : movies) {
            if (movie.getCast().toLowerCase().contains(cast.toLowerCase())) {
                results.add(movie);
            }
        }
        if (results.size() == 0) {
            System.out.println("No results found.");
        } else {
            System.out.println("Results:");
            for (Movie movie : results) {
                System.out.println(movie);
            }
        }
        System.out.println("Enter number: ");
        int num = scanner.nextInt();
        Movie movie = movies.get(num);
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Runtime: " + movie.getRuntime());
        System.out.println("Directed by: " + movie.getDirector());
        System.out.println("Cast: " + movie.getCast());
        System.out.println("Overview: " + movie.getOverview());
        System.out.println("User rating: " + movie.getUserRating());
        System.out.println();
        scanner.close();
    }
}

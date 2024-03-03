import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class MovieCollection {
    ArrayList<Movie> movies;
    ArrayList<Movie> results;
    ArrayList<String> results2;

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
            scan.nextLine();
            while(scan.hasNext()){
                String data = scan.nextLine();
                String[] values = data.split(",");
                String title = values[0];
                String cast = values[1];
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
        System.out.println("|----------------------------------|");
        System.out.println("  Welcome to the movie collection!");
        System.out.println("|----------------------------------|");
        System.out.println();
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
                System.out.println();
                System.out.println("------------ Search Titles ----------");
                System.out.print("Enter a title search term: ");
                String title = scanner.nextLine();
                searchTitles(title);
            } else if (menuOption.equals("c")) {
                System.out.println();
                System.out.println("------------ Search Cast ----------");
                System.out.print("Enter a person to search for (first or last name): ");
                String cast = scanner.nextLine();
                searchCast(cast);
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye! Thank you for using my collection!");
            } else {
                System.out.println("Invalid choice!");
                System.out.println();
            }
        }
    }


    public void searchTitles(String title) { //helper method used to look for movie titles
        results = new ArrayList<Movie>();
        for (Movie movie : movies) { //using for loop to add movies that contain title to a new Arraylist: results
            if (movie.getTitle().toLowerCase().contains(title.toLowerCase())) {
                results.add(movie);
            }
        }
        selectionSortMovie(results); //sorting the movies alphabetically
        if (results.size() == 0) {
            System.out.println("No movie titles match that search term!");
            System.out.println();
        } else {
            for (int i = 0; i < results.size(); i++) {
                System.out.println((i + 1) + ". " + results.get(i).getTitle());
            }
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.print("Enter number: ");
        int num = scanner.nextInt();
        Movie movie = results.get(num - 1);
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Runtime: " + movie.getRuntime() + " minutes");
        System.out.println("Directed by: " + movie.getDirector());
        System.out.println("Cast: " + movie.getCast());
        System.out.println("Overview: " + movie.getOverview());
        System.out.println("User rating: " + movie.getUserRating());
        System.out.println();
    }

    public void searchCast(String cast) { //helper method used to search for movie stars
        Scanner scanner = new Scanner(System.in);
        results2 = new ArrayList<String>();
        for (int i = 0; i < movies.size(); i++) {
            String actors = movies.get(i).getCast();
            String[] actorList = actors.split("\\|"); //splitting the cast
            for (String actor : actorList) {
                if (actor.toLowerCase().contains(cast.toLowerCase()) && !results2.contains(actor)) {
                    results2.add(actor);
                }
            }
        }
        selectionSortCast(results2); //sorting through the string of casts alphabetically
        if (results2.size() == 0) {
            System.out.println("No results found.");
            System.out.println();
        } else {
            for (int j = 0; j < results2.size(); j++) {
                System.out.println((j + 1) + ". " + results2.get(j));
            }
            System.out.println();
            System.out.println("Who would you like to see all movies for?");
            System.out.print("Enter number: ");
            int num = scanner.nextInt();
            if (num < 1 || num > results2.size()) {
                System.out.println("Invalid choice!");
                System.out.println();
            } else {
                ArrayList<Movie> castMovies = new ArrayList<>();
                for (int i = 0; i < movies.size(); i++) {
                    if (movies.get(i).getCast().toLowerCase().contains(results2.get(num - 1).toLowerCase())) {
                        castMovies.add(movies.get(i));
                    }
                }
                selectionSortMovie(castMovies); //sorting the movies of the actor alphabetically
                int count = 1;
                for (int m = 0; m < castMovies.size(); m++) {
                    System.out.println(count + ". " + castMovies.get(m).getTitle());
                    count++;
                }
                System.out.println();
                System.out.println("Which movie would you like to learn more about?");
                System.out.print("Enter number: ");
                int movieChoice = scanner.nextInt();
                Movie movie = castMovies.get(movieChoice - 1);
                System.out.println("Title: " + movie.getTitle());
                System.out.println("Runtime: " + movie.getRuntime() + " minutes");
                System.out.println("Directed by: " + movie.getDirector());
                System.out.println("Cast: " + movie.getCast());
                System.out.println("Overview: " + movie.getOverview());
                System.out.println("User rating: " + movie.getUserRating());
                System.out.println();
            }
        }
    }

    public static void selectionSortMovie(ArrayList<Movie> string) { //helper method used to alphabetically sort Movie objects
        for (int i = 0; i < string.size(); i++) {
            for (int j = i + 1; j < string.size(); j++) {
                if (string.get(j).getTitle().compareTo(string.get(i).getTitle()) < 0) {
                    Movie wordI = string.get(i);
                    string.set(i, string.get(j));
                    string.set(j, wordI);
                }
            }
        }
    }

    public static void selectionSortCast(ArrayList<String> string) { //helper method used to alphabetically sort String objects
        for (int i = 0; i < string.size(); i++) {
            for (int j = i + 1; j < string.size(); j++) {
                if (string.get(j).compareTo(string.get(i)) < 0) {
                    String wordI = string.get(i);
                    string.set(i, string.get(j));
                    string.set(j, wordI);
                }
            }
        }
    }
}


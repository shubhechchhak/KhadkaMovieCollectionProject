public class Movie {
    private String title;
    private String cast;
    private String director;
    private String overview;
    private int runtime;
    private double userRating;

    public Movie(String title, String cast, String director, String overview, int runtime, double userRating) {
        this.title = title;
        this.cast = cast;
        this.director = director;
        this.overview = overview;
        this.runtime = runtime;
        this.userRating = userRating;
    }

    public String toString(){
        return "Title: " + title + "\nRuntime: " + runtime  + "\nDirector: " + director + "\nCast: " + cast + "\nOverview: " + overview + "\nUser Rating: " + userRating;
    }
    public String getTitle() {
        return this.title;
    }

    public String getCast() {
        return this.cast;
    }

    public String getDirector() {
        return this.director;
    }

    public String getOverview() {
        return this.overview;
    }

    public int getRuntime() {
        return this.runtime;
    }

    public double getUserRating() {
        return this.userRating;
    }
}

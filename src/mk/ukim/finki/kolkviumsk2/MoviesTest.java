package mk.ukim.finki.kolkviumsk2;

import java.util.*;
import java.util.stream.Collectors;

class Movie {
    String title;
    int[] ratings;

    public Movie(String title, int[] ratings) {
        this.title = title;
        this.ratings = ratings;
    }

    double getAvgRating() {
        return (double) Arrays.stream(ratings).sum() / ratings.length;
    }

    public String getTitle() {
        return title;
    }

    public double getCoef() {
        return getAvgRating() * ratings.length / MoviesList.MAX;
    }

    @Override
    public String toString() {
        return String.format("%s (%.2f) of %d ratings", title, getAvgRating(), ratings.length);
    }

    public int getNumOfRatings() {
        return ratings.length;
    }
}

class MoviesList {
    List<Movie> movies;
    static int MAX;

    public MoviesList() {
        this.movies = new ArrayList<>();
    }

    public void addMovie(String title, int[] ratings) {
        movies.add(new Movie(title, ratings));
    }

    public List<Movie> top10ByAvgRating() {
        return movies.stream().sorted(Comparator.comparing(Movie::getAvgRating).reversed().thenComparing(Movie::getTitle)).collect(Collectors.toList()).subList(0, 10);
    }

    public List<Movie> top10ByRatingCoef() {
        MAX = movies.stream().mapToInt(Movie::getNumOfRatings).max().getAsInt();
        List<Movie> sorted = movies.stream().sorted(Comparator.comparing(Movie::getCoef).reversed().thenComparing(Movie::getTitle)).collect(Collectors.toList());
        return sorted.subList(0, 10);
    }
}

public class MoviesTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MoviesList moviesList = new MoviesList();
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String title = scanner.nextLine();
            int x = scanner.nextInt();
            int[] ratings = new int[x];
            for (int j = 0; j < x; ++j) {
                ratings[j] = scanner.nextInt();
            }
            scanner.nextLine();
            moviesList.addMovie(title, ratings);
        }
        scanner.close();
        List<Movie> movies = moviesList.top10ByAvgRating();
        System.out.println("=== TOP 10 BY AVERAGE RATING ===");
        for (Movie movie : movies) {
            System.out.println(movie);
        }
        movies = moviesList.top10ByRatingCoef();
        System.out.println("=== TOP 10 BY RATING COEFFICIENT ===");
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }
}

// vashiot kod ovde

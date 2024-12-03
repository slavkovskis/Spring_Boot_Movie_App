package mk.ukim.finki.mk.lab.service;

import mk.ukim.finki.mk.lab.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> listAll();

    Optional<Movie> findById(Long id);

    List<Movie> filterMovies(String text, Double rating);

    void saveMovie(String title, String summary, Double rating, Long id, String productionName);

    void deleteMovie(Long id);
}

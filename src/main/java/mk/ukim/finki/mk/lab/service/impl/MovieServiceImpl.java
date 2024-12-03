package mk.ukim.finki.mk.lab.service.impl;

import mk.ukim.finki.mk.lab.model.Movie;
import mk.ukim.finki.mk.lab.model.Production;
import mk.ukim.finki.mk.lab.repository.jpa.MovieRepository;
import mk.ukim.finki.mk.lab.repository.jpa.ProductionRepository;
import mk.ukim.finki.mk.lab.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final ProductionRepository productionRepository;

    public MovieServiceImpl(MovieRepository movieRepository, ProductionRepository productionRepository) {
        this.movieRepository = movieRepository;
        this.productionRepository = productionRepository;
    }

    @Override
    public List<Movie> listAll() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }


    @Override
    public void saveMovie(String title, String summary, Double rating, Long id, String productionName) {
        Production production = productionRepository.findByName(productionName).orElseThrow(() -> new IllegalArgumentException("Production not found with name: " + id));
        Movie movie;
        if (id != null) {
            movie = movieRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Movie not found with ID: " + id));
            movie.setTitle(title);
            movie.setSummary(summary);
            movie.setRating(rating);
            movie.setProduction(production);
        } else {
            movie = new Movie(title, summary, rating, production);
        }
        movieRepository.save(movie);

    }

    @Override
    public void deleteMovie(Long id) {
        this.movieRepository.deleteById(id);
    }

    @Override
    public List<Movie> filterMovies(String title, Double rating) {
        if (title != null && rating != null) {
            return movieRepository.findMoviesByTitleIgnoreCaseContainingAndRatingGreaterThanEqual(title, rating);
        } else if (title == null && Double.isNaN(rating)) {
            return listAll();
        } else if (title != null) {
            return movieRepository.findMoviesByTitleIgnoreCaseContaining(title);
        } else {
            return movieRepository.findMoviesByRatingGreaterThanEqual(rating);
        }
    }
}

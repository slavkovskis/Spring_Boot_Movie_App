package mk.ukim.finki.mk.lab.repository.jpa;

import mk.ukim.finki.mk.lab.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findMoviesByTitleIgnoreCaseContainingAndRatingGreaterThanEqual(String title, Double rating);
    List<Movie> findMoviesByTitleIgnoreCaseContaining(String text);
    List<Movie> findMoviesByRatingGreaterThanEqual(Double rating);
}

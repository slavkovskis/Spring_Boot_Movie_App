package mk.ukim.finki.mk.lab.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import mk.ukim.finki.mk.lab.model.*;

import mk.ukim.finki.mk.lab.repository.jpa.MovieRepository;
import mk.ukim.finki.mk.lab.repository.jpa.ProductionRepository;
import mk.ukim.finki.mk.lab.repository.jpa.UserRepository;
import mk.ukim.finki.mk.lab.service.MovieService;
import mk.ukim.finki.mk.lab.service.ProductionService;
import mk.ukim.finki.mk.lab.service.UserService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DataHolder {
    private final MovieRepository movieRepository;
    private final ProductionRepository productionRepository;
    private final UserService userService;


    @PostConstruct
    public void init() {

        User admin = this.userService.create("admin", "admin", Role.ROLE_ADMIN);
        User guest = this.userService.create("user", "user", Role.ROLE_USER);


        if (productionRepository.findAll().isEmpty()) {
            productionRepository.save(new Production("Television Zdravkin", "Macedonia", "Veles"));
            productionRepository.save(new Production("Bollywood Pictures", "India", "Mumbai"));
            productionRepository.save(new Production("Universal Pictures", "USA", "New York"));
            productionRepository.save(new Production("Warner Bros.", "USA", "California"));
            productionRepository.save(new Production("Rockstar Games", "USA", "San Francisco"));
        }

        if (movieRepository.findAll().isEmpty()) {
            movieRepository.save(new Movie("Rane", "A bittersweet movie about the mafia life in Yugoslavia.", 9.0, productionRepository.findAll().get(0)));
            movieRepository.save(new Movie("The Sabarmati Report", "Based on the shocking train incident of 2002 in India.", 8.0, productionRepository.findAll().get(1)));
            movieRepository.save(new Movie("E.T. the Extra-Terrestrial", "Story about an alien landing on Earth, and it's friendship with a human.", 7.0, productionRepository.findAll().get(2)));
            movieRepository.save(new Movie("The Dark Knight", "The Dark Knight Batman, protects Gotham from Joker and other villains.", 3.0, productionRepository.findAll().get(3)));
            movieRepository.save(new Movie("Grand Theft Auto 5", "Movie version of the game GTA 5, follow Micheal, Franklin and Trevor and their illegal activities.", 5.6, productionRepository.findAll().get(4)));
        }
    }
}

package mk.ukim.finki.mk.lab.web.controller;

import mk.ukim.finki.mk.lab.model.Movie;
import mk.ukim.finki.mk.lab.model.Production;
import mk.ukim.finki.mk.lab.service.MovieService;
import mk.ukim.finki.mk.lab.service.ProductionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping()
public class MovieController {

    private final MovieService movieService;
    private final ProductionService productionService;

    public MovieController(MovieService movieService, ProductionService productionService) {
        this.movieService = movieService;
        this.productionService = productionService;
    }

    @GetMapping(value = {"/", "/movies"})
    public String getMoviesPage(@RequestParam(value = "movieFilter", required = false) String title ,
                                @RequestParam(value = "minRating", required = false) Double rating,
                                Model model) {
        List<Movie> movies;
        if (title == null && rating == null) {
            model.addAttribute("movies", movieService.listAll());
        } else {
            model.addAttribute("movies", movieService.filterMovies(title, rating));
        }

        return "listMovies";
    }

    @GetMapping("/movies/add")
    public String getAddMoviesPage(Model model) {
        model.addAttribute("productions", productionService.listAll());
        return "movieForm";
    }

    @GetMapping("/movies/edit/{id}")
    public String getEditMoviePage(@PathVariable Long id, Model model){
        Optional<Movie> movie = movieService.findById(id);
        model.addAttribute("movie", movie.get());
        model.addAttribute("productions", productionService.listAll());
        return "movieForm";
    }

    @PostMapping("/movies/save")
    public String saveMovie(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam("title") String title,
            @RequestParam("summary") String summary,
            @RequestParam("rating") double rating,
            @RequestParam("productionName") String productionName){

        movieService.saveMovie(title, summary, rating, id, productionName);

        return "redirect:/movies";
    }

    @PostMapping("/movies/delete/{id}")
    public String deleteMovie(@PathVariable Long id, Model model){
        movieService.deleteMovie(id);
        model.addAttribute("movies", movieService.listAll());
        return "redirect:/movies";
    }


}

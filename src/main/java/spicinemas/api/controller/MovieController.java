package spicinemas.api.controller;

import org.springframework.web.bind.annotation.RequestParam;
import spicinemas.api.db.MovieRepository;
import spicinemas.api.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import spicinemas.api.service.MovieService;
import spicinemas.api.type.MovieListingType;

import java.util.List;
import java.util.Map;

@RestController
public class MovieController {
    @Autowired
    MovieService movieService;


    @RequestMapping(value = "/movies",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Movie> getMovies(@RequestParam(required = false) Map<String, String> requestParams) {
        return movieService.getMovies(requestParams);
    }

}

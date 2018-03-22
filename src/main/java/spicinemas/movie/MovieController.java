package spicinemas.movie;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import spicinemas.location.LocationService;

import java.util.List;
import java.util.Map;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private LocationService locationService;

    @RequestMapping(value = "/movies",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Movie> getMovies(@RequestParam(required = false) Map<String, String> requestParams) {
        return movieService.getMovies(requestParams);
    }

    @GetMapping("/locations")
    public List<String> getLocations(){
        return locationService.getLocations();
    }
}

package spicinemas.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spicinemas.api.db.MovieRepository;
import spicinemas.api.model.Movie;
import spicinemas.api.type.MovieListingType;

import java.util.List;
import java.util.Map;

import static spicinemas.api.type.MovieListingType.NOW_SHOWING;

@Service
public class MovieService {
    public static final String MOVIE_TYPE = "movieType";
    private static final MovieListingType DEFAULT_LISTING_TYPE = NOW_SHOWING;

    @Autowired
    private MovieRepository movieRepository;


    public List<Movie> getMovies(Map<String, String> requestFilter) {
        MovieListingType listingType = getListingType(requestFilter);
        return movieRepository.getMovies(listingType);
    }

    private MovieListingType getListingType(Map<String, String> requestFilter) {
        if(null == requestFilter)
            return DEFAULT_LISTING_TYPE;

        String requestListingType = requestFilter.getOrDefault(MOVIE_TYPE, "");
        MovieListingType movieListingType;
        try{
            movieListingType = MovieListingType.valueOf(requestListingType.toUpperCase());
        }catch (IllegalArgumentException iae){
            movieListingType = DEFAULT_LISTING_TYPE;
        }
        return movieListingType;
    }
}

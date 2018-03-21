package spicinemas.api.db;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import spicinemas.api.model.Movie;
import spicinemas.api.service.MovieService;
import spicinemas.api.type.MovieListingType;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static spicinemas.api.service.MovieService.MOVIE_TYPE;
import static spicinemas.api.type.MovieListingType.COMING_SOON;
import static spicinemas.api.type.MovieListingType.NOW_SHOWING;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService = new MovieService();

    private Map<String, String> requestFilter;
    private List<Movie> movieListReponse;

    @Before
    public void init(){
        requestFilter = new HashMap<>();
        movieListReponse = new ArrayList<>();
        movieListReponse.add(new Movie("Rambo","RDX", NOW_SHOWING));
        movieListReponse.add(new Movie("Banjo","RDX 3D", NOW_SHOWING));
        movieListReponse.add(new Movie("Sucide Squad","Dolby Atmos, RDX 3D, SUB", COMING_SOON));
    }

    @Test
    public void shouldReturnAllNowShowingMoviesForDefault(){
        when(movieRepository.getMoviesByFilters(NOW_SHOWING, new ArrayList<>()))
                .thenReturn(movieListReponse);
        List<Movie> movies = movieService.getMovies(requestFilter);
        assertEquals(3, movies.size());
    }

    @Test
    public void shouldReturnOnlyComingSoonMoviesWhenMovieTypeIsComingSoon(){
        when(movieRepository.getMoviesByFilters(COMING_SOON, new ArrayList<>()))
                .thenReturn(movieListReponse);
        requestFilter.put(MOVIE_TYPE, "coming_soon");
        List<Movie> movies = movieService.getMovies(requestFilter);
        assertEquals(3, movies.size());
    }

    @Test
    public void shouldReturnNowShowingMoviesWhenNoFilterPassed(){
        when(movieRepository.getMoviesByFilters(NOW_SHOWING, new ArrayList<>()))
                .thenReturn(movieListReponse);
        List<Movie> movies = movieService.getMovies(new HashMap<>());
        assertEquals(3, movies.size());
    }

    @Test
    public void shouldReturnNowShowingMoviesWhenFilterIsNull(){
        when(movieRepository.getMoviesByFilters(NOW_SHOWING, new ArrayList<>()))
                .thenReturn(movieListReponse);
        List<Movie> movies = movieService.getMovies(null);
        assertEquals(3, movies.size());
    }
}

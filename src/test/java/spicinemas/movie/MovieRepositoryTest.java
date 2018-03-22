package spicinemas.movie;

import org.jooq.DSLContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import spicinemas.SpiCinemasApplication;
import spicinemas.movie.Movie;
import spicinemas.movie.MovieListingType;
import spicinemas.movie.MovieRepository;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static spicinemas.movie.MovieListingType.COMING_SOON;
import static spicinemas.movie.MovieListingType.NOW_SHOWING;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpiCinemasApplication.class)
@ActiveProfiles("test")
public class MovieRepositoryTest {
    @Autowired
    private MovieRepository movieRepo;
    @Autowired
    DSLContext dslContext;

    @Test
    public void shouldReturnComingSoonHindiMovies(){
        assertTrue(movieRepo.getMoviesByFilters(COMING_SOON, Arrays.asList("Hindi")).size() >= 1);
    }

    @Test
    public void shouldReturnNowShowingEnglishMovies(){
        assertTrue(movieRepo.getMoviesByFilters(NOW_SHOWING, Arrays.asList("English")).size() >= 1);
    }

    @Test
    public void shouldNotReturnMoviesIfUnknownLanguageIsSpecified(){
        assertTrue(movieRepo.getMoviesByFilters(NOW_SHOWING, Arrays.asList("Unknown")).size() == 0);
    }

    @Test
    public void shouldReturnNowShowingMoviesIfNoLanguageFilterIsSpecified(){
        List<Movie> movieList = movieRepo.getMoviesByFilters(NOW_SHOWING, Arrays.asList());
        assertTrue(movieList.size() >= 1);
        assertTrue(movieList.stream().allMatch(movie -> movie.getListingType().equals(MovieListingType.NOW_SHOWING)));
    }

    @Test
    public void shouldReturnComingSoonHindiAndEnglishMovies(){
        List<Movie> movieList = movieRepo.getMoviesByFilters(COMING_SOON, Arrays.asList("Hindi", "English"));
        assertTrue(movieList.size() >= 1);

        assertTrue(movieList.stream().anyMatch(movie -> movie.getName().equals("Sultan")));
        assertTrue(movieList.stream().anyMatch(movie -> movie.getName().equals("Suicide Squad")));
    }

}
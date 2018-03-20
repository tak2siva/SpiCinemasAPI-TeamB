package spicinemas.api.db;

import org.jooq.DSLContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import spicinemas.SpiCinemasApplication;
import spicinemas.api.model.Movie;
import spicinemas.api.type.MovieListingType;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static spicinemas.api.type.MovieListingType.COMING_SOON;
import static spicinemas.api.type.MovieListingType.NOW_SHOWING;
import static spicinemas.db.gen.tables.Movie.MOVIE;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpiCinemasApplication.class)
@ActiveProfiles("test")
public class MovieRepositoryTest {
    @Autowired
    private MovieRepository movieRepo;
    @Autowired
    DSLContext dslContext;

    @Before
    public void init() {
    }

    @After
    public void tearDown() {
    }


    @Test
    public void shouldReturnNowShowingMovies(){
        assertTrue(movieRepo.getMovies(NOW_SHOWING).size() > 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldReturnNowShowingMoviesByDefault(){
        movieRepo.getMovies(null);
    }

    @Test
    public void shouldReturnComingSoonMovies(){
        assertTrue(movieRepo.getMovies(COMING_SOON).size() > 1);
    }
}
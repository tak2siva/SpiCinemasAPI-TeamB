package spicinemas.api.db;

import org.jooq.SelectJoinStep;
import org.jooq.SelectOnConditionStep;
import org.springframework.util.Assert;
import spicinemas.api.model.Movie;
import spicinemas.api.type.MovieListingType;
import spicinemas.db.gen.tables.records.MovieRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static spicinemas.db.gen.tables.Language.LANGUAGE;
import static spicinemas.db.gen.tables.Movie.MOVIE;
import static spicinemas.db.gen.tables.MovieLangMapping.MOVIE_LANG_MAPPING;

@Repository
@Transactional
public class MovieRepository {
    @Autowired
    private DSLContext dsl;


    public List<Movie> getMoviesByFilters(MovieListingType listingType, List<String> languages){
        List<Integer> languageIds = dsl.select().from(LANGUAGE).where(LANGUAGE.NAME.in(languages)).fetch(LANGUAGE.ID);

        List<Integer> movieIds;
        SelectJoinStep step = dsl.select(MOVIE.ID).from(MOVIE);
        if (!languages.isEmpty()) {
            SelectOnConditionStep cond = step.join(MOVIE_LANG_MAPPING)
                    .on(MOVIE.ID.eq(MOVIE_LANG_MAPPING.MOVIE_ID)
                            .and(MOVIE_LANG_MAPPING.LANG_ID.in(languageIds))
                    );
            movieIds = cond.where(MOVIE.LISTING_TYPE.eq(listingType.toString())).fetch(MOVIE.ID);
        } else {
            movieIds = step.where(MOVIE.LISTING_TYPE.eq(listingType.toString())).fetch(MOVIE.ID);
        }

        List<Movie> movies = dsl.selectFrom(MOVIE)
                .where(MOVIE.ID.in(movieIds))
                .fetchMap(MOVIE.ID)
                .values()
                .stream().map(Movie::new).collect(toList());

        return movies;
    }
}

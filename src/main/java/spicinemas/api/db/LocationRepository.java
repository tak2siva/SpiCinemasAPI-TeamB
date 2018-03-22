package spicinemas.api.db;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static spicinemas.db.gen.tables.Location.LOCATION;

@Repository
@Transactional
public class LocationRepository {
    @Autowired
    private DSLContext dsl;

    public List<String> getLocations() {
        return dsl.selectFrom(LOCATION).fetch(LOCATION.NAME);
    }
}

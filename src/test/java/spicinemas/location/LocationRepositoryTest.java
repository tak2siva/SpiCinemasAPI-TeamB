package spicinemas.location;

import org.jooq.DSLContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import spicinemas.SpiCinemasApplication;
import spicinemas.location.LocationRepository;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpiCinemasApplication.class)
@ActiveProfiles("test")
public class LocationRepositoryTest {

    @Autowired
    private LocationRepository locationRepo;

    @Autowired
    DSLContext dslContext;

    @Test
    public void shouldReturnLocations(){
        assertTrue(locationRepo.getLocations().size() >= 1);
    }

}

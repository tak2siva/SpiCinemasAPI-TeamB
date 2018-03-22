package spicinemas.location;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import spicinemas.location.LocationService;
import spicinemas.location.LocationRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LocationServiceTest {

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private LocationService locationService = new LocationService();
    private List<String> locations;

    @Before
    public void init() {
        locations = new ArrayList<>();
        locations.add("Chennai");
        locations.add("Trechy");
        locations.add("Madurai");
    }

    @Test
    public void shouldReturnAllNowShowingMoviesForDefault() {
        when(locationRepository.getLocations())
                .thenReturn(locations);
        List<String> locations = locationService.getLocations();
        assertEquals(3, locations.size());
    }

}
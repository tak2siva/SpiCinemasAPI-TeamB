package spicinemas.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spicinemas.location.LocationRepository;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public List<String> getLocations() {
        return locationRepository.getLocations();
    }
}

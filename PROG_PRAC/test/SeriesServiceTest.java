

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import prog_prac.SeriesService;
import prog_prac.SeriesModel;
import java.util.ArrayList;

public class SeriesServiceTest {

    private SeriesService service;

    @Before
    public void setUp() {
        service = new SeriesService(new ArrayList<>());
        service.addSeries("101", "Extreme Sports", 12, "10");
        service.addSeries("102", "Bargain Hunters", 10, "10");
    }

    @Test
    public void TestSearchSeries() {                                      //coding with john,2022
        SeriesModel s = service.findById("101");
        assertNotNull(s);
        assertEquals("101", s.getSeriesID());
        assertEquals("Extreme Sports", s.getSeriesName());
        assertEquals("12", s.getSeriesAge());
        assertEquals("10", s.getSeriesNumberOfEpisodes());
    }

    @Test
    public void TestSearchSeries_SeriesNotFound() {                        //coding with john,2022
        assertNull(service.findById("999"));
    }

    @Test
    public void TestUpdateSeries() {                                       //coding with john,2022
        assertTrue(service.updateSeries("101", "Extreme Sports 2025", 10, "12"));
        SeriesModel s = service.findById("101");
        assertEquals("Extreme Sports 2025", s.getSeriesName());
        assertEquals("10", s.getSeriesAge());
        assertEquals("12", s.getSeriesNumberOfEpisodes());
    }

    @Test
    public void TestDeleteSeries() {                                       //coding with john,2022
        assertTrue(service.deleteSeries("102"));
        assertNull(service.findById("102"));
    }

    @Test
    public void TestDeleteSeries_SeriesNotFound() {                        //coding with john,2022
        assertFalse(service.deleteSeries("999"));
    }

    @Test
    public void TestSeriesAgeRestriction_AgeValid() {                      //coding with john,2022
        assertTrue(service.isValidAge(2));
        assertTrue(service.isValidAge(10));
        assertTrue(service.isValidAge(18));
    }

    @Test
    public void TestSeriesAgeRestriction_SeriesAgeInValid() {              //coding with john,2022
        assertFalse(service.isValidAge(1));
        assertFalse(service.isValidAge(19));
        assertFalse(service.isValidAge(-5));
    }
}

package prog_prac;

import java.util.List;

/**
 *
 * @author heath
 */
public class SeriesService {

    private final List<SeriesModel> data;

    public SeriesService(List<SeriesModel> backing) {
        this.data = backing;
    }

    public boolean isValidAge(int age) {
        return age >= 2 && age <= 18;
    }

    public SeriesModel addSeries(String id, String name, int age, String episodes) {
        if (!isValidAge(age)) {
            throw new IllegalArgumentException("age must be 2–18");
        }
        SeriesModel s = new SeriesModel(id, name, String.valueOf(age), episodes);
        data.add(s);
        return s;
    }

    public SeriesModel findById(String id) {
        for (SeriesModel s : data) {
            if (s.getSeriesID().equalsIgnoreCase(id)) {
                return s;
            }
        }
        return null;
    }

    public boolean updateSeries(String id, String newName, int newAge, String newEpisodes) {
        SeriesModel s = findById(id);
        if (s == null) {
            return false;
        }
        if (!isValidAge(newAge)) {
            throw new IllegalArgumentException("age must be 2–18");
        }
        s.setSeriesName(newName);
        s.setSeriesAge(String.valueOf(newAge));
        s.setSeriesNumberOfEpisodes(newEpisodes);
        return true;
    }

    public boolean deleteSeries(String id) {
        SeriesModel s = findById(id);
        return (s != null) && data.remove(s);
    }
}

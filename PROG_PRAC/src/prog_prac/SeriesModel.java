package prog_prac;

public class SeriesModel {                                          //farrell,2023

    private String seriesID;
    private String seriesName;
    private String seriesAge;
    private String seriesNumberOfEpisodes;

    public SeriesModel(String id, String name, String age, String episodes) {       //farrell,2023
        this.seriesID = id;
        this.seriesName = name;
        this.seriesAge = age;
        this.seriesNumberOfEpisodes = episodes;
    }

    public String getSeriesID() {
        return seriesID;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public String getSeriesAge() {
        return seriesAge;
    }

    public String getSeriesNumberOfEpisodes() {
        return seriesNumberOfEpisodes;
    }

    public void setSeriesName(String v) {
        seriesName = v;
    }

    public void setSeriesAge(String v) {
        seriesAge = v;
    }

    public void setSeriesNumberOfEpisodes(String v) {
        seriesNumberOfEpisodes = v;
    }

    @Override
    public String toString() {                                        //farrell,2023
        return "[" + seriesID + "] " + seriesName + " | Age " + seriesAge + " | Episodes " + seriesNumberOfEpisodes;
    }
}

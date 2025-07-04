package pt.hugo.carmedia.models.data;

import pt.hugo.carmedia.models.data.radio.Station;
import pt.hugo.carmedia.models.data.radio.StationLibrary;

public class InternetRadio {
    StationLibrary stationLibrary;
    private int stationNumber = 0;
    public InternetRadio(){
        this.stationLibrary = new StationLibrary();
        stationLibrary.loadStationList();
    }

    public String getStationFromLibrary(){
        return stationLibrary.getStations().get(this.stationNumber).getUrl();
    }

    public Station getStationData(){
        if (stationLibrary.getStations().get(this.stationNumber) != null)
            return stationLibrary.getStations().get(this.stationNumber);
        return null;
    }
    private int getStationListSize(){
        return stationLibrary.getStations().size();
    }
    public void nextStation(){
        if (this.stationNumber >= getStationListSize() - 1){
            this.stationNumber = 0;
            return;
        }
        this.stationNumber++;
    }

    public void previousStation(){
        if (this.stationNumber <= 0){
            this.stationNumber = getStationListSize() - 1;
            return;
        }
        this.stationNumber--;
    }
}

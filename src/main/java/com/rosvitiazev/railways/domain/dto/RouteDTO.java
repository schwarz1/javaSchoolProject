package com.rosvitiazev.railways.domain.dto;

public class RouteDTO {
    private String trainNumber;
    private String stationIdDeparture;
    private String stationIdDestination;

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getStationIdDeparture() {
        return stationIdDeparture;
    }

    public void setStationIdDeparture(String stationIdDeparture) {
        this.stationIdDeparture = stationIdDeparture;
    }

    public String getStationIdDestination() {
        return stationIdDestination;
    }

    public void setStationIdDestination(String stationIdDestination) {
        this.stationIdDestination = stationIdDestination;
    }
}

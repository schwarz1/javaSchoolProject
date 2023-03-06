package com.rosvitiazev.railways.domain.DTO;

public class OrderDTO {

    private String FirstName;
    private String LastName;
    private String BirthDate;
    private String TrainNumber;
    private String DepartureDate;

    public OrderDTO(String firstName, String lastName, String birthDate, String trainNumber, String departureDate) {
        FirstName = firstName;
        LastName = lastName;
        BirthDate = birthDate;
        TrainNumber = trainNumber;
        DepartureDate = departureDate;
    }
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(String birthDate) {
        BirthDate = birthDate;
    }

    public String getTrainNumber() {
        return TrainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        TrainNumber = trainNumber;
    }

    public String getDepartureDate() {
        return DepartureDate;
    }

    public void setDepartureDate(String departureDate) {
        DepartureDate = departureDate;
    }

}

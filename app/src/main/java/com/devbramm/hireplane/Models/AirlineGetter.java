package com.devbramm.hireplane.Models;

public class AirlineGetter {
    private String airline_name;
    private String airline_logo;
    private String airline_code;

    public AirlineGetter() {
    }

    public AirlineGetter(String airline_name, String airline_logo, String airline_code) {
        this.airline_name = airline_name;
        this.airline_logo = airline_logo;
        this.airline_code = airline_code;
    }

    public String getAirline_name() {
        return airline_name;
    }

    public void setAirline_name(String airline_name) {
        this.airline_name = airline_name;
    }

    public String getAirline_logo() {
        return airline_logo;
    }

    public void setAirline_logo(String airline_logo) {
        this.airline_logo = airline_logo;
    }

    public String getAirline_code() {
        return airline_code;
    }

    public void setAirline_code(String airline_code) {
        this.airline_code = airline_code;
    }
}

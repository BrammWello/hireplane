package com.devbramm.hireplane.Models;

public class AirportsGetter {
    private String city;
    private String country;
    private String elevation;
    private String iata;
    private String icao;
    private String lat;
    private String lon;
    private String name;
    private String state;
    private String tz;

    public AirportsGetter() {
    }

    public AirportsGetter(String city, String country, String elevation, String iata, String icao, String lat, String lon, String name, String state, String tz) {
        this.city = city;
        this.country = country;
        this.elevation = elevation;
        this.iata = iata;
        this.icao = icao;
        this.lat = lat;
        this.lon = lon;
        this.name = name;
        this.state = state;
        this.tz = tz;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getElevation() {
        return elevation;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public String getIcao() {
        return icao;
    }

    public void setIcao(String icao) {
        this.icao = icao;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTz() {
        return tz;
    }

    public void setTz(String tz) {
        this.tz = tz;
    }
}

package com.bridgelab;

import com.opencsv.bean.CsvBindByName;

public class CSVStatesCensus extends CSVStatesCode {
    @CsvBindByName(column = "State", required = true)
    public String state;

    @CsvBindByName(column = "Population", required = true)
    public String population;

    @CsvBindByName(column = "AreaInSqKm", required = true)
    public String areaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm", required = true)
    public String densityPerSqKm;

    @Override
    public String toString() {
        return "com.bridgelabz.censusanalyserproject.IndiaCensusCSV {" +
                "State='" + state + '\'' +
                " ,Population='" + population + '\'' +
                " ,AreaInSqKm='" + areaInSqKm + '\'' +
                " ,DensityPerSqKm='" + densityPerSqKm + '\'' +
                '}';
    }

    //GETTER AND SETTER
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getAreaInSqKm() {
        return areaInSqKm;
    }

    public void setAreaInSqKm(String areaInSqKm) {
        this.areaInSqKm = areaInSqKm;
    }

    public String getDensityPerSqKm() {
        return densityPerSqKm;
    }

    public void setDensityPerSqKm(String densityPerSqKm) {
        this.densityPerSqKm = densityPerSqKm;
    }
}


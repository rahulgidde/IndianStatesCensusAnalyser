package com.bridgelab.sensusanalyser.dto;

import com.opencsv.bean.CsvBindByName;

public class USCensusCSV {

    @CsvBindByName(column = "State Id", required = true)
    private String stateId;

    @CsvBindByName(column = "State", required = true)
    private String state;

    @CsvBindByName(column = "Population", required = true)
    private int population;

    @CsvBindByName(column = "Total area", required = true)
    private double totalArea;

    @CsvBindByName(column = "Population Density", required = true)
    private double populationDensity;

    @CsvBindByName(column = "Population Density", required = true)
    private double densityPerSqKm;

    public String getStateId() {
        return stateId;
    }

    public String getState() {
        return state;
    }

    public int getPopulation() {
        return population;
    }

    public double getTotalArea() {
        return totalArea;
    }

    public double getDensityPerSqKm() {
        return densityPerSqKm;
    }
}
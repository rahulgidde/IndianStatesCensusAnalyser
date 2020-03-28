package com.bridgelab.sensusanalyser.dao;

import com.bridgelab.sensusanalyser.dto.CSVStatesCensus;
import com.bridgelab.sensusanalyser.dto.CSVStatesCode;

public class CensusDAO {
    public String state;
    public int population;
    public int area;
    public int density;
    public String stateCode;

    public CensusDAO(CSVStatesCensus csvStateCensus) {
        this.state = csvStateCensus.getState();
        this.population = csvStateCensus.getPopulation();
        this.area = csvStateCensus.getAreaInSqKm();
        this.density = csvStateCensus.getDensityPerSqKm();
    }

    public CensusDAO(CSVStatesCode csvStatesCode) {
        this.stateCode = csvStatesCode.getStateCode();
    }
}
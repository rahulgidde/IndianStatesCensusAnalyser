package com.bridgelab.sensusanalyser.dao;

import com.bridgelab.sensusanalyser.dto.CSVStatesCensus;
import com.bridgelab.sensusanalyser.dto.CSVStatesCode;
import com.bridgelab.sensusanalyser.dto.USCensusCSV;

public class CensusDAO {
    public String state;
    public int population;
    public int area;
    public int density;
    public String stateCode;
    public double areaInSqKm;
    public double densityPerSqKm;

    public CensusDAO(CSVStatesCensus csvStateCensus) {
        this.state = csvStateCensus.getState();
        this.population = csvStateCensus.getPopulation();
        this.area = csvStateCensus.getAreaInSqKm();
        this.density = csvStateCensus.getDensityPerSqKm();
    }

    public CensusDAO(CSVStatesCode csvStatesCode) {
        this.stateCode = csvStatesCode.getStateCode();
    }

    public CensusDAO(USCensusCSV usCensusCSV) {
        this.state = usCensusCSV.getState();
        this.areaInSqKm = usCensusCSV.getTotalArea();
        this.densityPerSqKm = usCensusCSV.getDensityPerSqKm();
        this.population = usCensusCSV.getPopulation();
    }
}
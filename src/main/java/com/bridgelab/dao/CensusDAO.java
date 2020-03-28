package com.bridgelab.dao;

import com.bridgelab.censusanalyser.CSVStatesCensus;

public class CensusDAO {
    public String state;
    public int  population;
    public int area;
    public int density;

    public CensusDAO(CSVStatesCensus csvStateCensus) {
        this.state = csvStateCensus.getState();
        this.population = csvStateCensus.getPopulation();
        this.area = csvStateCensus.getAreaInSqKm();
        this.density = csvStateCensus.getDensityPerSqKm();
    }
}
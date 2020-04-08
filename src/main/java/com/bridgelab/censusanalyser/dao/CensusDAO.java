package com.bridgelab.censusanalyser.dao;

import com.bridgelab.censusanalyser.dto.CSVStatesCensus;
import com.bridgelab.censusanalyser.dto.CSVStatesCode;
import com.bridgelab.censusanalyser.dto.USCensusCSV;
import com.bridgelab.censusanalyser.service.CensusAnalyser;

import java.util.Comparator;

public class CensusDAO {
    public String state;
    public int population;
    public String stateCode;
    public double areaInSqKm;
    public double densityPerSqKm;

    public CensusDAO(CSVStatesCensus csvStateCensus) {
        this.state = csvStateCensus.getState();
        this.population = csvStateCensus.getPopulation();
        this.areaInSqKm = csvStateCensus.getAreaInSqKm();
        this.densityPerSqKm = csvStateCensus.getDensityPerSqKm();
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

    public Object getCensusDTO(CensusAnalyser.Country country) {
        if (country.equals(CensusAnalyser.Country.INDIA))
            return new CSVStatesCensus(state, population, areaInSqKm, densityPerSqKm);
        return new USCensusCSV(stateCode, state, population, areaInSqKm, population);
    }

    public static Comparator<CensusDAO> getComparator(CensusAnalyser.SortingMode mode) {
        if (mode.equals(CensusAnalyser.SortingMode.STATE))
            return Comparator.comparing(census -> census.state);
        if (mode.equals(CensusAnalyser.SortingMode.POPULATION))
            return Comparator.comparing(census -> census.population);
        if (mode.equals(CensusAnalyser.SortingMode.DENSITY))
            return Comparator.comparing(census -> census.densityPerSqKm);
        if (mode.equals(CensusAnalyser.SortingMode.AREA))
            return Comparator.comparing(census -> census.areaInSqKm);
        return null;
    }
}
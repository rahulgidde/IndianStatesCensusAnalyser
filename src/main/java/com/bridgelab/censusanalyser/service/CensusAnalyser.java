package com.bridgelab.censusanalyser.service;

import com.bridgelab.censusanalyser.adapter.CensusAdapter;
import com.bridgelab.censusanalyser.adapter.CensusAdapterFactory;
import com.bridgelab.censusanalyser.dao.CensusDAO;
import com.bridgelab.censusanalyser.exception.StateAnalyzerException;
import com.google.gson.Gson;
import org.apache.commons.collections.map.HashedMap;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class CensusAnalyser {

    Map<String, CensusDAO> censusMap = null;
    Country country;

    //ENUM FOR COUNTRY
    public enum Country {
        INDIA, US;
    }

    //ENUM FOR Sorting MODE
    public enum SortingMode {
        STATE, POPULATION, DENSITY, AREA;
    }

    //DEFAULT CONSTRUCTOR
    public CensusAnalyser() {
        this.censusMap = new HashedMap();
    }

    //PARAMETRIZED CONSTRUCTOR
    public CensusAnalyser(Country country) {
        this.country = country;
    }

    //GENERIC METHOD FOR LOADING FILE DATA
    public int loadCensusData(String... csvFilePath) throws StateAnalyzerException {
        CensusAdapter censusLoader = CensusAdapterFactory.getCensusData(country);
        censusMap = censusLoader.loadCensusData(csvFilePath);
        return censusMap.size();
    }

    //METHOD TO GET FILE EXTENSION
    public static String getFileExtension(File file) {
        String fileExtension = "";
        // GET FILE NAME FIRST
        String fileName = file.getName();
        fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
        return fileExtension;
    }

    //METHOD TO SORT STATE CENSUS DATA
    public String getSortedCensusData(SortingMode mode) throws StateAnalyzerException {
        if (censusMap == null || censusMap.size() == 0)
            throw new StateAnalyzerException(StateAnalyzerException.ExceptionType.NO_SUCH_CENSUS_DATA, "Data not found");
        ArrayList censusList = censusMap.values().stream()
                .sorted(CensusDAO.getComparator(mode))
                .map(censusDAO -> censusDAO.getCensusDTO(country))
                .collect(Collectors.toCollection(ArrayList::new));
        return new Gson().toJson(censusList);
    }
}

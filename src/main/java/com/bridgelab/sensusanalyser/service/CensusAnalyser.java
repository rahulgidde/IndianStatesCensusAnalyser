package com.bridgelab.sensusanalyser.service;

import com.bridgelab.sensusanalyser.adapter.CensusAdapter;
import com.bridgelab.sensusanalyser.adapter.CensusAdapterFactory;
import com.bridgelab.sensusanalyser.dao.CensusDAO;
import com.bridgelab.sensusanalyser.exception.StateAnalyzerException;
import com.google.gson.Gson;
import org.apache.commons.collections.map.HashedMap;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class CensusAnalyser {

    Map<String, CensusDAO> censusMap = new HashMap<>();

    public enum COUNTRY {
        INDIA, US;
    }

    //DEFAULT CONSTRUCTOR
    public CensusAnalyser() {
        this.censusMap = new HashedMap();
    }

    //GENERIC METHOD LOADING EVERY FILE DATA
    public int loadCensusData(COUNTRY country, String... csvFilePath) throws StateAnalyzerException {
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

    //METHOD FOR STATE CENSUS COMPARATOR
    public String getSortedCensusData() throws StateAnalyzerException {
        if (censusMap == null || censusMap.size() == 0)
            throw new StateAnalyzerException(StateAnalyzerException.ExceptionType.NO_SUCH_CENSUS_DATA, "Data not found");
        Comparator<CensusDAO> comparator = Comparator.comparing(stateCensus -> stateCensus.state);
        List<CensusDAO> censusList = censusMap.values().stream().collect(Collectors.toList());
        this.sort(comparator, censusList);
        String toJson = new Gson().toJson(censusList);
        return toJson;
    }

    //METHOD TO SORT STATE CENSUS DATA BY POPULATION
    public String getPopulationWiseSortedCensusData() throws StateAnalyzerException {
        if (censusMap == null || censusMap.size() == 0)
            throw new StateAnalyzerException(StateAnalyzerException.ExceptionType.NO_SUCH_CENSUS_DATA, "Data not found");
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.population);
        List<CensusDAO> censusList = censusMap.values().stream().collect(Collectors.toList());
        this.sort(censusComparator, censusList);
        Collections.reverse(censusList);
        String toJson = new Gson().toJson(censusList);
        return toJson;
    }

    //METHOD TO SORT STATE CENSUS DATA BY POPULATION
    public String getPopulationDensityWiseSortedCensusData() throws StateAnalyzerException {
        if (censusMap == null || censusMap.size() == 0)
            throw new StateAnalyzerException(StateAnalyzerException.ExceptionType.NO_SUCH_CENSUS_DATA, "Data not found");
        Comparator<CensusDAO> censusComparator = Comparator.comparing(stateCensus -> stateCensus.density);
        List<CensusDAO> censusList = censusMap.values().stream().collect(Collectors.toList());
        this.sort(censusComparator, censusList);
        Collections.reverse(censusList);
        String toJson = new Gson().toJson(censusList);
        return toJson;
    }

    //METHOD TO SORT STATE CENSUS DATA BY AREA WISE
    public String getAreaWiseSortedCensusData() throws StateAnalyzerException {
        if (censusMap == null || censusMap.size() == 0)
            throw new StateAnalyzerException(StateAnalyzerException.ExceptionType.NO_SUCH_CENSUS_DATA, "Data not found");
        Comparator<CensusDAO> censusComparator = Comparator.comparing(stateCensus -> stateCensus.area);
        List<CensusDAO> censusList = censusMap.values().stream().collect(Collectors.toList());
        this.sort(censusComparator, censusList);
        Collections.reverse(censusList);
        String toJson = new Gson().toJson(censusList);
        return toJson;
    }

    //METHOD TO SORT STATE CENSUS DATA BY POPULATION DENSITY
    private <T> void sort(Comparator<T> csvComparator, List<T> csvList) {
        for (int index1 = 0; index1 < csvList.size() - 1; index1++) {
            for (int index2 = 0; index2 < csvList.size() - index1 - 1; index2++) {
                T census1 = csvList.get(index2);
                T census2 = csvList.get(index2 + 1);
                if (csvComparator.compare(census1, census2) > 0) {
                    csvList.set(index2, census2);
                    csvList.set(index2 + 1, census1);
                }
            }
        }
    }
}

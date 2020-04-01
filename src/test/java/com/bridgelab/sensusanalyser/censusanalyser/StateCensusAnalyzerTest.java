package com.bridgelab.sensusanalyser.censusanalyser;

import com.bridgelab.sensusanalyser.dao.CensusDAO;
import com.bridgelab.sensusanalyser.exception.StateAnalyzerException;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import com.bridgelab.sensusanalyser.service.CensusAnalyser;

public class StateCensusAnalyzerTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/StateCensusData.csv";
    private static final String WRONG_INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/StateCensus.csv";
    private static final String WRONG_INDIA_CENSUS_CSV_FILE_EXTENSION = "./src/test/resources/StateCensus.pdf";
    private static final String WRONG_INDIA_CENSUS_CSV_FILE_CONTENT = "./src/test/resources/WrongStateCensusData.csv";
    private static final String INDIA_CODE_CSV_FILE_PATH = "./src/test/resources/StateCode.csv";
    private static final String WRONG_INDIA_CODE_CSV_FILE_PATH = "./src/test/resources/State.csv";
    private static final String WRONG_INDIA_CODE_CSV_FILE_PATH_EXTENSION = "./src/test/resources/StateCode.pdf";
    private static final String WRONG_INDIA_CODE_CSV_FILE_CONTENT = "./src/test/resources/WrongStateCode.csv";
    private static final String US_CENSUS_CSV_FILE_PATH = "./src/test/resources/USCensusData.csv";
    int result;

    @Test
    public void givenStatesCensusCSVFile_WhenNumberOfRecordMatches_ThenReturnNumberOfRecords() throws StateAnalyzerException {
        CensusAnalyser censusAnalyzer = new CensusAnalyser(CensusAnalyser.Country.INDIA);
        result = censusAnalyzer.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        Assert.assertEquals(29, result);
    }

    @Test
    public void givenStatesCensusCSVFile_WhenImproper_ThenReturnFileNotFoundException() {
        try {
            CensusAnalyser censusAnalyzer = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            result = censusAnalyzer.loadCensusData(WRONG_INDIA_CENSUS_CSV_FILE_PATH);
        } catch (StateAnalyzerException e) {
            Assert.assertEquals(StateAnalyzerException.ExceptionType.FILE_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenStatesCensusCSVFile_WhenImproper_ThenReturnNoSuchTypeException() {
        try {
            CensusAnalyser censusAnalyzer = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            result = censusAnalyzer.loadCensusData(WRONG_INDIA_CENSUS_CSV_FILE_EXTENSION);
        } catch (StateAnalyzerException e) {
            Assert.assertEquals(StateAnalyzerException.ExceptionType.NO_SUCH_TYPE, e.type);
        }
    }

    @Test
    public void givenWrongStatesCensusCSVFile_WhenIncorrectDelimiter_ThenReturnWrongDelimiterException() {
        try {
            CensusAnalyser censusAnalyzer = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            result = censusAnalyzer.loadCensusData(WRONG_INDIA_CENSUS_CSV_FILE_CONTENT);
        } catch (StateAnalyzerException e) {
            Assert.assertEquals(StateAnalyzerException.ExceptionType.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenWrongStatesCensusCSVFile_WhenIncorrectHeader_ThenReturnWrongHeaderException() {
        try {
            CensusAnalyser censusAnalyzer = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            result = censusAnalyzer.loadCensusData(WRONG_INDIA_CENSUS_CSV_FILE_CONTENT);
        } catch (StateAnalyzerException e) {
            Assert.assertEquals(StateAnalyzerException.ExceptionType.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenStatesCodeCSVFile_WhenNumberOfRecordsMatches_ThenReturnNumberOfRecords() throws StateAnalyzerException {
        CensusAnalyser censusAnalyzer = new CensusAnalyser(CensusAnalyser.Country.INDIA);
        result = censusAnalyzer.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_CODE_CSV_FILE_PATH);
        Assert.assertEquals(29, result);
    }

    @Test
    public void givenStatesCodeCSVFile_WhenImproper_ThenReturnFileNotFoundException() {
        try {
            CensusAnalyser censusAnalyzer = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            result = censusAnalyzer.loadCensusData(WRONG_INDIA_CODE_CSV_FILE_PATH);
        } catch (StateAnalyzerException e) {
            Assert.assertEquals(StateAnalyzerException.ExceptionType.FILE_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenStatesCodeCSVFile_WhenImproper_ThenReturnNoSuchTypeException() {
        try {
            CensusAnalyser censusAnalyzer = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            result = censusAnalyzer.loadCensusData(WRONG_INDIA_CODE_CSV_FILE_PATH_EXTENSION);
        } catch (StateAnalyzerException e) {
            Assert.assertEquals(StateAnalyzerException.ExceptionType.NO_SUCH_TYPE, e.type);
        }
    }

    @Test
    public void givenWrongStatesCodeCSVFile_WhenIncorrectDelimiter_ThenReturnWrongDelimiterException() {
        try {
            CensusAnalyser censusAnalyzer = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            result = censusAnalyzer.loadCensusData(WRONG_INDIA_CODE_CSV_FILE_CONTENT);
        } catch (StateAnalyzerException e) {
            Assert.assertEquals(StateAnalyzerException.ExceptionType.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenWrongStatesCodeCSVFile_WhenIncorrectHeader_ThenReturnWrongHeaderException() {
        try {
            CensusAnalyser censusAnalyzer = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            result = censusAnalyzer.loadCensusData(WRONG_INDIA_CODE_CSV_FILE_CONTENT);
        } catch (StateAnalyzerException e) {
            Assert.assertEquals(StateAnalyzerException.ExceptionType.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenStateCensusCsvFile_WhenSortedOnState_ThenReturnSortedFirstResult() {
        try {
            CensusAnalyser censusAnalyzer = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyzer.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyzer.getSortedCensusData(CensusAnalyser.SortingMode.STATE);
            CensusDAO[] censuses = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("Andhra Pradesh", censuses[0].state);
        } catch (StateAnalyzerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCensusCsvFile_WhenSortedOnState_ThenReturnSortedLastResult() {
        try {
            CensusAnalyser censusAnalyzer = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyzer.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyzer.getSortedCensusData(CensusAnalyser.SortingMode.STATE);
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("West Bengal", censusCSV[28].state);
        } catch (StateAnalyzerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenImproperFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyzer = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            String sortedCensusData = censusAnalyzer.getSortedCensusData(CensusAnalyser.SortingMode.STATE);
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("West Bengal", censusCSV[28].state);
        } catch (StateAnalyzerException e) {
            Assert.assertEquals(StateAnalyzerException.ExceptionType.NO_SUCH_CENSUS_DATA, e.type);
        }
    }

    @Test
    public void givenStateCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyzer = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyzer.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyzer.getSortedCensusData(CensusAnalyser.SortingMode.POPULATION);
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals(199812341, censusCSV[28].population);
        } catch (StateAnalyzerException e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenStateCensusData_WhenSortedOnDensity_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyzer = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyzer.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyzer.getSortedCensusData(CensusAnalyser.SortingMode.DENSITY);
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals(1102, censusCSV[28].densityPerSqKm, 0);
        } catch (StateAnalyzerException e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenStateCensusData_WhenSortedOnArea_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyzer = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyzer.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyzer.getSortedCensusData(CensusAnalyser.SortingMode.AREA);
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals(342239, censusCSV[28].areaInSqKm, 0);
        } catch (StateAnalyzerException e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenUSCensusData_WhenNumberOfRecordMatches_ThenReturnNumberOfRecords() throws StateAnalyzerException {
        CensusAnalyser censusAnalyzer = new CensusAnalyser(CensusAnalyser.Country.US);
        result = censusAnalyzer.loadCensusData(US_CENSUS_CSV_FILE_PATH);
        Assert.assertEquals(51, result);
    }

    @Test
    public void givenUSStateCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyzer = new CensusAnalyser(CensusAnalyser.Country.US);
            censusAnalyzer.loadCensusData(US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyzer.getSortedCensusData(CensusAnalyser.SortingMode.POPULATION);
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("California", censusCSV[50].state);
        } catch (StateAnalyzerException e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenUSStateCensusData_WhenSortedOnDensity_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyzer = new CensusAnalyser(CensusAnalyser.Country.US);
            censusAnalyzer.loadCensusData(US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyzer.getSortedCensusData(CensusAnalyser.SortingMode.DENSITY);
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("District of Columbia", censusCSV[50].state);
        } catch (StateAnalyzerException e) {
            e.getStackTrace();
        }
    }
}
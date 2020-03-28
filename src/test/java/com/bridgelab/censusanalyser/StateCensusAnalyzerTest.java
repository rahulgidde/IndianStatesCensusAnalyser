package com.bridgelab.censusanalyser;

import com.bridgelab.dao.CensusDAO;
import com.bridgelab.exception.StateAnalyzerException;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class StateCensusAnalyzerTest {
    CensusAnalyser censusAnalyzer = new CensusAnalyser();
    int result;

    @Test
    public void givenStatesCensusCSVFile_WhenNumberOfRecordMatches_ThenReturnNumberOfRecords() throws StateAnalyzerException {
        result = censusAnalyzer.loadCensusData("./src/test/resources/StateCensusData.csv", CSVStatesCensus.class);
        Assert.assertEquals(29, result);
    }

    @Test
    public void givenStatesCensusCSVFile_WhenImproper_ThenReturnFileNotFoundException() {
        try {
            result = censusAnalyzer.loadCensusData("./src/test/resources/StateCensusDat.csv", CSVStatesCensus.class);
        } catch (StateAnalyzerException e) {
            Assert.assertEquals(StateAnalyzerException.ExceptionType.FILE_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenStatesCensusCSVFile_WhenImproper_ThenReturnNoSuchTypeException() {
        try {
            result = censusAnalyzer.loadCensusData("./src/test/resources/StateCensusData.pdf", CSVStatesCensus.class);
        } catch (StateAnalyzerException e) {
            Assert.assertEquals(StateAnalyzerException.ExceptionType.NO_SUCH_TYPE, e.type);
        }
    }

    @Test
    public void givenWrongStatesCensusCSVFile_WhenIncorrectDelimiter_ThenReturnWrongDelimiterException() {
        try {
            result = censusAnalyzer.loadCensusData("./src/test/resources/WrongStateCensusData.csv", CSVStatesCensus.class);
        } catch (StateAnalyzerException e) {
            Assert.assertEquals(StateAnalyzerException.ExceptionType.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenWrongStatesCensusCSVFile_WhenIncorrectHeader_ThenReturnWrongHeaderException() {
        try {
            result = censusAnalyzer.loadCensusData("./src/test/resources/WrongStateCensusData.csv", CSVStatesCensus.class);
        } catch (StateAnalyzerException e) {
            Assert.assertEquals(StateAnalyzerException.ExceptionType.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenStatesCensusCSVFile_WhenNumberOfRecordsMatches_ThenReturnNumberOfRecords() throws StateAnalyzerException {
        result = censusAnalyzer.loadCensusData("./src/test/resources/StateCode.csv", CSVStatesCode.class);
        Assert.assertEquals(37, result);
    }

    @Test
    public void givenStatesCodeCSVFile_WhenImproper_ThenReturnFileNotFoundException() {
        try {
            result = censusAnalyzer.loadCensusData("./src/test/resources/StateCensusDat.csv", CSVStatesCode.class);
        } catch (StateAnalyzerException e) {
            Assert.assertEquals(StateAnalyzerException.ExceptionType.FILE_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenStatesCodeCSVFile_WhenImproper_ThenReturnNoSuchTypeException() {
        try {
            result = censusAnalyzer.loadCensusData("./src/test/resources/StateCensusData.pdf", CSVStatesCode.class);
        } catch (StateAnalyzerException e) {
            Assert.assertEquals(StateAnalyzerException.ExceptionType.NO_SUCH_TYPE, e.type);
        }
    }

    @Test
    public void givenWrongStatesCodeCSVFile_WhenIncorrectDelimiter_ThenReturnWrongDelimiterException() {
        try {
            result = censusAnalyzer.loadCensusData("./src/test/resources/WrongStateCensusData.csv", CSVStatesCode.class);
        } catch (StateAnalyzerException e) {
            Assert.assertEquals(StateAnalyzerException.ExceptionType.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenWrongStatesCodeCSVFile_WhenIncorrectHeader_ThenReturnWrongHeaderException() {
        try {
            result = censusAnalyzer.loadCensusData("./src/test/resources/WrongStateCensusData.csv", CSVStatesCode.class);
        } catch (StateAnalyzerException e) {
            Assert.assertEquals(StateAnalyzerException.ExceptionType.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenStateCensusCsvFile_WhenSortedOnState_ThenReturnSortedFirstResult() {
        try {
            censusAnalyzer.loadCensusData("./src/test/resources/StateCensusData.csv", CSVStatesCensus.class);
            String sortedCensusData = censusAnalyzer.getSortedCensusData();
            CensusDAO[] censuses = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("Andhra Pradesh", censuses[0].state);
        } catch (StateAnalyzerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCensusCsvFile_WhenSortedOnState_ThenReturnSortedLastResult() {
        try {
            censusAnalyzer.loadCensusData("./src/test/resources/StateCensusData.csv", CSVStatesCensus.class);
            String sortedCensusData = censusAnalyzer.getSortedCensusData();
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("West Bengal", censusCSV[28].state);
        } catch (StateAnalyzerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenImproperFile_ShouldThrowException() {
        try {
            String sortedCensusData = censusAnalyzer.getSortedCensusData();
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("West Bengal", censusCSV[28].state);
        } catch (StateAnalyzerException e) {
            Assert.assertEquals(StateAnalyzerException.ExceptionType.NO_SUCH_CENSUS_DATA, e.type);
        }
    }

    @Test
    public void givenStateCodeCsvFile_WhenSortedOnStateCode_ThenReturnSortedFirstResult() {
        try {
            censusAnalyzer.loadCensusData("./src/test/resources/StateCode.csv", CSVStatesCode.class);
            String sortedCodeData = censusAnalyzer.getSortedCodeData();
            CSVStatesCode[] codes = new Gson().fromJson(sortedCodeData, CSVStatesCode[].class);
            Assert.assertEquals("AD", codes[0].getStateCode());
        } catch (StateAnalyzerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCodeCsvFile_WhenSortedOnStateCode_ThenReturnSortedLastResult() {
        try {
            censusAnalyzer.loadCensusData("./src/test/resources/StateCode.csv", CSVStatesCode.class);
            String sortedCodeData = censusAnalyzer.getSortedCodeData();
            CSVStatesCode[] codes = new Gson().fromJson(sortedCodeData, CSVStatesCode[].class);
            Assert.assertEquals("WB", codes[36].getStateCode());
        } catch (StateAnalyzerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCodeData_WhenImproperFile_ShouldThrowException() {
        try {
            String sortedCodeData = censusAnalyzer.getSortedCodeData();
            CSVStatesCode[] codes = new Gson().fromJson(sortedCodeData, CSVStatesCode[].class);
            Assert.assertEquals("WB", codes[36].getStateCode());
        } catch (StateAnalyzerException e) {
            Assert.assertEquals(StateAnalyzerException.ExceptionType.NO_SUCH_CENSUS_DATA, e.type);
        }
    }

    @Test
    public void givenStateCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() {
        try {
            censusAnalyzer.loadCensusData("./src/test/resources/StateCensusData.csv", CSVStatesCensus.class);
            String sortedCensusData = censusAnalyzer.getPopulationWiseSortedCensusData();
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals(199812341, censusCSV[0].population);
        } catch (StateAnalyzerException e) {
            e.getStackTrace();
        }
    }
}

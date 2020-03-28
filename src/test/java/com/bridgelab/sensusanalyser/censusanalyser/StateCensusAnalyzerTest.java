package com.bridgelab.sensusanalyser.censusanalyser;

import com.bridgelab.sensusanalyser.dao.CensusDAO;
import com.bridgelab.sensusanalyser.dto.CSVStatesCensus;
import com.bridgelab.sensusanalyser.dto.CSVStatesCode;
import com.bridgelab.sensusanalyser.exception.StateAnalyzerException;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import com.bridgelab.sensusanalyser.service.CensusAnalyser;

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
            CensusDAO[] codes = new Gson().fromJson(sortedCodeData, CensusDAO[].class);
            Assert.assertEquals("AD", codes[0].stateCode);
        } catch (StateAnalyzerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCodeCsvFile_WhenSortedOnStateCode_ThenReturnSortedLastResult() {
        try {
            censusAnalyzer.loadCensusData("./src/test/resources/StateCode.csv", CSVStatesCode.class);
            String sortedCodeData = censusAnalyzer.getSortedCodeData();
            CensusDAO[] codes = new Gson().fromJson(sortedCodeData, CensusDAO[].class);
            Assert.assertEquals("WB", codes[36].stateCode);
        } catch (StateAnalyzerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCodeData_WhenImproperFile_ShouldThrowException() {
        try {
            String sortedCodeData = censusAnalyzer.getSortedCodeData();
            CensusDAO[] codes = new Gson().fromJson(sortedCodeData, CensusDAO[].class);
            Assert.assertEquals("WB", codes[36]);
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

    @Test
    public void givenStateCensusData_WhenSortedOnDensity_ShouldReturnSortedResult() {
        try {
            censusAnalyzer.loadCensusData("./src/test/resources/StateCensusData.csv", CSVStatesCensus.class);
            String sortedCensusData = censusAnalyzer.getPopulationDensityWiseSortedCensusData();
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals(1102, censusCSV[0].density);
        } catch (StateAnalyzerException e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenStateCensusData_WhenSortedOnArea_ShouldReturnSortedResult() {
        try {
            censusAnalyzer.loadCensusData("./src/test/resources/StateCensusData.csv", CSVStatesCensus.class);
            String sortedCensusData = censusAnalyzer.getAreaWiseSortedCensusData();
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals(342239, censusCSV[0].area);
        } catch (StateAnalyzerException e) {
            e.getStackTrace();
        }
    }
}

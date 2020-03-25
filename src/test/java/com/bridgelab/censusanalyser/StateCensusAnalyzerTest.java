package com.bridgelab.censusanalyser;

import com.bridgelab.CSVStatesCensus;
import com.bridgelab.CensusAnalyzer;
import Exception.StateAnalyzerException;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class StateCensusAnalyzerTest {
    CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
    int result;

    @Test
    public void givenStatesCensusCSVFile_WhenNumberOfRecordMatches_ThenReturnNumberOfRecords() throws StateAnalyzerException {
        result = censusAnalyzer.loadIndianCensusData("./src/test/resources/StateCensusData.csv");
        Assert.assertEquals(29, result);
    }

    @Test
    public void givenStatesCensusCSVFile_WhenImproper_ThenReturnFileNotFoundException() {
        try {
            result = censusAnalyzer.loadIndianCensusData("./src/test/resources/StateCensusDat.csv");
        } catch (StateAnalyzerException e) {
            Assert.assertEquals(StateAnalyzerException.ExceptionType.FILE_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenStatesCensusCSVFile_WhenImproper_ThenReturnNoSuchTypeException() {
        try {
            result = censusAnalyzer.loadIndianCensusData("./src/test/resources/StateCensusData.pdf");
        } catch (StateAnalyzerException e) {
            Assert.assertEquals(StateAnalyzerException.ExceptionType.NO_SUCH_TYPE, e.type);
        }
    }

    @Test
    public void givenWrongStatesCensusCSVFile_WhenIncorrectDelimiter_ThenReturnWrongDelimiterException() {
        try {
            result = censusAnalyzer.loadIndianCensusData("./src/test/resources/WrongStateCensusData.csv");
        } catch (StateAnalyzerException e) {
            Assert.assertEquals(StateAnalyzerException.ExceptionType.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenWrongStatesCensusCSVFile_WhenIncorrectHeader_ThenReturnWrongHeaderException() {
        try {
            result = censusAnalyzer.loadIndianCensusData("./src/test/resources/WrongStateCensusData.csv");
        } catch (StateAnalyzerException e) {
            Assert.assertEquals(StateAnalyzerException.ExceptionType.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenStatesCensusCSVFile_WhenNumberOfRecordsMatches_ThenReturnNumberOfRecords() throws StateAnalyzerException {
        result = censusAnalyzer.loadIndianStateCodeData("./src/test/resources/StateCode.csv");
        Assert.assertEquals(37, result);
    }

    @Test
    public void givenStatesCodeCSVFile_WhenImproper_ThenReturnFileNotFoundException() {
        try {
            result = censusAnalyzer.loadIndianStateCodeData("./src/test/resources/StateCensusDat.csv");
        } catch (StateAnalyzerException e) {
            Assert.assertEquals(StateAnalyzerException.ExceptionType.FILE_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenStatesCodeCSVFile_WhenImproper_ThenReturnNoSuchTypeException() {
        try {
            result = censusAnalyzer.loadIndianStateCodeData("./src/test/resources/StateCensusData.pdf");
        } catch (StateAnalyzerException e) {
            Assert.assertEquals(StateAnalyzerException.ExceptionType.NO_SUCH_TYPE, e.type);
        }
    }

    @Test
    public void givenWrongStatesCodeCSVFile_WhenIncorrectDelimiter_ThenReturnWrongDelimiterException() {
        try {
            result = censusAnalyzer.loadIndianStateCodeData("./src/test/resources/WrongStateCensusData.csv");
        } catch (StateAnalyzerException e) {
            Assert.assertEquals(StateAnalyzerException.ExceptionType.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenWrongStatesCodeCSVFile_WhenIncorrectHeader_ThenReturnWrongHeaderException() {
        try {
            result = censusAnalyzer.loadIndianStateCodeData("./src/test/resources/WrongStateCensusData.csv");
        } catch (StateAnalyzerException e) {
            Assert.assertEquals(StateAnalyzerException.ExceptionType.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenStateCensusCsvFile_WhenSortedOnState_ThenReturnSortedFirstResult() {
        try {
            String sortedCensusData = censusAnalyzer.getSortedCensusData("./src/test/resources/StateCensusData.csv");
            CSVStatesCensus[] censuses = new Gson().fromJson(sortedCensusData, CSVStatesCensus[].class);
            Assert.assertEquals("Andhra Pradesh", censuses[0].state);
        } catch (StateAnalyzerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnState_ShouldReturnLastSortedResult() throws StateAnalyzerException {
        try {
            String sortedCensusData = censusAnalyzer.getSortedCensusData("./src/test/resources/StateCensusData.csv");
            CSVStatesCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStatesCensus[].class);
            Assert.assertEquals("West Bengal", censusCSV[28].state);
        } catch (StateAnalyzerException e) {
            e.printStackTrace();
        }
    }
}

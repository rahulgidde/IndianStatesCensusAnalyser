package com.bridgelab.censusanalyser;

import com.bridgelab.CensusAnalyzer;
import com.bridgelab.StateAnalyzerException;
import org.junit.Assert;
import org.junit.Test;

public class StateCensusAnalyzerTest {
    CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
    int result;

    @Test
    public void givenStatesCensusCSVFile_WhenNumberOfRecordMatches_ThenReturnNumberOfRecords() throws StateAnalyzerException {
        result = censusAnalyzer.getStateCensusRecords("./src/test/resources/StateCensusData.csv");
        Assert.assertEquals(29, result);
    }

    @Test
    public void givenStatesCensusCSVFile_WhenImproper_ThenReturnFileNotFoundException() {
        try {
            result = censusAnalyzer.getStateCensusRecords("./src/test/resources/StateCensusDat.csv");
        } catch (StateAnalyzerException e) {
            Assert.assertEquals(StateAnalyzerException.ExceptionType.FILE_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenStatesCensusCSVFile_WhenImproper_ThenReturnNoSuchTypeException() {
        try {
            result = censusAnalyzer.getStateCensusRecords("./src/test/resources/StateCensusData.pdf");
        } catch (StateAnalyzerException e) {
            Assert.assertEquals(StateAnalyzerException.ExceptionType.NO_SUCH_TYPE, e.type);
        }
    }

    @Test
    public void givenWrongStatesCensusCSVFile_WhenIncorrectDelimiter_ThenReturnWrongDelimiterException() {
        try {
            result = censusAnalyzer.getStateCensusRecords("./src/test/resources/WrongStateCensusData.csv");
        } catch (StateAnalyzerException e) {
            Assert.assertEquals(StateAnalyzerException.ExceptionType.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenWrongStatesCensusCSVFile_WhenIncorrectHeader_ThenReturnWrongHeaderException() {
        try {
            result = censusAnalyzer.getStateCensusRecords("./src/test/resources/WrongStateCensusData.csv");
        } catch (StateAnalyzerException e) {
            Assert.assertEquals(StateAnalyzerException.ExceptionType.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenStatesCensusCSVFile_WhenNumberOfRecordsMatches_ThenReturnNumberOfRecords() throws StateAnalyzerException {
        result = censusAnalyzer.getStateCodeRecords("./src/test/resources/StateCode.csv");
        Assert.assertEquals(37, result);
    }

    @Test
    public void givenStatesCodeCSVFile_WhenImproper_ThenReturnFileNotFoundException() {
        try {
            result = censusAnalyzer.getStateCensusRecords("./src/test/resources/StateCensusDat.csv");
        } catch (StateAnalyzerException e) {
            Assert.assertEquals(StateAnalyzerException.ExceptionType.FILE_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenStatesCodeCSVFile_WhenImproper_ThenReturnNoSuchTypeException() {
        try {
            result = censusAnalyzer.getStateCensusRecords("./src/test/resources/StateCensusData.pdf");
        } catch (StateAnalyzerException e) {
            Assert.assertEquals(StateAnalyzerException.ExceptionType.NO_SUCH_TYPE, e.type);
        }
    }
}

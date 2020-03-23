package com.bridgelab.censusanalyser;

import com.bridgelab.CensusAnalyzer;
import org.junit.Assert;
import org.junit.Test;

public class StateCensusAnalyzerTest {
    CensusAnalyzer censusAnalyzer = new CensusAnalyzer();

    @Test
    public void givenStatesCensusCSVFile_WhenNumberOfRecordMatches_ThenReturnNumberOfRecords() {
        int result = censusAnalyzer.getRecords();
        Assert.assertEquals(29, result);
    }
}
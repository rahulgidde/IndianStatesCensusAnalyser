package com.bridgelab.censusanalyser.adapter;

import com.bridgelab.censusanalyser.dao.CensusDAO;
import com.bridgelab.censusanalyser.dto.USCensusCSV;
import com.bridgelab.censusanalyser.exception.StateAnalyzerException;

import java.util.Map;

public class USCensusAdapter extends CensusAdapter {
    @Override
    public Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws StateAnalyzerException {
        return super.loadCensusData(USCensusCSV.class, csvFilePath[0]);
    }
}

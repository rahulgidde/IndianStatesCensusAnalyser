package com.bridgelab.sensusanalyser.adapter;

import com.bridgelab.sensusanalyser.dao.CensusDAO;
import com.bridgelab.sensusanalyser.dto.USCensusCSV;
import com.bridgelab.sensusanalyser.exception.StateAnalyzerException;

import java.util.Map;

public class USCensusAdapter extends CensusAdapter {
    @Override
    public Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws StateAnalyzerException {
        return super.loadCensusData(USCensusCSV.class, csvFilePath[0]);
    }
}

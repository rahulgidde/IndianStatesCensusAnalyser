package com.bridgelab.sensusanalyser.adapter;

import com.bridgelab.sensusanalyser.service.CensusAnalyser;

public class CensusAdapterFactory {
    public static CensusAdapter getCensusData(CensusAnalyser.Country country) {
        if (country.equals(CensusAnalyser.Country.INDIA))
            return new IndiaCensusAdapter();
        if (country.equals(CensusAnalyser.Country.US))
            return new USCensusAdapter();
        return null;
    }
}

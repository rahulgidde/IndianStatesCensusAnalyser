package com.bridgelab.censusanalyser.dto;

import com.opencsv.bean.CsvBindByName;

public class CSVStatesCensus {

    //DEFAULT CONSTRUCTOR
    public CSVStatesCensus() {
    }

    //PARAMETRIZED CONSTRUCTOR
    public CSVStatesCensus(String state, int population, double areaInSqKm, double densityPerSqKm) {
        this.state = state;
        this.population = population;
        this.areaInSqKm = areaInSqKm;
        this.densityPerSqKm = densityPerSqKm;
    }

    @CsvBindByName(column = "State", required = true)
    private String state;

    @CsvBindByName(column = "Population", required = true)
    private int population;

    @CsvBindByName(column = "AreaInSqKm", required = true)
    private double areaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm", required = true)
    private double densityPerSqKm;

    //GETTER
    public String getState() {
        return state;
    }

    public int getPopulation() {
        return population;
    }

    public double getAreaInSqKm() {
        return areaInSqKm;
    }

    public double getDensityPerSqKm() {
        return densityPerSqKm;
    }

    @Override
    public String toString() {
        return "CSVStatesCensus {" +
                "State='" + state + '\'' +
                " ,Population='" + population + '\'' +
                " ,AreaInSqKm='" + areaInSqKm + '\'' +
                " ,DensityPerSqKm='" + densityPerSqKm + '\'' +
                '}';
    }
}


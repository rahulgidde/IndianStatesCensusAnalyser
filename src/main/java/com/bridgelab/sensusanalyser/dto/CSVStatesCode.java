package com.bridgelab.sensusanalyser.dto;

import com.opencsv.bean.CsvBindByName;

public class CSVStatesCode {
    @CsvBindByName(column = "SrNo", required = true)
    private int SrNo;

    @CsvBindByName(column = "State", required = true)
    private String State;

    @CsvBindByName(column = "Name", required = true)
    private String Name;

    @CsvBindByName(column = "TIN", required = true)
    private String TIN;

    @CsvBindByName(column = "StateCode", required = true)
    private String StateCode;

    //GETTER
    public int getSrNo() {
        return SrNo;
    }

    public String getState() {
        return State;
    }

    public String getName() {
        return Name;
    }

    public String getTIN() {
        return TIN;
    }

    public String getStateCode() {
        return StateCode;
    }

    @Override
    public String toString() {
        return "CSVStates{" +
                "SrNo='" + SrNo + '\'' +
                ", state='" + State + '\'' +
                ", population='" + Name + '\'' +
                ", areaInSqKm='" + TIN + '\'' +
                ", densityPerSqKm='" + StateCode + '\'' +
                '}';
    }
}

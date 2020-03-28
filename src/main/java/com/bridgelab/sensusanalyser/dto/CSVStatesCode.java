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

    //GETTER AND SETTER
    public int getSrNo() {
        return SrNo;
    }

    public void setSrNo(int srNo) {
        SrNo = srNo;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTIN() {
        return TIN;
    }

    public void setTIN(String TIN) {
        this.TIN = TIN;
    }

    public String getStateCode() {
        return StateCode;
    }

    public void setStateCode(String stateCode) {
        StateCode = stateCode;
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

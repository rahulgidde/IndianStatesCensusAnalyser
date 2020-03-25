package com.bridgelab.censusanalyzer;

import com.opencsv.bean.CsvBindByName;

public class CSVStatesCode {
    @CsvBindByName(column = "SrNo", required = true)
    public String SrNo;

    @CsvBindByName(column = "State", required = true)
    public String State;

    @CsvBindByName(column = "Name", required = true)
    public String Name;

    @CsvBindByName(column = "TIN", required = true)
    public String TIN;

    @CsvBindByName(column = "StateCode", required = true)
    public String StateCode;

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

    public String getSrNo() {
        return SrNo;
    }

    public void setSrNo(String srNo) {
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
}

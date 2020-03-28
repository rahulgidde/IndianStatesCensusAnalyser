package com.bridgelab.censusanalyser;

import com.bridgelab.dao.CensusDAO;
import com.bridgelab.exception.CsvBuilderException;
import com.bridgelab.exception.StateAnalyzerException;
import com.google.gson.Gson;
import org.apache.commons.collections.map.HashedMap;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CensusAnalyser {

    List<CensusDAO> csvFileList = null;
    List<CSVStatesCode> csvCodeFileList = null;
    Map<String, CensusDAO> censusMap;
    Map<String, CSVStatesCode> codeMap;

    //DEFAULT CONSTRUCTOR
    public CensusAnalyser() {
        this.censusMap = new HashedMap();
        this.codeMap = new HashedMap();
    }

    //METHOD TO LOAD THE CSV FILE AND GET RECORDS
    public <T> int loadCensusData(String csvFilePath, Class<T> className) throws StateAnalyzerException {
        String extension = getFileExtension(new File(csvFilePath));
        if (extension.compareTo("csv") != 0) {
            throw new StateAnalyzerException(StateAnalyzerException.ExceptionType.NO_SUCH_TYPE,
                    "Invalid file extension");
        }
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            IcsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            Iterator<T> stateCensusIterator = csvBuilder.getCSVFileIterator(reader, className);
            if (className.getName().contains("CSVStatesCensus")) {
                Iterable<CSVStatesCensus> stateCensuses = () -> (Iterator<CSVStatesCensus>) stateCensusIterator;
                StreamSupport.stream(stateCensuses.spliterator(), false)
                        .forEach(csvStateCensus -> censusMap.put(csvStateCensus.getState(), new CensusDAO(csvStateCensus)));
                csvFileList = censusMap.values().stream().collect(Collectors.toList());
                return csvFileList.size();
            }
            if (className.getName().contains("CSVStatesCode")) {
                while (stateCensusIterator.hasNext()) {
                    CSVStatesCode stateCensus = (CSVStatesCode) stateCensusIterator.next();
                    this.codeMap.put(stateCensus.getStateCode(), stateCensus);
                    csvCodeFileList = codeMap.values().stream().collect(Collectors.toList());
                }
                return csvCodeFileList.size();
            }
        } catch (NoSuchFileException e) {
            throw new StateAnalyzerException(StateAnalyzerException.ExceptionType.FILE_NOT_FOUND, "File Not Found");
        } catch (RuntimeException e) {
            throw new StateAnalyzerException(StateAnalyzerException.ExceptionType.WRONG_DELIMITER_OR_HEADER,
                    "Wrong Delimiter Or Header");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvBuilderException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //METHOD TO GET COUNT OF RECORDS
    private <E> int getCount(Iterator<E> iterator) {
        Iterable<E> iterable = () -> iterator;
        int recordCount = (int) StreamSupport.stream(iterable.spliterator(), false).count();
        return recordCount;
    }

    //METHOD TO GET FILE EXTENSION
    public String getFileExtension(File file) {
        String fileExtension = "";
        // GET FILE NAME FIRST
        String fileName = file.getName();
        fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
        return fileExtension;
    }

    //METHOD FOR STATE CENSUS COMPARATOR
    public String getSortedCensusData() throws StateAnalyzerException {
        if (csvFileList == null || csvFileList.size() == 0)
            throw new StateAnalyzerException(StateAnalyzerException.ExceptionType.NO_SUCH_CENSUS_DATA, "Data not found");
        Comparator<CensusDAO> comparator = Comparator.comparing(stateCensus -> stateCensus.state);
        this.sort(comparator, csvFileList);
        String toJson = new Gson().toJson(csvFileList);
        return toJson;
    }

    //METHOD TO SORT STATE CENSUS DATA BY POPULATION
    public String getPopulationWiseSortedCensusData() throws StateAnalyzerException {
        if (csvFileList == null || csvFileList.size() == 0)
            throw new StateAnalyzerException(StateAnalyzerException.ExceptionType.NO_SUCH_CENSUS_DATA, "Data not found");
        Comparator<CensusDAO> comparator = Comparator.comparing(stateCensus -> stateCensus.population);
        this.sort(comparator, csvFileList);
        Collections.reverse(csvFileList);
        String toJson = new Gson().toJson(csvFileList);
        return toJson;
    }

    //METHOD TO SORT STATE CENSUS DATA BY POPULATION
    public String getPopulationDensityWiseSortedCensusData() throws StateAnalyzerException {
        if (csvFileList == null || csvFileList.size() == 0)
            throw new StateAnalyzerException(StateAnalyzerException.ExceptionType.NO_SUCH_CENSUS_DATA, "Data not found");
        Comparator<CensusDAO> comparator = Comparator.comparing(stateCensus -> stateCensus.density);
        this.sort(comparator, csvFileList);
        Collections.reverse(csvFileList);
        String toJson = new Gson().toJson(csvFileList);
        return toJson;
    }

    //METHOD FOR STATE CODE COMPARATOR
    public String getSortedCodeData() throws StateAnalyzerException {
        if (csvCodeFileList == null || csvCodeFileList.size() == 0)
            throw new StateAnalyzerException(StateAnalyzerException.ExceptionType.NO_SUCH_CENSUS_DATA, "Data not found");
        Comparator<CSVStatesCode> comparator = Comparator.comparing(statesCode -> statesCode.getStateCode());
        this.sort(comparator, csvCodeFileList);
        String toJson = new Gson().toJson(csvCodeFileList);
        return toJson;
    }

    //METHOD TO SORT STATE CENSUS DATA BY POPULATION DENSITY
    private <T> void sort(Comparator<T> csvComparator, List<T> csvList) {
        for (int index1 = 0; index1 < csvList.size() - 1; index1++) {
            for (int index2 = 0; index2 < csvList.size() - index1 - 1; index2++) {
                T census1 = csvList.get(index2);
                T census2 = csvList.get(index2 + 1);
                if (csvComparator.compare(census1, census2) > 0) {
                    csvList.set(index2, census2);
                    csvList.set(index2 + 1, census1);
                }
            }
        }
    }
}

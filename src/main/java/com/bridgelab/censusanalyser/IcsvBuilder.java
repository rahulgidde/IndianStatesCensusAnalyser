package com.bridgelab.censusanalyser;

import com.bridgelab.exception.CsvBuilderException;
import com.bridgelab.exception.StateAnalyzerException;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface IcsvBuilder {
    <E> List<E> getCSVFileList(Reader reader, Class<E> csvClass) throws CsvBuilderException, StateAnalyzerException;

    public <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws CsvBuilderException, StateAnalyzerException;
}

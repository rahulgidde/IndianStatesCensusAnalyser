package com.bridgelab;

import Exception.CsvBuilderException;
import Exception.StateAnalyzerException;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface IcsvBuilder {
    <E> List<E> getCSVFileList(Reader reader, Class<E> csvClass) throws CsvBuilderException, StateAnalyzerException;

    public <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws CsvBuilderException, StateAnalyzerException;
}

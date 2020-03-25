package com.bridgelab;

import Exception.StateAnalyzerException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public class CsvBuilder implements IcsvBuilder {

    //GENERIC METHOD TO GET CSV ITERATOR
    public <E> CsvToBean<E> getCSVBean(Reader reader, Class<E> csvClass) throws StateAnalyzerException {
        try {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            return csvToBeanBuilder.build();
        } catch (IllegalStateException e) {
            throw new StateAnalyzerException(StateAnalyzerException.ExceptionType.FILE_NOT_FOUND, "Wrong file");
        }
    }

    @Override
    public <E> List<E> getCSVFileList(Reader reader, Class<E> csvClass) throws StateAnalyzerException {
        return this.getCSVBean(reader, csvClass).parse();
    }

    @Override
    public <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws StateAnalyzerException {
        return this.getCSVBean(reader, csvClass).iterator();
    }
}
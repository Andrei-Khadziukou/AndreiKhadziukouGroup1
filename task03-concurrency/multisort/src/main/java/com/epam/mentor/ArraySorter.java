package com.epam.mentor;

import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.concurrent.Callable;

/**
 * Created by sanko on 10/26/14.
 */
public class ArraySorter implements Callable<Integer[]> {

    private static Logger LOG = Logger.getLogger(ArraySorter.class);

    private Integer[] array;

    public ArraySorter(Integer[] array, int startIndex, int endIndex) {
        this.array = Arrays.copyOfRange(array, startIndex, endIndex);
    }

    @Override
    public Integer[] call() throws Exception {
        LOG.info(Thread.currentThread().getName() + " is executing...");
        Arrays.sort(array);
        return array;
    }
}

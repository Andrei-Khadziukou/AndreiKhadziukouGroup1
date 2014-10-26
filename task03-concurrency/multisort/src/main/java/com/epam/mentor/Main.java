package com.epam.mentor;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by sanko on 10/25/14.
 */
public class Main {
    private static Logger LOG = Logger.getLogger(Main.class);

    public static final int THREAD_AMOUNT = 4;
    public static final int ARRAY_CAPACITY = 200;
    private Integer[] array = new Integer[ARRAY_CAPACITY];

    /**
     * Default constructor.
     */
    public Main() {
        Random random = new Random();
        for (int i = 0; i < ARRAY_CAPACITY; i++) {
            array[i] = random.nextInt(ARRAY_CAPACITY);
            LOG.info(array[i]);
        }
    }

    /**
     * Start sorting of array.
     *
     * @param threadsAmount - amount of threads.
     */
    public void startSort(int threadsAmount) {
        ExecutorService executor = Executors.newFixedThreadPool(threadsAmount);
        sort(threadsAmount, executor);
        Arrays.sort(array);
        showArray(array);
        executor.shutdown();
    }

    /**
     * Recursive method of sorting.
     *
     * @param threads  amount should be a power of two.
     * @param executor contains of pool of threads.
     */
    private void sort(int threads, ExecutorService executor) {
        if (threads == 1) {
            return;
        }

        int delta = ARRAY_CAPACITY / threads;
        List<Callable> callables = new ArrayList<>();

        LOG.info("\n\nCreate callables...");
        for (int i = 0; i < threads; i++) {
            callables.add(new ArraySorter(array, i * delta, (i + 1) * delta));
        }
        int arraysAmount = callables.size();
        LOG.info(String.format("Submit %d futures...", arraysAmount));
        List<Future<Integer[]>> futures = new ArrayList<>(callables.size());
        for (Callable callable : callables) {
            futures.add(executor.submit(callable));
        }

        LOG.info(String.format("Merge %d arrays...", arraysAmount));
        try {
            for (int i = 0; i < futures.size(); i++) {
                Integer[] array = futures.get(i).get();
                merge(array, i * delta, (i + 1) * delta);
            }
        } catch (InterruptedException | ExecutionException e) {
            LOG.warn(e.getMessage(), e);
        }
        showArray(array);
        sort(threads / 2, executor);
    }

    private void merge(Integer[] sortedArray, int startIndex, int endIndex) {
        int index = 0;
        for (int i = startIndex; i < endIndex; i++) {
            array[i] = sortedArray[index++];
        }
    }

    private void showArray(Integer[] array) {
        LOG.info("\nShow sorted array...");
        for (Integer value : array) {
            LOG.info(value);
        }
    }

    public static void main(String[] args) throws IOException {
        int threadsAmount = NumberUtils.toInt(System.getProperty("threadAmount"), THREAD_AMOUNT);
        if (isExpOf2(threadsAmount)) {
            new Main().startSort(threadsAmount);
            return;
        }
        LOG.error("Thread amount is not a power of two");
    }

    private static boolean isExpOf2(int value) {
        return (value != 0) && ((value & (value - 1)) == 0);
    }
}

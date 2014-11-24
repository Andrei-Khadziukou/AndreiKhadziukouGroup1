package com.epam.mentor.repository;

/**
 * Created by sanko on 11/18/14.
 */
public interface ICrud<K, T> {

    public K create(T entity);

    public T read(K key);

    public void update(T entity);

    public void delete(K key);

}

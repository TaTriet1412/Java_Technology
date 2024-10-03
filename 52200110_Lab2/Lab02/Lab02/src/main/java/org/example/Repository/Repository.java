package org.example.Repository;

import java.sql.SQLException;
import java.util.List;

public interface Repository <T,K> {
    K add(T item);
    List<T> readAll();
    T read(K id) throws SQLException;
    boolean update(T item);
    boolean delete(K id);
}

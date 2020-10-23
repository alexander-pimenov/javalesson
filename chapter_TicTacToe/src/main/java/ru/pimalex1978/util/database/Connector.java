package ru.pimalex1978.util.database;

import java.sql.Connection;

public interface Connector extends AutoCloseable {
    @Override
    void close() throws Exception;

    Connection getConnection();
}

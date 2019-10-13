package service;

import lombok.extern.java.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
@Log
public class Executor {
    private final Connection connection;

    public Executor(Connection connection){
        this.connection = connection;
    }

    public int execUpdate(String update){
        int updated = 0;
        try (Statement statement = connection.createStatement()){

            statement.execute(update);
            updated = statement.getUpdateCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updated;
    }

    public <T> T execQuery(String query, ResultHandler<T> handler){
        T value = null;
        try (Statement statement = connection.createStatement()){
            statement.execute(query);
            ResultSet result = statement.getResultSet();
            value = handler.handle(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }
}

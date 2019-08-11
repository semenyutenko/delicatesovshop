package dbservice.dao;

import dbservice.executor.Executor;
import exceptions.DSDBexception;
import lombok.extern.java.Log;
import models.Client;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@Log
public class ClientDAO {
    private final Executor executor;

    public ClientDAO(Connection connection){
        executor = new Executor(connection);
    }

    public ArrayList<Client> getClients() {
        ArrayList<Client> clients = new ArrayList<Client>();
        try {
            executor.execQuery("select * from clients", resultSet ->{
                while (resultSet.next()){
                    Client client = new Client(resultSet.getString("name"),
                            resultSet.getInt("phone"));
                    client.setClientId(resultSet.getInt("client_id"));
                    client.setClientDescription(resultSet.getString("client_description"));
                    clients.add(client);
                }
                return clients;
            });
        } catch (SQLException e) {
            log.warning("the list of clients hasn't been received");
            e.printStackTrace();
        }
        return null;
    }

}

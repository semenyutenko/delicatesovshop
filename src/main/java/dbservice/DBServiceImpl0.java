package dbservice;

import dao.IDAO;

public class DBServiceImpl0 implements  IDBService{
    private final IDAO dao;

    public DBServiceImpl0(final IDAO dao){
        this.dao = dao;
    }
}

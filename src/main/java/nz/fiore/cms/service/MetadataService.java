package nz.fiore.cms.service;

import nz.fiore.cms.pojo.Metadata;

import javax.inject.Singleton;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class MetadataService {

    DataSource dataSource;
    Map<String, Metadata> metadataMap;

    public MetadataService(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public Map<String, Metadata> metadataMap() {
        if (this.metadataMap == null) {
            this.metadataMap = new HashMap<>();
            Metadata metadata = new Metadata("CUSTOMERS",
                    "ID",
                    "CREATE TABLE CUSTOMERS (ID VARCHAR (20) NOT NULL, " +
                            "NAME VARCHAR (20) NOT NULL, " +
                            "AGE  INT NOT NULL," +
                            "ADDRESS CHAR (25), " +
                            "SALARY DECIMAL (18, 2)," +
                            "PRIMARY KEY (ID) )", "NAME ASC");
            this.metadataMap.put("customers", metadata);
        }
        return this.metadataMap;
    }


}

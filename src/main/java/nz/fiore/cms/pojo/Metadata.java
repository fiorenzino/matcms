package nz.fiore.cms.pojo;

import java.util.List;

public class Metadata {
    public String uuid;
    public String table;
    public String table_key;
    public String creation_query;
    public String orderBy;
    public List<FieldDefinition> fields;
    public List<Condition> conditions;

    public Metadata(String table, String table_key, String creation_query, String orderBy) {
        this.table = table;
        this.table_key = table_key;
        this.creation_query = creation_query;
        this.orderBy = orderBy;
    }

    public Metadata() {
    }

    @Override
    public String toString() {
        return "Metadata{" +
                "uuid='" + uuid + '\'' +
                ", table='" + table + '\'' +
                ", creation_query='" + creation_query + '\'' +
                ", table_key='" + table_key + '\'' +
                ", orderBy='" + orderBy + '\'' +
                ", fields=" + fields +
                ", conditions=" + conditions +
                '}';
    }
}

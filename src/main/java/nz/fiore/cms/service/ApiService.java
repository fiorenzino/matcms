package nz.fiore.cms.service;

import io.micronaut.discovery.event.ServiceStartedEvent;
import io.micronaut.http.HttpParameters;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.scheduling.annotation.Async;
import nz.fiore.cms.pojo.Metadata;
import nz.fiore.cms.util.SqlUtils;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Singleton
public class ApiService {

    @Inject
    MetadataService metadataService;

    DataSource dataSource;

    public ApiService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @EventListener
    @Async
    public void loadConferenceData(final ServiceStartedEvent event) {
        System.out.println("Loading data at startup: " + event.toString());
        List<String> initQueries = new ArrayList<>();
        metadataService.metadataMap().forEach(
                (s, metadata) -> {
                    initQueries.add(metadata.creation_query);
                }
        );
        try {
            batch(initQueries.toArray(new String[]{}));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void init() {
        System.out.println("init---START");
        System.out.println("init---END");
    }

    public List<Map<String, Object>> list(String table, HttpParameters httpParameters) throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + table);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return SqlUtils.list(resultSet);
            }
        }
    }

    public Map<String, Object> fecth(String table, String uuid) throws Exception {
        String table_key = "ID";
        if (metadataService.metadataMap().containsKey(table)) {
            Metadata metadata = metadataService.metadataMap().get(table);
            table_key = metadata.table_key;
        }
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + table + " WHERE " + table_key + " = ?");
            preparedStatement.setInt(1, Integer.valueOf(uuid));
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return SqlUtils.single(resultSet);
            }
        }
    }

    public boolean delete(String table, String uuid) throws Exception {
        String table_key = "ID";
        if (metadataService.metadataMap().containsKey(table)) {
            Metadata metadata = metadataService.metadataMap().get(table);
            table_key = metadata.table_key;
        }
        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM " + table + " WHERE " + table_key + " = ?");
            preparedStatement.setInt(1, Integer.valueOf(uuid));
            int result = preparedStatement.executeUpdate();
            return result > 0;
        }
    }

    public void batch(String[] queries) throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            for (String query : queries) {
                statement.addBatch(query);
            }
            statement.executeBatch();
            statement.close();
        }
    }


    public void prova() {
        try (Connection connection = dataSource.getConnection()) {
            connection.prepareStatement("SELECT 1").execute();
            System.out.println(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

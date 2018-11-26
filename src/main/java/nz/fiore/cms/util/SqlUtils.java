package nz.fiore.cms.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlUtils {


    public static List<Map<String, Object>> list(final ResultSet rs)
            throws Exception {
        final var rsmd = rs.getMetaData();
        final var columnCount = rsmd.getColumnCount();
        List<Map<String, Object>> lista = new ArrayList<>();
        while (rs.next()) {
            lista.add(row(rs, rsmd, columnCount));
        }
        return lista;
    }


    public static Map<String, Object> single(ResultSet rs) throws Exception {
        if (rs.next()) {
            final var rsmd = rs.getMetaData();
            final var columnCount = rsmd.getColumnCount();
            return row(rs, rsmd, columnCount);
        }
        return null;
    }

    private static Map<String, Object> row(ResultSet rs, ResultSetMetaData rsmd, int columnCount) throws Exception {
        final Map<String, Object> map = new HashMap<>();
        for (var i = 1; i <= columnCount; i++) {
            map.put(rsmd.getColumnName(i), rs.getObject(i));
        }
        return map;
    }
}

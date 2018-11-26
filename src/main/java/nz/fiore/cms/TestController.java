package nz.fiore.cms;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import nz.fiore.cms.service.ApiService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("/test")
public class TestController {

    @Inject
    ApiService apiService;

    public TestController() {
    }

    @Get("/prova")
    public HttpResponse prova() {
        Map<String, Object> mappa = new HashMap<>();
        mappa.put("nome", "fiorenzo");
        mappa.put("eta", 3L);
        return HttpResponse.ok(mappa);
    }

    @Get("/prove")
    public HttpResponse prove() {
        List<Map<String, Object>> lista = new ArrayList<>();
        Map<String, Object> mappa = new HashMap<>();
        mappa.put("nome", "fiorenzo");
        mappa.put("eta", 3L);
        lista.add(mappa);
        return HttpResponse.ok(lista);
    }

    @Get("/create")
    public HttpResponse create() throws Exception {
        String[] queries = {
                "INSERT INTO CUSTOMERS (ID,NAME,AGE,ADDRESS,SALARY) VALUES (1, 'fiorenzo', 30, 'via roma 1', 1000.00 );",
                "INSERT INTO CUSTOMERS (ID,NAME,AGE,ADDRESS,SALARY) VALUES (2, 'mario', 31, 'via Ahmedabad', 2000.00 );",
                "INSERT INTO CUSTOMERS (ID,NAME,AGE,ADDRESS,SALARY) VALUES (3, 'andrea', 32, 'via ramaz 33', 3000.00 );"
        };
        apiService.batch(queries);
        return HttpResponse.ok();
    }


}
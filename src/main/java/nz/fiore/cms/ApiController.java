package nz.fiore.cms;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import nz.fiore.cms.service.ApiService;
import nz.fiore.cms.util.JsonUtils;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.Map;

import static io.micronaut.http.HttpResponse.ok;
import static io.micronaut.http.HttpResponse.serverError;

@Controller("/api")
public class ApiController {

    @Inject
    ApiService apiService;


    public ApiController() {
    }


    @Get("/{+path}")
    public HttpResponse<?> get(HttpRequest<?> request, String path) throws Exception {
        debug(request, path);
        if (path == null) {
            throw new Exception("errore non c'e niente");
        }
        if (path.contains("/")) {
            String[] pars = path.split("/");
            if (pars.length > 1) {
                return ok(apiService.fecth(pars[0], pars[1]));
            } else {
                return ok(apiService.list(pars[0]));
            }
        } else {
            return ok(apiService.list(path));
        }
    }

    @Post("/{table}")
    public HttpResponse<?> post(@Body String body, @NotNull String table) throws Exception {
        Map<String, Object> map = JsonUtils.fromJson(body);
        return ok();
    }

    @Put("/{table}")
    public HttpResponse<?> put(@Body String body, @NotNull String table) throws Exception {
        Map<String, Object> map = JsonUtils.fromJson(body);
        return ok();
    }

    @Delete("/{table}/{uuid}")
    public HttpResponse<?> delete(HttpRequest<?> request, @NotNull String table, @NotNull String uuid) throws Exception {
        debug(request, null);
        boolean result = apiService.delete(table, uuid);
        if (result)
            return ok();
        return serverError();
    }


    private void debug(HttpRequest<?> request, String path) {
        System.out.println("------------");
        System.out.println("METHOD: " + request.getMethod().name());
        System.out.println("RELATIVE PATH: " + path);
        StringBuffer sb = new StringBuffer();
        request.getParameters().forEach(param -> sb.append(param.getKey() + ":" + param.getValue()));
        if (sb.length() > 0) {
            System.out.println("QUERY: " + path);
        }
        System.out.println("------------");
        System.out.println(request.getPath());
        System.out.println("------------");
        request.getParameters().forEach(param -> System.out.print("," + param.getKey() + ":" + param.getValue()));
        System.out.println("------------");
    }

}
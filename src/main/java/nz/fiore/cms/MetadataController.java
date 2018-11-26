package nz.fiore.cms;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.HttpStatus;

@Controller("/metadata")
public class MetadataController {

    @Get("/")
    public HttpStatus index() {
        return HttpStatus.OK;
    }
}
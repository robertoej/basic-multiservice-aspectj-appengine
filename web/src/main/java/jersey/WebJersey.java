package jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("hello")
public class WebJersey {
    @GET
    public Response hello() {
        return Response.ok("Hello from the web module!").build();
    }
}

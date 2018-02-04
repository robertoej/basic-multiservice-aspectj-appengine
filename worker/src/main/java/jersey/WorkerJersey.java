package jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("hello")
public class WorkerJersey {
    @GET
    public Response hello() {
        return Response.ok("Hello from the worker module!").build();
    }
}

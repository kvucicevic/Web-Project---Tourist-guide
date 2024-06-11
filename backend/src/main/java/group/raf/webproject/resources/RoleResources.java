package group.raf.webproject.resources;

import group.raf.webproject.service.role.RoleService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/roles")
public class RoleResources{

    @Inject
    private RoleService roleService;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        return Response.ok(this.roleService.findAll()).build();
    }

}

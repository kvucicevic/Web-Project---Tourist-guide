package group.raf.webproject.resources;

import group.raf.webproject.dto.role.RoleRequestDTO;
import group.raf.webproject.service.role.RoleService;

import javax.inject.Inject;
import javax.validation.Valid;
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

    @PUT
    @Path("/{id}/update")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Integer id, @Valid RoleRequestDTO roleRequestDTO){
        return Response.ok(this.roleService.update(id, roleRequestDTO)).build();
    }
}

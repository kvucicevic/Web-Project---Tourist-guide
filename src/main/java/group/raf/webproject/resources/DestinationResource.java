package group.raf.webproject.resources;

import group.raf.webproject.dto.destination.DestinationRequestDTO;
import group.raf.webproject.dto.user.UserRequestDTO;
import group.raf.webproject.service.destination.DestinationService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/destinations")
public class DestinationResource {

    /**
     *     DestinationResponseDTO add(DestinationRequestDTO destinationRequestDTO);
     *
     *     List<DestinationResponseDTO> findAll();
     *
     *     DestinationResponseDTO findById(Integer id);
     *
     *     DestinationResponseDTO update(Integer id, DestinationRequestDTO destinationRequestDTO);
     *
     *     DestinationResponseDTO delete(Integer id);
     */

    @Inject
    private DestinationService destinationService;


    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(@Valid DestinationRequestDTO destinationRequestDTO) {
        return Response.ok(destinationService.add(destinationRequestDTO)).build();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        return Response.ok(this.destinationService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Integer id){
        return Response.ok(this.destinationService.findById(id)).build();
    }

    @PUT
    @Path("/{id}/update")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Integer id, @Valid DestinationRequestDTO destinationRequestDTO){
        return Response.ok(this.destinationService.update(id, destinationRequestDTO)).build();
    }

    @DELETE
    @Path("/{id}/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Integer id){
        return Response.ok(this.destinationService.delete(id)).build();
    }


}

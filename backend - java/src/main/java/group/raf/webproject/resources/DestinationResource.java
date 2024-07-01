package group.raf.webproject.resources;

import group.raf.webproject.dto.activity.ActivityRequestDTO;
import group.raf.webproject.dto.destination.DestinationRequestDTO;
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
    public Response addDestination(@Valid DestinationRequestDTO destinationRequestDTO) {
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

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByName(@PathParam("name") String name){
        return Response.ok(this.destinationService.findByName(name)).build();
    }

    @PUT
    @Path("/{id}/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Integer id, @Valid DestinationRequestDTO destinationRequestDTO){
        System.out.println("req: " + destinationRequestDTO.getDescription());
        return Response.ok(this.destinationService.update(id, destinationRequestDTO)).build();
    }

    @DELETE
    @Path("/{id}/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Integer id){
        return Response.ok(this.destinationService.delete(id)).build();
    }

    @POST
    @Path("/{id}/addActivity")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addActivity(@PathParam("id") Integer destinationId, @Valid ActivityRequestDTO activityRequestDTO){
        return Response.ok(this.destinationService.addActivity(destinationId, activityRequestDTO)).build();
    }

    @GET
    @Path("/{id}/allActivities")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response allActivities(@PathParam("id") Integer id) {
        return Response.ok(destinationService.allActivitiesForDestination(id)).build();
    }

}

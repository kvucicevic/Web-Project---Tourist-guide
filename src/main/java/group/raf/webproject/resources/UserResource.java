package group.raf.webproject.resources;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import group.raf.webproject.dto.token.TokenRequestDTO;
import group.raf.webproject.dto.token.TokenResponseDTO;
import group.raf.webproject.dto.user.UserRequestDTO;
import group.raf.webproject.service.user.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/users")
public class UserResource {

    @Inject
    private UserService userService;

    /**
     *     UserResponseDTO add(UserRequestDTO UserRequestDTO);
     *
     *     List<UserResponseDTO> findAll();
     *
     *     UserResponseDTO findById(Integer id);
     *
     *     UserResponseDTO update(Integer id, UserRequestDTO UserRequestDTO);
     *
     *     UserResponseDTO activate(Integer id, boolean activate);
     *
     *     TokenResponseDTO login(TokenRequestDTO tokenRequestDTO);
     *
     *     void delete(Integer id);
     */

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(@Valid UserRequestDTO userRequestDTO) {
        return Response.ok(userService.add(userRequestDTO)).build();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){ //@HeaderParam("Authorization") String authHeader){
//        if (!isValidToken(authHeader))
//            return Response.status(Response.Status.UNAUTHORIZED).build();

        return Response.ok(this.userService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Integer id){
//        if (!isValidToken(authHeader))
//            return Response.status(Response.Status.UNAUTHORIZED).build();

        return Response.ok(this.userService.findById(id)).build();
    }

    @PUT
    @Path("/{id}/update")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Integer id, @Valid UserRequestDTO userRequestDTO){
//        if (!isValidToken(authHeader))
//            return Response.status(Response.Status.UNAUTHORIZED).build();

        return Response.ok(this.userService.update(id, userRequestDTO)).build();
    }

    @PUT
    @Path("/{id}/activate")
    @Produces(MediaType.APPLICATION_JSON)
    public Response activate(@HeaderParam("Authorization") String authHeader, @PathParam("id") Integer id){
        if (!isValidToken(authHeader))
            return Response.status(Response.Status.UNAUTHORIZED).build();

        return Response.ok(this.userService.activate(id, true)).build();
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@Valid TokenRequestDTO tokenRequestDTO) {
        Map<String, String> response = new HashMap<>();

        TokenResponseDTO userResponse = userService.login(tokenRequestDTO);
        if (userResponse == null) {
            response.put("message", "Invalid credentials");
            return Response.status(401, "Unauthorized: wrong credentials").entity(response).build();
        }

//        response.put("jwt", user.split(" , ")[0]);
//        response.put("name", user.split(" , ")[1]);
        return Response.ok(response).build();
    }

    @DELETE
    @Path("/{id}/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@HeaderParam("Authorization") String authHeader, @PathParam("id") Integer id){
        if (!isValidToken(authHeader))
            return Response.status(Response.Status.UNAUTHORIZED).build();

        return Response.ok(this.userService.delete(id)).build();
    }

    private boolean isValidToken(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) return false;
        String token = authHeader.substring("Bearer ".length());

        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWT.require(algorithm)
                    .build()
                    .verify(token);

            // accept the token if the token does not expire in the next 10 minutes
            long currentTimeMillis = System.currentTimeMillis() + 10 * 60 * 1000;
            JWT.require(algorithm)
                    .acceptNotBefore(currentTimeMillis)
                    .build()
                    .verify(token);

        } catch (JWTVerificationException exception){
            return false;                                //Invalid signature/claims
        }
        return true;
    }
}

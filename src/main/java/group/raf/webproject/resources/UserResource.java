package group.raf.webproject.resources;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import group.raf.webproject.dto.token.TokenRequestDTO;
import group.raf.webproject.dto.token.TokenResponseDTO;
import group.raf.webproject.service.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/users")
public class UserResource {

    @Inject
    private UserService userService;


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

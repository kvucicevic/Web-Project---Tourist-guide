package group.raf.webproject.auth;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import group.raf.webproject.auth.annotations.Admin;
import group.raf.webproject.auth.annotations.AdminOrManager;
import group.raf.webproject.auth.annotations.Manager;
import group.raf.webproject.service.user.UserService;

import java.lang.reflect.Method;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class RoleBasedSecurityFilter implements ContainerRequestFilter {

    @Inject
    private UserService userService;

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        // Use this filter only for methods that have the annotations
        Method method = resourceInfo.getResourceMethod();

        if (!method.isAnnotationPresent(Admin.class) &&
                !method.isAnnotationPresent(Manager.class) &&
                !method.isAnnotationPresent(AdminOrManager.class)) {
            return;
        }

        String authHeader = requestContext.getHeaderString("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            return;
        }

        String token = authHeader.split(" ")[1];    // Bearer token
        DecodedJWT jwt = JWT.decode(token);

        if (method.isAnnotationPresent(Admin.class)) {
            if (!userService.isAdmin(jwt)) {
                requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
            }
        } else if (method.isAnnotationPresent(Manager.class)) {
            if (!userService.isEditor(jwt)) {
                requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
            }
        } else if (method.isAnnotationPresent(AdminOrManager.class)) {
            if (!userService.isAdmin(jwt) && !userService.isEditor(jwt)) {
                requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
            }
        }
    }
}


package group.raf.webproject.resources;

import group.raf.webproject.dto.article.ArticleRequestDTO;
import group.raf.webproject.service.article.ArticleService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/articles")
public class ArticleResource {

    /**
     *     ArticleResponseDTO add(ArticleRequestDTO articleRequestDTO);
     *
     *     List<ArticleResponseDTO> findAll();
     *
     *     ArticleResponseDTO findById(Integer id);
     *
     *     ArticleResponseDTO update(Integer id, ArticleRequestDTO articleRequestDTO);
     *
     *     ArticleResponseDTO delete(Integer id);
     */

    @Inject
    private ArticleService articleService;

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(@Valid ArticleRequestDTO articleRequestDTO) {
        return Response.ok(articleService.add(articleRequestDTO)).build();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        return Response.ok(this.articleService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Integer id){
        return Response.ok(this.articleService.findById(id)).build();
    }

    @PUT
    @Path("/{id}/update")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Integer id, @Valid ArticleRequestDTO ArticleRequestDTO){
        return Response.ok(this.articleService.update(id, ArticleRequestDTO)).build();
    }

    @DELETE
    @Path("/{id}/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Integer id){
        return Response.ok(this.articleService.delete(id)).build();
    }
}

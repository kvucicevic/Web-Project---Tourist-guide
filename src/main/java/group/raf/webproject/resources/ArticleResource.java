package group.raf.webproject.resources;

import group.raf.webproject.dto.article.ArticleRequestDTO;
import group.raf.webproject.dto.comment.CommentRequestDTO;
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
     *     CommentResponseDTO addCommentForArticle(CommentRequestDTO commentRequestDTO);
     *
     *     List<CommentResponseDTO> allCommentsForArticle(ArticleRequestDTO articleRequestDTO);
     *
     *     List<ArticleResponseDTO> allArticlesByMostRead();
     *
     *     List<ArticleResponseDTO> allArticlesFromDestination(ArticleRequestDTO articleRequestDTO);
     *
     *     List<ArticleResponseDTO> allArticlesByActivityType(String activityType);
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
    public Response addArticle(@Valid ArticleRequestDTO articleRequestDTO) {
        return Response.ok(articleService.add(articleRequestDTO)).build();
    }

    @POST
    @Path("/{id}/addComment")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addComment(@PathParam("id") Integer id, @Valid CommentRequestDTO commentRequestDTO) {
        return Response.ok(articleService.addCommentForArticle(id, commentRequestDTO)).build();
    }

    @GET
    @Path("/{id}/allComments")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allCommentsForArticle(@PathParam("id") Integer id){
        return Response.ok(this.articleService.allCommentsForArticle(id)).build();
    }

    @GET
    @Path("/mostRead")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allArticlesByMostRead(){
        return Response.ok(this.articleService.allArticlesByMostRead()).build();
    }

    @GET
    @Path("/articlesForDestination")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allArticlesByDestination(@QueryParam("destinationId") Integer destinationId){
        return Response.ok(this.articleService.allArticlesFromDestination(destinationId)).build();
    }

    @GET
    @Path("/articlesByActivity")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allArticlesByActivity(@QueryParam("activityType") String activityType){
        return Response.ok(this.articleService.allArticlesByActivityType(activityType)).build();
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

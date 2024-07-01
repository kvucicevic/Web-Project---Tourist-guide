package group.raf.webproject.service.article;

import group.raf.webproject.database.model.Article;
import group.raf.webproject.database.model.Comment;
import group.raf.webproject.dto.article.ArticleRequestDTO;
import group.raf.webproject.dto.article.ArticleResponseDTO;
import group.raf.webproject.dto.comment.CommentRequestDTO;
import group.raf.webproject.dto.comment.CommentResponseDTO;
import group.raf.webproject.mapper.ArticleMapper;
import group.raf.webproject.mapper.CommentMapper;
import group.raf.webproject.repository.article.ArticleRepository;
import group.raf.webproject.repository.comment.CommentRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class ArticleServiceImpl implements ArticleService {
    
    @Inject
    private ArticleRepository articleRepository;

    @Inject
    private CommentRepository commentRepository;
    
    @Inject
    private ArticleMapper articleMapper;

    @Inject
    private CommentMapper commentMapper;

    
    @Override
    public ArticleResponseDTO add(ArticleRequestDTO articleRequestDTO) {
        Article article = articleRepository.addArticle(articleMapper.articleRequestDTOToArticle(articleRequestDTO));
        return articleMapper.articleToArticleResponseDTO(article);
    }

    @Override
    public List<ArticleResponseDTO> findAll() {
        List<ArticleResponseDTO> articleResponseDTOS = new ArrayList<>();
        for(Article Article : articleRepository.allArticles()){
            articleResponseDTOS.add(articleMapper.articleToArticleResponseDTO(Article));
        }
        return articleResponseDTOS;
    }

    @Override
    public CommentResponseDTO addCommentForArticle(Integer id, CommentRequestDTO commentRequestDTO) {
        Comment comment = commentRepository.addCommentForArticle(id, commentMapper.commentRequestDTOToComment(commentRequestDTO));
        return commentMapper.commentToCommentResponseDTO(comment);
    }

    @Override
    public List<CommentResponseDTO> allCommentsForArticle(Integer id) {
        List<CommentResponseDTO> commentResponseDTOS = new ArrayList<>();
        for(Comment comment : commentRepository.allCommentsForArticle(id)){
            commentResponseDTOS.add(commentMapper.commentToCommentResponseDTO(comment));
        }
        return commentResponseDTOS;
    }

    @Override
    public List<ArticleResponseDTO> allArticlesByMostRead() {
        List<ArticleResponseDTO> articleResponseDTOS = new ArrayList<>();
        for(Article Article : articleRepository.allArticlesByMostRead()){
            articleResponseDTOS.add(articleMapper.articleToArticleResponseDTO(Article));
        }
        return articleResponseDTOS;
    }

    @Override
    public List<ArticleResponseDTO> allArticlesFromDestination(Integer destinationId) {
        List<ArticleResponseDTO> articleResponseDTOS = new ArrayList<>();
        for(Article Article : articleRepository.allArticlesFromDestination(destinationId)){
            articleResponseDTOS.add(articleMapper.articleToArticleResponseDTO(Article));
        }
        return articleResponseDTOS;
    }

    @Override
    public List<ArticleResponseDTO> allArticlesByActivityType(String activityType) {
        List<ArticleResponseDTO> articleResponseDTOS = new ArrayList<>();
        for(Article Article : articleRepository.allArticlesByActivityType(activityType)){
            articleResponseDTOS.add(articleMapper.articleToArticleResponseDTO(Article));
        }
        return articleResponseDTOS;
    }

    @Override
    public ArticleResponseDTO findById(Integer id) {
        return articleMapper.articleToArticleResponseDTO(articleRepository.findArticleById(id));
    }


    @Override
    public ArticleResponseDTO update(Integer id, ArticleRequestDTO articleRequestDTO) {
        Article article = articleMapper.articleRequestDTOToArticle(articleRequestDTO);
        article.setId(id);
        System.out.println("Destination: " + articleRequestDTO.getDestinationId());
        return articleMapper.articleToArticleResponseDTO(articleRepository.updateArticle(article));
    }

    @Override
    public ArticleResponseDTO delete(Integer id) {
        return articleMapper.articleToArticleResponseDTO(articleRepository.deleteArticleById(id));
    }
}

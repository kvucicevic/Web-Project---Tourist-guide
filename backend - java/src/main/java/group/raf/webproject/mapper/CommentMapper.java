package group.raf.webproject.mapper;

import group.raf.webproject.database.model.Comment;
import group.raf.webproject.dto.comment.CommentRequestDTO;
import group.raf.webproject.dto.comment.CommentResponseDTO;

public class CommentMapper {

    public Comment commentRequestDTOToComment(CommentRequestDTO commentRequestDTO) {
        Comment comment = new Comment();
        comment.setId(commentRequestDTO.getId());
        comment.setAuthor(commentRequestDTO.getAuthor());
        comment.setContent(commentRequestDTO.getContent());
        comment.setArticleId(commentRequestDTO.getArticleId());
        return comment;
    }

    public CommentResponseDTO commentToCommentResponseDTO(Comment comment) {
        CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
        commentResponseDTO.setAuthor(comment.getAuthor());
        commentResponseDTO.setContent(comment.getContent());
        commentResponseDTO.setArticleId(comment.getArticleId());
        return commentResponseDTO;
    }
}

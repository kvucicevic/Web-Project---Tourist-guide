package group.raf.webproject;

import group.raf.webproject.mapper.*;
import group.raf.webproject.repository.activity.ActivityRepository;
import group.raf.webproject.repository.activity.ActivityRepositoryImpl;
import group.raf.webproject.repository.article.ArticleRepository;
import group.raf.webproject.repository.article.ArticleRepositoryImpl;
import group.raf.webproject.repository.comment.CommentRepository;
import group.raf.webproject.repository.comment.CommentRepositoryImpl;
import group.raf.webproject.repository.destination.DestinationRepository;
import group.raf.webproject.repository.destination.DestinationRepositoryImpl;
import group.raf.webproject.repository.role.RoleRepository;
import group.raf.webproject.repository.role.RoleRepositoryImpl;
import group.raf.webproject.repository.user.UserRepository;
import group.raf.webproject.repository.user.UserRepositoryImpl;
import group.raf.webproject.service.article.ArticleService;
import group.raf.webproject.service.article.ArticleServiceImpl;
import group.raf.webproject.service.destination.DestinationService;
import group.raf.webproject.service.destination.DestinationServiceImpl;
import group.raf.webproject.service.role.RoleService;
import group.raf.webproject.service.role.RoleServiceImpl;
import group.raf.webproject.service.user.UserService;
import group.raf.webproject.service.user.UserServiceImpl;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/myApp") // root
public class Application extends ResourceConfig {

    public Application() {

        // Ukljucujemo validaciju
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        // Definisemo implementacije u dependency container-u
        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                this.bind(UserRepositoryImpl.class).to(UserRepository.class).in(Singleton.class);
                this.bind(DestinationRepositoryImpl.class).to(DestinationRepository.class).in(Singleton.class);
                this.bind(ArticleRepositoryImpl.class).to(ArticleRepository.class).in(Singleton.class);
                this.bind(RoleRepositoryImpl.class).to(RoleRepository.class).in(Singleton.class);
                this.bind(CommentRepositoryImpl.class).to(CommentRepository.class).in(Singleton.class);
                this.bind(ActivityRepositoryImpl.class).to(ActivityRepository.class).in(Singleton.class);

                this.bind(UserMapper.class).to(UserMapper.class).in(Singleton.class);
                this.bind(DestinationMapper.class).to(DestinationMapper.class).in(Singleton.class);
                this.bind(ArticleMapper.class).to(ArticleMapper.class).in(Singleton.class);
                this.bind(RoleMapper.class).to(RoleMapper.class).in(Singleton.class);
                this.bind(CommentMapper.class).to(CommentMapper.class).in(Singleton.class);
                this.bind(ActivityMapper.class).to(ActivityMapper.class).in(Singleton.class);

                this.bind(UserServiceImpl.class).to(UserService.class).in(Singleton.class);
                this.bind(DestinationServiceImpl.class).to(DestinationService.class).in(Singleton.class);
                this.bind(ArticleServiceImpl.class).to(ArticleService.class).in(Singleton.class);
                this.bind(RoleServiceImpl.class).to(RoleService.class).in(Singleton.class);
            }
        };
        register(binder);

        // Ucitavamo resurse
        packages("group.raf.webproject.database.model");
        packages("group.raf.webproject.mapper");
        packages("group.raf.webproject.resources");
        packages("group.raf.webproject.repository");
        packages("group.raf.webproject.filters");
    }
}

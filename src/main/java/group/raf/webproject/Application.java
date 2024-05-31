package group.raf.webproject;

import group.raf.webproject.mapper.DestinationMapper;
import group.raf.webproject.mapper.UserMapper;
import group.raf.webproject.repository.destination.DestinationRepository;
import group.raf.webproject.repository.destination.DestinationRepositoryImpl;
import group.raf.webproject.repository.user.UserRepository;
import group.raf.webproject.repository.user.UserRepositoryImpl;
import group.raf.webproject.service.destination.DestinationService;
import group.raf.webproject.service.destination.DestinationServiceImpl;
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
                
                this.bind(UserMapper.class).to(UserMapper.class).in(Singleton.class);
                this.bind(DestinationMapper.class).to(DestinationMapper.class).in(Singleton.class);

                this.bind(UserServiceImpl.class).to(UserService.class).in(Singleton.class);
                this.bind(DestinationServiceImpl.class).to(DestinationService.class).in(Singleton.class);
            }
        };
        register(binder);

        // Ucitavamo resurse
        packages("group.raf.webproject.database.model");
        packages("group.raf.webproject.mapper");
        packages("group.raf.webproject.resources");
        packages("group.raf.webproject.repository");
    }
}

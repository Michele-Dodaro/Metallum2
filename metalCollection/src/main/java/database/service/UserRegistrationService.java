package database.service;

import database.persistence.Entity.User;
import database.persistence.repository.UserRepository;
import database.web.model.UserRegistrationRequest;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.Optional;

@ApplicationScoped
public class UserRegistrationService {
    private final UserRepository userRepository;

    public UserRegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Optional<String> register(UserRegistrationRequest request) {
        if (request == null) return Optional.of("Utente nullo");
        Optional<User> optionalUser = userRepository.findByUsername(request.getUsername());
        if (optionalUser.isPresent()) {
            return Optional.of("Utente già esistente");
        }

        User u = new User();
        u.setUsername(request.getUsername());
        u.setPassword(BcryptUtil.bcryptHash(request.getPassword()));
        userRepository.persist(u);
        return Optional.empty();
    }
}

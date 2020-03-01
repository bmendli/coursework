package polytech.spbstu.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import polytech.spbstu.entity.RoleEntity;
import polytech.spbstu.entity.UserEntity;
import polytech.spbstu.repos.RoleRepository;
import polytech.spbstu.repos.UserRepository;
import polytech.spbstu.service.UserService;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    @Override
    public UserEntity register(UserEntity userEntity) {
        RoleEntity roleEntity = roleRepository.findByName("ROLE_USER");
        List<RoleEntity> roles = List.of(roleEntity);
        userEntity.setPassword(encoder.encode(userEntity.getPassword()));
        userEntity.setRoleEntities(roles);
        UserEntity registeredUser = userRepository.save(userEntity);
        return registeredUser;
    }

    @Override
    public List<UserEntity> getAll() {
        final List<UserEntity> users = userRepository.findAll();
        return users;
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserEntity findById(Long id) {
        final UserEntity user = userRepository.findById(id).orElse(null);
        return user;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}

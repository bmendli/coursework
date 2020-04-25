package polytech.spbstu.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import polytech.spbstu.entity.RoleEntity;
import polytech.spbstu.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUserDetails create(UserEntity userEntity) {
        return new JwtUserDetails(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getPassword(),
                mapToGrantedAuthorities(userEntity.getRoleEntities())
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<RoleEntity> roleEntities) {
        return roleEntities.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}

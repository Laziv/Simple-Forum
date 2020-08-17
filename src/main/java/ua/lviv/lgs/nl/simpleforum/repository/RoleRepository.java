package ua.lviv.lgs.nl.simpleforum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lviv.lgs.nl.simpleforum.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Byte> {
    Optional<Role> findByName(String name);
}

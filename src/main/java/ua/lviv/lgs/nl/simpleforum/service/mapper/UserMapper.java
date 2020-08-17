package ua.lviv.lgs.nl.simpleforum.service.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.lviv.lgs.nl.simpleforum.dto.UserDTO;
import ua.lviv.lgs.nl.simpleforum.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {


    @Mapping(source = "creationDate", target = "creationDate", dateFormat = "yyyy-MM-dd, HH:mm")
    UserDTO toDto(User user);

    User toEntity(UserDTO userDTO);

    //UserDetailsImpl toUserDetails(User user, boolean isEnabled);


}

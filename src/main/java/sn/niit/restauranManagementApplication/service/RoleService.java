package sn.niit.restauranManagementApplication.service;

import org.springframework.stereotype.Service;
import sn.niit.restauranManagementApplication.domain.Role;


@Service
public interface RoleService
{
     Role findUserByNom(String nom);


}

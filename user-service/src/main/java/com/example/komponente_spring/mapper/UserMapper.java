package com.example.komponente_spring.mapper;

import com.example.komponente_spring.domain.*;
import com.example.komponente_spring.dto.AdminDto;
import com.example.komponente_spring.dto.ClientDto;
import com.example.komponente_spring.dto.ManagerDto;
import com.example.komponente_spring.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserMapper() {
    }

    public UserDto getUserDtoFromUserDomain(User user){
        if(user instanceof Admin){
            return getAdminDtoFromAdminDomain((Admin) user);
        }
        else if(user instanceof Client){
            return getClientDtoFromClientDomain((Client) user);
        }
        else if(user instanceof Manager){
            return getManagerDtoFromManagerDomain((Manager) user);
        }
        return null;
    }

    public User getUserDomainFromUserDto(UserDto userDto){
        if(userDto instanceof AdminDto){
            return getAdminDomainFromAdminDto((AdminDto) userDto);
        }
        else if(userDto instanceof ClientDto){
            return getClientDomainFromClientDto((ClientDto) userDto);
        }
        else if(userDto instanceof ManagerDto){
            return getManagerDomainFromManagerDto((ManagerDto) userDto);
        }
        return null;
    }

    public ManagerDto getManagerDtoFromManagerDomain(Manager manager){
        ManagerDto managerDto = new ManagerDto();
        managerDto.setFirstName(manager.getFirstName());
        managerDto.setLastName(manager.getLastName());
        managerDto.setEmail(manager.getEmail());
        managerDto.setPassword(manager.getPassword());
        managerDto.setDateOfBirth(manager.getDateOfBirth());
        managerDto.setUsername(manager.getUsername());
        managerDto.setHireDate(manager.getHireDate());
        managerDto.setRole(Role.MANAGER);
        return managerDto;
    }

    public Manager getManagerDomainFromManagerDto(ManagerDto managerDto){
        Manager manager = new Manager();
        manager.setLastName(managerDto.getLastName());
        manager.setEmail(managerDto.getEmail());
        manager.setFirstName(managerDto.getFirstName());
        manager.setPassword(managerDto.getPassword());
        manager.setUsername(managerDto.getUsername());
        manager.setDateOfBirth(managerDto.getDateOfBirth());
        manager.setHireDate(managerDto.getHireDate());
        manager.setRole(Role.MANAGER);
        return manager;
    }

    public AdminDto getAdminDtoFromAdminDomain(Admin admin){
        AdminDto adminDto = new AdminDto();
        adminDto.setFirstName(admin.getFirstName());
        adminDto.setLastName(admin.getLastName());
        adminDto.setPassword(admin.getPassword());
        adminDto.setEmail(admin.getEmail());
        adminDto.setUsername(admin.getUsername());
        adminDto.setDateOfBirth(admin.getDateOfBirth());
        adminDto.setRole(Role.ADMIN);
        return adminDto;
    }
    public Admin getAdminDomainFromAdminDto(AdminDto adminDto){
        Admin admin = new Admin();
        admin.setLastName(adminDto.getLastName());
        admin.setEmail(adminDto.getEmail());
        admin.setFirstName(adminDto.getFirstName());
        admin.setPassword(adminDto.getPassword());
        admin.setUsername(adminDto.getUsername());
        admin.setDateOfBirth(adminDto.getDateOfBirth());
        admin.setRole(Role.ADMIN);
        return admin;
    }

    public ClientDto getClientDtoFromClientDomain(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setFirstName(client.getFirstName());
        clientDto.setLastName(client.getLastName());
        clientDto.setEmail(client.getEmail());
        clientDto.setPassword(client.getPassword());
        clientDto.setDateOfBirth(client.getDateOfBirth());
        clientDto.setUsername(client.getUsername());
        clientDto.setNumberOfReservations(client.getNumberOfReservations());
        clientDto.setRole(Role.CLIENT);
        return clientDto;
    }

    public Client getClientDomainFromClientDto(ClientDto clientDto) {
        Client client = new Client();
        client.setEmail(clientDto.getEmail());
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setPassword(clientDto.getPassword());
        client.setDateOfBirth(clientDto.getDateOfBirth());
        client.setUsername(clientDto.getUsername());
        client.setNumberOfReservations(clientDto.getNumberOfReservations());
        client.setRole(Role.CLIENT);
        return client;
    }

}

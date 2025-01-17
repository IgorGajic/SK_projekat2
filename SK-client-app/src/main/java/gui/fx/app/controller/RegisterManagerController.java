package gui.fx.app.controller;

import gui.fx.app.Main;
import gui.fx.app.restclient.UserServiceRestClient;
import gui.fx.app.restclient.dto.ManagerCreateDto;
import gui.fx.app.restclient.dto.ManagerDto;
import gui.fx.app.view.RegisterManagerView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

public class RegisterManagerController implements EventHandler<ActionEvent> {

    private RegisterManagerView registerManagerView;
    private UserServiceRestClient userServiceRestClient;

    public RegisterManagerController(RegisterManagerView registerManagerView){
        this.registerManagerView = registerManagerView;
        this.userServiceRestClient = new UserServiceRestClient();
    }
    @Override
    public void handle(ActionEvent event) {
        ManagerCreateDto managerCreateDto = new ManagerCreateDto();
        managerCreateDto.setLastName(registerManagerView.getTfLastName().getText());
        managerCreateDto.setFirstName(registerManagerView.getTfFirstName().getText());
        managerCreateDto.setEmail(registerManagerView.getTfEmail().getText());
        managerCreateDto.setUsername(registerManagerView.getTfUsername().getText());
        managerCreateDto.setPassword(registerManagerView.getTfPassword().getText());
        managerCreateDto.setDateOfBirth(Date.valueOf("1999-01-01"));
        managerCreateDto.setHireDate(Date.valueOf(LocalDate.now()));
        managerCreateDto.setRole("MANAGER");
        try {
            ManagerDto managerDto = userServiceRestClient.registerManager(managerCreateDto);
            System.out.println(managerDto);
            Main.secondStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

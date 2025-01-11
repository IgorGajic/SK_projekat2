package gui.fx.app.controller;

import gui.fx.app.Main;
import gui.fx.app.restclient.UserServiceRestClient;
import gui.fx.app.restclient.dto.ClientCreateDto;
import gui.fx.app.restclient.dto.ClientDto;
import gui.fx.app.view.RegisterClientView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.sql.Date;

public class RegisterClientController implements EventHandler<ActionEvent> {

    private RegisterClientView registerClientView;
    private UserServiceRestClient userServiceRestClient;

    public RegisterClientController(RegisterClientView registerClientView){
        this.registerClientView = registerClientView;
        this.userServiceRestClient = new UserServiceRestClient();
    }
    @Override
    public void handle(ActionEvent event) {
        ClientCreateDto clientCreateDto = new ClientCreateDto();
        clientCreateDto.setFirstName(registerClientView.getTfFirstName().getText());
        clientCreateDto.setLastName(registerClientView.getTfLastName().getText());
        clientCreateDto.setEmail(registerClientView.getTfEmail().getText());
        clientCreateDto.setUsername(registerClientView.getTfUsername().getText());
        clientCreateDto.setPassword(registerClientView.getTfPassword().getText());
        clientCreateDto.setDateOfBirth(Date.valueOf("1999-01-01"));
        clientCreateDto.setNumberOfReservations(0);
        clientCreateDto.setRole("CLIENT");

        try {
            ClientDto client = userServiceRestClient.registerClient(clientCreateDto);
            System.out.println(client);
            Main.secondStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

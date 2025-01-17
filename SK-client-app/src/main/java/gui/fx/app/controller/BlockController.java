package gui.fx.app.controller;

import gui.fx.app.restclient.UserServiceRestClient;
import gui.fx.app.restclient.dto.UserDto;
import gui.fx.app.view.AdminView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.net.URISyntaxException;

public class BlockController implements EventHandler<ActionEvent> {
    private UserServiceRestClient userServiceRestClient;
    private AdminView adminView;

    public BlockController(AdminView adminView) {
        this.adminView = adminView;
        this.userServiceRestClient = new UserServiceRestClient();
    }

    @Override
    public void handle(ActionEvent event) {
        UserDto userDto = adminView.getUserTable().getSelectionModel().getSelectedItem();
        try {
            userServiceRestClient.blockUser(userDto.getUsername(), userDto.getEmail());
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}

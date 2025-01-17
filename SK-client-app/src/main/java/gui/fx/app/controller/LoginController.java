package gui.fx.app.controller;

import gui.fx.app.ClientApp;
import gui.fx.app.Main;
import gui.fx.app.model.User;
import gui.fx.app.restclient.UserServiceRestClient;
import gui.fx.app.view.AdminView;
import gui.fx.app.view.ReservationView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController implements EventHandler<ActionEvent> {

    private TextField username;
    private TextField password;
    private UserServiceRestClient userServiceRestClient;

    public LoginController(TextField username, TextField password) {
        this.username = username;
        this.password = password;
        userServiceRestClient = new UserServiceRestClient();
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            String token = userServiceRestClient.login(username.getText(), password.getText());
            ClientApp.getInstance().setToken(token);

            User user = userServiceRestClient.getUser(token);
            Scene sc;

            if(user.getRole().equals("ADMIN")) {
                sc = new Scene(new AdminView(), 800, 800);
            }
            else {
                sc = new Scene(new ReservationView(), 800, 800);
            }
            Main.mainStage.setScene(sc);
            Main.mainStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

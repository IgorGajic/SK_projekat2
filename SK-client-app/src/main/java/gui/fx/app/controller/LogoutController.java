package gui.fx.app.controller;

import gui.fx.app.ClientApp;
import gui.fx.app.Main;
import gui.fx.app.model.User;
import gui.fx.app.restclient.UserServiceRestClient;
import gui.fx.app.view.AdminView;
import gui.fx.app.view.LoginView;
import gui.fx.app.view.ReservationView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LogoutController implements EventHandler<ActionEvent> {


    public LogoutController() {
    }

    @Override
    public void handle(ActionEvent event) {
        ClientApp.getInstance().setToken(null);
        Main.mainStage.setScene(new Scene(new LoginView(), 300, 300));
        Main.mainStage.show();
    }
}

package gui.fx.app.controller;

import gui.fx.app.restclient.ReservationServiceRestClient;
import gui.fx.app.restclient.dto.ReservationDto;
import gui.fx.app.restclient.dto.ReservationSlotDto;
import gui.fx.app.view.ReservationView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;

public class ReservationController implements EventHandler<ActionEvent> {

    private ReservationView reservationView;
    private ReservationServiceRestClient reservationServiceRestClient;

    public ReservationController(ReservationView reservationView) {
        this.reservationView = reservationView;
        this.reservationServiceRestClient = new ReservationServiceRestClient();
    }

    @Override
    public void handle(ActionEvent event) {
        ReservationSlotDto selected = reservationView.getTableReservation().getSelectionModel().getSelectedItem();
        try {
            ReservationDto reservation = reservationServiceRestClient.makeReservation(1L, selected.getRestaurantId(), "ACTIVE");
            reservationView.getReservationList().add(reservation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ReservationView getReservationView() {
        return reservationView;
    }

    public void setReservationView(ReservationView reservationView) {
        this.reservationView = reservationView;
    }

    public ReservationServiceRestClient getReservationServiceRestClient() {
        return reservationServiceRestClient;
    }

    public void setReservationServiceRestClient(ReservationServiceRestClient reservationServiceRestClient) {
        this.reservationServiceRestClient = reservationServiceRestClient;
    }
}

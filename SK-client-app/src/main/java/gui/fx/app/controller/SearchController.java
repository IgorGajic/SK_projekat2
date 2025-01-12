package gui.fx.app.controller;

import gui.fx.app.restclient.ReservationServiceRestClient;
import gui.fx.app.restclient.dto.ReservationSlotDto;
import gui.fx.app.view.ReservationView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class SearchController implements EventHandler<ActionEvent> {

    private ReservationView reservationView;
    private ReservationServiceRestClient reservationServiceRestClient;

    public SearchController(ReservationView reservationView) {
        this.reservationView = reservationView;
        this.reservationServiceRestClient = new ReservationServiceRestClient();
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            reservationView.getReservationSlotList().clear();
            List<ReservationSlotDto> reservationSlotListDto = reservationServiceRestClient.getAvailable(
                    reservationView.getTfRestaurant().getText(),
                    reservationView.getTfFrom().getText(),
                    reservationView.getTfTo().getText());

            reservationView.getReservationSlotList().addAll(reservationSlotListDto);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

}

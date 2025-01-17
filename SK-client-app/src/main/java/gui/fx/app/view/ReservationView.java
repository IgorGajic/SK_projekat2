package gui.fx.app.view;

import gui.fx.app.controller.LogoutController;
import gui.fx.app.controller.ReservationController;
import gui.fx.app.controller.SearchController;
import gui.fx.app.restclient.ReservationServiceRestClient;
import gui.fx.app.restclient.dto.ReservationDto;
import gui.fx.app.restclient.dto.ReservationSlotDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ReservationView extends VBox {

    private HBox hBoxFilers;

    private Label lblRestaurant;
    private Label lblFrom;
    private Label lblTo;
    private Label lblReservation;
    private TextField tfRestaurant;
    private TextField tfFrom;
    private TextField tfTo;
    private Button btnSearch;
    private Button btnReserve;
    private Button btnLogout;

    private ObservableList<ReservationSlotDto> reservationSlotList;
    private TableView<ReservationSlotDto>  tableReservation;
    private ObservableList<ReservationDto> reservationList;
    private TableView<ReservationDto>  userReservationtable;

    private ReservationServiceRestClient reservationServiceRestClient;

    public ReservationView(){
        initViewElements();
        addElements();
        addListeners();
        initReservations();
    }

    private void initReservations() {
        reservationServiceRestClient = new ReservationServiceRestClient();
        try {
            reservationList.addAll(reservationServiceRestClient.getReservations());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addListeners() {
        btnSearch.setOnAction(new SearchController(this));
        btnReserve.setOnAction(new ReservationController(this));
        btnLogout.setOnAction(new LogoutController());
    }

    private void addElements() {
        this.hBoxFilers.getChildren().addAll(lblRestaurant, tfRestaurant, lblFrom, tfFrom, lblTo, tfTo, btnSearch, btnLogout);
        this.getChildren().addAll(hBoxFilers, tableReservation, btnReserve, lblReservation, userReservationtable);
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);
    }

    private void initViewElements() {
        hBoxFilers = new HBox(5);
        lblRestaurant = new Label("Restaurant");
        lblFrom = new Label("From");
        lblTo = new Label("To");
        lblReservation = new Label("Your reservations");
        tfRestaurant = new TextField();
        tfFrom = new TextField();
        tfTo = new TextField();
        btnSearch = new Button("Search");
        btnReserve = new Button("Reserve");
        btnLogout = new Button("Logout");
        reservationSlotList = FXCollections.observableArrayList();
        tableReservation = new TableView<>(reservationSlotList);
        reservationList = FXCollections.observableArrayList();
        userReservationtable = new TableView<>(reservationList);

        TableColumn<ReservationSlotDto, String> col1 = new TableColumn<>("Restaurant");
        col1.setCellValueFactory(new PropertyValueFactory<>("restaurantId"));
        TableColumn<ReservationSlotDto, String> col2 = new TableColumn<>("Table number");
        col2.setCellValueFactory(new PropertyValueFactory<>("tableNumber"));
        TableColumn<ReservationSlotDto, Double> col3 = new TableColumn<>("Number of seats");
        col3.setCellValueFactory(new PropertyValueFactory<>("numberOfSeats"));
        TableColumn<ReservationSlotDto, Double> col4 = new TableColumn<>("Smoking");
        col4.setCellValueFactory(new PropertyValueFactory<>("smokingAllowed"));
        TableColumn<ReservationSlotDto, Double> col5 = new TableColumn<>("Inside");
        col5.setCellValueFactory(new PropertyValueFactory<>("isInside"));

        tableReservation.getColumns().add(col1);
        tableReservation.getColumns().add(col2);
        tableReservation.getColumns().add(col3);
        tableReservation.getColumns().add(col4);
        tableReservation.getColumns().add(col5);

        TableColumn<ReservationDto, String> kol1 = new TableColumn<>("Restaurant");
        kol1.setCellValueFactory(new PropertyValueFactory<>("restaurantId"));
        TableColumn<ReservationDto, String> kol2 = new TableColumn<>("Status");
        kol2.setCellValueFactory(new PropertyValueFactory<>("status"));

        userReservationtable.getColumns().add(kol1);
        userReservationtable.getColumns().add(kol2);
    }

    public TableView<ReservationSlotDto> getTableReservation() {
        return tableReservation;
    }

    public ObservableList<ReservationDto> getReservationList() {
        return reservationList;
    }

    public TableView<ReservationDto> getUserReservationtable() {
        return userReservationtable;
    }

    public TextField getTfFrom() {
        return tfFrom;
    }

    public TextField getTfTo() {
        return tfTo;
    }

    public HBox gethBoxFilers() {
        return hBoxFilers;
    }

    public void sethBoxFilers(HBox hBoxFilers) {
        this.hBoxFilers = hBoxFilers;
    }

    public Label getLblRestaurant() {
        return lblRestaurant;
    }

    public void setLblRestaurant(Label lblRestaurant) {
        this.lblRestaurant = lblRestaurant;
    }

    public Label getLblFrom() {
        return lblFrom;
    }

    public void setLblFrom(Label lblFrom) {
        this.lblFrom = lblFrom;
    }

    public Label getLblTo() {
        return lblTo;
    }

    public void setLblTo(Label lblTo) {
        this.lblTo = lblTo;
    }

    public Label getLblReservation() {
        return lblReservation;
    }

    public void setLblReservation(Label lblReservation) {
        this.lblReservation = lblReservation;
    }

    public TextField getTfRestaurant() {
        return tfRestaurant;
    }

    public void setTfRestaurant(TextField tfRestaurant) {
        this.tfRestaurant = tfRestaurant;
    }

    public void setTfFrom(TextField tfFrom) {
        this.tfFrom = tfFrom;
    }

    public void setTfTo(TextField tfTo) {
        this.tfTo = tfTo;
    }

    public Button getBtnSearch() {
        return btnSearch;
    }

    public void setBtnSearch(Button btnSearch) {
        this.btnSearch = btnSearch;
    }

    public Button getBtnReserve() {
        return btnReserve;
    }

    public void setBtnReserve(Button btnReserve) {
        this.btnReserve = btnReserve;
    }

    public ObservableList<ReservationSlotDto> getReservationSlotList() {
        return reservationSlotList;
    }

    public void setReservationSlotList(ObservableList<ReservationSlotDto> reservationSlotList) {
        this.reservationSlotList = reservationSlotList;
    }

    public void setTableReservation(TableView<ReservationSlotDto> tableReservation) {
        this.tableReservation = tableReservation;
    }

    public void setReservationList(ObservableList<ReservationDto> reservationList) {
        this.reservationList = reservationList;
    }

    public void setUserReservationtable(TableView<ReservationDto> userReservationtable) {
        this.userReservationtable = userReservationtable;
    }

    public ReservationServiceRestClient getReservationServiceRestClient() {
        return reservationServiceRestClient;
    }

    public void setReservationServiceRestClient(ReservationServiceRestClient reservationServiceRestClient) {
        this.reservationServiceRestClient = reservationServiceRestClient;
    }

    public Button getBtnLogout() {
        return btnLogout;
    }

    public void setBtnLogout(Button btnLogout) {
        this.btnLogout = btnLogout;
    }
}

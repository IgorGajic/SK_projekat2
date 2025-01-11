package gui.fx.app.view;

import gui.fx.app.controller.BlockController;
import gui.fx.app.controller.UnblockController;
import gui.fx.app.restclient.UserServiceRestClient;
import gui.fx.app.restclient.dto.UserDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URISyntaxException;

public class AdminView extends VBox {
    private ObservableList<UserDto> userDtos;
    private TableView<UserDto>  userTable;
    private UserServiceRestClient userServiceRestClient;

    private Button blockBtn;
    private Button unblockBtn;

    public AdminView(){
        initViewElements();
        addElements();
        addListeners();
        initReservations();
    }

    private void initReservations() {
        userServiceRestClient = new UserServiceRestClient();
        try {
            userDtos.addAll(userServiceRestClient.getAllUsers());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private void addListeners() {
        blockBtn.setOnAction(new BlockController(this));
        unblockBtn.setOnAction(new UnblockController(this));
    }

    private void addElements() {
        this.getChildren().addAll(userTable, blockBtn, unblockBtn);
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);
    }

    private void initViewElements() {
        blockBtn = new Button("Block user");
        unblockBtn = new Button("Unblock user");

        userDtos = FXCollections.observableArrayList();
        userTable = new TableView<>(userDtos);

        TableColumn<UserDto, String> col1 = new TableColumn<>("Username");
        col1.setCellValueFactory(new PropertyValueFactory<>("username"));
        TableColumn<UserDto, String> col2 = new TableColumn<>("First name");
        col2.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn<UserDto, String> col3 = new TableColumn<>("Last name");
        col3.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn<UserDto, String> col4 = new TableColumn<>("Blocked");
        col4.setCellValueFactory(new PropertyValueFactory<>("blocked"));

        userTable.getColumns().add(col1);
        userTable.getColumns().add(col2);
        userTable.getColumns().add(col3);
        userTable.getColumns().add(col4);

    }

    public ObservableList<UserDto> getUserDtos() {
        return userDtos;
    }

    public void setUserDtos(ObservableList<UserDto> userDtos) {
        this.userDtos = userDtos;
    }

    public TableView<UserDto> getUserTable() {
        return userTable;
    }

    public void setUserTable(TableView<UserDto> userTable) {
        this.userTable = userTable;
    }

    public UserServiceRestClient getUserServiceRestClient() {
        return userServiceRestClient;
    }

    public void setUserServiceRestClient(UserServiceRestClient userServiceRestClient) {
        this.userServiceRestClient = userServiceRestClient;
    }

    public Button getBlockBtn() {
        return blockBtn;
    }

    public void setBlockBtn(Button blockBtn) {
        this.blockBtn = blockBtn;
    }

    public Button getUnblockBtn() {
        return unblockBtn;
    }

    public void setUnblockBtn(Button unblockBtn) {
        this.unblockBtn = unblockBtn;
    }
}

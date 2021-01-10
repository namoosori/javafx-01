package io.nextree.javafx.starter.angrycircle;

import io.nextree.javafx.starter.angrycircle.controls.GrowingCircle;
import io.nextree.javafx.starter.angrycircle.controls.RelaxButtonTableCell;
import io.nextree.javafx.starter.angrycircle.viewmodel.GrowingCircleModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    //
    @FXML private TextField nameTextField;
    @FXML private Button createButton;
    @FXML private AnchorPane anchorPane;

    @FXML private TableView<GrowingCircleModel> tableView;
    @FXML private TableColumn<String, GrowingCircleModel> nameTableColumn;
    @FXML private TableColumn<Integer, GrowingCircleModel> xTableColumn;
    @FXML private TableColumn<Integer, GrowingCircleModel> yTableColumn;
    @FXML private TableColumn<Double, GrowingCircleModel> radiusTableColumn;
    @FXML private TableColumn<Boolean, GrowingCircle> minimizeTableColumn;

    private ObservableList<GrowingCircleModel> growingCircleModels;
    private GrowingCircle selectedGrowingCircle;

    public Controller() {
        //
        this.growingCircleModels = FXCollections.observableArrayList();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //
        Platform.runLater(() -> {
            initializeTableView();
            bindEvents();
        });
    }

    private void initializeTableView() {
        //
        tableView.setItems(growingCircleModels);
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        xTableColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
        yTableColumn.setCellValueFactory(new PropertyValueFactory<>("y"));
        radiusTableColumn.setCellValueFactory(new PropertyValueFactory<>("radius"));
        minimizeTableColumn.setCellValueFactory(new PropertyValueFactory<>("angry"));
        minimizeTableColumn.setCellFactory(cellValue -> new RelaxButtonTableCell());
    }

    private void bindEvents() {
        //
        createButton.setOnAction(event -> {
            String text = nameTextField.getText();
            if (!(text.isEmpty() || "".equals(text))) {
                GrowingCircleModel growingCircleModel = new GrowingCircleModel(text);
                growingCircleModels.add(growingCircleModel);
                GrowingCircle growingCircle = new GrowingCircle(growingCircleModel);
                anchorPane.getChildren().add(growingCircle);
                growingCircle.setOnMouseClicked(mouseEvent -> growingCircle.setRadius(growingCircle.getRadius() + 5.0));
                growingCircle.setOnMousePressed(mouseEvent -> selectedGrowingCircle = growingCircle);
            }
        });

        anchorPane.setOnMouseClicked(event -> selectedGrowingCircle = null);
        anchorPane.setOnMouseDragged(event -> {
            if (selectedGrowingCircle != null) {
                selectedGrowingCircle.setCenterX(event.getSceneX());
                selectedGrowingCircle.setCenterY(event.getSceneY());
            }
        });
    }
}

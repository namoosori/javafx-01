package io.nextree.javafx.starter.angrycircle.controls;

import io.nextree.javafx.starter.angrycircle.util.ImagePatternUtil;
import io.nextree.javafx.starter.angrycircle.viewmodel.GrowingCircleModel;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Cursor;
import javafx.scene.shape.Circle;

public class GrowingCircle extends Circle {
    //
    private SimpleStringProperty name;
    private SimpleBooleanProperty angry;

    private GrowingCircle() {
        super(GrowingCircleModel.initialX,
                GrowingCircleModel.initialY,
                GrowingCircleModel.initialRadius);
        this.name = new SimpleStringProperty();
        this.angry = new SimpleBooleanProperty();
        this.setCursor(Cursor.HAND);
        this.setFill(ImagePatternUtil.goodCircle);
    }

    public GrowingCircle(GrowingCircleModel model) {
        this();
        this.centerXProperty().bindBidirectional(model.xProperty());
        this.centerYProperty().bindBidirectional(model.yProperty());
        this.radiusProperty().bindBidirectional(model.radiusProperty());
        this.name.bind(model.nameProperty());
        this.angry.bindBidirectional(model.angryProperty());

        this.angry.addListener(((observable, oldValue, newValue) -> {
            if (oldValue != newValue) {
                this.setFill(newValue ? ImagePatternUtil.angryCircle : ImagePatternUtil.goodCircle);
            }
        }));
    }
}

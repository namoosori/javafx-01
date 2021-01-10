package io.nextree.javafx.starter.angrycircle.viewmodel;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class GrowingCircleModel {
    //
    public static final double limitedRadius = 30.0;
    public static final int initialX = 250;
    public static final int initialY = 100;
    public static final double initialRadius = 10.0;

    private SimpleStringProperty name;
    private SimpleIntegerProperty x;
    private SimpleIntegerProperty y;
    private SimpleDoubleProperty radius;
    private SimpleBooleanProperty angry;

    private GrowingCircleModel() {
        //
        this.name = new SimpleStringProperty();
        this.x = new SimpleIntegerProperty(initialX);
        this.y = new SimpleIntegerProperty(initialY);
        this.radius = new SimpleDoubleProperty(initialRadius);
        this.angry = new SimpleBooleanProperty();
        this.radius.addListener(((observable, oldValue, newValue) ->
                this.angry.set(newValue.doubleValue() > limitedRadius)));
    }

    public GrowingCircleModel(String name) {
        this();
        this.name.set(name);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleIntegerProperty xProperty() {
        return x;
    }

    public SimpleIntegerProperty yProperty() {
        return y;
    }

    public SimpleDoubleProperty radiusProperty() {
        return radius;
    }

    public SimpleBooleanProperty angryProperty() {
        return angry;
    }
}

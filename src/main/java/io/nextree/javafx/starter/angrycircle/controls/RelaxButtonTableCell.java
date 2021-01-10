package io.nextree.javafx.starter.angrycircle.controls;

import io.nextree.javafx.starter.angrycircle.viewmodel.GrowingCircleModel;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

public class RelaxButtonTableCell<T> extends TableCell<T, Boolean> {
    //
    @Override
    protected void updateItem(Boolean item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || getTableRow().getItem() == null) {
            setGraphic(null);
            return;
        }

        GrowingCircleModel model = (GrowingCircleModel) getTableRow().getItem();
        RelaxButton relaxButton = getRelaxButton();
        relaxButton.rebind(model);
    }

    private class RelaxButton extends Button {
        //
        private static final String buttonText = "Relax!";
        private static final String buttonStyle = "relaxButton";

        private RelaxButton() {
            super(buttonText);
            this.getStyleClass().add(buttonStyle);
        }

        private void rebind(GrowingCircleModel growingCircleModel) {
            this.visibleProperty().unbind();
            this.visibleProperty().bind(growingCircleModel.angryProperty());
            this.setOnAction(event -> growingCircleModel.radiusProperty().set(GrowingCircleModel.initialRadius));
        }
    }

    private RelaxButton getRelaxButton() {
        if (getGraphic() == null) {
            setGraphic(new RelaxButton());
        }
        return (RelaxButton) getGraphic();
    }
}

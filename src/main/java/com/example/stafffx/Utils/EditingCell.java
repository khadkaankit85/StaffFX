package com.example.stafffx.Utils;

import com.example.stafffx.Model.Employee;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;

public class EditingCell extends TableCell<Employee, String> {
    private TextField textField;

    public EditingCell() {
        super();
    }

    @Override
    public void startEdit() {
        super.startEdit();
        if (textField == null) {
            createTextField();
        }
        setGraphic(textField);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        textField.requestFocus();
    }

    private void createTextField() {
        textField = new TextField(getItem());
        textField.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ENTER:
                    commitEdit(textField.getText());
                    break;
                case ESCAPE:
                    cancelEdit();
                    break;
                default:
                    break;
            }
        });
    }

    @Override
    public void commitEdit(String newValue) {
        super.commitEdit(newValue);
        // Optionally update your data model here
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText(getItem());
        setGraphic(null);
        setContentDisplay(ContentDisplay.TEXT_ONLY);
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            setText(item);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }
    }
}

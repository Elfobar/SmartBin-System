package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CardController implements MQTTDataObserver{
    @FXML
    private Label bin1_humid;

    @FXML
    private Label bin1_full;

    @FXML
    private void initialize() {
        bin1_humid.setText("55");
        bin1_full.setText("11");
    }

    @FXML
    public void updateHumid(String newValue) {
        bin1_humid.setText(newValue);
    }

    @FXML
    public void updateFull(String newValue) {
        bin1_full.setText(newValue);
    }

    public Label getBin1_full() {
        return bin1_full;
    }

    public Label getBin1_humid() {
        return bin1_humid;
    }

    @Override
    public void onHumidityUpdate(float value) {
        updateHumid(String.valueOf((int)value));
    }

    @Override
    public void onFullnessUpdate(float value) {
        updateFull(String.valueOf((int)value));
    }

}

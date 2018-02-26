package fr.rtgrenoble.velascof.simtelui.controller.param;

import fr.rtgrenoble.velascof.simtelui.Main;
import fr.rtgrenoble.velascof.simtelui.model.ModulationListItem;
import fr.rtgrenoble.velascof.simtelui.view.ModulationListCellView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ResourceBundle;

public class CodageCanalController implements IParamController, Initializable {

    @FXML
    private ToggleGroup group;

    @FXML
    private RadioButton askButton;

    @FXML
    private VBox askPane;

    @FXML
    private TextField askOrdreField;

    @FXML
    private ListView<ModulationListItem> askListView;

    @FXML
    private TextField askFpField;

    @FXML
    private TextField askPhaseField;

    @FXML
    private RadioButton fskButton;

    @FXML
    private VBox fskPane;

    @FXML
    private TextField fskOrdreField;

    @FXML
    private TextField fskTensionField;

    @FXML
    private ListView<ModulationListItem> fskListView;

    @FXML
    private TextField fskPhaseField;

    @FXML
    private RadioButton pskButton;

    @FXML
    private VBox pskPane;

    @FXML
    private TextField pskOrdreField;

    @FXML
    private TextField pskTensionField;

    @FXML
    private TextField pskFpField;

    @FXML
    private ListView<ModulationListItem> pskListView;

    @FXML
    private TextField fEchField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        askPane.disableProperty().bind(askButton.selectedProperty().not());
        fskPane.disableProperty().bind(fskButton.selectedProperty().not());
        pskPane.disableProperty().bind(pskButton.selectedProperty().not());
        askOrdreField.textProperty().addListener(new ModulationOrdreFieldListener(askOrdreField, askListView, "V"));
        askListView.setCellFactory(lv -> new ModulationListCellView());
        fskOrdreField.textProperty().addListener(new ModulationOrdreFieldListener(fskOrdreField, fskListView, "F"));
        fskListView.setCellFactory(lv -> new ModulationListCellView());
        pskOrdreField.textProperty().addListener(new ModulationOrdreFieldListener(pskOrdreField, pskListView, "P"));
        pskListView.setCellFactory(lv -> new ModulationListCellView());
        askButton.setOnAction(event -> updateAsk());
        fskButton.setOnAction(event -> updateFsk());
        pskButton.setOnAction(event -> updatePsk());
    }

    @Override
    public boolean validate() {
        if (!Main.root.getController().modulerCheckBox.isSelected()) return true;
        if (!isDouble(fEchField)) return false;
        if (group.getSelectedToggle() == askButton) {
            return askListView.getItems().stream().allMatch(ModulationListItem::isValid) && isDouble(askFpField) && isDouble(askPhaseField);
        } else if (group.getSelectedToggle() == fskButton) {
            return isDouble(fskTensionField) && fskListView.getItems().stream().allMatch(ModulationListItem::isValid) && isDouble(fskPhaseField);
        } else if (group.getSelectedToggle() == pskButton) {
            return isDouble(pskTensionField) && isDouble(pskFpField) && pskListView.getItems().stream().allMatch(ModulationListItem::isValid);
        }
        return false;
    }

    @Override
    public void toJson(JSONObject json) {
        if (!Main.root.getController().modulerCheckBox.isSelected()) return;
        JSONObject modulation = new JSONObject();
        if (group.getSelectedToggle() == askButton) {
            modulation.put("type", "ask");
            modulation.put("v", askListView.getItems().stream().mapToDouble(ModulationListItem::getValue).toArray());
            modulation.put("fp", toDouble(askFpField));
            modulation.put("p", toDouble(askPhaseField) * Math.PI);
        } else if (group.getSelectedToggle() == fskButton) {
            modulation.put("type", "fsk");
            modulation.put("v", toDouble(fskTensionField));
            modulation.put("f", fskListView.getItems().stream().mapToDouble(ModulationListItem::getValue).toArray());
            modulation.put("p", toDouble(fskPhaseField) * Math.PI);
        } else if (group.getSelectedToggle() == pskButton) {
            modulation.put("type", "psk");
            modulation.put("v", toDouble(pskTensionField));
            modulation.put("fp", toDouble(pskFpField));
            modulation.put("p", pskListView.getItems().stream().mapToDouble(item -> item.getValue() * Math.PI).toArray());
        }
        modulation.put("fech", toDouble(fEchField));
        json.put("modulation", modulation);
    }

    @Override
    public void fromJson(JSONObject json) throws JSONException {
        if (!json.has("modulation")) {
            Main.root.getController().modulerCheckBox.setSelected(false);
            return;
        }
        JSONObject modulation = json.getJSONObject("modulation");
        switch (modulation.getString("type")) {
            case "ask":
                group.selectToggle(askButton);
                createListView(modulation.getJSONArray("v"), askOrdreField, askListView, "V");
                askFpField.setText(String.valueOf(modulation.getDouble("fp")));
                askPhaseField.setText(String.valueOf(modulation.getDouble("p")));
                break;
            case "fsk":
                group.selectToggle(fskButton);
                fskTensionField.setText(String.valueOf(modulation.getDouble("v")));
                createListView(modulation.getJSONArray("f"), fskOrdreField, fskListView, "F");
                fskPhaseField.setText(String.valueOf(modulation.getDouble("p")));
                break;
            case "psk":
                group.selectToggle(pskButton);
                pskTensionField.setText(String.valueOf(modulation.getDouble("v")));
                pskFpField.setText(String.valueOf(modulation.getDouble("fp")));
                createListView(modulation.getJSONArray("p"), pskOrdreField, pskListView, "P");
                break;
        }
        fEchField.setText(String.valueOf(modulation.getDouble("fech")));
    }

    private void updateAsk() {
        askListView.setPrefHeight(122);
        fskListView.setPrefHeight(30);
        pskListView.setPrefHeight(30);
    }

    private void updateFsk() {
        askListView.setPrefHeight(30);
        fskListView.setPrefHeight(122);
        pskListView.setPrefHeight(30);
    }

    private void updatePsk() {
        askListView.setPrefHeight(30);
        fskListView.setPrefHeight(30);
        pskListView.setPrefHeight(122);
    }

    private void createListView(JSONArray array, TextField ordreField, ListView<ModulationListItem> listView, String prefix) {
        ordreField.setText(String.valueOf(array.length()));
        listView.getItems().clear();
        for (int i = 0; i < array.length(); i++) {
            listView.getItems().add(new ModulationListItem(prefix, array.length(), i, array.getDouble(i)));
        }
    }

    private class ModulationOrdreFieldListener implements ChangeListener<String> {


        private final ListView<ModulationListItem> listView;
        private final TextField ordreField;
        private final String prefix;

        public ModulationOrdreFieldListener(TextField ordreField, ListView<ModulationListItem> listView, String prefix) {
            this.ordreField = ordreField;
            this.listView = listView;
            this.prefix = prefix;
        }

        @Override
        public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
            if (isInteger(ordreField)) {
                int ordre = Integer.parseInt(ordreField.getText());
                if (ordre != 1 && Math.log(ordre) / Math.log(2) == (int) (Math.log(ordre) / Math.log(2))) {
                    listView.getItems().clear();
                    for (int i = 0; i < ordre; i++) {
                        listView.getItems().add(new ModulationListItem(prefix, ordre, i));
                    }
                }
            }
        }
    }

}

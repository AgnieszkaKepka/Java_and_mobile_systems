module Javafx {

    requires javafx.fxml;
    requires javafx.controls;
    requires commons.csv;

    opens sample;
    opens sample.Controllers;
}
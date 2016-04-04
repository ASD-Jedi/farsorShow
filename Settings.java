package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;

public class Settings {
    private int height;
    private int width;
    private int screenH;
    private int screenW;
    private Controller mainLogic;
    private Pane local;
    private Rectangle backgroundLocal;
    private Button setter;
    private Label resLabel;
    private ComboBox<String> resolutionBox;

    public Settings() {
        local = new Pane();
        mainLogic = new Controller();
        GraphicsDevice getRes =
            GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        screenH = getRes.getDisplayMode().getHeight();
        screenW = getRes.getDisplayMode().getWidth();
    }

    private void backgroundInit() {
        backgroundLocal = new Rectangle();
        backgroundLocal.setHeight(400);
        backgroundLocal.setWidth(200);
        backgroundLocal.setX(0);
        backgroundLocal.setY(0);
        backgroundLocal.setFill(Paint.valueOf("#BFBFBF"));
    }

    private void resLabelInit() {
        resLabel = new Label();
        resLabel.setLayoutX(55);
        resLabel.setLayoutY(10);
        resLabel.setPrefSize(200, 20);
        resLabel.setText("Set resolution");
        resLabel.setTextFill(Paint.valueOf("Black"));
        resLabel.setTextAlignment(TextAlignment.CENTER);
    }

    private void setterInit() {
        setter = new Button();
        setter.setText("Accept Settings");
        setter.setPrefSize(130, 20);
        setter.setTranslateX(30);
        setter.setTranslateY(350);
    }

    private void resolutionBoxInit() {
        resolutionBox = new ComboBox<>();
        resolutionBox.getItems().addAll("800x600", "1024x768", "1280x720", "1366x768");
        resolutionBox.setPrefSize(150, 30);
        resolutionBox.setTranslateX(30);
        resolutionBox.setTranslateY(40);
    }

    private void interfaceInit() {
        backgroundInit();
        resLabelInit();
        setterInit();
        resolutionBoxInit();
    }

    public Scene settingsScene(Stage edit) {
        interfaceInit();
        setter.setOnAction(event -> {
            if (resolutionBox.getValue() != null) {
                switch (resolutionBox.getValue()) {
                    case "800x600":
                        width = 800;
                        height = 600;
                        break;
                    case "1024x768":
                        width = 1024;
                        height = 768;
                        break;
                    case "1280x720":
                        width = 1280;
                        height = 720;
                        break;
                    case "1366x768":
                        width = 1366;
                        height = 768;
                        break;
                    default:
                        System.out.println("Doesn't work");
                        break;
                }
            } else {
                width = 800;
                height = 600;
            }
            mainLogic.sizeSet(height, width);
            edit.setX(screenH / 2 - 800 / 2);
            edit.setY(screenW / 2 - 600 / 2);
            edit.setWidth(width);
            edit.setHeight(height);
            mainLogic.sceneController(edit);
        });

        local.getChildren().addAll(backgroundLocal, resLabel, setter, resolutionBox);
        Scene chose = new Scene(local);
        return chose;
    }

}

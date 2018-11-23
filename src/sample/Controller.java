package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private GridPane mainContainer;

    final int col = 10;
    final int row = 10;

    private int shipLenght = 0;
    private int i2;
    private int j2;
    private boolean rotation=false;
    private boolean[][] position = new boolean[10][10];


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        mainContainer.setStyle("-fx-background-color: darkslategrey");
        mainContainer.setVgap(3);
        mainContainer.setHgap(3);


        Button[][] buttons = new Button[10][10];
        Button[][] enemyButtons = new Button[10][10];



        EventHandler<MouseEvent> mouseEnteredHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                    Object sourceObjcet = event.getSource();
                    Button sourceButton = (Button) sourceObjcet;
                    MouseButton button = event.getButton();

                    String buttonID = sourceButton.getId();

                    i2 = (buttonID.charAt(7)-48);
                    j2 = (buttonID.charAt(8)-48);

                    System.out.println(buttonID+"["+(i2)+"]  ["+(j2)+"]");
                    if(sourceObjcet instanceof Button) {
                        try {


                            if (shipLenght == 0 && !rotation && (i2 + 3) < row) {
                                sourceButton.setStyle("-fx-background-color: greenyellow");
                                buttons[i2 + 1][j2].setStyle(("-fx-background-color: greenyellow"));
                                buttons[i2 + 2][j2].setStyle(("-fx-background-color: greenyellow"));
                                buttons[i2 + 3][j2].setStyle(("-fx-background-color: greenyellow"));

                            }
                            if(button == MouseButton.SECONDARY) {
                                rotation = !rotation;
                            }
                            if(button == MouseButton.PRIMARY && shipLenght == 0 && position[i2][j2] == false &&
                                    position[i2+1][j2] == false && position[i2+2][j2] == false && position[i2+3][j2] == false) {
                                System.out.println("Jebana ");
                            }

                        } catch (IndexOutOfBoundsException e) {
                            e.getStackTrace();
                        }
                    }
            }
        };


        EventHandler<MouseEvent> mouseExitedHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Object sourceObjcet = event.getSource();
                Button sourceButton = (Button) sourceObjcet;


                if(shipLenght == 0) {
                    sourceButton.setStyle("-fx-background-color: aqua");
                    buttons[i2+1][j2].setStyle(("-fx-background-color: aqua"));
                    buttons[i2+2][j2].setStyle(("-fx-background-color: aqua"));
                    buttons[i2+3][j2].setStyle(("-fx-background-color: aqua"));
                }

            }
        };






        for(int i=0;i<row;i++)
            for (int j = 0; j < col; j++) {

                buttons[i][j] = new Button();
                buttons[i][j].setId("buttons"+i+j);

                buttons[i][j].setMinSize(24,24);
                buttons[i][j].setStyle("-fx-background-color: aqua");
                buttons[i][j].addEventHandler(MouseEvent.MOUSE_ENTERED , mouseEnteredHandler);
                buttons[i][j].addEventHandler(MouseEvent.MOUSE_EXITED , mouseExitedHandler);
                buttons[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEnteredHandler);

                mainContainer.add(buttons[i][j],i,j+1);

            }

        for(int i=0;i<row;i++)
            for (int j = 0; j < col; j++) {

                enemyButtons[i][j] = new Button();

                enemyButtons[i][j].setMinSize(24,24);
                enemyButtons[i][j].setStyle("-fx-background-color: black");
                enemyButtons[i][j].setDisable(true);
                mainContainer.add(enemyButtons[i][j],i+15,j+1);

            }
        //mainContainer.setAlignment(Pos.BOTTOM_CENTER);
        TextArea textArea = new TextArea();
        mainContainer.add(textArea,0,15,31,40);




    }
}

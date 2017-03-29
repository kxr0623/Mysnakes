package SnakeGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller {
   @FXML
   private ResourceBundle resources;
   @FXML
   private ImageView bg_Imageview;

   @FXML
   private URL location;

   @FXML
   private Pane startPane;
   @FXML
   private Pane op_pane;
   @FXML
   private ImageView mainbg_Imageview;

   @FXML
   private Button start_btn;
   @FXML
   private Label score_lbl;

   @FXML
   private Button stop_btn;

   @FXML
   void invisible_startPane(ActionEvent event) {
         startPane.setVisible(false);
         bg_Imageview.setVisible(false);
         mainbg_Imageview.setImage(new Image("resources/bg3.png"));
         mainbg_Imageview.setVisible(true);
         op_pane.toFront();
         op_pane.setVisible(true);
   }
   @FXML
   void pauseGame(ActionEvent event) {
      String s;
      if("Stop".equals(stop_btn.getText())){
         stop_btn.setText("Continue");
         //todo: pause the game
      }
      else {
         stop_btn.setText("Stop");
         //todo:continue the game.
      }


   }
   @FXML
   void initialize() {
      assert mainbg_Imageview != null : "fx:id=\"mainbg_Imageview\" was not injected: check your FXML file 'sample.fxml'.";
      assert startPane != null : "fx:id=\"startPane\" was not injected: check your FXML file 'sample.fxml'.";
      assert bg_Imageview != null : "fx:id=\"bg_Imageview\" was not injected: check your FXML file 'sample.fxml'.";
      assert start_btn != null : "fx:id=\"start_btn\" was not injected: check your FXML file 'sample.fxml'.";
      assert op_pane != null : "fx:id=\"op_pane\" was not injected: check your FXML file 'sample.fxml'.";
      assert score_lbl != null : "fx:id=\"score_lbl\" was not injected: check your FXML file 'sample.fxml'.";
      assert stop_btn != null : "fx:id=\"stop_btn\" was not injected: check your FXML file 'sample.fxml'.";

   }
}

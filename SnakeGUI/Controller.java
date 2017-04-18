package SnakeGUI;
import SnakeLogic.Board;
import SnakeLogic.Position;
import SnakeLogic.History;
import SnakeLogic.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller {
   private static int SIZE=10;// the size of a box;

   @FXML
   private ResourceBundle resources;
   @FXML
   private URL location;
   @FXML
   private Pane rootPane;
   @FXML
   private ImageView bg_Imageview;
   @FXML
   private Pane startPane;

   @FXML
   private Pane op_pane;

   @FXML
   private Canvas canvas;
   @FXML
   private Canvas op_canvas;

   @FXML
   private Text prompt_txt;

   @FXML
   private Button start_btn;
   @FXML
   private Label score_lbl;
   @FXML
   private Button stop_btn;
   @FXML
   private Button save_btn1;
   @FXML
   private Pane save_pane;

   @FXML
   private TextArea userrname_txtarea;

   @FXML
   private Button save_okButton;

   @FXML
   private Button history_btn11;

   @FXML
   private Button restart_btn;

   private Position p=new Position(30,30);
   private Snake snake;
   private Board board;
   private GraphicsContext context;
   private Timeline timeline;
   private final double speed=100.0;//the initial speed of snake.
   private final int length = 2;//the initial length of snake.
   @FXML
   void invisible_startPane(ActionEvent event) {
      startPane.setVisible(false);
      GraphicsContext opGc=op_canvas.getGraphicsContext2D();
      context=canvas.getGraphicsContext2D();
      op_pane.setVisible(true);
      canvas.toFront();
      canvas.setFocusTraversable(true);
      opGc.drawImage(new Image("resources/bg3.png"),0,0);
      run();
   }
   // create a snake , food and board, move the snake
   private void run() {
      Position p=new Position(30,30);
      snake=new Snake(Color.GREEN,length,p);
      board=new Board(760,600,SIZE,snake);
      timeline = new Timeline();
      timeline.setCycleCount(Timeline.INDEFINITE);
      timeline.getKeyFrames().add(
              new KeyFrame(Duration.millis(speed),
                      new EventHandler<ActionEvent>() {
                         public void handle(ActionEvent event) {
                            paint(board,context);
                            board.updateState();
                         }
                      }));
      timeline.playFromStart();
   }
   @FXML
   void keyPressedEvent(KeyEvent event) {
      Direction direction=board.getSnake().getDirection();
      switch (event.getCode()) {
         case UP:
            if (direction != Direction.DOWN)
            board.getSnake().changeDirection(Direction.UP);
            break;
         case DOWN:
            if (direction != Direction.UP)
            board.getSnake().changeDirection(Direction.DOWN);
            break;
         case LEFT:
            if (direction != Direction.RIGHT)
            board.getSnake().changeDirection(Direction.LEFT);
            break;
         case RIGHT:
            if (direction != Direction.LEFT)
               board.getSnake().changeDirection(Direction.RIGHT);
            break;
         default:
            board.getSnake().changeDirection(Direction.RIGHT);
            break;
      }
   }
   @FXML
   void handleMouseEntered(MouseEvent event) {
      rootPane.requestFocus();
   }

   @FXML
   void pauseGame(ActionEvent event) {
      String s;
      if("Stop".equals(stop_btn.getText())){
         stop_btn.setText("Continue");
         if (timeline != null) {
            timeline.pause();
         }
         // pause the game
      }
      else {
         stop_btn.setText("Stop");
         if (timeline != null ) {
            timeline.play();
         }
         //continue the game.
      }
   }
   @FXML
   void restartGame(ActionEvent event) {
      if(timeline!=null){
         timeline.stop();
      }
      restart_btn.setDisable(true);
      stop_btn.setDisable(false);
      painfeedbake("Prompt:");
      run();
   }
   @FXML
   void saveScore(ActionEvent event) {
//todo
      save_btn1.setDisable(true);
      save_pane.setVisible(true);
      History h=new History(".");
      h.writetofile("us",score_lbl.getText(),"history.txt");
   }
   @FXML
   void save_newScore(ActionEvent event) {
      History h=new History(".");
      h.writetofile(userrname_txtarea.getText(),score_lbl.getText(),"history.txt");
   }

   @FXML
   void showHistoryScore(ActionEvent event) {
//todo
   }
   public void paint(Board board, GraphicsContext gc) {
      gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
      //paint the bakeground
      Image image=new Image("resources/skyblue.jpg");
      gc.drawImage(image,0,0);
      // paint the Food
      gc.setFill(board.getFood().getColor());
      paintPoint(board.getFood().getPosition(), gc);
      // paint the snake
      snake = board.getSnake();
      gc.setFill(snake.getColor());
      snake.getSnakebody().forEach(p -> paintPoint(p, gc));
      // restart the game
      if (!snake.getAlive()) {
         painfeedbake("OH~your snake is dead!!!.");
         timeline.stop();
         stop_btn.setDisable(true);
         restart_btn.setDisable(false);
         save_btn1.setDisable(false);
         history_btn11.setDisable(false);
      }
      // The score
      score_lbl.setText(""+ (snake.getLength()-length));
   }
   //paint one point
   private  void paintPoint(Position p, GraphicsContext gc) {
      gc.fillOval(p.getRow()*SIZE, p.getCol()*SIZE, SIZE, SIZE);
   }
   //返回重玩提示语
   public void painfeedbake(String s){
      prompt_txt.setText(s);
   }

   @FXML
   void initialize() {
      assert rootPane != null : "fx:id=\"rootPane\" was not injected: check your FXML file 'sample.fxml'.";
      assert canvas != null : "fx:id=\"canvas\" was not injected: check your FXML file 'sample.fxml'.";
      assert startPane != null : "fx:id=\"startPane\" was not injected: check your FXML file 'sample.fxml'.";
      assert bg_Imageview != null : "fx:id=\"bg_Imageview\" was not injected: check your FXML file 'sample.fxml'.";
      assert start_btn != null : "fx:id=\"start_btn\" was not injected: check your FXML file 'sample.fxml'.";
      assert op_pane != null : "fx:id=\"op_pane\" was not injected: check your FXML file 'sample.fxml'.";
      assert op_canvas != null : "fx:id=\"op_canvas\" was not injected: check your FXML file 'sample.fxml'.";
      assert prompt_txt != null : "fx:id=\"prompt_txt\" was not injected: check your FXML file 'sample.fxml'.";
      assert score_lbl != null : "fx:id=\"score_lbl\" was not injected: check your FXML file 'sample.fxml'.";
      assert stop_btn != null : "fx:id=\"stop_btn\" was not injected: check your FXML file 'sample.fxml'.";
      assert restart_btn != null : "fx:id=\"restart_btn\" was not injected: check your FXML file 'sample.fxml'.";
   }
}

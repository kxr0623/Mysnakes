package SnakeGUI;
import SnakeLogic.Board;
import SnakeLogic.Position;
import SnakeLogic.History;
import SnakeLogic.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
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
/*
* this class is used to control the actions on the game interfaces.
* including the starting page, playing page, save page, history page.
* */
public class Controller {
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
   private Label save_lbl;
   @FXML
   private Button stop_btn;
   @FXML
   private Button save_btn1;
   @FXML
   private Button save_cancelBtn;
   @FXML
   private Pane save_pane;
   @FXML
   private TextField userrname_txtField;
   @FXML
   private Button save_okButton;
   @FXML
   private ListView<String> history_lv;
   @FXML
   private Button history_okBtn;
   @FXML
   private Button history_btn;
   @FXML
   private Pane history_pane;
   @FXML
   private Button restart_btn;
   private Position p=new Position(30,30);
   private Snake snake;
   private Board board;
   private GraphicsContext context;
   private Timeline timeline;
   private final double speed=90.0;//the initial speed of snake.
   private final int length = 2;//the initial length of snake.
   private final int SIZE=10;// the size of a box;
   private int top10=0;//the 10th score in the history.
   private History h=new History(".");
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
   // use keyboard to change direction of snake
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
   //pause or continue the game
   void pauseGame(ActionEvent event) {
      String s;
      if("Stop".equals(stop_btn.getText())){
         stop_btn.setText("Continue");
         history_btn.setDisable(false);
         restart_btn.setDisable(false);
         if (timeline != null) {
            timeline.pause();
         }
      }
      else {
         stop_btn.setText("Stop");
         restart_btn.setDisable(true);
         history_btn.setDisable(true);
         if (timeline != null ) {
            timeline.play();
         }
      }
   }
   @FXML
   // restart the snake game enable all buttons
   void restartGame(ActionEvent event) {
      if(timeline!=null){
         timeline.stop();
      }
      restart_btn.setDisable(true);
      save_btn1.setDisable(true);
      history_btn.setDisable(true);
      stop_btn.setDisable(false);
      stop_btn.setText("Stop");
      painfeedbake("Prompt:");
      run();
   }
   @FXML
      //open the save pane
   void saveScore(ActionEvent event) {
      String content[]=h.readfile("history.txt");
      int score=Integer.parseInt(score_lbl.getText());
      int score9=-1;
      if(content.length>=10){
         String[] s=content[9].split("\\s+");
         score9=Integer.parseInt(s[0]);
      }
      if(content.length<10||  score9<score){
         save_btn1.setDisable(true);
         save_pane.toFront();
         save_pane.setVisible(true);
         restart_btn.setDisable(true);
         history_btn.setDisable(true);
         userrname_txtField.setText("");
      }
      else painfeedbake("Your score is too low to recored in History!");
   }
   // save the new score to txt file
   @FXML
   void save_newScore(ActionEvent event) {

      if(!userrname_txtField.getText().equals(null) && !userrname_txtField.getText().equals("")) {
         h.writetofile(userrname_txtField.getText(), score_lbl.getText(), "history.txt");
         save_pane.toBack();
         save_pane.setVisible(false);
         history_btn.setDisable(false);
         restart_btn.setDisable(false);
         painfeedbake("Save susessfully!");
      }
      else save_lbl.setText("username is null,Input valid username:");
   }
   // cancel the save pane and enable or disable other buttons .
   @FXML
   void save_cancel(ActionEvent event) {
      save_pane.toBack();
      save_pane.setVisible(false);
      save_btn1.setDisable(false);
      history_btn.setDisable(false);
      restart_btn.setDisable(false);
   }
   @FXML
   //show the top 10 scores in history
   void showHistoryScore(ActionEvent event) {
      String content[]=h.readfile("history.txt");
      if(content.equals(null)||content.length==0){
         painfeedbake("There is no history");
      }
      else {
         String show[] ;
         if(content.length<10) {
            show= new String[content.length];
            show=content;
         }
         else {
            show=new String[10];
            for(int i=0;i<10;i++){
               show[i]=content[i];
            }
         }
         ObservableList<String> items = FXCollections.observableArrayList(
                 show);
         history_lv.setItems(items);
         restart_btn.setDisable(true);
         stop_btn.setDisable(true);
         history_pane.toFront();
         history_pane.setVisible(true);
      }
   }
   //close the history pane
   @FXML
   void closeHistory(ActionEvent event) {
      restart_btn.setDisable(false);
      stop_btn.setDisable(false);
      history_pane.toBack();
      history_pane.setVisible(false);
   }
   // pain the bakeground the food, snake, score on the interface, and allow restart the game
   private void paint(Board board, GraphicsContext gc) {
      gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
      Image image=new Image("resources/skyblue.jpg");
      gc.drawImage(image,0,0);
      gc.setFill(board.getFood().getColor());
      paintPoint(board.getFood().getPosition(), gc);
      snake = board.getSnake();
      gc.setFill(snake.getColor());
      snake.getSnakebody().forEach(p -> paintPoint(p, gc));
      if (!snake.getAlive()) {
         painfeedbake("OH~your snake is dead!!!.");
         timeline.stop();
         stop_btn.setDisable(true);
         restart_btn.setDisable(false);
         save_btn1.setDisable(false);
         history_btn.setDisable(false);
      }
      score_lbl.setText(""+ (snake.getLength()-length));
   }
   //paint one point on the interface
   private  void paintPoint(Position p, GraphicsContext gc) {
      gc.fillOval(p.getRow()*SIZE, p.getCol()*SIZE, SIZE, SIZE);
   }
   //give a feedback to user
   private void painfeedbake(String s){
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

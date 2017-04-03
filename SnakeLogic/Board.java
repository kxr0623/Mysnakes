package SnakeLogic;
import javafx.scene.paint.Color;

/**
 * Created by 52704 on 2017/3/26.
 */
public class Board {
   int width, heigh,rows,cols;
   int boxSize;
   Food food;
   Snake snake;

   public Board(int wigh, int heigh, int boxSize,Snake snake) {
      this.width = wigh;
      this.heigh = heigh;
      this.boxSize = boxSize;
      rows=(int)wigh/boxSize;
      cols=(int) heigh/boxSize;
      this.food=new Food(rows,cols);
      if(checkBound(snake.getHead()))
         this.snake=snake;
      else {
         throw new Error("the snake should in the board,change the position of head");
      }
   }

   //allow the snake always in the grid
   public boolean checkBound(Position position){
      int x = position.getRow();
      int y = position.getCol();
      if (x >= width /boxSize) return false;
      if (y >=heigh/boxSize) return false;
      if (x < 0) return false;
      if (y < 0) return false;
      return true;
   }
   //make the snake move or forward the food.
   public boolean updateState(){

      if (food.getPosition().equals(snake.getHead())) {
         snake.move_grow();
         setfoodPosition(snake);
      } else {
         snake.move();
      }
      if(!checkBound(snake.getHead())){
         snake.setAlive(false);
         return false;//outside the board
      }
      return true;
   }
   //set food position in the board and not on the body of snake
   public void setfoodPosition(Snake snake){
      do{
         food.setPosition();
      }
      while (snake.getSnakebody().contains(food.getPosition()));
   }
   public Position getfoodPosition(){
      return food.getPosition();
   }

   public int getWidth() {
      return width;
   }

   public int getHeigh() {
      return heigh;
   }
   public void setBoxSize(int size){
      boxSize=size;
   }
   public int getBoxSize() {
      return boxSize;
   }

   public Snake getSnake() {
      return snake;
   }

   public Food getFood() {
      return food;
   }

   public static void main(String[] args){
      Position start=new Position(25,25);
      Snake s=new Snake(Color.GREEN,1,start);
      Board board_test=new Board(900,600,10,s);
      boolean testing = false;
      assert(testing = true);
      if(testing){
         board_test.test();
      }
      else {
         System.err.println(" Use java -ea Board for testing.");
         System.exit(1);
      }
   }
   void test(){
      //todo:test the Board.
      // this.updateState();


   }
}

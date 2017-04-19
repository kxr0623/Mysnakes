package SnakeLogic;
import javafx.scene.paint.Color;

/**
 * this class handle the attributes of a board. the board include a snake and a food
 * the class check the snake and food are always in the board
 * the class update the position of food and snake
 */
public class Board {
   private int width, heigh,rows,cols;
   private int boxSize;
   private Food food;
   private Snake snake;

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
   private boolean checkBound(Position position){
      int x = position.getRow();
      int y = position.getCol();
      if (x >= width /boxSize) return false;
      if (y >=heigh/boxSize) return false;
      if (x < 0) return false;
      if (y < 0) return false;
      return true;
   }
   //make the snake move or eat the food. ensure the snake is in the board
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
   private void setfoodPosition(Snake snake){
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
   public Snake getSnake() {
      return snake;
   }
   public Food getFood() {
      return food;
   }

   public static void main(String[] args){
      Position start=new Position(89,25);
      Snake s=new Snake(Color.GREEN,1,start);
      Board board_test=new Board(900,600,10,s);
      boolean testing = false;
      assert(testing = true);
      if(testing){
         board_test.test();
         System.out.println("Tests pass!");
      }
      else {
         System.err.println(" Use java -ea Board for testing.");
         System.exit(1);
      }
   }
   //test the methods of Board.
   private void test(){
      Position h=new Position(50,25);
      Snake s=new Snake(Color.GREEN,1,h);
      Board b=new Board(900,600,10,s);

      Position p=new Position(30,300);
      assert (!this.checkBound(p)) ;
      assert (this.checkBound(this.getFood().getPosition()));

      assert(!this.updateState()) ;
      assert(b.updateState());
   }
}

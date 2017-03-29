package SnakeLogic;

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
      if(checkBound(snake.head))
         this.snake=snake;
      else {
         throw new Error("the snake should in the board,change the position of head");
      }
   }

   //allow the snake always in the grid
   boolean checkBound(Position position){
      int x = position.getRow();
      int y = position.getCol();
      if (x >= width /boxSize) return false;
      if (y >=heigh/boxSize) return false;
      if (x < 0) return false;
      if (y < 0) return false;
      return true;
   }
   void updateState(){
      if (food.getPosition().equals(snake.getHead())) {
         snake.extend();
         setfoodPosition(snake);
      } else {
         snake.move();
      }
   }
   //set food position in the board and not on the body of snake
   void setfoodPosition(Snake snake){
      do{
         food.setPosition();
      }
      while (snake.getSnakebody().contains(food.getPosition()));
   }
   Position getfoodPosition(){
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

}

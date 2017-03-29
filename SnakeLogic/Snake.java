package SnakeLogic;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by 52704 on 2017/3/26.
 */
public class Snake {
   Direction direction;
   ArrayList<Position> snakebody ;
   Color snakeColor;
   int length;
   Boolean dead ;
   Position head;
   Board board;

   public Snake(Color snakeColor, int length,Position head) {
      this.direction = Direction.RIGHT;
      this.snakeColor = snakeColor;
      this.length = length;
      this.dead = false;
      this.head = head;
      snakebody=new ArrayList<Position>();
      snakebody.add(head);
   }

   public Direction getDirection() {
      return direction;
   }

   public int getLength() {
      return length;
   }

   public Boolean getDead() {
      return dead;
   }

   public Position getHead() {
      return head;
   }

   //update the place of snake.
   // add the new place of snake and remove the point of tail.
   void changePlace(){

   }
   //move according to the direction
   void move(){

   }
   //eat a food and make the snake longer
   void eat(){

   }
   //make the snake longer
   void extend(){
      if(dead==false){
         length++;
         //add the head
      }
   }
   void checkdead(Position position){

   }

   public ArrayList<Position> getSnakebody() {
      return snakebody;
   }
}

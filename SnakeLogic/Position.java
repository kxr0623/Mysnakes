package SnakeLogic;

/**
 * Created by 52704 on 2017/3/26.
 * @param x The X coordinate.
 * @param y The Y coordinate.
 */
public class Position {
   private int row;
   private int  col;

   public Position(int row,int col) {
      this.row = row;
      this.col =col;
   }

   public int getRow() {
      return row;
   }

   public int getCol() {
      return col;
   }
   public Position translate(int dx, int dy){
      return new Position(row + dx, col + dy);
   }


}

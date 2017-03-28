package SnakeLogic;

/**
 * Created by 52704 on 2017/3/26.
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
   // check the food point is occupied by snake's body or not.
   public boolean occupied(Position snakebody){
      return (row==snakebody.row) && (col==snakebody.col);
   }





   private void tests(){
      Position testP =new Position(1,1);
      Position testP2=new Position(1,3);
      assert (occupied(testP)) ;
      assert (!occupied(testP2));
   }

   public static void main(String[] args){
      Position from=new Position(1,1);
      from.tests();
      System.out.println("tests pass!");
   }

}

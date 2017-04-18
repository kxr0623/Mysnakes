package SnakeLogic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This class is used to read history scores from a txt file and save the new score to a file.
 */
public class History {
   private String path;

   public History(String path) {
      this.path = path;
   }
   //read file and put the content to record[][]
  public String[][] readfile(String filename) {
      List<String> words =new ArrayList<>();
      try {
         File file = new File(this.path+"/"+ filename);
            System.out.println("readfile:" + file.getCanonicalPath());
            Scanner in = new Scanner(file);
            while (in.hasNextLine()) {
               String line = in.nextLine();
               if (line.length() == 0) continue;
               words.add(line);
            }
            in.close();
      } catch (Exception e) { throw new Error(e); }
      String[] lines=new String[words.size()];
      lines= words.toArray(lines);
      String[][] records=new String[words.size()][3];
      for(int i=0;i<lines.length;i++)
      {
         records[i] = lines[i].split("\\s+");
      }
      records=sortScores(records);
      for(int i=0;i<lines.length;i++)
      {
         System.out.println(records[i][0] + " "+records[i][2]);
      }
      return records;
   }
  private String [][] sortScores(String [][] r){
      String [][] temp=new String[r.length][3];
      if(r.length>=1) {
         for (int i = 0; i < r.length - 1; i++) {
            for (int j = 0; j < r.length - 1 - i; j++ ) {
               int score1 = Integer.parseInt(r[j][0]);
               int score2 = Integer.parseInt(r[j + 1][0]);
               System.out.print(" sco1:"+score1+" sc2:"+score2+"\n");
               if (score1 < score2) {
                  copyString(temp[j],r[j + 1]);
                  copyString(r[j+1] , r[j]);
                  copyString(r[j] , temp[j]);
               }
            }
         }
      }
      return r;
   }
  private void copyString(String[] ts, String[] s){
      for (int i=0;i<s.length;i++){
         ts[i]=s[i];
      }
   }
   //put the new score to a .txt file
   public boolean writetofile(String username, String score,String filename){
      String[][] content=readfile(filename);
      try {
         File file= new File(path+"/"+ filename);
         if (file.exists()){
            file.delete();
         }
         FileWriter writto = new FileWriter(file, true);
         writto.write("\r\n");
         for(int j=0;j<content.length;j++){
            for (int i = 0; i < content[j].length; i++)
               writto.write(content[j][i]+"  ");
            writto.write("\r\n");
         }
         String timeStamp = new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss").format(Calendar.getInstance().getTime());
         String newS=score+"  "+username+"  "+timeStamp;
         writto.write(newS);
         writto.close();
      }catch (IOException e) {
         e.printStackTrace();
         return false;
      }
      return true;
   }
   public static void main(String[] args) {
      History history = new History(".");
      boolean testing = false;
      assert(testing = true);
      if (testing ) {
         history.test(args, history);
         System.out.println("TESTS PASS!");
      }
      else {
         System.err.println("please input \"java -ea history data.txt\"for testing.");
         System.exit(1);
      }
   }
   //args[0]="data.txt"
   private void test(String[] args ,History history){

      assert (history.writetofile("kkk","7","test_History.txt"));
      String test = " kkk 23";
      assert (!history.readfile("test_History.txt").equals(test));
      //assert (history.writetofile(t));


   }

}

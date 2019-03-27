public class GameOfLife {

   // Konstanten: Länge und Breite des Spielfeldes
   private static final int ROW = 12;
   private static final int COL = 12;

   // Definition des Spielfeldes
   private boolean[][] field;

   /**
    * Initialisieren des Spielfeldes
    */
   private void initField(int row, int col) {
      this.field = new boolean[row][col];

      for (int x = 0; x < row; x++) {
         for (int y = 0; y < col; y++) {
            field[x][y] = Math.random() > 0.4;
         }
      }
   }

   /**
    * Darstellen des Spielfeldes
    */
   private void showField() {
      for (int x = 0; x < ROW; x++) {
         for (int y = 0; y < COL; y++) {
            System.out.print((field[x][y]) ? "1 " : "0 ");
         }
         System.out.println();
      }
   }

   /**
    * Zählen der Nachbarfelder
    */
   private int countNeighbors(int row, int col) {
      int count = 0;

      for (int x = row - 1; x <= row + 1; x++) {

         // Die Position von x muss innerhalb des Felds sein
         if (x >= 0 && x < ROW) {
            for (int y = col - 1; y <= col + 1; y++) {

               // Die Position von y muss innerhalb des Felds sein
               if (y >= 0 && y < COL) {

                  // Unsere eigene Position (row, col) dürfen wir nicht mitzählen
                  if ( !(x == row && y == col) ) {
                     if (field[x][y]) {
                        count++;
                     }
                  }
               }
            }
         }
      }

      return count;
   }

   /**
    * Meine Regeln
    */
   private void rules() {
      boolean[][] newGeneration = new boolean[ROW][COL];

      for (int x = 0; x < ROW; x++) {
         for (int y = 0; y < COL; y++) {
            int neightbors = countNeighbors(x,y);
            if (neightbors < 2 || neightbors > 3) {
               newGeneration[x][y] = false;
            } else if (!field[x][y] && neightbors == 3) {
               newGeneration[x][y] = true;
            } else {
               newGeneration[x][y] = field[x][y];
            }
         }
      }

      field = newGeneration;
   }

   public static void main(String[] args) {
      GameOfLife game = new GameOfLife();

      game.initField(ROW, COL);
      game.showField();

      int count = game.countNeighbors(0,0);

      for (int i = 0; i <= 10; i++) {
         System.out.println();
         game.rules();
         game.showField();
      }
   }
}

public class QueenBoard {
  private int[][] board;

  public QueenBoard(int size) {
    board = new int[size][size];
  }

  private boolean addQueen(int r, int c) {
    if (board[r][c] == -1) return false; //queen already there
    if (board[r][c] > 0) return false; // if queen cannot be placed there
    board[r][c] = -1;
    for (int i = 1; r - i >= 0 && c + i < board.length; i++) {
      board[r-i][c+i] ++;
    }
    for (int i = 1; c + i < board.length; i++) {
      board[r][c+i] ++;
    }
    for (int i = 1; r + i < board.length && c + i < board.length; i ++) {
      board[r+i][c+i] ++;
    }
    return true;
  }

  private boolean removeQueen(int r, int c) {
    if (board[r][c] != -1) return false;
    board[r][c] = 0;
    for (int i = 1; r - i >= 0 && c + i < board.length; i++) {
      board[r-i][c+i] --;
    }
    for (int i = 1; c + i < board.length; i++) {
      board[r][c+i] --;
    }
    for (int i = 1; r + i < board.length && c + i < board.length; i ++) {
      board[r+i][c+i] --;
    }
    return true;
  }

  private void removeLastQueen() {
    boolean removed = false;
    for (int r = board.length-1; r >-1 && !removed; r++) {
      for (int c = board.length-1; c > -1 && !removed; c++) {
        if (board[r][c] == -1) removeQueen(r,c);
        removed = true;
      }
    }
  }

  /**
  *@return The output string formatted as follows:
  *All numbers that represent queens are replaced with 'Q'
  *all others are displayed as underscores '_'
  *There are spaces between each symbol:
  *"""_ _ Q _
  *Q _ _ _
  *_ _ _ Q
  *_ Q _ _"""
  *(pythonic string notation for clarity,
  *excludes the character up to the *)
  */
  public String toString(){
    String finalBoard = "";
    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board.length; col++) {
        if (board[row][col] == -1) finalBoard += "Q ";
        else finalBoard += "_ ";
      }
      finalBoard+="\n";
    }
    return finalBoard;
  }


  /**
  *@return false when the board is not solveable and leaves the board filled with zeros;
  *        true when the board is solveable, and leaves the board in a solved state
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public boolean solve(){
    if (board.length==0 || board.length==1) return true;
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board.length; c++) {
        if (board[r][c] != 0) {
          throw new IllegalStateException("Board must be empty in order to use solve()");
        }
      }
    }
    return solveHelp(0);
  }

  private boolean solveHelp(int col) {
    if (col == board.length) return true;
    for (int row = 0; row < board.length; row ++) {
      if (addQueen(row, col)) {
        if (solveHelp(col+1)) {
          return true;
        }
        removeQueen(row, col);
      }
    }
    return false;
  }

  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions(){
    for (int r = 0; r < board.length; r ++) {
      for (int c = 0; c < board.length; c ++) {
        if (board[r][c]!=0) {
          throw new IllegalStateException("Board must be empty in order to use countSolutions()");
        }
      }
    }
    return countHelp(0);
  }

  private int countHelp(int col) {
    if (col == board.length) return 1;
    int currentCount = 0;
    for (int row = 0; row < board.length; row++) {
      if (addQueen(row,col)) {
        currentCount+=countHelp(col+1);
      }
      removeQueen(row, col);
    }
    return currentCount;
  }

  // public static void main(String[] args) {
  //   QueenBoard test = new QueenBoard(21);
  //   System.out.println(test.solve());
  //   System.out.println(test);
  //   QueenBoard test2 = new QueenBoard(14);
  //   System.out.println(test2.countSolutions());
  // }
}

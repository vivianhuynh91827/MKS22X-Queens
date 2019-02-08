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
    return solveHelp(0,0, false);
  }

  private boolean solveHelp(int col, int row, boolean hasQueen) {
    if (col == board.length-1 && hasQueen) return true; //if the board has been completed
    if (col == board.length-1 && row == board.length && !hasQueen) return false;
    if (board[row][col] != 0) {
      if (row < board.length) return solveHelp(col, row+1);
      else return solveHelp(col+1, 0);
    }
  }

  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions(){

  }
}


class IsValidSudoku {
  public static void main(String[] args) {

    char[][] board = {
        { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
        { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
        { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
        { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
        { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
        { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
        { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
        { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
        { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
    };

    char[][] board2 = {
        { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
        { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
        { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
        { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
        { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
        { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
        { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
        { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
        { '.', '.', '.', '.', '.', '.', '.', '.', '.' }
    };

    IsValidSudoku.isValidSudoku(board);
  }

  public static boolean isValidSudoku(char[][] board) {
    boolean[] isValid = new boolean[1];
    IsValidSudoku.solve(board, isValid);
    return isValid[0];
  }

  static void solve(char[][] board, boolean[] isValid) {
    if (isValid[0])
      return;
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] == '.') {
          int pick = 1;
          while (pick <= 10) {
            if (pick == 10) {
              return;
            }
            if (!IsValidSudoku.checkRow(board, pick, i)
                || !IsValidSudoku.checkCol(board, pick, j)
                || !IsValidSudoku.checkBox(board, pick, i, j)) {
              pick++;
              if (pick == 10) board[i][j] = '.';
            } else {
              board[i][j] = Character.forDigit(pick, 10);
              solve(board, isValid);
            }
          }
        }
        if (i == board.length - 1 && j == board[i].length - 1) {
          IsValidSudoku.printBoard(board);
          isValid[0] = true;
          return;
        }
      }
    }
  }

  public static boolean checkRow(char[][] board, int pick, int index) {
    for (int k = 0; k < board[index].length; k++) {
      if (board[index][k] == Character.forDigit(pick, 10))
        return false;
    }
    return true;
  }

  public static boolean checkCol(char[][] board, int pick, int index) {
    for (int k = 0; k < board[index].length; k++) {
      if (board[k][index] == Character.forDigit(pick, 10))
        return false;
    }
    return true;
  }

  public static boolean checkBox(char[][] board, int pick, int i, int j) {
    int rowStart = i >= 6 ? 6 : (i < 6 && i >= 3 ? 3 : 0);
    int colStart = j >= 6 ? 6 : (j < 6 && j >= 3 ? 3 : 0);
    for (int k = rowStart; k < rowStart + 3; k++) {
      for (int l = colStart; l < colStart + 3; l++) {
        if (board[k][l] == Character.forDigit(pick, 10))
          return false;
      }
    }
    return true;
  }

  static void printBoard(char[][] board) {
    for (int i = 0; i < board.length; i++) {
      if (i == 3 || i == 6) {
        for (int k = 0; k < (board.length * 2) + 4; k++) {
          if (k == 6 || k == 14) {
            System.out.print("+");
          } else {
            System.out.print("-");

          }
        }
        System.out.println();
      }
      for (int j = 0; j < board[i].length; j++) {
        System.out.print(((j == 3 || j == 6) ? " " : "") + board[i][j] + " ");
        if (j == 2 || j == 5) {
          System.out.print("|");
        }
      }
      System.out.println();
    }
  }
}

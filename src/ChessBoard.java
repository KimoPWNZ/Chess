public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8];
    private boolean whiteKingMoved = false;
    private boolean blackKingMoved = false;
    private boolean whiteRook0Moved = false; // Ладья (A)
    private boolean whiteRook7Moved = false; // Ладья (H)
    private boolean blackRook0Moved = false; // Ладья (A)
    private boolean blackRook7Moved = false; // Ладья (H)
    String nowPlayer;

    public ChessBoard() {
        initializeBoard();
    }

    private void initializeBoard() {
        // Установка фигур на доске
        board[0][0] = new Rook("White");
        board[0][1] = new King("White");
        board[0][2] = new Bishop("White");
        board[0][3] = new Queen("White");
        board[0][4] = new King("White");
        board[0][5] = new Bishop("White");
        board[0][6] = new King("White");
        board[0][7] = new Rook("White");

        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn("White");
        }

        // Черные фигуры
        board[7][0] = new Rook("Black");
        board[7][1] = new King("Black");
        board[7][2] = new Bishop("Black");
        board[7][3] = new Queen("Black");
        board[7][4] = new King("Black");
        board[7][5] = new Bishop("Black");
        board[7][6] = new King("Black");
        board[7][7] = new Rook("Black");

        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn("Black");
        }
    }

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public void switchPlayer() {
        nowPlayer = nowPlayer.equals("Black") ? "Black" : "White";
    }

    public boolean moveToPosition(int fromLine, int fromColumn, int toLine, int toColumn) {
        ChessPiece piece = board[fromLine][fromColumn];
        if (piece == null || !piece.canMoveToPosition(this, fromLine, fromColumn, toLine, toColumn)) {
            return false;
        }

        // Рокировка обработки
        if (piece instanceof King) {
            if (fromLine == toLine && Math.abs(fromColumn - toColumn) == 2) {
                // Рокировка
                int line = 0;
                int column = 0;
                return castling0(line, column) || castling7(fromLine, fromColumn);
            }
            // Отмечаем, что король двигался
            if (piece.getColor().equals("white")) {
                whiteKingMoved = true;
            } else {
                blackKingMoved = true;
            }
        } else if (piece instanceof Rook) {
            // Отмечаем, что ладья двигалась
            if (piece.getColor().equals("white")) {
                if (fromColumn == 0) {
                    whiteRook0Moved = true;
                } else if (fromColumn == 7) {
                    whiteRook7Moved = true;
                }
            } else {
                if (fromColumn == 0) {
                    blackRook0Moved = true;
                } else if (fromColumn == 7) {
                    blackRook7Moved = true;
                }
            }
        }

        // Перемещение фигуры и очистка старой позиции
        board[toLine][toColumn] = piece;
        board[fromLine][fromColumn] = null;
        return true; // Успешное перемещение
    }

    // Метод для рокировки 0 столбец
    public boolean castling0(int line, int column) {
        // Проверяем, возможно ли рокировка
        if ((line == 0 && column == 4 && !whiteKingMoved && !whiteRook0Moved) ||
                (line == 7 && column == 4 && !blackKingMoved && !blackRook0Moved)) {
            // Проверьте, что между королем и ладьей нет фигуры
            if (board[line][1] == null && board[line][2] == null && board[line][3] == null) {
                // Перемещаем короля
                board[line][2] = board[line][4]; // Перемещаем короля на 2 клетки
                board[line][4] = null;

                // Перемещаем ладью
                board[line][3] = board[line][0]; // Перемещаем ладью на 3 клетку
                board[line][0] = null;

                // Отмечаем, что король и ладья были перемещены
                if (line == 0) {
                    whiteKingMoved = true;
                    whiteRook0Moved = true;
                } else {
                    blackKingMoved = true;
                    blackRook0Moved = true;
                }
                return true; // Рокировка успешна
            }
        }
        return false; // Рокировка невозможна
    }

    // Метод для рокировки 7 столбец
    public boolean castling7(int line, int column) {
        // Проверяем, возможно ли рокировка
        if ((line == 0 && column == 4 && !whiteKingMoved && !whiteRook7Moved) ||
                (line == 7 && column == 4 && !blackKingMoved && !blackRook7Moved)) {
            // Проверьте, что между королем и ладьей нет фигуры
            if (board[line][5] == null && board[line][6] == null) {
                // Перемещаем короля
                board[line][6] = board[line][4]; // Перемещаем короля на 2 клетки
                board[line][4] = null;

                // Перемещаем ладью
                board[line][5] = board[line][7]; // Перемещаем ладью на 5 клетку
                board[line][7] = null;

                // Отмечаем, что король и ладья были перемещены
                if (line == 0) {
                    whiteKingMoved = true;
                    whiteRook7Moved = true;
                } else {
                    blackKingMoved = true;
                    blackRook7Moved = true;
                }
                return true; // Рокировка успешна
            }
        }
        return false; // Рокировка невозможна
    }

    public void printBoard() {
        System.out.println("\t\t\tBlack side");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("\t\t\tWhite side");
        System.out.println();
        System.out.printf("Введите действие: ");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    public ChessPiece[] getPieces() {
        return null;
    }
}
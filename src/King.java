public class King extends ChessPiece {
    // Конструктор, принимающий цвет фигуры
    public King(String color) {
        super(color);
    }

    // Метод для проверки возможности хода
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверка, что цель находится в пределах доски
        if (toLine < 0 || toLine >= 8 || toColumn < 0 || toColumn >= 8) {
            return false; // Выход за пределы доски
        }

        // Проверяем, что король не остается на месте
        if (line == toLine && column == toColumn) {
            return false; // Король не может остаться на месте
        }

        // Король может двигаться на одну клетку в любом направлении
        int rowDifference = Math.abs(toLine - line);
        int colDifference = Math.abs(toColumn - column);

        return (rowDifference <= 1 && colDifference <= 1);
    }

    // Метод для определения символа фигуры
    @Override
    public String getSymbol() {
        return "K"; // Символ для короля
    }

    // Метод для проверки, находится ли поле под атакой
    public boolean isUnderAttack(ChessBoard board, int line, int column) {
        // Проверим все фигуры на доске
        for (ChessPiece piece : board.getPieces()) {
            // Если фигура принадлежит противнику, проверим, может ли она взять короля
            if (!piece.getColor().equals(this.getColor()) && piece.canMoveToPosition(board, line, column, line, column)) {
                return true; // Поле под атакой
            }
        }
        return false; // Поле не под атакой
    }
}
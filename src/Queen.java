public class Queen extends ChessPiece {
    // Конструктор, принимающий цвет фигуры
    public Queen(String color) {
        super(color);
    }

    // Метод для проверки возможности хода
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверка, что цель находится в пределах доски
        if (toLine < 0 || toLine >= 8 || toColumn < 0 || toColumn >= 8) {
            return false; // Выход за пределы доски
        }

        // Проверяем, что ферзь не остается на месте
        if (line == toLine && column == toColumn) {
            return false; // Ферзь не может остаться на месте
        }

        // Проверка, что ферзь может двигаться либо по диагонали, либо по прямой
        int rowDifference = Math.abs(toLine - line);
        int colDifference = Math.abs(toColumn - column);

        return (rowDifference == colDifference || line == toLine || column == toColumn);
    }

    // Метод для получения символа фигуры
    @Override
    public String getSymbol() {
        return "Q"; // Символ для ферзя
    }
}
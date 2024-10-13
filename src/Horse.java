public class Horse extends ChessPiece {
    // Конструктор, принимающий цвет фигуры
    public Horse(String color) {
        super(color);
    }

    // Метод для проверки возможности хода
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверка, что цель находится в пределах доски
        if (toLine < 0 || toLine >= 8 || toColumn < 0 || toColumn >= 8) {
            return false; // Выход за пределы доски
        }

        // Проверка, что конь ходит буквой "Г"
        int rowDifference = Math.abs(toLine - line);
        int colDifference = Math.abs(toColumn - column);

        // Конь может ходить на 2 клетки в одном направлении и 1 клетку в перпендикулярном
        if ((rowDifference == 2 && colDifference == 1) || (rowDifference == 1 && colDifference == 2)) {
            return !(line == toLine && column == toColumn); // Не может оставаться на месте
        }

        return false; // Ход не возможен
    }

    // Метод для получения символа фигуры
    @Override
    public String getSymbol() {
        return "H"; // Символ для коня
    }
}
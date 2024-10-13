public class Bishop extends ChessPiece {
    // Конструктор, принимающий цвет фигуры
    public Bishop(String color) {
        super(color);
    }

    // Метод для проверки возможности хода
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверка, что цель находится в пределах доски
        if (toLine < 0 || toLine >= 8 || toColumn < 0 || toColumn >= 8) {
            return false; // Выход за пределы доски
        }

        // Проверяем, что слон не остается на месте
        if (line == toLine && column == toColumn) {
            return false; // Слон не может остаться на месте
        }

        // Проверка, что слон движется по диагонали
        int rowDifference = Math.abs(toLine - line);
        int colDifference = Math.abs(toColumn - column);

        // Слон может двигаться только по диагонали, то есть разница в строках должна равняться разнице в столбцах
        if (rowDifference == colDifference) {
            return true; // Ход возможен
        }

        return false; // Ход не возможен
    }

    // Метод для получения символа фигуры
    @Override
    public String getSymbol() {
        return "B"; // Символ для слона
    }
}
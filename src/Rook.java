public class Rook extends ChessPiece {
    // Конструктор, принимающий цвет фигуры
    public Rook(String color) {
        super(color);
    }

    // Метод для проверки возможности хода
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверка, что цель находится в пределах доски
        if (toLine < 0 || toLine >= 8 || toColumn < 0 || toColumn >= 8) {
            return false; // Выход за пределы доски
        }

        // Проверяем, что ладья не остается на месте
        if (line == toLine && column == toColumn) {
            return false; // Ладья не может остаться на месте
        }

        // Ладья может двигаться только по прямой: либо по строкам, либо по столбцам
        if (line == toLine || column == toColumn) {
            return true; // Ход возможен
        }

        return false; // Ход не возможен
    }

    // Метод для получения символа фигуры
    @Override
    public String getSymbol() {
        return "R"; // Символ для ладьи
    }
}
public class Pawn extends ChessPiece {
    // Конструктор, принимающий цвет фигуры
    public Pawn(String color) {
        super(color);
    }

    // Метод для проверки возможности хода
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверка, что цель находится в пределах доски
        if (toLine < 0 || toLine >= 8 || toColumn < 0 || toColumn >= 8) {
            return false; // Выход за пределы доски
        }

        // Проверяем, что пешка не остается на месте
        if (line == toLine && column == toColumn) {
            return false; // Пешка не может остаться на месте
        }

        // Условие для белой пешки
        if (color.equals("White")) {
            // Пешка может двигаться на 1 клетку вперед
            if (toLine == line + 1 && column == toColumn) {
                return true;
            }
            // Первый ход пешки: может двигаться на 2 клетки вперед
            if (line == 1 && toLine == line + 2 && column == toColumn) {
                return true;
            }
        }

        // Условие для черной пешки
        if (color.equals("Black")) {
            // Пешка может двигаться на 1 клетку вперед
            if (toLine == line - 1 && column == toColumn) {
                return true;
            }
            // Первый ход пешки: может двигаться на 2 клетки вперед
            if (line == 6 && toLine == line - 2 && column == toColumn) {
                return true;
            }
        }

        return false; // Ход не возможен
    }

    // Метод для получения символа фигуры
    @Override
    public String getSymbol() {
        return "P"; // Символ для пешки
    }
}
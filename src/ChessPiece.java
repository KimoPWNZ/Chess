public abstract class ChessPiece {
    protected String color;    // Цвет фигуры
    protected boolean check;   // Логическая переменная, по умолчанию true

    // Конструктор, принимающий цвет
    public ChessPiece(String color) {
        this.color = color;
        this.check = true; // По умолчанию
    }

    // Метод для получения цвета фигуры
    public String getColor() {
        return color;
    }

    // Абстрактный метод для проверки возможности хода
    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    // Абстрактный метод для получения символа фигуры
    public abstract String getSymbol();
}
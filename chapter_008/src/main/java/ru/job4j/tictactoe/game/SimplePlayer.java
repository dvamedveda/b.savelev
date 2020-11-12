package ru.job4j.tictactoe.game;

/**
 * Класс, реализующий игрока.
 */
public class SimplePlayer implements Player {

    /**
     * Здесь хранится марка игрока.
     */
    private Mark mark;

    /**
     * Здесь хранятся правила игры.
     */
    private Rules rules;

    /**
     * Здесь хранится игровое поле.
     */
    private Board board;

    /**
     * Конструктор игрока.
     *
     * @param mark  фишка для игрока.
     * @param rules правила игры.
     * @param board игровое поле.
     */
    public SimplePlayer(Mark mark, Rules rules, Board board) {
        this.mark = mark;
        this.rules = rules;
        this.board = board;
    }

    /**
     * Сделать ход на игровое поле.
     *
     * @param point координаты.
     */
    @Override
    public void doMove(Point point) {
        if (this.mark.getType().equals(this.rules.currentPlayer().getMark().getType())) {
            if (this.rules.moveAvailable(point)) {
                if (this.board.setField(point, this.mark)) {
                    rules.moveDone();
                }
            } else {
                System.out.println("This position not empty or out, choose other field!");
            }
        } else {
            System.out.println("Now must move other player!");
        }
    }

    /**
     * Получить фишку игрока.
     *
     * @return фишка игрока.
     */
    @Override
    public Mark getMark() {
        return this.mark;
    }
}
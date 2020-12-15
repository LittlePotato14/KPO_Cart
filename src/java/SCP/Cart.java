/**
 * @author <a href="mailto:pvtochilina@edu.hse.ru">Polina Tochilina</a>
 */

package SCP;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class Cart {
    // монитор для System.out.println
    static final Object MONITOR_OUT = new Object();
    // рандом
    static final Random RANDOM = new Random();


    // форматирование даблов
    static final String DOUBLE_FORMAT = "#0.00";
    // вывод телеги
    static final String ART =
            "       _--_" + System.lineSeparator() +
                    "    _/______\\_" + System.lineSeparator() +
                    "   |__________|_/" + System.lineSeparator() +
                    "      O     O";

    // координаты телеги
    private double xCoo, yCoo;

    // все существа
    private final ArrayList<Creature> creatures = new ArrayList<>();
    // все потоки
    private final ArrayList<Thread> threads = new ArrayList<>();

    /**
     * Конструктор, инициализирующий существ и начальные координаты телеги
     *
     * @param x координата x
     * @param y координата y
     */
    public Cart(double x, double y) {
        xCoo = x;
        yCoo = y;
    }

    /**
     * Добавляет существо и возвращает его поток
     *
     * @param c существо
     * @return поток
     */
    public Thread addCreature(Creature c) {
        creatures.add(c);
        Thread t = new Thread(c);
        threads.add(t);
        return t;
    }

    /**
     * Передвинуть телегу на значение
     *
     * @param x значение по x
     * @param y значение по y
     */
    public void move(double x, double y) {
        this.xCoo += x;
        this.yCoo += y;
    }

    /**
     * x координата
     */
    public double getX() {
        return xCoo;
    }

    /**
     * y координата
     */
    public double getY() {
        return yCoo;
    }

    /**
     * Выводит арт телеги и ее координаты
     *
     * @return строковое представление телеги
     */
    @Override
    public String toString() {
        String formattedX = new DecimalFormat(DOUBLE_FORMAT).format(xCoo);
        String formattedY = new DecimalFormat(DOUBLE_FORMAT).format(yCoo);
        return ART + System.lineSeparator() + "Position: (" + formattedX + "; " + formattedY + ")";
    }

    /**
     * Симулирует начало движения телеги
     */
    @SuppressWarnings("BusyWait")
    public void goCart() {
        // выводим начальное состояние телеги
        System.out.println(this + " Time: 0.00 sec");

        // запускаем потоки всех существ
        for (int i = 0; i < threads.size(); i++)
            threads.get(i).start();

        // запоминаем время начала работы
        long start = System.nanoTime();

        try {
            // выводим состояние телеги каждые 2 секунды
            while ((System.nanoTime() - start) / 1000000000 < 24) {
                Thread.sleep(2000);
                System.out.println(this + " Time: " + (System.nanoTime() - start) / 10000000 / 100.0 + " sec");
            }
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Cart has been stopped");
        }

        // прерываем все потоки существ
        for (var t : threads)
            t.interrupt();

        // выводим конечное положение телеги
        System.out.println("Final cart state:" + System.lineSeparator() + this);
    }
}


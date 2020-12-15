/**
 * @author <a href="mailto:pvtochilina@edu.hse.ru">Polina Tochilina</a>
 */

package SCP;

import java.text.DecimalFormat;

public class Creature implements Runnable {
    // монитор для тяги
    static final Object MONITOR_MOVE = new Object();

    // форматирование даблов
    static final String DOUBLE_FORMAT = "#0.00";

    // название животного
    private final String name;
    // телега, в которую запряжено животное
    private final Cart cart;

    // на сколько передвигает по x
    private final double xMove;
    // на сколько передвигает по y
    private final double yMove;

    /**
     * Коснтруктор, инициализирующий название, телегу и координаты перемещения
     *
     * @param name  название животного
     * @param angle угол движжения
     * @param cart  телега
     */
    public Creature(String name, int angle, Cart cart) {
        this.name = name;
        this.cart = cart;

        double s = 1 + 9 * Cart.RANDOM.nextDouble();
        String formatted = new DecimalFormat(DOUBLE_FORMAT).format(s);
        xMove = s * Math.cos(Math.toRadians(angle));
        yMove = s * Math.sin(Math.toRadians(angle));

        synchronized (Cart.MONITOR_OUT) {
            System.out.println(name + "'s s = " + formatted);
        }
    }

    /**
     * Животное начинает попеременную тягу с отдыхом
     */
    @SuppressWarnings("BusyWait")
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            // выводим информацию о готовности к тяге
            synchronized (Cart.MONITOR_OUT) {
                System.out.println(name + " is ready to move");
            }

            try {
                // толкьо одно жиыотное может тянуть в один момент
                synchronized (MONITOR_MOVE) {
                    // выводим информацию о начале тяги
                    synchronized (Cart.MONITOR_OUT) {
                        System.out.println(name + " moves");
                    }
                    // делаем задержку по времени тяги
                    Thread.sleep(500);
                    // сдвигаем телегу
                    cart.move(xMove, yMove);
                }

                // генерируем время сна
                int time = 1000 + Cart.RANDOM.nextInt(4000);

                // выводим информацию о начале сна
                synchronized (Cart.MONITOR_OUT) {
                    System.out.println(name + " sleep now for " + time + " milliseconds");
                }

                // животное отдыхает
                Thread.sleep(time);
            } catch (InterruptedException e) {
                // животное нуждается в длительном отдыхе
                System.out.println(name + " just finished and need a long rest");
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}

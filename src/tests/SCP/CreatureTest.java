/**
 * @author <a href="mailto:pvtochilina@edu.hse.ru">Polina Tochilina</a>
 */

package SCP;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreatureTest {
    /**
     * Тестируем прерывание потока
     */
    @Test
    void testInterrupted() throws InterruptedException {
        Cart cart = new Cart(1, 4.5);
        Thread t = cart.addCreature(new Creature("name", 100, cart));
        t.start();
        Thread.sleep(100);
        t.interrupt();
        Thread.sleep(100);
        assertFalse(t.isAlive());
    }
}
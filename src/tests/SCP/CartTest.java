/**
 * @author <a href="mailto:pvtochilina@edu.hse.ru">Polina Tochilina</a>
 */

package SCP;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {
    /**
     * Тестируем верное присвоение координаты x
     */
    @Test
    void getX() {
        Cart cart = new Cart(1, 4.5);
        assertEquals(1, cart.getX());
    }

    /**
     * Тестируем верное присвоение координаты y
     */
    @Test
    void getY() {
        Cart cart = new Cart(1, 4.5);
        assertEquals(4.5, cart.getY());
    }

    /**
     * Тестируем верное перемещщение телеги
     */
    @Test
    void move() {
        Cart cart = new Cart(1, 4.5);
        cart.move(3, 5.7);
        assertEquals(4, cart.getX());
        assertEquals(10.2, cart.getY());
    }

    /**
     * Тестируем строковое представление телеги
     */
    @Test
    void testToString() {
        Cart cart = new Cart(1, 4.5);
        assertEquals(Cart.ART + System.lineSeparator() + "Position: (1,00; 4,50)", cart.toString());
    }

    /**
     * Тестим запуск телеги на двух существах
     * (при двух существах достоверно известно, что конечное положение телеги не совпадет с начальным)
     */
    @Test
    void goCart() {
        Cart cart = new Cart(0, 0);
        cart.addCreature(new Creature("1", 0, cart));
        cart.addCreature(new Creature("2", 90, cart));
        cart.goCart();
        assertNotEquals(0, cart.getX());
        assertNotEquals(0, cart.getY());
    }
}
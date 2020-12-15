/**
 * @author <a href="mailto:pvtochilina@edu.hse.ru">Polina Tochilina</a>
 */

package SCP;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {
    /**
     * Тест на создание телеги с 0 аргументами
     */
    @Test
    void createCart0() {
        Cart cart = Project.createCart(new String[]{});
        assertEquals(0, cart.getX());
        assertEquals(0, cart.getY());
    }

    /**
     * Тест на создание телеги с 1 аргументом
     */
    @Test
    void createCart1() {
        Cart cart = Project.createCart(new String[]{"1"});
        assertEquals(1, cart.getX());
        assertEquals(0, cart.getY());
    }

    /**
     * Тест на создание телеги с 2 аргументами
     */
    @Test
    void createCart2() {
        Cart cart = Project.createCart(new String[]{"1", "2.4"});
        assertEquals(1, cart.getX());
        assertEquals(2.4, cart.getY());
    }

    /**
     * Тест на создание телеги с больше чем 2 аргументами
     */
    @Test
    void createCartMore() {
        assertThrows(IllegalArgumentException.class, () -> Project.createCart(new String[]{"1", "2", "3"}));
    }

    /**
     * Тест на создание телеги с аргументами неверного типа
     */
    @Test
    void createCartWrongType() {
        assertThrows(IllegalArgumentException.class, () -> Project.createCart(new String[]{"dsf", "0"}));
    }
}
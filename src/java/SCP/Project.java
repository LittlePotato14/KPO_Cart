/**
 * @author <a href="mailto:pvtochilina@edu.hse.ru">Polina Tochilina</a>
 */

package SCP;

public class Project {
    // углы существ
    static final int angleSwan = 60, angleCray = 180, anglePike = 300;

    /**
     * Генерирует телегу на переданных аргументах
     *
     * @param coos координаты
     * @return телега
     * @throws IllegalArgumentException если аргументы неверного типа или их больше 2
     */
    public static Cart createCart(String[] coos) throws IllegalArgumentException {
        double x = 0, y = 0;
        // слишком много аргументов
        if (coos.length > 2)
            throw new IllegalArgumentException("Too much arguments. Please input 0-2 double arguments.");

        // парсим аргументы
        try {
            if (coos.length == 1)
                x = Double.parseDouble(coos[0]);
            else if (coos.length == 2) {
                x = Double.parseDouble(coos[0]);
                y = Double.parseDouble(coos[1]);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Wrong argument type. Please input 0-2 double arguments.");
        }

        // создаем телегу
        return new Cart(x, y);
    }

    /**
     * Добавляет в телегу лебедя, рака и щуку
     *
     * @param cart телега, куда нужно добавить существ
     */
    public static void addCreatures(Cart cart) {
        cart.addCreature(new Creature("Swan", angleSwan, cart));
        cart.addCreature(new Creature("Crayfish", angleCray, cart));
        cart.addCreature(new Creature("Pike", anglePike, cart));
    }

    public static void main(String[] args) {
        Cart cart;

        try {
            cart = createCart(args);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        addCreatures(cart);
        cart.goCart();
    }
}

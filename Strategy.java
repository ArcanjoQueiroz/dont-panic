import java.io.Serializable;

/**
 * This is a Strategy Pattern
 */
public class Strategy {

  static class Character implements Serializable {

    private static final long serialVersionUID = -5934386384764785282L;

    private String name;
    private Weapon weapon;

    public Character(final String name) { 
      this.name = name;
    }

    public String getName() {
      return this.name;
    }

    /**
     * We can encapsulate a family of algorithms
     */
    public void setWeapon(final Weapon weapon) {
      this.weapon = weapon;
    }

    public void fight() {
      if (weapon != null) {
        weapon.use();
      }
    }
  }

  @FunctionalInterface
  interface Weapon {
    public void use();
  }

  static class Sword implements Weapon {

    @Override
    public void use() {
      System.out.println("Using a Sword");
    }

  }

  static class ArrowAndBow implements Weapon {

    @Override
    public void use() {
      System.out.println("Using an Arrow and a Bow");
    }

  }

  static class Knife implements Weapon {

    @Override
    public void use() {
      System.out.println("Using a Knife");
    }

  }

  public static void main(String[] args) {
    final Character chuck = new Character("Chuck");
    chuck.setWeapon(new Sword());
    chuck.fight();
    chuck.setWeapon(new ArrowAndBow());
    chuck.fight();
    chuck.setWeapon(new Knife());
    chuck.fight();
  }
}
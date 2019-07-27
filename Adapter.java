public class Adapter {
  interface Duck {
    public void squeak();
  }

  static class MallardDuck implements Duck {

    @Override
    public void squeak() {
      System.out.println("Squeaking...");
    }

  }

  interface Bird {
    public void fly();

    public void makeNoise();
  }

  static class BirdAdapter implements Bird {

    private Duck duck;

    public BirdAdapter(final Duck duck) {
      this.duck = duck;
    }

    @Override
    public void fly() {
      System.out.println("Flying...");
    }

    @Override
    public void makeNoise() {
      for (int i = 0; i < 5; i++) {
        this.duck.squeak();
      }
    }

    public Duck getDuck() {
      return this.duck;
    }

  }

  public static void main(String[] args) {
    // This is a Duck, a Mallard Duck
    final Duck duck = new MallardDuck();

    // A BirdAdapter adapts a Duck to a Bird: "It is not a transformation"
    final Bird adapter = new BirdAdapter(duck);

    // This method expects a Bird
    doSomething(adapter);

    // In this point, I am going to extract the "actual" Duck from the adapter
    final Duck d = ((BirdAdapter) adapter).getDuck();
    d.squeak();
  }

  public static void doSomething(final Bird bird) {
    bird.fly();
    bird.makeNoise();
  }
}

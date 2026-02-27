public class Cat {

    private  static final Utility utility = new Utility();

    // fazer sistema de stamina
    String name;
    int energy;

    // Isso é o construtor: ele roda assim que você dá o "new"
    // equivalente ao .new() de OOP dos metatables, this é o equivalente de self
    public Cat(String nome) {

        this.name = nome;
        this.energy = 10; // starts with 10 energy

    }

    public void roar() {
        int cost = 1;
        if (this.energy > 1) {
            this.energy--;
            System.out.println("gato " + this.name + " diz: " + "RAWRRRR!!!!");
            System.out.println(this.name + " perde " + cost + " stamina. Atual: " + this.energy);
        } else {
            utility.write(this.name + " nao possui energia suficiente para rugidos, tente um descanso");
        }
    }

    public void sleep() {
        if (this.energy < 10) {
            utility.write(this.name + " iniciando soneca");
            while (this.energy < 10) {
                utility.sleep(1);

                this.energy++;

                utility.write(this.name + " energy: " + this.energy);
            }
            utility.sleep(0.5);
            utility.write(this.name + " dormiu um bocado e recuperou energia" );
        } else {
            utility.write(this.name + " nao precisa de descanso por ora");
        }
    }

}
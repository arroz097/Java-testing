public class Cat {

    private static final Utility utility = new Utility();

    // fazer sistema de stamina
    // private faz com que scripts de fora nao consigam alterar variaveis private
    // final permite que variavel só seja atribuido um valor uma unica vez
    private final String name;
    private int energy;
    private boolean onAction = false;

    // Isso é o construtor: ele roda assim que você dá o "new"
    // equivalente ao .new() de OOP dos metatables, this é o equivalente de self
    public Cat(String nome) {

        this.name = nome;
        this.energy = 10; // começa com 10 de energia

    }

    public String getName() {
        return this.name;
    }

    public int getEnergy() {
        return this.energy;
    }

    public boolean getAction() {
        return this.onAction;
    }

    public void roar() {
        if (this.onAction) return;

        int cost = 1;

        if (this.energy >= cost) {
            this.onAction = true;

            this.energy--;
            utility.write("gato " + this.name + " diz: " + "RAWRRRR!!!!");
            utility.sleep(0.5);

            utility.write(this.name + " perde " + cost + " stamina");
            utility.sleep(0.5);

            utility.write(this.name + " possui atualmente " + this.energy + " de energia");
            utility.sleep(0.3);

            this.onAction = false;
        } else {
            utility.write(this.name + " nao possui energia suficiente para rugidos, tente um descanso");
        }

    }

    public void sleep() {
        if (this.energy < 10) {
            if (this.onAction) return;

            this.onAction = true;

            utility.write(this.name + " iniciando soneca");

            int recoveredEnergy = 0;

            while (this.energy < 10) {
                utility.sleep(1);

                this.energy++;
                recoveredEnergy++;

                utility.write(this.name + " energia: " + this.energy);
            }

            utility.sleep(0.5);
            utility.write(this.name + " dormiu um bocado e recuperou +" + recoveredEnergy + " de energia"); // talvez dizer quanto de energia recuperou?

            utility.sleep(0.5);

            this.onAction = false;
        } else {
            utility.write(this.name + " nao precisa de descanso por ora");
        }
    }
}
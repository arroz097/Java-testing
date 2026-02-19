public class Cat {

    String nome;

    // Isso é o construtor: ele roda assim que você dá o "new"
    // equivalente ao .new() de OOP dos metatables, this é o equivalente de self
    public Cat(String nome) {

        this.nome = nome;
    }

    public void roar() {
        System.out.println("gato " + this.nome + " diz: " + "RAWRRRR!!!!");
    }
}

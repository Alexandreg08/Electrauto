public class TypeRecharge {
    private String nom;
    private int puissance; // en kW

    public TypeRecharge(String nom, int puissance) {
        this.nom = nom;
        this.puissance = puissance;
    }

    public String getNom() {
        return nom;
    }

    public int getPuissance() {
        return puissance;
    }
}
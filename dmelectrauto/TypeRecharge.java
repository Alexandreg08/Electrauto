package dmelectrauto;

public class TypeRecharge {
    public static final String NORMALE = "normale";
    public static final String SEMI_RAPIDE = "semi-rapide";
    public static final String RAPIDE = "rapide";
    
    private String nom;
    private int puissance; // en kW

    public TypeRecharge(String nom, int puissance) {
        if (!nom.equals(NORMALE) && !nom.equals(SEMI_RAPIDE) && !nom.equals(RAPIDE)) {
            throw new IllegalArgumentException("Type de recharge invalide. Types acceptés : " + 
                                           NORMALE + ", " + SEMI_RAPIDE + ", " + RAPIDE);
        }
        this.nom = nom;
        this.puissance = puissance;
    }

    public String getNom() {
        return nom;
    }

    public int getPuissance() {
        return puissance;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TypeRecharge other = (TypeRecharge) obj;
        return nom.equals(other.nom);
    }

    @Override
    public String toString() {
        return nom + " (" + puissance + " kW)";
    }
}
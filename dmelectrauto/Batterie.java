package dmelectrauto;

public class Batterie {
    private String reference;
    private String fabricant;
    private int chargeMax;
    private int chargeActuelle;
    private TypeRecharge typeRecharge;

    public Batterie(String reference, String fabricant, int chargeMax, TypeRecharge typeRecharge) {
        this.reference = reference;
        this.fabricant = fabricant;
        this.chargeMax = chargeMax;
        this.chargeActuelle = 0; // batterie vide au départ
        this.typeRecharge = typeRecharge;
    }

    public String getRef() {
        return reference;
    }

    public String getFabricant() {
        return fabricant;
    }

    public int getChargeMax() {
        return chargeMax;
    }

    public int getChargeActuelle() {
        return chargeActuelle;
    }

    public void setChargeActuelle(int chargeActuelle) {
        if (chargeActuelle < 0) {
            this.chargeActuelle = 0;
        } else {
            this.chargeActuelle = Math.min(chargeActuelle, chargeMax);
        }
    }

    public TypeRecharge getTypeRecharge() {
        return typeRecharge;
    }

    public boolean estCompatible(TypeRecharge type) {
        // Toutes les batteries supportent la charge normale
        if (type.getNom().equals("normale")) {
            return true;
        }
        // Vérification de la compatibilité avec le type de recharge
        return this.typeRecharge.getNom().equals(type.getNom());
    }

    public int getPourcentageCharge() {
        return (chargeActuelle * 100) / chargeMax;
    }
}
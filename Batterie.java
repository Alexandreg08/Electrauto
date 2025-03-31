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
        this.chargeActuelle = Math.min(chargeActuelle, chargeMax);
    }

    public TypeRecharge getTypeRecharge() {
        return typeRecharge;
    }
}
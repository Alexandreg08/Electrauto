public class Main {
    public static void main(String[] args) {
        TypeRecharge normale = new TypeRecharge("normale", 3);
        Carte carte1 = new Carte("Doe", "Jane", "forfait", 50f);
        Borne borne1 = new Borne(101, normale);

        borne1.insererCarte(carte1);
        borne1.insererPrise();
        try {
            carte1.decompterCharge(10f);
            borne1.charger(5);
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}
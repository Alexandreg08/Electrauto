package dmelectrauto;

public class Main {
    public static void main(String[] args) {
        // Création des types de recharge
        TypeRecharge rechargeNormale = new TypeRecharge(TypeRecharge.NORMALE, 3);
        TypeRecharge rechargeSemiRapide = new TypeRecharge(TypeRecharge.SEMI_RAPIDE, 24);
        TypeRecharge rechargeRapide = new TypeRecharge(TypeRecharge.RAPIDE, 50);

        // Création d'une batterie
        Batterie batterie = new Batterie("BAT-001", "Tesla", 75, rechargeSemiRapide);

        // Création d'une carte avec forfait
        Carte carteForfait = new Carte(
                "Dupont-De-Ligonès",
                "Xavier",
                "0123456789",
                "0612345678",
                "3 rue de la terrasse",
                "xavier.dupont-de-ligonès@gmail.com",
                "AB-123-CD",
                "forfait",
                100.0f);

        // Création d'une carte avec abonnement
        Carte carteAbonnement = new Carte(
                "Mazure",
                "Hugo",
                "0987654321",
                "0678901234",
                "456 avenue des Champs-Élysées",
                "hugo.mazure@gmail.com",
                "XY-789-ZW",
                "abonnement",
                12 // 12 mois d'abonnement
        );

        // Création d'une borne
        Borne borne = new Borne(1, rechargeSemiRapide, 48.8566, 2.3522, "123 rue de Paris");

        // Simulation d'une session de recharge avec forfait
        System.out.println("=== Début de la session de recharge (Forfait) ===");
        try {
            // Insertion de la carte
            borne.insererCarte(carteForfait);

            // Connexion de la prise
            borne.insererPrise(batterie);

            // Simulation d'un chargement de 30 secondes
            borne.charger(30);

            System.out.println("État final de la batterie : " + batterie.getChargeActuelle() +
                    " kWh (" + batterie.getPourcentageCharge() + "%)");
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        System.out.println("=== Fin de la session de recharge (Forfait) ===");

        // Simulation d'une session de recharge avec abonnement
        System.out.println("\n=== Début de la session de recharge (Abonnement) ===");
        try {
            // Insertion de la carte
            borne.insererCarte(carteAbonnement);

            // Connexion de la prise
            borne.insererPrise(batterie);

            // Simulation d'un chargement de 60 secondes
            borne.charger(60);

            System.out.println("État final de la batterie : " + batterie.getChargeActuelle() +
                    " kWh (" + batterie.getPourcentageCharge() + "%)");
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        System.out.println("=== Fin de la session de recharge ===");
    }
}
package dmelectrauto;

public class TestSystem {
    public static void main(String[] args) {
        System.out.println("=== Début des tests du système ElectrAuto ===\n");

        // Test 1: Création des types de recharge
        System.out.println("Test 1: Types de recharge");
        try {
            TypeRecharge rechargeNormale = new TypeRecharge("normale", 3);
            TypeRecharge rechargeSemiRapide = new TypeRecharge("semi-rapide", 24);
            TypeRecharge rechargeRapide = new TypeRecharge("rapide", 50);
            System.out.println("✓ Types de recharge créés avec succès");
        } catch (Exception e) {
            System.out.println("✗ Erreur lors de la création des types de recharge: " + e.getMessage());
        }

        // Test 2: Création des batteries
        System.out.println("\nTest 2: Batteries");
        try {
            Batterie batterieNormale = new Batterie("BAT-001", "Tesla", 75, 
                new TypeRecharge("normale", 3));
            Batterie batterieSemiRapide = new Batterie("BAT-002", "Tesla", 75, 
                new TypeRecharge("semi-rapide", 24));
            Batterie batterieRapide = new Batterie("BAT-003", "Tesla", 75, 
                new TypeRecharge("rapide", 50));
            System.out.println("✓ Batteries créées avec succès");
        } catch (Exception e) {
            System.out.println("✗ Erreur lors de la création des batteries: " + e.getMessage());
        }

        // Test 3: Création des cartes
        System.out.println("\nTest 3: Cartes");
        try {
            Carte carteForfait = new Carte(
                "Dupont", "Jean", "0123456789", "0612345678",
                "123 rue de Paris", "jean.dupont@email.com", "AB-123-CD",
                "forfait", 100.0f
            );
            Carte carteAbonnement = new Carte(
                "Martin", "Marie", "0987654321", "0678901234",
                "456 avenue des Champs-Élysées", "marie.martin@email.com", "XY-789-ZW",
                "abonnement", 12
            );
            System.out.println("✓ Cartes créées avec succès");
        } catch (Exception e) {
            System.out.println("✗ Erreur lors de la création des cartes: " + e.getMessage());
        }

        // Test 4: Création des bornes
        System.out.println("\nTest 4: Bornes");
        try {
            Borne borneNormale = new Borne(1, new TypeRecharge("normale", 3),
                48.8566, 2.3522, "123 rue de Paris");
            Borne borneSemiRapide = new Borne(2, new TypeRecharge("semi-rapide", 24),
                48.8566, 2.3522, "456 rue de Paris");
            Borne borneRapide = new Borne(3, new TypeRecharge("rapide", 50),
                48.8566, 2.3522, "789 rue de Paris");
            System.out.println("✓ Bornes créées avec succès");
        } catch (Exception e) {
            System.out.println("✗ Erreur lors de la création des bornes: " + e.getMessage());
        }

        // Test 5: Session de recharge complète
        System.out.println("\nTest 5: Session de recharge complète");
        try {
            // Configuration
            Borne borne = new Borne(1, new TypeRecharge("semi-rapide", 24),
                48.8566, 2.3522, "123 rue de Paris");
            Batterie batterie = new Batterie("BAT-001", "Tesla", 75,
                new TypeRecharge("semi-rapide", 24));
            Carte carte = new Carte(
                "Test", "Test", "0123456789", "0612345678",
                "123 rue de Paris", "test@email.com", "AB-123-CD",
                "forfait", 100.0f
            );

            // Test de la session
            borne.insererCarte(carte);
            borne.insererPrise(batterie);
            borne.charger(30);
            
            System.out.println("✓ Session de recharge réussie");
            System.out.println("État final de la batterie : " + batterie.getChargeActuelle() + " kWh");
        } catch (Exception e) {
            System.out.println("✗ Erreur lors de la session de recharge: " + e.getMessage());
        }

        // Test 6: Test des incompatibilités
        System.out.println("\nTest 6: Test des incompatibilités");
        try {
            Borne borneRapide = new Borne(1, new TypeRecharge("rapide", 50),
                48.8566, 2.3522, "123 rue de Paris");
            Batterie batterieNormale = new Batterie("BAT-001", "Tesla", 75,
                new TypeRecharge("normale", 3));
            Carte carte = new Carte(
                "Test", "Test", "0123456789", "0612345678",
                "123 rue de Paris", "test@email.com", "AB-123-CD",
                "forfait", 100.0f
            );

            borneRapide.insererCarte(carte);
            borneRapide.insererPrise(batterieNormale);
            System.out.println("✗ Erreur: La compatibilité n'a pas été vérifiée correctement");
        } catch (Exception e) {
            System.out.println("✓ Test d'incompatibilité réussi: " + e.getMessage());
        }

        System.out.println("\n=== Fin des tests du système ElectrAuto ===");
    }
} 
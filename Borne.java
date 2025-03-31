public class Borne {
    private int id;
    private TypeRecharge typeRecharge;
    private Carte carteInseree;

    public Borne(int id, TypeRecharge typeRecharge) {
        this.id = id;
        this.typeRecharge = typeRecharge;
    }

    public void insererCarte(Carte carte) {
        this.carteInseree = carte;
        afficher("Carte insérée : " + carte.getNom() + " " + carte.getPrenom());
        if (carte.estUnForfait()) {
            try {
                afficher("Solde : " + carte.lireSolde() + " kWh");
            } catch (Exception e) {
                afficher(e.getMessage());
            }
        } else {
            afficher("Contrat : Abonnement actif");
        }
    }

    public void afficher(String texte) {
        System.out.println("[Borne " + id + "] " + texte);
    }

    public void insererPrise() {
        if (carteInseree == null) {
            afficher("Erreur : aucune carte insérée.");
        } else {
            afficher("Prise connectée. Prêt à charger.");
        }
    }

    public void charger(int t) {
        if (carteInseree == null) {
            afficher("Impossible de charger : carte absente.");
            return;
        }
        afficher("Chargement en cours pendant " + t + " secondes...");
        try {
            Thread.sleep(t * 1000);
        } catch (InterruptedException e) {
            afficher("Chargement interrompu.");
        }
        afficher("Chargement terminé.");
    }
}
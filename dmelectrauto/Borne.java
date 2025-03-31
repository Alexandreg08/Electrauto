package dmelectrauto;

import java.time.LocalDate;

public class Borne {
    private int id;
    private TypeRecharge typeRecharge;
    private Carte carteInseree;
    private double latitude;
    private double longitude;
    private String adresse;
    private LocalDate dateMiseEnService;
    private LocalDate dateDerniereRevision;
    private Batterie batterieConnectee;

    public Borne(int id, TypeRecharge typeRecharge, double latitude, double longitude, 
                String adresse) {
        this.id = id;
        this.typeRecharge = typeRecharge;
        this.latitude = latitude;
        this.longitude = longitude;
        this.adresse = adresse;
        this.dateMiseEnService = LocalDate.now();
        this.dateDerniereRevision = LocalDate.now();
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
            afficher("Contrat : Abonnement actif jusqu'au " + carte.getDateFinAbonnement());
        }
    }

    public void afficher(String texte) {
        System.out.println("[Borne " + id + "] " + texte);
    }

    public void insererPrise(Batterie batterie) throws Exception {
        if (carteInseree == null) {
            throw new Exception("Erreur : aucune carte insérée.");
        }
        if (!carteInseree.estAbonnementValide()) {
            throw new Exception("Abonnement expiré.");
        }
        if (!batterie.getTypeRecharge().equals(typeRecharge)) {
            throw new Exception("Type de recharge incompatible. Borne : " + typeRecharge.getNom() + 
                              ", Batterie : " + batterie.getTypeRecharge().getNom());
        }
        this.batterieConnectee = batterie;
        afficher("Prise connectée. Prêt à charger.");
    }

    public void charger(int t) throws Exception {
        if (carteInseree == null) {
            throw new Exception("Impossible de charger : carte absente.");
        }
        if (batterieConnectee == null) {
            throw new Exception("Impossible de charger : aucune batterie connectée.");
        }
        if (!carteInseree.estAbonnementValide()) {
            throw new Exception("Abonnement expiré.");
        }

        afficher("Chargement en cours pendant " + t + " secondes...");
        try {
            Thread.sleep(t * 1000);
            // Calcul approximatif de l'énergie délivrée (kWh)
            double energieDelivree = (typeRecharge.getPuissance() * t) / 3600.0;
            if (carteInseree.estUnForfait()) {
                carteInseree.decompterCharge((float)energieDelivree);
            }
            batterieConnectee.setChargeActuelle(
                (int)Math.min(batterieConnectee.getChargeMax(), 
                            batterieConnectee.getChargeActuelle() + (int)energieDelivree)
            );
            afficher("Chargement terminé. Énergie délivrée : " + energieDelivree + " kWh");
        } catch (InterruptedException e) {
            afficher("Chargement interrompu.");
        }
    }

    // Getters
    public int getId() { return id; }
    public TypeRecharge getTypeRecharge() { return typeRecharge; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
    public String getAdresse() { return adresse; }
    public LocalDate getDateMiseEnService() { return dateMiseEnService; }
    public LocalDate getDateDerniereRevision() { return dateDerniereRevision; }
    public Carte getCarteInseree() { return carteInseree; }
    public Batterie getBatterieConnectee() { return batterieConnectee; }

    public void setDateDerniereRevision(LocalDate date) {
        this.dateDerniereRevision = date;
    }
}
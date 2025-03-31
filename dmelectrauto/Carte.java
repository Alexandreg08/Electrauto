package dmelectrauto;

import java.time.LocalDate;

public class Carte {
    private String nom;
    private String prenom;
    private String telephoneFixe;
    private String telephoneMobile;
    private String adressePostale;
    private String email;
    private String immatriculation;
    private LocalDate dateContrat;
    private String typeContrat; // "forfait" ou "abonnement"
    private Float soldeForfait; // null si abonnement
    private LocalDate dateDebutAbonnement;
    private LocalDate dateFinAbonnement;

    public Carte(String nom, String prenom, String telephoneFixe, String telephoneMobile, 
                String adressePostale, String email, String immatriculation, 
                String typeContrat, Float soldeForfait) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephoneFixe = telephoneFixe;
        this.telephoneMobile = telephoneMobile;
        this.adressePostale = adressePostale;
        this.email = email;
        this.immatriculation = immatriculation;
        this.dateContrat = LocalDate.now();
        this.typeContrat = typeContrat.toLowerCase();
        this.soldeForfait = this.typeContrat.equals("forfait") ? soldeForfait : null;
    }

    public Carte(String nom, String prenom, String telephoneFixe, String telephoneMobile, 
                String adressePostale, String email, String immatriculation, 
                String typeContrat, int dureeAbonnementMois) {
        this(nom, prenom, telephoneFixe, telephoneMobile, adressePostale, email, 
             immatriculation, typeContrat, null);
        this.dateDebutAbonnement = LocalDate.now();
        this.dateFinAbonnement = this.dateDebutAbonnement.plusMonths(dureeAbonnementMois);
    }

    // Getters
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getTelephoneFixe() { return telephoneFixe; }
    public String getTelephoneMobile() { return telephoneMobile; }
    public String getAdressePostale() { return adressePostale; }
    public String getEmail() { return email; }
    public String getImmatriculation() { return immatriculation; }
    public LocalDate getDateContrat() { return dateContrat; }
    public LocalDate getDateDebutAbonnement() { return dateDebutAbonnement; }
    public LocalDate getDateFinAbonnement() { return dateFinAbonnement; }

    public boolean estUnForfait() {
        return "forfait".equals(typeContrat);
    }

    public float lireSolde() throws Exception {
        if (!estUnForfait()) throw new Exception("Contrat non forfaitaire : pas de solde disponible.");
        return soldeForfait;
    }

    public void decompterCharge(float montant) throws Exception {
        if (!estUnForfait()) throw new Exception("Opération interdite sur un abonnement.");
        if (soldeForfait < montant) throw new Exception("Solde insuffisant.");
        soldeForfait -= montant;
    }

    public boolean estAbonnementValide() {
        if (estUnForfait()) return true;
        return LocalDate.now().isBefore(dateFinAbonnement);
    }

    public void renouvelerAbonnement(int dureeMois) throws Exception {
        if (estUnForfait()) throw new Exception("Impossible de renouveler un forfait.");
        dateFinAbonnement = dateFinAbonnement.plusMonths(dureeMois);
    }
}
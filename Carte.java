public class Carte {
    private String nom;
    private String prenom;
    private String typeContrat; // "forfait" ou "abonnement"
    private Float soldeForfait; // null si abonnement

    public String getNom() {
        return nom;
    }
    
    public String getPrenom() {
        return prenom;
    }
    
    public Carte(String nom, String prenom, String typeContrat, Float soldeForfait) {
        this.nom = nom;
        this.prenom = prenom;
        this.typeContrat = typeContrat.toLowerCase();
        this.soldeForfait = this.typeContrat.equals("forfait") ? soldeForfait : null;
    }

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
}
package dmelectrauto;

import java.awt.*;
import javax.swing.*;

public class ElectrAutoSwingApp extends JFrame {
    private Borne borne;
    private Carte carteCourante;
    private Batterie batterieCourante;
    private JTextArea logArea;
    private JTextField nomField, prenomField, telephoneFixeField, telephoneMobileField;
    private JTextField adresseField, emailField, immatriculationField;
    private JTextField typeContratField, soldeField, tempsChargementField;
    private boolean priseConnectee = false;
    
    public ElectrAutoSwingApp() {
        // Configuration de la fenêtre
        setTitle("ElectrAuto - Gestion des bornes de recharge");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        // Création des composants
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Panel de configuration
        JPanel configPanel = new JPanel(new GridLayout(10, 2, 5, 5));
        configPanel.setBorder(BorderFactory.createTitledBorder("Configuration"));
        
        configPanel.add(new JLabel("Nom:"));
        nomField = new JTextField();
        configPanel.add(nomField);
        
        configPanel.add(new JLabel("Prénom:"));
        prenomField = new JTextField();
        configPanel.add(prenomField);
        
        configPanel.add(new JLabel("Téléphone fixe:"));
        telephoneFixeField = new JTextField();
        configPanel.add(telephoneFixeField);
        
        configPanel.add(new JLabel("Téléphone mobile:"));
        telephoneMobileField = new JTextField();
        configPanel.add(telephoneMobileField);
        
        configPanel.add(new JLabel("Adresse:"));
        adresseField = new JTextField();
        configPanel.add(adresseField);
        
        configPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        configPanel.add(emailField);
        
        configPanel.add(new JLabel("Immatriculation:"));
        immatriculationField = new JTextField();
        configPanel.add(immatriculationField);
        
        configPanel.add(new JLabel("Type de contrat (forfait/abonnement):"));
        typeContratField = new JTextField();
        configPanel.add(typeContratField);
        
        configPanel.add(new JLabel("Solde (kWh) ou durée abonnement (mois):"));
        soldeField = new JTextField();
        configPanel.add(soldeField);
        
        configPanel.add(new JLabel("Temps de chargement (secondes):"));
        tempsChargementField = new JTextField();
        configPanel.add(tempsChargementField);
        
        // Panel des boutons
        JPanel buttonPanel = new JPanel();
        JButton creerCarteButton = new JButton("Créer carte");
        JButton insererCarteButton = new JButton("Insérer carte");
        JButton connecterPriseButton = new JButton("Connecter prise");
        JButton chargerButton = new JButton("Charger");
        
        buttonPanel.add(creerCarteButton);
        buttonPanel.add(insererCarteButton);
        buttonPanel.add(connecterPriseButton);
        buttonPanel.add(chargerButton);
        
        // Zone de log
        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Log"));
        
        // Ajout des composants
        mainPanel.add(configPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);
        
        add(mainPanel);
        
        // Initialisation de la borne
        borne = new Borne(1, new TypeRecharge("semi-rapide", 24), 48.8566, 2.3522, "123 rue de Paris");
        
        // Création d'une batterie par défaut
        batterieCourante = new Batterie("BAT-001", "Tesla", 75, new TypeRecharge("semi-rapide", 24));
        
        // Gestionnaires d'événements
        creerCarteButton.addActionListener(e -> {
            try {
                String nom = nomField.getText();
                String prenom = prenomField.getText();
                String telephoneFixe = telephoneFixeField.getText();
                String telephoneMobile = telephoneMobileField.getText();
                String adresse = adresseField.getText();
                String email = emailField.getText();
                String immatriculation = immatriculationField.getText();
                String typeContrat = typeContratField.getText();
                float solde = Float.parseFloat(soldeField.getText());
                
                if (typeContrat.equalsIgnoreCase("forfait")) {
                    carteCourante = new Carte(nom, prenom, telephoneFixe, telephoneMobile, 
                                            adresse, email, immatriculation, typeContrat, solde);
                } else {
                    carteCourante = new Carte(nom, prenom, telephoneFixe, telephoneMobile, 
                                            adresse, email, immatriculation, typeContrat, (int)solde);
                }
                logArea.append("Carte créée pour " + nom + " " + prenom + "\n");
            } catch (NumberFormatException ex) {
                logArea.append("Erreur: Le solde doit être un nombre\n");
            }
        });
        
        insererCarteButton.addActionListener(e -> {
            if (carteCourante != null) {
                borne.insererCarte(carteCourante);
                logArea.append("Carte insérée dans la borne\n");
                priseConnectee = false; // Réinitialiser l'état de la prise
            } else {
                logArea.append("Erreur: Aucune carte créée\n");
            }
        });
        
        connecterPriseButton.addActionListener(e -> {
            try {
                if (carteCourante == null) {
                    logArea.append("Erreur: Insérez d'abord une carte\n");
                    return;
                }
                borne.insererPrise(batterieCourante);
                priseConnectee = true;
                logArea.append("Prise connectée\n");
                logArea.append("État de la batterie : " + batterieCourante.getChargeActuelle() + 
                             " kWh sur " + batterieCourante.getChargeMax() + " kWh\n");
            } catch (Exception ex) {
                logArea.append("Erreur: " + ex.getMessage() + "\n");
                priseConnectee = false;
            }
        });
        
        chargerButton.addActionListener(e -> {
            try {
                if (!priseConnectee) {
                    logArea.append("Erreur: Connectez d'abord la prise\n");
                    return;
                }
                int temps = Integer.parseInt(tempsChargementField.getText());
                borne.charger(temps);
                logArea.append("Chargement effectué pendant " + temps + " secondes\n");
                logArea.append("Nouvel état de la batterie : " + batterieCourante.getChargeActuelle() + 
                             " kWh sur " + batterieCourante.getChargeMax() + " kWh\n");
            } catch (NumberFormatException ex) {
                logArea.append("Erreur: Le temps de chargement doit être un nombre\n");
            } catch (Exception ex) {
                logArea.append("Erreur: " + ex.getMessage() + "\n");
            }
        });
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ElectrAutoSwingApp().setVisible(true);
        });
    }
}
import javax.swing.*;
import java.awt.*;

public class ElectrAutoSwingApp extends JFrame {
    private JTextField nomField, prenomField, typeContratField, soldeField;
    private JTextArea console;
    private Carte carte;
    private Borne borne;

    public ElectrAutoSwingApp() {
        setTitle("ElectrAuto - Simulation de Borne");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Créer la borne avec un type de recharge "normale"
        TypeRecharge typeRecharge = new TypeRecharge("normale", 3);
        borne = new Borne(1, typeRecharge);

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        nomField = new JTextField();
        prenomField = new JTextField();
        typeContratField = new JTextField();
        soldeField = new JTextField();

        inputPanel.add(new JLabel("Nom:"));
        inputPanel.add(nomField);
        inputPanel.add(new JLabel("Prénom:"));
        inputPanel.add(prenomField);
        inputPanel.add(new JLabel("Type de contrat (forfait/abonnement):"));
        inputPanel.add(typeContratField);
        inputPanel.add(new JLabel("Solde (si forfait):"));
        inputPanel.add(soldeField);

        JButton insertCardBtn = new JButton("Insérer carte");
        JButton insertPlugBtn = new JButton("Brancher la prise");
        JButton chargeBtn = new JButton("Charger 5 kWh");

        insertCardBtn.addActionListener(e -> insererCarte());
        insertPlugBtn.addActionListener(e -> borne.insererPrise());
        chargeBtn.addActionListener(e -> charger());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(insertCardBtn);
        buttonPanel.add(insertPlugBtn);
        buttonPanel.add(chargeBtn);

        console = new JTextArea(8, 50);
        console.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(console);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }

    private void insererCarte() {
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String contrat = typeContratField.getText().toLowerCase();
        String soldeStr = soldeField.getText();

        Float solde = null;
        if (contrat.equals("forfait")) {
            try {
                solde = Float.parseFloat(soldeStr);
            } catch (NumberFormatException ex) {
                afficher("Solde invalide.");
                return;
            }
        }

        carte = new Carte(nom, prenom, contrat, solde);
        borne.insererCarte(carte);
    }

    private void charger() {
        if (carte == null) {
            afficher("Insérez d'abord une carte.");
            return;
        }
        try {
            if (carte.estUnForfait()) {
                carte.decompterCharge(5f);
            }
            borne.charger(2);
        } catch (Exception e) {
            afficher("Erreur : " + e.getMessage());
        }
    }

    private void afficher(String texte) {
        console.append(texte + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ElectrAutoSwingApp().setVisible(true));
    }
}
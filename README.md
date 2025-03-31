# ElectrAuto

Application de gestion des bornes de recharge électrique.

## Prérequis

- Java 11 ou supérieur
- Maven 3.6 ou supérieur

## Installation

1. Cloner le repository
2. Compiler le projet :
```bash
mvn clean package
```

## Utilisation

Pour lancer l'application :
```bash
java -jar target/electrauto-1.0-SNAPSHOT-jar-with-dependencies.jar
```

## Fonctionnalités

- Gestion des cartes de recharge (forfait et abonnement)
- Gestion des bornes de recharge
- Gestion des batteries
- Interface graphique Swing
- Persistance des données en JSON

## Structure du projet

- `src/main/java/dmelectrauto/` : Code source principal
- `src/test/java/dmelectrauto/test/` : Tests unitaires
- `docs/` : Documentation (diagrammes UML, etc.)
- `target/` : Fichiers compilés et JAR

## Tests

Pour exécuter les tests :
```bash
mvn test
```

## Documentation

Le diagramme de classe UML est disponible dans le dossier `docs/`. 
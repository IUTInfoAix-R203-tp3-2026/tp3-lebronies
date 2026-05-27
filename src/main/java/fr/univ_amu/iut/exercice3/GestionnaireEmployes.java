package fr.univ_amu.iut.exercice3;

import java.util.ArrayList;
import java.util.List;

/// Kata 3 - Gestionnaire d'employés.
///
/// Opérations simples sur une collection d'[Employe] : filtrer, agréger, trier.
/// Kata idéal pour pratiquer les collections Java et la pensée "fonctions pures"
/// (les méthodes ne modifient pas la liste initiale).
public class GestionnaireEmployes {

  private final List<Employe> employes;

  public GestionnaireEmployes(List<Employe> employes) {
    this.employes = List.copyOf(employes);
  }

  /// Retourne la liste des employés âgés d'au moins 18 ans, dans
  /// l'ordre d'origine.
  public List<Employe> getMajeurs() {
    List<Employe> majeurs = new ArrayList<>();
    // TODO kata 3 : filtrer employes pour ne garder que ceux d'age >= 18.
    for (Employe employe : employes) {
      if (employe.age() >= 18) {
        majeurs.add(employe);
      }
    }
    return majeurs;
  }

  /// Retourne l'âge moyen des employés majeurs, ou `0.0` s'il n'y a aucun majeur
  /// (pour éviter une division par zéro).
  public double ageMoyenDesMajeurs() {
    double moyenne = 0.0;
    // TODO kata 3 : additionner les âges des majeurs puis diviser par leur nombre.
    // Attention au cas "aucun majeur" → 0.0 (pas NaN).
    List<Employe> majeurs = getMajeurs();
    if (!majeurs.isEmpty()) {
      int sommeAges = 0;
      for (Employe employe : majeurs) {
        sommeAges += employe.age();
      }
      moyenne = (double) sommeAges / majeurs.size();
    }
    return moyenne;
  }

  /// Retourne les employés triés par ordre alphabétique du nom (ne modifie pas
  /// la liste).
  public List<Employe> parOrdreAlphabetique() {
    List<Employe> tries = new ArrayList<>(employes);
    // TODO kata 3 : trier tries avec un Comparator sur le nom.
    tries.sort((e1, e2) -> e1.nom().compareTo(e2.nom()));
    return tries;
  }

  /// Retourne les employés triés par ordre croissant d'âge (ne modifie pas
  /// la liste).
  public List<Employe> parAgeCroissant() {
    List<Employe> tries = new ArrayList<>(employes);
    // TODO kata 3 : trier tries avec un Comparator sur l'âge.
    tries.sort((e1, e2) -> Integer.compare(e1.age(), e2.age()));
    return tries;
  }
}

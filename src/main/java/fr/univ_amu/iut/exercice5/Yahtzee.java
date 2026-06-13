package fr.univ_amu.iut.exercice5;

/// Kata 5 - Yahtzee scoring.
///
/// Chaque méthode statique calcule le score selon sa règle pour un lancer de 5
/// dés. Le kata est un classique du refactoring (Yatzy Refactoring Kata) : les
/// solutions "brute-force" fonctionnent vite, mais vous verrez que certaines
/// règles partagent une structure (compter les occurrences de chaque face) qu'on
/// peut factoriser.
///
/// Conseil : implémentez chaque méthode en TDD puis, quand plusieurs sont
/// vertes, cherchez une factorisation. C'est un bon entraînement au
/// TP4 (Refactoring).
public class Yahtzee {

  private Yahtzee() {}

  private static int[] compterOccurrences(int... des) {
    int[] occurrences = new int[7];
    for (int de : des) {
      occurrences[de]++;
    }
    return occurrences;
  }

  /// Somme des 5 dés (quelle que soit leur valeur).
  public static int chance(int d1, int d2, int d3, int d4, int d5) {
    // TODO kata 5 : sommer les 5 dés.
    return d1 + d2 + d3 + d4 + d5;
  }

  /// 50 points si les 5 dés sont identiques, 0 sinon.
  public static int yahtzee(int d1, int d2, int d3, int d4, int d5) {
    int[] occurrences = compterOccurrences(d1, d2, d3, d4, d5);
    for (int count : occurrences) {
      if (count == 5) return 50;
    }
    return 0;
  }

  // TODO kata 5 : 50 si d1 == d2 == d3 == d4 == d5, 0 sinon.

  /// Somme des dés qui montrent la face demandée (utilisé pour ones, twos,
  /// ..., sixes).
  public static int nombres(int face, int[] des) {
    int[] occurrences = compterOccurrences(des);
    // TODO kata 5 : additionner les dés qui valent 'face'.
    return occurrences[face] * face;
  }

  /// Valeur de la paire la plus haute (2 * valeur). 0 si aucune paire.
  public static int paire(int d1, int d2, int d3, int d4, int d5) {
    int[] occurrences = compterOccurrences(d1, d2, d3, d4, d5);
    // TODO kata 5 : compter les occurrences de chaque face (1..6) puis
    // renvoyer 2 * la plus haute face qui apparaît au moins 2 fois.
    for (int i = 6; i >= 1; i--) {
      if (occurrences[i] >= 2) return i * 2;
    }
    return 0;
  }

  /// Somme de deux paires de valeurs différentes. 0 sinon.
  public static int deuxPaires(int d1, int d2, int d3, int d4, int d5) {
    int[] occurrences = compterOccurrences(d1, d2, d3, d4, d5);
    int pairesTrouvees = 0;
    int score = 0;
    // TODO kata 5 : trouver deux faces distinctes qui apparaissent >= 2 fois.
    for (int i = 6; i >= 1; i--) {
      if (occurrences[i] >= 2) {
        pairesTrouvees++;
        score += i * 2;
      }
    }
    return (pairesTrouvees == 2) ? score : 0;
  }

  /// Somme de 3 dés identiques, 0 sinon.
  public static int brelan(int d1, int d2, int d3, int d4, int d5) {
    int[] occurrences = compterOccurrences(d1, d2, d3, d4, d5);
    // TODO kata 5 : trouver la face qui apparaît au moins 3 fois, renvoyer 3 *
    // face.
    for (int i = 6; i >= 1; i--) {
      if (occurrences[i] >= 3) return i * 3;
    }
    return 0;
  }

  /// 15 si les 5 dés sont exactement 1-2-3-4-5, 0 sinon.
  public static int petiteSuite(int d1, int d2, int d3, int d4, int d5) {
    int[] occ = compterOccurrences(d1, d2, d3, d4, d5);
    // TODO kata 5 : vérifier que chaque face de 1 à 5 apparaît exactement une fois.
    for (int i = 1; i <= 5; i++) {
      if (occ[i] != 1) return 0;
    }
    return 15;
  }

  /// 20 si les 5 dés sont exactement 2-3-4-5-6, 0 sinon.
  public static int grandeSuite(int d1, int d2, int d3, int d4, int d5) {
    int[] occ = compterOccurrences(d1, d2, d3, d4, d5);
    for (int i = 2; i <= 6; i++) {
      if (occ[i] != 1) return 0;
    }
    return 20;
  }

  /// Somme des 5 dés si on a un brelan plus une paire d'une autre valeur,
  /// 0 sinon.
  public static int full(int d1, int d2, int d3, int d4, int d5) {
    int[] occurrences = compterOccurrences(d1, d2, d3, d4, d5);
    boolean aBrelan = false;
    boolean aPaire = false;
    // TODO kata 5 : un brelan (une face 3 fois) + une paire (autre face 2 fois).
    for (int i = 1; i <= 6; i++) {
      if (occurrences[i] == 3) aBrelan = true;
      if (occurrences[i] == 2) aPaire = true;
    }

    if (aBrelan && aPaire) {
      return chance(d1, d2, d3, d4, d5); // On réutilise chance() pour sommer tous les dés
    }
    return 0;
  }
}

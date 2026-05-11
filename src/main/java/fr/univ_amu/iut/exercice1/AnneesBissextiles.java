package fr.univ_amu.iut.exercice1;

/// Kata 1 - Années bissextiles.
///
/// Premier kata en pair programming. Kata court (~10-15 min) parfait pour poser le rythme
/// driver/navigator et les swaps de 7 minutes.
public class AnneesBissextiles {

  /// Détermine si une année est bissextile selon les règles du calendrier grégorien.
  ///
  /// @param annee année à tester (positive)
  /// @return `true` si l'année est bissextile, `false` sinon
  public static boolean estBissextile(int annee) {
    // TODO kata 1 : découvrir progressivement les 3 règles en activant
    // les tests un par un. Commencez par un simple modulo 4, puis
    // ajoutez les exceptions à chaque fois qu'un nouveau test échoue.
    boolean bissextile = false;

    if ((annee % 400 == 0) || ((annee % 4 == 0) && (annee % 100 != 0))) bissextile = true;
    return bissextile;
  }
}

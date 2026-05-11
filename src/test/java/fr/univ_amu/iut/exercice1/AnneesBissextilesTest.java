package fr.univ_amu.iut.exercice1;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Kata 1 - Années bissextiles.
 *
 * <p>Les tests sont rangés pour vous faire découvrir les règles <b>une par une</b> :
 *
 * <ol>
 *   <li>Tests 1 à 3 : poser le terrain. Année non bissextile - fake it {@code return false} passe.
 *   <li>Tests 4 à 6 : force à introduire {@code annee % 4 == 0}.
 *   <li>Tests 7 à 8 : régression sur des années divisibles par 4.
 *   <li>Tests 9 à 10 : force à introduire l'exception {@code annee % 100 == 0}.
 *   <li>Tests 11 à 12 : force à introduire l'exception de l'exception {@code annee % 400 == 0}.
 *   <li>Tests 13 à 14 : régression sur cas complexes.
 * </ol>
 *
 * <p>Activez-les un à un, en binôme, avec un swap driver / navigator tous les 7 minutes. Si tous
 * les tests deviennent verts d'un coup quand vous implémentez la règle, c'est que vous avez sauté
 * des baby steps.
 */
class AnneesBissextilesTest {

  // ========= Tests 1 à 3 : poser le terrain =========

  @Test
  void l_annee_2021_n_est_pas_bissextile() {
    assertThat(AnneesBissextiles.estBissextile(2021)).isFalse();
  }

  @Test
  void l_annee_2023_n_est_pas_bissextile() {
    assertThat(AnneesBissextiles.estBissextile(2023)).isFalse();
  }

  @Test
  void l_annee_2025_n_est_pas_bissextile() {
    assertThat(AnneesBissextiles.estBissextile(2025)).isFalse();
  }

  // ========= Tests 4 à 6 : introduction de la règle "divisible par 4" =========

  @Test
  void l_annee_2020_est_bissextile() {
    assertThat(AnneesBissextiles.estBissextile(2020)).isTrue();
  }

  @Test
  void l_annee_2024_est_bissextile() {
    assertThat(AnneesBissextiles.estBissextile(2024)).isTrue();
  }

  @Test
  void l_annee_2028_est_bissextile() {
    assertThat(AnneesBissextiles.estBissextile(2028)).isTrue();
  }

  // ========= Tests 7 à 8 : régression, années divisibles par 4 dans d'autres siècles =========

  @Test
  void l_annee_1996_est_bissextile() {
    assertThat(AnneesBissextiles.estBissextile(1996)).isTrue();
  }

  @Test
  void l_annee_1804_est_bissextile() {
    assertThat(AnneesBissextiles.estBissextile(1804)).isTrue();
  }

  // ========= Tests 9 à 10 : exception "divisible par 100" =========

  @Test
  void l_annee_1900_n_est_pas_bissextile() {
    // 1900 est divisible par 4 ET par 100 : l'exception de 100 l'emporte.
    assertThat(AnneesBissextiles.estBissextile(1900)).isFalse();
  }

  @Test
  void l_annee_1700_n_est_pas_bissextile() {
    assertThat(AnneesBissextiles.estBissextile(1700)).isFalse();
  }

  // ========= Tests 11 à 12 : exception de l'exception "divisible par 400" =========

  @Test
  void l_annee_2000_est_bissextile() {
    // 2000 est divisible par 4, par 100 ET par 400 : la règle 400 l'emporte.
    assertThat(AnneesBissextiles.estBissextile(2000)).isTrue();
  }

  @Test
  void l_annee_1600_est_bissextile() {
    assertThat(AnneesBissextiles.estBissextile(1600)).isTrue();
  }

  // ========= Tests 13 à 14 : régression, cas plus éloignés =========

  @Test
  void l_annee_2100_n_est_pas_bissextile() {
    assertThat(AnneesBissextiles.estBissextile(2100)).isFalse();
  }

  @Test
  void l_annee_2400_est_bissextile() {
    assertThat(AnneesBissextiles.estBissextile(2400)).isTrue();
  }
}

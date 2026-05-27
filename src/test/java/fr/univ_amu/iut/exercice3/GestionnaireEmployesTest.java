package fr.univ_amu.iut.exercice3;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Kata 3 - Gestionnaire d'employés.
 *
 * <p>Progression TDD :
 *
 * <ol>
 *   <li>Filtre des majeurs (âge &gt;= 18)
 *   <li>Agrégations (moyenne d'âge)
 *   <li>Tri (alphabétique, par âge)
 * </ol>
 */
class GestionnaireEmployesTest {

  @Test
  void la_liste_vide_n_a_aucun_majeur() {
    GestionnaireEmployes g = new GestionnaireEmployes(List.of());
    assertThat(g.getMajeurs()).isEmpty();
  }

  @Test
  void le_gestionnaire_filtre_les_majeurs() {
    GestionnaireEmployes g =
        new GestionnaireEmployes(
            List.of(new Employe("Alice", 25), new Employe("Bob", 15), new Employe("Charlie", 40)));
    assertThat(g.getMajeurs()).extracting(Employe::nom).containsExactly("Alice", "Charlie");
  }

  @Test
  void la_personne_de_18_ans_est_majeure() {
    GestionnaireEmployes g = new GestionnaireEmployes(List.of(new Employe("Dana", 18)));
    assertThat(g.getMajeurs()).hasSize(1);
  }

  @Test
  void le_gestionnaire_calcule_l_age_moyen_des_majeurs() {
    GestionnaireEmployes g =
        new GestionnaireEmployes(
            List.of(new Employe("Alice", 20), new Employe("Bob", 30), new Employe("Charlie", 40)));
    assertThat(g.ageMoyenDesMajeurs()).isEqualTo(30.0);
  }

  @Test
  void l_age_moyen_sans_majeur_vaut_0() {
    GestionnaireEmployes g =
        new GestionnaireEmployes(List.of(new Employe("Alice", 10), new Employe("Bob", 12)));
    assertThat(g.ageMoyenDesMajeurs()).isEqualTo(0.0);
  }

  @Test
  void le_gestionnaire_trie_alphabetiquement() {
    GestionnaireEmployes g =
        new GestionnaireEmployes(
            List.of(new Employe("Charlie", 40), new Employe("Alice", 25), new Employe("Bob", 30)));
    assertThat(g.parOrdreAlphabetique())
        .extracting(Employe::nom)
        .containsExactly("Alice", "Bob", "Charlie");
  }

  @Test
  void le_gestionnaire_trie_par_age_croissant() {
    GestionnaireEmployes g =
        new GestionnaireEmployes(
            List.of(new Employe("Charlie", 40), new Employe("Alice", 25), new Employe("Bob", 30)));
    assertThat(g.parAgeCroissant()).extracting(Employe::age).containsExactly(25, 30, 40);
  }
}

package fr.univ_amu.iut.exercice5;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Kata 5 - Yahtzee scoring.
 *
 * <p>Les tests sont organisés par catégorie (chance, yahtzee, nombres...) et par catégorie on a
 * typiquement 2 à 3 cas : un cas positif, un cas limite, parfois un cas négatif. Cette densité est
 * essentielle pour empêcher les solutions triviales (ex : pour {@code paire}, un simple {@code
 * return 2*d1} passerait un seul test mais pas trois).
 */
class YahtzeeTest {

  // ========= chance =========

  @Test
  void la_chance_donne_la_somme_des_des() {
    assertThat(Yahtzee.chance(1, 1, 3, 3, 6)).isEqualTo(14);
  }

  @Test
  void la_chance_avec_cinq_uns_vaut_5() {
    assertThat(Yahtzee.chance(1, 1, 1, 1, 1)).isEqualTo(5);
  }

  @Test
  void la_chance_avec_cinq_six_vaut_30() {
    assertThat(Yahtzee.chance(6, 6, 6, 6, 6)).isEqualTo(30);
  }

  // ========= yahtzee =========

  @Test
  void le_yahtzee_avec_cinq_quatre_vaut_50() {
    assertThat(Yahtzee.yahtzee(4, 4, 4, 4, 4)).isEqualTo(50);
  }

  @Test
  void le_yahtzee_avec_cinq_uns_vaut_50() {
    assertThat(Yahtzee.yahtzee(1, 1, 1, 1, 1)).isEqualTo(50);
  }

  @Test
  void le_yahtzee_avec_quatre_uns_et_un_deux_vaut_0() {
    assertThat(Yahtzee.yahtzee(1, 1, 1, 1, 2)).isZero();
  }

  @Test
  void le_yahtzee_avec_cinq_des_tous_differents_vaut_0() {
    assertThat(Yahtzee.yahtzee(1, 2, 3, 4, 5)).isZero();
  }

  // ========= nombres =========

  @Test
  void le_decompte_compte_trois_uns() {
    assertThat(Yahtzee.nombres(1, new int[] {1, 2, 1, 4, 1})).isEqualTo(3);
  }

  @Test
  void le_decompte_compte_deux_six() {
    assertThat(Yahtzee.nombres(6, new int[] {1, 6, 3, 6, 2})).isEqualTo(12);
  }

  @Test
  void le_decompte_retourne_zero_si_face_absente() {
    assertThat(Yahtzee.nombres(6, new int[] {1, 2, 3, 4, 5})).isZero();
  }

  // ========= paire =========

  @Test
  void la_paire_avec_deux_trois_vaut_6() {
    assertThat(Yahtzee.paire(3, 3, 1, 2, 4)).isEqualTo(6);
  }

  @Test
  void la_paire_retourne_la_plus_haute() {
    // 3-3 et 4-4 : on prend la plus haute
    assertThat(Yahtzee.paire(3, 3, 4, 4, 1)).isEqualTo(8);
  }

  @Test
  void la_paire_avec_quatre_six_vaut_12() {
    assertThat(Yahtzee.paire(6, 6, 6, 6, 1)).isEqualTo(12);
  }

  @Test
  void la_paire_sans_aucune_paire_vaut_0() {
    assertThat(Yahtzee.paire(1, 2, 3, 4, 5)).isZero();
  }

  // ========= deuxPaires =========

  @Test
  void les_deux_paires_donnent_la_somme_des_deux() {
    assertThat(Yahtzee.deuxPaires(1, 1, 2, 3, 3)).isEqualTo(8); // 1+1 + 3+3
  }

  @Test
  void les_deux_paires_prennent_les_deux_plus_hautes() {
    assertThat(Yahtzee.deuxPaires(2, 2, 5, 5, 6)).isEqualTo(14);
  }

  @Test
  void les_deux_paires_refusent_une_seule_paire() {
    assertThat(Yahtzee.deuxPaires(1, 1, 2, 3, 4)).isZero();
  }

  @Test
  void les_deux_paires_refusent_un_brelan_seul() {
    assertThat(Yahtzee.deuxPaires(3, 3, 3, 1, 2)).isZero();
  }

  // ========= brelan =========

  @Test
  void le_brelan_avec_trois_des_donne_la_somme() {
    assertThat(Yahtzee.brelan(3, 3, 3, 4, 5)).isEqualTo(9);
  }

  @Test
  void le_brelan_avec_cinq_des_identiques_prend_trois() {
    // 5 dés identiques : la catégorie "brelan" ne compte que 3 de la valeur
    assertThat(Yahtzee.brelan(5, 5, 5, 5, 5)).isEqualTo(15);
  }

  @Test
  void le_brelan_sans_aucun_brelan_vaut_0() {
    assertThat(Yahtzee.brelan(1, 1, 2, 2, 5)).isZero();
  }

  // ========= petiteSuite =========

  @Test
  void la_petite_suite_1_a_5_vaut_15() {
    assertThat(Yahtzee.petiteSuite(1, 2, 3, 4, 5)).isEqualTo(15);
  }

  @Test
  void la_petite_suite_dans_un_ordre_quelconque_vaut_15() {
    assertThat(Yahtzee.petiteSuite(3, 1, 5, 4, 2)).isEqualTo(15);
  }

  @Test
  void la_petite_suite_avec_un_de_double_vaut_0() {
    assertThat(Yahtzee.petiteSuite(1, 2, 2, 4, 5)).isZero();
  }

  @Test
  void la_petite_suite_de_2_a_6_vaut_0() {
    // C'est une grande suite, pas une petite
    assertThat(Yahtzee.petiteSuite(2, 3, 4, 5, 6)).isZero();
  }

  // ========= grandeSuite =========

  @Test
  void la_grande_suite_2_a_6_vaut_20() {
    assertThat(Yahtzee.grandeSuite(2, 3, 4, 5, 6)).isEqualTo(20);
  }

  @Test
  void la_grande_suite_de_1_a_5_vaut_0() {
    // C'est une petite suite, pas une grande
    assertThat(Yahtzee.grandeSuite(1, 2, 3, 4, 5)).isZero();
  }

  @Test
  void la_grande_suite_avec_un_de_double_vaut_0() {
    assertThat(Yahtzee.grandeSuite(2, 3, 3, 5, 6)).isZero();
  }

  // ========= full =========

  @Test
  void le_full_avec_brelan_de_2_et_paire_de_6_donne_la_somme_des_5_des() {
    assertThat(Yahtzee.full(2, 2, 2, 6, 6)).isEqualTo(18);
  }

  @Test
  void le_full_avec_brelan_de_5_et_paire_de_3_donne_la_somme_des_5_des() {
    assertThat(Yahtzee.full(5, 5, 5, 3, 3)).isEqualTo(21);
  }

  @Test
  void le_full_sans_brelan_vaut_0() {
    assertThat(Yahtzee.full(2, 2, 3, 4, 5)).isZero();
  }

  @Test
  void le_full_sans_paire_vaut_0() {
    assertThat(Yahtzee.full(3, 3, 3, 4, 5)).isZero();
  }
}

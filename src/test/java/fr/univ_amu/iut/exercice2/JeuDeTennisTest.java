package fr.univ_amu.iut.exercice2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Kata 2 - Tennis scoring.
 *
 * <p>Progression TDD conçue pour du pair programming ping-pong : chaque test ajoute exactement un
 * petit élément à l'implémentation. Résistez à la tentation d'écrire la machine à états complète
 * dès le premier "0-0".
 *
 * <ul>
 *   <li>Tests 1 à 6 : les comptages simples avant qu'un joueur atteigne 3 points
 *   <li>Tests 7 à 11 : les libellés "15", "30", "40" pour le premier et le second joueur
 *   <li>Tests 12 à 14 : la gestion des égalités puis Deuce
 *   <li>Tests 15 à 18 : Advantage (aller / retour à Deuce)
 *   <li>Tests 19 à 22 : Win (plusieurs scénarios)
 * </ul>
 */
class JeuDeTennisTest {

  private JeuDeTennis jeu;

  @BeforeEach
  void setUp() {
    jeu = new JeuDeTennis("Alice", "Bob");
  }

  // ========= Score initial =========

  @Test
  void le_score_initial_est_0_0() {
    assertThat(jeu.getScore()).isEqualTo("0-0");
  }

  // ========= Points simples d'Alice =========

  @Test
  void apres_un_point_d_alice_le_score_est_15_0() {
    jeu.marquerPoint("Alice");
    assertThat(jeu.getScore()).isEqualTo("15-0");
  }

  @Test
  void apres_deux_points_d_alice_le_score_est_30_0() {
    jeu.marquerPoint("Alice");
    jeu.marquerPoint("Alice");
    assertThat(jeu.getScore()).isEqualTo("30-0");
  }

  @Test
  void apres_trois_points_d_alice_le_score_est_40_0() {
    for (int i = 0; i < 3; i++) jeu.marquerPoint("Alice");
    assertThat(jeu.getScore()).isEqualTo("40-0");
  }

  // ========= Points simples de Bob =========

  @Test
  void apres_un_point_de_bob_le_score_est_0_15() {
    jeu.marquerPoint("Bob");
    assertThat(jeu.getScore()).isEqualTo("0-15");
  }

  @Test
  void apres_deux_points_de_bob_le_score_est_0_30() {
    jeu.marquerPoint("Bob");
    jeu.marquerPoint("Bob");
    assertThat(jeu.getScore()).isEqualTo("0-30");
  }

  @Test
  void apres_trois_points_de_bob_le_score_est_0_40() {
    for (int i = 0; i < 3; i++) jeu.marquerPoint("Bob");
    assertThat(jeu.getScore()).isEqualTo("0-40");
  }

  // ========= Mélanges, pour exercer la matrice =========

  @Test
  void apres_un_point_chacun_le_score_est_15_15() {
    jeu.marquerPoint("Alice");
    jeu.marquerPoint("Bob");
    assertThat(jeu.getScore()).isEqualTo("15-15");
  }

  @Test
  void apres_deux_points_d_alice_et_un_point_de_bob_le_score_est_30_15() {
    jeu.marquerPoint("Alice");
    jeu.marquerPoint("Alice");
    jeu.marquerPoint("Bob");
    assertThat(jeu.getScore()).isEqualTo("30-15");
  }

  @Test
  void apres_trois_points_d_alice_et_un_point_de_bob_le_score_est_40_15() {
    for (int i = 0; i < 3; i++) jeu.marquerPoint("Alice");
    jeu.marquerPoint("Bob");
    assertThat(jeu.getScore()).isEqualTo("40-15");
  }

  @Test
  void apres_trois_points_d_alice_et_deux_points_de_bob_le_score_est_40_30() {
    for (int i = 0; i < 3; i++) jeu.marquerPoint("Alice");
    for (int i = 0; i < 2; i++) jeu.marquerPoint("Bob");
    assertThat(jeu.getScore()).isEqualTo("40-30");
  }

  @Test
  void apres_deux_points_chacun_le_score_est_30_30() {
    for (int i = 0; i < 2; i++) jeu.marquerPoint("Alice");
    for (int i = 0; i < 2; i++) jeu.marquerPoint("Bob");
    assertThat(jeu.getScore()).isEqualTo("30-30");
  }

  // ========= Deuce =========

  @Test
  void apres_trois_points_chacun_le_score_est_egalite() {
    for (int i = 0; i < 3; i++) {
      jeu.marquerPoint("Alice");
      jeu.marquerPoint("Bob");
    }
    assertThat(jeu.getScore()).isEqualTo("Égalité");
  }

  // ========= Advantage =========

  @Test
  void apres_egalite_et_un_point_d_alice_le_score_est_avantage_alice() {
    for (int i = 0; i < 3; i++) {
      jeu.marquerPoint("Alice");
      jeu.marquerPoint("Bob");
    }
    jeu.marquerPoint("Alice");
    assertThat(jeu.getScore()).isEqualTo("Avantage Alice");
  }

  @Test
  void apres_egalite_et_un_point_de_bob_le_score_est_avantage_bob() {
    for (int i = 0; i < 3; i++) {
      jeu.marquerPoint("Alice");
      jeu.marquerPoint("Bob");
    }
    jeu.marquerPoint("Bob");
    assertThat(jeu.getScore()).isEqualTo("Avantage Bob");
  }

  @Test
  void apres_avantage_alice_et_un_point_de_bob_le_score_revient_a_egalite() {
    for (int i = 0; i < 3; i++) {
      jeu.marquerPoint("Alice");
      jeu.marquerPoint("Bob");
    }
    jeu.marquerPoint("Alice");
    jeu.marquerPoint("Bob");
    assertThat(jeu.getScore()).isEqualTo("Égalité");
  }

  // ========= Win =========

  @Test
  void apres_avantage_alice_et_un_point_d_alice_alice_a_gagne() {
    for (int i = 0; i < 3; i++) {
      jeu.marquerPoint("Alice");
      jeu.marquerPoint("Bob");
    }
    jeu.marquerPoint("Alice");
    jeu.marquerPoint("Alice");
    assertThat(jeu.getScore()).isEqualTo("Jeu pour Alice");
  }

  @Test
  void apres_quatre_points_d_alice_alice_a_gagne() {
    for (int i = 0; i < 4; i++) jeu.marquerPoint("Alice");
    assertThat(jeu.getScore()).isEqualTo("Jeu pour Alice");
  }

  @Test
  void apres_quatre_points_de_bob_bob_a_gagne() {
    for (int i = 0; i < 4; i++) jeu.marquerPoint("Bob");
    assertThat(jeu.getScore()).isEqualTo("Jeu pour Bob");
  }

  @Test
  void apres_quatre_points_d_alice_et_un_point_de_bob_alice_a_gagne() {
    for (int i = 0; i < 4; i++) {
      jeu.marquerPoint("Alice");
      if (i == 1) jeu.marquerPoint("Bob");
    }
    assertThat(jeu.getScore()).isEqualTo("Jeu pour Alice");
  }

  @Test
  void apres_quatre_points_d_alice_et_deux_points_de_bob_alice_a_gagne() {
    for (int i = 0; i < 4; i++) jeu.marquerPoint("Alice");
    jeu.marquerPoint("Bob");
    jeu.marquerPoint("Bob");
    assertThat(jeu.getScore()).isEqualTo("Jeu pour Alice");
  }
}

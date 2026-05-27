package fr.univ_amu.iut.exercice4;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Kata 4 - Pagination.
 *
 * <p>Affichage compact d'une barre de pagination. Le kata classique impose 7 "slots" lisibles : on
 * montre les pages au plus proches de la page courante et on remplit les "trous" par {@code ...}.
 *
 * <p>Règles :
 *
 * <ul>
 *   <li>La page courante est affichée entre parenthèses
 *   <li>Les pages sont séparées par un espace
 *   <li>Si le nombre total de pages est <b>&lt;= 7</b>, on les affiche toutes
 *   <li>Sinon, on affiche : page {@code 1}, page {@code courant-1}, page {@code courant}, page
 *       {@code courant+1}, page {@code total}. Entre deux numéros consécutifs dans cette liste, si
 *       l'écart est &gt; 1 on insère {@code "..."}.
 * </ul>
 */
class PaginationTest {

  @Test
  void la_pagination_d_une_seule_page_n_affiche_qu_elle_meme() {
    assertThat(new Pagination(1, 1).afficher()).isEqualTo("(1)");
  }

  @Test
  void la_pagination_de_5_pages_avec_courant_au_milieu_affiche_toutes_les_pages() {
    assertThat(new Pagination(3, 5).afficher()).isEqualTo("1 2 (3) 4 5");
  }

  @Test
  void la_pagination_de_5_pages_avec_courant_au_debut_affiche_toutes_les_pages() {
    assertThat(new Pagination(1, 5).afficher()).isEqualTo("(1) 2 3 4 5");
  }

  @Test
  void la_pagination_de_5_pages_avec_courant_a_la_fin_affiche_toutes_les_pages() {
    assertThat(new Pagination(5, 5).afficher()).isEqualTo("1 2 3 4 (5)");
  }

  @Test
  void la_pagination_de_7_pages_affiche_toutes_les_pages() {
    assertThat(new Pagination(4, 7).afficher()).isEqualTo("1 2 3 (4) 5 6 7");
  }

  @Test
  void la_pagination_de_15_pages_avec_courant_au_milieu_affiche_des_ellipses_des_deux_cotes() {
    assertThat(new Pagination(7, 15).afficher()).isEqualTo("1 ... 6 (7) 8 ... 15");
  }

  @Test
  void la_pagination_de_15_pages_avec_courant_pres_du_debut_n_affiche_qu_une_ellipse_a_droite() {
    assertThat(new Pagination(3, 15).afficher()).isEqualTo("1 2 (3) 4 ... 15");
  }

  @Test
  void la_pagination_de_15_pages_avec_courant_pres_de_la_fin_n_affiche_qu_une_ellipse_a_gauche() {
    assertThat(new Pagination(13, 15).afficher()).isEqualTo("1 ... 12 (13) 14 15");
  }

  @Test
  void la_pagination_de_15_pages_avec_courant_en_seconde_position() {
    assertThat(new Pagination(2, 15).afficher()).isEqualTo("1 (2) 3 ... 15");
  }

  @Test
  void la_pagination_de_15_pages_avec_courant_en_avant_derniere_position() {
    assertThat(new Pagination(14, 15).afficher()).isEqualTo("1 ... 13 (14) 15");
  }
}

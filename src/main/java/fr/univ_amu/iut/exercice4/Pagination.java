package fr.univ_amu.iut.exercice4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/// Kata 4 - Pagination.
///
/// Kata algorithmique avec beaucoup de cas limites. Idéal pour pratiquer la
/// **discipline TDD** : on active les tests dans l'ordre (du plus simple au plus
/// complexe) et on résiste à la tentation d'anticiper.
public class Pagination {

  private final int courant;
  private final int total;

  public Pagination(int courant, int total) {
    this.courant = courant;
    this.total = total;
  }

  /// Retourne la représentation textuelle de la barre de pagination.
  ///
  /// Format : pages séparées par des espaces, page courante entre parenthèses,
  /// `...` pour combler les trous quand il y a plus de 7 pages au total.
  public String afficher() {
    StringBuilder sortie = new StringBuilder();
    int precedent = -1;

    for (int page : pagesAAfficher()) {
      if (precedent != -1) {
        sortie.append(separateurEntre(precedent, page));
      }
      sortie.append(formatPage(page));
      precedent = page;
    }

    return sortie.toString();
  }

  private List<Integer> pagesAAfficher() {
    if (total <= 7) {
      return IntStream.rangeClosed(1, total).boxed().toList();
    }

    return Arrays.asList(1, courant - 1, courant, courant + 1, total).stream()
        .filter(page -> page >= 1 && page <= total)
        .distinct()
        .toList();
  }

  private String formatPage(int page) {
    return page == courant ? "(" + page + ")" : String.valueOf(page);
  }

  private String separateurEntre(int precedent, int page) {
    return page == precedent + 1 ? " " : " ... ";
  }
}

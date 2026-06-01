# <img src=".github/assets/logo.png" alt="class logo" class="logo" width="120"/> R2.03 - Qualité de développement

### IUT d'Aix-Marseille - Département Informatique Aix-en-Provence

- **Ressource :** [Syllabus R2.03](https://github.com/IUTInfoAix-R203/syllabus) (compétences, calendrier, évaluations, ressources détaillées)

- **Équipe pédagogique :**

  - [Sébastien Nedjar](mailto:sebastien.nedjar@univ-amu.fr) - responsable du module
  - [Sophie Nabitz](mailto:sophie.nabitz@univ-avignon.fr)
  - [Leïla Sakli Miled](mailto:leila.SAKLI@univ-amu.fr)

- **Besoin d'aide ?**
    - Consulter et/ou créer des [issues](https://github.com/IUTInfoAix-R203/tp3/issues)
    - [Email](mailto:sebastien.nedjar@univ-amu.fr) pour toute question


## TP3 - Kata et pair programming : le TDD à deux

## Objectifs de la séance

### Ce que vous saurez faire à la fin de cette séance

Les exercices de ce TP sont organisés en progression. Cette progression suit la **taxonomie de Bloom**, un modèle pédagogique qui décrit les niveaux de maîtrise d'un savoir-faire -du plus simple (comprendre un concept) au plus complexe (créer une application complète).

| Niveau Bloom | Kata | Vous serez capable de... | Compétence BUT |
|---|---|---|---|
| **Comprendre** | 1 et 2 | Appliquer les règles du pair programming (driver / navigator, swap toutes les 7 min), pratiquer la triangulation sur des règles à plusieurs exceptions (années bissextiles) et sur une machine à états (scoring tennis) | AC11.03 |
| **Appliquer** | 3 et 4 | Manipuler les collections Java (filtrer, agréger, trier) en TDD, construire un algorithme à partir de ses cas limites (pagination compacte avec ellipses) | AC11.02, AC11.03 |
| **Analyser / Créer** | 5 | Décomposer un scoring multi-règles (Yahtzee, 9 catégories) en méthodes testables individuellement, repérer les factorisations possibles et résister à la tentation de factoriser avant d'avoir tous les tests verts | AC11.02, AC11.03 |
| **Créer** *(bonus)* | 6 | Concevoir une machine à états **comportementale** pilotée par le temps (ticks) : priorités, invariants et transitions progressives - implémentée par petits pas sans pré-design | AC11.02, AC11.03 |

**Tout au long du TP** vous prolongez le workflow Git du TP1 et la discipline TDD du TP2, dans un cadre désormais **à deux** : branche partagée par le binôme, Pull Request co-signée, revue croisée par le binôme voisin. C'est la mise en œuvre de **AC15.02 - outils de gestion de projet**, mais aussi l'apprentissage d'une compétence souvent invisible et pourtant décisive en équipe : **savoir coder en expliquant ce qu'on fait à quelqu'un d'autre**.

### Pourquoi cette démarche ?

Un **kata** (型, "forme" en japonais) est un exercice de programmation court que l'on répète pour entretenir un geste technique, comme un sportif qui refait les mêmes enchaînements à chaque entraînement. Les kata de ce TP sont tous des problèmes classiques (années bissextiles, tennis, Yahtzee...) : vous les rencontrerez à nouveau en entretien d'embauche, en auto-entraînement, ou lors d'un *coding dojo* avec vos futurs collègues.

Le **pair programming** consiste à coder à deux sur un seul poste : un **driver** tient le clavier et écrit le code, un **navigator** regarde, réfléchit à la stratégie et questionne. On alterne les rôles toutes les 7 minutes environ (le timer est un allié). Cette pratique :

- oblige à **verbaliser** son raisonnement, ce qui révèle les raccourcis tacites qu'on prenait seul
- construit une **compréhension partagée** du code, précieuse en équipe
- divise par deux le nombre de bugs introduits (étude Laurie Williams, UNC 2001)

Variante avancée : le **ping-pong TDD**. Le navigator écrit le prochain test ; le driver implémente juste ce qu'il faut pour le faire passer ; puis on échange. Impossible de dévier du TDD dans ce rythme.

Copilot Chat reste votre **tuteur** pour les deux : il explique, oriente vers la documentation, et ne donne du code qu'en dernier recours. Le binôme discute avec Copilot comme un oracle commun.

### Documentation de référence

- [Java 25 API Documentation](https://docs.oracle.com/en/java/javase/25/docs/api/)
- [JUnit 6 User Guide](https://docs.junit.org/current/user-guide/)
- [AssertJ Core Documentation](https://assertj.github.io/doc/)
- [ApprovalTests Java](https://github.com/approvals/ApprovalTests.Java)

---

<details>
<summary><strong>Mise en place du Codespace</strong> (rappel R2.02 / TP1) - déplier si besoin</summary>

La mise en place se fait en deux étapes : accepter le devoir sur GitHub Classroom (qui crée le dépôt **partagé du binôme**), puis ouvrir ce dépôt dans un Codespace (votre environnement de développement dans le navigateur).

### Étape 1 - Accepter le devoir sur GitHub Classroom (en binôme)

> [!IMPORTANT]
> Ce TP est en **pair programming**. L'assignment Classroom est configuré en **mode équipe** : un seul dépôt par binôme, avec les deux membres en accès push. **Mettez-vous d'accord sur votre binôme avant** de cliquer sur le lien.

1. Cliquez sur le lien suivant :

   👉 **https://classroom.github.com/a/hT0F_8VU**

2. Si c'est votre première utilisation de GitHub Classroom, autorisez l'accès à votre compte GitHub.
3. Sélectionnez votre nom dans la liste des étudiants (si elle apparaît) pour associer votre compte GitHub à votre identité dans le cours.
4. **Créez ou rejoignez une équipe** :
   - Si vous êtes le **premier du binôme** : cliquez sur **"Create a new team"** et donnez un nom explicite (par exemple `binome-alice-bob`, en utilisant les vrais logins GitHub des deux membres).
   - Si vous êtes le **second du binôme** : la liste des équipes existantes apparaît ; cliquez sur **"Join"** à côté du nom de l'équipe créée par votre coéquipier.
5. Cliquez sur **"Accept this assignment"**.
6. Attendez quelques secondes - GitHub crée automatiquement un dépôt unique au nom de l'équipe : `IUTInfoAix-R203-tp3-2026/tp3-<nom-equipe>`.
7. Cliquez sur le lien du dépôt créé pour y accéder. Les **deux membres du binôme** ont accès au dépôt en lecture **et en écriture** : chacun peut pusher, et l'autograding remonte la note pour les deux.

### Étape 2 - Ouvrir le projet dans GitHub Codespaces

Une fois sur la page de votre dépôt :

1. Cliquez sur le bouton vert **"Code"** (en haut à droite du listing de fichiers).
2. Sélectionnez l'onglet **"Codespaces"**.
3. Cliquez sur **"Create codespace on main"**.

<img src="src/main/resources/assets/create_codespace_on_main.png" alt="Bouton Code -> Codespaces -> Create codespace on main" width="400"/>

4. Attendez que l'environnement se construise (de 1 à 5 minutes la première fois).
5. VS Code s'ouvre **dans votre navigateur** avec tout l'environnement pré-configuré :
   - Java 25
   - Maven (via le wrapper `./mvnw`)
   - Git
   - Copilot Chat (votre assistant IA pédagogique)
   - Toutes les extensions nécessaires

![VS Code dans le navigateur après ouverture du Codespace](src/main/resources/assets/codespace_vscode.png)

> [!IMPORTANT]
> Le Codespace est **personnel et persistant**. Quand vous fermez l'onglet, votre travail est sauvegardé. Pour reprendre, retournez sur votre dépôt GitHub -> **"Code"** -> **"Codespaces"** -> cliquez sur le Codespace existant (ne créez pas un nouveau à chaque fois).

### Vérification rapide

Une fois le Codespace ouvert, ouvrez un terminal via le menu **Terminal -> New Terminal** :

![Menu Terminal -> New Terminal](src/main/resources/assets/codespace_vscode_nouveau_terminal.png)

Puis lancez :

```bash
./mvnw test
```

Vous devriez voir un résultat du type :
```
Tests run: X, Failures: 0, Errors: 0, Skipped: X
BUILD SUCCESS
```

Si c'est le cas, tout est prêt. Le seul test actif (`AppTest`) passe, et les tests d'exercices sont en attente (`Skipped`) - c'est normal, ils seront activés au fil de votre progression.

</details>

---

<details>
<summary><strong>Comment vous êtes évalué·e</strong> (autograding /1000, rappel R2.02) - déplier si besoin</summary>

## Rendu du travail et évaluation

### Comment vous êtes évalués

L'évaluation de ce TP est **entièrement automatique** : à chaque fois que vous poussez (`push`) votre code sur GitHub, un système d'autograding exécute tous vos tests et calcule un score sur **1000 points**. Ce score est affiché tel quel par le reporter Classroom ; pour le ramener à la note sur 20, divisez par 50 (ex : 850/1000 = 17/20).

- **100 points** sont attribués si le projet **compile** correctement
- **900 points** sont répartis entre les différents **tests des exercices**, chaque test valant au moins 1 point
- Un test `@Disabled` (non encore activé) rapporte **0 point** - c'est normal
- Un test activé et **qui passe** rapporte ses points
- Un test activé et **qui échoue** rapporte 0 point

Votre score augmente progressivement au fil de votre avancement. Il n'y a pas de date limite brutale : chaque push met à jour votre score.

### Consulter votre note actuelle

Après chaque `push`, rendez-vous sur la page de votre dépôt GitHub -> onglet **"Actions"** -> dernier run du workflow **"GitHub Classroom Workflow"**. Le score apparaît dans le résumé :

```
Points 250/1000
```

Vous pouvez aussi voir le détail test par test pour savoir exactement quels exercices sont validés et lesquels restent à faire.

</details>

---

<details>
<summary><strong>Commandes Maven essentielles</strong> (rappel R2.02) - déplier si besoin</summary>

**Maven** est un outil de construction de projets Java utilisé dans la majorité des projets professionnels. Il gère automatiquement la compilation du code, le téléchargement des bibliothèques nécessaires (JUnit, AssertJ, Mockito, ApprovalTests...), l'exécution des tests et le packaging de l'application. Plutôt que de lancer `javac` et `java` à la main avec des dizaines d'options, une seule commande Maven suffit.

Dans ce projet, Maven est embarqué via un **Maven Wrapper** (`./mvnw`) : un script qui télécharge et utilise automatiquement la bonne version de Maven. Aucune installation n'est nécessaire : la première exécution prend quelques secondes de plus (téléchargement), puis tout est instantané.

| Commande | Effet |
|----------|-------|
| `./mvnw compile exec:java` | Lance le menu console de l'application (choisit un exercice) |
| `./mvnw test` | Exécute les tests unitaires |
| `./mvnw clean test` | Rebuild propre (supprime `target/` puis relance les tests) |
| `./mvnw clean` | Supprime les artefacts (`target/`) |
| `./mvnw spotless:apply` | Formate le code Java (Google Java Style) |

> [!NOTE]
> Le code est aussi formaté **automatiquement** avant chaque commit via un hook pre-commit invisible. Il n'est pas nécessaire de lancer `spotless:apply` à la main, sauf pour vérifier visuellement le formatage avant un commit.

</details>

---

<details>
<summary><strong>Workflow Git par exercice</strong> (branche / PR / merge, rappel TP1) - déplier si besoin</summary>

Chaque exercice suit le même cycle. Cette démarche structurée vous aide à travailler de manière **méthodique et professionnelle** : c'est exactement le workflow que vous utiliserez en entreprise.

**1. Créer une branche pour l'exercice**

```bash
git checkout -b exerciceN
```

**2. Activer le premier test** - ouvrez le fichier de test correspondant et retirez l'annotation `@Disabled` du premier test.

**3. Vérifier que le test est rouge**

```bash
./mvnw test
```

Le test doit échouer - c'est normal et attendu. Le message d'erreur vous indique ce que le test attend.

**4. Implémenter le minimum** pour faire passer ce test au vert. Pas plus que nécessaire.

**5. Vérifier que le test passe**

```bash
./mvnw test
```

**6. Lancer l'application** pour voir le résultat depuis le menu :

```bash
./mvnw compile exec:java
```

Ou via `Ctrl+Shift+B` dans VS Code.

**7. Recommencer** - activez le test suivant (étapes 2 à 6) jusqu'à ce que tous les tests de l'exercice soient verts.

**8. Finaliser l'exercice** - quand tous les tests passent :

```bash
git add .
git commit -m "feat(exerciceN): termine l'exercice"
git push -u origin exerciceN
```

**9. Créer une Pull Request** pour voir votre travail et recevoir une review automatique :

```bash
gh pr create --title "feat(exerciceN): termine l'exercice" --body "Tous les tests passent."
```

Ouvrez la PR dans le navigateur (`gh pr view --web`) pour consulter le diff, les checks CI, le score autograding et les commentaires de la review Copilot.

**10. Merger et passer à la suite** :

```bash
gh pr merge --rebase --delete-branch
```

Cette commande merge la PR, bascule votre HEAD local sur `main`, `pull` les
derniers commits et supprime la branche de feature locale et distante.

Votre score sur GitHub Actions augmente à chaque exercice terminé. Vous pouvez maintenant passer à l'exercice suivant en reprenant à l'étape 1.

> [!TIP]
> **Copilot Chat** est là pour vous accompagner à chaque étape. N'hésitez pas à lui poser des questions - il vous guidera sans donner la solution directement.

</details>

---

<details>
<summary><strong>Copilot Chat comme tuteur</strong> (rappel R2.02 / TP1) - déplier si besoin</summary>

Vous avez le droit d'utiliser **Copilot Chat** (panneau latéral dans VS Code) quand vous bloquez sur un exercice. Il est configuré spécifiquement pour ce TP : il ne donnera pas la solution directement, mais vous guidera par étapes : d'abord une explication du concept, puis un pointeur vers la documentation, et seulement en dernier recours un minimum de code.

**Copilot Chat n'est pas un raccourci, c'est un tuteur.** Il vous aide à comprendre, pas à copier-coller. L'objectif est que vous soyez capable d'écrire ce code **seul(e)** à la fin de la séance.

**Pourquoi c'est important** : l'évaluation de ce module se fera **sur papier, sans aucun outil d'assistance**. Il est donc essentiel que vous construisiez vos automatismes en écrivant le code vous-même. Copilot Chat est un filet de sécurité pour débloquer, pas un substitut à la réflexion.

**Conseil pratique** : sur les premiers exercices, n'hésitez pas à demander de l'aide pour vous familiariser avec les concepts et le workflow. Sur les exercices avancés, essayez d'aller le plus loin possible par vous-même avant de solliciter l'assistant. C'est cette progression vers l'autonomie qui vous préparera le mieux aux évaluations.

Le TP est découpé en plusieurs **exercices** à faire dans l'ordre. Chaque exercice vit dans son propre sous-paquet (code et tests en miroir). L'exercice 1 est très guidé pas à pas pour vous familiariser avec l'environnement. À partir de l'exercice 2, une boucle de travail systématique est introduite que vous appliquerez pour tous les exercices suivants.

</details>

---

## Protocole du pair programming pour la séance

1. **Formez un binôme** avec un·e camarade (sans préférence particulière).
2. Décidez qui démarre comme **driver** (clavier) et qui démarre comme **navigator**.
3. Lancez un minuteur sur **7 minutes**.
4. À chaque "bip", **échangez les rôles** : le navigator prend le clavier, le driver se recule.
5. **Le driver n'écrit rien sans consultation du navigator**. Le navigator valide chaque ligne.
6. Pendant un kata, on ne change pas de binôme. Entre deux kata, on peut réorganiser si besoin.

> [!TIP]
> **Essayez le ping-pong TDD** au moins une fois (kata 3 ou 4 par exemple) : le navigator écrit le prochain test, le driver écrit juste ce qu'il faut pour le faire passer. Ce rythme force à rester en TDD strict.

---

## Lire les noms de tests

Tout au long du TP, les méthodes de test suivent une même structure de nommage que vous lirez naturellement comme une phrase française : **`<sujet>_<action>_<conséquence>`**.

Quelques exemples concrets, extraits du TP :

| Nom de la méthode | Lecture |
|---|---|
| `l_annee_2024_est_bissextile` | L'année 2024 est bissextile. |
| `le_jeu_de_tennis_a_zero_zero_au_demarrage` | Le jeu de tennis a "zéro / zéro" au démarrage. |
| `la_pagination_avec_une_seule_page_n_affiche_pas_d_ellipse` | La pagination avec une seule page n'affiche pas d'ellipse. |
| `le_yahtzee_avec_cinq_des_identiques_donne_50_points` | Le Yahtzee avec cinq dés identiques donne 50 points. |
| `la_calculatrice_avec_un_nombre_negatif_leve_exception` | La calculatrice avec un nombre négatif lève une exception. |

**Pourquoi cette forme ?**

- **Elle se lit toute seule.** Pas besoin d'aller voir le corps de la méthode pour comprendre ce qui est testé : le nom suffit.
- **Elle est exhaustive.** Le sujet (à qui on parle), l'action (ce qu'on fait) et la conséquence (ce qu'on attend) sont tous nommés.
- **Elle est en français.** Le code de production reste en anglais idiomatique (convention Java) mais les tests racontent l'histoire de votre code dans la langue dans laquelle vous pensez.

**Petites règles à retenir si vous écrivez de nouveaux tests :**

1. **`snake_case`** (mots séparés par `_`), pas `camelCase`. Plus lisible quand les noms s'allongent.
2. **Verbe conjugué à la 3e personne du singulier** : *l'année est bissextile* (pas `etreBissextile`), *la calculatrice additionne* (pas `additionner`).
3. **Forme négative avec `sans`** plutôt que `ne ... pas` : `tourne_a_droite_sans_changer_la_position` plutôt que `tourne_a_droite_ne_change_pas_la_position`.
4. **Pas de verbe vague** comme `fonctionne` ou `marche`. Préférez le verbe précis qui décrit l'effet : `retourne_X`, `donne_X`, `additionne`, `est_correctement_X`, `leve_exception`.
5. **La longueur n'est pas un problème, la lisibilité l'est.** Un nom de dix mots qui se lit comme une phrase est meilleur qu'un nom de trois mots qui force à ouvrir le corps du test.

> [!TIP]
> Cette forme transforme vos tests en **documentation exécutable**. Quand un collègue (ou vous-même dans six mois) lit la liste des tests d'une classe, il comprend en quelques secondes ce que la classe est censée faire et garantir, sans lire une seule ligne de code de test.

---

## Lancer un test rapidement (CodeLens)

Au-dessus de chaque méthode `@Test` dans VS Code, un lien cliquable **Run Test** apparaît. Il lance le test courant en une seconde, sans avoir à taper de commande Maven.

![CodeLens Run Test au-dessus d'une méthode @Test](src/main/resources/assets/codelens_run_test.png)

- **Run Test** au-dessus de la méthode : exécute **ce seul test**. Idéal pour la boucle TDD rouge → vert.
- **Run Test** au-dessus de la classe : exécute **tous les tests du fichier**.
- Le résultat s'affiche dans le panneau **Test Results** en bas, avec un point vert ou rouge à côté de chaque méthode dans l'arborescence à gauche.

> [!TIP]
> Pendant le TP, gardez le réflexe **CodeLens pour la boucle TDD** (un test à la fois, retour en moins d'une seconde) et `./mvnw test` (depuis le terminal) **avant chaque commit** pour relancer toute la suite et confirmer qu'aucun test antérieur n'a régressé.

---

## Kata 1 - Années bissextiles (★)

> [!IMPORTANT]
> **🎲 Contrainte de progression (CM2) : 🙅 Pas de `if` / `else`.**
> Force le polymorphisme et les *lookup tables*. Apprend à remplacer les branches par des objets ou des expressions.
>
> **Pour vous aider** :
> - L'algorithme grégorien peut s'écrire comme **une seule expression booléenne** : "bissextile = (divisible par 400) **OU** (divisible par 4 **ET non** divisible par 100)".
> - Le `return` direct de cette expression est plus court et plus lisible que d'accumuler des affectations.
>
> **Si vous bloquez** au-delà de 10 min, repassez à une version classique avec `if`/`else` cumulatifs (multiple de 4, sauf 100, sauf 400). La contrainte est un défi d'exploration, pas un mur — l'autograding évalue uniquement les comportements, pas le style.

### Objectif

Premier kata très court (~10 min) pour caler le rythme de pair programming et observer la stratégie TDD sur une **règle à 3 exceptions**.

### Règles

Une année est bissextile si :

1. elle est divisible par 4
2. **sauf si** elle est divisible par 100
3. **sauf si** elle est divisible par 400

Exemples : 2020 bissextile, 2021 non, 1900 non (divisible par 100), 2000 bissextile (divisible par 400).

### API fournie

`AnneesBissextiles.estBissextile(int annee)` : retourne `true` si bissextile.

### Travail à faire

Activez les 14 tests un par un. Observez comment chaque nouveau test vous force à ajouter une règle. C'est un exemple parfait de la **conception émergente** en TDD : l'algorithme apparaît progressivement.

---

## Kata 2 - Jeu de tennis (★★★)

> [!IMPORTANT]
> **🎲 Contrainte de progression (CM2) : 📦 Pas de primitives** *(dans l'API publique de la classe)*.
> Force à créer de vrais **objets métier**. Apprend *Tell, Don't Ask*.
>
> **Pour vous aider** :
> - Modélisez le **score** comme un type. Une `sealed interface Score permits Compte, Egalite, Avantage, JeuGagne` avec des `record` pour chaque cas est l'idiome moderne ; chaque variante porte ses propres champs (deux `int` pour `Compte`, un `Joueur` pour `Avantage` et `JeuGagne`).
> - Introduisez un `record Joueur(String nom)` plutôt que de balader des `String` partout dans la signature de `marquerPoint`.
> - L'objet `Score` sait se rendre en chaîne via une méthode `format()` ; c'est `getScore()` qui appelle `format()`.
>
> **Si vous bloquez**, repassez à la version classique avec deux `int points1, points2` et un `if`/`else` qui choisit entre les libellés. Vous y reviendrez peut-être après le TP4 (Refactoring) avec un œil neuf.

### Objectif

Kata classique du TDD. Implémenter le scoring d'un jeu de tennis est étonnamment subtil : "0", "15", "30", "40", puis "Deuce", "Advantage", "Win"... C'est une **machine à états** complète à découvrir au fil des tests.

### Règles

Format du score :

- `"<p1>-<p2>"` avec `<p>` ∈ `{"0", "15", "30", "40"}` tant que personne n'a plus de 3 points
- `"Deuce"` si les deux joueurs sont à égalité avec au moins 3 points chacun
- `"Advantage <nom>"` si un joueur mène d'un point après Deuce
- `"Win for <nom>"` si un joueur a 4+ points et 2+ points d'avance

### API fournie

- `new JeuDeTennis(String joueur1, String joueur2)`
- `marquerPoint(String nom)`
- `getScore()` : retourne la chaîne selon les règles

### Travail à faire

Activez les 21 tests dans l'ordre. Ils sont conçus pour vous faire découvrir les règles progressivement : 0-0 d'abord, puis les points simples, puis Deuce, puis Advantage, puis les cas Win.

### Question de compréhension

À la fin du kata, en binôme, répondez à la question : à quel moment avez-vous ressenti le besoin de refactorer ? Avez-vous introduit une méthode privée ? Laquelle ? Pourquoi à ce moment et pas avant ?

---

## Kata 3 - Gestionnaire d'employés (★★)

> [!IMPORTANT]
> **🎲 Contrainte de progression (CM2) : 🔁 Pas de boucles**.
> Force la **récursion** ou les `Stream`. Apprend la programmation fonctionnelle.
>
> **Pour vous aider** :
> - `employes.stream().filter(e -> e.age() >= 18).toList()` remplace la boucle `for + add`.
> - `getMajeurs().stream().mapToInt(Employe::age).average().orElse(0.0)` remplace une boucle de somme + division. La méthode `OptionalDouble.orElse(0.0)` gère le cas "aucun majeur" sans `if isEmpty` manuel.
> - `employes.stream().sorted(Comparator.comparing(Employe::nom)).toList()` remplace le tri manuel.
> - **Attention** : un `for-each` reste une boucle pour cette contrainte. On cherche du vraiment déclaratif.
>
> **Si vous bloquez** sur les *streams*, faites une première passe avec des `for-each` classiques, puis transformez méthode par méthode. La transformation manuelle est elle-même un excellent exercice.

### Objectif

Premier kata qui manipule des **collections** en TDD. Au-delà du scoring ponctuel, on fait des **agrégations** (moyenne) et des **tris** (par nom, par âge). C'est un avant-goût du travail quotidien en entreprise : transformer des données.

### API fournie

- `Employe(String nom, int age)` : un `record`
- `new GestionnaireEmployes(List<Employe>)`
- `getMajeurs()` : filtre âge >= 18, retourne dans l'ordre d'origine
- `ageMoyenDesMajeurs()` : retourne `0.0` s'il n'y a aucun majeur (pas `NaN`)
- `parOrdreAlphabetique()` : sans modifier la liste d'origine
- `parAgeCroissant()` : sans modifier la liste d'origine

### Travail à faire

Activez les 7 tests dans l'ordre. Respectez l'**immutabilité** : les méthodes qui trient retournent une nouvelle liste sans modifier la liste d'origine. Utilisez `Comparator.comparing(...)` et `Comparator.comparingInt(...)`.

---

## Kata 4 - Pagination (★★★)

> [!IMPORTANT]
> **🎲 Contrainte de progression (CM2) : ✂️ 4 lignes max par méthode**.
> Méthodes courtes obligatoires. Apprend **Extract Method** à fond et la décomposition fine.
>
> **Pour vous aider** :
> - Décomposez en méthodes privées avec un nom qui dit ce qu'elles font : `pagesAAfficher()` (la liste des numéros à montrer), `formatPage(int)` (un seul numéro avec ou sans parenthèses), `separateurEntre(int, int)` (espace ou ` ... ` selon l'écart).
> - Une méthode = une responsabilité. Si vous dépassez 4 lignes, c'est qu'il y en a deux.
> - Comptez les **lignes utiles** (pas les `}` seuls, pas les blank lines, pas la Javadoc).
>
> **Si vous bloquez**, autorisez-vous 6-8 lignes pour une méthode complexe puis voyez si vous arrivez à la redécomposer plus tard. Les tests de structure ne vérifient pas la longueur, juste le comportement.

### Objectif

Kata algorithmique avec beaucoup de **cas limites**. Afficher une barre de pagination compacte (à la Google : `1 ... 5 6 (7) 8 ... 15`) est un petit exercice étonnamment piégeux.

### Règles

- Page courante entre parenthèses : `(3)`
- Séparateur : espace simple
- Si `total <= 7` : afficher toutes les pages
- Sinon : afficher `1`, `courant-1`, `courant`, `courant+1`, `total`, avec `...` entre deux numéros si l'écart est > 1

### API fournie

`new Pagination(int courant, int total).afficher()` retourne la chaîne.

### Travail à faire

Activez les 10 tests dans l'ordre. Les premiers couvrent les cas simples (une page, quelques pages). Les derniers imposent la logique d'ellipses. Résistez à la tentation de coder toute la logique dès le premier test : baby steps.

---

## Kata 5 - Yahtzee (★★★★)

> [!IMPORTANT]
> **🎲 Contrainte de progression (CM2) : 🤐 Silence**.
> Communiquez **uniquement par le code et les tests**. Apprend l'expressivité.
>
> **Pour vous aider** :
> - Avant chaque cycle, le navigator **écrit le nom du prochain test** (au tableau, sur une feuille ou en commentaire `// next: la_paire_la_plus_haute_est_choisie`). Le driver le saisit dans le code, pas de phrase orale.
> - Si vous voulez signaler un cas limite oublié, écrivez un `@Test @Disabled` avec un nom parlant — votre coéquipier·e le verra.
> - Cette contrainte récompense les **noms de tests expressifs** (cf. la section "Lire les noms de tests" en début de TP). Si un nom de test est ambigu pour vous, il l'est aussi pour votre coéquipier·e.
>
> **Si vous craquez** : reprenez la conversation orale, mais notez ce que vous auriez voulu communiquer par le code. C'est un excellent post-mortem pour la prochaine séance.

### Objectif

Kata riche avec **9 règles de scoring** différentes. Un classique du **refactoring** : l'implémentation naïve dupliquera du code (chaque règle compte les occurrences des faces) ; à vous de repérer ce motif et d'extraire une méthode quand tous les tests sont verts.

### Règles (abrégées)

Pour 5 dés à 6 faces :

- `chance` : somme des 5 dés
- `yahtzee` : 50 si les 5 dés identiques, sinon 0
- `nombres(face, dés)` : somme des dés d'une face donnée
- `paire` : 2 * la plus haute valeur apparaissant ≥ 2 fois
- `deuxPaires` : somme des deux paires (de valeurs distinctes)
- `brelan` : 3 * la valeur du brelan (≥ 3 fois la même face)
- `petiteSuite` : 15 si exactement 1-2-3-4-5, sinon 0
- `grandeSuite` : 20 si exactement 2-3-4-5-6, sinon 0
- `full` : somme des 5 dés si brelan + paire (valeurs distinctes), sinon 0

### API fournie

Méthodes statiques de `Yahtzee` (toutes prennent les dés en paramètres, retournent un `int`).

### Travail à faire

Activez les 32 tests dans l'ordre. Implémentez chaque méthode isolément, **puis** quand plusieurs sont vertes (au moins 3), cherchez la factorisation naturelle (comptage des occurrences). Ne refactorez **jamais** sur du rouge.

### Question de compréhension

Quand vous avez refactoré (si vous l'avez fait), combien de lignes avez-vous supprimé ? Auriez-vous pu refactorer au premier kata déjà ? Pourquoi avoir attendu ?

---

## Kata 6 - Ascenseur (★★★★, bonus)

> [!NOTE]
> **Hors barème.** Ce kata n'est pas comptabilisé dans l'autograding (le paquet `bonus6/` est volontairement ignoré par le système de notation). Faites-le pour vous, uniquement si vous avez terminé les cinq kata précédents. Il est plus dense que Yahtzee et demande de vraiment tenir la discipline ping-pong pour ne pas partir dans une réécriture.

> [!IMPORTANT]
> **🎲 Contrainte de progression (CM2) : 🏓 Mute ping-pong** (sur la phase d'**extension** du kata).
> Une fois les 14 tests fournis activés et verts, **ajoutez vos propres tests** en ping-pong silencieux : A écrit un nouveau test pour un cas limite ou un comportement non couvert, B le fait passer sans dire un mot, B écrit le suivant, etc. Apprend la rigueur TDD pure.
>
> **Pistes de tests à ajouter** :
> - Demandes simultanées qui s'annulent (deux passagers appellent l'ascenseur depuis le même étage).
> - Comportement quand l'ascenseur est appelé pendant qu'il en sert déjà une autre.
> - Limites d'étages (négatif, plafond) si vous voulez les introduire.
> - Refus d'une nouvelle demande quand les portes sont ouvertes.
>
> **Pourquoi ça marche ici** : le bonus n'impacte pas l'autograding (le paquet `bonus6/` est exclu). Vous pouvez ajouter ou modifier des tests librement, c'est même tout l'intérêt — découvrir un comportement non spécifié et le pin par un test.
>
> **Si vous bloquez** : revenez au pair programming classique avec discussion. Mais essayez de tenir la contrainte au moins sur les 2-3 premiers tests ajoutés pour goûter à l'effet "tout passe par le code".

### Objectif

Modéliser un **ascenseur unique** contrôlé par un horloge discrète. L'extérieur pousse des `demandes` (un étage), puis appelle `tick()` autant de fois que nécessaire : chaque appel fait avancer la simulation d'une unité de temps.

C'est la machine à états la plus riche du TP : plus complexe que le tennis (kata 2), parce que le temps est **explicite** et que plusieurs invariants coexistent (portes, déplacement, demandes).

### Règles de fonctionnement (à chaque `tick`)

Dans l'ordre de priorité :

1. **Si les portes sont ouvertes**, on les referme. Fin du tick.
2. **Sinon, s'il reste une demande pour l'étage courant**, on ouvre les portes et on retire cette demande. Fin du tick.
3. **Sinon, s'il reste une demande ailleurs**, on avance d'un étage vers la **demande la plus proche**.
4. **Sinon**, l'ascenseur reste sur place.

Contraintes additionnelles :

- `demander(int etage)` est **idempotent** : demander trois fois le même étage ne crée qu'une seule demande.
- `getDemandes()` renvoie une **vue non modifiable** (List immuable) - la signature est fournie, respectez-la.

### API fournie

```java
public class Ascenseur {
  public Ascenseur(int etageInitial) { ... }
  public int getEtage() { ... }
  public boolean portesOuvertes() { ... }
  public List<Integer> getDemandes() { ... }
  public void demander(int etageDemande) { ... }   // à compléter
  public void tick() { ... }                       // à compléter
}
```

### Travail à faire

Le fichier `AscenseurTest` contient **14 tests désactivés** organisés en 4 groupes (état initial, déplacement, arrivée / portes, demandes multiples). Activez-les **un par un**, strictement dans l'ordre proposé, en ping-pong.

Piège classique du kata : **ne pas traiter ouverture de portes et consommation de la demande au même tick que le déplacement**. Gardez bien les 4 cas de la Javadoc mutuellement exclusifs - c'est ce qui rend le code simple.

### Question de compréhension

Votre `tick()` finit probablement en 3 ou 4 branches `if`. Si un collègue vous demandait « et maintenant, on ajoute la gestion des appels externes (avec direction HAUT / BAS) », par où commenceriez-vous ? Quel(s) **code smell(s)** (cf. CM2) vous guideraient pour refactorer avant de coder la nouvelle fonctionnalité ?

> 💡 Cette question est exactement ce que demande le **kata Gilded Rose** du TP4.

---

## Ressources complémentaires

- [JUnit 6 User Guide](https://docs.junit.org/current/user-guide/)
- [AssertJ Core Documentation](https://assertj.github.io/doc/)
- [ApprovalTests Java](https://github.com/approvals/ApprovalTests.Java)
- [Mockito Documentation](https://site.mockito.org)
- [Refactoring (Martin Fowler)](https://refactoring.com)

---

*IUT d'Aix-Marseille - Département Informatique - 2026*

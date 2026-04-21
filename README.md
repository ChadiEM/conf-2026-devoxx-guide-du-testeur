# Le Guide du Testeur - 6 mauvaises habitudes à briser absolument

Support de conférence pour la session présentée à **Devoxx France 2026**.

## Abstract

Nous l'avons tous déjà vécu : un pipeline CI qui brille de mille feux avec 100 % de couverture de tests, et
pourtant, une production qui part en fumée. Comment se fait-il que nous écrivons toujours plus de tests, mais que
notre sentiment de confiance n'ait jamais été aussi bas ?

La vérité est cruelle : bon nombre de nos "bonnes pratiques" de test actuelles sont en réalité des tueurs
silencieux. Du "Coverage-Driven Development", qui ne teste parfois rien, aux hacks `Thread.sleep()` qui rendent
nos tests lents et instables, en passant par la surutilisation des mocks, nous produisons souvent du code qui
ressemble à une suite de tests, mais qui agit comme un boulet.

Dans cette session ultra-rapide de 15 minutes, code à l'appui (Java 21 & JUnit 5), nous allons réaliser une
véritable "intervention chirurgicale" afin de disséquer 6 anti-patterns fréquents et repartir avec des solutions
concrètes pour rendre vos tests enfin dignes de confiance.

## Contenu du dépôt

```
.
├── Code/          # Exemples de code Java (Maven)
└── Presentation/  # Slides de la conférence (PDF)
```

## Les 6 anti-patterns

| # | Package | Anti-pattern | Remède |
|---|---------|--------------|--------|
| 1 | `illisible` | Tests illisibles et impossibles à suivre | Pattern Given/When/Then, un scénario par test |
| 2 | `beantesting` | Tester les getters/setters (Coverage-Driven Development) | Ne tester que la logique métier réelle |
| 3 | `mocks` | Sur-utilisation des mocks (test = miroir de l'implémentation) | Tester les comportements, pas les interactions internes |
| 4 | `assertionscollections` | Assertions faibles sur les collections | [AssertJ](https://assertj.github.io/doc/) |
| 5 | `async` | `Thread.sleep()` pour attendre les traitements asynchrones | [Awaitility](https://github.com/awaitility/awaitility) |
| 6 | `statics` | État statique mutable partagé entre les tests | Injection de dépendances / variables d'environnement |


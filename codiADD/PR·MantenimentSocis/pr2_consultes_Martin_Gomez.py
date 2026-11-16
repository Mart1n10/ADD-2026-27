"""PR2 - Consultes per a la pantalla principal d'una biblioteca.

Alumne: Martín Gómez Porras
Assignatura: MP06 Accés a Dades (DAM)

Requisits:
- Retornar objectes (mai text imprès dins de la funció).
- Una línia de justificació per a cada JOIN.
- Consultes parametritzades amb %s.
- Cursors tancats correctament (usant closing()).
"""
from contextlib import closing
from dataclasses import dataclass
from datetime import date
from typing import List, Optional

from db import connectar


# ---------------------------------------------------------------------------
# Classes per emmagatzemar els resultats (Objectes)
# ---------------------------------------------------------------------------

@dataclass
class Llibre:
    """Representa un llibre de la biblioteca amb el seu autor."""
    id: int
    titol: str
    any_pub: Optional[int]
    disponibles: int
    autor: str

    def es_pot_prestar(self) -> bool:
        return self.disponibles > 0


@dataclass
class SociRetard:
    """Representa un soci amb un préstec que supera el límit de dies."""
    soci_id: int
    soci: str
    email: str
    titol: str
    data_prestec: date
    dies_retard: int

    @property
    def nom(self) -> str:
        return self.soci


@dataclass
class TopLlibre:
    """Representa un llibre i el recompte total de vegades que ha estat prestat."""
    id: int
    titol: str
    autor: str
    total_prestecs: int

    @property
    def prestecs(self) -> int:
        return self.total_prestecs


# ---------------------------------------------------------------------------
# Funcions de consulta
# ---------------------------------------------------------------------------

def llibres_de_autor(cn, nom: str) -> List[Llibre]:
    """Retorna la llista d'objectes Llibre escrits per un autor determinat."""
    # Justificació JOIN: Relacionem llibre amb autor mitjançant autor_id per filtrar pel nom de l'autor i obtenir-ne les dades.
    sql = """
        SELECT l.id, l.titol, l.any_pub, l.disponibles, a.nom AS autor
          FROM llibre l
          JOIN autor a ON a.id = l.autor_id
         WHERE a.nom = %s
         ORDER BY l.id
    """
    with closing(cn.cursor(dictionary=True)) as cur:
        cur.execute(sql, (nom,))
        return [Llibre(**fila) for fila in cur.fetchall()]


def socis_amb_retard(cn, dies: int) -> List[SociRetard]:
    """Retorna els socis amb préstecs pendents de retorn que superen un nombre de dies."""
    # Justificació JOIN soci: Relacionem prestec amb soci mitjançant soci_id per obtenir el nom i email del soci deutor.
    # Justificació JOIN llibre: Relacionem prestec amb llibre mitjançant llibre_id per saber quin títol està pendent de retorn.
    sql = """
        SELECT s.id AS soci_id,
               s.nom AS soci,
               s.email,
               l.titol,
               p.data_prestec,
               DATEDIFF(CURDATE(), p.data_prestec) AS dies_retard
          FROM prestec p
          JOIN soci s ON s.id = p.soci_id
          JOIN llibre l ON l.id = p.llibre_id
         WHERE p.data_retorn IS NULL
           AND DATEDIFF(CURDATE(), p.data_prestec) > %s
         ORDER BY dies_retard DESC
    """
    with closing(cn.cursor(dictionary=True)) as cur:
        cur.execute(sql, (dies,))
        return [SociRetard(**fila) for fila in cur.fetchall()]


def top_llibres(cn, n: int) -> List[TopLlibre]:
    """Retorna els n llibres més prestats de la biblioteca."""
    # Justificació JOIN llibre: Relacionem prestec amb llibre mitjançant llibre_id per agrupar i obtenir les dades del llibre prestat.
    # Justificació JOIN autor: Relacionem llibre amb autor mitjançant autor_id per incloure el nom de l'autor del llibre.
    sql = """
        SELECT l.id,
               l.titol,
               a.nom AS autor,
               COUNT(p.id) AS total_prestecs
          FROM prestec p
          JOIN llibre l ON l.id = p.llibre_id
          JOIN autor a ON a.id = l.autor_id
         GROUP BY l.id, l.titol, a.nom
         ORDER BY total_prestecs DESC, l.id ASC
         LIMIT %s
    """
    with closing(cn.cursor(dictionary=True)) as cur:
        cur.execute(sql, (n,))
        return [TopLlibre(**fila) for fila in cur.fetchall()]


def llibres_esgotats(cn) -> List[Llibre]:
    """Retorna els llibres amb 0 exemplars disponibles, incloent el nom de l'autor."""
    # Justificació JOIN autor: Relacionem llibre amb autor mitjançant autor_id per mostrar el nom de l'autor de cada llibre esgotat.
    sql = """
        SELECT l.id, l.titol, l.any_pub, l.disponibles, a.nom AS autor
          FROM llibre l
          JOIN autor a ON a.id = l.autor_id
         WHERE l.disponibles = %s
         ORDER BY l.id
    """
    with closing(cn.cursor(dictionary=True)) as cur:
        cur.execute(sql, (0,))
        return [Llibre(**fila) for fila in cur.fetchall()]


# ---------------------------------------------------------------------------
# Bloc d'execució i proves (les impressions es fan fora de les funcions)
# ---------------------------------------------------------------------------

if __name__ == "__main__":
    with closing(connectar()) as cn:
        print("=== 1) Llibres de Mercè Rodoreda ===")
        for llibre in llibres_de_autor(cn, "Mercè Rodoreda"):
            print(f"- {llibre.titol} ({llibre.any_pub}), disponibles: {llibre.disponibles}, autor: {llibre.autor}")

        print("\n=== 2) Socis amb préstecs de més de 15 dies de retard ===")
        for retard in socis_amb_retard(cn, 15):
            print(f"- {retard.soci} ({retard.email}): '{retard.titol}' prestat el {retard.data_prestec} ({retard.dies_retard} dies)")

        print("\n=== 3) Top 3 llibres més prestats ===")
        for top in top_llibres(cn, 3):
            print(f"- {top.titol} ({top.autor}): {top.total_prestecs} préstecs")

        print("\n=== 4) Llibres esgotats (disponibles = 0) ===")
        for esgotat in llibres_esgotats(cn):
            print(f"- {esgotat.titol} ({esgotat.autor}) - Disponibles: {esgotat.disponibles}")

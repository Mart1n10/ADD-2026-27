import mysql.connector
from db import connectar


def _errno(e):
    # Obté el codi d'error de MySQL de l'excepció.
    return getattr(e, "errno", None) or (e.args[0] if e.args else None)


def alta_soci(cn, nom, email):
    # Insereix un soci i retorna el seu id nou; si l'email és repetit (1062) mostra l'error i retorna None.
    cur = cn.cursor()
    try:
        cur.execute("INSERT INTO soci (nom, email) VALUES (%s, %s)", (nom, email))
        cn.commit()
        return cur.lastrowid
    except mysql.connector.IntegrityError as e:
        cn.rollback()
        if _errno(e) == 1062:
            print(f"  [ERROR 1062] L'email '{email}' ja està registrat a un altre soci.")
        else:
            print(f"  [ERROR {_errno(e)}] {e}")
        return None
    finally:
        cur.close()


def canviar_nom(cn, id_soci, nom):
    # Canvia el nom d'un soci i retorna True si s'ha modificat alguna fila, False si no.
    cur = cn.cursor()
    try:
        cur.execute("UPDATE soci SET nom = %s WHERE id = %s", (nom, id_soci))
        cn.commit()
        return cur.rowcount > 0
    finally:
        cur.close()


def baixa_soci(cn, id_soci):
    # Esborra un soci; si té préstecs (error 1451) no el deixa esborrar i retorna False.
    cur = cn.cursor()
    try:
        cur.execute("DELETE FROM soci WHERE id = %s", (id_soci,))
        cn.commit()
        return cur.rowcount > 0
    except mysql.connector.IntegrityError as e:
        cn.rollback()
        if _errno(e) == 1451:
            print(f"  [ERROR 1451] El soci {id_soci} té préstecs: no es pot donar de baixa.")
        else:
            print(f"  [ERROR {_errno(e)}] {e}")
        return False
    finally:
        cur.close()


def _un_soci(cn):
    # Retorna (id, nom, email) del primer soci que ja existeix a la BD.
    cur = cn.cursor()
    cur.execute("SELECT id, nom, email FROM soci ORDER BY id LIMIT 1")
    fila = cur.fetchone()
    cur.close()
    return fila


def _soci_amb_prestecs(cn):
    # Retorna l'id d'un soci que tingui préstecs, o None si no n'hi ha cap.
    cur = cn.cursor()
    cur.execute("SELECT soci_id FROM prestec LIMIT 1")
    fila = cur.fetchone()
    cur.close()
    return fila[0] if fila else None


def main():
    # Prova els quatre casos, cadascun amb el seu soci, i deixa la BD com estava.
    cn = connectar()
    nou_id = None
    try:
        print("1) Alta d'un soci nou")
        nom = input("  Nom del soci nou [Soci Prova]: ").strip() or "Soci Prova"
        email = input("  Email del soci nou [prova@test.cat]: ").strip() or "prova@test.cat"
        nou_id = alta_soci(cn, nom, email)
        print(f"  Soci creat amb id = {nou_id}")

        print("\n2) Alta amb un email que ja existeix")
        id_existent, nom_existent, email_existent = _un_soci(cn)
        resultat = alta_soci(cn, "Soci Repetit", email_existent)
        print(f"  Resultat: {resultat}")

        print("\n3) Canviar el nom d'un soci existent")
        print(f"  Id {id_existent} -> {canviar_nom(cn, id_existent, nom_existent + ' (prova)')}")
        canviar_nom(cn, id_existent, nom_existent)
        print(f"  Id inexistent -> {canviar_nom(cn, 999999, 'Ningú')}")

        print("\n4) Baixa d'un soci amb préstecs")
        id_amb_prestecs = _soci_amb_prestecs(cn)
        if id_amb_prestecs is None:
            print("  No hi ha cap soci amb préstecs a la BD per provar-ho.")
        else:
            print(f"  Baixa soci {id_amb_prestecs} -> {baixa_soci(cn, id_amb_prestecs)}")

    finally:
        if nou_id is not None:
            baixa_soci(cn, nou_id)
            print("\nNeteja feta: soci de prova eliminat, la BD queda com estava.")
        cn.close()


if __name__ == "__main__":
    main()
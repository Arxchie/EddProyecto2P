/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edd_hospital_.multi_lista;

import java.io.Serializable;

/**
 *
 * @author Jou
 */
public class MultiListaDL<T> implements Serializable {

    // Asegúrate de que todos los atributos internos también sean serializables
    private NodoML r = null;
    private boolean b = false;

    public NodoML getR() {
        return r;
    }

    public void setR(NodoML r) {
        this.r = r;
    }

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }

    /**
     * Metodo para insertar un nodo en la multilista
     *
     * @param obj Nodo que se desea insertar
     * @param ruta ruta donde se quiere insertar el objeto, ejemplo: si se desea
     * insertar en el primer nivel, no se coloca nada o se coloca un arreglo de
     * longitud 0 ejemplo de ruta: "A","B","C" quiere decir, entra a "A", entra
     * a "B", entra a "C" y ahi inserta
     */
    public void inserta(NodoML obj, String... ruta) {
        String[] nuevaRuta = new String[ruta.length + 1];
        System.arraycopy(ruta, 0, nuevaRuta, 0, ruta.length); // Copiar todo igual
        nuevaRuta[nuevaRuta.length - 1] = ""; // Agregar "" al final
        this.r = inserta(obj, this.r, nuevaRuta, 0);
    }

    private NodoML inserta(NodoML obj, NodoML r, String[] s, int nivel) {
        if (nivel == s.length - 1) {
            ListaDLML l = new ListaDLML();
            l.setR(r);
            l.inserta(obj);
            b = true;
            return l.getR();
        } else {
            NodoML aux = buscaEnLista(r, s[nivel]);
            if (aux != null) {
                aux.setAbj(inserta(obj, aux.getAbj(), s, nivel + 1));
                if (b) {
                    obj.setArb(aux);
                    b = false;
                }
            }

            return r;
        }
    }

    /**
     * Busca un nodo con etiqueta "s" en una lista.
     *
     * @param aux la raiz de la lista en la que se desea buscar
     * @param s etiqueta del nodo buscado
     * @return el nodo con la etiqueta especificada, si es que existe, null si
     * no existe
     */
    public NodoML buscaEnLista(NodoML aux, String s) {
        while (aux != null) {
            if (aux.getEt().equals(s)) {
                return aux;
            } else {
                aux = aux.getSig();
            }
        }
        return null;
    }

//    public NodoML buscarEnMultilistaRecu(int nivel, NodoML r, String... ruta)
//    {
//        if (r != null)
//        {
//            if (nivel == ruta.length - 1)
//            {
//                return buscaEnLista(r, ruta[nivel]);
//            } else
//            {
//                NodoML auxiliar = buscaEnLista(r, ruta[nivel]);
//                if (auxiliar != null)
//                {
//                    return buscarEnMultilistaRecu(nivel + 1, auxiliar.getAbj(), ruta);
//                } else
//                {
//                    return null;
//                }
//            }
//
//        } else
//        {
//            return null;
//        }
//
//    }
    /**
     * Metodo para buscar un nodo en la multilista
     *
     * @param ruta Ruta del objeto a buscar, ejemplo: "A","B","C": significa,
     * busca entra a "A", entra a "B", busca "C"
     * @return el ultimo nodo el la ruta especificada, si no se encuentra algun
     * elemento de la ruta devuelve null
     */
    public NodoML buscarEnMultilista(String... ruta) {
        NodoML actual = this.r;
        for (int nivel = 0; nivel < ruta.length; nivel++) {
            if (actual == null) {
                return null;
            }
            actual = buscaEnLista(actual, ruta[nivel]);
            if (nivel < ruta.length - 1 && actual != null) {
                actual = actual.getAbj();
            }
        }
        return actual;
    }

    public String desp() {
        return desp(this.r, "");
    }

    private String desp(NodoML r, String t) {

        String s = "";
        while (r != null) {
            if (r.getArb() != null) {
                s += t + r.getEt() + "->" + r.getArb().getEt() + "\n";
            } else {
                s += t + r.getEt() + "\n";
            }
            s += desp(r.getAbj(), t + "\t");
            r = r.getSig();
        }
        return s;
    }

    /**
     * Metodo para eliminar un nodo de la multilista por su etiqueta
     *
     * @param ruta especifica la ruta del objeto en la multilista ejemplo
     * "A","B","C": significa, entra a "A", entra a "B" y elimina "C"
     * @return el nodo eliminado, null si no se encontró
     */
    public NodoML elimina(String... ruta) {
        NodoML datos[] = elimina(this.r, ruta, 0);
        this.r = datos[1];
        return datos[0];

    }

    private NodoML[] elimina(NodoML r, String[] s, int nivel) {
        NodoML[] resultado = new NodoML[2];
        if (r == null) {
            resultado[0] = null;
            resultado[1] = null;

        } else {
            if (nivel == s.length - 1) {
                ListaDLML l = new ListaDLML();
                l.setR(r);
                NodoML eliminado = l.elimina(new NodoML(null, s[nivel]));
                resultado[0] = eliminado;
                if (eliminado != null) {
                    resultado[0].setArb(null);
                }
                resultado[1] = l.getR();
                return resultado;
            } else {
                NodoML aux = buscaEnLista(r, s[nivel]);
                if (aux != null) {
                    NodoML[] resAbajo = elimina(aux.getAbj(), s, nivel + 1);
                    aux.setAbj(resAbajo[1]);
                    resultado[0] = resAbajo[0];
                }
                resultado[1] = r;
            }
        }
        return resultado;

    }

    public static void main(String[] args) {
        MultiListaDL m = new MultiListaDL();
        NodoML n1 = new NodoML("A", "A");
        NodoML n2 = new NodoML("B", "B");
        NodoML n3 = new NodoML("C", "C");

        NodoML n4 = new NodoML("a", "a");
        NodoML n5 = new NodoML("b", "b");
        NodoML n6 = new NodoML("c", "c");
        NodoML n7 = new NodoML("d", "d");
        NodoML n8 = new NodoML("e", "e");

        String arr[] = new String[0];
        m.inserta(n1, arr);
        m.inserta(n2);
        m.inserta(n3);

        m.inserta(n4, "A");
        m.inserta(n5, "B");
        m.inserta(n6, "C");

        m.inserta(n7, "C", "c");
        m.inserta(n8, "C", "c", "d");
        System.out.println(m.desp());
        System.out.println(m.buscarEnMultilista("C", "c", "d").getEt());
        m.elimina("C", "c", "d");
        System.out.println(m.desp());
    }
}

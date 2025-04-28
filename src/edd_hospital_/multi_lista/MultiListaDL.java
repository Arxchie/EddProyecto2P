/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edd_hospital_.multi_lista;

/**
 *
 * @author Jou
 */
public class MultiListaDL
{

    private NodoML r = null;
    private boolean b = false;

    public NodoML getR()
    {
        return r;
    }

    public void setR(NodoML r)
    {
        this.r = r;
    }

    public boolean isB()
    {
        return b;
    }

    public void setB(boolean b)
    {
        this.b = b;
    }

    public void inserta(NodoML obj, String... ruta)
    {
        String[] nuevaRuta = new String[ruta.length + 1];
        System.arraycopy(ruta, 0, nuevaRuta, 0, ruta.length); // Copiar todo igual
        nuevaRuta[nuevaRuta.length - 1] = ""; // Agregar "" al final
        this.r = inserta(obj, this.r, nuevaRuta, 0);
    }

    private NodoML inserta(NodoML obj, NodoML r, String[] s, int nivel)
    {
        if (nivel == s.length - 1)
        {
            ListaDLML l = new ListaDLML();
            l.setR(r);
            l.inserta(obj);
            b = true;
            return l.getR();
        } else
        {
            NodoML aux = busca(r, s[nivel]);
            if (aux != null)
            {
                aux.setAbj(inserta(obj, aux.getAbj(), s, nivel + 1));
                if (b)
                {
                    obj.setArb(aux);
                    b = false;
                }
            }

            return r;
        }
    }

    public NodoML busca(NodoML aux, String s)
    {
        while (aux != null)
        {
            if (aux.getEt().equals(s))
            {
                return aux;
            } else
            {
                aux = aux.getSig();
            }
        }
        return null;
    }

    public String desp()
    {
        return desp(this.r, "");
    }

    private String desp(NodoML r, String t)
    {

        String s = "";
        while (r != null)
        {
            if (r.getArb() != null)
            {
                s += t + r.getEt() + "->" + r.getArb().getEt() + "\n";
            } else
            {
                s += t + r.getEt() + "\n";
            }
            s += desp(r.getAbj(), t + "\t");
            r = r.getSig();
        }
        return s;
    }

    public NodoML[] elimina(NodoML r, String[] s, int nivel)
    {
        NodoML[] resultado = new NodoML[2];
        if (r == null)
        {
            return resultado;
        } else
        {
            if (nivel == s.length - 1)
            {
                ListaDLML l = new ListaDLML();
                l.setR(r);
                NodoML eliminado = l.elimina(new NodoML(null, s[nivel]));
                resultado[0] = eliminado;
                resultado[0].setArb(null);
                resultado[1] = l.getR();
                return resultado;
            } else
            {
                NodoML aux = busca(r, s[nivel]);
                if (aux != null)
                {
                    NodoML[] resAbajo = elimina(aux.getAbj(), s, nivel + 1);
                    aux.setAbj(resAbajo[1]);
                    resultado[0] = resAbajo[0];
                }
                resultado[1] = r;
            }
            return resultado;
        }
    }

    public static void main(String[] args)
    {
        MultiListaDL m = new MultiListaDL();
        NodoML n1 = new NodoML("A", "A");
        NodoML n2 = new NodoML("B", "B");
        NodoML n3 = new NodoML("C", "C");

        NodoML n4 = new NodoML("a", "a");
        NodoML n5 = new NodoML("b", "b");
        NodoML n6 = new NodoML("c", "c");
        NodoML n7 = new NodoML("d", "d");
        NodoML n8 = new NodoML("e", "e");
        m.inserta(n1);
        m.inserta(n2);
        m.inserta(n3);
        String arr[]={"A"};
        m.inserta(n4, arr);
        m.inserta(n5, "B");
        m.inserta(n6, "C");
        String arr2[]={"C","c"};
        m.inserta(n7, arr2);
        m.inserta(n8, "C", "c", "d");
        System.out.println(m.desp());
    }
}

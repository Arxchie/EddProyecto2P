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

    public NodoML inserta(NodoML obj, NodoML r, String[] s, int nivel)
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

    private NodoML busca(NodoML aux, String s)
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

    public String desp(NodoML r, String t)
    {
        String s = "";
        while (r != null)
        {
            String superior = "";
            if (r.getArb() != null)
            {
                superior = " (Padre: " + r.getArb().getEt() + ")";
            }
            s += t + r.getEt() + superior + "\n" + desp(r.getAbj(), t + "\t");
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
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edd_hospital_.multi_lista;

/**
 *
 * @author Joabp
 */
public class ListaDLML
{

    private NodoML r;

    public ListaDLML(NodoML r)
    {
        this.r = r;
    }

    public ListaDLML()
    {
    }

    public NodoML getR()
    {
        return r;
    }

    public void setR(NodoML r)
    {
        this.r = r;
    }

    public void inserta(NodoML n)
    {
        if (n == null)
        {
            System.out.println("No se puede insertar un nodo nulo.");
        } else
        {
            if (r == null)
            {
                r = n;
            } else
            {
                if (r.getEt().compareTo(n.getEt()) > 0)
                {
                    n.setSig(r);
                    r.setAnt(n);
                    r = n;
                } else
                {
                    NodoML aux = r;
                    while (aux.getSig() != null)
                    {
                        if (aux.getSig().getEt().compareTo(n.getEt()) > 0)
                        {
                            n.setSig(aux.getSig());
                            n.setAnt(aux);
                            aux.getSig().setAnt(n);
                            aux.setSig(n);
                            return;
                        }
                        aux = aux.getSig();
                    }
                    aux.setSig(n);
                    n.setAnt(aux);
                }
            }
        }
    }

    public String desp()
    {
        if (r == null)
        {
            return "Lista vacia";
        }
        String s = "";
        NodoML aux = r;
        while (aux != null)
        {
            s += aux.getEt() + "\t";
            if (aux.getSig() == null)
            {
                break;
            } else
            {
                aux = aux.getSig();
            }
        }
        s += "\n";
        while (aux != null)
        {
            s += aux.getEt() + "\t";
            aux = aux.getAnt();
        }
        return s;
    }

    public String despRecursivo(NodoML aux, String s)
    {
        if (aux != null)
        {
            s += aux.getEt() + "\t" + despRecursivo(aux.getSig(), s);
        }
        return s;
    }

    public NodoML elimina(NodoML n)
    {
        if (r == null || r.getEt().compareTo(n.getEt()) > 0)
        {
            return null;
        }
        NodoML eliminado = null;
        if (r.getEt().compareTo(n.getEt()) == 0)
        {
            eliminado = r;
            r = r.getSig();
            if (r != null)
            {
                r.setAnt(null);
            }
            return eliminado;
        }
        NodoML aux = r;
        while (aux.getSig() != null)
        {
            if (aux.getSig().getEt().compareTo(n.getEt()) == 0)
            {
                eliminado = aux.getSig();
                aux.setSig(eliminado.getSig());
                if (eliminado.getSig() != null)
                {
                    eliminado.getSig().setAnt(aux);
                }
                eliminado.setAnt(null);
                eliminado.setSig(null);
                return eliminado;
            }
            aux = aux.getSig();
        }
        return eliminado;
    }
}

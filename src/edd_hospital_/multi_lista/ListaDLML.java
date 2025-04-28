/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edd_hospital_.multi_lista;

/**
 *
 * @author Joabp
 */
public class ListaDLML<T>
{

    private NodoML<T> r;

    public ListaDLML()
    {
    }

    public ListaDLML(NodoML<T> r)
    {
        this.r = r;
    }

    /**
     * @return the r
     */
    public NodoML<T> getR()
    {
        return r;
    }

    /**
     * @param r the r to set
     */
    public void setR(NodoML<T> r)
    {
        this.r = r;
    }

    public void inserta(NodoML<T> n)
    {
        if (n == null)
        {
            System.out.println("No se puede insertar un nodo nulo.");
        } else
        {
            if (getR() == null)
            {
                setR((NodoML<T>) n);
            } else
            {
                if (getR().getEt().compareTo(n.getEt()) > 0)
                {
                    n.setSig(getR());
                    getR().setAnt(n);
                    setR((NodoML<T>) n);
                } else
                {
                    NodoML aux = getR();
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
        if (getR() == null)
        {
            return "Lista vacia";
        }
        String s = "";
        NodoML aux = getR();
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

    public String despRecursivo(NodoML<T> aux, String s)
    {
        if (aux != null)
        {
            s += aux.getEt() + "\t" + despRecursivo(aux.getSig(), s);
        }
        return s;
    }

    public NodoML elimina(NodoML <T>n)
    {
        if (getR() == null || getR().getEt().compareTo(n.getEt()) > 0)
        {
            return null;
        }
        NodoML eliminado = null;
        if (getR().getEt().compareTo(n.getEt()) == 0)
        {
            eliminado = getR();
            setR((NodoML<T>) getR().getSig());
            if (getR() != null)
            {
                getR().setAnt(null);
            }
            return eliminado;
        }
        NodoML aux = getR();
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

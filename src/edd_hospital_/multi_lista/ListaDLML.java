/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edd_hospital_.multi_lista;

import java.io.Serializable;

/**
 *
 * @author Joabp
 */
public class ListaDLML<T> implements Serializable
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

    public NodoML buscaEnLista(NodoML aux, String s)
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

    public NodoML elimina(String et)
    {
        NodoML n = null;
        if (r == null)
        {
            System.out.println("lista vacia");
        } else
        {
            if (r.getEt().compareTo(et) > 0)
            {
                System.out.println("no existe el dato");
            } else
            {
                if (r.getEt().equals(et))
                {
                    n = r;
                    r = n.getSig();
                    if (r != null)
                    {
                        r.setAnt(null);
                    }
                    n.setSig(null);
                } else
                {
                    NodoML aux = r;
                    boolean b = true;
                    while (aux.getSig() != null && b)
                    {
                        if (aux.getSig().getEt().equals(et))
                        {
                            n = aux.getSig();
                            if (n.getSig() != null)
                            {
                                aux.getSig().getSig().setAnt(aux);
                            }
                            aux.setSig(n.getSig());
                            n.setSig(null);
                            n.setAnt(null);
                            b = false;
                        } else
                        {
                            if (aux.getSig().getEt().compareTo(et) > 0)
                            {
                                System.out.println("dato no encontrado");
                                b = false;
                            } else
                            {
                                aux = aux.getSig();
                            }
                        }
                    }
                    if (b)
                    {
                        System.out.println("no se encontro el dato");
                    }
                }
            }
        }
        return n;
    }
}

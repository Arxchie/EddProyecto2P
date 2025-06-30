/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edd_hospital_.modelo;

/**
 *
 * @author ivanh
 */
public class Bicola<T>
{

    private T arr[];
    private int a1 = -1;
    private int a2;

    public Bicola(T arr[])
    {
        this.arr = arr;
        a2 = arr.length;
    }

    /**
     * @return the arr
     */
    public T[] getArr()
    {
        return arr;
    }

    /**
     * @param arr the arr to set
     */
    public void setArr(T[] arr)
    {
        this.arr = arr;
    }

    public int getA1()
    {
        return a1;
    }

    public void setA1(int a1)
    {
        this.a1 = a1;
    }

    public int getA2()
    {
        return a2;
    }

    public void setA2(int a2)
    {
        this.a2 = a2;
    }

    public int inserta(T obj, int a, int lim, int f)
    {
        if (a + f == lim)
        {
            System.out.println("bicola llena");
            throw new IllegalStateException("bicola llena");
        } else
        {
            arr[a + f] = obj;
        }
        return a + f;
    }

    public Object[] elimina(int a, int lim, int f)
    {
        Object de[] = new Object[2];

        if (a == lim)
        {
            System.out.println("bicola vacia");
            throw new IllegalStateException("bicola vacia");

        } else
        {
            de[0] = arr[lim + f];
            for (int i = lim + f; i != a; i += f)
            {
                arr[i] = arr[i + f];
            }
            de[1] = a - f;
        }
        return de;
    }

    public boolean llena(int a, int lim, int f)
    {
        if (a + f == lim)
        {

            return true;
        }
        return false;
    }

    public void despIzq()
    {

        for (int i = 0; i < a2; i++)
        {
            if (arr[i] != null)
            {
                System.out.println(arr[i].toString());

            }
        }

    }

    public void despDer()
    {
        for (int i = arr.length - 1; i > a1; i--)
        {

            if (arr[i] != null)
            {
                System.out.println(arr[i].toString());

            }
        }
    }

}

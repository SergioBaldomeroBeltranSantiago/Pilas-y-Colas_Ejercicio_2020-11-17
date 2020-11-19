package Codigo;

interface Pila<E> {

    void push(E elem);

    void pop() throws PilaVacia;

    E top() throws PilaVacia;

    boolean isEmpty();
}

public class PilaSecuencial<E> implements Pila<E> {

    private Object[] pila;
    private int tope;

    public PilaSecuencial() {
        pila = new Object[10];
        tope = -1;
    }

    @Override
    public void push(E elem) {
        if (tope + 1 == pila.length) {
            Object[] temp = new Object[(pila.length * 2)];
            System.arraycopy(pila, 0, temp, 0, pila.length);
            pila = temp;
        }
        pila[++tope] = elem;
    }

    @Override
    public void pop() throws PilaVacia {
        if (tope == -1) {
            throw new PilaVacia();
        } else {
            tope--;
        }
    }

    @Override
    public E top() throws PilaVacia {
        if (tope == -1) {
            throw new PilaVacia();
        } else {
            return (E) pila[tope];
        }
    }

    @Override
    public boolean isEmpty() {
        return tope == -1;
    }
}

class PilaVacia extends Exception {
}

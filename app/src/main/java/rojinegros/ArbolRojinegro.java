package rojinegros;

import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.units.qual.A;

import javax.naming.OperationNotSupportedException;
import java.util.LinkedList;
import java.util.Queue;

public class ArbolRojinegro {
    @Getter
    @Setter
    private ArbolRojinegro izq;

    @Getter
    @Setter
    private ArbolRojinegro der;

    @Getter
    @Setter
    private int valor;

    @Getter
    @Setter
    private ArbolRojinegro father;

    @Getter
    @Setter
    private boolean black; //Si es negro True, en otro caso rojo

    public ArbolRojinegro(ArbolRojinegro izq,
                          ArbolRojinegro der,
                          int valor,
                          boolean black) {
        this.izq = izq;
        this.der = der;
        this.valor = valor;
        this.black = black;
    }

    public ArbolRojinegro() {
        this.izq = null;
        this.der = null;
        this.black = true;
    }
    /*
     * Metodos a implementar
     */

    public void insertar(int x) throws Exception {
        throw new OperationNotSupportedException();
    }

    public int maximo() throws Exception {
        ArbolRojinegro nodoActual = this;
        while (nodoActual.getDer() != null) {
            nodoActual = nodoActual.getDer();
        }
        return nodoActual.getValor();
    }

    public int minimo() throws Exception {
        ArbolRojinegro nodoActual = this;
        while (nodoActual.getIzq() != null) {
            nodoActual = nodoActual.getIzq();
        }
        return nodoActual.getValor();
    }

    public ArbolRojinegro search(int x) throws Exception {
        if (this.valor == x) {
            return this;
        } else {
            if (x >= this.valor) {
                if (this.getDer() != null) {
                    return this.getDer().search(x);
                } else {
                    return null;
                }
            } else {
                if (this.getIzq() != null) {
                    return this.getIzq().search(x);
                } else {
                    return null;
                }
            }
        }
    }

    public void rotacionIzquierda(int x) throws Exception {
        ArbolRojinegro pivote = search(x);
        ArbolRojinegro ramaIzq = new ArbolRojinegro(this.getIzq(),
                this.getDer().getIzq(),
                this.getValor(),
                true);
        ArbolRojinegro ramaDer = new ArbolRojinegro(null,
                null,
                this.getDer().getDer().getValor(),
                true);
        this.setValor(pivote.getValor());
        this.setIzq(ramaIzq);
        this.setDer(ramaDer);
    }

    public void rotacionDerecha(int x) throws  Exception {
        ArbolRojinegro pivote = search(x);
        ArbolRojinegro ramaIzq = new ArbolRojinegro(null,
                null,
                this.getIzq().getIzq().getValor(),
                true);
        ArbolRojinegro ramaDer = new ArbolRojinegro(this.getIzq().getDer(),
                this.getDer(),this.getValor(),true);
        this.setValor(pivote.getValor());
        this.setIzq(ramaIzq);
        this.setDer(ramaDer);

    }

    /*
     *  Area de pruebas, no modificar.
     */
    //Verificaciones
    /*
     * Busqueda por amplitud para verificar arbol.
     */
    public String bfs() {
        String salida = "";
        String separador = "";
        Queue<ArbolRojinegro> cola = new LinkedList<>();
        cola.add(this);
        while (cola.size() > 0) {
            ArbolRojinegro nodo = cola.poll();
            salida += separador + String.valueOf(nodo.getValor());
            separador = " ";
            if (nodo.getIzq() != null) {
                cola.add(nodo.getIzq());
            }
            if (nodo.getDer() != null) {
                cola.add(nodo.getDer());
            }
        }
        return salida;
    }

    /*
     * Recorrido inorder.
     * Verifica propiedad de orden.
     */
    public String inorden() {
        String recorrido = "";
        String separador = "";
        if (this.getIzq() != null) {
            recorrido += this.getIzq().inorden();
            separador = " ";
        }
        recorrido += separador + String.valueOf(this.getValor());
        if (this.getDer() != null) {
            recorrido += " " + this.getDer().inorden();
        }
        return recorrido;
    }

}
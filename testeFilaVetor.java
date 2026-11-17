// Etapa 1: Medindo o gargalo O(n) na Fila Ingênua
public class testeFilaVetor {

    static FilaVetor criarFilaVetor(int capacidade) {
        FilaVetor fila = new FilaVetor();
        fila.valores = new int[capacidade];
        fila.tamanho = 0;
        fila.totalDeslocamentos = 0;
        return fila;
    }

    static boolean filaVaziaVetor(FilaVetor fila) {
        return fila.tamanho == 0;
    }

    static boolean filaCheiaVetor(FilaVetor fila) {
        return fila.tamanho == fila.valores.length;
    }

    static void enqueueFilaVetor(FilaVetor fila, int valor) {
        if (filaCheiaVetor(fila)) {
            System.out.println("Fila cheia! Valor " + valor + " recusado.");
            return;
        }
        fila.valores[fila.tamanho] = valor;
        fila.tamanho++;
    }

    // O(n): desloca todos os elementos restantes uma posição para a esquerda
    static int dequeueFilaVetor(FilaVetor fila) {
        if (filaVaziaVetor(fila)) {
            System.out.println("Fila vazia!");
            return -1;
        }
        int removido = fila.valores[0];
        for (int i = 0; i < fila.tamanho - 1; i++) {
            fila.valores[i] = fila.valores[i + 1];
            fila.totalDeslocamentos++;
        }
        fila.tamanho--;
        return removido;
    }

    static void imprimirFilaVetor(FilaVetor fila) {
        System.out.print("Fila: [");
        for (int i = 0; i < fila.tamanho; i++) {
            System.out.print(fila.valores[i]);
            if (i < fila.tamanho - 1) System.out.print(", ");
        }
        System.out.println("] tamanho=" + fila.tamanho);
    }

    public static void main(String[] args) {
        FilaVetor fila = criarFilaVetor(5);

        for (int v = 10; v <= 50; v += 10) {
            enqueueFilaVetor(fila, v);
        }
        imprimirFilaVetor(fila);

        for (int i = 0; i < 5; i++) {
            int removido = dequeueFilaVetor(fila);
            System.out.println("Removido: " + removido
                    + " | deslocamentos acumulados: " + fila.totalDeslocamentos);
        }
        imprimirFilaVetor(fila);

        System.out.println("Total de deslocamentos realizados: " + fila.totalDeslocamentos);
    }
}

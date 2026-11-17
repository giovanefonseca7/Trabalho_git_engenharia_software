// Etapas 2 e 3: Buffer Circular O(1) e Sobrescrita Contínua
public class testeBufferCircular {

    static BufferCircular criarBufferCircular(int capacidade) {
        BufferCircular fila = new BufferCircular();
        fila.valores = new int[capacidade];
        fila.inicio = 0;
        fila.fim = 0;
        fila.tamanho = 0;
        return fila;
    }

    // ---------------- Etapa 2 ----------------

    // O(1): sem laços, apenas aritmética modular
    static void enqueueCircular(BufferCircular fila, int valor) {
        if (fila.tamanho == fila.valores.length) {
            System.out.println("Buffer cheio! Valor " + valor + " recusado.");
            return;
        }
        fila.valores[fila.fim] = valor;
        fila.fim = (fila.fim + 1) % fila.valores.length;
        fila.tamanho++;
    }

    // O(1): apenas avança o ponteiro inicio, sem deslocar elementos
    static int dequeueCircular(BufferCircular fila) {
        if (fila.tamanho == 0) {
            System.out.println("Buffer vazio!");
            return -1;
        }
        int removido = fila.valores[fila.inicio];
        fila.inicio = (fila.inicio + 1) % fila.valores.length;
        fila.tamanho--;
        return removido;
    }

    // Mostra o vetor físico, os índices e os elementos na ordem lógica da fila
    static void imprimirBufferCircular(BufferCircular fila) {
        System.out.print("Vetor: [");
        for (int i = 0; i < fila.valores.length; i++) {
            System.out.print(fila.valores[i]);
            if (i < fila.valores.length - 1) System.out.print(", ");
        }
        System.out.println("]  inicio=" + fila.inicio
                + "  fim=" + fila.fim + "  tamanho=" + fila.tamanho);

        System.out.print("Fila (ordem lógica): [");
        for (int i = 0; i < fila.tamanho; i++) {
            int indice = (fila.inicio + i) % fila.valores.length;
            System.out.print(fila.valores[indice]);
            if (i < fila.tamanho - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    // ---------------- Etapa 3 ----------------

    static void enqueueSobrescrita(BufferCircular fila, int valor) {
        if (fila.tamanho < fila.valores.length) {
            enqueueCircular(fila, valor);
            return;
        }
        // Buffer cheio: o mais antigo está em inicio (que coincide com fim)
        System.out.println("Aviso: buffer cheio, sobrescrevendo o valor antigo "
                + fila.valores[fila.inicio] + " por " + valor);
        fila.valores[fila.fim] = valor;
        fila.fim = (fila.fim + 1) % fila.valores.length;
        fila.inicio = (fila.inicio + 1) % fila.valores.length;
        // tamanho permanece inalterado
    }

    public static void main(String[] args) {
        System.out.println("===== Etapa 2: Buffer Circular O(1) =====");
        BufferCircular b = criarBufferCircular(5);
        for (int v = 1; v <= 5; v++) enqueueCircular(b, v);
        imprimirBufferCircular(b);
        enqueueCircular(b, 6); // deve ser recusado

        System.out.println("Removido: " + dequeueCircular(b));
        System.out.println("Removido: " + dequeueCircular(b));
        imprimirBufferCircular(b);

        enqueueCircular(b, 6); // reaproveita a posição 0 (volta ao início)
        enqueueCircular(b, 7); // reaproveita a posição 1
        imprimirBufferCircular(b);

        System.out.print("Esvaziando: ");
        while (b.tamanho > 0) System.out.print(dequeueCircular(b) + " ");
        System.out.println();
        imprimirBufferCircular(b);

        System.out.println();
        System.out.println("===== Etapa 3: Caso de Teste Obrigatório =====");
        BufferCircular fila = criarBufferCircular(5);
        enqueueSobrescrita(fila, 10);
        enqueueSobrescrita(fila, 20);
        enqueueSobrescrita(fila, 30);
        enqueueSobrescrita(fila, 40);
        enqueueSobrescrita(fila, 50);
        imprimirBufferCircular(fila);

        enqueueSobrescrita(fila, 60);
        enqueueSobrescrita(fila, 70);
        imprimirBufferCircular(fila);

        System.out.print("Ordem de saída: ");
        while (fila.tamanho > 0) {
            System.out.print(dequeueCircular(fila) + " ");
        }
        System.out.println();
        System.out.println("(esperado: 30 40 50 60 70)");
    }
}

// Etapa 2: estrutura do Buffer Circular (Ring Buffer)
public class BufferCircular {
    int[] valores;
    int inicio;   // head: posição do elemento mais antigo
    int fim;      // tail: próxima posição livre para inserir
    int tamanho;  // size: quantidade atual de elementos válidos
}

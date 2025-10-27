package structures.FP07;

import structures.FP03.LinkedStack;

public class Hanoi<T> {
    public void solveHanoi(int n, LinkedStack<T> origem, LinkedStack<T> destino, LinkedStack<T> auxiliar, char nomeOrigem, char nomeDestino, char nomeAuxiliar) {
        if (n == 1) {
            T disco = origem.pop();
            destino.push(disco);
            System.out.println("Mover " + disco + " de " + nomeOrigem + " para " + nomeDestino);
            return;
        }

        solveHanoi(n - 1, origem, auxiliar, destino, nomeOrigem, nomeAuxiliar, nomeDestino); // Passo 1: mover n-1 discos da origem pra o auxiliar

        // Passo 2: mover o > disco da origem pra o destino
        T disco = origem.pop();
        destino.push(disco);
        System.out.println("Mover " + disco + " de " + nomeOrigem + " para " + nomeDestino);

        solveHanoi(n - 1, auxiliar, destino, origem, nomeAuxiliar, nomeDestino, nomeOrigem); // Passo 3: mover n-1 discos do auxiliar pra o destino
    }
}

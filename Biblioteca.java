import java.util.ArrayList;
import java.util.List;

// =========================================================
// CLASSE GENÉRICA: Biblioteca<T>
//
// GENERICS permitem criar classes/métodos que funcionam
// com qualquer tipo, mantendo a segurança de tipos em
// tempo de compilação.
//
// <T> é um "tipo parâmetro" — um placeholder.
// Quando instanciar, você diz qual tipo usar:
//   Biblioteca<Livro>   -> T vira Livro
//   Biblioteca<Revista> -> T vira Revista
//   Biblioteca<String>  -> T vira String
//
// POR QUE ArrayList<int> NÃO COMPILA?
// Generics só funcionam com tipos de referência (objetos),
// nunca com tipos primitivos (int, double, boolean...).
// Use os Wrappers: Integer, Double, Boolean.
//   ERRADO:  ArrayList<int>     → ERRO de compilação
//   CORRETO: ArrayList<Integer> → OK!
// =========================================================
public class Biblioteca<T> {

    private final List<T> acervo = new ArrayList<>();
    private final String nome;

    public Biblioteca(String nome) {
        this.nome = nome;
    }

    // Adiciona qualquer item do tipo T ao acervo
    public void adicionar(T item) {
        acervo.add(item);
        System.out.println("  ✔ Adicionado: " + item);
    }

    // Lista todos os itens do acervo
    public void listar() {
        System.out.println("\n  === Acervo: " + nome + " ===");
        if (acervo.isEmpty()) {
            System.out.println("  (acervo vazio)");
            return;
        }
        for (int i = 0; i < acervo.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + acervo.get(i));
        }
        System.out.println("  Total: " + acervo.size() + " item(ns).");
    }

    // Retorna quantos itens há no acervo
    public int total() {
        return acervo.size();
    }

    // Retorna o acervo completo (para uso externo se necessário)
    public List<T> getAcervo() {
        return acervo;
    }
}

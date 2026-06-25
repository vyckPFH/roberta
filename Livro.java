// =========================================================
// RECORD: Livro
// Um record é uma classe imutável e compacta para guardar dados.
// O Java gera automaticamente: construtor, getters, equals(),
// hashCode() e toString() — sem precisar escrever nada disso!
//
// Sintaxe: record NomeRecord(tipo campo1, tipo campo2, ...)
// =========================================================
public record Livro(String titulo, String autor, int ano) {

    // VALIDAÇÃO NO CONSTRUTOR COMPACTO (opcional)
    // O record permite adicionar validações sem redeclarar os campos.
    public Livro {
        if (ano < 1000 || ano > 2100) {
            throw new IllegalArgumentException("Ano inválido: " + ano);
        }
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título não pode ser vazio.");
        }
    }

    // Você pode adicionar métodos extras normalmente.
    public String getResumo() {
        return "\"" + titulo + "\" por " + autor + " (" + ano + ")";
    }
}

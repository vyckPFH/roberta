// =========================================================
// ENUM: StatusLivro
// Um enum define um conjunto fixo de constantes nomeadas.
// Útil quando um valor só pode ser um de poucos estados.
// =========================================================
public enum StatusLivro {
    DISPONIVEL,   // Livro disponível para empréstimo
    EMPRESTADO,   // Livro atualmente emprestado
    MANUTENCAO;   // Livro em manutenção/restauro

    // Enums podem ter métodos. Aqui retornamos uma descrição amigável.
    public String getDescricao() {
        return switch (this) {
            case DISPONIVEL  -> "Disponível para empréstimo";
            case EMPRESTADO  -> "Atualmente emprestado";
            case MANUTENCAO  -> "Em manutenção";
        };
    }
}

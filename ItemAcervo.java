// =========================================================
// CLASSE BASE: ItemAcervo
// Usada para demonstrar a annotation @Override.
// Representa qualquer item que uma biblioteca pode ter.
// =========================================================
public class ItemAcervo {

    protected String nome;

    public ItemAcervo(String nome) {
        this.nome = nome;
    }

    // Método que será sobrescrito nas subclasses
    public String getDescricao() {
        return "[Item do Acervo] " + nome;
    }
}


// =========================================================
// SUBCLASSE: Revista
// Herda de ItemAcervo e sobrescreve getDescricao() com @Override.
//
// POR QUE @Override É ÚTIL?
// 1. Garante que você está realmente sobrescrevendo um método da
//    superclasse (se errar o nome, o compilador avisa).
// 2. Melhora a leitura do código — fica claro que é override.
// 3. Se a superclasse remover o método, o compilador avisa.
// =========================================================
class Revista extends ItemAcervo {

    private int edicao;
    private int ano;

    public Revista(String nome, int edicao, int ano) {
        super(nome); // chama o construtor de ItemAcervo
        this.edicao = edicao;
        this.ano    = ano;
    }

    @Override // <-- ANNOTATION: garante que é sobrescrita
    public String getDescricao() {
        return "[Revista] " + nome + " | Edição " + edicao + " | Ano: " + ano;
    }
}


// =========================================================
// SUBCLASSE: DVD
// Outra subclasse para mostrar polimorfismo com @Override.
// =========================================================
class DVD extends ItemAcervo {

    private String diretor;

    public DVD(String titulo, String diretor) {
        super(titulo);
        this.diretor = diretor;
    }

    @Override
    public String getDescricao() {
        return "[DVD] " + nome + " | Diretor: " + diretor;
    }
}

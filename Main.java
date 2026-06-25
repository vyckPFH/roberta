import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

// =========================================================
// Main.java — Sistema de Biblioteca
// Demonstra: Enum, Record, Wrapper/Autoboxing, Collections,
//            Iterator, Generics, Exceções, Datas, Annotations
// =========================================================
public class Main {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║      SISTEMA DE BIBLIOTECA - JAVA    ║");
        System.out.println("╚══════════════════════════════════════╝\n");

        demoEnum();
        demoRecord();
        demoWrapperAutoboxing();
        demoCollections();
        demoIterator();
        demoGenerics();
        demoExcecoes();
        demoDatas();
        demoAnnotations();
        demoBibliotecaGenerica();

        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║         FIM DO PROGRAMA              ║");
        System.out.println("╚══════════════════════════════════════╝");
    }

    // ─────────────────────────────────────────────────────
    // 1. ENUM
    // ─────────────────────────────────────────────────────
    static void demoEnum() {
        System.out.println("══════════════════════════════════════");
        System.out.println("  1. ENUM — StatusLivro");
        System.out.println("══════════════════════════════════════");

        // Declarando variável do tipo enum
        StatusLivro status = StatusLivro.DISPONIVEL;
        System.out.println("Status atual: " + status);
        System.out.println("Descrição   : " + status.getDescricao());

        // Alterando o status
        status = StatusLivro.EMPRESTADO;
        System.out.println("Novo status : " + status);
        System.out.println("Descrição   : " + status.getDescricao());

        // Listando todos os valores do enum com values()
        System.out.println("\nTodos os status possíveis:");
        for (StatusLivro s : StatusLivro.values()) {
            System.out.println("  - " + s + " → " + s.getDescricao());
        }
        System.out.println();
    }

    // ─────────────────────────────────────────────────────
    // 2. RECORD
    // ─────────────────────────────────────────────────────
    static void demoRecord() {
        System.out.println("══════════════════════════════════════");
        System.out.println("  2. RECORD — Livro");
        System.out.println("══════════════════════════════════════");

        // Criando dois objetos Livro
        Livro livro1 = new Livro("Dom Casmurro", "Machado de Assis", 1899);
        Livro livro2 = new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", 1954);

        // toString() já é gerado automaticamente pelo record!
        System.out.println("Livro 1: " + livro1);
        System.out.println("Livro 2: " + livro2);

        // Acessando campos via getters automáticos do record
        System.out.println("\nTítulo do livro 1: " + livro1.titulo());
        System.out.println("Autor  do livro 2: " + livro2.autor());
        System.out.println("Ano    do livro 1: " + livro1.ano());

        // Método extra que adicionamos ao record
        System.out.println("Resumo: " + livro1.getResumo());

        // equals() também é gerado pelo record
        Livro livro1Copia = new Livro("Dom Casmurro", "Machado de Assis", 1899);
        System.out.println("\nlivro1 == livro1Copia? " + livro1.equals(livro1Copia));
        System.out.println();
    }

    // ─────────────────────────────────────────────────────
    // 3. WRAPPER E AUTOBOXING
    // ─────────────────────────────────────────────────────
    static void demoWrapperAutoboxing() {
        System.out.println("══════════════════════════════════════");
        System.out.println("  3. WRAPPER E AUTOBOXING");
        System.out.println("══════════════════════════════════════");

        // AUTOBOXING: int primitivo → Integer (objeto Wrapper)
        // O Java converte automaticamente, sem precisar escrever new Integer(320)
        Integer paginas = 320;
        System.out.println("Autoboxing   → Integer paginas = 320; | valor: " + paginas);

        // UNBOXING: Integer (objeto) → int primitivo
        // O Java extrai o valor automaticamente
        int x = paginas;
        System.out.println("Unboxing     → int x = paginas;       | valor: " + x);

        // Métodos úteis da classe Wrapper Integer
        System.out.println("\nMétodos do Wrapper Integer:");
        System.out.println("  Integer.MAX_VALUE     : " + Integer.MAX_VALUE);
        System.out.println("  Integer.MIN_VALUE     : " + Integer.MIN_VALUE);
        System.out.println("  Integer.parseInt(\"42\"): " + Integer.parseInt("42"));
        System.out.println("  paginas.compareTo(300): " + paginas.compareTo(300)); // 1 = maior

        // Outros Wrappers
        Double preco = 49.90;        // autoboxing double → Double
        Boolean disponivel = true;   // autoboxing boolean → Boolean
        System.out.println("\nDouble  preco      = 49.90 → " + preco);
        System.out.println("Boolean disponivel = true  → " + disponivel);

        // CUIDADO com null em unboxing → causa NullPointerException!
        System.out.println("\n⚠ CUIDADO: Integer nulo em unboxing causa NullPointerException!");
        try {
            Integer nulo = null;
            int valor = nulo; // NullPointerException aqui!
        } catch (NullPointerException e) {
            System.out.println("  NullPointerException capturada com sucesso.");
        }
        System.out.println();
    }

    // ─────────────────────────────────────────────────────
    // 4. COLLECTIONS — ArrayList
    // ─────────────────────────────────────────────────────
    static void demoCollections() {
        System.out.println("══════════════════════════════════════");
        System.out.println("  4. COLLECTIONS — ArrayList<Livro>");
        System.out.println("══════════════════════════════════════");

        // Criando o ArrayList (vetor dinâmico que cresce automaticamente)
        ArrayList<Livro> colecao = new ArrayList<>();

        // Adicionando cinco livros
        colecao.add(new Livro("Dom Casmurro",          "Machado de Assis",  1899));
        colecao.add(new Livro("O Alquimista",           "Paulo Coelho",      1988));
        colecao.add(new Livro("Clean Code",             "Robert C. Martin",  2008));
        colecao.add(new Livro("Java: The Complete Ref", "Herbert Schildt",   2018));
        colecao.add(new Livro("Effective Java",         "Joshua Bloch",      2018));

        System.out.println("Lista com " + colecao.size() + " livros:\n");

        // Percorrendo com for-each (jeito mais comum e limpo)
        for (Livro l : colecao) {
            System.out.println("  → " + l.getResumo());
        }

        // Por que ArrayList em vez de array simples?
        // Array:     String[] v = new String[5]; — tamanho FIXO!
        // ArrayList: cresce e diminui dinamicamente, tem métodos prontos
        //            (add, remove, contains, size, sort, etc.)
        System.out.println("\n  [ArrayList vs Array]");
        System.out.println("  Array comum: tamanho fixo, sem métodos extras.");
        System.out.println("  ArrayList  : tamanho dinâmico, métodos prontos (add, remove, sort…)");
        System.out.println();
    }

    // ─────────────────────────────────────────────────────
    // 5. ITERATOR
    // ─────────────────────────────────────────────────────
    static void demoIterator() {
        System.out.println("══════════════════════════════════════");
        System.out.println("  5. ITERATOR — Removendo livros < 2015");
        System.out.println("══════════════════════════════════════");

        ArrayList<Livro> colecao = new ArrayList<>();
        colecao.add(new Livro("Dom Casmurro",          "Machado de Assis",  1899));
        colecao.add(new Livro("O Alquimista",           "Paulo Coelho",      1988));
        colecao.add(new Livro("Clean Code",             "Robert C. Martin",  2008));
        colecao.add(new Livro("Java: The Complete Ref", "Herbert Schildt",   2018));
        colecao.add(new Livro("Effective Java",         "Joshua Bloch",      2018));

        System.out.println("Antes da remoção (" + colecao.size() + " livros):");
        for (Livro l : colecao) System.out.println("  → " + l.getResumo());

        // ITERATOR: forma segura de remover elementos durante a iteração.
        // Usar colecao.remove() dentro de um for-each causa ConcurrentModificationException!
        Iterator<Livro> iterator = colecao.iterator();
        while (iterator.hasNext()) {       // hasNext(): ainda tem próximo?
            Livro l = iterator.next();     // next(): pega o próximo item
            if (l.ano() < 2015) {
                iterator.remove();         // remove() de forma SEGURA
            }
        }

        System.out.println("\nApós remover livros anteriores a 2015 (" + colecao.size() + " restantes):");
        for (Livro l : colecao) System.out.println("  → " + l.getResumo());
        System.out.println();
    }

    // ─────────────────────────────────────────────────────
    // 6. GENERICS — Explicação e correção
    // ─────────────────────────────────────────────────────
    static void demoGenerics() {
        System.out.println("══════════════════════════════════════");
        System.out.println("  6. GENERICS");
        System.out.println("══════════════════════════════════════");

        System.out.println("Por que ArrayList<int> NÃO compila?");
        System.out.println("  Generics exigem tipos de REFERÊNCIA (objetos).");
        System.out.println("  'int' é primitivo. Use o Wrapper Integer:");
        System.out.println("    ERRADO:  ArrayList<int>     → erro!");
        System.out.println("    CORRETO: ArrayList<Integer> → OK!\n");

        // Código correto com Wrapper
        ArrayList<Integer> numeros = new ArrayList<>();
        numeros.add(10);   // autoboxing int → Integer
        numeros.add(20);
        numeros.add(30);
        System.out.println("ArrayList<Integer>: " + numeros);

        // Genérico funciona para qualquer tipo de referência
        ArrayList<String> nomes = new ArrayList<>();
        nomes.add("Alice");
        nomes.add("Bruno");
        System.out.println("ArrayList<String> : " + nomes);
        System.out.println();
    }

    // ─────────────────────────────────────────────────────
    // 7. TRATAMENTO DE EXCEÇÕES
    // ─────────────────────────────────────────────────────
    static void demoExcecoes() {
        System.out.println("══════════════════════════════════════");
        System.out.println("  7. TRATAMENTO DE EXCEÇÕES");
        System.out.println("══════════════════════════════════════");

        Scanner scanner = new Scanner(System.in);
        int anoValido = -1;

        while (anoValido == -1) {
            System.out.print("Digite o ano de publicação do livro: ");
            String entrada = scanner.nextLine().trim();

            try {
                // parseInt lança NumberFormatException se a entrada não for número
                int ano = Integer.parseInt(entrada);

                if (ano < 1000 || ano > 2100) {
                    // Exceção personalizada com mensagem clara
                    throw new IllegalArgumentException("Ano fora do intervalo válido (1000–2100).");
                }

                anoValido = ano;
                System.out.println("  ✔ Ano registrado com sucesso: " + anoValido);

            } catch (NumberFormatException e) {
                // Captura quando o usuário digita texto em vez de número
                System.out.println("  ✘ Erro: \"" + entrada + "\" não é um número. Tente novamente.");

            } catch (IllegalArgumentException e) {
                // Captura o ano fora do range
                System.out.println("  ✘ Erro: " + e.getMessage() + " Tente novamente.");

            } finally {
                // finally SEMPRE executa, independente de erro ou não
                System.out.println("  [finally] Verificação concluída.");
            }
        }
        System.out.println();
    }

    // ─────────────────────────────────────────────────────
    // 8. DATAS — LocalDate
    // ─────────────────────────────────────────────────────
    static void demoDatas() {
        System.out.println("══════════════════════════════════════");
        System.out.println("  8. DATAS — LocalDate");
        System.out.println("══════════════════════════════════════");

        // Data atual do sistema
        LocalDate hoje = LocalDate.now();
        System.out.println("Data de hoje    : " + hoje);
        System.out.println("Ano atual       : " + hoje.getYear());
        System.out.println("Mês atual       : " + hoje.getMonthValue() + " (" + hoje.getMonth() + ")");
        System.out.println("Dia atual       : " + hoje.getDayOfMonth());

        // Calculando há quantos anos livros foram publicados
        System.out.println("\nIdade de alguns livros:");
        Livro[] livros = {
            new Livro("Dom Casmurro",    "Machado de Assis", 1899),
            new Livro("O Alquimista",    "Paulo Coelho",     1988),
            new Livro("Effective Java",  "Joshua Bloch",     2018)
        };

        for (Livro l : livros) {
            LocalDate publicacao = LocalDate.of(l.ano(), 1, 1);
            // Period.between calcula a diferença entre duas datas
            int anos = Period.between(publicacao, hoje).getYears();
            System.out.printf("  %-25s publicado em %d → %d ano(s) atrás%n",
                "\"" + l.titulo() + "\"", l.ano(), anos);
        }
        System.out.println();
    }

    // ─────────────────────────────────────────────────────
    // 9. ANNOTATIONS — @Override
    // ─────────────────────────────────────────────────────
    static void demoAnnotations() {
        System.out.println("══════════════════════════════════════");
        System.out.println("  9. ANNOTATIONS — @Override");
        System.out.println("══════════════════════════════════════");

        // Polimorfismo: referência do tipo base aponta para subclasses
        ItemAcervo[] itens = {
            new ItemAcervo("Item Genérico"),
            new Revista("Veja", 2891, 2023),
            new DVD("Matrix", "Wachowski")
        };

        for (ItemAcervo item : itens) {
            // Cada objeto chama o getDescricao() da SUA classe
            System.out.println("  " + item.getDescricao());
        }

        System.out.println();
        System.out.println("  Por que @Override é útil?");
        System.out.println("  1. O compilador confirma que você está realmente sobrescrevendo.");
        System.out.println("  2. Evita bugs por erro de digitação no nome do método.");
        System.out.println("  3. Torna o código mais legível para outros devs.");
        System.out.println();
    }

    // ─────────────────────────────────────────────────────
    // 10. CLASSE GENÉRICA Biblioteca<T>
    // ─────────────────────────────────────────────────────
    static void demoBibliotecaGenerica() {
        System.out.println("══════════════════════════════════════");
        System.out.println("  10. CLASSE GENÉRICA Biblioteca<T>");
        System.out.println("══════════════════════════════════════");

        // Biblioteca de Livros
        System.out.println("\n[Biblioteca de Livros]");
        Biblioteca<Livro> bibLivros = new Biblioteca<>("Acervo Principal");
        bibLivros.adicionar(new Livro("Clean Code",    "Robert C. Martin", 2008));
        bibLivros.adicionar(new Livro("Effective Java","Joshua Bloch",     2018));
        bibLivros.listar();

        // A MESMA classe funciona para Revistas!
        System.out.println("\n[Biblioteca de Revistas]");
        Biblioteca<Revista> bibRevistas = new Biblioteca<>("Periódicos");
        bibRevistas.adicionar(new Revista("National Geographic", 350, 2022));
        bibRevistas.adicionar(new Revista("Veja",                2891, 2023));
        bibRevistas.listar();

        // E também para Strings!
        System.out.println("\n[Biblioteca de Strings — só pra provar que funciona com qualquer tipo]");
        Biblioteca<String> bibStrings = new Biblioteca<>("Palavras");
        bibStrings.adicionar("Java");
        bibStrings.adicionar("Generics");
        bibStrings.adicionar("Records");
        bibStrings.listar();

        System.out.println();
    }
}

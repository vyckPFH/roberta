# 🛠️ README — Como Implementar e Executar o Projeto

> Guia prático de como rodar o sistema e como implementar cada funcionalidade pedida.

---

## 📁 Estrutura do Projeto

```
biblioteca/
├── src/
│   ├── StatusLivro.java   → Enum com os status possíveis
│   ├── Livro.java         → Record com titulo, autor, ano
│   ├── ItemAcervo.java    → Classe base + subclasses (demo @Override)
│   ├── Biblioteca.java    → Classe genérica Biblioteca<T>
│   └── Main.java          → Programa principal com TODOS os demos
├── out/                   → Arquivos .class compilados (criado pelo javac)
├── README_CONTEUDO.md     → Explicação teórica dos conceitos
└── README_IMPLEMENTACAO.md → Este arquivo
```

---

## ▶️ Como Compilar e Executar

### Pré-requisito
Tenha o **Java 21** (ou 17+) instalado. Verifique com:
```bash
java -version
```

### Compilar todos os arquivos de uma vez
```bash
mkdir -p out
javac src/*.java -d out/
```

### Executar o programa
```bash
cd out
java Main
```

### Ou em um único comando (da pasta raiz do projeto)
```bash
javac src/*.java -d out/ && java -cp out Main
```

---

## 🔨 Como Implementar Cada Funcionalidade

### ✅ 1. ENUM — StatusLivro

**Arquivo:** `src/StatusLivro.java`

```java
public enum StatusLivro {
    DISPONIVEL, EMPRESTADO, MANUTENCAO;
}
```

**Declarando variável no Main:**
```java
StatusLivro status = StatusLivro.DISPONIVEL;
System.out.println(status);             // DISPONIVEL
System.out.println(status.name());      // "DISPONIVEL"

// Percorrendo todos os valores
for (StatusLivro s : StatusLivro.values()) {
    System.out.println(s);
}
```

---

### ✅ 2. RECORD — Livro

**Arquivo:** `src/Livro.java`

```java
public record Livro(String titulo, String autor, int ano) { }
```

**Criando objetos e imprimindo no Main:**
```java
Livro livro1 = new Livro("Dom Casmurro", "Machado de Assis", 1899);
Livro livro2 = new Livro("O Alquimista", "Paulo Coelho", 1988);

System.out.println(livro1);         // toString() automático
System.out.println(livro2.titulo()); // getter automático
System.out.println(livro1.equals(livro2)); // equals() automático
```

---

### ✅ 3. WRAPPER E AUTOBOXING

**Dentro de qualquer método no Main:**

```java
// a) Autoboxing: int primitivo → Integer objeto (automático)
Integer paginas = 320;

// b) Unboxing: Integer objeto → int primitivo (automático)
int x = paginas;

// Demonstração completa:
Integer paginas = 320;       // autoboxing
int x = paginas;             // unboxing

System.out.println("paginas = " + paginas); // 320
System.out.println("x       = " + x);       // 320

// Método da classe Wrapper
System.out.println(Integer.parseInt("42"));  // 42
System.out.println(Integer.MAX_VALUE);       // 2147483647
```

**Respostas para as perguntas:**

**a) O que aconteceu em `Integer paginas = 320`?**  
Autoboxing: o Java converteu automaticamente o literal `int 320` em um objeto `Integer`. Equivale a `Integer.valueOf(320)`.

**b) O que acontece em `int x = paginas`?**  
Unboxing: o Java extraiu o valor primitivo do objeto `Integer`. Equivale a `paginas.intValue()`.

---

### ✅ 4. COLLECTIONS — ArrayList<Livro>

**Dentro de um método no Main:**

```java
import java.util.ArrayList;

ArrayList<Livro> colecao = new ArrayList<>();

// Adicionando cinco livros
colecao.add(new Livro("Dom Casmurro",    "Machado de Assis", 1899));
colecao.add(new Livro("O Alquimista",    "Paulo Coelho",     1988));
colecao.add(new Livro("Clean Code",      "Robert C. Martin", 2008));
colecao.add(new Livro("Effective Java",  "Joshua Bloch",     2018));
colecao.add(new Livro("Java Completo",   "Herbert Schildt",  2019));

// Percorrendo e imprimindo
for (Livro l : colecao) {
    System.out.println(l);
}
```

**Por que ArrayList em vez de vetor (array)?**  
- Array tem tamanho fixo definido na criação: `Livro[] v = new Livro[5]`
- ArrayList cresce dinamicamente conforme você adiciona elementos
- ArrayList tem métodos prontos: `add()`, `remove()`, `contains()`, `sort()`, `size()`
- Com array, você gerencia tamanho e posições manualmente — trabalhoso e propenso a erros

---

### ✅ 5. ITERATOR — Remover livros antes de 2015

**Logo após criar o ArrayList da etapa 4:**

```java
import java.util.Iterator;

Iterator<Livro> iterator = colecao.iterator();
while (iterator.hasNext()) {
    Livro l = iterator.next();
    if (l.ano() < 2015) {
        iterator.remove(); // remoção SEGURA durante iteração
    }
}

// Imprimir após a remoção
System.out.println("Livros publicados a partir de 2015:");
for (Livro l : colecao) {
    System.out.println(l);
}
```

> ⚠️ **Nunca** use `colecao.remove()` dentro de um `for-each` — causa `ConcurrentModificationException`!

---

### ✅ 6. GENERICS — Explicação e correção

**Explicação para a prova:**

`ArrayList<int>` não compila porque **Generics aceitam apenas tipos de referência** (objetos). `int` é um tipo primitivo, não um objeto.

**Correção:**
```java
// ERRADO — não compila:
ArrayList<int> numeros = new ArrayList<>();

// CORRETO — usa o Wrapper:
ArrayList<Integer> numeros = new ArrayList<>();
numeros.add(10);  // autoboxing int → Integer
numeros.add(20);
System.out.println(numeros); // [10, 20]
```

---

### ✅ 7. TRATAMENTO DE EXCEÇÕES

**Dentro de um método no Main:**

```java
import java.util.Scanner;

Scanner scanner = new Scanner(System.in);
int anoValido = -1;

while (anoValido == -1) {
    System.out.print("Digite o ano de publicação: ");
    String entrada = scanner.nextLine();

    try {
        int ano = Integer.parseInt(entrada); // NumberFormatException se não for número

        if (ano < 1000 || ano > 2100) {
            throw new IllegalArgumentException("Ano fora do intervalo.");
        }

        anoValido = ano;
        System.out.println("Ano válido: " + anoValido);

    } catch (NumberFormatException e) {
        System.out.println("Erro: '" + entrada + "' não é um número válido!");

    } catch (IllegalArgumentException e) {
        System.out.println("Erro: " + e.getMessage());

    } finally {
        System.out.println("Tentativa finalizada.");
    }
}
```

---

### ✅ 8. DATAS — LocalDate

**Imports necessários:**
```java
import java.time.LocalDate;
import java.time.Period;
```

**Dentro de um método:**
```java
// Data atual
LocalDate hoje = LocalDate.now();
System.out.println("Hoje: " + hoje);

// Calculando anos desde a publicação
Livro livro = new Livro("Dom Casmurro", "Machado de Assis", 1899);

LocalDate dataPublicacao = LocalDate.of(livro.ano(), 1, 1);
int anosAtras = Period.between(dataPublicacao, hoje).getYears();

System.out.println("\"" + livro.titulo() + "\" foi publicado há " + anosAtras + " anos.");
```

---

### ✅ 9. ANNOTATIONS — @Override

**Arquivo:** `src/ItemAcervo.java`

```java
// Superclasse
public class ItemAcervo {
    protected String nome;

    public ItemAcervo(String nome) { this.nome = nome; }

    public String getDescricao() {
        return "Item: " + nome;
    }
}

// Subclasse sobrescrevendo o método
class Revista extends ItemAcervo {
    private int edicao;

    public Revista(String nome, int edicao) {
        super(nome);
        this.edicao = edicao;
    }

    @Override  // <-- annotation aqui
    public String getDescricao() {
        return "Revista: " + nome + " - Edição " + edicao;
    }
}
```

**Testando no Main:**
```java
ItemAcervo item = new Revista("Veja", 2891);
System.out.println(item.getDescricao()); // chama o método da Revista
```

**Por que @Override é útil?**
1. O compilador confirma que o método existe na superclasse.
2. Se você errar o nome (`getdescricao`), o compilador avisa na hora.
3. Documenta visualmente que há herança.

---

### ✅ 10. CLASSE GENÉRICA Biblioteca<T>

**Arquivo:** `src/Biblioteca.java`

```java
import java.util.ArrayList;
import java.util.List;

public class Biblioteca<T> {
    private List<T> acervo = new ArrayList<>();

    public void adicionar(T item) {
        acervo.add(item);
    }

    public void listar() {
        for (T item : acervo) {
            System.out.println(item);
        }
    }
}
```

**Usando no Main:**
```java
// Com Livros
Biblioteca<Livro> bibLivros = new Biblioteca<>();
bibLivros.adicionar(new Livro("Clean Code", "Robert", 2008));
bibLivros.listar();

// A MESMA classe com Strings
Biblioteca<String> bibStrings = new Biblioteca<>();
bibStrings.adicionar("Java");
bibStrings.adicionar("Generics");
bibStrings.listar();
```

---

## 🧪 Como Adicionar Suas Próprias Funcionalidades

Para estudar, experimente modificar o projeto:

1. **Adicionar método `buscar()` em `Biblioteca<T>`** que recebe o índice e retorna o item.
2. **Adicionar `StatusLivro` ao Record `Livro`** — precisará mudar de record para class (records são imutáveis).
3. **Criar um enum `Genero`** com FICCAO, TECNICO, BIOGRAFIA, INFANTIL.
4. **Criar um método que busca livro por título** usando `Iterator` ou `for-each`.
5. **Usar `Collections.sort()`** para ordenar livros por ano.

---

## 🔑 Pontos-Chave para a Prova

| Conceito | O que saber |
|---|---|
| Enum | Conjunto fixo de constantes; `.values()` lista todos |
| Record | Imutável; getters chamados `campo()` sem "get" |
| Autoboxing | `Integer i = 42` — automático, nenhum cast necessário |
| Unboxing | `int x = integer` — automático; cuidado com `null` |
| ArrayList vs Array | ArrayList é dinâmico e tem métodos; array é fixo |
| Iterator | Único jeito seguro de remover durante iteração |
| `ArrayList<int>` | Não compila! Use `ArrayList<Integer>` |
| try-catch-finally | `finally` sempre executa; `catch` só se houver erro |
| LocalDate | `now()` = hoje; `Period.between()` = diferença |
| @Override | Garante que método existe na superclasse |
| `Biblioteca<T>` | `T` é substituído pelo tipo real na instanciação |

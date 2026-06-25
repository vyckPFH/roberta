# 📚 README — Conteúdo do Bimestre: Java Moderno

> Guia completo dos conceitos usados no sistema de Biblioteca.  
> Leia antes de estudar o código!

---

## 1. 🏷️ ENUM

Um `enum` (enumeração) define um **conjunto fixo e nomeado de constantes**.  
Use quando um valor só pode ser **um de poucos estados possíveis**.

```java
public enum StatusLivro {
    DISPONIVEL, EMPRESTADO, MANUTENCAO;
}
```

**Declarando uma variável enum:**
```java
StatusLivro status = StatusLivro.DISPONIVEL;
```

**Por que usar enum em vez de `int` ou `String`?**
- `int status = 1` → o que significa 1? 2? Ninguém sabe.
- `String status = "disponivel"` → e se alguém digitar "Disponivel" com D maiúsculo?
- `StatusLivro.DISPONIVEL` → claro, seguro, sem erros de digitação.

**Recursos úteis:**
```java
StatusLivro.values()       // retorna todos os valores como array
StatusLivro.EMPRESTADO.name()      // → "EMPRESTADO" (String)
StatusLivro.valueOf("EMPRESTADO")  // String → enum
```

---

## 2. 📦 RECORD

Um `record` é uma **classe imutável e compacta** para guardar dados.  
Introduzido no Java 16 (JEP 395).

```java
public record Livro(String titulo, String autor, int ano) { }
```

O Java gera **automaticamente** para você:
| O que é gerado | Descrição |
|---|---|
| Construtor | `new Livro("Dom Casmurro", "Machado de Assis", 1899)` |
| Getters | `livro.titulo()`, `livro.autor()`, `livro.ano()` |
| `toString()` | `Livro[titulo=Dom Casmurro, autor=..., ano=1899]` |
| `equals()` | Compara campo a campo |
| `hashCode()` | Baseado nos campos |

**Imutável** = após criar o objeto, os campos **não mudam**.  
Isso torna o código mais seguro e previsível.

**Você pode adicionar:**
- Construtor compacto (validações)
- Métodos extras
- Implementar interfaces

---

## 3. 🔢 WRAPPER E AUTOBOXING

### Tipos primitivos vs. Wrappers

| Primitivo | Wrapper (objeto) |
|---|---|
| `int`     | `Integer`  |
| `double`  | `Double`   |
| `boolean` | `Boolean`  |
| `char`    | `Character`|
| `long`    | `Long`     |

**Wrappers existem porque Generics (`ArrayList<T>`) exigem objetos**, não primitivos.

### Autoboxing
Conversão **automática** de primitivo → objeto (Wrapper).
```java
Integer paginas = 320;
// Equivale a: Integer paginas = Integer.valueOf(320);
// O Java faz isso automaticamente!
```

### Unboxing
Conversão **automática** de objeto (Wrapper) → primitivo.
```java
int x = paginas;
// Equivale a: int x = paginas.intValue();
```

### ⚠️ Cuidado!
```java
Integer nulo = null;
int valor = nulo; // NullPointerException! O Java tenta chamar .intValue() em null
```

### Métodos úteis do Wrapper
```java
Integer.parseInt("42")      // String → int
Integer.MAX_VALUE           // 2147483647
Integer.toBinaryString(10)  // "1010"
Integer.compare(5, 10)      // -1 (5 < 10)
```

---

## 4. 🗃️ COLLECTIONS — ArrayList

`ArrayList` é uma **lista dinâmica** baseada em array interno.  
Faz parte do Java Collections Framework (`java.util`).

```java
ArrayList<Livro> colecao = new ArrayList<>();
colecao.add(new Livro("Clean Code", "Robert C. Martin", 2008));
colecao.size();        // quantidade de elementos
colecao.get(0);        // pega elemento pelo índice
colecao.remove(0);     // remove pelo índice
colecao.contains(obj); // verifica se contém
```

### Por que usar ArrayList em vez de array comum?

| Array `Livro[]`             | `ArrayList<Livro>`               |
|-----------------------------|----------------------------------|
| Tamanho **fixo** na criação | Tamanho **dinâmico**, cresce sozinho |
| Sem métodos de busca/remoção | `add()`, `remove()`, `contains()`, `sort()` |
| Sintaxe verbosa para inserir | Simples: `lista.add(item)` |
| Pode ter posições vazias (null) | Sem buracos |

### Percorrendo:
```java
// For-each (mais limpo)
for (Livro l : colecao) { System.out.println(l); }

// For com índice (quando precisa do índice)
for (int i = 0; i < colecao.size(); i++) { ... }
```

---

## 5. 🔄 ITERATOR

`Iterator` é uma interface para **percorrer e modificar** coleções de forma segura.

**Por que não usar for-each para remover?**
```java
for (Livro l : colecao) {
    colecao.remove(l); // ❌ ConcurrentModificationException!
}
```
O for-each usa Iterator internamente, mas não permite modificação durante a iteração.

**Solução com Iterator:**
```java
Iterator<Livro> it = colecao.iterator();
while (it.hasNext()) {
    Livro l = it.next();
    if (l.ano() < 2015) {
        it.remove(); // ✅ Seguro! Remove o elemento atual
    }
}
```

| Método | O que faz |
|---|---|
| `hasNext()` | Retorna `true` se ainda há elementos |
| `next()` | Avança e retorna o próximo elemento |
| `remove()` | Remove o elemento retornado por `next()` |

---

## 6. 🔡 GENERICS

Generics permitem criar classes e métodos que funcionam com **qualquer tipo**, mantendo a **segurança em tempo de compilação**.

```java
public class Biblioteca<T> {
    private List<T> acervo = new ArrayList<>();
    public void adicionar(T item) { acervo.add(item); }
}

Biblioteca<Livro>   b1 = new Biblioteca<>("Livros");
Biblioteca<Revista> b2 = new Biblioteca<>("Revistas");
```

### Por que `ArrayList<int>` não compila?

Generics funcionam **somente com tipos de referência** (objetos).  
`int`, `double`, `boolean` são **tipos primitivos** — não são objetos.

```java
ArrayList<int>     lista = ...; // ❌ ERRO de compilação
ArrayList<Integer> lista = ...; // ✅ Correto — Integer é objeto
```

Use os Wrappers correspondentes:
```
int     → Integer
double  → Double
char    → Character
boolean → Boolean
```

---

## 7. ⚠️ TRATAMENTO DE EXCEÇÕES

Exceções são **erros em tempo de execução**. Com `try-catch`, o programa não trava.

```java
try {
    // código que pode lançar exceção
    int ano = Integer.parseInt("abc"); // NumberFormatException

} catch (NumberFormatException e) {
    // trata o erro específico
    System.out.println("Não é um número: " + e.getMessage());

} catch (IllegalArgumentException e) {
    // outro tipo de erro
    System.out.println("Valor inválido: " + e.getMessage());

} finally {
    // SEMPRE executa, com erro ou sem erro
    System.out.println("Bloco finally executado.");
}
```

### Exceções comuns em Java

| Exceção | Quando ocorre |
|---|---|
| `NumberFormatException` | `Integer.parseInt("abc")` |
| `NullPointerException` | Chamar método em referência nula |
| `ArrayIndexOutOfBoundsException` | Índice fora do range do array |
| `ClassCastException` | Cast inválido entre tipos |
| `IllegalArgumentException` | Argumento inválido passado a método |

### Hierarquia resumida:
```
Throwable
 ├── Error (não capture — JVM fora de controle)
 └── Exception
      ├── IOException (checked — deve declarar ou capturar)
      └── RuntimeException (unchecked — opcional capturar)
           ├── NumberFormatException
           ├── NullPointerException
           └── IllegalArgumentException
```

---

## 8. 📅 DATAS — LocalDate

`LocalDate` (pacote `java.time`, Java 8+) representa uma **data sem horário**.  
Substitui o antigo `Date` e `Calendar` (que eram confusos).

```java
LocalDate hoje = LocalDate.now();           // data atual do sistema
LocalDate publicacao = LocalDate.of(1988, 1, 1); // 1 de janeiro de 1988

// Calculando diferença entre datas
Period periodo = Period.between(publicacao, hoje);
int anos = periodo.getYears();
```

### Métodos úteis:
```java
hoje.getYear()        // ano (int)
hoje.getMonth()       // mês (Month enum)
hoje.getMonthValue()  // mês (int, 1–12)
hoje.getDayOfMonth()  // dia (int)
hoje.isBefore(outra)  // true se anterior
hoje.isAfter(outra)   // true se posterior
hoje.plusDays(30)     // adiciona 30 dias
hoje.minusYears(1)    // subtrai 1 ano
```

---

## 9. 🏷️ ANNOTATIONS

Annotations são **metadados** adicionados ao código para guiar o compilador, frameworks ou ferramentas.

### @Override
```java
class Revista extends ItemAcervo {
    @Override
    public String getDescricao() {
        return "Revista: " + nome;
    }
}
```

**Por que @Override é útil?**
1. **Garante** que você está sobrescrevendo um método real da superclasse.
2. Se você **errar o nome** (`getdescricao` em vez de `getDescricao`), o compilador **avisa**.
3. Se a superclasse **remover o método**, o compilador **avisa**.
4. **Documenta** para outros devs que aquele método é uma sobrescrita.

### Outras annotations comuns:
| Annotation | Onde aparece | Para quê |
|---|---|---|
| `@Override` | Métodos | Garante que está sobrescrevendo |
| `@Deprecated` | Classes/métodos | Avisa que está desatualizado |
| `@SuppressWarnings` | Classes/métodos | Suprime avisos do compilador |
| `@FunctionalInterface` | Interfaces | Garante apenas 1 método abstrato |
| `@Autowired` | Spring Boot | Injeção de dependência |
| `@Entity` | JPA/Hibernate | Mapeamento para banco de dados |

---

## 10. 🔡 CLASSE GENÉRICA

Uma classe genérica usa `<T>` como **placeholder de tipo**:

```java
public class Biblioteca<T> {
    private List<T> acervo = new ArrayList<>();

    public void adicionar(T item) { acervo.add(item); }

    public void listar() {
        for (T item : acervo) { System.out.println(item); }
    }
}
```

A mesma classe funciona para qualquer tipo:
```java
Biblioteca<Livro>   bLivros   = new Biblioteca<>("Livros");
Biblioteca<Revista> bRevistas = new Biblioteca<>("Revistas");
Biblioteca<String>  bStrings  = new Biblioteca<>("Strings");
```

**Sem Generics**, você precisaria de uma classe diferente para cada tipo,  
ou usar `Object` (perdendo a segurança de tipos).

---

## 📌 Resumo Visual

```
ENUM        → Conjunto fixo de constantes nomeadas
RECORD      → Classe imutável para dados (getters, equals, toString automáticos)
WRAPPER     → Objeto que "embrulha" um primitivo (int→Integer)
AUTOBOXING  → Conversão automática primitivo ↔ Wrapper
COLLECTIONS → Estruturas dinâmicas (ArrayList, HashMap, etc.)
ITERATOR    → Percorre e modifica coleção de forma segura
GENERICS    → Código reutilizável para qualquer tipo (com segurança)
EXCEÇÕES    → try/catch/finally para tratar erros sem travar o programa
LOCALDATE   → Datas modernas em Java (java.time)
ANNOTATIONS → Metadados (@Override, @Deprecated...)
```

package structures.FP06;

import structures.FP02.LinkedList;
import structures.FP03.ArrayStack;
import structures.FP05.ArrayUnorderedList;

import java.util.Arrays;

/**
 * Validador simples de XHTML usando Stack
 */
public class XHTMLValidator {
    private ArrayStack<String> tagStack;
    private ArrayUnorderedList<String> validTags;

    public XHTMLValidator() {
        this.tagStack = new ArrayStack<>();
        this.validTags = new ArrayUnorderedList<>();

        initializeValidTags(); // adiciona tags validas usando ArrayUnorderedList
    }

    /**
     * Inicializa a lista de tags XHTML válidas
     */
    private void initializeValidTags() {
        String[] tags = {
                "html", "head", "title", "body", "h1", "h2", "h3", "h4", "h5", "h6",
                "p", "br", "hr", "a", "img", "div", "span", "ul", "ol", "li",
                "table", "tr", "td", "th", "form", "input", "button", "select", "option"
        };

        for (String tag : tags)
            validTags.addToRear(tag);
    }

    /**
     * Valida um documento XHTML
     *
     * @param xhtmlDocument o documento XHTML como string
     * @return true se o documento for válido, false caso contrário
     */
    public boolean validate(String xhtmlDocument) {
        tagStack = new ArrayStack<>(); // Reset da stack

        String[] tokens = xhtmlDocument.split("\\s+"); // Quebra o documento por espaços para obter tags

        for (String token : tokens) {
            if (isOpeningTag(token)) {
                String tagName = extractTagName(token);

                // Verifica se é uma tag válida
                if (!validTags.contains(tagName)) {
                    System.out.println("❌ Tag inválida: " + tagName);
                    return false;
                }

                // Empilha a tag de abertura
                tagStack.push(tagName);
                System.out.println("📥 Empilhada: " + tagName);

            } else if (isClosingTag(token)) {
                String tagName = extractTagName(token);

                if (tagStack.isEmpty()) {
                    System.out.println("❌ Tag de fecho sem abertura: " + tagName);
                    return false;
                }

                String expectedTag = tagStack.pop();

                if (!expectedTag.equals(tagName)) {
                    System.out.println("❌ Fecho incorreto. Esperado: </" + expectedTag + ">, encontrado: " + token);
                    return false;
                }

                System.out.println("📤 Desempilhada: " + tagName + " (correspondente a " + expectedTag + ")");
            }
        }

        // Verifica se todas as tags foram fechadas
        if (!tagStack.isEmpty()) {
            System.out.println("❌ Tags não fechadas: " + tagStack);
            return false;
        }

        System.out.println("✅ Documento XHTML válido!");
        return true;
    }

    /**
     * Verifica se é uma tag de abertura
     */
    private boolean isOpeningTag(String token) {
        return token.startsWith("<") && !token.startsWith("</") && token.endsWith(">");
    }

    /**
     * Verifica se é uma tag de fecho
     */
    private boolean isClosingTag(String token) {
        return token.startsWith("</") && token.endsWith(">");
    }

    /**
     * Extrai o nome da tag (remove <, </ e >)
     */
    private String extractTagName(String tag) {
        if (tag.startsWith("</"))
            return tag.substring(2, tag.length() - 1);
        else if (tag.startsWith("<"))
            return tag.substring(1, tag.length() - 1);
        return tag;
    }

    /**
     * Método auxiliar para demonstrar o validador
     */
    public void demonstrateValidation() {
        System.out.println("=== 🎯 VALIDADOR XHTML ===\n");

        // Exemplo VÁLIDO 1
        System.out.println("1. EXEMPLO VÁLIDO:");
        String validXHTML1 = "<body> <h1> Titulo </h1> <p> Corpo com <a> link </a> </p> </body>";
        System.out.println("Documento: " + validXHTML1);
        boolean result1 = validate(validXHTML1);
        System.out.println("Válido: " + result1 + "\n");

        // Exemplo VÁLIDO 2
        System.out.println("2. EXEMPLO VÁLIDO (complexo):");
        String validXHTML2 = "<html> <body> <h1> Cabecalho </h1> <p> Paragrafo <a> link </a> </p> <div> <span> texto </span> </div> </body> </html>";
        System.out.println("Documento: " + validXHTML2);
        boolean result2 = validate(validXHTML2);
        System.out.println("Válido: " + result2 + "\n");

        // Exemplo INVÁLIDO 1 - tags cruzadas
        System.out.println("3. EXEMPLO INVÁLIDO - tags cruzadas:");
        String invalidXHTML1 = "<body> <h1> Titulo </h1> <p> Corpo com <a> link </p> </a> </body>";
        System.out.println("Documento: " + invalidXHTML1);
        boolean result3 = validate(invalidXHTML1);
        System.out.println("Válido: " + result3 + "\n");

        // Exemplo INVÁLIDO 2 - tag não fechada
        System.out.println("4. EXEMPLO INVÁLIDO - tag não fechada:");
        String invalidXHTML2 = "<body> <h1> Titulo </h1> <p> Corpo com <a> link </a>";
        System.out.println("Documento: " + invalidXHTML2);
        boolean result4 = validate(invalidXHTML2);
        System.out.println("Válido: " + result4 + "\n");

        // Exemplo INVÁLIDO 3 - tag inválida
        System.out.println("5. EXEMPLO INVÁLIDO - tag inválida:");
        String invalidXHTML3 = "<body> <h1> Titulo </h1> <invalid> tag </invalid> </body>";
        System.out.println("Documento: " + invalidXHTML3);
        boolean result5 = validate(invalidXHTML3);
        System.out.println("Válido: " + result5 + "\n");

        // Exemplo INVÁLIDO 4 - fecho sem abertura
        System.out.println("6. EXEMPLO INVÁLIDO - fecho sem abertura:");
        String invalidXHTML4 = "</body> <h1> Titulo </h1> </html>";
        System.out.println("Documento: " + invalidXHTML4);
        boolean result6 = validate(invalidXHTML4);
        System.out.println("Válido: " + result6 + "\n");
    }
}

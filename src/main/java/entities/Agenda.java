package entities;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private List<Contato> contatos;
    private final String arquivo = "contatos.txt";

    public Agenda() {
        contatos = new ArrayList<>();
        carregarDoArquivo();
    }

    public void adicionarContato(Contato contato) {
        contatos.add(contato);
        salvarNoArquivo();
    }

    public void removerContatoPorNome(String nome) {
        contatos.removeIf(c -> c.getNome().equalsIgnoreCase(nome));
        salvarNoArquivo();
    }

    public Contato buscarContatoPorNome(String nome) {
        for (Contato c : contatos) {
            if (c.getNome().equalsIgnoreCase(nome)) {
                return c;
            }
        }
        return null;
    }

    public void listarContatos() {
        if (contatos.isEmpty()) {
            System.out.println("A agenda está vazia.");
        } else {
            for (Contato c : contatos) {
                System.out.println("Nome: " + c.getNome());
                System.out.println("Telefone: " + c.getTelefone());
                System.out.println("Email: " + c.getEmail());
                System.out.println("---------------------");
            }
        }
    }

    private void salvarNoArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            for (Contato c: contatos) {
                writer.write(c.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar contatos: " + e.getMessage());
        }
    }

    private void carregarDoArquivo() {
        File file = new File(arquivo);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while((linha = reader.readLine()) != null) {
                Contato c = Contato.fromString(linha);
                if (c != null) contatos.add(c);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar contatos: " + e.getMessage());
        }
    }

}

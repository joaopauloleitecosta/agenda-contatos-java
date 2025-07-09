package org.example;

import entities.Agenda;
import entities.Contato;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("==== AGENDA ====");
            System.out.println("1 - Adicionar contato");
            System.out.println("2 - Remover contato");
            System.out.println("3 - Buscar contato");
            System.out.println("4 - Listar contato");
            System.out.println("5 - Exportar contatos para arquivo JSON");
            System.out.println("0 - Sair");

            System.out.println("Opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.println("Telefone: ");
                    String telefone = scanner.nextLine();
                    System.out.println("Email: ");
                    String email = scanner.nextLine();
                    agenda.adicionarContato(new Contato(nome, telefone, email));
                    break;
                case 2:
                    System.out.println("Nome do contato para remover: ");
                    String nomeRemover = scanner.nextLine();
                    Contato encontradoRemover = agenda.buscarContatoPorNome(nomeRemover);
                    if (encontradoRemover != null) {
                        agenda.removerContatoPorNome(nomeRemover);
                    } else {
                        System.out.println("Contato não encontrado para remover.");
                    }
                    break;
                case 3:
                    System.out.println("Nome do contato para buscar: ");
                    String nomeBuscar = scanner.nextLine();
                    Contato encontrado = agenda.buscarContatoPorNome(nomeBuscar);
                    if (encontrado != null) {
                        System.out.println("Contato encontrado:");
                        System.out.println("Nome: " + encontrado.getNome());
                        System.out.println("Telefone: " + encontrado.getTelefone());
                        System.out.println("Email: " + encontrado.getEmail());
                    } else {
                        System.out.println("Contato buscado não encontrado.");
                    }
                    break;
                case 4:
                    agenda.listarContatos();
                    break;
                case 5:
                    agenda.exportarArquivoJson();
                    System.out.println("Arquivo exportado.");
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        scanner.close();

    }
}
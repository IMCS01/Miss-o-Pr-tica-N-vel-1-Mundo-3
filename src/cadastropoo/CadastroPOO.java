
package cadastropoo;
import model.*;
import java.io.IOException;
import java.util.Scanner;


public class CadastroPOO {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
        PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();

        while (true) {
            System.out.println("\n=========================");
            System.out.println("1. Incluir Pessoa");
            System.out.println("2. Alterar Pessoa");
            System.out.println("3. Excluir Pessoa");
            System.out.println("4. Buscar pelo ID");
            System.out.println("5. Exibir todos");
            System.out.println("6. Persistir dados");
            System.out.println("7. Recuperar dados");
            System.out.println("0. Finalizar Programa");
            System.out.print("=========================\n");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 0) {
                System.out.println("Encerrando o programa...");
                break;
            }

            switch (opcao) {
                case 1 -> incluir(scanner, repoFisica, repoJuridica);
                case 2 -> alterar(scanner, repoFisica, repoJuridica);
                case 3 -> excluir(scanner, repoFisica, repoJuridica);
                case 4 -> exibirPorId(scanner, repoFisica, repoJuridica);
                case 5 -> exibirTodos(scanner, repoFisica, repoJuridica);
                case 6 -> salvarDados(scanner, repoFisica, repoJuridica);
                case 7 -> recuperarDados(scanner, repoFisica, repoJuridica);
                default -> System.out.println("Opção invalida. Tente novamente.");
            }
        }

        scanner.close();
    }

    private static void incluir(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.print("Incluir (1 - Fisica, 2 - Juridica): ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        if (tipo == 1) {
            System.out.print("Digite o ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Insira os dados...\n");
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();
            System.out.print("Idade: ");
            int idade = scanner.nextInt();
            repoFisica.inserir(new PessoaFisica(id, nome, cpf, idade));
        } else if (tipo == 2) {
            System.out.print("Digite o ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Insira os dados...\n");
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("CNPJ: ");
            String cnpj = scanner.nextLine();
            repoJuridica.inserir(new PessoaJuridica(id, nome, cnpj));
        } else {
            System.out.println("Tipo invalido.");
        }
    }

    private static void alterar(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.print("Alterar (1 - Fisica, 2 - Juridica): ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        if (tipo == 1) {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            PessoaFisica pf = repoFisica.obter(id);
            if (pf != null) {
                System.out.println("Dados atuais:");
                pf.exibir();
                System.out.print("Novo nome: ");
                pf.setNome(scanner.nextLine());
                System.out.print("Novo CPF: ");
                pf.setCpf(scanner.nextLine());
                System.out.print("Nova idade: ");
                pf.setIdade(scanner.nextInt());
                repoFisica.alterar(pf);
            } else {
                System.out.println("Pessoa Fisica nao encontrada.");
            }
        } else if (tipo == 2) {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            PessoaJuridica pj = repoJuridica.obter(id);
            if (pj != null) {
                System.out.println("Dados atuais:");
                pj.exibir();
                System.out.print("Novo nome: ");
                pj.setNome(scanner.nextLine());
                System.out.print("Novo CNPJ: ");
                pj.setCnpj(scanner.nextLine());
                repoJuridica.alterar(pj);
            } else {
                System.out.println("Pessoa Juridica nao encontrada.");
            }
        } else {
            System.out.println("Tipo invalido.");
        }
    }

    private static void excluir(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.print("Excluir (1 - Fisica, 2 - Juridica): ");
        int tipo = scanner.nextInt();
        System.out.print("ID: ");
        int id = scanner.nextInt();

        if (tipo == 1) {
            repoFisica.excluir(id);
        } else if (tipo == 2) {
            repoJuridica.excluir(id);
        } else {
            System.out.println("Tipo invalido.");
        }
    }

    private static void exibirPorId(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.print("Exibir pelo ID (1 - Fisica, 2 - Juridica): ");
        int tipo = scanner.nextInt();
        System.out.print("ID: ");
        int id = scanner.nextInt();

        if (tipo == 1) {
            PessoaFisica pf = repoFisica.obter(id);
            if (pf != null) pf.exibir();
            else System.out.println("Pessoa Fisica nao encontrada.");
        } else if (tipo == 2) {
            PessoaJuridica pj = repoJuridica.obter(id);
            if (pj != null) pj.exibir();
            else System.out.println("Pessoa Juridica nao encontrada.");
        } else {
            System.out.println("Tipo invalido.");
        }
    }

    private static void exibirTodos(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.print("Exibir todos (1 - Fisica, 2 - Juridica): ");
        int tipo = scanner.nextInt();

        if (tipo == 1) {
            for (PessoaFisica pf : repoFisica.obterTodos()) {
                pf.exibir();
            }
        } else if (tipo == 2) {
            for (PessoaJuridica pj : repoJuridica.obterTodos()) {
                pj.exibir();
            }
        } else {
            System.out.println("Tipo invalido.");
        }
    }

    private static void salvarDados(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.print("Prefixo do arquivo: ");
        String prefixo = scanner.next();
        try {
            repoFisica.persistir(prefixo + ".fisica.bin");
            repoJuridica.persistir(prefixo + ".juridica.bin");
            System.out.println("Dados salvos com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    private static void recuperarDados(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.print("Prefixo do arquivo: ");
        String prefixo = scanner.next();
        try {
            repoFisica.recuperar(prefixo + ".fisica.bin");
            repoJuridica.recuperar(prefixo + ".juridica.bin");
            System.out.println("Dados recuperados com sucesso.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao recuperar os dados: " + e.getMessage());
        }
    }
}

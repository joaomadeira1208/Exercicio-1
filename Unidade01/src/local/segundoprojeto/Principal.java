

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DAO dao = new DAO();

        dao.conectar();

        int opcao;
        do {
            System.out.println("Menu:");
            System.out.println("1. Listar Funcionários");
            System.out.println("2. Inserir Funcionário");
            System.out.println("3. Excluir Funcionário");
            System.out.println("4. Atualizar Funcionário");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (opcao) {
                case 1:
                    listarFuncionarios(dao);
                    break;
                case 2:
                    inserirFuncionario(scanner, dao);
                    break;
                case 3:
                    excluirFuncionario(scanner, dao);
                    break;
                case 4:
                    atualizarFuncionario(scanner, dao);
                    break;
                case 5:
                    System.out.println("Encerrando o programa.");
                    break;
                default:
                    System.out.println("Opção inválida. Escolha uma opção válida.");
            }
        } while (opcao != 5);

        dao.close();
        scanner.close();
    }

    public static void listarFuncionarios(DAO dao) {
        Funcionario[] funcionarios = dao.getFuncionario();
        if (funcionarios != null) {
            for (Funcionario funcionario : funcionarios) {
                System.out.println(funcionario);
            }
        } else {
            System.out.println("Nenhum funcionário encontrado.");
        }
    }

    public static void inserirFuncionario(Scanner scanner, DAO dao) {
        System.out.print("Digite o código do funcionário: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Digite o nome do funcionário: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o sobrenome do funcionário: ");
        String sobrenome = scanner.nextLine();

        System.out.print("Digite o salário do funcionário: ");
        double salario = scanner.nextDouble();

        Funcionario novoFuncionario = new Funcionario(codigo, nome, sobrenome, salario);
        if (dao.inserirFuncionario(novoFuncionario)) {
            System.out.println("Funcionário inserido com sucesso.");
        } else {
            System.out.println("Erro ao inserir o funcionário.");
        }
    }

    public static void excluirFuncionario(Scanner scanner, DAO dao) {
        System.out.print("Digite o código do funcionário a ser excluído: ");
        int codigo = scanner.nextInt();
        if (dao.excluirFuncionario(codigo)) {
            System.out.println("Funcionário excluído com sucesso.");
        } else {
            System.out.println("Erro ao excluir o funcionário.");
        }
    }

    public static void atualizarFuncionario(Scanner scanner, DAO dao) {
        System.out.print("Digite o código do funcionário a ser atualizado: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Digite o novo nome do funcionário: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o novo sobrenome do funcionário: ");
        String sobrenome = scanner.nextLine();

        System.out.print("Digite o novo salário do funcionário: ");
        double salario = scanner.nextDouble();

        Funcionario funcionarioAtualizado = new Funcionario(codigo, nome, sobrenome, salario);
        if (dao.atualizarFuncionario(funcionarioAtualizado)) {
            System.out.println("Funcionário atualizado com sucesso.");
        } else {
            System.out.println("Erro ao atualizar o funcionário.");
        }
    }
}

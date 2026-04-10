import model.Tarefa;
import service.TarefaService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        TarefaService service = new TarefaService();
        service.criarTabela();

        int opcao = 0;

        while (opcao != 6) {

            System.out.println("\n----- MENU -----");
            System.out.println("1 - Adicionar tarefa");
            System.out.println("2 - Listar tarefas");
            System.out.println("3 - Concluir tarefas");
            System.out.println("4 - Editar tarefas");
            System.out.println("5 - Remover tarefa");
            System.out.println("6 - Sair");
            System.out.print("Escolha: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
            } else {
                System.out.println("Digite apenas números!");
                scanner.next();
                continue;
            }

            switch (opcao) {

                case 1:
                    scanner.nextLine();

                    Tarefa tarefa = new Tarefa();

                    System.out.println("Digite a descrição da tarefa: ");
                    tarefa.setDescricao(scanner.nextLine());

                    tarefa.setConcluida(false);

                    service.adicionarTarefa(tarefa);

                    break;

                case 2:

                    service.listarTarefas();

                    break;

                case 3:

                    System.out.println("Digite o ID da tarefa: ");
                    int id = scanner.nextInt();

                    service.concluirTarefa(id);

                    break;

                case 4:

                    System.out.println("Digite o ID da tarefa que deseja editar: ");
                    int idE = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Digite a nova descrição: ");
                    String edt = scanner.nextLine();

                    service.editarTarefa(idE, edt);

                    break;

                case 5:

                    System.out.println("Digite o ID da tarefa para remover: ");
                    int idR = scanner.nextInt();

                    service.removerTarefa(idR);

                    break;

                case 6:

                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
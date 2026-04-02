import model.Tarefa;
import service.TarefaService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        TarefaService service = new TarefaService();
        service.carregarDoArquivo();

        int opcao = 0;

        while (opcao != 5) {

            System.out.println("\n----- MENU -----");
            System.out.println("1 - Adicionar tarefa");
            System.out.println("2 - Listar tarefas");
            System.out.println("3 - Concluir tarefas");
            System.out.println("4 - Remover tarefa");
            System.out.println("5 - Sair");
            System.out.println("Escolha: ");

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

                    System.out.println("Digite o número da tarefa: ");
                    int numero = scanner.nextInt();

                    service.concluirTarefa(numero - 1);

                    break;

                case 4:

                    System.out.println("Digite o número da tarefa para remover: ");
                    int remover = scanner.nextInt();

                    service.removerTarefa(remover - 1);

                    break;

                case 5:

                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
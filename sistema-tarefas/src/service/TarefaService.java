package service;

import model.Tarefa;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class TarefaService {

    private ArrayList<Tarefa> tarefas = new ArrayList<>();

    public void adicionarTarefa(Tarefa tarefa) {

        tarefas.add(tarefa);
        salvarEmArquivo();
        System.out.println("Tarefa adicionada com sucesso!");
    }

    public void listarTarefas() {

        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada!");
        } else {

            System.out.println("----- LISTA DE TAREFAS -----");

            int i = 1;

            for (Tarefa t : tarefas) {

                String status = t.isConcluida() ? "[✔]" : "[ ]";

                System.out.println(i + " | " + status + " | " + t.getDescricao());
                i++;
             }
        }
    }

    public void salvarEmArquivo() {

        try {

            FileWriter writer = new FileWriter("tarefas.txt");

            for (Tarefa t : tarefas) {

                writer.write(t.getDescricao() + " ; " + t.isConcluida() + "\n");
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo!");
        }
    }

    public void carregarDoArquivo() {

        try {

            BufferedReader reader = new BufferedReader(new FileReader("tarefas.txt"));

            String linha;

            while ((linha= reader.readLine()) != null) {

                String[] partes = linha.split(";");

                Tarefa tarefa = new Tarefa();
                tarefa.setDescricao(partes[0]);
                tarefa.setConcluida(Boolean.parseBoolean(partes[1]));

                tarefas.add(tarefa);
            }
            reader.close();

        } catch (IOException e) {
            System.out.println("Arquivo não encontrado, iniciando vazio.");
        }
    }

    public void concluirTarefa(int indice) {

        if (indice >= 0 && indice < tarefas.size()) {

            tarefas.get(indice).setConcluida(true);
            salvarEmArquivo();

            System.out.println("Tarefa concluída com sucesso!");

        } else {
            System.out.println("Índice inválido!");
        }
    }

    public void removerTarefa(int indice) {

        if (indice >= 0 && indice < tarefas.size()) {

            tarefas.remove(indice);
            salvarEmArquivo();

            System.out.println("Tarefa removida com sucesso!");

        } else {
            System.out.println("Índice inválido!");
        }
    }
}

package service;

import model.Tarefa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class TarefaService {

    public void criarTabela() {

        String sql = "CREATE TABLE IF NOT EXISTS tarefas (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "descricao TEXT NOT NULL," +
                "concluida BOOLEAN NOT NULL" +
                ");";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("Tabela criada/verificada!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void adicionarTarefa(Tarefa tarefa) {

        String sql = "INSERT INTO tarefas (descricao, concluida) VALUES (?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tarefa.getDescricao());
            stmt.setBoolean(2, tarefa.isConcluida());

            stmt.executeUpdate();

            System.out.println("✔ Tarefa adicionada com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listarTarefas() {

        String sql = "SELECT * FROM tarefas ORDER BY id";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("----- LISTA DE TAREFAS -----");

            int total = 0;
            int concluidas = 0;
            int pendentes = 0;

            while (rs.next()) {

                int id = rs.getInt("id");
                String descricao = rs.getString("descricao");

                int concluidaInt = rs.getInt("concluida");
                boolean concluida = (concluidaInt == 1);

                String status = concluida ? "[✔]" : "[ ]";

                System.out.println(id + " | " + status + " | " + descricao);
                total++;

                if (concluida) {
                    concluidas++;
                } else {
                    pendentes++;
                }
            }

            System.out.println("---------------------");
            System.out.println("Total: " + total);
            System.out.println("Concluídas: " + concluidas);
            System.out.println("Pendentes: " + pendentes);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void concluirTarefa(int id) {

        String sql = "UPDATE tarefas SET concluida = 1 WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("✔ Tarefa concluída com sucesso!");
            } else {
                System.out.println("❌ Tarefa não encontrada!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editarTarefa(int id, String edt) {
        String sql = "UPDATE tarefas SET descricao = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1,edt);
            stmt.setInt(2,id);

            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("✔ Tarefa editada com sucesso!");
            } else {
                System.out.println("❌ Tarefa não encontrada!");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removerTarefa(int id) {

        String sql = "DELETE FROM tarefas WHERE id = ? ";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
        }




    }

}

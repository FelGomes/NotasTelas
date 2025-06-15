package Funcionalidades;

import conexao.Conexao;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PProfessor {

    public List<Object[]> listarProfessores() {
        List<Object[]> lista = new ArrayList<>();
        String sql = "SELECT u.usuarios_id, u.usuarios_nome, p.professores_disciplina, p.professores_turma, p.professores_titularidade "
                + "FROM professores p INNER JOIN usuarios u ON p.fk_professores_usuarios_id = u.usuarios_id";

        try (Connection conexao = new Conexao().getConexao(); PreparedStatement stmt = conexao.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("usuarios_id");
                String nome = rs.getString("usuarios_nome");
                String disciplina = rs.getString("professores_disciplina");
                String turma = rs.getString("professores_turma");
                String titularidade = rs.getString("professores_titularidade");

                lista.add(new Object[]{id, nome, disciplina, turma, titularidade});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean usuarioEhAluno(int usuarioId) {
        String sql = "SELECT 1 FROM alunos WHERE fk_alunos_usuarios_id = ?";
        try (Connection conexao = new Conexao().getConexao(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean usuarioEhProfessor(int usuarioId) {
        String sql = "SELECT 1 FROM professores WHERE fk_professores_usuarios_id = ?";
        try (Connection conexao = new Conexao().getConexao(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void inserirProfessor(int usuarioId, String disciplina, String turma, String titularidade) {
        String sql = "INSERT INTO professores (professores_disciplina, professores_turma, professores_titularidade, fk_professores_usuarios_id) VALUES (?, ?, ?, ?)";

        try (Connection conexao = new Conexao().getConexao(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, disciplina);
            stmt.setString(2, turma);
            stmt.setString(3, titularidade);
            stmt.setInt(4, usuarioId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarProfessor(int usuarioId, String disciplina, String turma, String titularidade) {
        String sql = "UPDATE professores SET professores_disciplina = ?, professores_turma = ?, professores_titularidade = ? WHERE fk_professores_usuarios_id = ?";

        try (Connection conexao = new Conexao().getConexao(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, disciplina);
            stmt.setString(2, turma);
            stmt.setString(3, titularidade);
            stmt.setInt(4, usuarioId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirProfessor(int usuarioId) {
        String sql = "DELETE FROM professores WHERE fk_professores_usuarios_id = ?";

        try (Connection conexao = new Conexao().getConexao(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Object[]> listarTodosUsuarios() {
        List<Object[]> lista = new ArrayList<>();
        String sql = "SELECT usuarios_id, usuarios_nome, usuarios_cpf, usuarios_nascimento FROM usuarios";

        try (Connection conexao = new Conexao().getConexao(); PreparedStatement stmt = conexao.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("usuarios_id");
                String nome = rs.getString("usuarios_nome");
                String cpf = rs.getString("usuarios_cpf");
                String nascimento = rs.getString("usuarios_nascimento");
                lista.add(new Object[]{id, nome, cpf, nascimento});
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void gerarArquivoProfessores() {
        List<Object[]> professores = listarProfessores();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("professores.txt"))) {
            writer.write("==== LISTA DE PROFESSORES ====\n\n");
            for (Object[] prof : professores) {
                writer.write("ID: " + prof[0] + "\n");
                writer.write("Nome: " + prof[1] + "\n");
                writer.write("Disciplina: " + prof[2] + "\n");
                writer.write("Turma: " + prof[3] + "\n");
                writer.write("Titularidade: " + prof[4] + "\n");
                writer.write("------------------------------\n");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public List<Object[]> buscarProfessoresPorNome(String nome) {
        List<Object[]> lista = new ArrayList<>();

        String sql = "SELECT u.usuarios_id, u.usuarios_nome, p.professores_disciplina, p.professores_turma, p.professores_titularidade "
                + "FROM professores p "
                + "INNER JOIN usuarios u ON p.fk_professores_usuarios_id = u.usuarios_id "
                + "WHERE u.usuarios_nome LIKE ?";

        try (Connection conexao = new Conexao().getConexao(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("usuarios_id");
                    String nomeCompleto = rs.getString("usuarios_nome");
                    String disciplina = rs.getString("professores_disciplina");
                    String turma = rs.getString("professores_turma");
                    String titularidade = rs.getString("professores_titularidade");

                    lista.add(new Object[]{id, nomeCompleto, disciplina, turma, titularidade});
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    
}

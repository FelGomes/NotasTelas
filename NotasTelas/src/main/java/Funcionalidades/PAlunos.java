package Funcionalidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import conexao.Conexao;

/**
 *
 * @author Carlos
 */
public class PAlunos {

    // Listar todos os alunos
    public ArrayList<String[]> listarAlunos() throws SQLException {
        ArrayList<String[]> dadosAlunos = new ArrayList<>();

        String sql = "SELECT alunos_id, alunos_matriculados, alunos_sala, alunos_turma, qtd_disciplinas FROM alunos";

        try (Connection conexao = new Conexao().getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                dadosAlunos.add(new String[]{
                        rs.getString("alunos_id"),
                        rs.getBoolean("alunos_matriculados") ? "Sim" : "Não",
                        rs.getString("alunos_sala"),
                        rs.getString("alunos_turma"),
                        rs.getString("qtd_disciplinas")
                });
            }
        }

        return dadosAlunos;
    }

    // Buscar alunos por sala
    public ArrayList<String[]> buscarAlunosPorSala(String nomeBusca) throws SQLException {
        ArrayList<String[]> dadosAlunos = new ArrayList<>();

        String sql = "SELECT alunos_id, alunos_matriculados, alunos_sala, alunos_turma, qtd_disciplinas FROM alunos WHERE UPPER(alunos_sala) LIKE ?";

        try (Connection conexao = new Conexao().getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, "%" + nomeBusca.toUpperCase().trim() + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    dadosAlunos.add(new String[]{
                            rs.getString("alunos_id"),
                            rs.getBoolean("alunos_matriculados") ? "Sim" : "Não",
                            rs.getString("alunos_sala"),
                            rs.getString("alunos_turma"),
                            rs.getString("qtd_disciplinas")
                    });
                }
            }
        }

        return dadosAlunos;
    }

    // Inserir aluno
    public String incluirAlunos(boolean matriculado, String sala, String turma, int qtd_disciplinas) {
        String sql = "INSERT INTO alunos (alunos_matriculados, alunos_sala, alunos_turma, qtd_disciplinas) VALUES (?, ?, ?, ?)";

        try (Connection conexao = new Conexao().getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setBoolean(1, matriculado);
            stmt.setString(2, sala.toUpperCase());
            stmt.setString(3, turma.toUpperCase());
            stmt.setInt(4, qtd_disciplinas);

            stmt.executeUpdate();
            return "Inclusão efetuada com sucesso!";

        } catch (SQLException e) {
            return "Erro na inclusão: " + e.getMessage();
        }
    }

    // Atualizar aluno
    public String alterarAluno(int id, boolean matriculado, String sala, String turma, int qtd_disciplinas) {
        String sql = "UPDATE alunos SET alunos_matriculados = ?, alunos_sala = ?, alunos_turma = ?, qtd_disciplinas = ? WHERE alunos_id = ?";

        try (Connection conexao = new Conexao().getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setBoolean(1, matriculado);
            stmt.setString(2, sala.toUpperCase());
            stmt.setString(3, turma.toUpperCase());
            stmt.setInt(4, qtd_disciplinas);
            stmt.setInt(5, id);

            stmt.executeUpdate();
            return "Alteração efetuada com sucesso!";

        } catch (SQLException e) {
            return "Erro na alteração: " + e.getMessage();
        }
    }

    // Excluir aluno
    public String excluirAluno(int id) {
        String sql = "DELETE FROM alunos WHERE alunos_id = ?";

        try (Connection conexao = new Conexao().getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            return "Exclusão efetuada com sucesso!";

        } catch (SQLException e) {
            return "Erro na exclusão: " + e.getMessage();
        }
    }
}

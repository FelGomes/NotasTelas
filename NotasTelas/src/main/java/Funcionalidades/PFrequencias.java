package Funcionalidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import conexao.Conexao;

public class PFrequencias {

    // Método para consultar todas as frequências
    public ArrayList<EFrequencias> consultarFrequencia() {
        ArrayList<EFrequencias> lista = new ArrayList<>();
                
            String sql = "SELECT f.frequencias_id, "
                        + "f.aulas_ministradas, "
                        + "f.frequencias_faltas, "
                        + "f.prctg_presenca, "
                        + "f.frequencias_disciplinas, "
                        + "uprof.usuarios_nome as professor_nome, "
                        + "ualuno.usuarios_nome as aluno_nome, "
                        + "f.total_aulas "
                        + "FROM frequencias f "
                        + "INNER JOIN professores p ON f.fk_frequencias_professores_id = p.professores_id "
                        + "INNER JOIN usuarios uprof ON p.fk_professores_usuarios_id = uprof.usuarios_id "
                        + "INNER JOIN alunos a ON f.fk_frequencias_alunos_id = a.alunos_id "
                        + "INNER JOIN usuarios ualuno ON a.fk_alunos_usuarios_id = ualuno.usuarios_id " 
                        + "WHERE f.frequencias_id = ?";

            try (Connection conexao = new Conexao().getConexao(); PreparedStatement stmt = conexao.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                EFrequencias freq = new EFrequencias();
                System.out.println("ID: " + rs.getInt("frequencias_id"));
                System.out.println("Aulas ministradas: " + rs.getInt("aulas_ministradas"));
                System.out.println("Faltas: " + rs.getInt("frequencias_faltas"));
                System.out.println("% Presença: " + rs.getInt("prctg_presenca"));
                System.out.println("Disciplina: " + rs.getString("frequencias_disciplinas"));
                System.out.println("Nome do Professor(a): " + rs.getString("professor_nome"));
                System.out.println("Nome do Aluno(a): " + rs.getString("aluno_nome"));
                System.out.println("Total de aulas: " + rs.getString("total_aulas"));
                System.out.println("--------------------------------------------------");

                lista.add(freq);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erro consultarFrequencia: " + e.getMessage());
        }
        return lista;
    }

    // Método para incluir frequência
    public String incluirFrequencia(EFrequencias freq) {
        String resultado;
        
        String sql = "INSERT INTO frequencias (total_aulas, aulas_ministradas, frequencias_faltas, prctg_presenca, frequencias_disciplinas,fk_frequencias_professores_id, fk_frequencias_alunos_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conexao = new Conexao().getConexao(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, freq.getTotal_aulas());
            stmt.setInt(2, freq.getAulas_ministradas());
            stmt.setInt(3, freq.getFrequencias_faltas());
            stmt.setFloat(4, freq.getPrctg_presenca());
            stmt.setString(5, freq.getFrequencias_disciplinas().toUpperCase());
            stmt.setInt(6, freq.getProfessores());
            stmt.setInt(7, freq.getAluno().getUsuario_id();

            int linhas = pstm.executeUpdate();
            if (linhas > 0) {
                resultado = "Inclusão efetuada com sucesso!";
            } else {
                resultado = "Falha na inclusão.";
            }
        } catch (SQLException e) {
            resultado = "Erro na inclusão: " + e.getMessage();
        } finally {
            Conexao.fecharConexao();
        }
        return resultado;
    }

    // Método para alterar frequência
    public String alterarFrequencia(EFrequencias freq) {
        String resultado;
        Connection conn = Conexao.obterConexaoMySQL();

        String SQL = "UPDATE frequencias SET total_aulas = ?, aulas_ministradas = ?, frequencias_faltas = ?, prctg_presenca = ?, frequencias_disciplinas = ?, professores_id = ?, alunos_id = ? WHERE frequencias_id = ?";

        try (PreparedStatement pstm = conn.prepareStatement(SQL)) {
            pstm.setInt(1, freq.getTotal_aulas());
            pstm.setInt(2, freq.getAulas_ministradas());
            pstm.setInt(3, freq.getFrequencias_faltas());
            pstm.setFloat(4, freq.getPrctg_presenca());
            pstm.setString(5, freq.getFrequencias_disciplinas().toUpperCase());
            pstm.setInt(6, freq.getProfessores().getId());
            pstm.setInt(7, freq.getAluno().getId());
            pstm.setInt(8, freq.getFrequencias_id());

            int linhas = pstm.executeUpdate();
            if (linhas > 0) {
                resultado = "Alteração efetuada com sucesso!";
            } else {
                resultado = "Nenhum registro encontrado para alterar.";
            }
        } catch (SQLException e) {
            resultado = "Erro na alteração: " + e.getMessage();
        } finally {
            Conexao.fecharConexao();
        }
        return resultado;
    }

    // Método para excluir frequência pelo id
    public String excluirFrequencia(int frequencias_id) {
        String resultado;
        Connection conn = Conexao.obterConexaoMySQL();

        String SQL = "DELETE FROM frequencias WHERE frequencias_id = ?";

        try (PreparedStatement pstm = conn.prepareStatement(SQL)) {
            pstm.setInt(1, frequencias_id);

            int linhas = pstm.executeUpdate();
            if (linhas > 0) {
                resultado = "Exclusão efetuada com sucesso!";
            } else {
                resultado = "Nenhum registro encontrado para exclusão.";
            }
        } catch (SQLException e) {
            resultado = "Erro na exclusão: " + e.getMessage();
        } finally {
            Conexao.fecharConexao();
        }
        return resultado;
    }
}

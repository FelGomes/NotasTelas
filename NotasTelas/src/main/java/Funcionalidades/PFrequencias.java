package Funcionalidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexao.Conexao;
import Funcionalidades.EFrequencias;
import Funcionalidades.EProfessor;
import Funcionalidades.EAlunos;

public class PFrequencias {

    // Listar todas as frequências
    public ArrayList<EFrequencias> listarFrequencias() {
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

        try (Connection conexao = new Conexao().getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                EFrequencias freq = new EFrequencias();
                freq.setFrequencias_id(rs.getInt("frequencias_id"));
                freq.setTotal_aulas(rs.getInt("aulas_ministradas"));
                freq.setAulas_ministradas(rs.getInt("frequencias_faltas"));
                freq.setFrequencias_faltas(rs.getInt("prctg_presenca"));    
                freq.setPrctg_presenca(rs.getFloat("frequencias_disciplinas"));
                freq.setFrequencias_disciplinas(rs.getString("total_aulas"));

                // Professor
                EProfessor prof = new EProfessor();
                prof.setIdProfessor(rs.getInt("professor_id"));
                freq.setProfessores(prof);

                // Aluno
                EAlunos aluno = new EAlunos();
                aluno.setUsuario_id(rs.getInt("aluno_id"));
                aluno.setNome(rs.getString("aluno_nome"));
                freq.setAluno(aluno);

                lista.add(freq);
            }

        } catch (SQLException e) {
            System.err.println("Erro na listagem de frequências: " + e.getMessage());
        }

        return lista;
    }

    // Incluir frequência
    public String incluirFrequencia(EFrequencias freq) {
        String sql = "INSERT INTO frequencias (total_aulas, aulas_ministradas, frequencias_faltas, prctg_presenca, " +
                     "frequencias_disciplinas, fk_frequencias_professores_id, fk_frequencias_alunos_id) " +
                     "VALUES (?, ?, ?, ?, UPPER(?), ?, ?)";

        try (Connection conexao = new Conexao().getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, freq.getTotal_aulas());
            stmt.setInt(2, freq.getAulas_ministradas());
            stmt.setInt(3, freq.getFrequencias_faltas());
            stmt.setFloat(4, freq.getPrctg_presenca());
            stmt.setString(5, freq.getFrequencias_disciplinas());
            stmt.setInt(6, freq.getProfessores().getIdProfessor());
            stmt.setInt(7, freq.getAluno().getUsuario_id()); // Corrigido

            stmt.executeUpdate();
            return "Inclusão efetuada com sucesso!";

        } catch (SQLException e) {
            return "Erro na inclusão: " + e.getMessage();
        }
    }

    // Alterar frequência
    public String alterarFrequencia(EFrequencias freq) {
        String sql = "UPDATE frequencias SET total_aulas = ?, aulas_ministradas = ?, frequencias_faltas = ?, " +
                     "prctg_presenca = ?, frequencias_disciplinas = UPPER(?), fk_frequencias_professores_id = ?, " +
                     "fk_frequencias_alunos_id = ? WHERE frequencias_id = ?";

        try (Connection conexao = new Conexao().getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, freq.getTotal_aulas());
            stmt.setInt(2, freq.getAulas_ministradas());
            stmt.setInt(3, freq.getFrequencias_faltas());
            stmt.setFloat(4, freq.getPrctg_presenca());
            stmt.setString(5, freq.getFrequencias_disciplinas());
            stmt.setInt(6, freq.getProfessores().getIdProfessor());
            stmt.setInt(7, freq.getAluno().getUsuario_id()); // Corrigido
            stmt.setInt(8, freq.getFrequencias_id());

            stmt.executeUpdate();
            return "Alteração efetuada com sucesso!";

        } catch (SQLException e) {
            return "Erro na alteração: " + e.getMessage();
        }
    }

    // Excluir frequência
    public String excluirFrequencia(int id) {
        String sql = "DELETE FROM frequencias WHERE frequencias_id = ?";

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
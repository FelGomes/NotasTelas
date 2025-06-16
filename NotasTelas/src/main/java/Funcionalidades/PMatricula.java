package Funcionalidades;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PMatricula {

    public String salvarMatricula(EMatricula m) {
        String sql = "INSERT INTO matriculas (matriculas_data_inicio, matriculas_data_fim, qtd_tempo, fk_matricula_instituicao_id, fk_matricula_alunos_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = new Conexao().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, m.getData_inicio());
            stmt.setString(2, m.getData_fim());
            stmt.setInt(3, m.getQtd_tempo());
            stmt.setInt(4, m.getFk_instituicao_id());
            stmt.setInt(5, m.getFk_aluno_id());

            stmt.executeUpdate();
            return "Matrícula salva com sucesso!";

        } catch (SQLException e) {
            return "Erro ao salvar matrícula: " + e.getMessage();
        }
    }

    public String alterarMatricula(EMatricula m) {
        String sql = "UPDATE matriculas SET matriculas_data_inicio=?, matriculas_data_fim=?, qtd_tempo=?, fk_matricula_instituicao_id=?, fk_matricula_alunos_id=? WHERE matriculas_id=?";
        try (Connection con = new Conexao().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, m.getData_inicio());
            stmt.setString(2, m.getData_fim());
            stmt.setInt(3, m.getQtd_tempo());
            stmt.setInt(4, m.getFk_instituicao_id());
            stmt.setInt(5, m.getFk_aluno_id());
            stmt.setInt(6, m.getMatriculas_id());

            stmt.executeUpdate();
            return "Matrícula alterada com sucesso!";

        } catch (SQLException e) {
            return "Erro ao alterar matrícula: " + e.getMessage();
        }
    }

    public String excluirMatricula(int id) {
        String sql = "DELETE FROM matriculas WHERE matriculas_id=?";
        try (Connection con = new Conexao().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            return "Matrícula excluída com sucesso!";

        } catch (SQLException e) {
            return "Erro ao excluir matrícula: " + e.getMessage();
        }
    }

    public ArrayList<EMatricula> consultarMatricula(String filtro) {
        ArrayList<EMatricula> lista = new ArrayList<>();
        String sql = "SELECT * FROM matriculas WHERE CAST(fk_matricula_alunos_id AS CHAR) LIKE ?";
        try (Connection con = new Conexao().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, "%" + filtro + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    EMatricula m = new EMatricula();
                    m.setMatriculas_id(rs.getInt("matriculas_id"));
                    m.setData_inicio(rs.getString("matriculas_data_inicio"));
                    m.setData_fim(rs.getString("matriculas_data_fim"));
                    m.setQtd_tempo(rs.getInt("qtd_tempo"));
                    m.setFk_instituicao_id(rs.getInt("fk_matricula_instituicao_id"));
                    m.setFk_aluno_id(rs.getInt("fk_matricula_alunos_id"));
                    lista.add(m);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar matrícula: " + e.getMessage());
        }
        return lista;
    }

    public ArrayList<String[]> listarMatriculas() {
        ArrayList<String[]> lista = new ArrayList<>();
        String sql = "SELECT * FROM matriculas";
        try (Connection con = new Conexao().getConexao();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String[] linha = new String[6];
                linha[0] = String.valueOf(rs.getInt("matriculas_id"));
                linha[1] = rs.getString("matriculas_data_inicio");
                linha[2] = rs.getString("matriculas_data_fim");
                linha[3] = rs.getString("qtd_tempo");
                linha[4] = String.valueOf(rs.getInt("fk_matricula_instituicao_id"));
                linha[5] = String.valueOf(rs.getInt("fk_matricula_alunos_id"));
                lista.add(linha);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar matrículas: " + e.getMessage());
        }
        return lista;
    }
}

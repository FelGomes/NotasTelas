package Funcionalidades;

import conexao.Conexao;
import java.sql.*;
import java.util.ArrayList;

public class PDiario {

    public String salvar(EDiario d) {
        String sql = "INSERT INTO diarios (diarios_local, diarios_disciplinas, qtd_alunos, fk_diarios_professores_id, fk_diarios_alunos_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = new Conexao().getConexao(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, d.getDiarios_local());
            stmt.setString(2, d.getDiarios_disciplinas());
            stmt.setInt(3, d.getQtd_alunos());
            stmt.setInt(4, d.getFk_diarios_professores_id());
            stmt.setInt(5, d.getFk_diarios_alunos_id());

            int res = stmt.executeUpdate();

            if (res > 0) {
                return "Diário salvo com sucesso!";
            } else {
                return "Erro ao salvar diário.";
            }

        } catch (SQLException e) {
            return "Erro ao salvar: " + e.getMessage();
        }
    }

    public String alterar(EDiario d) {
        String sql = "UPDATE diarios SET diarios_local=?, diarios_disciplinas=?, qtd_alunos=?, fk_diarios_professores_id=?, fk_diarios_alunos_id=? WHERE diarios_id=?";
        try (Connection con = new Conexao().getConexao(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, d.getDiarios_local());
            stmt.setString(2, d.getDiarios_disciplinas());
            stmt.setInt(3, d.getQtd_alunos());
            stmt.setInt(4, d.getFk_diarios_professores_id());
            stmt.setInt(5, d.getFk_diarios_alunos_id());
            stmt.setInt(6, d.getDiarios_id());

            int res = stmt.executeUpdate();

            if (res > 0) {
                return "Diário alterado com sucesso!";
            } else {
                return "Erro ao alterar diário.";
            }

        } catch (SQLException e) {
            return "Erro ao alterar: " + e.getMessage();
        }
    }

    public String deletar(int id) {
        String sql = "DELETE FROM diarios WHERE diarios_id=?";
        try (Connection con = new Conexao().getConexao(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int res = stmt.executeUpdate();

            if (res > 0) {
                return "Diário excluído com sucesso!";
            } else {
                return "Erro ao excluir diário.";
            }

        } catch (SQLException e) {
            return "Erro ao excluir: " + e.getMessage();
        }
    }

    public ArrayList<EDiario> listar(String filtro) {
        ArrayList<EDiario> lista = new ArrayList<>();
        String sql = "SELECT * FROM diarios WHERE diarios_local LIKE ? ORDER BY diarios_id";

        try (Connection con = new Conexao().getConexao(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, "%" + filtro + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                EDiario d = new EDiario();

                d.setDiarios_id(rs.getInt("diarios_id"));
                d.setDiarios_local(rs.getString("diarios_local"));
                d.setDiarios_disciplinas(rs.getString("diarios_disciplinas"));
                d.setQtd_alunos(rs.getInt("qtd_alunos"));
                d.setFk_diarios_professores_id(rs.getInt("fk_diarios_professores_id"));
                d.setFk_diarios_alunos_id(rs.getInt("fk_diarios_alunos_id"));

                lista.add(d);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Método para gerar lista de String[] para exportação de arquivo (usado na interface)
    public ArrayList<String[]> listarDiarios() {
        ArrayList<String[]> lista = new ArrayList<>();
        String sql = "SELECT * FROM diarios ORDER BY diarios_id";

        try (Connection con = new Conexao().getConexao(); PreparedStatement stmt = con.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String[] linha = new String[6];
                linha[0] = String.valueOf(rs.getInt("diarios_id"));
                linha[1] = rs.getString("diarios_local");
                linha[2] = rs.getString("diarios_disciplinas");
                linha[3] = String.valueOf(rs.getInt("qtd_alunos"));
                linha[4] = String.valueOf(rs.getInt("fk_diarios_professores_id"));
                linha[5] = String.valueOf(rs.getInt("fk_diarios_alunos_id"));
                lista.add(linha);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}


package Funcionalidades;

/**
 *
 * @author Kauã Luiz
 */
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PDiario {
    public String salvar(EDiario diario) {
        String sql = "INSERT INTO diarios (diarios_local, diarios_disciplinas, qtd_alunos, fk_diarios_professores_id, fk_diarios_alunos_id) VALUES (?, ?, ?, ?, ?)";
        try {
            Conexao conexao = new Conexao();
            Connection con = conexao.getConexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, diario.getDiarios_local());
            stmt.setString(2, diario.getDiarios_disciplinas());
            stmt.setInt(3, diario.getQtd_alunos());
            stmt.setInt(4, diario.getFk_diarios_professores_());
            stmt.setInt(5, diario.getFk_diarios_alunos_());
            stmt.executeUpdate();
            return "Diário cadastrado com sucesso!";
        } catch (SQLException e) {
            return "Erro ao inserir diário: " + e.getMessage();
        }
    }

    public String alterar(EDiario diario) {
        String sql = "UPDATE diarios SET diarios_local = ?, diarios_disciplinas = ?, qtd_alunos = ?, fk_diarios_professores_id = ?, fk_diarios_alunos_id = ? WHERE diarios_id = ?";
        try {
            Conexao conexao = new Conexao();
            Connection con = conexao.getConexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, diario.getDiarios_local());
            stmt.setString(2, diario.getDiarios_disciplinas());
            stmt.setInt(3, diario.getQtd_alunos());
            stmt.setInt(4, diario.getFk_diarios_professores_());
            stmt.setInt(5, diario.getFk_diarios_alunos_());
            stmt.setInt(6, diario.getDiarios_id());
            stmt.executeUpdate();
            return "Diário alterado com sucesso!";
        } catch (SQLException e) {
            return "Erro ao alterar diário: " + e.getMessage();
        }
    }

    public String deletar(int diarios_id) {
        String sql = "DELETE FROM diarios WHERE diarios_id = ?";
        try {
            Conexao conexao = new Conexao();
            Connection con = conexao.getConexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, diarios_id);
            stmt.executeUpdate();
            return "Diário deletado com sucesso!";
        } catch (SQLException e) {
            return "Erro ao deletar diário: " + e.getMessage();
        }
    }

    public ArrayList<EDiario> listar(String filtro) {
        ArrayList<EDiario> lista = new ArrayList<>();
        String sql = "SELECT * FROM diarios WHERE diarios_local LIKE ?";
        try {
            Conexao conexao = new Conexao();
            Connection con = conexao.getConexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + filtro + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                EDiario diario = new EDiario();
                diario.setDiarios_id(rs.getInt("diarios_id"));
                diario.setDiarios_local(rs.getString("diarios_local"));
                diario.setDiarios_disciplinas(rs.getString("diarios_disciplinas"));
                diario.setQtd_alunos(rs.getInt("qtd_alunos"));
                diario.setFk_diarios_professores_(rs.getInt("fk_diarios_professores_id"));
                diario.setFk_diarios_alunos_(rs.getInt("fk_diarios_alunos_id"));
                lista.add(diario);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar diário: " + e.getMessage());
        }
        return lista;
    }
}

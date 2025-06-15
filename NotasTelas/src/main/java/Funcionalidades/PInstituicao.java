package Funcionalidades;

import conexao.Conexao;
import Funcionalidades.EInstituicao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PInstituicao {
    
    public List<Object[]> listarInstituicoes() {
        List<Object[]> lista = new ArrayList<>();
        String sql = "SELECT instituicao_id, instituicao_nome, instituicao_endereco, instituicao_cidade, instituicao_uf, instituicao_escolaridade, instituicao_nivel FROM instituicao";

        try (Connection conexao = new Conexao().getConexao(); PreparedStatement stmt = conexao.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("instituicao_id");
                String nome = rs.getString("instituicao_nome");
                String endereco = rs.getString("instituicao_endereco");
                String cidade = rs.getString("instituicao_cidade");
                String uf = rs.getString("instituicao_uf");
                String escolaridade = rs.getString("instituicao_escolaridade");
                float nivel = rs.getFloat("instituicao_nivel");

                lista.add(new Object[]{id, nome, endereco, cidade, uf, escolaridade, nivel});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    public void inserirInstituicao(EInstituicao inst) {
        String sql = "INSERT INTO instituicao (instituicao_nome, instituicao_endereco, instituicao_cidade, instituicao_uf, instituicao_escolaridade, instituicao_nivel) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = new Conexao().getConexao(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, inst.getInst_nome());
            stmt.setString(2, inst.getInst_endereco());
            stmt.setString(3, inst.getInst_cidade());
            stmt.setString(4, inst.getInst_uf());
            stmt.setString(5, inst.getInst_escolaridade());
            stmt.setFloat(6, inst.getInst_nivel());

            stmt.executeUpdate();
            System.out.println("Instituição inserida com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir instituição: " + e.getMessage());
        }
    }

    public void atualizarInstituicao(int id, EInstituicao inst) {
        String sql = "UPDATE instituicao SET instituicao_nome = ?, instituicao_endereco = ?, instituicao_cidade = ?, instituicao_uf = ?, instituicao_escolaridade = ?, instituicao_nivel = ? WHERE instituicao_id = ?";

        try (Connection conexao = new Conexao().getConexao(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, inst.getInst_nome());
            stmt.setString(2, inst.getInst_endereco());
            stmt.setString(3, inst.getInst_cidade());
            stmt.setString(4, inst.getInst_uf());
            stmt.setString(5, inst.getInst_escolaridade());
            stmt.setFloat(6, inst.getInst_nivel());
            stmt.setInt(7, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Object[]> buscarInstituicoesPorNome(String nome) {
        List<Object[]> lista = new ArrayList<>();
        String sql = "SELECT instituicao_id, instituicao_nome, instituicao_endereco, instituicao_cidade, instituicao_uf, instituicao_escolaridade, instituicao_nivel "
                + "FROM instituicao WHERE instituicao_nome LIKE ? ORDER BY instituicao_nome";

        try (Connection conexao = new Conexao().getConexao(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, "%" + nome + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("instituicao_id");
                    String instNome = rs.getString("instituicao_nome");
                    String endereco = rs.getString("instituicao_endereco");
                    String cidade = rs.getString("instituicao_cidade");
                    String uf = rs.getString("instituicao_uf");
                    String escolaridade = rs.getString("instituicao_escolaridade");
                    float nivel = rs.getFloat("instituicao_nivel");

                    lista.add(new Object[]{id, instNome, endereco, cidade, uf, escolaridade, nivel});
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }


    public void excluirInstituicao(int id) {
        String sql = "DELETE FROM instituicao WHERE instituicao_id = ?";

        try (Connection conexao = new Conexao().getConexao(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void gerarArquivoInstituicoes() throws IOException{
        List<Object[]> lista = listarInstituicoes();
        FileWriter writer = new FileWriter("instituicoes.txt");

        writer.write("ID | Nome | Endereço | Cidade | UF | Escolaridade | Nível\n");
        writer.write("------------------------------------------------------------\n");

        for (Object[] obj : lista) {
            writer.write(
                    obj[0] + " | " + obj[1] + " | " + obj[2] + " | " + obj[3] + " | " +
                    obj[4] + " | " + obj[5] + " | " + obj[6] + "\n"
            );
        }

        writer.close();
    }
}

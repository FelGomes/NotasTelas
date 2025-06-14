/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funcionalidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import conexao.Conexao;
import java.sql.Connection;

/**
 *
 * @author felipe
 */


public class PUsuarios {

    // Listar todos os usuários
    public ArrayList<String[]> listarUsuarios() throws SQLException {
        ArrayList<String[]> dadosUsuarios = new ArrayList<>();

        String sql = "SELECT usuarios_id, usuarios_nome, usuarios_sexo, usuarios_cpf, usuarios_endereco, usuarios_nascimento "
                + "FROM usuarios ORDER BY usuarios_nome";

        try (Connection conexao = new Conexao().getConexao(); PreparedStatement stmt = conexao.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                dadosUsuarios.add(new String[]{
                    rs.getString("usuarios_id"),
                    rs.getString("usuarios_nome"),
                    rs.getString("usuarios_sexo"),
                    rs.getString("usuarios_cpf"),
                    rs.getString("usuarios_endereco"),
                    rs.getString("usuarios_nascimento")
                });
            }
        }

        return dadosUsuarios;
    }

    // Buscar usuários por nome
    public ArrayList<String[]> buscarUsuariosPorNome(String nomeBusca) throws SQLException {
        ArrayList<String[]> dadosUsuarios = new ArrayList<>();

        String sql = "SELECT usuarios_id, usuarios_nome, usuarios_sexo, usuarios_cpf, usuarios_endereco, usuarios_nascimento "
                + "FROM usuarios WHERE UPPER(usuarios_nome) LIKE ? ORDER BY usuarios_nome";

        try (Connection conexao = new Conexao().getConexao(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, "%" + nomeBusca.toUpperCase().trim() + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    dadosUsuarios.add(new String[]{
                        rs.getString("usuarios_id"),
                        rs.getString("usuarios_nome"),
                        rs.getString("usuarios_sexo"),
                        rs.getString("usuarios_cpf"),
                        rs.getString("usuarios_endereco"),
                        rs.getString("usuarios_nascimento")
                    });
                }
            }
        }

        return dadosUsuarios;
    }

    // Inserir usuário
    public String incluirUsuario(String nome, String sexo, String cpf, String endereco, String dataNasc) {
        String sql = "INSERT INTO usuarios (usuarios_nome, usuarios_sexo, usuarios_cpf, usuarios_endereco, usuarios_nascimento) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conexao = new Conexao().getConexao(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, nome.toUpperCase());
            stmt.setString(2, sexo.toUpperCase());
            stmt.setString(3, cpf.toUpperCase());
            stmt.setString(4, endereco.toUpperCase());
            stmt.setString(5, dataNasc.toUpperCase());

            stmt.executeUpdate();
            return "Inclusão efetuada com sucesso!";

        } catch (SQLException e) {
            return "Erro na inclusão: " + e.getMessage();
        }
    }

    // Atualizar usuário usando o ID
    public String alterarUsuario(int id, String nome, String sexo, String endereco, String dataNasc) {
        String sql = "UPDATE usuarios SET usuarios_nome = ?, usuarios_sexo = ?, usuarios_endereco = ?, usuarios_nascimento = ? "
                + "WHERE usuarios_id = ?";

        try (Connection conexao = new Conexao().getConexao(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, nome.toUpperCase());
            stmt.setString(2, sexo.toUpperCase());
            stmt.setString(3, endereco.toUpperCase());
            stmt.setString(4, dataNasc.toUpperCase());
            stmt.setInt(5, id);

            stmt.executeUpdate();
            return "Alteração efetuada com sucesso!";

        } catch (SQLException e) {
            return "Erro na alteração: " + e.getMessage();
        }
    }

    // Excluir usuário pelo ID
    public String excluirUsuario(int id) {
        String sql = "DELETE FROM usuarios WHERE usuarios_id = ?";

        try (Connection conexao = new Conexao().getConexao(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            return "Exclusão efetuada com sucesso!";

        } catch (SQLException e) {
            return "Erro na exclusão: " + e.getMessage();
        }
    }
}

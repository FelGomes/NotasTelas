package Funcionalidades;

import conexao.Conexao;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PNotas {

    public List<Object[]> listarUsuarios() {
        List<Object[]> lista = new ArrayList<>();
        String sql = "SELECT usuarios_id, usuarios_nome, usuarios_cpf FROM usuarios";
        try (Connection conexao = new Conexao().getConexao(); 
             PreparedStatement stmt = conexao.prepareStatement(sql); 
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getInt("usuarios_id"),
                    rs.getString("usuarios_nome"),
                    rs.getString("usuarios_cpf")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Object[]> listarAlunos() {
        List<Object[]> lista = new ArrayList<>();
        String sql = "SELECT a.alunos_id AS id, u.usuarios_nome FROM alunos a JOIN usuarios u ON a.fk_alunos_usuarios_id = u.usuarios_id";
        try (Connection conexao = new Conexao().getConexao(); 
             PreparedStatement stmt = conexao.prepareStatement(sql); 
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getInt("id"),           // usar alias do SELECT
                    rs.getString("usuarios_nome")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Object[]> listarProfessores() {
        List<Object[]> lista = new ArrayList<>();
        String sql = "SELECT p.professores_id AS id, u.usuarios_nome, p.professores_disciplina FROM professores p JOIN usuarios u ON p.fk_professores_usuarios_id = u.usuarios_id";
        try (Connection conexao = new Conexao().getConexao(); 
             PreparedStatement stmt = conexao.prepareStatement(sql); 
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getInt("id"),           // usar alias do SELECT
                    rs.getString("usuarios_nome"),
                    rs.getString("professores_disciplina")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void inserirNota(int idAluno, int idProfessor, double n1, double n2, double n3, double n4, String disciplina) {
        double media = (n1 + n2 + n3 + n4) / 4.0;
        String sql = """
            INSERT INTO notas (nota_um, nota_dois, nota_tres, nota_quatro, nota_media, nota_disciplina, fk_notas_alunos_id, fk_notas_professores_id)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;
        try (Connection conexao = new Conexao().getConexao(); 
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDouble(1, n1);
            stmt.setDouble(2, n2);
            stmt.setDouble(3, n3);
            stmt.setDouble(4, n4);
            stmt.setDouble(5, media);
            stmt.setString(6, disciplina);
            stmt.setInt(7, idAluno);
            stmt.setInt(8, idProfessor);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarNota(int idAluno, int idProfessor, double n1, double n2, double n3, double n4, String disciplina) {
        double media = (n1 + n2 + n3 + n4) / 4.0;
        String sql = """
            UPDATE notas
            SET nota_um = ?, nota_dois = ?, nota_tres = ?, nota_quatro = ?, nota_media = ?, nota_disciplina = ?
            WHERE fk_notas_alunos_id = ? AND fk_notas_professores_id = ?
        """;
        try (Connection conexao = new Conexao().getConexao(); 
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDouble(1, n1);
            stmt.setDouble(2, n2);
            stmt.setDouble(3, n3);
            stmt.setDouble(4, n4);
            stmt.setDouble(5, media);
            stmt.setString(6, disciplina);
            stmt.setInt(7, idAluno);
            stmt.setInt(8, idProfessor);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Object[]> listarNotas() {
        List<Object[]> lista = new ArrayList<>();
        String sql = """
        SELECT 
            a.alunos_id AS idAluno,
            ua.usuarios_nome AS nomeAluno,
            p.professores_id AS idProfessor,
            up.usuarios_nome AS nomeProfessor,
            n.nota_um,
            n.nota_dois,
            n.nota_tres,
            n.nota_quatro,
            n.nota_media,
            p.professores_disciplina AS disciplina
        FROM notas n
        JOIN alunos a ON n.fk_notas_alunos_id = a.alunos_id
        JOIN usuarios ua ON a.fk_alunos_usuarios_id = ua.usuarios_id
        JOIN professores p ON n.fk_notas_professores_id = p.professores_id
        JOIN usuarios up ON p.fk_professores_usuarios_id = up.usuarios_id
    """;
        try (Connection conexao = new Conexao().getConexao(); PreparedStatement stmt = conexao.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getInt("idAluno"),
                    rs.getString("nomeAluno"),
                    rs.getInt("idProfessor"),
                    rs.getString("nomeProfessor"),
                    rs.getDouble("nota_um"),
                    rs.getDouble("nota_dois"),
                    rs.getDouble("nota_tres"),
                    rs.getDouble("nota_quatro"),
                    rs.getDouble("nota_media"),
                    rs.getString("disciplina")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }


    public void excluirNota(int idAluno, int idProfessor) {
        String sql = "DELETE FROM notas WHERE fk_notas_alunos_id = ? AND fk_notas_professores_id = ?";
        try (Connection conexao = new Conexao().getConexao(); 
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idAluno);
            stmt.setInt(2, idProfessor);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean alunoExiste(int id) {
        String sql = "SELECT 1 FROM alunos WHERE alunos_id = ?";
        try (Connection conexao = new Conexao().getConexao(); 
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean professorExiste(int id) {
        String sql = "SELECT 1 FROM professores WHERE professores_id = ?";
        try (Connection conexao = new Conexao().getConexao(); 
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    

    public List<Object[]> buscarNotasPorDisciplina(String disciplina) {
        List<Object[]> lista = new ArrayList<>();
        String sql = """
        SELECT 
            a.alunos_id AS idAluno,
            ua.usuarios_nome AS nomeAluno,
            p.professores_id AS idProfessor,
            up.usuarios_nome AS nomeProfessor,
            n.nota_um,
            n.nota_dois,
            n.nota_tres,
            n.nota_quatro,
            n.nota_media,
            p.professores_disciplina AS disciplina
        FROM notas n
        JOIN alunos a ON n.fk_notas_alunos_id = a.alunos_id
        JOIN usuarios ua ON a.fk_alunos_usuarios_id = ua.usuarios_id
        JOIN professores p ON n.fk_notas_professores_id = p.professores_id
        JOIN usuarios up ON p.fk_professores_usuarios_id = up.usuarios_id
        WHERE p.professores_disciplina LIKE ?
    """;

        try (Connection conexao = new Conexao().getConexao(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, "%" + disciplina + "%"); // busca parcial (LIKE)

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Object[]{
                        rs.getInt("idAluno"),
                        rs.getString("nomeAluno"),
                        rs.getInt("idProfessor"),
                        rs.getString("nomeProfessor"),
                        rs.getDouble("nota_um"),
                        rs.getDouble("nota_dois"),
                        rs.getDouble("nota_tres"),
                        rs.getDouble("nota_quatro"),
                        rs.getDouble("nota_media"),
                        rs.getString("disciplina")
                    });
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }


    
    
    public void gerarArquivoNotas() throws IOException {
        List<Object[]> lista = listarNotas();
        FileWriter writer = new FileWriter("notas.txt");

        writer.write("ID Aluno | Nome Aluno | ID Professor | Nome Professor | Nota 1 | Nota 2 | Nota 3 | Nota 4 | Média | Disciplina\n");
        writer.write("-------------------------------------------------------------------------------------------------------------\n");

        for (Object[] obj : lista) {
            writer.write(
                    obj[0] + " | "
                    + // ID Aluno
                    obj[1] + " | "
                    + // Nome Aluno
                    obj[2] + " | "
                    + // ID Professor
                    obj[3] + " | "
                    + // Nome Professor
                    obj[4] + " | "
                    + // Nota 1
                    obj[5] + " | "
                    + // Nota 2
                    obj[6] + " | "
                    + // Nota 3
                    obj[7] + " | "
                    + // Nota 4
                    obj[8] + " | "
                    + // Média
                    obj[9] + "\n" // Disciplina
            );
        }

        writer.close();
    }

}

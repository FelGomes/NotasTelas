package Funcionalidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import conexao.Conexao;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import Funcionalidades.EFrequencias;
import Funcionalidades.EProfessor;
import Funcionalidades.EAlunos;

public class PFrequencias {

public String gerarArquivoFrequencias(List<EFrequencias> lista) {
    String nomeArquivo = "frequencias_exportadas.csv";
    try (FileWriter writer = new FileWriter(nomeArquivo)) {
        writer.write("ID;Total Aulas;Aulas Ministradas;Faltas;% Presença;Disciplina;ID Professor;ID Aluno\n");
        for (EFrequencias f : lista) {
            writer.write(
                f.getFrequencias_id() + ";" +
                f.getTotal_aulas() + ";" +
                f.getAulas_ministradas() + ";" +
                f.getFrequencias_faltas() + ";" +
                f.getPrctg_presenca() + ";" +
                f.getFrequencias_disciplinas() + ";" +
                (f.getProfessores() != null ? f.getProfessores().getIdProfessor() : "") + ";" +
                (f.getAluno() != null ? f.getAluno().getUsuario_id() : "") + "\n"
            );
        }
        return "Arquivo gerado com sucesso: " + nomeArquivo;
    } catch (IOException e) {
        return "Erro ao gerar arquivo: " + e.getMessage();
    }
}


    // Listar todas as frequências
    public ArrayList<EFrequencias> listarFrequencias() {
        ArrayList<EFrequencias> lista = new ArrayList<>();

        String sql = "SELECT f.frequencias_id, "
                + "f.aulas_ministradas, "
                + "f.frequencias_faltas, "
                + "f.prctg_presenca, "
                + "f.frequencias_disciplinas, "
                + "f.total_aulas, "
                + "p.professores_id AS professor_id, "
                + "a.alunos_id AS aluno_id, "
                + "uprof.usuarios_nome AS professor_nome, "
                + "ualuno.usuarios_nome AS aluno_nome "
                + "FROM frequencias f "
                + "INNER JOIN professores p ON f.fk_frequencias_professores_id = p.professores_id "
                + "INNER JOIN usuarios uprof ON p.fk_professores_usuarios_id = uprof.usuarios_id "
                + "INNER JOIN alunos a ON f.fk_frequencias_alunos_id = a.alunos_id "
                + "INNER JOIN usuarios ualuno ON a.fk_alunos_usuarios_id = ualuno.usuarios_id";

        try {
            Conexao conexao = new Conexao();
            Connection con = conexao.getConexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                EFrequencias freq = new EFrequencias();
                freq.setFrequencias_id(rs.getInt("frequencias_id"));
                freq.setAulas_ministradas(rs.getInt("aulas_ministradas"));
                freq.setFrequencias_faltas(rs.getInt("frequencias_faltas"));
                freq.setPrctg_presenca(rs.getFloat("prctg_presenca"));
                freq.setFrequencias_disciplinas(rs.getString("frequencias_disciplinas"));
                freq.setTotal_aulas(rs.getInt("total_aulas"));

                // Professor
                EProfessor prof = new EProfessor();
                prof.setIdProfessor(rs.getInt("professor_id"));
                freq.setProfessores(prof);

                // Aluno
                EAlunos aluno = new EAlunos();
                aluno.setUsuario_id(rs.getInt("aluno_id"));
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
        String sql = "INSERT INTO frequencias (total_aulas, aulas_ministradas, frequencias_faltas, prctg_presenca, frequencias_disciplinas,fk_frequencias_professores_id, fk_frequencias_alunos_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Conexao conexao = new Conexao();
            Connection con = conexao.getConexao();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, freq.getTotal_aulas());
            stmt.setInt(2, freq.getAulas_ministradas());
            stmt.setInt(3, freq.getFrequencias_faltas());
            stmt.setFloat(4, freq.getPrctg_presenca());
            stmt.setString(5, freq.getFrequencias_disciplinas());
            stmt.setInt(6, freq.getProfessores().getIdProfessor());
            stmt.setInt(7, freq.getAluno().getUsuario_id());
            stmt.executeUpdate();
            return "Inclusão efetuada com sucesso!";

        } catch (SQLException e) {
            return "Erro na inclusão: " + e.getMessage();
        }
    }

    public String alterarFrequencia(EFrequencias freq) {
        String sql = "UPDATE frequencias SET total_aulas = ?, aulas_ministradas = ?, frequencias_faltas = ?, frequencias_disciplinas = ?, fk_frequencias_professores_id = ?, fk_frequencias_alunos_id = ? WHERE frequencias_id = ?";
        try {
            Connection con = new Conexao().getConexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, freq.getTotal_aulas());
            stmt.setInt(2, freq.getAulas_ministradas());
            stmt.setInt(3, freq.getFrequencias_faltas());
            stmt.setString(4, freq.getFrequencias_disciplinas());
            stmt.setInt(5, freq.getProfessores().getIdProfessor());
            stmt.setInt(6, freq.getAluno().getUsuario_id());
            stmt.setInt(7, freq.getFrequencias_id());
            stmt.executeUpdate();
            return "Frequência alterada com sucesso!";
        } catch (SQLException e) {
            return "Erro ao alterar frequência: " + e.getMessage();
        }
    }

    public String excluirFrequencia(int idFrequencia) {
        String sql = "DELETE FROM frequencias WHERE frequencias_id = ?";
        try {
            Connection con = new Conexao().getConexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idFrequencia);
            stmt.executeUpdate();
            return "Frequência excluída com sucesso!";
        } catch (SQLException e) {
            return "Erro ao excluir frequência: " + e.getMessage();
        }
    }
}
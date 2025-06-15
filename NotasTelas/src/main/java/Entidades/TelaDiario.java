
package Entidades;

/**
 *
 * @author Kauã Luiz
 */
import Funcionalidades.EDiario;
import Funcionalidades.PDiario;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;

public class TelaDiario {
    public static void montarTelaDiario() throws IOException, SQLException {

        JFrame janela = new JFrame("Gerenciar Diário");
        janela.setBounds(200, 150, 650, 550);
        janela.setLayout(null);

        // Labels e campos
        JLabel labelLocal = new JLabel("Local:");
        labelLocal.setBounds(20, 20, 80, 25);
        janela.add(labelLocal);

        JTextField campoLocal = new JTextField();
        campoLocal.setBounds(100, 20, 200, 25);
        janela.add(campoLocal);

        JLabel labelDisciplinas = new JLabel("Disciplinas:");
        labelDisciplinas.setBounds(20, 60, 80, 25);
        janela.add(labelDisciplinas);

        JTextField campoDisciplinas = new JTextField();
        campoDisciplinas.setBounds(100, 60, 200, 25);
        janela.add(campoDisciplinas);

        JLabel labelQtdAlunos = new JLabel("Qtd Alunos:");
        labelQtdAlunos.setBounds(20, 100, 80, 25);
        janela.add(labelQtdAlunos);

        JTextField campoQtdAlunos = new JTextField();
        campoQtdAlunos.setBounds(100, 100, 200, 25);
        janela.add(campoQtdAlunos);

        JLabel labelFkProfessor = new JLabel("ID Professor:");
        labelFkProfessor.setBounds(320, 20, 100, 25);
        janela.add(labelFkProfessor);

        JTextField campoFkProfessor = new JTextField();
        campoFkProfessor.setBounds(420, 20, 200, 25);
        janela.add(campoFkProfessor);

        JLabel labelFkAluno = new JLabel("ID Aluno:");
        labelFkAluno.setBounds(320, 60, 100, 25);
        janela.add(labelFkAluno);

        JTextField campoFkAluno = new JTextField();
        campoFkAluno.setBounds(420, 60, 200, 25);
        janela.add(campoFkAluno);

        // Botões
        JButton botaoSalvar = new JButton("SALVAR");
        botaoSalvar.setBounds(320, 100, 90, 25);
        janela.add(botaoSalvar);

        JButton botaoAlterar = new JButton("ALTERAR");
        botaoAlterar.setBounds(420, 100, 90, 25);
        botaoAlterar.setEnabled(false);
        janela.add(botaoAlterar);

        JButton botaoExcluir = new JButton("EXCLUIR");
        botaoExcluir.setBounds(520, 100, 90, 25);
        botaoExcluir.setEnabled(false);
        janela.add(botaoExcluir);

        JLabel labelFiltro = new JLabel("Filtrar (Local):");
        labelFiltro.setBounds(20, 140, 120, 25);
        janela.add(labelFiltro);

        JTextField campoFiltro = new JTextField();
        campoFiltro.setBounds(140, 140, 160, 25);
        janela.add(campoFiltro);

        JButton botaoFiltrar = new JButton("FILTRAR");
        botaoFiltrar.setBounds(320, 140, 90, 25);
        janela.add(botaoFiltrar);

        JButton botaoGerarArquivo = new JButton("GERAR ARQ");
        botaoGerarArquivo.setBounds(420, 140, 90, 25);
        janela.add(botaoGerarArquivo);

        JButton botaoCancelar = new JButton("CANCELAR");
        botaoCancelar.setBounds(520, 140, 90, 25);
        janela.add(botaoCancelar);
        
    }
}
